/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gymmanagementsystem;

import java.time.LocalDate;

/**
 *
 * @author toshiba
 */
public class reportData {
    private LocalDate date;
    private String category;
    private String metric;
    private double value;
    private double change;
    private String trend;
    
    public reportData(LocalDate date, String category, String metric, double value, double change, String trend) {
        this.date = date;
        this.category = category;
        this.metric = metric;
        this.value = value;
        this.change = change;
        this.trend = trend;
    }
    
    // Simplified constructor for basic reporting
    public reportData(LocalDate date, String category, double value) {
        this.date = date;
        this.category = category;
        this.value = value;
        this.change = 0.0;
        this.trend = "stable";
        this.metric = "count";
    }

    // Constructor specifically for financial reports
    public reportData(LocalDate date, String category, double value, double change) {
        this.date = date;
        this.category = category;
        this.value = value;
        this.change = change;
        
        if (change > 0) {
            this.trend = "up";
        } else if (change < 0) {
            this.trend = "down";
        } else {
            this.trend = "stable";
        }
        
        this.metric = "amount";
    }

    @Override
    public String toString() {
        return date + " - " + category + ": " + value + " (" + change + "%)";
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getChange() {
        return change;
    }

    public void setChange(double change) {
        this.change = change;
    }

    public String getTrend() {
        return trend;
    }

    public void setTrend(String trend) {
        this.trend = trend;
    }
} 