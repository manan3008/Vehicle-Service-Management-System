# Vehicle Service Management System (VSMS) - MVC Pattern Application

## Project Overview

The Vehicle Service Management System (VSMS) is a Java desktop application designed for managing vehicle service records for a car service firm. The application caters to the administrative staff or managers of the service firm, enabling them to book services as per customers' requests. The system encompasses a front-end graphical user interface (GUI) and a back-end database system, facilitating operations like customer and vehicle registration, service booking, and reporting.

## Objectives

- **Software Design and Development**: Apply principles and document the software lifecycle from requirements to testing.
- **Layered Information System Design**: Illustrate and implement a system with presentation, application, and data layers.
- **Quality Management**: Employ quality management techniques in design, development, and testing scenarios.

## Features

- **Customer Management**: Search, add, and update customer details.
- **Vehicle Management**: Add vehicles for new or existing customers.
- **Service Booking**: Record service details and manage bookings, including cancellation.
- **Service Management**: Search and display service bookings, sorted by price.
- **Reporting**: Generate reports on service pricing statistics and vehicle services by brand.

## System Requirements

- **Functional Requirements**: Manage customer, vehicle, and service information; support for booking and managing services.
- **Non-Functional Requirements**: User-friendly GUI, data persistence in a MySQL database, error handling, and reporting capabilities.

## Architectural Design

- The system follows the MVC (Model-View-Controller) design pattern, separating the application into three interconnected components to separate internal representations of information from the ways information is presented to and accepted from the user.

## Implementation

- **Technology Stack**: Java SE, MySQL Server, JDBC for database connectivity.
- **Database**: `CarServiceDB` with tables `Customers`, `Vehicles`, `Services`. The database schema includes ER diagrams and SQL scripts for table creation and data insertion.

## Testing

- A detailed test plan with cases for correct and incorrect inputs. Tests cover functionalities such as adding new customers, registering vehicles, booking services, and generating reports.

## Installation and Running Instructions

1. **Database Setup**: Create `CarServiceDB` on MySQL Server and execute provided SQL scripts to create tables and insert sample data.
2. **Project Setup**: Open the project in an IDE supporting Java (preferably NetBeans) and configure the database connection settings.
3. **Execution**: Run the project from the IDE to launch the VSMS application.

## Usage

- **Search Customers**: By name or phone number.
- **Customer and Vehicle Registration**: Enter customer information and vehicle details into the system.
- **Service Booking**: Record details of each service booking.
- **View and Cancel Bookings**: View all bookings and cancel specific ones.
- **Generate Reports**: For service pricing statistics and number of vehicles serviced by brand.

