/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package payrollderbysorteditgui;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.converter.IntegerStringConverter;
import payrolldatabaseoperations.*;

/**
 *
 * @authors hgontarz
 */
public class PayrollDerbySortEditGUI extends Application {

    TableView<Employee> payrollTable = new TableView<Employee>();

    @Override
    public void start(Stage primaryStage) {
        PayrollDatabaseOperations.setConnection("./data/", "PayrollMaster", "Admin", "MuCis");
        String employeeNumberRequest = "Enter Employee Number ";
        String Message = "Commit Record ";

        payrollTable.setEditable(true);
        payrollTable.getSelectionModel().setCellSelectionEnabled(true);
        payrollTable.setPrefWidth(800);
        BorderPane borderPane = new BorderPane();
        ScrollPane sc1 = new ScrollPane();
        sc1.setContent(payrollTable);
        sc1.setFitToWidth(true);
        sc1.setFitToHeight(true);
        borderPane.setCenter(sc1);

        Button insertButton = new Button("New Record");
        borderPane.setBottom(insertButton);

        ObservableList<Employee> observableEmployees = moveFields();

        modifyTable();

        addNewRecord(insertButton, observableEmployees, employeeNumberRequest, Message);
        Scene scene = new Scene(borderPane, 1060, 500);

        primaryStage.setTitle("Payroll Table");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    ObservableList<Employee> moveFields() {
        String[] employeeFields = findFieldname();
        ObservableList<Employee> observableEmployees = makeObservableList();
        payrollTable.setItems(observableEmployees);
        fillTable(employeeFields);
        return observableEmployees;
    }

    String[] findFieldname() {
        int numberOfEmployeeFields = Employee.class.getDeclaredFields().length;
        String[] employeeFields = new String[numberOfEmployeeFields];
        for (int i = 0; i < numberOfEmployeeFields; i++) {
            employeeFields[i] = Employee.class.getDeclaredFields()[i].getName();
        }
        return employeeFields;
    }

    ObservableList<Employee> makeObservableList() {
        Employees employees = PayrollDatabaseOperations.retrieveAll();
        ObservableList<Employee> observableEmployees = FXCollections.observableArrayList();
        for (int i = 0; i < employees.size(); i++) {
            observableEmployees.add(employees.get(i));
        }
        return observableEmployees;
    }

    void fillTable(String[] employeeFields) {
        ArrayList<String> columnNames = PayrollDatabaseOperations.getFieldNames();
        for (int i = 0; i < columnNames.size(); i++) {
            TableColumn tc = new TableColumn(columnNames.get(i));
            tc.setId(Integer.toString(i));
            tc.setMinWidth(payrollTable.getPrefWidth() / columnNames.size());
            if (i == 0) {
                tc.setCellValueFactory(new PropertyValueFactory<Employee, Integer>(employeeFields[i]));
            }
            else if (i != 1) {
                tc.setCellValueFactory(new PropertyValueFactory<Employee, Integer>(employeeFields[i]));
                tc.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
            } else {
                tc.setCellValueFactory(new PropertyValueFactory<Employee, String>(employeeFields[i]));
                tc.setCellFactory(TextFieldTableCell.forTableColumn());
            }
            payrollTable.getColumns().add(tc);
        }
    }

    void modifyTable() {
        for (int i = 0; i < payrollTable.getColumns().size(); i++) {
            TableColumn tc = payrollTable.getColumns().get(i);
            if (i != 1) {
                modifyIntegerFieldListener(tc);
            } else {
                modifyStringFieldListener(tc);
            }
        }
    }

    void modifyStringFieldListener(TableColumn tc) {
        tc.setOnEditCommit(new EventHandler<CellEditEvent<Employee, String>>() {
            @Override
            public void handle(CellEditEvent<Employee, String> t) {
                Employee employee = ((Employee) t.getTableView().getItems().get(t.getTablePosition().getRow()));
                employee.setEmployeeName(t.getNewValue());
                PayrollDatabaseOperations.modifyName(t.getNewValue(), employee.getEmployeeNumber());
            }
        });
    }

    void modifyIntegerFieldListener(TableColumn tc) {
        tc.setOnEditCommit(new EventHandler<CellEditEvent<Employee, Integer>>() {
            @Override
            public void handle(CellEditEvent<Employee, Integer> t) {
                Employee employee = ((Employee) t.getTableView().getItems().get(t.getTablePosition().getRow()));
                switch (t.getTablePosition().getColumn()) {
                    case 2:
                        employee.setTerritoryNumber(t.getNewValue());
                        PayrollDatabaseOperations.modifyTerritoryNumber(t.getNewValue(), employee.getEmployeeNumber());
                        break;
                    case 3:
                        employee.setAnnualSalary(t.getNewValue());
                        PayrollDatabaseOperations.modifyAnnualSalary(t.getNewValue(), employee.getEmployeeNumber());
                        break;
                }
            }
        });
    }

    void addNewRecord(Button insertButton, ObservableList<Employee> observableEmployees, String employeeNumberRequest, String Message) {
        insertButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                InputBox employeeNumberDialog = new InputBox(employeeNumberRequest);
                employeeNumberDialog.setOnHiding(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent e) {
                        if (employeeNumberDialog.getInputValue() != null) {
                            Employee employee = new Employee();
                            observableEmployees.add(employee);
                            int employeeNumberIn = Integer.parseInt(employeeNumberDialog.getInputValue());
                            employee.setEmployeeNumber(employeeNumberIn);
                            payrollTable.scrollTo(observableEmployees.size());
                            payrollTable.refresh();
                            MessageBox mb = new MessageBox(Message);
                            mb.setOnHiding(new EventHandler<WindowEvent>() {
                                @Override
                                public void handle(WindowEvent e) {
                                    if (mb.getButtonChoice()) {
                                        PayrollDatabaseOperations.insert(employee);
                                    }
                                    TableColumn tc1 = payrollTable.getColumns().get(1);
                                    payrollTable.edit(observableEmployees.size() - 1, tc1);
                                }
                            });
                        }
                    }
                });
            }
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
