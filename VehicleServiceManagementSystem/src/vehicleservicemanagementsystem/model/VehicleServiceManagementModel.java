/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehicleservicemanagementsystem.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alerz
 */
public class VehicleServiceManagementModel implements IVehicleServiceManagementModel {
    // Connection variables.
    private static final String URL = "jdbc:mysql://localhost/CarServiceDB";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "password";
    private Connection connection = null; // manages connection
    
    // Queries
    private PreparedStatement selectCustomersByName = null;
    private PreparedStatement selectCustomersByPhone = null;
    private PreparedStatement insertNewCustomer = null;
    private PreparedStatement updateCustomer = null;
    
    private PreparedStatement insertVehicle = null;
    private PreparedStatement selectVehicleByID = null;
    
    private PreparedStatement selectAllServices = null;
    private PreparedStatement selectServiceByVehicle = null;
    private PreparedStatement insertService = null;
    private PreparedStatement deleteService = null;
    
    private PreparedStatement getServicePrices = null;
    private PreparedStatement getVehicleBrands = null;
    
    public VehicleServiceManagementModel() {
        try {
            connection = DriverManager.getConnection( URL,USERNAME,PASSWORD );
           
            selectCustomersByName = connection.prepareStatement("SELECT * FROM CUSTOMERS WHERE firstName = ? AND lastName = ?");
            selectCustomersByPhone = connection.prepareStatement("SELECT * FROM CUSTOMERS WHERE phone = ?");
            insertNewCustomer = connection.prepareStatement("INSERT INTO CUSTOMERS (firstName, lastName, address, phone) VALUES (?,?,?,?)");
            updateCustomer = connection.prepareStatement("UPDATE CUSTOMERS SET address = ?, phone = ? WHERE customerID = ?");

            insertVehicle = connection.prepareStatement("INSERT INTO VEHICLES (vehicleRegoNumber, brand, model, manufacturedYear, numberOfKM, customerID) VALUES (?,?,?,?,?,?)");
            selectVehicleByID = connection.prepareStatement("SELECT * FROM VEHICLES WHERE customerID = ?");
            
            selectAllServices = connection.prepareStatement("SELECT * FROM SERVICES AS S LEFT JOIN VEHICLES AS V ON S.vehicleRegoNumber = V.vehicleRegoNumber LEFT JOIN CUSTOMERS AS C ON C.customerID = V.customerID");
            selectServiceByVehicle = connection.prepareStatement("SELECT * FROM SERVICES AS S LEFT JOIN VEHICLES AS V ON S.vehicleRegoNumber = V.vehicleRegoNumber LEFT JOIN CUSTOMERS AS C ON C.customerID = V.customerID WHERE S.vehicleRegoNumber = ?");
            insertService = connection.prepareStatement("INSERT INTO SERVICES (description, date, price, vehicleRegoNumber) VALUES (?,?,?,?)");
            deleteService = connection.prepareStatement("DELETE FROM SERVICES WHERE vehicleRegoNumber = ? AND date = ?");
            
            getServicePrices = connection.prepareStatement("SELECT PRICE FROM SERVICES");
            getVehicleBrands = connection.prepareStatement("SELECT BRAND, COUNT(*) FROM VEHICLES GROUP BY BRAND");
        } catch ( SQLException sqlException ) {
            sqlException.printStackTrace();
            System.exit( 1 );
        } // end catch
    }

    @Override
    public List<Object> getCustomersByName(String firstName, String lastName) {
        List<Vehicle> vehicleResults = new ArrayList<Vehicle>();
        Customer customerResult = null;
        ResultSet resultSet = null;
        
        try {
            // executeQuery returns ResultSet containing matching entries
            selectCustomersByName.setString(1, firstName);
            selectCustomersByName.setString(2, lastName);
            resultSet = selectCustomersByName.executeQuery();

            while ( resultSet.next() ) {
                if (customerResult == null) {
                    customerResult = new Customer(
                        resultSet.getInt( "customerID" ),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("address"),
                        resultSet.getString("phone")
                    );
                }
            } // end while
                
            if (customerResult == null) {
                return null;
            }
            
            selectVehicleByID.setInt(1, customerResult.getId());
            
            resultSet.close();
            
            resultSet = selectVehicleByID.executeQuery();
            while ( resultSet.next() ) {
                vehicleResults.add( new Vehicle(
                    resultSet.getString("vehicleRegoNumber"),
                    resultSet.getString("brand"),
                    resultSet.getString("model"),
                    resultSet.getInt("manufacturedYear"),
                    resultSet.getInt("numberOfKM"),
                    resultSet.getInt("customerID")
                ));
            } // end while    
                
            
        } // end try
        catch ( SQLException sqlException )
        {
           sqlException.printStackTrace();
        } // end catch
        finally {
           try {
              resultSet.close();
           } // end try
           catch ( SQLException sqlException ) {
              sqlException.printStackTrace();
              close();
           } // end catch
        } // end finally
        
        List<Object> results = new ArrayList<Object>();
        results.add(vehicleResults);
        results.add(customerResult);
        
        return results;
    }

    @Override
    public List<Object> getCustomersByPhone(String phone) {
        List<Vehicle> vehicleResults = new ArrayList<Vehicle>();
        Customer customerResult = null;
        ResultSet resultSet = null;
        
        try {
            // executeQuery returns ResultSet containing matching entries
            selectCustomersByPhone.setString(1, phone);
            resultSet = selectCustomersByPhone.executeQuery();
            
            while ( resultSet.next() ) {
                if (customerResult == null) {
                    customerResult = new Customer(
                        resultSet.getInt( "customerID" ),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("address"),
                        resultSet.getString("phone")
                    );
                }
            } // end while
                
            if (customerResult == null) {
                return null;
            }
            
            resultSet.close();
            
            selectVehicleByID.setInt(1, customerResult.getId());
            
            resultSet = selectVehicleByID.executeQuery();
            while ( resultSet.next() ) {
                vehicleResults.add( new Vehicle(
                    resultSet.getString("vehicleRegoNumber"),
                    resultSet.getString("brand"),
                    resultSet.getString("model"),
                    resultSet.getInt("manufacturedYear"),
                    resultSet.getInt("numberOfKM"),
                    resultSet.getInt("customerID")
                ));
            } // end while    
            
        } // end try
        catch ( SQLException sqlException )
        {
           sqlException.printStackTrace();
        } // end catch
        finally {
           try {
              resultSet.close();
           } // end try
           catch ( SQLException sqlException ) {
              sqlException.printStackTrace();
              close();
           } // end catch
        } // end finally
        
        List<Object> results = new ArrayList<Object>();
        results.add(vehicleResults);
        results.add(customerResult);
        
        return results;
    }

    @Override
    public int addCustomer(String firstName, String lastName, String address, String phone) {
        int result = 0;
        // set parameters, then execute insertNewPatient
        try {
           insertNewCustomer.setString( 1, firstName );
           insertNewCustomer.setString( 2, lastName );
           insertNewCustomer.setString( 3, address );
           insertNewCustomer.setString( 4, phone );

           // insert the new entry; returns # of rows updated
           result = insertNewCustomer.executeUpdate();
        } // end try
        catch ( SQLException sqlException ) {
           sqlException.printStackTrace();
        } // end catch

        return result;
    }

    @Override
    public int updateCustomer(int id, String address, String phone) {
        int result = 0;
        
        // set parameters, then execute insertNewPatient
        try {
            updateCustomer.setString( 1, address );
            updateCustomer.setString( 2, phone );
            updateCustomer.setInt( 3, id );
           
           // insert the new entry; returns # of rows updated
           result = updateCustomer.executeUpdate();
        } // end try
        catch ( SQLException sqlException ) {
           sqlException.printStackTrace();
        } // end catch
        
        return result;
    }

    @Override
    public int addVehicleToCustomer(String vehicleRegoNumber, String brand, String model, int manufacturedYear, int numberOfKM, int customerID) {
        int result = 0;
        
        try {
            insertVehicle.setString(1, vehicleRegoNumber);
            insertVehicle.setString(2, brand);
            insertVehicle.setString(3, model);
            insertVehicle.setInt(4, manufacturedYear);
            insertVehicle.setInt(5, numberOfKM);
            insertVehicle.setInt(6, customerID);
            
            // insert the new entry; returns # of rows updated
           result = insertVehicle.executeUpdate();
        } catch ( SQLException sqlException ) {
           sqlException.printStackTrace();
        } // end catch
        
        return result;
        
    }

    @Override
    public List<Object> getAllServices() {
        List<Service> serviceResults = new ArrayList<Service>();
        List<Vehicle> vehicleResults = new ArrayList<Vehicle>();
        List<Customer> customerResults = new ArrayList<Customer>();
        ResultSet resultSet = null;
        
        try {
            // executeQuery returns ResultSet containing matching entries
            resultSet = selectAllServices.executeQuery();

            while ( resultSet.next() ) {
             
                serviceResults.add( new Service(
                    resultSet.getInt( "serviceID" ),
                    resultSet.getString("description"),
                    resultSet.getDate("date").toString(),
                    resultSet.getDouble("price"),
                    resultSet.getString("vehicleRegoNumber")
                ) );
                
                vehicleResults.add( new Vehicle(
                    resultSet.getString("vehicleRegoNumber"),
                    resultSet.getString("brand"),
                    resultSet.getString("model"),
                    resultSet.getInt("manufacturedYear"),
                    resultSet.getInt("numberOfKM"),
                    resultSet.getInt("customerID")
                ));
                
                customerResults.add( new Customer(
                    resultSet.getInt( "customerID" ),
                    resultSet.getString("firstName"),
                    resultSet.getString("lastName"),
                    resultSet.getString("address"),
                    resultSet.getString("phone")
                ));
            } // end while
        } // end try
        catch ( SQLException sqlException )
        {
           sqlException.printStackTrace();
        } // end catch
        finally {
           try {
              resultSet.close();
           } // end try
           catch ( SQLException sqlException ) {
              sqlException.printStackTrace();
              close();
           } // end catch
        } // end finally
        
        List<Object> results = new ArrayList<Object>();
        results.add(serviceResults);
        results.add(vehicleResults);
        results.add(customerResults);
        
        return results;
    }

    @Override
    public List<Object> getServiceByVehicle(String vehicleRegoNumber) {
        List<Service> serviceResults = new ArrayList<Service>();
        List<Vehicle> vehicleResults = new ArrayList<Vehicle>();
        List<Customer> customerResults = new ArrayList<Customer>();
        ResultSet resultSet = null;
        
        try {
            selectServiceByVehicle.setString(1, vehicleRegoNumber);
            // executeQuery returns ResultSet containing matching entries
            resultSet = selectServiceByVehicle.executeQuery();

            while ( resultSet.next() ) {
                
                vehicleResults.add( new Vehicle(
                    resultSet.getString("vehicleRegoNumber"),
                    resultSet.getString("brand"),
                    resultSet.getString("model"),
                    resultSet.getInt("manufacturedYear"),
                    resultSet.getInt("numberOfKM"),
                    resultSet.getInt("customerID")     
                ));

                serviceResults.add( new Service(
                    resultSet.getInt( "serviceID" ),
                    resultSet.getString("description"),
                    resultSet.getDate("date").toString(),
                    resultSet.getDouble("price"),
                    resultSet.getString("vehicleRegoNumber")
                ) );
                
                customerResults.add( new Customer(
                    resultSet.getInt( "customerID" ),
                    resultSet.getString("firstName"),
                    resultSet.getString("lastName"),
                    resultSet.getString("address"),
                    resultSet.getString("phone")
                ));

            } // end while
        } // end try
        catch ( SQLException sqlException )
        {
           sqlException.printStackTrace();
        } // end catch
        finally {
           try {
              resultSet.close();
           } // end try
           catch ( SQLException sqlException ) {
              sqlException.printStackTrace();
              close();
           } // end catch
        } // end finally
        List<Object> results = new ArrayList<Object>();
        
        results.add(serviceResults);
        results.add(vehicleResults);
        results.add(customerResults);
        return results; 
    }

    @Override
    public int addServiceToCustomer(String description, Date date, double price, String vehicleRegoNumber) {
        int result = 0;
        
        try {
            insertService.setString(1, description);
            insertService.setDate(2, date);
            insertService.setDouble(3, price);
            insertService.setString(4, vehicleRegoNumber);
            
            // insert the new entry; returns # of rows updated
           result = insertService.executeUpdate();
        } catch ( SQLException sqlException ) {
           sqlException.printStackTrace();
        } // end catch
        
        return result;
    }

    /**
     *
     * @param vehicleRegoNumber
     * @param date
     * @return
     * @throws ParseException
     */
    @Override
    public int deleteService(String vehicleRegoNumber, String date) throws ParseException {
        int result = 0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsed = format.parse(date);
        java.sql.Date sql = new java.sql.Date(parsed.getTime());
        try {
            deleteService.setString(1, vehicleRegoNumber);
            deleteService.setDate(2, sql);
            
            // insert the new entry; returns # of rows updated
           result = deleteService.executeUpdate();
        } catch ( SQLException sqlException ) {
           sqlException.printStackTrace();
        } // end catch
        
        return result;
    }

    @Override
    public List<String> getStatistics() {
        String priceResults = "";
        String brandCount = "";
        List<String> results = new ArrayList<String>();
        ResultSet resultSet = null;
        
        try {
            // executeQuery returns ResultSet containing matching entries
            resultSet = getServicePrices.executeQuery();

            while ( resultSet.next() ) {
                priceResults += resultSet.getDouble("price") + " ";
            } // end while
            resultSet.close();
            
            resultSet = getVehicleBrands.executeQuery();
            while ( resultSet.next() ) {
                brandCount += resultSet.getString("brand") + "-";
                brandCount += resultSet.getInt("count(*)") + " ";
            } // end while
            
        } // end try
        catch ( SQLException sqlException )
        {
           sqlException.printStackTrace();
        } // end catch
        finally {
           try {
              resultSet.close();
           } // end try
           catch ( SQLException sqlException ) {
              sqlException.printStackTrace();
              close();
           } // end catch
        } // end finally
        results.add(priceResults);
        results.add(brandCount);
        return results;
    }

    @Override
    public void close() {
        try {
           connection.close();
        } // end try
        catch ( SQLException sqlException ) {
           sqlException.printStackTrace();
        } // end catch
    }
}
