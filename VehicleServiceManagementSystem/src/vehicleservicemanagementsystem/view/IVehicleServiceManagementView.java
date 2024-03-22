/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehicleservicemanagementsystem.view;

import java.util.List;
import vehicleservicemanagementsystem.model.Customer;
import vehicleservicemanagementsystem.model.Service;
import vehicleservicemanagementsystem.model.Vehicle;
import vehicleservicemanagementsystem.presenter.VehicleServiceManagementPresenter;

/**
 *
 * @author Alerz
 */
public interface IVehicleServiceManagementView {
    void bind(VehicleServiceManagementPresenter p);
    void setBrowsing(Boolean f); 
    void displayMessage(String m); 
    void displayCustomer(Customer c);
    void displayVehicle(Vehicle v);
    void displayService(Service s);
    void displayMaxAndCurrent(int m,int c);
    void displayTextArea(String s);
    void displayStatistics(List<String> s);
}
