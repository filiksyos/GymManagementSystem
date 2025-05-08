package gymmanagementsystem;

import java.sql.Date;

public class paymentData {
    private Integer id;
    private String paymentId;
    private String memberId;
    private Double amount;
    private Date paymentDate;
    private String paymentMethod;
    private String description;
    
    // Constructor
    public paymentData(Integer id, String paymentId, String memberId, Double amount,
                      Date paymentDate, String paymentMethod, String description) {
        this.id = id;
        this.paymentId = paymentId;
        this.memberId = memberId;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
        this.description = description;
    }
    
    // Getters
    public Integer getId() {
        return id;
    }
    
    public String getPaymentId() {
        return paymentId;
    }
    
    public String getMemberId() {
        return memberId;
    }
    
    public Double getAmount() {
        return amount;
    }
    
    public Date getPaymentDate() {
        return paymentDate;
    }
    
    public String getPaymentMethod() {
        return paymentMethod;
    }
    
    public String getDescription() {
        return description;
    }
    
    // Setters
    public void setId(Integer id) {
        this.id = id;
    }
    
    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }
    
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
    
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    
    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }
    
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    // Format amount as currency
    public String getFormattedAmount() {
        return String.format("₱%.2f", amount);
    }
    
    @Override
    public String toString() {
        return "Payment ID: " + paymentId + ", Amount: ₱" + amount + ", Date: " + paymentDate;
    }
} 