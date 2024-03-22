/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehicleservicemanagementsystem;

import vehicleservicemanagementsystem.model.IVehicleServiceManagementModel;
import vehicleservicemanagementsystem.model.VehicleServiceManagementModel;
import vehicleservicemanagementsystem.presenter.VehicleServiceManagementPresenter;
import vehicleservicemanagementsystem.view.IVehicleServiceManagementView;
import vehicleservicemanagementsystem.view.VehicleServiceManagementView;

/**
 *
 * @author Alerz
 */
public class VehicleServiceManagementSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        IVehicleServiceManagementModel tpm = new VehicleServiceManagementModel();

        IVehicleServiceManagementView tpv = new VehicleServiceManagementView();

        VehicleServiceManagementPresenter tpp = new VehicleServiceManagementPresenter(tpm,tpv);

        tpv.bind(tpp);
    }
    
}
