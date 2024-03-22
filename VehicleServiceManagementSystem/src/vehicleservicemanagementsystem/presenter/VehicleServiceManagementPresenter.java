/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehicleservicemanagementsystem.presenter;

import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import vehicleservicemanagementsystem.model.Customer;
import vehicleservicemanagementsystem.model.IVehicleServiceManagementModel;
import vehicleservicemanagementsystem.model.Service;
import vehicleservicemanagementsystem.model.Vehicle;
import vehicleservicemanagementsystem.view.IVehicleServiceManagementView;

/**
 *
 * @author Alerz
 */
public class VehicleServiceManagementPresenter {
    IVehicleServiceManagementModel model;
    IVehicleServiceManagementView view;
    
    // Customer Variables
    private List<Customer> customerResults;
    private Customer currentCustomer;
    
    // Vehicle Variables
    private List<Vehicle> vehicleResults;
    private Vehicle currentVehicle;
    
    // Service Variables
    private List<Service> serviceResults;
    private int currentEntryIndex;
    private int numberOfEntries;
    private Service currentService;
    public VehicleServiceManagementPresenter(IVehicleServiceManagementModel model, IVehicleServiceManagementView view) {
        this.view = view;
        this.model = model;
        
        // Customer Variables
        customerResults = null;
        currentCustomer = null;
        
        // Vehicle Variables
        vehicleResults = null;
        currentVehicle = null;
        
        // Service Variables
        currentEntryIndex = 0;
        numberOfEntries = 0;
        serviceResults = null;
        currentService = null;
    }
    
    // private-helper
    private void populateAllTextFields() {
        view.displayCustomer(currentCustomer);
        view.displayVehicle(currentVehicle);
        view.displayService(currentService);
        view.displayMaxAndCurrent(numberOfEntries, currentEntryIndex);
    }

    // handles call when previousButton is clicked
    public void showPreviousService() {
        currentEntryIndex--;
        // wrap around
        if ( currentEntryIndex < 0 ) {
           currentEntryIndex = numberOfEntries - 1;
        }
        currentService = serviceResults.get( currentEntryIndex );
        currentVehicle = vehicleResults.get( currentEntryIndex);
        currentCustomer = customerResults.get(currentEntryIndex);
        populateAllTextFields();
    }

    // handles call when nextButton is clicked
    public void showNextService() {
        currentEntryIndex++;
        // wrap around
        if ( currentEntryIndex >= numberOfEntries ) {
           currentEntryIndex = 0;
        }
        currentService = serviceResults.get( currentEntryIndex );
        currentVehicle = vehicleResults.get( currentEntryIndex);
        currentCustomer = customerResults.get(currentEntryIndex);
        populateAllTextFields();
    }
    
    // handles call when queryButton is clicked
    public void performQueryByName(String firstName, String lastName) {
        if (firstName == null || firstName.equals("")) {
            view.displayMessage("Enter a first name.");
            return;
        }
        
        if (lastName == null || lastName.equals("")) {
            view.displayMessage("Enter a last name.");
            return;
        }
        System.out.println("lmfao");
        List<Object> results = model.getCustomersByName( firstName, lastName );
        serviceResults = null;
        currentService = null;
        currentCustomer = null;
        currentVehicle = null;
        view.setBrowsing(false);
        if (results == null) {
            view.displayMessage("No results.");
        }
        customerResults = new ArrayList<Customer>();
        vehicleResults = (List<Vehicle>)results.get(0);

        if (vehicleResults.size() == 0) {
            // no vehicles
            vehicleResults = null;
            customerResults = new ArrayList<Customer>();
            customerResults.add((Customer) results.get(1));
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("%4s %9s %9s %12s %20s\n", "id", "firstName", "lastName", "phone", "address"));
            sb.append(customerResults.get(0).toString());
            view.displayTextArea(sb.toString());
        } else {
            customerResults = new ArrayList<Customer>();
            for (int i = 0; i < vehicleResults.size(); i++) {
                customerResults.add((Customer)results.get(1));
            }  
            
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("%4s %9s %9s %12s %20s", "id", "firstName", "lastName", "phone", "address"));
            sb.append(String.format("%18s %11s %12s %16s %10s\n", "vehicleRegoNumber", "brand", "model", "manufacturedYear", "numberOfKM"));
            for (int i = 0; i < vehicleResults.size(); i++) {
                sb.append(customerResults.get(i).toString());
                sb.append(vehicleResults.get(i).toString());
                sb.append("\n");
            }
            view.displayTextArea(sb.toString());
        }
    }
    public void performQueryByPhone(String phone) {
        if (phone == null || phone.equals("")) {
            view.displayMessage("Enter a phone.");
            return;
        }
        
        List<Object> results = model.getCustomersByPhone( phone );
        serviceResults = null;
        currentService = null;
        currentCustomer = null;
        currentVehicle = null;
        view.setBrowsing(false);
        if (results == null) {
            view.displayMessage("No results.");
        }
        customerResults = new ArrayList<Customer>();
        vehicleResults = (List<Vehicle>)results.get(0);

        if (vehicleResults.size() == 0) {
            // no vehicles
            vehicleResults = null;
            customerResults = new ArrayList<Customer>();
            customerResults.add((Customer) results.get(1));
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("%4s %9s %9s %12s %20s\n", "id", "firstName", "lastName", "phone", "address"));
            sb.append(customerResults.get(0).toString());
            view.displayTextArea(sb.toString());
        } else {
            customerResults = new ArrayList<Customer>();
            for (int i = 0; i < vehicleResults.size(); i++) {
                customerResults.add((Customer)results.get(1));
            }  
            
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("%4s %9s %9s %12s %20s", "id", "firstName", "lastName", "phone", "address"));
            sb.append(String.format("%18s %11s %12s %16s %10s\n", "vehicleRegoNumber", "brand", "model", "manufacturedYear", "numberOfKM"));
            for (int i = 0; i < vehicleResults.size(); i++) {
                sb.append(customerResults.get(i).toString());
                sb.append(vehicleResults.get(i).toString());
                sb.append("\n");
            }
            view.displayTextArea(sb.toString());
        }
   }
   // handles call when browseButton is clicked
   public void displayAllServices() {
        List<Object> results = model.getAllServices();
        serviceResults = (List<Service>)results.get(0);
        vehicleResults = (List<Vehicle>)results.get(1);
        customerResults = (List<Customer>)results.get(2);
        numberOfEntries = serviceResults.size();
        switch (numberOfEntries) {
            case 0:
                view.displayMessage("Not found");
                break;
            case 1:
                currentEntryIndex = 0;
                currentService = serviceResults.get( currentEntryIndex );
                currentVehicle = vehicleResults.get( currentEntryIndex);
                currentCustomer = customerResults.get(currentEntryIndex);
                populateAllTextFields();
                view.setBrowsing(false);
                view.displayTextArea(results.get(currentEntryIndex).toString());
                break;
            default:
                currentEntryIndex = 0;
                currentService = serviceResults.get( currentEntryIndex );
                currentVehicle = vehicleResults.get( currentEntryIndex);
                currentCustomer = customerResults.get(currentEntryIndex);
                populateAllTextFields();
                view.setBrowsing(true);
                StringBuilder sb = new StringBuilder();
                sb.append(String.format("%9s %20s %10s %8s %3s %9s %9s %12s %20s %17s %11s %12s %16s %10s\n", "serviceID", "description", "date", "price", "id", "firstName", "lastName", "phone", "address", "vehicleRegoNumber", "brand", "model", "manufacturedYear", "numberOfKM"));
                for (int i = 0; i < numberOfEntries; i++) {
                    sb.append(serviceResults.get(i).toString());
                    sb.append(customerResults.get(i).toString());
                    sb.append(vehicleResults.get(i).toString());
                    sb.append("\n");
                }
                view.displayTextArea(sb.toString());
                break;
        }
   }

    // handles call when insertButton is clicked
    public void insertCustomer(String firstName, String lastName, String address, String phone) {
        if (firstName == null || firstName.equals("")) {
            view.displayMessage("Enter a first name.");
            return;
        }
        
        if (lastName == null || lastName.equals("")) {
            view.displayMessage("Enter a last name.");
            return;
        }
        
        if (address == null || address.equals("")) {
            view.displayMessage("Enter a address.");
            return;
        }
        
        if (phone == null || phone.equals("")) {
            view.displayMessage("Enter a phone.");
            return;
        }
        
        int result = model.addCustomer(firstName, lastName, address, phone);
        if ( result == 1 ) {
            view.displayMessage("Customer added");
        } else {
            view.displayMessage("Customer not added");
        }

        displayAllServices();
    }
    
    public void updateEntry(int id, String address, String phone) {
        if (address == null || address.equals("")) {
            view.displayMessage("Enter a address.");
            return;
        }
        
        if (phone == null || phone.equals("")) {
            view.displayMessage("Enter a phone.");
            return;
        }
        int result = model.updateCustomer(id, address, phone);
        if ( result == 1 ) {
            view.displayMessage("Customer updated");
        } else {
            view.displayMessage("Customer update unsuccessful");
        }
        
        displayAllServices();
    }
    
    public void performQueryByVehicle(String vehicleRegoNumber) {
        if (vehicleRegoNumber == null || vehicleRegoNumber.equals("")) {
            view.displayMessage("Enter a vehicle rego number.");
            return;
        }
        List<Object> results = model.getServiceByVehicle(vehicleRegoNumber);
        serviceResults = (List<Service>)results.get(0);
        vehicleResults = (List<Vehicle>)results.get(1);
        customerResults = (List<Customer>)results.get(2);
        
        numberOfEntries = serviceResults.size();
        StringBuilder sb = new StringBuilder();
        switch (numberOfEntries) {
            case 0:
                view.displayMessage("Not found");
                break;
            case 1:
                currentEntryIndex = 0;
                currentService = serviceResults.get( currentEntryIndex );
                currentVehicle = vehicleResults.get( currentEntryIndex);
                currentCustomer = customerResults.get(currentEntryIndex);
                populateAllTextFields();
                view.setBrowsing(false);
                
                sb.append(String.format("%9s %20s %10s %8s %3s %9s %9s %12s %20s %17s %11s %12s %16s %10s\n", "serviceID", "description", "date", "price", "id", "firstName", "lastName", "phone", "address", "vehicleRegoNumber", "brand", "model", "manufacturedYear", "numberOfKM"));
                sb.append(serviceResults.get(0).toString());
                sb.append(customerResults.get(0).toString());
                sb.append(vehicleResults.get(0).toString());
                sb.append("\n");
                view.displayTextArea(sb.toString());
                break;
            default:
                currentEntryIndex = 0;
                currentService = serviceResults.get( currentEntryIndex );
                currentVehicle = vehicleResults.get( currentEntryIndex);
                currentCustomer = customerResults.get(currentEntryIndex);
                populateAllTextFields();
                view.setBrowsing(true);
                
                sb.append(String.format("%9s %20s %10s %8s %3s %9s %9s %12s %20s %17s %11s %12s %16s %10s\n", "serviceID", "description", "date", "price", "id", "firstName", "lastName", "phone", "address", "vehicleRegoNumber", "brand", "model", "manufacturedYear", "numberOfKM"));
                for (int i = 0; i < numberOfEntries; i++) {
                    sb.append(serviceResults.get(i).toString());
                    sb.append(customerResults.get(i).toString());
                    sb.append(vehicleResults.get(i).toString());
                    sb.append("\n");
                }
                System.out.println(sb);
                view.displayTextArea(sb.toString());
                break;
        }
    }
    
    public void insertVehicle(String vehicleRegoNumber, String brand, String model, int manufacturedYear, int numberOfKM, int customerID) {
        if (vehicleRegoNumber == null || vehicleRegoNumber.equals("")) {
            view.displayMessage("Enter a vehicle rego number.");
            return;
        }
        
        if (brand == null || brand.equals("")) {
            view.displayMessage("Enter a brand.");
            return;
        }
        
        if (model == null || model.equals("")) {
            view.displayMessage("Enter a model.");
            return;
        }
        int result = this.model.addVehicleToCustomer(vehicleRegoNumber, brand, model, manufacturedYear, numberOfKM, customerID);
        if ( result == 1 ) {
            view.displayMessage("Customer added");
        } else {
            view.displayMessage("Customer not added");
        }

        displayAllServices();
    }
    
    public void insertService(String description, String date, double price, String vehicleRegoNumber) {
        if (description == null || description.equals("")) {
            view.displayMessage("Enter a description.");
            return;
        }
        
        if (vehicleRegoNumber == null || vehicleRegoNumber.equals("")) {
            view.displayMessage("Enter a vehicleRegoNumber.");
            return;
        }
        
        if (date == null) {
            view.displayMessage("Enter a date.");
            return;
        }
        
        int result = model.addServiceToCustomer(description, Date.valueOf(date), price,vehicleRegoNumber);
        if ( result == 1 ) {
            view.displayMessage("service added");
        } else {
            view.displayMessage("service not added");
        }

        displayAllServices();
    
    }
    
    public void deleteService(String vehicleRegoNumber, String date) {
        if (date == null || date.equals("")) {
            view.displayMessage("Enter a date.");
            return;
        }
        
        if (vehicleRegoNumber == null || vehicleRegoNumber.equals("")) {
            view.displayMessage("Enter a vehicleRegoNumber.");
            return;
        }
        
        int result;
        try {
            result = model.deleteService(vehicleRegoNumber, date);
            if ( result == 1 ) {
                view.displayMessage("service deleted");
            } else {
                view.displayMessage("service not deleted");
            }
        } catch (ParseException ex) {
            Logger.getLogger(VehicleServiceManagementPresenter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        displayAllServices();
    
    }
    
    public void showStatistics() {
        List<String> stats = model.getStatistics();
        String[] priceString = stats.get(0).split(" ");
        
        double average = 0;
        double min = Double.valueOf(priceString[0]);
        double max = Double.valueOf(priceString[0]);
        
        for (int i = 0; i < priceString.length; i++) {
            if (min > Double.valueOf(priceString[i])) {
                min = Double.valueOf(priceString[i]);
            } else if (max < Double.valueOf(priceString[i])) {
                max = Double.valueOf(priceString[i]);
            }
            
            average += Double.valueOf(priceString[i]);
            
        }
        
        average /= priceString.length;
        
        
        String averageString = String.format("The minimum service offered is %.2f.\n", min) + String.format("The maximum service offered is %.2f.\n", max) + String.format("The average service offered is %.2f.\n\n", average);
        List<String> send = new ArrayList<String>();
        send.add(averageString);
        
        String[] brandAmalgamatedString = stats.get(1).split(" ");
        for (int i = 0; i < brandAmalgamatedString.length; i++) {
            System.out.println(brandAmalgamatedString[i]);
        }
        List<String> brandStrings = new ArrayList<String>();
        List<String> countStrings = new ArrayList<String>();
        for (int i = 0; i < brandAmalgamatedString.length; i++) {
            String[] stuff = brandAmalgamatedString[i].split("-");
            brandStrings.add(stuff[0]);
            countStrings.add(stuff[1]);
        }
        
        String countString = "";
        for (int i = 0; i < countStrings.size(); i++) {
            countString += String.format("There are %s %s vehicles being serviced.\n", countStrings.get(i), brandStrings.get(i));
        }
        
        send.add(countString);
        view.displayStatistics(send);
    }

    // handles window closure
    public void close() {
        model.close();
    }
    
}
