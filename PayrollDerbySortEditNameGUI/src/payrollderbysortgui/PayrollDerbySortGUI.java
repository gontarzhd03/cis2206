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
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
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
        payrollTable.getSelectionModel().setCellSelectionEnabled(true);
        payrollTable.setPrefWidth(800);
        BorderPane borderPane = new BorderPane();
        ScrollPane sc1 = new ScrollPane();
        sc1.setContent(payrollTable);
        sc1.setFitToWidth(true);
        sc1.setFitToHeight(true);
        borderPane.setCenter(sc1);
        
        initialization(dbDirectory, dbName, user, password);
        ObservableList<Employee> observableEmployees = moveFields();
        modifyTable();
        primaryStage.setOnHiding(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                terminationRoutine();
            }
        });
        Scene scene = new Scene(borderPane, 1000, 800);
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
            tc.setId(Integer.toString(i));
            tc.setMinWidth(payrollTable.getPrefWidth() / columnNames.size());
            if(i != 1) {
                tc.setCellValueFactory(new PropertyValueFactory<Employee, Integer>(employeeFields[i]));
            }
            else {
                tc.setCellValueFactory(new PropertyValueFactory<Employee, String>(employeeFields[i]));
                tc.setCellFactory(TextFieldTableCell.forTableColumn());
            }
            payrollTable.getColumns().add(tc);
        }
    }
    void modifyTable() {
        TableColumn tc = payrollTable.getColumns().get(1);
        modifyStringFieldListener(tc);
    }
    void modifyStringFieldListener(TableColumn tc) {
        tc.setOnEditCommit(new EventHandler<CellEditEvent<Employee, String>>() {
            @Override
            public void handle(CellEditEvent<Employee, String> t) {
                Employee employee = ((Employee) t.getTableView().getItems().get(t.getTablePosition().getRow()));
                employee.setEmployeeName(t.getNewValue());
            }
        });
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
