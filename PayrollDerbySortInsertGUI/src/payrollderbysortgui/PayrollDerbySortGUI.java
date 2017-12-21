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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import payrolldatabaseoperations.*;

/**
 *
 * @author 55gontarhd03
 */
public class PayrollDerbySortGUI extends Application {
    TableView<Employee> payrollTable = new TableView<Employee>();
    HBox insertRow = new HBox();
    
    @Override
    public void start(Stage primaryStage) {
        String dbDirectory = "./data/";
        String dbName = "PayrollMaster";
        String user = "Admin";
        String password = "MuCis";
        
        ScrollPane sc1 = new ScrollPane();
        payrollTable.setEditable(true);
        payrollTable.setPrefWidth(800);
        BorderPane borderPane = new BorderPane();
        sc1.setContent(payrollTable);
        sc1.setFitToWidth(true);
        sc1.setFitToHeight(true);
        borderPane.setCenter(sc1);

        initialization(dbDirectory, dbName, user, password);
        
        ObservableList<Employee> observableEmployees = moveFields();
        createInsertRow(observableEmployees);
        primaryStage.setOnHiding(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                terminationRoutine();
            }
        });
        borderPane.setBottom(insertRow);
        Scene scene = new Scene(borderPane, 1000, 500);
        primaryStage.setTitle("Payroll Table");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    void initialization(String dbDirectory, String dbName, String user, String password) {
        PayrollDatabaseOperations.setConnection(dbDirectory, dbName, user, password);
    }
    ObservableList<Employee> moveFields() {
        String [] employeeFields = findFieldname();
        ObservableList<Employee> observableEmployees = makeObservableList();
        payrollTable.setItems(observableEmployees);
        fillTable(employeeFields);
        return observableEmployees;
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
    void createInsertRow(ObservableList<Employee> observableEmployees) {
        TextField insertEmployeeNumber = new TextField();
        TextField insertName = new TextField();
        TextField insertTerritoryNumber = new TextField();
        TextField insertAnnualSalary = new TextField();
        Button insertButton = new Button("Insert Record");
        
        insertEmployeeNumber.setPromptText("Employee Number");
        insertName.setPromptText("Name");
        insertTerritoryNumber.setPromptText("Territory Number");
        insertAnnualSalary.setPromptText("AnnualSalary");
        
        insertRow.getChildren().addAll(insertEmployeeNumber, insertName, insertTerritoryNumber, insertAnnualSalary, insertButton);
        insertButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                addNewRecord(observableEmployees);
            }
        });
    }
    void addNewRecord(ObservableList<Employee> observableEmployees) {
        Employee employee = new Employee();
        employee.setEmployeeNumber(Integer.parseInt(((TextField)(insertRow.getChildren().get(0))).getText()));
        employee.setEmployeeName(((TextField)(insertRow.getChildren().get(1))).getText());
        employee.setTerritoryNumber(Integer.parseInt(((TextField)(insertRow.getChildren().get(2))).getText()));
        employee.setAnnualSalary(Integer.parseInt(((TextField)(insertRow.getChildren().get(3))).getText()));
        observableEmployees.add(employee);
        for(int i = 0; i < insertRow.getChildren().size() - 1; i++) {
            ((TextField)insertRow.getChildren().get(i)).clear();
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
