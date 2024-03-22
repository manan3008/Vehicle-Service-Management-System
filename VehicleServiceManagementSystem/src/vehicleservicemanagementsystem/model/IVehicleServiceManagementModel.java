/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehicleservicemanagementsystem.model;

import java.sql.Date;
import java.text.ParseException;
import java.util.List;

/**
 *
 * @author Alerz
 * Interface Definition.
 */
public interface IVehicleServiceManagementModel {
    
    // Customer Functions
    public List<Object> getCustomersByName(String firstName, String lastName);
    public List<Object> getCustomersByPhone(String phone);
    public int addCustomer(String firstName, String lastName, String address, String phone);
    public int updateCustomer(int id, String address, String phone);
    
    // Vehicle Functions
    public int addVehicleToCustomer(String vehicleRegoNumber, String brand, String model, int manufacturedYear, int numberOfKM, int customerID); 
    
    // Service Functions
    public List<Object> getAllServices();
    public List<Object> getServiceByVehicle(String vehicleRegoNumber);
    public int addServiceToCustomer(String description, Date date, double price, String vehicleRegoNumber);
    public int deleteService(String vehicleRegoNumber, String Date) throws ParseException;
    
    // Regular Functions
    public List<String> getStatistics();
    public void close();
}
