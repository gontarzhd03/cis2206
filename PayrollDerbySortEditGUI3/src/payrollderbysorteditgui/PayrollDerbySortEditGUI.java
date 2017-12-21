/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package payrollderbysorteditgui;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
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
        String dbDirectory = "./data/";
        String databaseName = "PayrollMaster";
        String user = "Admin";
        String password = "MuCis";

        int[] methodNumbers = {12, 10, 14, 8};
        int[] dbMethodNumbers = {1, 4, 5, 3};
        String integerChoiceString = "java.lang.Integer";
        String employeeNumberRequest = "Enter Employee Number ";
        String Message = "Commit Record ";

        payrollTable.setEditable(true);
        payrollTable.getSelectionModel().setCellSelectionEnabled(true);
        payrollTable.setPrefWidth(800);

        BorderPane borderPane = new BorderPane();

        ScrollPane sc1 = new ScrollPane();
        sc1.setFitToWidth(true);
        sc1.setFitToHeight(true);
        sc1.setContent(payrollTable);

        borderPane.setCenter(sc1);

        Button insertButton = new Button("New Record");
        borderPane.setBottom(insertButton);

        PayrollDatabaseOperations.setConnection(dbDirectory, databaseName, user, password);
        ObservableList<Employee> observableEmployees = moveFields();
        modifyTable(methodNumbers, dbMethodNumbers, integerChoiceString);
        addNewRecord(insertButton, observableEmployees, employeeNumberRequest, Message);
        Scene scene = new Scene(borderPane, 1000, 500);

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
            } else if (i != 1) {
                tc.setCellValueFactory(new PropertyValueFactory<Employee, Integer>(employeeFields[i]));
                tc.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
            } else {
                tc.setCellValueFactory(new PropertyValueFactory<Employee, String>(employeeFields[i]));
                tc.setCellFactory(TextFieldTableCell.forTableColumn());
            }
            payrollTable.getColumns().add(tc);
        }
    }

    void modifyTable(int[] methodNumbers, int[] dbMethodNumbers, String integerChoiceString) {
        ArrayList<String> columnNames = PayrollDatabaseOperations.getFieldNames();
        Method[] methods = Employee.class.getDeclaredMethods();
        Method[] dbMethods = PayrollDatabaseOperations.class.getDeclaredMethods();
        Arrays.sort(methods, new MethodComparator());
        Arrays.sort(dbMethods, new MethodComparator());
        TableColumn tc0 = payrollTable.getColumns().get(0);
        System.out.println();
        for (int i = 1; i < columnNames.size(); i++) {
            TableColumn tc = payrollTable.getColumns().get(i);
            if (tc.getCellData(0).getClass().getName() == integerChoiceString) {
                modifyIntegerFieldListener(tc0, tc, methods[methodNumbers[i]], dbMethods[dbMethodNumbers[i]]);
            } else {
                modifyStringFieldListener(tc0, tc, methods[methodNumbers[i]]);
            }
        }
    }

    void modifyIntegerFieldListener(TableColumn tce, TableColumn tc, Method method, Method dbMethod) {
        tc.setOnEditCommit(new EventHandler<CellEditEvent<Employee, Integer>>() {
            @Override
            public void handle(CellEditEvent<Employee, Integer> t) {
                int rowNumber = t.getTablePosition().getRow();
                Employee employee = ((Employee) t.getTableView().getItems().get(rowNumber));

                try {
                    int editValue = t.getNewValue();
                    method.invoke(employee, editValue);
                    dbMethod.invoke(null, editValue, employee.getEmployeeNumber());
                } catch (IllegalAccessException ex) {
                    System.out.println(ex.toString());
                } catch (InvocationTargetException ex) {
                    System.out.println(ex.toString());
                }
            }
        });
    }

    void modifyStringFieldListener(TableColumn tce, TableColumn tc, Method method) {
        tc.setOnEditCommit(new EventHandler<CellEditEvent<Employee, String>>() {
            @Override
            public void handle(CellEditEvent<Employee, String> t) {
                int rowNumber = t.getTablePosition().getRow();
                Employee employee = ((Employee) t.getTableView().getItems().get(rowNumber));
                try {
                    String editValue = t.getNewValue();
                    method.invoke(employee, editValue);
                    PayrollDatabaseOperations.modifyName(editValue, employee.getEmployeeNumber());
                } catch (IllegalAccessException ex) {
                    System.out.println(ex.toString());
                } catch (InvocationTargetException ex) {
                    System.out.println(ex.toString());
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
