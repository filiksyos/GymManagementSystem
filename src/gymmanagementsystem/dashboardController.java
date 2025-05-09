/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gymmanagementsystem;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author MarcoMan
 * Subscribe our YouTube Channel: https://www.youtube.com/@marcomanchannel
 */
public class dashboardController implements Initializable {

    @FXML
    private AnchorPane main_form;

    @FXML
    private Button close;

    @FXML
    private Button minimize;

    @FXML
    private Button dashboard_btn;

    @FXML
    private Button coaches_btn;

    @FXML
    private Button members_btn;

    @FXML
    private Button logout;

    @FXML
    private Button payment_btn;
    
    @FXML
    private Button equipment_btn;

    @FXML
    private Button schedule_btn;

    @FXML
    private Button reports_btn;

    @FXML
    private Button admin_btn;

    @FXML
    private AnchorPane dashboard_form;

    @FXML
    private Label dashboard_NM;

    @FXML
    private Label dashboard_NC;

    @FXML
    private Label dashboard_TI;

    @FXML
    private AreaChart<?, ?> dashboard_incomeChart;

    @FXML
    private AnchorPane coaches_form;

    @FXML
    private TextField coaches_coachID;

    @FXML
    private TextField coaches_name;

    @FXML
    private TextArea coaches_address;

    @FXML
    private ComboBox<?> coaches_gender;

    @FXML
    private TextField coaches_phoneNum;

    @FXML
    private Button coaches_addBtn;

    @FXML
    private Button coaches_updateBtn;

    @FXML
    private Button coaches_resetBtn;

    @FXML
    private Button coaches_deleteBtn;

    @FXML
    private ComboBox<?> coaches_status;

    @FXML
    private TableView<coachData> coaches_tableView;

    @FXML
    private TableColumn<coachData, String> coaches_col_coachID;

    @FXML
    private TableColumn<coachData, String> coaches_col_name;

    @FXML
    private TableColumn<coachData, String> coaches_col_address;

    @FXML
    private TableColumn<coachData, String> coaches_col_gender;

    @FXML
    private TableColumn<coachData, String> coaches_col_phoneNum;

    @FXML
    private TableColumn<coachData, String> coaches_col_status;
    
    // Equipment form components
    @FXML
    private AnchorPane equipment_form;
    
    @FXML
    private TextField equipment_id;
    
    @FXML
    private TextField equipment_name;
    
    @FXML
    private TextField equipment_type;
    
    @FXML
    private DatePicker equipment_purchaseDate;
    
    @FXML
    private DatePicker equipment_lastMaintenance;
    
    @FXML
    private DatePicker equipment_nextMaintenance;
    
    @FXML
    private ComboBox<?> equipment_status;
    
    @FXML
    private Button equipment_addBtn;
    
    @FXML
    private Button equipment_updateBtn;
    
    @FXML
    private Button equipment_resetBtn;
    
    @FXML
    private Button equipment_deleteBtn;
    
    @FXML
    private TableView<equipmentData> equipment_tableView;
    
    @FXML
    private TableColumn<equipmentData, String> equipment_col_id;
    
    @FXML
    private TableColumn<equipmentData, String> equipment_col_name;
    
    @FXML
    private TableColumn<equipmentData, String> equipment_col_type;
    
    @FXML
    private TableColumn<equipmentData, String> equipment_col_purchaseDate;
    
    @FXML
    private TableColumn<equipmentData, String> equipment_col_lastMaintenance;
    
    @FXML
    private TableColumn<equipmentData, String> equipment_col_nextMaintenance;
    
    @FXML
    private TableColumn<equipmentData, String> equipment_col_status;

    @FXML
    private AnchorPane members_form;

    @FXML
    private TextField members_customerId;

    @FXML
    private TextField members_name;

    @FXML
    private TextArea members_caddress;

    @FXML
    private TextField members_phoneNum;

    @FXML
    private ComboBox<?> members_gender;

    @FXML
    private ComboBox<?> members_schedule;

    @FXML
    private DatePicker members_startDate;

    @FXML
    private DatePicker members_endDate;

    @FXML
    private ComboBox<?> members_status;

    @FXML
    private Button members_addBtn;

    @FXML
    private Button members_clearBtn;

    @FXML
    private Button members_updateBtn;

    @FXML
    private Button members_deleteBtn;

    @FXML
    private TableView<memberData> members_tableView;

    @FXML
    private TableColumn<memberData, String> members_col_customerID;

    @FXML
    private TableColumn<memberData, String> members_col_name;

    @FXML
    private TableColumn<memberData, String> members_col_address;

    @FXML
    private TableColumn<memberData, String> members_col_phoneNum;

    @FXML
    private TableColumn<memberData, String> members_col_gender;

    @FXML
    private TableColumn<memberData, String> members_col_schedule;

    @FXML
    private TableColumn<memberData, String> members_col_startDate;

    @FXML
    private TableColumn<memberData, String> members_col_endDate;

    @FXML
    private TableColumn<memberData, String> members_col_status;

    @FXML
    private AnchorPane payment_Form;

    @FXML
    private TableView<memberData> payment_tableView;

    @FXML
    private TableColumn<memberData, String> payment_col_customerID;

    @FXML
    private TableColumn<memberData, String> payment_col_name;

    @FXML
    private TableColumn<memberData, String> payment_col_startDate;

    @FXML
    private TableColumn<memberData, String> payment_col_endDate;

    @FXML
    private TableColumn<memberData, String> payment_col_status;

    @FXML
    private ComboBox<?> payment_customerID;

    @FXML
    private ComboBox<?> payment_name;

    @FXML
    private Label payment_total;

    @FXML
    private TextField payment_amount;

    @FXML
    private Label payment_change;

    @FXML
    private Button payment_payBtn;

    @FXML
    private Label username;

    @FXML
    private AnchorPane schedule_form;

    @FXML
    private AnchorPane reports_form;

    @FXML
    private AnchorPane admin_form;

    @FXML
    private TextField admin_username;

    @FXML
    private PasswordField admin_password;

    @FXML
    private TextField admin_fullName;

    @FXML
    private TextField admin_email;

    @FXML
    private ComboBox<String> admin_role;

    @FXML
    private ComboBox<String> admin_status;

    @FXML
    private Button admin_addBtn;

    @FXML
    private Button admin_updateBtn;

    @FXML
    private Button admin_deleteBtn;

    @FXML
    private Button admin_clearBtn;

    @FXML
    private TextField admin_search;

    @FXML
    private TableView<adminData> admin_tableView;

    @FXML
    private TableColumn<adminData, String> admin_col_username;

    @FXML
    private TableColumn<adminData, String> admin_col_fullName;

    @FXML
    private TableColumn<adminData, String> admin_col_email;

    @FXML
    private TableColumn<adminData, String> admin_col_role;

    @FXML
    private TableColumn<adminData, String> admin_col_status;

    @FXML
    private TableColumn<adminData, Timestamp> admin_col_lastLogin;

    @FXML
    private TableView<activityLogData> admin_logTableView;

    @FXML
    private TableColumn<activityLogData, Timestamp> log_col_timestamp;

    @FXML
    private TableColumn<activityLogData, String> log_col_username;

    @FXML
    private TableColumn<activityLogData, String> log_col_action;

    @FXML
    private TableColumn<activityLogData, String> log_col_module;

    @FXML
    private TableColumn<activityLogData, String> log_col_details;

    // Schedule form components
    @FXML
    private TextField schedule_id;
    
    @FXML
    private TextField schedule_className;
    
    @FXML
    private ComboBox<String> schedule_coachId;
    
    @FXML
    private ComboBox<String> schedule_dayOfWeek;
    
    @FXML
    private TextField schedule_startTime;
    
    @FXML
    private TextField schedule_endTime;
    
    @FXML
    private TextField schedule_maxCapacity;
    
    @FXML
    private ComboBox<String> schedule_status;
    
    @FXML
    private Button schedule_addBtn;
    
    @FXML
    private Button schedule_updateBtn;
    
    @FXML
    private Button schedule_resetBtn;
    
    @FXML
    private Button schedule_deleteBtn;
    
    @FXML
    private TableView<scheduleData> schedule_tableView;
    
    @FXML
    private TableColumn<scheduleData, String> schedule_col_id;
    
    @FXML
    private TableColumn<scheduleData, String> schedule_col_className;
    
    @FXML
    private TableColumn<scheduleData, String> schedule_col_coachId;
    
    @FXML
    private TableColumn<scheduleData, String> schedule_col_dayOfWeek;
    
    @FXML
    private TableColumn<scheduleData, String> schedule_col_timeRange;
    
    @FXML
    private TableColumn<scheduleData, Integer> schedule_col_capacity;
    
    @FXML
    private TableColumn<scheduleData, Integer> schedule_col_enrollment;
    
    @FXML
    private TableColumn<scheduleData, String> schedule_col_status;

    // Reports form components
    @FXML
    private Button report_membership;
    
    @FXML
    private Button report_financial;
    
    @FXML
    private Button report_equipment;
    
    @FXML
    private Button report_schedule;
    
    @FXML
    private Button report_coach;
    
    @FXML
    private DatePicker report_startDate;
    
    @FXML
    private DatePicker report_endDate;
    
    @FXML
    private ComboBox<String> report_interval;
    
    @FXML
    private Button report_generate;
    
    @FXML
    private Button report_export;
    
    @FXML
    private AreaChart<String, Number> report_trendChart;
    
    @FXML
    private BarChart<String, Number> report_comparisonChart;
    
    @FXML
    private PieChart report_distributionChart;
    
    @FXML
    private TableView<reportData> report_detailTable;
    
    @FXML
    private TableColumn<reportData, String> report_col_date;
    
    @FXML
    private TableColumn<reportData, String> report_col_category;
    
    @FXML
    private TableColumn<reportData, Double> report_col_value;
    
    @FXML
    private TableColumn<reportData, Double> report_col_change;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Statement statement;

    private String[] equipmentStatusList = {"Working", "Maintenance", "Out of Order"};
    private String[] daysList = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    private String[] scheduleStatusList = {"Active", "Cancelled", "Full"};

    // Add an attribute to track the current report type
    private String currentReportType = "Membership";

    public void equipmentStatusList() {
        List<String> statusL = new ArrayList<>();
        
        for(String data: equipmentStatusList) {
            statusL.add(data);
        }
        
        ObservableList listData = FXCollections.observableArrayList(statusL);
        equipment_status.setItems(listData);
    }

    public void equipmentAddBtn() {
        String sql = "INSERT INTO equipment (equipmentId, name, type, purchaseDate, lastMaintenance, nextMaintenance, status) "
                + "VALUES(?,?,?,?,?,?,?)";

        connect = database.connectDb();

        try {
            Alert alert;

            if (equipment_id.getText().isEmpty() || equipment_name.getText().isEmpty()
                    || equipment_type.getText().isEmpty() 
                    || equipment_purchaseDate.getValue() == null
                    || equipment_lastMaintenance.getValue() == null
                    || equipment_nextMaintenance.getValue() == null
                    || equipment_status.getSelectionModel().getSelectedItem() == null) {
                emptyFields();
            } else {
                String checkData = "SELECT equipmentId FROM equipment WHERE equipmentId = '"
                        + equipment_id.getText() + "'";

                statement = connect.createStatement();
                result = statement.executeQuery(checkData);

                if (result.next()) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Equipment ID: " + equipment_id.getText() + " already exists!");
                    alert.showAndWait();
                } else {
                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, equipment_id.getText());
                    prepare.setString(2, equipment_name.getText());
                    prepare.setString(3, equipment_type.getText());
                    prepare.setString(4, String.valueOf(equipment_purchaseDate.getValue()));
                    prepare.setString(5, String.valueOf(equipment_lastMaintenance.getValue()));
                    prepare.setString(6, String.valueOf(equipment_nextMaintenance.getValue()));
                    prepare.setString(7, (String) equipment_status.getSelectionModel().getSelectedItem());

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added!");
                    alert.showAndWait();

                    // Insert data
                    prepare.executeUpdate();
                    // Update TableView
                    equipmentShowData();
                    // Clear fields
                    equipmentClearBtn();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void equipmentUpdateBtn() {
        String sql = "UPDATE equipment SET name = '"
                + equipment_name.getText() + "', type = '"
                + equipment_type.getText() + "', purchaseDate = '"
                + equipment_purchaseDate.getValue() + "', lastMaintenance = '"
                + equipment_lastMaintenance.getValue() + "', nextMaintenance = '"
                + equipment_nextMaintenance.getValue() + "', status = '"
                + equipment_status.getSelectionModel().getSelectedItem() + "' WHERE equipmentId = '"
                + equipment_id.getText() + "'";

        connect = database.connectDb();

        try {
            Alert alert;
            if (equipment_id.getText().isEmpty() || equipment_name.getText().isEmpty()
                    || equipment_type.getText().isEmpty() 
                    || equipment_purchaseDate.getValue() == null
                    || equipment_lastMaintenance.getValue() == null
                    || equipment_nextMaintenance.getValue() == null
                    || equipment_status.getSelectionModel().getSelectedItem() == null) {
                emptyFields();
            } else {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE Equipment ID: " + equipment_id.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    prepare = connect.prepareStatement(sql);
                    prepare.executeUpdate();
                    
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();
                    
                    // Update TableView
                    equipmentShowData();
                    // Clear fields
                    equipmentClearBtn();
                } else {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Cancelled Update!");
                    alert.showAndWait();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void equipmentDeleteBtn() {
        String sql = "DELETE FROM equipment WHERE equipmentId = '"
                + equipment_id.getText() + "'";

        connect = database.connectDb();

        try {
            Alert alert;
            if (equipment_id.getText().isEmpty() || equipment_name.getText().isEmpty()
                    || equipment_type.getText().isEmpty() 
                    || equipment_purchaseDate.getValue() == null
                    || equipment_lastMaintenance.getValue() == null
                    || equipment_nextMaintenance.getValue() == null
                    || equipment_status.getSelectionModel().getSelectedItem() == null) {
                emptyFields();
            } else {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to DELETE Equipment ID: " + equipment_id.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    prepare = connect.prepareStatement(sql);
                    prepare.executeUpdate();
                    
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted!");
                    alert.showAndWait();
                    
                    // Update TableView
                    equipmentShowData();
                    // Clear fields
                    equipmentClearBtn();
                } else {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Cancelled Delete!");
                    alert.showAndWait();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void equipmentClearBtn() {
        equipment_id.setText("");
        equipment_name.setText("");
        equipment_type.setText("");
        equipment_purchaseDate.setValue(null);
        equipment_lastMaintenance.setValue(null);
        equipment_nextMaintenance.setValue(null);
        equipment_status.getSelectionModel().clearSelection();
    }

    public ObservableList<equipmentData> equipmentDataList() {
        ObservableList<equipmentData> listData = FXCollections.observableArrayList();
        
        String sql = "SELECT * FROM equipment";
        
        connect = database.connectDb();
        
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            
            equipmentData ed;
            
            while(result.next()) {
                ed = new equipmentData(
                        result.getInt("id"),
                        result.getString("equipmentId"),
                        result.getString("name"),
                        result.getString("type"),
                        result.getDate("purchaseDate"),
                        result.getDate("lastMaintenance"),
                        result.getDate("nextMaintenance"),
                        result.getString("status")
                );
                
                listData.add(ed);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return listData;
    }
    
    private ObservableList<equipmentData> equipmentListData;
    
    public void equipmentShowData() {
        equipmentListData = equipmentDataList();
        
        equipment_col_id.setCellValueFactory(new PropertyValueFactory<>("equipmentId"));
        equipment_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        equipment_col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        equipment_col_purchaseDate.setCellValueFactory(new PropertyValueFactory<>("purchaseDate"));
        equipment_col_lastMaintenance.setCellValueFactory(new PropertyValueFactory<>("lastMaintenance"));
        equipment_col_nextMaintenance.setCellValueFactory(new PropertyValueFactory<>("nextMaintenance"));
        equipment_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        
        equipment_tableView.setItems(equipmentListData);
    }
    
    public void equipmentSelect() {
        equipmentData ed = equipment_tableView.getSelectionModel().getSelectedItem();
        int num = equipment_tableView.getSelectionModel().getSelectedIndex();
        
        if ((num - 1) < -1) {
            return;
        }
        
        equipment_id.setText(ed.getEquipmentId());
        equipment_name.setText(ed.getName());
        equipment_type.setText(ed.getType());
        equipment_purchaseDate.setValue(LocalDate.parse(String.valueOf(ed.getPurchaseDate())));
        equipment_lastMaintenance.setValue(LocalDate.parse(String.valueOf(ed.getLastMaintenance())));
        equipment_nextMaintenance.setValue(LocalDate.parse(String.valueOf(ed.getNextMaintenance())));
    }

    public void emptyFields() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error Message");
        alert.setHeaderText(null);
        alert.setContentText("Please fill all blank fields");
        alert.showAndWait();
    }

    public void dashboardNM() {

        String sql = "SELECT COUNT(id) FROM member WHERE status = 'Paid'";

        connect = database.connectDb();

        int nm = 0;

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                nm = result.getInt("COUNT(id)");
            }

            dashboard_NM.setText(String.valueOf(nm));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void dashboardTC() {

        String sql = "SELECT COUNT(id) FROM coach WHERE status = 'Active'";

        connect = database.connectDb();

        int tc = 0;

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                tc = result.getInt("COUNT(id)");
            }
            dashboard_NC.setText(String.valueOf(tc));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void dashboardTI() {

        String sql = "SELECT SUM(price) FROM member WHERE status = 'Paid'";

        connect = database.connectDb();

        double ti = 0;

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                ti = result.getDouble("SUM(price)");
            }

            dashboard_TI.setText("$" + String.valueOf(ti));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void dashboardChart() {

        dashboard_incomeChart.getData().clear();

        String sql = "SELECT startDate, SUM(price) FROM member WHERE status = 'Paid' GROUP BY startDate ORDER BY TIMESTAMP(startDate) ASC LIMIT 10";

        connect = database.connectDb();

        XYChart.Series chart = new XYChart.Series();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                chart.getData().add(new XYChart.Data(result.getString(1), result.getDouble(2)));
            }

            dashboard_incomeChart.getData().add(chart);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void coachesAddBtn() {

        String sql = "INSERT INTO coach (coachId, name, address, gender, phoneNum, status) "
                + "VALUES(?,?,?,?,?,?)";

        connect = database.connectDb();

        try {

            Alert alert;

            if (coaches_coachID.getText().isEmpty() || coaches_name.getText().isEmpty()
                    || coaches_address.getText().isEmpty()
                    || coaches_gender.getSelectionModel().getSelectedItem() == null
                    || coaches_phoneNum.getText().isEmpty()
                    || coaches_status.getSelectionModel().getSelectedItem() == null) {
                emptyFields();
            } else {

                String checkData = "SELECT coachId FROM coach WHERE coachId = '"
                        + coaches_coachID.getText() + "'";

                statement = connect.createStatement();
                result = statement.executeQuery(checkData);

                if (result.next()) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Coach ID: " + coaches_coachID.getText() + " was already taken!");
                    alert.showAndWait();
                } else {
                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, coaches_coachID.getText());
                    prepare.setString(2, coaches_name.getText());
                    prepare.setString(3, coaches_address.getText());
                    prepare.setString(4, (String) coaches_gender.getSelectionModel().getSelectedItem());
                    prepare.setString(5, coaches_phoneNum.getText());
                    prepare.setString(6, (String) coaches_status.getSelectionModel().getSelectedItem());

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added!");
                    alert.showAndWait();

                    // TO INSERT all DATA
                    prepare.executeUpdate();
                    // TO UPDATE TABLEVIEW
                    coachesShowData();
                    // TO CLEAR ALL FIELDS
                    coachesClearBtn();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void coachesUpdateBtn() {

        String sql = "UPDATE coach SET name = '"
                + coaches_name.getText() + "', address = '"
                + coaches_address.getText() + "', gender = '"
                + coaches_gender.getSelectionModel().getSelectedItem() + "', phoneNum = '"
                + coaches_phoneNum.getText() + "', status = '"
                + coaches_status.getSelectionModel().getSelectedItem() + "' WHERE coachId = '"
                + coaches_coachID.getText() + "'";

        connect = database.connectDb();

        try {
            Alert alert;
            if (coaches_coachID.getText().isEmpty() || coaches_name.getText().isEmpty()
                    || coaches_address.getText().isEmpty()
                    || coaches_gender.getSelectionModel().getSelectedItem() == null
                    || coaches_phoneNum.getText().isEmpty()
                    || coaches_status.getSelectionModel().getSelectedItem() == null) {
                emptyFields();
            } else {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE Coach ID: " + coaches_coachID.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    prepare = connect.prepareStatement(sql);

                    prepare.executeUpdate();
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();
                    // TO UPDATE TABLEVIEW
                    coachesShowData();
                    // TO CLEAR ALL FIELDS
                    coachesClearBtn();
                } else {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Cancelled Update!");
                    alert.showAndWait();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void coachesDeleteBtn() {
        String sql = "DELETE FROM coach WHERE coachId = '"
                + coaches_coachID.getText() + "'";

        connect = database.connectDb();

        try {
            Alert alert;
            if (coaches_coachID.getText().isEmpty() || coaches_name.getText().isEmpty()
                    || coaches_address.getText().isEmpty()
                    || coaches_gender.getSelectionModel().getSelectedItem() == null
                    || coaches_phoneNum.getText().isEmpty()
                    || coaches_status.getSelectionModel().getSelectedItem() == null) {
                emptyFields();
            } else {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to DELETE Coach ID: " + coaches_coachID.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    prepare = connect.prepareStatement(sql);

                    // TO EXECUTE THE QUERY YOU TYPED
                    prepare.executeUpdate();
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted!");
                    alert.showAndWait();
                    // TO UPDATE TABLEVIEW
                    coachesShowData();
                    // TO CLEAR ALL FIELDS
                    coachesClearBtn();
                } else {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Cancelled Update!");
                    alert.showAndWait();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void coachesClearBtn() {
        coaches_coachID.setText("");
        coaches_name.setText("");
        coaches_address.setText("");
        coaches_gender.getSelectionModel().clearSelection();
        coaches_phoneNum.setText("");
        coaches_status.getSelectionModel().clearSelection();
    }

    public ObservableList<coachData> coachesDataList() {

        ObservableList<coachData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM coach";

        connect = database.connectDb();

        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            coachData cd;

            while (result.next()) {
                cd = new coachData(result.getInt("id"), result.getString("coachId"),
                        result.getString("name"), result.getString("address"),
                        result.getString("gender"), result.getInt("phoneNum"),
                        result.getString("status"));

                listData.add(cd);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listData;
    }

    private ObservableList<coachData> coachesListData;

    public void coachesShowData() {

        coachesListData = coachesDataList();

        coaches_col_coachID.setCellValueFactory(new PropertyValueFactory<>("coachId"));
        coaches_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        coaches_col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        coaches_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        coaches_col_phoneNum.setCellValueFactory(new PropertyValueFactory<>("phoneNum"));
        coaches_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        coaches_tableView.setItems(coachesListData);

    }

    public void coachesSelect() {
        coachData cd = coaches_tableView.getSelectionModel().getSelectedItem();
        int num = coaches_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        coaches_coachID.setText(cd.getCoachId());
        coaches_name.setText(cd.getName());
        coaches_address.setText(cd.getAddress());
        coaches_phoneNum.setText(String.valueOf(cd.getPhoneNum()));

    }

    private String gender[] = {"Male", "Female", "Others"};

    public void coachGenderList() {
        List<String> genderList = new ArrayList<>();

        for (String data : gender) {
            genderList.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(genderList);
        coaches_gender.setItems(listData);
    }

    private String coachStatus[] = {"Active", "Inactive"};

    public void coachStatusList() {
        List<String> coachList = new ArrayList<>();

        for (String data : coachStatus) {
            coachList.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(coachList);
        coaches_status.setItems(listData);
    }

    private int totalDay;
    private double price;

    public void membersAddBtn() {

        String sql = "INSERT INTO member (memberId, name, address, phoneNum, gender, schedule, startDate, endDate, price, status) "
                + "VALUES(?,?,?,?,?,?,?,?,?,?)";

        connect = database.connectDb();

        try {
            Alert alert;

            if (members_customerId.getText().isEmpty() || members_name.getText().isEmpty()
                    || members_phoneNum.getText().isEmpty() || members_caddress.getText().isEmpty()
                    || members_gender.getSelectionModel().getSelectedItem() == null
                    || members_schedule.getSelectionModel().getSelectedItem() == null
                    || members_startDate.getValue() == null
                    || members_endDate.getValue() == null) {
                emptyFields();
            } else {

                String checkData = "SELECT memberId FROM member WHERE memberId = '"
                        + members_customerId.getText() + "'";

                statement = connect.createStatement();
                result = statement.executeQuery(checkData);

                if (result.next()) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Member ID: " + members_customerId.getText() + " was already taken!");
                    alert.showAndWait();
                } else {
                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, members_customerId.getText());
                    prepare.setString(2, members_name.getText());
                    prepare.setString(4, members_phoneNum.getText());
                    prepare.setString(3, members_caddress.getText());
                    prepare.setString(5, (String) members_gender.getSelectionModel().getSelectedItem());
                    prepare.setString(6, (String) members_schedule.getSelectionModel().getSelectedItem());
                    prepare.setString(7, String.valueOf(members_startDate.getValue()));
                    prepare.setString(8, String.valueOf(members_endDate.getValue()));

                    totalDay = (int) ChronoUnit.DAYS.between(members_startDate.getValue(), members_endDate.getValue());

                    price = (totalDay * 50);

                    prepare.setString(9, String.valueOf(price));
                    prepare.setString(10, (String) members_status.getSelectionModel().getSelectedItem());

                    prepare.executeUpdate();
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfuly Added!");
                    alert.showAndWait();

                    membersShowData();
                    membersClear();
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void membersUpdate() {

        totalDay = (int) ChronoUnit.DAYS.between(members_startDate.getValue(), members_endDate.getValue());
        price = (totalDay * 50);

        String sql = "UPDATE member SET name = '"
                + members_name.getText() + "', address = '"
                + members_caddress.getText() + "', phoneNum = '"
                + members_phoneNum.getText() + "', gender = '"
                + members_gender.getSelectionModel().getSelectedItem() + "', schedule = '"
                + members_schedule.getSelectionModel().getSelectedItem() + "', startDate = '"
                + members_startDate.getValue() + "', endDate = '"
                + members_endDate.getValue() + "', price = '"
                + String.valueOf(price) + "', status = '"
                + members_status.getSelectionModel().getSelectedItem() + "' WHERE memberId = '"
                + members_customerId.getText() + "'";

        connect = database.connectDb();

        try {
            Alert alert;

            if (members_customerId.getText().isEmpty() || members_name.getText().isEmpty()
                    || members_phoneNum.getText().isEmpty() || members_caddress.getText().isEmpty()
                    || members_gender.getSelectionModel().getSelectedItem() == null
                    || members_schedule.getSelectionModel().getSelectedItem() == null
                    || members_startDate.getValue() == null
                    || members_endDate.getValue() == null) {
                emptyFields();
            } else {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE Member ID: " + members_customerId.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    prepare = connect.prepareStatement(sql);
                    prepare.executeUpdate();

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfuly Updated!");
                    alert.showAndWait();

                    membersShowData();
                    membersClear();

                } else {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Cancelled Update!!");
                    alert.showAndWait();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void membersDelete() {

        String sql = "DELETE FROM member WHERE memberId = '"
                + members_customerId.getText() + "'";

        connect = database.connectDb();

        try {
            Alert alert;

            if (members_customerId.getText().isEmpty() || members_name.getText().isEmpty()
                    || members_phoneNum.getText().isEmpty() || members_caddress.getText().isEmpty()
                    || members_gender.getSelectionModel().getSelectedItem() == null
                    || members_schedule.getSelectionModel().getSelectedItem() == null
                    || members_startDate.getValue() == null
                    || members_endDate.getValue() == null) {
                emptyFields();
            } else {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to DELETE Member ID: " + members_customerId.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    prepare = connect.prepareStatement(sql);
                    prepare.executeUpdate();

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfuly Deleted!");
                    alert.showAndWait();

                    membersShowData();
                    membersClear();

                } else {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Cancelled Delete!!");
                    alert.showAndWait();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void membersClear() {
        members_customerId.setText("");
        members_name.setText("");
        members_caddress.setText("");
        members_phoneNum.setText("");
        members_gender.getSelectionModel().clearSelection();
        members_schedule.getSelectionModel().clearSelection();
        members_startDate.setValue(null);
        members_endDate.setValue(null);
        members_status.getSelectionModel().clearSelection();
    }

    public void membersGender() {
        List<String> genderList = new ArrayList<>();

        for (String data : gender) {
            genderList.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(genderList);
        members_gender.setItems(listData);

    }

    private String[] scheduleList = {"9AM - 11AM", "11AM - 1PM", "1PM - 3PM", "3PM - 5PM", "5PM - 7PM"};

    public void membersSchedule() {

        List<String> sl = new ArrayList<>();

        for (String data : scheduleList) {
            sl.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(sl);
        members_schedule.setItems(listData);

    }

    private String memberStatus[] = {"Paid", "Unpaid"};

    public void membersStatus() {

        List<String> ms = new ArrayList<>();

        for (String data : memberStatus) {
            ms.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(memberStatus);
        members_status.setItems(listData);

    }

    public ObservableList<memberData> membersDataList() {

        ObservableList<memberData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM member";

        connect = database.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            memberData md;

            while (result.next()) {
                md = new memberData(result.getInt("id"),
                        result.getString("memberId"),
                        result.getString("name"),
                        result.getString("address"),
                        result.getInt("phoneNum"),
                        result.getString("gender"),
                        result.getString("schedule"),
                        result.getDate("startDate"),
                        result.getDate("endDate"),
                        result.getDouble("price"),
                        result.getString("status"));

                listData.add(md);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<memberData> membersListData;

    public void membersShowData() {
        membersListData = membersDataList();

        members_col_customerID.setCellValueFactory(new PropertyValueFactory<>("memberId"));
        members_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        members_col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        members_col_phoneNum.setCellValueFactory(new PropertyValueFactory<>("phoneNum"));
        members_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        members_col_schedule.setCellValueFactory(new PropertyValueFactory<>("schedule"));
        members_col_startDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        members_col_endDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        members_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        members_tableView.setItems(membersListData);
    }

    public void membersSelect() {

        memberData md = members_tableView.getSelectionModel().getSelectedItem();
        int num = members_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        members_customerId.setText(md.getMemberId());
        members_name.setText(md.getName());
        members_caddress.setText(md.getAddress());
        members_phoneNum.setText(String.valueOf(md.getPhoneNum()));
        members_startDate.setValue(LocalDate.parse(String.valueOf(md.getStartDate())));
        members_endDate.setValue(LocalDate.parse(String.valueOf(md.getEndDate())));

    }

    public void paymentMemberId() {

        String sql = "SELECT memberId FROM member WHERE status = 'Unpaid'";

        connect = database.connectDb();

        try {
            ObservableList listData = FXCollections.observableArrayList();

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                listData.add(result.getString("memberId"));
            }
            payment_customerID.setItems(listData);

            paymentName();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void paymentName() {

        String sql = "SELECT name FROM member WHERE memberId = '"
                + payment_customerID.getSelectionModel().getSelectedItem() + "'";

        connect = database.connectDb();

        try {
            ObservableList listData = FXCollections.observableArrayList();
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                listData.add(result.getString("name"));
            }
            payment_name.setItems(listData);

            paymentDisplayTotal();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private double totalP;

    public void displayTotal() {

        String sql = "SELECT price FROM member WHERE name = '"
                + payment_name.getSelectionModel().getSelectedItem() + "' and memberId = '"
                + payment_customerID.getSelectionModel().getSelectedItem() + "'";

        connect = database.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                totalP = result.getDouble("price");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void paymentDisplayTotal() {
        displayTotal();
        payment_total.setText("$" + String.valueOf(totalP));
    }

    private double amount;
    private double change;

    public void paymentAmount() {
        displayTotal();

        Alert alert;

        if (payment_amount.getText().isEmpty() || totalP == 0) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Invalid :3");
            alert.showAndWait();

            payment_amount.setText("");
        } else {
            amount = Double.parseDouble(payment_amount.getText());

            if (amount >= totalP) {
                change = (amount - totalP);
                payment_change.setText("$" + String.valueOf(change));
            } else {
                payment_amount.setText("");
                change = 0;
                amount = 0;
            }
        }
    }

    public void paymentPayBtn() {

        String sql = "UPDATE member SET status = 'Paid' WHERE memberId = '"
                + payment_customerID.getSelectionModel().getSelectedItem() + "'";

        connect = database.connectDb();

        try {
            Alert alert;
            if (totalP == 0 || change == 0 || payment_amount.getText().isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Something Wrong :3");
                alert.showAndWait();
            } else {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    prepare = connect.prepareStatement(sql);
                    prepare.executeUpdate();

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successful!");
                    alert.showAndWait();

                    paymentShowData();
                    paymentClear();
                } else {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Cancelled.");
                    alert.showAndWait();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void paymentClear() {
        payment_customerID.getSelectionModel().clearSelection();
        payment_name.getSelectionModel().clearSelection();
        payment_total.setText("$0.0");
        payment_amount.setText("");
        payment_change.setText("$0.0");

        amount = 0;
        change = 0;
        totalP = 0;
    }

    public ObservableList<memberData> paymentDataList() {

        ObservableList<memberData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM member";

        connect = database.connectDb();

        try {
            memberData md;
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                md = new memberData(result.getInt("id"),
                        result.getString("memberId"),
                        result.getString("name"),
                        result.getString("address"),
                        result.getInt("phoneNum"),
                        result.getString("gender"),
                        result.getString("schedule"),
                        result.getDate("startDate"),
                        result.getDate("endDate"),
                        result.getDouble("price"),
                        result.getString("status"));

                listData.add(md);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<memberData> paymentListData;

    public void paymentShowData() {

        paymentListData = paymentDataList();

        payment_col_customerID.setCellValueFactory(new PropertyValueFactory<>("memberId"));
        payment_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        payment_col_startDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        payment_col_endDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        payment_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        payment_tableView.setItems(paymentListData);

    }

    public void displayUsername() {

        String user = data.username;
        user = user.substring(0, 1).toUpperCase() + user.substring(1);

        username.setText(user);

    }

    public void switchForm(ActionEvent event) {

        if (event.getSource() == dashboard_btn) {
            dashboard_form.setVisible(true);
            coaches_form.setVisible(false);
            members_form.setVisible(false);
            payment_Form.setVisible(false);
            equipment_form.setVisible(false);
            schedule_form.setVisible(false);
            reports_form.setVisible(false);
            admin_form.setVisible(false);

            dashboardNM();
            dashboardTC();
            dashboardTI();
            dashboardChart();

        } else if (event.getSource() == coaches_btn) {
            dashboard_form.setVisible(false);
            coaches_form.setVisible(true);
            members_form.setVisible(false);
            payment_Form.setVisible(false);
            equipment_form.setVisible(false);
            schedule_form.setVisible(false);
            reports_form.setVisible(false);
            admin_form.setVisible(false);

            coachGenderList();
            coachStatusList();
            coachesShowData();

        } else if (event.getSource() == members_btn) {
            dashboard_form.setVisible(false);
            coaches_form.setVisible(false);
            members_form.setVisible(true);
            payment_Form.setVisible(false);
            equipment_form.setVisible(false);
            schedule_form.setVisible(false);
            reports_form.setVisible(false);
            admin_form.setVisible(false);

            membersShowData();
            membersGender();
            membersSchedule();
            membersStatus();

        } else if (event.getSource() == payment_btn) {
            dashboard_form.setVisible(false);
            coaches_form.setVisible(false);
            members_form.setVisible(false);
            payment_Form.setVisible(true);
            equipment_form.setVisible(false);
            schedule_form.setVisible(false);
            reports_form.setVisible(false);
            admin_form.setVisible(false);

            paymentShowData();
            paymentMemberId();
            paymentName();

        } else if (event.getSource() == equipment_btn) {
            dashboard_form.setVisible(false);
            coaches_form.setVisible(false);
            members_form.setVisible(false);
            payment_Form.setVisible(false);
            equipment_form.setVisible(true);
            schedule_form.setVisible(false);
            reports_form.setVisible(false);
            admin_form.setVisible(false);
            
            equipmentStatusList();
            equipmentShowData();
        } else if (event.getSource() == schedule_btn) {
            dashboard_form.setVisible(false);
            coaches_form.setVisible(false);
            members_form.setVisible(false);
            payment_Form.setVisible(false);
            equipment_form.setVisible(false);
            schedule_form.setVisible(true);
            reports_form.setVisible(false);
            admin_form.setVisible(false);
            
            // Schedule functionality will be implemented later
        } else if (event.getSource() == reports_btn) {
            dashboard_form.setVisible(false);
            coaches_form.setVisible(false);
            members_form.setVisible(false);
            payment_Form.setVisible(false);
            equipment_form.setVisible(false);
            schedule_form.setVisible(false);
            reports_form.setVisible(true);
            admin_form.setVisible(false);
            
            // Reports functionality will be implemented later
            initializeReportIntervals();
        } else if (event.getSource() == admin_btn) {
            dashboard_form.setVisible(false);
            coaches_form.setVisible(false);
            members_form.setVisible(false);
            payment_Form.setVisible(false);
            equipment_form.setVisible(false);
            schedule_form.setVisible(false);
            reports_form.setVisible(false);
            admin_form.setVisible(true);
            
            // Initialize admin functionality
            populateAdminRoles();
            populateAdminStatus();
            adminShowData();
        }

    }

    private double x = 0;
    private double y = 0;

    public void logout() {

        try {

            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to logout?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {

                // TO HIDE YOUR DASHBOARD FORM
                logout.getScene().getWindow().hide();

                // LINK YOUR LOGIN FORM
                Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

                Stage stage = new Stage();
                Scene scene = new Scene(root);

                root.setOnMousePressed((MouseEvent event) -> {
                    x = event.getSceneX();
                    y = event.getSceneY();
                });

                root.setOnMouseDragged((MouseEvent event) -> {
                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);

                    stage.setOpacity(.8);
                });

                root.setOnMouseReleased((MouseEvent event) -> {
                    stage.setOpacity(1);
                });

                stage.initStyle(StageStyle.TRANSPARENT);

                stage.setScene(scene);
                stage.show();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void minimize() {

        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);

    }

    public void close() {

        javafx.application.Platform.exit();

    }

    public void initializeReportIntervals() {
        // Define the report intervals
        ObservableList<String> intervals = FXCollections.observableArrayList(
                "Daily", "Weekly", "Monthly", "Yearly"
        );
        
        report_interval.setItems(intervals);
        report_interval.getSelectionModel().select("Monthly");
        
        // Set default dates (current month)
        LocalDate now = LocalDate.now();
        report_startDate.setValue(now.withDayOfMonth(1)); // First day of current month
        report_endDate.setValue(now); // Current day
        
        // Clear any existing chart data
        if (report_trendChart.getData() != null) {
            report_trendChart.getData().clear();
        }
        if (report_comparisonChart.getData() != null) {
            report_comparisonChart.getData().clear();
        }
        if (report_distributionChart.getData() != null) {
            report_distributionChart.getData().clear();
        }
        
        // Configure cell factories for the table columns
        report_col_date.setCellValueFactory(cellData -> {
            LocalDate date = cellData.getValue().getDate();
            return new SimpleStringProperty(date.toString());
        });
        
        report_col_category.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getCategory());
        });
        
        report_col_value.setCellValueFactory(cellData -> {
            return new SimpleObjectProperty<>(cellData.getValue().getValue());
        });
        
        report_col_change.setCellValueFactory(cellData -> {
            return new SimpleObjectProperty<>(cellData.getValue().getChange());
        });
        
        // Set event handlers for report type buttons
        report_membership.setOnAction(event -> {
            currentReportType = "Membership";
            updateReportTypeUI();
        });
        
        report_financial.setOnAction(event -> {
            currentReportType = "Financial";
            updateReportTypeUI();
        });
        
        report_equipment.setOnAction(event -> {
            currentReportType = "Equipment";
            updateReportTypeUI();
        });
        
        report_schedule.setOnAction(event -> {
            currentReportType = "Schedule";
            updateReportTypeUI();
        });
        
        report_coach.setOnAction(event -> {
            currentReportType = "Coach";
            updateReportTypeUI();
        });
        
        // Set event handler for generate button
        report_generate.setOnAction(event -> {
            generateReport();
        });
        
        // Set event handler for export button
        report_export.setOnAction(event -> {
            exportReport();
        });
        
        // Select default report type
        updateReportTypeUI();
    }
    
    private void updateReportTypeUI() {
        // Reset all chart visibilities
        report_trendChart.setVisible(false);
        report_comparisonChart.setVisible(false);
        report_distributionChart.setVisible(false);
        report_detailTable.setVisible(false);
        
        // Set appropriate chart visibility based on report type
        switch (currentReportType) {
            case "Membership":
                report_trendChart.setTitle("Membership Trends");
                report_trendChart.setVisible(true);
                break;
                
            case "Financial":
                report_trendChart.setTitle("Financial Analysis");
                report_trendChart.setVisible(true);
                break;
                
            case "Equipment":
                report_distributionChart.setTitle("Equipment Status Distribution");
                report_distributionChart.setVisible(true);
                break;
                
            case "Schedule":
                report_comparisonChart.setTitle("Class Capacity Analysis");
                report_comparisonChart.setVisible(true);
                break;
                
            case "Coach":
                report_comparisonChart.setTitle("Coach Workload Analysis");
                report_comparisonChart.setVisible(true);
                break;
        }
        
        // Generate the initial report if dates are set
        if (report_startDate.getValue() != null && report_endDate.getValue() != null) {
            generateReport();
        }
    }
    
    private void generateReport() {
        try {
            LocalDate startDate = report_startDate.getValue();
            LocalDate endDate = report_endDate.getValue();
            String interval = report_interval.getValue();
            
            if (startDate == null || endDate == null || interval == null) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please select date range and interval.");
                alert.showAndWait();
                return;
            }
            
            // Check if start date is before end date
            if (startDate.isAfter(endDate)) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Start date must be before end date.");
                alert.showAndWait();
                return;
            }
            
            // Reset all charts
            report_trendChart.getData().clear();
            report_comparisonChart.getData().clear();
            report_distributionChart.getData().clear();
            report_detailTable.getItems().clear();
            
            // Generate report based on the selected type
            switch (currentReportType) {
                case "Membership":
                    // Generate trend chart
                    ObservableList<XYChart.Series<String, Number>> membershipData = 
                            reportGenerator.generateMembershipTrend(startDate, endDate, interval);
                    report_trendChart.getData().addAll(membershipData);
                    
                    // Generate detailed table data
                    ObservableList<reportData> membershipDetails = 
                            reportGenerator.generateDetailedReport("Membership", startDate, endDate);
                    report_detailTable.setItems(membershipDetails);
                    report_detailTable.setVisible(true);
                    break;
                    
                case "Financial":
                    // Generate trend chart
                    ObservableList<XYChart.Series<String, Number>> financialData = 
                            reportGenerator.generateFinancialReport(startDate, endDate, interval);
                    report_trendChart.getData().addAll(financialData);
                    
                    // Generate detailed table data
                    ObservableList<reportData> financialDetails = 
                            reportGenerator.generateDetailedReport("Financial", startDate, endDate);
                    report_detailTable.setItems(financialDetails);
                    report_detailTable.setVisible(true);
                    break;
                    
                case "Equipment":
                    // Generate pie chart
                    ObservableList<PieChart.Data> equipmentData = 
                            reportGenerator.generateEquipmentStatusDistribution();
                    report_distributionChart.setData(equipmentData);
                    
                    // Generate detailed table data
                    ObservableList<reportData> equipmentDetails = 
                            reportGenerator.generateDetailedReport("Equipment", startDate, endDate);
                    report_detailTable.setItems(equipmentDetails);
                    report_detailTable.setVisible(true);
                    break;
                    
                case "Schedule":
                    // Generate bar chart
                    ObservableList<XYChart.Series<String, Number>> scheduleData = 
                            reportGenerator.generateScheduleCapacityAnalysis();
                    report_comparisonChart.getData().addAll(scheduleData);
                    
                    // Generate detailed table data
                    ObservableList<reportData> scheduleDetails = 
                            reportGenerator.generateDetailedReport("Schedule", startDate, endDate);
                    report_detailTable.setItems(scheduleDetails);
                    report_detailTable.setVisible(true);
                    break;
                    
                case "Coach":
                    // Generate bar chart
                    ObservableList<XYChart.Series<String, Number>> coachData = 
                            reportGenerator.generateCoachWorkload();
                    report_comparisonChart.getData().addAll(coachData);
                    
                    // Generate detailed table data
                    ObservableList<reportData> coachDetails = 
                            reportGenerator.generateDetailedReport("Coach", startDate, endDate);
                    report_detailTable.setItems(coachDetails);
                    report_detailTable.setVisible(true);
                    break;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Error generating report: " + e.getMessage());
            alert.showAndWait();
        }
    }
    
    private void exportReport() {
        try {
            if (report_startDate.getValue() == null || report_endDate.getValue() == null) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please generate a report first.");
                alert.showAndWait();
                return;
            }
            
            LocalDate startDate = report_startDate.getValue();
            LocalDate endDate = report_endDate.getValue();
            
            // Get the report data from the table
            ObservableList<reportData> reportDataList = report_detailTable.getItems();
            
            if (reportDataList.isEmpty()) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("No data to export. Please generate a report with data first.");
                alert.showAndWait();
                return;
            }
            
            // Show in-progress message
            Alert progressAlert = new Alert(AlertType.INFORMATION);
            progressAlert.setTitle("Export Information");
            progressAlert.setHeaderText(null);
            progressAlert.setContentText("Exporting report...");
            progressAlert.show();
            
            // Export the data
            String exportedFilePath = reportExporter.exportToCSV(
                currentReportType, 
                reportDataList,
                startDate,
                endDate
            );
            
            // Close the progress alert
            progressAlert.close();
            
            // Show success message
            Alert successAlert = new Alert(AlertType.INFORMATION);
            successAlert.setTitle("Export Success");
            successAlert.setHeaderText(null);
            successAlert.setContentText("Report successfully exported to:\n" + exportedFilePath);
            successAlert.showAndWait();
            
        } catch (Exception e) {
            e.printStackTrace();
            
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Error exporting report: " + e.getMessage());
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        displayUsername();

        dashboardNM();
        dashboardTC();
        dashboardTI();
        dashboardChart();

        coachGenderList();
        coachStatusList();
        coachesShowData();

        membersShowData();
        membersGender();
        membersSchedule();
        membersStatus();

        paymentMemberId();
        paymentName();
        paymentShowData();

        // Initialize equipment functionality
        equipmentStatusList();
        equipmentShowData();

        // Initialize schedule functionality
        scheduleDaysList();
        scheduleStatusList();
        scheduleCoachList();
        scheduleShowData();
        
        // Add mouse click event handler for schedule table
        schedule_tableView.setOnMouseClicked((MouseEvent event) -> {
            scheduleSelect();
        });

        // Initialize reports functionality
        initializeReportIntervals();
        
        // Initialize admin functionality
        populateAdminRoles();
        populateAdminStatus();
        adminShowData();
        
        // Add mouse click event handler for admin table
        admin_tableView.setOnMouseClicked((MouseEvent event) -> {
            adminSelect();
        });
        
        // Set up event handlers for admin buttons
        admin_addBtn.setOnAction(event -> adminAddBtn());
        admin_updateBtn.setOnAction(event -> adminUpdateBtn());
        admin_deleteBtn.setOnAction(event -> adminDeleteBtn());
        admin_clearBtn.setOnAction(event -> adminClearBtn());
    }

    // Schedule Management Methods
    
    public void scheduleDaysList() {
        List<String> dayList = new ArrayList<>();
        for(String data: daysList) {
            dayList.add(data);
        }
        ObservableList listData = FXCollections.observableArrayList(dayList);
        schedule_dayOfWeek.setItems(listData);
    }
    
    public void scheduleStatusList() {
        List<String> statusList = new ArrayList<>();
        for(String data: scheduleStatusList) {
            statusList.add(data);
        }
        ObservableList listData = FXCollections.observableArrayList(statusList);
        schedule_status.setItems(listData);
    }
    
    public void scheduleCoachList() {
        String sql = "SELECT coachId FROM coach WHERE status = 'Active'";
        connect = database.connectDb();
        
        try {
            ObservableList listData = FXCollections.observableArrayList();
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            
            while(result.next()) {
                listData.add(result.getString("coachId"));
            }
            schedule_coachId.setItems(listData);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void scheduleAddBtn() {
        String sql = "INSERT INTO schedule (scheduleId, className, coachId, dayOfWeek, startTime, endTime, maxCapacity, status) "
                + "VALUES(?,?,?,?,?,?,?,?)";
        
        connect = database.connectDb();
        
        try {
            Alert alert;
            
            if(schedule_id.getText().isEmpty() || schedule_className.getText().isEmpty()
                    || schedule_coachId.getSelectionModel().getSelectedItem() == null
                    || schedule_dayOfWeek.getSelectionModel().getSelectedItem() == null
                    || schedule_startTime.getText().isEmpty()
                    || schedule_endTime.getText().isEmpty()
                    || schedule_maxCapacity.getText().isEmpty()
                    || schedule_status.getSelectionModel().getSelectedItem() == null) {
                emptyFields();
            } else {
                String checkData = "SELECT scheduleId FROM schedule WHERE scheduleId = '"
                        + schedule_id.getText() + "'";
                
                statement = connect.createStatement();
                result = statement.executeQuery(checkData);
                
                if(result.next()) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Schedule ID: " + schedule_id.getText() + " already exists!");
                    alert.showAndWait();
                } else {
                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, schedule_id.getText());
                    prepare.setString(2, schedule_className.getText());
                    prepare.setString(3, schedule_coachId.getSelectionModel().getSelectedItem());
                    prepare.setString(4, schedule_dayOfWeek.getSelectionModel().getSelectedItem());
                    prepare.setString(5, schedule_startTime.getText());
                    prepare.setString(6, schedule_endTime.getText());
                    prepare.setString(7, schedule_maxCapacity.getText());
                    prepare.setString(8, schedule_status.getSelectionModel().getSelectedItem());
                    
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added!");
                    alert.showAndWait();
                    
                    prepare.executeUpdate();
                    scheduleShowData();
                    scheduleClearBtn();
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void scheduleUpdateBtn() {
        String sql = "UPDATE schedule SET className = '"
                + schedule_className.getText() + "', coachId = '"
                + schedule_coachId.getSelectionModel().getSelectedItem() + "', dayOfWeek = '"
                + schedule_dayOfWeek.getSelectionModel().getSelectedItem() + "', startTime = '"
                + schedule_startTime.getText() + "', endTime = '"
                + schedule_endTime.getText() + "', maxCapacity = '"
                + schedule_maxCapacity.getText() + "', status = '"
                + schedule_status.getSelectionModel().getSelectedItem() + "' WHERE scheduleId = '"
                + schedule_id.getText() + "'";
        
        connect = database.connectDb();
        
        try {
            Alert alert;
            if(schedule_id.getText().isEmpty() || schedule_className.getText().isEmpty()
                    || schedule_coachId.getSelectionModel().getSelectedItem() == null
                    || schedule_dayOfWeek.getSelectionModel().getSelectedItem() == null
                    || schedule_startTime.getText().isEmpty()
                    || schedule_endTime.getText().isEmpty()
                    || schedule_maxCapacity.getText().isEmpty()
                    || schedule_status.getSelectionModel().getSelectedItem() == null) {
                emptyFields();
            } else {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE Schedule ID: " + schedule_id.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();
                
                if(option.get().equals(ButtonType.OK)) {
                    statement = connect.createStatement();
                    statement.executeUpdate(sql);
                    
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();
                    
                    scheduleShowData();
                    scheduleClearBtn();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void scheduleDeleteBtn() {
        String sql = "DELETE FROM schedule WHERE scheduleId = '"
                + schedule_id.getText() + "'";
        
        connect = database.connectDb();
        
        try {
            Alert alert;
            if(schedule_id.getText().isEmpty() || schedule_className.getText().isEmpty()
                    || schedule_coachId.getSelectionModel().getSelectedItem() == null
                    || schedule_dayOfWeek.getSelectionModel().getSelectedItem() == null
                    || schedule_startTime.getText().isEmpty()
                    || schedule_endTime.getText().isEmpty()
                    || schedule_maxCapacity.getText().isEmpty()
                    || schedule_status.getSelectionModel().getSelectedItem() == null) {
                emptyFields();
            } else {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to DELETE Schedule ID: " + schedule_id.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();
                
                if(option.get().equals(ButtonType.OK)) {
                    prepare = connect.prepareStatement(sql);
                    prepare.executeUpdate();
                    
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted!");
                    alert.showAndWait();
                    
                    scheduleShowData();
                    scheduleClearBtn();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void scheduleClearBtn() {
        schedule_id.setText("");
        schedule_className.setText("");
        schedule_coachId.getSelectionModel().clearSelection();
        schedule_dayOfWeek.getSelectionModel().clearSelection();
        schedule_startTime.setText("");
        schedule_endTime.setText("");
        schedule_maxCapacity.setText("");
        schedule_status.getSelectionModel().clearSelection();
    }
    
    public ObservableList<scheduleData> scheduleDataList() {
        ObservableList<scheduleData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM schedule";
        
        connect = database.connectDb();
        
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            scheduleData sData;
            
            while(result.next()) {
                sData = new scheduleData(
                        result.getInt("id"),
                        result.getString("scheduleId"),
                        result.getString("className"),
                        result.getString("coachId"),
                        result.getString("dayOfWeek"),
                        result.getTime("startTime"),
                        result.getTime("endTime"),
                        result.getInt("maxCapacity"),
                        result.getInt("currentEnrollment"),
                        result.getString("status")
                );
                listData.add(sData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }
    
    private ObservableList<scheduleData> scheduleListData;
    
    public void scheduleShowData() {
        scheduleListData = scheduleDataList();
        
        schedule_col_id.setCellValueFactory(new PropertyValueFactory<>("scheduleId"));
        schedule_col_className.setCellValueFactory(new PropertyValueFactory<>("className"));
        schedule_col_coachId.setCellValueFactory(new PropertyValueFactory<>("coachId"));
        schedule_col_dayOfWeek.setCellValueFactory(new PropertyValueFactory<>("dayOfWeek"));
        schedule_col_timeRange.setCellValueFactory(new PropertyValueFactory<>("timeRange"));
        schedule_col_capacity.setCellValueFactory(new PropertyValueFactory<>("maxCapacity"));
        schedule_col_enrollment.setCellValueFactory(new PropertyValueFactory<>("currentEnrollment"));
        schedule_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        
        schedule_tableView.setItems(scheduleListData);
    }
    
    public void scheduleSelect() {
        scheduleData sData = schedule_tableView.getSelectionModel().getSelectedItem();
        int num = schedule_tableView.getSelectionModel().getSelectedIndex();
        
        if((num - 1) < -1) return;
        
        schedule_id.setText(sData.getScheduleId());
        schedule_className.setText(sData.getClassName());
        schedule_coachId.setValue(sData.getCoachId());
        schedule_dayOfWeek.setValue(sData.getDayOfWeek());
        schedule_startTime.setText(sData.getStartTime().toString());
        schedule_endTime.setText(sData.getEndTime().toString());
        schedule_maxCapacity.setText(String.valueOf(sData.getMaxCapacity()));
        schedule_status.setValue(sData.getStatus());
    }

    // Admin Management Methods
    
    public void adminAddBtn() {
        String sql = "INSERT INTO admin (username, password, fullName, email, role, status, createdBy) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        connect = database.connectDb();
        
        try {
            Alert alert;
            
            // Validate all fields are filled
            if(admin_username.getText().isEmpty() || 
               admin_password.getText().isEmpty() || 
               admin_fullName.getText().isEmpty() || 
               admin_email.getText().isEmpty() ||
               admin_role.getSelectionModel().getSelectedItem() == null ||
               admin_status.getSelectionModel().getSelectedItem() == null) {
                
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all fields");
                alert.showAndWait();
                return;
            }
            
            // Check if username already exists
            String checkUsername = "SELECT username FROM admin WHERE username = ?";
            PreparedStatement checkPs = connect.prepareStatement(checkUsername);
            checkPs.setString(1, admin_username.getText());
            ResultSet checkRs = checkPs.executeQuery();
            
            if(checkRs.next()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Username already exists");
                alert.showAndWait();
                return;
            }
            
            // Validate password strength
            if(!passwordUtil.isStrongPassword(admin_password.getText())) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Password must be at least 8 characters and include a mix of uppercase, lowercase, digits and special characters");
                alert.showAndWait();
                return;
            }
            
            // Validate email format
            if(!admin_email.getText().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Invalid email format");
                alert.showAndWait();
                return;
            }
            
            // Prepare the insert statement
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, admin_username.getText());
            prepare.setString(2, passwordUtil.hashPassword(admin_password.getText()));
            prepare.setString(3, admin_fullName.getText());
            prepare.setString(4, admin_email.getText());
            prepare.setString(5, admin_role.getSelectionModel().getSelectedItem());
            prepare.setString(6, admin_status.getSelectionModel().getSelectedItem());
            prepare.setString(7, data.username); // Current logged-in user
            
            // Execute the insert
            prepare.executeUpdate();
            
            // Log the action
            logActivity("Create", "Admin Management", "Created new admin user: " + admin_username.getText());
            
            // Show success message
            alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Success Message");
            alert.setHeaderText(null);
            alert.setContentText("Admin user added successfully");
            alert.showAndWait();
            
            // Refresh the table and clear fields
            adminShowData();
            adminClearBtn();
            
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Error: " + e.getMessage());
            alert.showAndWait();
        }
    }
    
    public void adminUpdateBtn() {
        
        // Check if user has selection
        if(admin_tableView.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please select an admin user to update");
            alert.showAndWait();
            return;
        }
        
        String sql;
        
        // If password field is filled, update password too
        if(!admin_password.getText().isEmpty()) {
            sql = "UPDATE admin SET fullName = ?, email = ?, role = ?, status = ?, password = ? WHERE username = ?";
        } else {
            sql = "UPDATE admin SET fullName = ?, email = ?, role = ?, status = ? WHERE username = ?";
        }
        
        connect = database.connectDb();
        
        try {
            Alert alert;
            
            // Validate required fields
            if(admin_username.getText().isEmpty() ||
               admin_fullName.getText().isEmpty() || 
               admin_email.getText().isEmpty() ||
               admin_role.getSelectionModel().getSelectedItem() == null ||
               admin_status.getSelectionModel().getSelectedItem() == null) {
                
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all required fields");
                alert.showAndWait();
                return;
            }
            
            // Validate email format
            if(!admin_email.getText().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Invalid email format");
                alert.showAndWait();
                return;
            }
            
            // Prevent super admin role change or deactivation if it's the last super admin
            if(admin_username.getText().equals(data.username) && 
               !admin_role.getSelectionModel().getSelectedItem().equals("SUPER_ADMIN") &&
               data.userRole.equals("SUPER_ADMIN")) {
                
                // Count super admins
                String countSql = "SELECT COUNT(*) FROM admin WHERE role = 'SUPER_ADMIN'";
                Statement stmt = connect.createStatement();
                ResultSet countRs = stmt.executeQuery(countSql);
                
                if(countRs.next() && countRs.getInt(1) <= 1) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Cannot remove the last super admin role");
                    alert.showAndWait();
                    return;
                }
            }
            
            // Validate password if provided
            if(!admin_password.getText().isEmpty() && 
               !passwordUtil.isStrongPassword(admin_password.getText())) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Password must be at least 8 characters and include a mix of uppercase, lowercase, digits and special characters");
                alert.showAndWait();
                return;
            }
            
            // Confirm update
            alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to update this admin user?");
            Optional<ButtonType> option = alert.showAndWait();
            
            if(option.get().equals(ButtonType.OK)) {
                prepare = connect.prepareStatement(sql);
                prepare.setString(1, admin_fullName.getText());
                prepare.setString(2, admin_email.getText());
                prepare.setString(3, admin_role.getSelectionModel().getSelectedItem());
                prepare.setString(4, admin_status.getSelectionModel().getSelectedItem());
                
                if(!admin_password.getText().isEmpty()) {
                    prepare.setString(5, passwordUtil.hashPassword(admin_password.getText()));
                    prepare.setString(6, admin_username.getText());
                } else {
                    prepare.setString(5, admin_username.getText());
                }
                
                prepare.executeUpdate();
                
                // Log the action
                logActivity("Update", "Admin Management", "Updated admin user: " + admin_username.getText());
                
                // Show success message
                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Success Message");
                alert.setHeaderText(null);
                alert.setContentText("Admin user updated successfully");
                alert.showAndWait();
                
                // Refresh the table and clear fields
                adminShowData();
                adminClearBtn();
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
    
    public void adminDeleteBtn() {
        // Check if user has selection
        if(admin_tableView.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please select an admin user to delete");
            alert.showAndWait();
            return;
        }
        
        String sql = "DELETE FROM admin WHERE username = ?";
        
        connect = database.connectDb();
        
        try {
            String username = admin_username.getText();
            String role = admin_tableView.getSelectionModel().getSelectedItem().getRole();
            
            // Prevent deletion of current user
            if(username.equals(data.username)) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("You cannot delete your own account");
                alert.showAndWait();
                return;
            }
            
            // Prevent deletion of last super admin
            if(role.equals("SUPER_ADMIN")) {
                String countSql = "SELECT COUNT(*) FROM admin WHERE role = 'SUPER_ADMIN'";
                Statement stmt = connect.createStatement();
                ResultSet countRs = stmt.executeQuery(countSql);
                
                if(countRs.next() && countRs.getInt(1) <= 1) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Cannot delete the last super admin account");
                    alert.showAndWait();
                    return;
                }
            }
            
            // Confirm deletion
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete this admin user?");
            Optional<ButtonType> option = alert.showAndWait();
            
            if(option.get().equals(ButtonType.OK)) {
                prepare = connect.prepareStatement(sql);
                prepare.setString(1, username);
                prepare.executeUpdate();
                
                // Log the action
                logActivity("Delete", "Admin Management", "Deleted admin user: " + username);
                
                // Show success message
                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Success Message");
                alert.setHeaderText(null);
                alert.setContentText("Admin user deleted successfully");
                alert.showAndWait();
                
                // Refresh the table and clear fields
                adminShowData();
                adminClearBtn();
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
    
    public void adminClearBtn() {
        admin_username.setText("");
        admin_password.setText("");
        admin_fullName.setText("");
        admin_email.setText("");
        admin_role.getSelectionModel().clearSelection();
        admin_status.getSelectionModel().clearSelection();
        admin_username.setEditable(true);
    }
    
    public ObservableList<adminData> adminDataList() {
        ObservableList<adminData> listData = FXCollections.observableArrayList();
        
        String sql = "SELECT * FROM admin ORDER BY username";
        
        connect = database.connectDb();
        
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            
            adminData adminD;
            
            while(result.next()) {
                adminD = new adminData(
                    result.getInt("id"),
                    result.getString("username"),
                    result.getString("fullName"),
                    result.getString("email"),
                    result.getString("role"),
                    result.getString("status"),
                    result.getTimestamp("lastLogin"),
                    result.getString("createdBy"),
                    result.getTimestamp("createdAt")
                );
                
                listData.add(adminD);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return listData;
    }
    
    private ObservableList<adminData> adminListData;
    private FilteredList<adminData> filteredAdminData;
    
    public void adminShowData() {
        adminListData = adminDataList();
        
        admin_col_username.setCellValueFactory(new PropertyValueFactory<>("username"));
        admin_col_fullName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        admin_col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        admin_col_role.setCellValueFactory(new PropertyValueFactory<>("role"));
        admin_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        admin_col_lastLogin.setCellValueFactory(new PropertyValueFactory<>("lastLogin"));
        
        // Create filtered list
        filteredAdminData = new FilteredList<>(adminListData, p -> true);
        
        // Set up search functionality
        admin_search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredAdminData.setPredicate(admin -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                String searchKeyword = newValue.toLowerCase();
                
                // Match against username, full name, or email
                if (admin.getUsername().toLowerCase().contains(searchKeyword)) {
                    return true;
                } else if (admin.getFullName().toLowerCase().contains(searchKeyword)) {
                    return true;
                } else if (admin.getEmail().toLowerCase().contains(searchKeyword)) {
                    return true;
                } else if (admin.getRole().toLowerCase().contains(searchKeyword)) {
                    return true;
                }
                
                return false;
            });
        });
        
        // Wrap the filtered list in a SortedList
        SortedList<adminData> sortedData = new SortedList<>(filteredAdminData);
        sortedData.comparatorProperty().bind(admin_tableView.comparatorProperty());
        
        // Set the table with filtered/sorted data
        admin_tableView.setItems(sortedData);
        
        // Also refresh activity log data
        adminShowLogData();
    }
    
    public void adminSelect() {
        adminData admin = admin_tableView.getSelectionModel().getSelectedItem();
        
        if (admin == null) {
            return;
        }
        
        admin_username.setText(admin.getUsername());
        admin_fullName.setText(admin.getFullName());
        admin_email.setText(admin.getEmail());
        admin_role.getSelectionModel().select(admin.getRole());
        admin_status.getSelectionModel().select(admin.getStatus());
        admin_password.setText(""); // Clear for security
        
        // Disable username field since it's the primary key
        admin_username.setEditable(false);
    }
    
    // Activity logging methods
    
    public void logActivity(String action, String module, String details) {
        String sql = "INSERT INTO activity_log (username, action, module, details) VALUES (?, ?, ?, ?)";
        
        connect = database.connectDb();
        
        try {
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, data.username);
            prepare.setString(2, action);
            prepare.setString(3, module);
            prepare.setString(4, details);
            prepare.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public ObservableList<activityLogData> activityLogDataList() {
        ObservableList<activityLogData> listData = FXCollections.observableArrayList();
        
        String sql = "SELECT * FROM activity_log ORDER BY timestamp DESC LIMIT 100";
        
        connect = database.connectDb();
        
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            
            activityLogData logData;
            
            while(result.next()) {
                logData = new activityLogData(
                    result.getInt("id"),
                    result.getString("username"),
                    result.getString("action"),
                    result.getString("module"),
                    result.getString("details"),
                    result.getTimestamp("timestamp"),
                    result.getString("ipAddress")
                );
                
                listData.add(logData);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return listData;
    }
    
    private ObservableList<activityLogData> activityLogListData;
    
    public void adminShowLogData() {
        activityLogListData = activityLogDataList();
        
        log_col_timestamp.setCellValueFactory(new PropertyValueFactory<>("timestamp"));
        log_col_username.setCellValueFactory(new PropertyValueFactory<>("username"));
        log_col_action.setCellValueFactory(new PropertyValueFactory<>("action"));
        log_col_module.setCellValueFactory(new PropertyValueFactory<>("module"));
        log_col_details.setCellValueFactory(new PropertyValueFactory<>("details"));
        
        admin_logTableView.setItems(activityLogListData);
    }

    // Admin Management Methods
    
    private void populateAdminRoles() {
        String sql = "SELECT roleName FROM role";
        connect = database.connectDb();
        
        try {
            List<String> roleList = new ArrayList<>();
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            
            while(result.next()) {
                roleList.add(result.getString("roleName"));
            }
            
            ObservableList<String> listData = FXCollections.observableArrayList(roleList);
            admin_role.setItems(listData);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void populateAdminStatus() {
        // Define status options
        List<String> statusList = Arrays.asList("active", "inactive", "locked");
        ObservableList<String> listData = FXCollections.observableArrayList(statusList);
        admin_status.setItems(listData);
    }
    
/*
    public ObservableList<adminData> adminDataList() {
        ObservableList<adminData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM admin ORDER BY username";

        connect = database.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            adminData adminD;

            while(result.next()) {
                adminD = new adminData(
                    result.getInt("id"),
                    result.getString("username"),
                    result.getString("fullName"),
                    result.getString("email"),
                    result.getString("role"),
                    result.getString("status"),
                    result.getTimestamp("lastLogin"),
                    result.getString("createdBy"),
                    result.getTimestamp("createdAt")
                );

                listData.add(adminD);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listData;
    }
*/

}

// THATS IT FOR THIS VIDEO, THANKS FOR WATCHING! : ) 
// PLEASE SUPPORT OUR CHANNEL! THANK YOU!
// SEE YOU ON THE NEXT VIDEO TUTORIALS!! 
// BYE!
