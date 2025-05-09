package gymmanagementsystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 * Data access class for the new tables introduced in Phase 1 expansion
 */
public class dataAccess {
    
    private Connection connect;
    
    public dataAccess() {
        connect = database.connectDb();
    }
    
    /**
     * EQUIPMENT MANAGEMENT METHODS
     */
    
    // Get all equipment items
    public List<equipmentData> getAllEquipment() {
        List<equipmentData> equipmentList = new ArrayList<>();
        
        String sql = "SELECT * FROM equipment";
        
        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
            ResultSet result = prepare.executeQuery();
            
            while (result.next()) {
                equipmentData equipment = new equipmentData(
                    result.getInt("id"),
                    result.getString("equipmentId"),
                    result.getString("name"),
                    result.getString("type"),
                    result.getDate("purchaseDate"),
                    result.getDate("lastMaintenance"),
                    result.getDate("nextMaintenance"),
                    result.getString("status")
                );
                equipmentList.add(equipment);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return equipmentList;
    }
    
    // Add new equipment
    public boolean addEquipment(equipmentData equipment) {
        String sql = "INSERT INTO equipment "
                + "(equipmentId, name, type, purchaseDate, lastMaintenance, nextMaintenance, status) "
                + "VALUES(?,?,?,?,?,?,?)";
        
        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setString(1, equipment.getEquipmentId());
            prepare.setString(2, equipment.getName());
            prepare.setString(3, equipment.getType());
            prepare.setDate(4, equipment.getPurchaseDate());
            prepare.setDate(5, equipment.getLastMaintenance());
            prepare.setDate(6, equipment.getNextMaintenance());
            prepare.setString(7, equipment.getStatus());
            
            prepare.executeUpdate();
            return true;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Update equipment
    public boolean updateEquipment(equipmentData equipment) {
        String sql = "UPDATE equipment SET name = ?, type = ?, purchaseDate = ?, "
                + "lastMaintenance = ?, nextMaintenance = ?, status = ? "
                + "WHERE equipmentId = ?";
        
        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setString(1, equipment.getName());
            prepare.setString(2, equipment.getType());
            prepare.setDate(3, equipment.getPurchaseDate());
            prepare.setDate(4, equipment.getLastMaintenance());
            prepare.setDate(5, equipment.getNextMaintenance());
            prepare.setString(6, equipment.getStatus());
            prepare.setString(7, equipment.getEquipmentId());
            
            prepare.executeUpdate();
            return true;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Delete equipment
    public boolean deleteEquipment(String equipmentId) {
        String sql = "DELETE FROM equipment WHERE equipmentId = ?";
        
        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setString(1, equipmentId);
            
            prepare.executeUpdate();
            return true;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Generate new equipment ID
    public String generateEquipmentId() {
        String sql = "SELECT COUNT(*) FROM equipment";
        int count = 0;
        
        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
            ResultSet result = prepare.executeQuery();
            
            if (result.next()) {
                count = result.getInt(1);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return "EID-" + (count + 101);
    }
    
    /**
     * SCHEDULE MANAGEMENT METHODS
     */
    
    // Get all schedules
    public List<scheduleData> getAllSchedules() {
        List<scheduleData> scheduleList = new ArrayList<>();
        
        String sql = "SELECT * FROM schedule";
        
        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
            ResultSet result = prepare.executeQuery();
            
            while (result.next()) {
                scheduleData schedule = new scheduleData(
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
                scheduleList.add(schedule);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return scheduleList;
    }
    
    // Add new schedule
    public boolean addSchedule(scheduleData schedule) {
        String sql = "INSERT INTO schedule "
                + "(scheduleId, className, coachId, dayOfWeek, startTime, endTime, maxCapacity, currentEnrollment, status) "
                + "VALUES(?,?,?,?,?,?,?,?,?)";
        
        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setString(1, schedule.getScheduleId());
            prepare.setString(2, schedule.getClassName());
            prepare.setString(3, schedule.getCoachId());
            prepare.setString(4, schedule.getDayOfWeek());
            prepare.setTime(5, schedule.getStartTime());
            prepare.setTime(6, schedule.getEndTime());
            prepare.setInt(7, schedule.getMaxCapacity());
            prepare.setInt(8, schedule.getCurrentEnrollment());
            prepare.setString(9, schedule.getStatus());
            
            prepare.executeUpdate();
            return true;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Update schedule
    public boolean updateSchedule(scheduleData schedule) {
        String sql = "UPDATE schedule SET className = ?, coachId = ?, dayOfWeek = ?, "
                + "startTime = ?, endTime = ?, maxCapacity = ?, currentEnrollment = ?, status = ? "
                + "WHERE scheduleId = ?";
        
        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setString(1, schedule.getClassName());
            prepare.setString(2, schedule.getCoachId());
            prepare.setString(3, schedule.getDayOfWeek());
            prepare.setTime(4, schedule.getStartTime());
            prepare.setTime(5, schedule.getEndTime());
            prepare.setInt(6, schedule.getMaxCapacity());
            prepare.setInt(7, schedule.getCurrentEnrollment());
            prepare.setString(8, schedule.getStatus());
            prepare.setString(9, schedule.getScheduleId());
            
            prepare.executeUpdate();
            return true;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Delete schedule
    public boolean deleteSchedule(String scheduleId) {
        String sql = "DELETE FROM schedule WHERE scheduleId = ?";
        
        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setString(1, scheduleId);
            
            prepare.executeUpdate();
            return true;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Generate new schedule ID
    public String generateScheduleId() {
        String sql = "SELECT COUNT(*) FROM schedule";
        int count = 0;
        
        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
            ResultSet result = prepare.executeQuery();
            
            if (result.next()) {
                count = result.getInt(1);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return "SID-" + (count + 101);
    }
    
    // Get schedules by coach ID
    public List<scheduleData> getSchedulesByCoach(String coachId) {
        List<scheduleData> scheduleList = new ArrayList<>();
        
        String sql = "SELECT * FROM schedule WHERE coachId = ?";
        
        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setString(1, coachId);
            ResultSet result = prepare.executeQuery();
            
            while (result.next()) {
                scheduleData schedule = new scheduleData(
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
                scheduleList.add(schedule);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return scheduleList;
    }
    
    /**
     * PAYMENT MANAGEMENT METHODS
     */
    
    // Get all payments
    public List<paymentData> getAllPayments() {
        List<paymentData> paymentList = new ArrayList<>();
        
        String sql = "SELECT * FROM payment";
        
        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
            ResultSet result = prepare.executeQuery();
            
            while (result.next()) {
                paymentData payment = new paymentData(
                    result.getInt("id"),
                    result.getString("paymentId"),
                    result.getString("memberId"),
                    result.getDouble("amount"),
                    result.getDate("paymentDate"),
                    result.getString("paymentMethod"),
                    result.getString("description")
                );
                paymentList.add(payment);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return paymentList;
    }
    
    // Add new payment
    public boolean addPayment(paymentData payment) {
        String sql = "INSERT INTO payment "
                + "(paymentId, memberId, amount, paymentDate, paymentMethod, description) "
                + "VALUES(?,?,?,?,?,?)";
        
        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setString(1, payment.getPaymentId());
            prepare.setString(2, payment.getMemberId());
            prepare.setDouble(3, payment.getAmount());
            prepare.setDate(4, payment.getPaymentDate());
            prepare.setString(5, payment.getPaymentMethod());
            prepare.setString(6, payment.getDescription());
            
            prepare.executeUpdate();
            return true;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Update payment
    public boolean updatePayment(paymentData payment) {
        String sql = "UPDATE payment SET memberId = ?, amount = ?, paymentDate = ?, "
                + "paymentMethod = ?, description = ? "
                + "WHERE paymentId = ?";
        
        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setString(1, payment.getMemberId());
            prepare.setDouble(2, payment.getAmount());
            prepare.setDate(3, payment.getPaymentDate());
            prepare.setString(4, payment.getPaymentMethod());
            prepare.setString(5, payment.getDescription());
            prepare.setString(6, payment.getPaymentId());
            
            prepare.executeUpdate();
            return true;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Delete payment
    public boolean deletePayment(String paymentId) {
        String sql = "DELETE FROM payment WHERE paymentId = ?";
        
        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setString(1, paymentId);
            
            prepare.executeUpdate();
            return true;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Generate new payment ID
    public String generatePaymentId() {
        String sql = "SELECT COUNT(*) FROM payment";
        int count = 0;
        
        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
            ResultSet result = prepare.executeQuery();
            
            if (result.next()) {
                count = result.getInt(1);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return "PID-" + (count + 101);
    }
    
    // Get payments by member ID
    public List<paymentData> getPaymentsByMember(String memberId) {
        List<paymentData> paymentList = new ArrayList<>();
        
        String sql = "SELECT * FROM payment WHERE memberId = ?";
        
        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setString(1, memberId);
            ResultSet result = prepare.executeQuery();
            
            while (result.next()) {
                paymentData payment = new paymentData(
                    result.getInt("id"),
                    result.getString("paymentId"),
                    result.getString("memberId"),
                    result.getDouble("amount"),
                    result.getDate("paymentDate"),
                    result.getString("paymentMethod"),
                    result.getString("description")
                );
                paymentList.add(payment);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return paymentList;
    }
} 