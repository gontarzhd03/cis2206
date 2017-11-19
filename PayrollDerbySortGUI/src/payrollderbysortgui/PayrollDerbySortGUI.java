/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package payrollderbysortgui;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import payrolldatabaseoperations.*;

/**
 *
 * @author 55gontarhd03
 */
public class PayrollDerbySortGUI extends Application {
    TableView<Employee> payrollTable = new TableView<Employee>();
    @Override
    public void start(Stage primaryStage) {
        String dbDirectory = "./data/";
        String dbName = "PayrollMaster";
        String user = "Admin";
        String password = "MuCis";
        
        payrollTable.setEditable(true);
        payrollTable.setPrefWidth(800);
        ScrollPane sc1 = new ScrollPane();
        sc1.setContent(payrollTable);
        Scene scene = new Scene(sc1, 1000, 800);
        initialization(dbDirectory, dbName, user, password);
        moveFields();
        terminationRoutine();
        
        primaryStage.setTitle("Payroll Table");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    void initialization(String dbDirectory, String dbName, String user, String password) {
        PayrollDatabaseOperations.setConnection(dbDirectory, dbName, user, password);
    }
    void moveFields() {
        String [] employeeFields = findFieldname();
        ObservableList<Employee> observableEmployee = makeObservableList();
        payrollTable.setItems(observableEmployee);
        fillTable(employeeFields);
    }
    String [] findFieldname() {
        int numberOfEmployeeFields = Employee.class.getDeclaredFields().length;
        String [] employeeFields = new String [numberOfEmployeeFields];
        for(int i = 0; i < numberOfEmployeeFields; i++) {
            employeeFields[i] = Employee.class.getDeclaredFields()[i].getName();
        }
        return employeeFields;
    }
    ObservableList<Employee> makeObservableList() {
        Employees employees = PayrollDatabaseOperations.retrieveAll();
        ObservableList<Employee> observableEmployees = FXCollections.observableArrayList();
        for(int i = 0; i < employees.size(); i++) {
            observableEmployees.add(employees.get(i));
        }
        return observableEmployees;
    }
    void fillTable(String [] employeeFields) {
        ArrayList <String> columnNames = PayrollDatabaseOperations.getFieldNames();
        for(int i = 0; i < columnNames.size(); i++) {
            TableColumn tc = new TableColumn(columnNames.get(i));
            tc.setMinWidth(payrollTable.getPrefWidth() / columnNames.size());
            if(i != 1) {
                tc.setCellValueFactory(new PropertyValueFactory<Employee, Integer>(employeeFields[i]));
            }
            else {
                tc.setCellValueFactory(new PropertyValueFactory<Employee, String>(employeeFields[i]));
            }
            payrollTable.getColumns().add(tc);
        }
    }
    void terminationRoutine() {
        System.out.println("Program is complete.");
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
