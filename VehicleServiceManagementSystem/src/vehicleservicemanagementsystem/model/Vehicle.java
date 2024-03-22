/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehicleservicemanagementsystem.model;

/**
 *
 * @author Alerz
 */
public class Vehicle {
    private String vehicleRegoNumber; // Primary Key
    private String brand;
    private String model;
    private int manufacturedYear;
    private int numberOfKM;
    private int customerID; // Foreign Key
    
    public Vehicle() {

    }
    
    public Vehicle(String vehicleRegoNumber, String brand, String model, int manufacturedYear, int numberOfKM, int customerID) {
        this.vehicleRegoNumber = vehicleRegoNumber;
        this.brand = brand;
        this.model = model;
        this.manufacturedYear = manufacturedYear;
        this.numberOfKM = numberOfKM;
        this.customerID = customerID;
    }

    public String getVehicleRegoNumber() {
        return vehicleRegoNumber;
    }

    public void setVehicleRegoNumber(String vehicleRegoNumber) {
        this.vehicleRegoNumber = vehicleRegoNumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getManufacturedYear() {
        return manufacturedYear;
    }

    public void setManufacturedYear(int manufacturedYear) {
        this.manufacturedYear = manufacturedYear;
    }

    public int getNumberOfKM() {
        return numberOfKM;
    }

    public void setNumberOfKM(int numberOfKM) {
        this.numberOfKM = numberOfKM;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
    
    @Override
    public String toString() {
        return String.format("%18s %11s %12s %16d %10d", vehicleRegoNumber, brand, model, manufacturedYear, numberOfKM);
    }
}
