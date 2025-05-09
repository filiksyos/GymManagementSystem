/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gymmanagementsystem;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.collections.ObservableList;

/**
 *
 * @author toshiba
 */
public class reportExporter {
    
    /**
     * Exports report data to a CSV file
     * 
     * @param reportType The type of report (Membership, Financial, etc.)
     * @param reportData The list of reportData objects to export
     * @param startDate The start date of the report period
     * @param endDate The end date of the report period
     * @return The path to the exported file
     * @throws IOException If there's an error writing the file
     */
    public static String exportToCSV(
            String reportType, 
            ObservableList<reportData> reportData,
            LocalDate startDate,
            LocalDate endDate) throws IOException {
        
        // Create filename with report type and date range
        String fileName = reportType + "_Report_" + 
                         startDate.toString() + "_to_" + 
                         endDate.toString() + ".csv";
        
        // Create a File object with user directory
        String userHome = System.getProperty("user.home");
        File exportFile = new File(userHome + File.separator + "Documents" + File.separator + fileName);
        
        // Ensure the parent directory exists
        if (!exportFile.getParentFile().exists()) {
            exportFile.getParentFile().mkdirs();
        }
        
        // Create FileWriter for the CSV file
        try (FileWriter writer = new FileWriter(exportFile)) {
            // Write CSV header
            writer.append("Date,Category,Metric,Value,Change,Trend\n");
            
            // Format for dates
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            
            // Write data rows
            for (reportData data : reportData) {
                writer.append(data.getDate().format(dateFormatter));
                writer.append(',');
                writer.append(escapeCSV(data.getCategory()));
                writer.append(',');
                writer.append(escapeCSV(data.getMetric()));
                writer.append(',');
                writer.append(String.valueOf(data.getValue()));
                writer.append(',');
                writer.append(String.valueOf(data.getChange()));
                writer.append(',');
                writer.append(escapeCSV(data.getTrend()));
                writer.append('\n');
            }
            
            writer.flush();
        }
        
        return exportFile.getAbsolutePath();
    }
    
    /**
     * Escapes special characters in CSV strings
     * 
     * @param value The string to escape
     * @return The escaped string
     */
    private static String escapeCSV(String value) {
        if (value == null) {
            return "";
        }
        
        // If the value contains quotes, commas, or newlines, escape it
        if (value.contains("\"") || value.contains(",") || value.contains("\n")) {
            // Replace double quotes with two double quotes
            value = value.replace("\"", "\"\"");
            // Wrap in quotes
            return "\"" + value + "\"";
        }
        
        return value;
    }
} 