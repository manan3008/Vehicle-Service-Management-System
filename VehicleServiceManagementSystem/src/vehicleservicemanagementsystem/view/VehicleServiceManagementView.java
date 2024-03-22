/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehicleservicemanagementsystem.view;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import vehicleservicemanagementsystem.model.Customer;
import vehicleservicemanagementsystem.model.Service;
import vehicleservicemanagementsystem.model.Vehicle;
import vehicleservicemanagementsystem.presenter.VehicleServiceManagementPresenter;

/**
 *
 * @author Alerz
 */
public class VehicleServiceManagementView extends javax.swing.JFrame implements IVehicleServiceManagementView{
    // the presenter for this view
    private VehicleServiceManagementPresenter presenter;
    
    // GUI STUFF
    // Labels
    private JLabel titleLabel;
    
    // customer
    private JLabel customerIDLabel;
    private JLabel firstNameLabel;
    private JLabel phoneLabel;
    private JLabel lastNameLabel;
    private JLabel addressLabel;
    
    // Vehicle
    private JLabel vehicleRegoNumberLabel;
    private JLabel brandLabel;
    private JLabel modelLabel;
    private JLabel manufacturedYearLabel;
    private JLabel numberOfKMLabel;
    private JLabel vehicleCustomerIDLabel;
    
    // Service
    private JLabel serviceIDLabel;
    private JLabel dateLabel;
    private JLabel priceLabel;
    private JLabel descriptionLabel;
    private JLabel serviceVehicleRegoNumberLabel;
    
    // Search
    private JLabel searchFirstNameLabel;
    private JLabel searchLastNameLabel;
    private JLabel searchPhoneLabel;
    private JLabel searchVehicleLabel;
    
    private JLabel deleteVehicleLabel;
    private JLabel deleteDateLabel;
    
    // navigation
    private JLabel ofLabel;
    
    // Panels
    private JPanel wrapperPanel;
    private JPanel customerPanel;
    private JPanel vehiclePanel;
    private JPanel servicePanel;
    private JPanel searchNamePanel;
    private JPanel searchPhonePanel;
    private JPanel searchVehiclePanel;
    private JPanel deleteServicePanel;
    
    // Sub Panels
    private JPanel topCustomerPanel;
    private JPanel topMidCustomerPanel;
    private JPanel botMidCustomerPanel;
    private JPanel botCustomerPanel;
    
    private JPanel topVehiclePanel;
    private JPanel topMidVehiclePanel;
    private JPanel botMidVehiclePanel;
    private JPanel botVehiclePanel;
    
    private JPanel topServicePanel;
    private JPanel topMidServicePanel;
    private JPanel midServicePanel;
    private JPanel botMidServicePanel;
    private JPanel botServicePanel;
    
    private JPanel topSearchNamePanel;
    private JPanel botSearchNamePanel;
    
    // TextFields
    private JTextField customerIDTextField;
    private JTextField firstNameTextField;
    private JTextField lastNameTextField;
    private JTextField phoneTextField;
    private JTextField addressTextField;
    
    private JTextField vehicleRegoNumberTextField;
    private JTextField brandTextField;
    private JTextField modelTextField;
    private JTextField manufacturedYearTextField;
    private JTextField numberOfKMTextField;
    private JTextField vehicleCustomerIDTextField;
    
    private JTextField serviceIDTextField;
    private JTextField descriptionTextField;
    private JTextField dateTextField;
    private JTextField priceTextField;
    private JTextField serviceVehicleRegoNumberTextField;
    // nav
    private JTextField indexTextField;
    private JTextField lengthTextField;
    
    private JTextField searchFirstNameTextField;
    private JTextField searchLastNameTextField;
    private JTextField searchPhoneTextField;
    private JTextField searchVehicleTextField;
    
    private JTextField deleteVehicleTextField;
    private JTextField deleteDateTextField;
    
    // Buttons
    private JButton addCustomerButton;
    private JButton addVehicleButton;
    private JButton previousButton;
    private JButton nextButton;
    private JButton addServiceButton;
    private JButton updateCustomerButton;
    private JButton searchNameButton;
    private JButton searchPhoneButton;
    private JButton searchVehicleButton;
    private JButton deleteButton;
    
    private JButton displayServices;
    private JButton getStats;
    private JButton exitButton;
    
    private JTextArea displayArea;
    
    public VehicleServiceManagementView() {
        super( "Vehicle Service Management System" ); 
        setLayout( new FlowLayout( FlowLayout.CENTER, 10, 10 ) );
        setSize( 1300, 900 );
        setResizable( false );
        
        
        // When window closes, exit program.
        addWindowListener( 
            new WindowAdapter() {  
                public void windowClosing( WindowEvent evt ) {
                   close();
                } // end method windowClosing
            } // end anonymous inner class
        ); // end call to addWindowListener
        
        // Title
        titleLabel = new JLabel();
        titleLabel.setText("Vehicle Service Management System");
        titleLabel.setFont(new Font("Calibri", Font.BOLD, 20));
        titleLabel.setForeground(Color.BLUE);
        add(titleLabel);
        
        // Wrapper Panel which holds all elements in it except for the title.
        wrapperPanel = new JPanel();
        wrapperPanel.setLayout(new FlowLayout( FlowLayout.CENTER, 10, 10));
        wrapperPanel.setPreferredSize(new Dimension(1275,460));
        wrapperPanel.setBorder( BorderFactory.createTitledBorder(
            "Data Entry, Browse & Query" ) );
        add(wrapperPanel);
        
        // Customer Data Panel
        customerPanel = new JPanel();
        customerPanel.setLayout(new GridLayout(4, 1));
        customerPanel.setPreferredSize(new Dimension(400,220));
        customerPanel.setBorder( BorderFactory.createTitledBorder(
            "Customer" ) );
        wrapperPanel.add(customerPanel);
        
        // Top row panel for customer
        topCustomerPanel = new JPanel();
        topCustomerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        customerPanel.add(topCustomerPanel);
        
        // Add data to top 
        customerIDLabel = new JLabel();
        customerIDLabel.setText("Customer ID");
        topCustomerPanel.add(customerIDLabel);
        
        customerIDTextField = new JTextField(5);
        topCustomerPanel.add(customerIDTextField);
        
        // Spacer Box
        topCustomerPanel.add( Box.createHorizontalStrut( 5 ) );
        
        phoneLabel = new JLabel();
        phoneLabel.setText("Phone");
        topCustomerPanel.add(phoneLabel);
        
        phoneTextField = new JTextField(10);
        topCustomerPanel.add(phoneTextField);
        
        
        // Top middle row panel for customer
        topMidCustomerPanel = new JPanel();
        topMidCustomerPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        customerPanel.add(topMidCustomerPanel);
        
        // Add data to top mid
        firstNameLabel = new JLabel();
        firstNameLabel.setText("First Name");
        topMidCustomerPanel.add(firstNameLabel);
        
        firstNameTextField = new JTextField(9);
        topMidCustomerPanel.add(firstNameTextField);
        
        // Spacer Box
        topMidCustomerPanel.add( Box.createHorizontalStrut( 4 ) );
        
        lastNameLabel = new JLabel();
        lastNameLabel.setText("Last Name");
        topMidCustomerPanel.add(lastNameLabel);
        
        lastNameTextField = new JTextField(9);
        topMidCustomerPanel.add(lastNameTextField);
        
        // bot Mid row panel for customer
        botMidCustomerPanel = new JPanel();
        botMidCustomerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        customerPanel.add(botMidCustomerPanel);
        
        // add data
        addressLabel = new JLabel();
        addressLabel.setText("Address");
        botMidCustomerPanel.add(addressLabel);
        
        addressTextField = new JTextField(25);
        botMidCustomerPanel.add(addressTextField);
        
        botCustomerPanel = new JPanel();
        botCustomerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        customerPanel.add(botCustomerPanel);
        
        
        addCustomerButton = new JButton();
        addCustomerButton.setText("    Add    ");
        addCustomerButton.addActionListener((ActionEvent evt) -> {
            insertNewCustomer();
        }); 
        botCustomerPanel.add(addCustomerButton);
        
        // Spacer Box
        botCustomerPanel.add( Box.createHorizontalStrut( 10 ) );
        
        updateCustomerButton = new JButton();
        updateCustomerButton.setText("Update Customer");
        updateCustomerButton.addActionListener((ActionEvent evt) -> {
            updateCustomer();
        }); 
        botCustomerPanel.add(updateCustomerButton);
        
        // Vehicle panel
        vehiclePanel = new JPanel();
        vehiclePanel.setLayout(new GridLayout(4, 1));
        vehiclePanel.setPreferredSize(new Dimension(400,220));
        vehiclePanel.setBorder( BorderFactory.createTitledBorder(
            "Vehicle" ) );
        wrapperPanel.add(vehiclePanel);
        
        // top vehicle sub panel
        topVehiclePanel = new JPanel();
        topVehiclePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        vehiclePanel.add(topVehiclePanel);
        
        // add data
        vehicleRegoNumberLabel = new JLabel();
        vehicleRegoNumberLabel.setText("Vehicle Rego Number");
        topVehiclePanel.add(vehicleRegoNumberLabel);
        
        vehicleRegoNumberTextField = new JTextField(5);
        topVehiclePanel.add(vehicleRegoNumberTextField);
        
        // Spacer Box
        topVehiclePanel.add( Box.createHorizontalStrut( 4 ) );
        
        brandLabel = new JLabel();
        brandLabel.setText("Brand");
        topVehiclePanel.add(brandLabel);
        
        brandTextField = new JTextField(8);
        topVehiclePanel.add(brandTextField);
        
        // top mid vehicle sub panel
        topMidVehiclePanel = new JPanel();
        topMidVehiclePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        vehiclePanel.add(topMidVehiclePanel);
        
        // add data
        modelLabel = new JLabel();
        modelLabel.setText("Model");
        topMidVehiclePanel.add(modelLabel);
        
        modelTextField = new JTextField(10);
        topMidVehiclePanel.add(modelTextField);
        
        // Spacer Box
        topMidVehiclePanel.add( Box.createHorizontalStrut( 4 ) );
        
        numberOfKMLabel = new JLabel();
        numberOfKMLabel.setText("Number of KM");
        topMidVehiclePanel.add(numberOfKMLabel);
        
        numberOfKMTextField = new JTextField(7);
        topMidVehiclePanel.add(numberOfKMTextField);
        
        // bottom mid vehicle sub panel
        botMidVehiclePanel =  new JPanel();
        botMidVehiclePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        vehiclePanel.add(botMidVehiclePanel);
        
        // add data
        manufacturedYearLabel = new JLabel();
        manufacturedYearLabel.setText("Manufactured Year");
        botMidVehiclePanel.add(manufacturedYearLabel);
        
        manufacturedYearTextField = new JTextField(7);
        botMidVehiclePanel.add(manufacturedYearTextField);
        
        // Spacer Box
        botMidVehiclePanel.add( Box.createHorizontalStrut( 4 ) );
        
        vehicleCustomerIDLabel = new JLabel();
        vehicleCustomerIDLabel.setText("Customer ID");
        botMidVehiclePanel.add(vehicleCustomerIDLabel);
        
        vehicleCustomerIDTextField = new JTextField(5);
        botMidVehiclePanel.add(vehicleCustomerIDTextField);
        
        // bottom sub panel
        botVehiclePanel = new JPanel();
        botVehiclePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        vehiclePanel.add(botVehiclePanel);
        
        addVehicleButton = new JButton();
        addVehicleButton.setText("Add Vehicle to Customer");
        addVehicleButton.addActionListener((ActionEvent evt) -> {
            insertNewVehicle();
        }); 
        botVehiclePanel.add(addVehicleButton);
        
        // Service panel
        servicePanel = new JPanel();
        servicePanel.setLayout(new GridLayout(5, 1));
        servicePanel.setPreferredSize(new Dimension(400,220));
        servicePanel.setBorder( BorderFactory.createTitledBorder(
            "Service" ) );
        wrapperPanel.add(servicePanel);
        
        // top sub panel
        topServicePanel = new JPanel();
        topServicePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        servicePanel.add(topServicePanel);
        
        // add data
        serviceIDLabel = new JLabel();
        serviceIDLabel.setText("Service ID");
        topServicePanel.add(serviceIDLabel);
        
        serviceIDTextField = new JTextField(9);
        topServicePanel.add(serviceIDTextField);
        
        // Spacer Box
        topServicePanel.add( Box.createHorizontalStrut( 4 ) );
        
        dateLabel = new JLabel();
        dateLabel.setText("Date");
        topServicePanel.add(dateLabel);
        
        dateTextField = new JTextField(9);
        topServicePanel.add(dateTextField);
        
        // top mid sub panel
        topMidServicePanel = new JPanel();
        topMidServicePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        servicePanel.add(topMidServicePanel);
        
        // add data
        descriptionLabel = new JLabel();
        descriptionLabel.setText("Description");
        topMidServicePanel.add(descriptionLabel);
        
        descriptionTextField = new JTextField(24);
        topMidServicePanel.add(descriptionTextField);
        
        
        // mid sub panel
        midServicePanel = new JPanel();
        midServicePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        servicePanel.add(midServicePanel);
        
        // add data
        priceLabel = new JLabel();
        priceLabel.setText("Price");
        midServicePanel.add(priceLabel);
        
        priceTextField = new JTextField(6);
        midServicePanel.add(priceTextField);
        
        // Spacer Box
        midServicePanel.add( Box.createHorizontalStrut( 4 ) );
        
        serviceVehicleRegoNumberLabel = new JLabel();
        serviceVehicleRegoNumberLabel.setText("Vehicle Rego Number");
        midServicePanel.add(serviceVehicleRegoNumberLabel);
        
        serviceVehicleRegoNumberTextField = new JTextField(5);
        midServicePanel.add(serviceVehicleRegoNumberTextField);
        
        // bot sub panel
        botMidServicePanel = new JPanel();
        botMidServicePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        servicePanel.add(botMidServicePanel);
        
        previousButton = new JButton();
        previousButton.setText("<< Previous");
        previousButton.addActionListener((ActionEvent evt) -> {
            presenter.showPreviousService();
        }); 
        botMidServicePanel.add(previousButton);
        
        // Spacer
        botMidServicePanel.add( Box.createHorizontalStrut( 10 ) );
        
        indexTextField = new JTextField(2);
        indexTextField.setPreferredSize(new Dimension(25,25));
        indexTextField.setMaximumSize(indexTextField.getPreferredSize());
        indexTextField.setEditable(false);
        botMidServicePanel.add(indexTextField);
        
        ofLabel = new JLabel();
        ofLabel.setText("of");
        botMidServicePanel.add(ofLabel);
        
        lengthTextField = new JTextField(2);
        lengthTextField.setPreferredSize(new Dimension(25,25));
        lengthTextField.setMaximumSize(lengthTextField.getPreferredSize());
        lengthTextField.setEditable(false);
        botMidServicePanel.add(lengthTextField);
        
        // Spacer Box
        botMidServicePanel.add( Box.createHorizontalStrut( 10 ) );
        
        nextButton = new JButton();
        nextButton.setText("Next >>");
        nextButton.addActionListener((ActionEvent evt) -> {
            presenter.showNextService();
        }); 
        botMidServicePanel.add(nextButton);
        
        // bot sub panel
        botServicePanel = new JPanel();
        botServicePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        servicePanel.add(botServicePanel); 
        
        addServiceButton = new JButton();
        addServiceButton.setText("Add Service");
        addServiceButton.addActionListener((ActionEvent evt) -> {
            insertNewService();
        }); 
        botServicePanel.add(addServiceButton);
        
        // Search Name Panel
        searchNamePanel = new JPanel();
        searchNamePanel.setLayout(new GridLayout(2, 1));
        searchNamePanel.setPreferredSize(new Dimension(295,150));
        searchNamePanel.setBorder( BorderFactory.createTitledBorder(
            "Search via Name" ) );
        wrapperPanel.add(searchNamePanel);
        
        // top sub panel
        topSearchNamePanel = new JPanel();
        topSearchNamePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        searchNamePanel.add(topSearchNamePanel);
        
        // Add data
        topSearchNamePanel.add( Box.createHorizontalStrut( 20 ) );
        searchFirstNameLabel = new JLabel();
        searchFirstNameLabel.setText("First Name:");
        topSearchNamePanel.add(searchFirstNameLabel);
        
        searchFirstNameTextField = new JTextField(8);
        topSearchNamePanel.add(searchFirstNameTextField);
        
        // Spacer Box
        topSearchNamePanel.add( Box.createHorizontalStrut( 20 ) );
        
        searchLastNameLabel = new JLabel();
        searchLastNameLabel.setText("Last Name:");
        topSearchNamePanel.add(searchLastNameLabel);
        
        searchLastNameTextField = new JTextField(8);
        topSearchNamePanel.add(searchLastNameTextField);
        
        // bot sub panel
        botSearchNamePanel = new JPanel();
        botSearchNamePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        searchNamePanel.add(botSearchNamePanel);
        
        // add data
        searchNameButton = new JButton();
        searchNameButton.setText("Search Name");
        searchNameButton.addActionListener((ActionEvent evt) -> {
            searchByName();
        }); 
        botSearchNamePanel.add(searchNameButton);
        
        // Search Phone Panel
        searchPhonePanel = new JPanel();
        searchPhonePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        searchPhonePanel.setPreferredSize(new Dimension(295,150));
        searchPhonePanel.setBorder( BorderFactory.createTitledBorder(
            "Search via Phone" ) );
        wrapperPanel.add(searchPhonePanel);
        
        // Spacer Box
        searchPhonePanel.add( Box.createHorizontalStrut( 295 ) );
        searchPhonePanel.add( Box.createHorizontalStrut( 295 ) );
        
        searchPhoneLabel = new JLabel();
        searchPhoneLabel.setText("Phone:");
        searchPhonePanel.add(searchPhoneLabel);
        
        searchPhoneTextField = new JTextField(8);
        searchPhonePanel.add(searchPhoneTextField);
        
        searchPhonePanel.add( Box.createHorizontalStrut( 295 ) );
        
        // add data
        searchPhoneButton = new JButton();
        searchPhoneButton.setText("Search Phone");
        searchPhoneButton.addActionListener((ActionEvent evt) -> {
            searchByPhone();
        }); 
        searchPhonePanel.add(searchPhoneButton);
        
        // Search Vehicle Panel
        searchVehiclePanel = new JPanel();
        searchVehiclePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        searchVehiclePanel.setPreferredSize(new Dimension(295,150));
        searchVehiclePanel.setBorder( BorderFactory.createTitledBorder(
            "Search via Vehicle Rego Number" ) );
        wrapperPanel.add(searchVehiclePanel);
        
        // Spacer Box
        searchVehiclePanel.add( Box.createHorizontalStrut( 295 ) );
        searchVehiclePanel.add( Box.createHorizontalStrut( 295 ) );
        
        searchVehicleLabel = new JLabel();
        searchVehicleLabel.setText("Vehicle Rego Number:");
        searchVehiclePanel.add(searchVehicleLabel);
        
        searchVehicleTextField = new JTextField(8);
        searchVehiclePanel.add(searchVehicleTextField);
        
        searchVehiclePanel.add( Box.createHorizontalStrut( 295 ) );
        
        
        // add data
        searchVehicleButton = new JButton();
        searchVehicleButton.setText("Search Vehicle");
        searchVehicleButton.addActionListener((ActionEvent evt) -> {
            searchByVehicle();
        }); 
        searchVehiclePanel.add(searchVehicleButton);
        
        deleteServicePanel = new JPanel();
        deleteServicePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        deleteServicePanel.setPreferredSize(new Dimension(295,150));
        deleteServicePanel.setBorder( BorderFactory.createTitledBorder(
            "Delete Service" ) );
        wrapperPanel.add(deleteServicePanel);
        
        deleteServicePanel.add(Box.createHorizontalStrut( 10 ));
        deleteVehicleLabel = new JLabel();
        deleteVehicleLabel.setText("Vehicle Rego Number:");
        deleteServicePanel.add(deleteVehicleLabel);
        
        deleteVehicleTextField = new JTextField(8);
        deleteServicePanel.add(deleteVehicleTextField);
        deleteServicePanel.add(Box.createHorizontalStrut( 10 ));
        deleteServicePanel.add(Box.createHorizontalStrut( 40 ));
        deleteDateLabel = new JLabel();
        deleteDateLabel.setText("Date:");
        deleteServicePanel.add(deleteDateLabel);
        
        deleteDateTextField = new JTextField(8);
        deleteServicePanel.add(deleteDateTextField);
        
        deleteServicePanel.add(Box.createHorizontalStrut( 40 ));
        
        deleteButton = new JButton();
        deleteButton.setText("Delete Service");
        deleteButton.addActionListener((ActionEvent evt) -> {
            deleteService();
        }); 
        deleteServicePanel.add(deleteButton);
        
        displayServices = new JButton();
        displayServices.setText("Display All Services");
        displayServices.addActionListener((ActionEvent evt) -> {
            presenter.displayAllServices();
        }); 
        wrapperPanel.add(displayServices);
        
        
        getStats = new JButton();
        getStats.setText("Statistics");
        getStats.addActionListener((ActionEvent evt) -> {
            presenter.showStatistics();
        }); 
        wrapperPanel.add(getStats);
        
        exitButton = new JButton();
        
        
        displayArea = new JTextArea();
        displayArea.setPreferredSize(new Dimension(1265,330));
        displayArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        displayArea.setEditable(false);
        Font textAreaFont = new Font("Courier New", Font.PLAIN, 12);
        displayArea.setFont(textAreaFont);
        add(displayArea);
        
        setBrowsing(false);
        setVisible( true );
    }
    
    @Override
    public void bind(VehicleServiceManagementPresenter p) {
        presenter = p;
    }

    @Override
    public void setBrowsing(Boolean f) {
        nextButton.setEnabled(f);
        previousButton.setEnabled( f );
    }

    @Override
    public void displayMessage(String m) {
        JOptionPane.showMessageDialog( this, m );
    }

    @Override
    public void displayCustomer(Customer c) {
        customerIDTextField.setText(c.getId() + "");
        firstNameTextField.setText(c.getFirstName());
        phoneTextField.setText(c.getPhone());
        lastNameTextField.setText(c.getLastName());
        addressTextField.setText(c.getAddress());
    }

    @Override
    public void displayMaxAndCurrent(int m, int c) {
        lengthTextField.setText(""+m);
        indexTextField.setText(""+(c+1));
    }

    @Override
    public void displayTextArea(String s) {
        displayArea.setText(s);
    }
    
    // handles exit button and window close
    private void close() {
        presenter.close();
        System.exit( 0 );
    }

    @Override
    public void displayVehicle(Vehicle v) {
        vehicleRegoNumberTextField.setText(v.getVehicleRegoNumber());
        brandTextField.setText(v.getBrand());
        modelTextField.setText(v.getModel());
        manufacturedYearTextField.setText(v.getManufacturedYear() + "");
        numberOfKMTextField.setText(v.getNumberOfKM() + "");
        vehicleCustomerIDTextField.setText(v.getCustomerID() + "");
    }

    @Override
    public void displayService(Service s) {
        serviceIDTextField.setText(s.getServiceID() + "");
        descriptionTextField.setText(s.getDescription());
        dateTextField.setText(s.getDate().toString());
        priceTextField.setText(s.getPrice() + "");
        serviceVehicleRegoNumberTextField.setText(s.getVehicleRegoNumber());
    }

    @Override
    public void displayStatistics(List<String> s) {
        displayArea.setText("");
        for (int i = 0; i < s.size(); i++) {
            displayArea.append(s.get(i));
        }
    }
    private void insertNewCustomer() {
        presenter.insertCustomer(firstNameTextField.getText(), lastNameTextField.getText(), addressTextField.getText(), phoneTextField.getText());
        presenter.displayAllServices();
    }

    private void insertNewVehicle() {
        presenter.insertVehicle(vehicleRegoNumberTextField.getText(), brandTextField.getText(), modelTextField.getText(), Integer.parseInt(manufacturedYearTextField.getText()), Integer.parseInt(numberOfKMTextField.getText()), Integer.parseInt(vehicleCustomerIDTextField.getText()));
        
        presenter.displayAllServices();
    }

    private void insertNewService() {
        presenter.insertService(descriptionTextField.getText(), dateTextField.getText(), Double.parseDouble(priceTextField.getText()), serviceVehicleRegoNumberTextField.getText());
        presenter.displayAllServices();
    }

    private void updateCustomer() {
        presenter.updateEntry(Integer.parseInt(customerIDTextField.getText()), addressTextField.getText(), phoneTextField.getText());
        presenter.displayAllServices();
    }

    private void searchByName() {
        presenter.performQueryByName(searchFirstNameTextField.getText(), searchLastNameTextField.getText());
    }

    private void searchByPhone() {
        presenter.performQueryByPhone(searchPhoneTextField.getText());
    }

    private void searchByVehicle() {
        presenter.performQueryByVehicle(searchVehicleTextField.getText());
    }

    private void deleteService() {
        presenter.deleteService(deleteVehicleTextField.getText(), deleteDateTextField.getText());
    }
}
