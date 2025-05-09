/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gymmanagementsystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

/**
 *
 * @author toshiba
 */
public class reportGenerator {
    
    private static Connection connect;
    private static PreparedStatement prepare;
    private static ResultSet result;
    private static Statement statement;
    
    // Generate membership trend data
    public static ObservableList<XYChart.Series<String, Number>> generateMembershipTrend(LocalDate start, LocalDate end, String interval) {
        ObservableList<XYChart.Series<String, Number>> chartData = FXCollections.observableArrayList();
        
        XYChart.Series<String, Number> activeSeries = new XYChart.Series<>();
        activeSeries.setName("Active Members");
        
        XYChart.Series<String, Number> inactiveSeries = new XYChart.Series<>();
        inactiveSeries.setName("Inactive Members");
        
        try {
            connect = database.connectDb();
            
            // Define date format based on interval
            DateTimeFormatter formatter;
            long stepDays;
            
            switch (interval) {
                case "Daily":
                    formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    stepDays = 1;
                    break;
                case "Weekly":
                    formatter = DateTimeFormatter.ofPattern("yyyy-'W'ww");
                    stepDays = 7;
                    break;
                case "Monthly":
                    formatter = DateTimeFormatter.ofPattern("yyyy-MM");
                    stepDays = 30;
                    break;
                case "Yearly":
                    formatter = DateTimeFormatter.ofPattern("yyyy");
                    stepDays = 365;
                    break;
                default:
                    formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    stepDays = 1;
            }
            
            // Loop through date range with appropriate intervals
            LocalDate current = start;
            while (!current.isAfter(end)) {
                LocalDate nextDate = current.plusDays(stepDays);
                
                // Query for active members
                String activeSql = "SELECT COUNT(id) AS count FROM member WHERE status = 'Paid' AND " +
                                   "startDate <= '" + current + "' AND " +
                                   "endDate >= '" + current + "'";
                
                prepare = connect.prepareStatement(activeSql);
                result = prepare.executeQuery();
                
                int activeCount = 0;
                if (result.next()) {
                    activeCount = result.getInt("count");
                }
                
                // Query for inactive members
                String inactiveSql = "SELECT COUNT(id) AS count FROM member WHERE status = 'Unpaid' AND " +
                                    "startDate <= '" + current + "'";
                
                prepare = connect.prepareStatement(inactiveSql);
                result = prepare.executeQuery();
                
                int inactiveCount = 0;
                if (result.next()) {
                    inactiveCount = result.getInt("count");
                }
                
                String dateLabel = current.format(formatter);
                activeSeries.getData().add(new XYChart.Data<>(dateLabel, activeCount));
                inactiveSeries.getData().add(new XYChart.Data<>(dateLabel, inactiveCount));
                
                current = nextDate;
            }
            
            chartData.add(activeSeries);
            chartData.add(inactiveSeries);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return chartData;
    }
    
    // Generate financial report data
    public static ObservableList<XYChart.Series<String, Number>> generateFinancialReport(LocalDate start, LocalDate end, String interval) {
        ObservableList<XYChart.Series<String, Number>> chartData = FXCollections.observableArrayList();
        
        XYChart.Series<String, Number> revenueSeries = new XYChart.Series<>();
        revenueSeries.setName("Revenue");
        
        try {
            connect = database.connectDb();
            
            // Define date format based on interval
            DateTimeFormatter formatter;
            long stepDays;
            
            switch (interval) {
                case "Daily":
                    formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    stepDays = 1;
                    break;
                case "Weekly":
                    formatter = DateTimeFormatter.ofPattern("yyyy-'W'ww");
                    stepDays = 7;
                    break;
                case "Monthly":
                    formatter = DateTimeFormatter.ofPattern("yyyy-MM");
                    stepDays = 30;
                    break;
                case "Yearly":
                    formatter = DateTimeFormatter.ofPattern("yyyy");
                    stepDays = 365;
                    break;
                default:
                    formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    stepDays = 1;
            }
            
            // Loop through date range with appropriate intervals
            LocalDate current = start;
            while (!current.isAfter(end)) {
                LocalDate nextDate = current.plusDays(stepDays);
                
                // Query for revenue
                String sql = "SELECT SUM(price) AS revenue FROM member WHERE status = 'Paid' AND " +
                             "startDate >= '" + current + "' AND " +
                             "startDate < '" + nextDate + "'";
                
                prepare = connect.prepareStatement(sql);
                result = prepare.executeQuery();
                
                double revenue = 0.0;
                if (result.next()) {
                    revenue = result.getDouble("revenue");
                }
                
                String dateLabel = current.format(formatter);
                revenueSeries.getData().add(new XYChart.Data<>(dateLabel, revenue));
                
                current = nextDate;
            }
            
            chartData.add(revenueSeries);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return chartData;
    }
    
    // Generate equipment status distribution
    public static ObservableList<PieChart.Data> generateEquipmentStatusDistribution() {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        
        try {
            connect = database.connectDb();
            
            // Count equipment by status
            String sql = "SELECT status, COUNT(id) as count FROM equipment GROUP BY status";
            
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            
            while (result.next()) {
                String status = result.getString("status");
                int count = result.getInt("count");
                
                pieChartData.add(new PieChart.Data(status, count));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return pieChartData;
    }
    
    // Generate schedule/class capacity analysis
    public static ObservableList<XYChart.Series<String, Number>> generateScheduleCapacityAnalysis() {
        ObservableList<XYChart.Series<String, Number>> chartData = FXCollections.observableArrayList();
        
        XYChart.Series<String, Number> capacitySeries = new XYChart.Series<>();
        capacitySeries.setName("Max Capacity");
        
        XYChart.Series<String, Number> enrollmentSeries = new XYChart.Series<>();
        enrollmentSeries.setName("Current Enrollment");
        
        try {
            connect = database.connectDb();
            
            // Query for schedule data
            String sql = "SELECT className, maxCapacity, currentEnrollment FROM schedule WHERE status = 'Active'";
            
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            
            while (result.next()) {
                String className = result.getString("className");
                int maxCapacity = result.getInt("maxCapacity");
                int currentEnrollment = result.getInt("currentEnrollment");
                
                capacitySeries.getData().add(new XYChart.Data<>(className, maxCapacity));
                enrollmentSeries.getData().add(new XYChart.Data<>(className, currentEnrollment));
            }
            
            chartData.add(capacitySeries);
            chartData.add(enrollmentSeries);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return chartData;
    }
    
    // Generate coach workload distribution
    public static ObservableList<XYChart.Series<String, Number>> generateCoachWorkload() {
        ObservableList<XYChart.Series<String, Number>> chartData = FXCollections.observableArrayList();
        
        XYChart.Series<String, Number> classesSeries = new XYChart.Series<>();
        classesSeries.setName("Number of Classes");
        
        XYChart.Series<String, Number> studentsSeries = new XYChart.Series<>();
        studentsSeries.setName("Number of Students");
        
        try {
            connect = database.connectDb();
            
            // Query for coach data
            String classCountSql = "SELECT c.name, COUNT(s.id) as classCount "
                                + "FROM coach c LEFT JOIN schedule s ON c.coachId = s.coachId "
                                + "WHERE c.status = 'Active' "
                                + "GROUP BY c.name";
            
            prepare = connect.prepareStatement(classCountSql);
            result = prepare.executeQuery();
            
            // Store coach names and class counts
            Map<String, Integer> coachClasses = new HashMap<>();
            
            while (result.next()) {
                String coachName = result.getString("name");
                int classCount = result.getInt("classCount");
                
                coachClasses.put(coachName, classCount);
                classesSeries.getData().add(new XYChart.Data<>(coachName, classCount));
            }
            
            // Query for student counts
            String studentCountSql = "SELECT c.name, SUM(s.currentEnrollment) as studentCount "
                                   + "FROM coach c LEFT JOIN schedule s ON c.coachId = s.coachId "
                                   + "WHERE c.status = 'Active' "
                                   + "GROUP BY c.name";
            
            prepare = connect.prepareStatement(studentCountSql);
            result = prepare.executeQuery();
            
            while (result.next()) {
                String coachName = result.getString("name");
                int studentCount = result.getInt("studentCount");
                
                studentsSeries.getData().add(new XYChart.Data<>(coachName, studentCount));
            }
            
            chartData.add(classesSeries);
            chartData.add(studentsSeries);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return chartData;
    }
    
    // Generate detailed report data
    public static ObservableList<reportData> generateDetailedReport(String reportType, LocalDate start, LocalDate end) {
        ObservableList<reportData> reportList = FXCollections.observableArrayList();
        
        try {
            connect = database.connectDb();
            String sql = "";
            
            switch (reportType) {
                case "Membership":
                    sql = "SELECT DATE(startDate) as date, COUNT(id) as count, status as category "
                        + "FROM member "
                        + "WHERE startDate BETWEEN '" + start + "' AND '" + end + "' "
                        + "GROUP BY DATE(startDate), status "
                        + "ORDER BY date ASC";
                    
                    prepare = connect.prepareStatement(sql);
                    result = prepare.executeQuery();
                    
                    while (result.next()) {
                        LocalDate date = LocalDate.parse(result.getString("date"));
                        String category = result.getString("category");
                        int count = result.getInt("count");
                        
                        reportList.add(new reportData(date, category, count));
                    }
                    break;
                    
                case "Financial":
                    sql = "SELECT DATE(startDate) as date, SUM(price) as amount "
                        + "FROM member "
                        + "WHERE status = 'Paid' AND startDate BETWEEN '" + start + "' AND '" + end + "' "
                        + "GROUP BY DATE(startDate) "
                        + "ORDER BY date ASC";
                    
                    prepare = connect.prepareStatement(sql);
                    result = prepare.executeQuery();
                    
                    double prevAmount = 0;
                    
                    while (result.next()) {
                        LocalDate date = LocalDate.parse(result.getString("date"));
                        double amount = result.getDouble("amount");
                        
                        // Calculate percentage change
                        double change = 0;
                        if (prevAmount > 0) {
                            change = ((amount - prevAmount) / prevAmount) * 100;
                        }
                        
                        reportList.add(new reportData(date, "Revenue", amount, change));
                        prevAmount = amount;
                    }
                    break;
                    
                case "Equipment":
                    sql = "SELECT DATE(lastMaintenance) as date, status, COUNT(id) as count "
                        + "FROM equipment "
                        + "WHERE lastMaintenance BETWEEN '" + start + "' AND '" + end + "' "
                        + "GROUP BY DATE(lastMaintenance), status "
                        + "ORDER BY date ASC";
                    
                    prepare = connect.prepareStatement(sql);
                    result = prepare.executeQuery();
                    
                    while (result.next()) {
                        LocalDate date = LocalDate.parse(result.getString("date"));
                        String status = result.getString("status");
                        int count = result.getInt("count");
                        
                        reportList.add(new reportData(date, status, count));
                    }
                    break;
                    
                case "Schedule":
                    sql = "SELECT s.dayOfWeek as category, s.className, s.maxCapacity, s.currentEnrollment, "
                        + "CURRENT_DATE as date "
                        + "FROM schedule s "
                        + "WHERE s.status = 'Active' "
                        + "ORDER BY s.dayOfWeek";
                    
                    prepare = connect.prepareStatement(sql);
                    result = prepare.executeQuery();
                    
                    while (result.next()) {
                        LocalDate today = LocalDate.now();
                        String className = result.getString("className");
                        String dayOfWeek = result.getString("category");
                        int enrollment = result.getInt("currentEnrollment");
                        int capacity = result.getInt("maxCapacity");
                        
                        // Calculate utilization percentage
                        double utilization = 0;
                        if (capacity > 0) {
                            utilization = ((double) enrollment / capacity) * 100;
                        }
                        
                        reportList.add(new reportData(
                                today, 
                                dayOfWeek + ": " + className,
                                enrollment,
                                utilization));
                    }
                    break;
                    
                case "Coach":
                    sql = "SELECT c.name as category, COUNT(s.id) as classCount, "
                        + "SUM(s.currentEnrollment) as studentCount, CURRENT_DATE as date "
                        + "FROM coach c LEFT JOIN schedule s ON c.coachId = s.coachId "
                        + "WHERE c.status = 'Active' "
                        + "GROUP BY c.name";
                    
                    prepare = connect.prepareStatement(sql);
                    result = prepare.executeQuery();
                    
                    while (result.next()) {
                        LocalDate today = LocalDate.now();
                        String coachName = result.getString("category");
                        int classCount = result.getInt("classCount");
                        int studentCount = result.getInt("studentCount");
                        
                        reportList.add(new reportData(today, coachName + " (Classes)", classCount));
                        reportList.add(new reportData(today, coachName + " (Students)", studentCount));
                    }
                    break;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return reportList;
    }
} 