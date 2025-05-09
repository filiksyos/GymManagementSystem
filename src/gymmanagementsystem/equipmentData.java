package gymmanagementsystem;

import java.sql.Date;

public class equipmentData {
    private Integer id;
    private String equipmentId;
    private String name;
    private String type;
    private Date purchaseDate;
    private Date lastMaintenance;
    private Date nextMaintenance;
    private String status;
    
    // Constructor
    public equipmentData(Integer id, String equipmentId, String name, String type, 
                         Date purchaseDate, Date lastMaintenance, 
                         Date nextMaintenance, String status) {
        this.id = id;
        this.equipmentId = equipmentId;
        this.name = name;
        this.type = type;
        this.purchaseDate = purchaseDate;
        this.lastMaintenance = lastMaintenance;
        this.nextMaintenance = nextMaintenance;
        this.status = status;
    }
    
    // Getters
    public Integer getId() {
        return id;
    }
    
    public String getEquipmentId() {
        return equipmentId;
    }
    
    public String getName() {
        return name;
    }
    
    public String getType() {
        return type;
    }
    
    public Date getPurchaseDate() {
        return purchaseDate;
    }
    
    public Date getLastMaintenance() {
        return lastMaintenance;
    }
    
    public Date getNextMaintenance() {
        return nextMaintenance;
    }
    
    public String getStatus() {
        return status;
    }
    
    // Setters
    public void setId(Integer id) {
        this.id = id;
    }
    
    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
    
    public void setLastMaintenance(Date lastMaintenance) {
        this.lastMaintenance = lastMaintenance;
    }
    
    public void setNextMaintenance(Date nextMaintenance) {
        this.nextMaintenance = nextMaintenance;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    @Override
    public String toString() {
        return "Equipment ID: " + equipmentId + ", Name: " + name;
    }
} 