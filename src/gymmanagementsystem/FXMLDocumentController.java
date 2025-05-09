/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gymmanagementsystem;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 *
 * @author MarcoMan
 * Subscribe our YouTube Channel: https://www.youtube.com/@marcomanchannel
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private AnchorPane main_form;

    @FXML
    private AnchorPane sub_form;

    @FXML
    private Button sub_signupBtn;

    @FXML
    private Button sub_loginBtn;

    @FXML
    private Label edit_label;

    @FXML
    private AnchorPane signup_form;

    @FXML
    private TextField su_email;

    @FXML
    private TextField su_username;

    @FXML
    private PasswordField su_password;

    @FXML
    private Button su_signupBtn;

    @FXML
    private AnchorPane login_form;

    @FXML
    private TextField si_username;

    @FXML
    private PasswordField si_password;

    @FXML
    private Button si_loginBtn;

    @FXML
    private Button close;

    @FXML
    private FontAwesomeIcon close_icon;

//   DATABASE TOOLS 
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    public void login() {

        String sql = "SELECT * FROM admin WHERE username = ?";

        connect = database.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, si_username.getText());
            result = prepare.executeQuery();

            Alert alert;

            if (si_username.getText().isEmpty() || si_password.getText().isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                if (result.next()) {
                    // Get the stored password hash
                    String storedPassword = result.getString("password");
                    
                    // Check if the password needs migration (not in hashed format)
                    if (!storedPassword.contains(":")) {
                        // Old plain text password format
                        if (storedPassword.equals(si_password.getText())) {
                            // Password matches, migrate to new format
                            String hashedPassword = passwordUtil.hashPassword(si_password.getText());
                            String updateSql = "UPDATE admin SET password = ? WHERE username = ?";
                            PreparedStatement updatePs = connect.prepareStatement(updateSql);
                            updatePs.setString(1, hashedPassword);
                            updatePs.setString(2, si_username.getText());
                            updatePs.executeUpdate();
                            updatePs.close();
                            
                            // Continue with login
                            storedPassword = hashedPassword;
                        } else {
                            // Wrong password
                            alert = new Alert(AlertType.ERROR);
                            alert.setTitle("Error Message");
                            alert.setHeaderText(null);
                            alert.setContentText("Wrong Username/Password");
                            alert.showAndWait();
                            return;
                        }
                    }
                    
                    // Verify the password using secure verification
                    if (storedPassword.contains(":") && 
                            passwordUtil.checkPassword(si_password.getText(), storedPassword)) {
                        
                        // Store user info in data class for the session
                        data.username = si_username.getText();
                        data.userId = result.getInt("id");
                        data.userRole = result.getString("role");
                        data.userFullName = result.getString("fullName");
                        
                        // Update last login time
                        String updateLoginSql = "UPDATE admin SET lastLogin = CURRENT_TIMESTAMP WHERE username = ?";
                        PreparedStatement updateLoginPs = connect.prepareStatement(updateLoginSql);
                        updateLoginPs.setString(1, si_username.getText());
                        updateLoginPs.executeUpdate();
                        updateLoginPs.close();
                        
                        // Log the login activity
                        String logSql = "INSERT INTO activity_log (username, action, module, details) VALUES (?, ?, ?, ?)";
                        PreparedStatement logPs = connect.prepareStatement(logSql);
                        logPs.setString(1, si_username.getText());
                        logPs.setString(2, "Login");
                        logPs.setString(3, "Authentication");
                        logPs.setString(4, "User logged in to the system");
                        logPs.executeUpdate();
                        logPs.close();

                        alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Information Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Successfully Login!");
                        alert.showAndWait();

                        si_loginBtn.getScene().getWindow().hide();

                        // LINK YOUR DASHBOARD FORM 
                        Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));

                        Stage stage = new Stage();
                        Scene scene = new Scene(root);

                        stage.initStyle(StageStyle.TRANSPARENT);

                        stage.setScene(scene);
                        stage.show();
                    } else {
                        alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Wrong Username/Password");
                        alert.showAndWait();
                    }
                } else {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Wrong Username/Password");
                    alert.showAndWait();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void signup() {

        String sql = "INSERT INTO admin (email, username, password, fullName, role, status, createdBy) VALUES(?,?,?,?,?,?,?)";

        connect = database.connectDb();

        try {
            Alert alert;

            if (su_email.getText().isEmpty() || su_username.getText().isEmpty()
                    || su_password.getText().isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                // Validate password strength
                if (!passwordUtil.isStrongPassword(su_password.getText())) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Password must be at least 8 characters long and contain a mix of uppercase, lowercase, digits, and special characters.");
                    alert.showAndWait();
                } else {
                    // Check if username already exists
                    String checkUser = "SELECT username FROM admin WHERE username = ?";
                    PreparedStatement checkPs = connect.prepareStatement(checkUser);
                    checkPs.setString(1, su_username.getText());
                    ResultSet checkResult = checkPs.executeQuery();
                    
                    if (checkResult.next()) {
                        alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Username already exists. Please choose a different username.");
                        alert.showAndWait();
                    } else {
                        // Hash the password
                        String hashedPassword = passwordUtil.hashPassword(su_password.getText());
                        
                        prepare = connect.prepareStatement(sql);
                        prepare.setString(1, su_email.getText());
                        prepare.setString(2, su_username.getText());
                        prepare.setString(3, hashedPassword);
                        prepare.setString(4, su_username.getText()); // Use username as fullName by default
                        prepare.setString(5, "STAFF"); // Default role
                        prepare.setString(6, "active"); // Default status
                        prepare.setString(7, "self-registration"); // Created by
                        
                        // Insert the new user
                        prepare.executeUpdate();
                        
                        // Log the signup activity
                        String logSql = "INSERT INTO activity_log (username, action, module, details) VALUES (?, ?, ?, ?)";
                        PreparedStatement logPs = connect.prepareStatement(logSql);
                        logPs.setString(1, su_username.getText());
                        logPs.setString(2, "Registration");
                        logPs.setString(3, "Authentication");
                        logPs.setString(4, "New user account created");
                        logPs.executeUpdate();
                        logPs.close();

                        alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Information Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Account created successfully! You can now login.");
                        alert.showAndWait();

                        su_email.setText("");
                        su_username.setText("");
                        su_password.setText("");
                        
                        // Switch to login form
                        signupSlider();
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Error: " + e.getMessage());
            alert.showAndWait();
        }
    }

    public void signupSlider() {

        TranslateTransition slider1 = new TranslateTransition();
        slider1.setNode(sub_form);
        slider1.setToX(300);
        slider1.setDuration(Duration.seconds(.5));
        slider1.play();

        slider1.setOnFinished((ActionEvent event) -> {
            edit_label.setText("Login Account");

            sub_signupBtn.setVisible(false);
            sub_loginBtn.setVisible(true);

            close_icon.setFill(Color.valueOf("#fff"));
        });

    }

    public void loginSlider() {

        TranslateTransition slider1 = new TranslateTransition();
        slider1.setNode(sub_form);
        slider1.setToX(0);
        slider1.setDuration(Duration.seconds(.5));
        slider1.play();

        slider1.setOnFinished((ActionEvent event) -> {
            edit_label.setText("Create Account");

            sub_signupBtn.setVisible(true);
            sub_loginBtn.setVisible(false);
            close_icon.setFill(Color.valueOf("#000"));
        });

    }

    public void close() {
        javafx.application.Platform.exit();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
