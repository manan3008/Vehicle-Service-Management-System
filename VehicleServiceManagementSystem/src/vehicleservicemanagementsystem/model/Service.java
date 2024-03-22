/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehicleservicemanagementsystem.model;

import java.sql.Date;

/**
 *
 * @author Alerz
 */
public class Service {
    private int serviceID; // Primary Key.
    private String description;
    private Date date;
    private double price;
    private String vehicleRegoNumber; // Foreign Key
    
    public Service() {

    }
    
    public Service(int serviceID, String description, String date, double price, String vehicleRegoNumber) {
        this.serviceID = serviceID;
        this.description = description;
        this.date = Date.valueOf(date);
        this.price = price;
        this.vehicleRegoNumber = vehicleRegoNumber;
    }

    public int getServiceID() {
        return serviceID;
    }

    public void setServiceID(int serviceID) {
        this.serviceID = serviceID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = Date.valueOf(date);
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getVehicleRegoNumber() {
        return vehicleRegoNumber;
    }

    public void setVehicleRegoNumber(String vehicleRegoNumber) {
        this.vehicleRegoNumber = vehicleRegoNumber;
    }
    
    @Override
    public String toString() {
        return String.format("%9d %20s %10s %8.2f", serviceID, description, date.toString(), price);
    }
}
