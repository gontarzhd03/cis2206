/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PayrollDatabaseOperations;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author hgontarz
 */
public class Employee {
    private SimpleIntegerProperty employeeNumberProperty = new SimpleIntegerProperty();
    private SimpleStringProperty employeeNameProperty = new SimpleStringProperty();
    private SimpleIntegerProperty territoryNumberProperty = new SimpleIntegerProperty();
    private SimpleIntegerProperty annualSalaryProperty = new SimpleIntegerProperty();
    private int employeeNumber;
    private String employeeName;
    private int territoryNumber;
    private int annualSalary;

    public int getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(int employeeNumber) {
        this.employeeNumber = employeeNumber;
        this.employeeNumberProperty.set(employeeNumber);
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
        this.employeeNameProperty.set(employeeName);
    }

    public int getTerritoryNumber() {
        return territoryNumber;
    }

    public void setTerritoryNumber(int territoryNumber) {
        this.territoryNumber = territoryNumber;
        this.territoryNumberProperty.set(territoryNumber);
    }

    public int getAnnualSalary() {
        return annualSalary;
    }

    public void setAnnualSalary(int annualSalary) {
        this.annualSalary = annualSalary;
        this.annualSalaryProperty.set(annualSalary);
    }

    public int getEmployeeNumberProperty() {
        return employeeNumberProperty.get();
    }

    public void setEmployeeNumberProperty(int employeeNumber) {
        this.employeeNumberProperty.set(employeeNumber);
        this.employeeNumber = employeeNumber;
    }

    public String getEmployeeNameProperty() {
        return employeeNameProperty.get();
    }

    public void setEmployeeNameProperty(String employeeNameString) {
        this.employeeNameProperty.set(employeeNameString);
        this.employeeName = employeeNameString;
    }

    public int getTerritoryNumberProperty() {
        return territoryNumberProperty.get();
    }

    public void setTerritoryNumberProperty(int territoryNumber) {
        this.territoryNumberProperty.set(territoryNumber);
        this.territoryNumber = territoryNumber;
    }

    public int getAnnualSalaryProperty() {
        return annualSalaryProperty.get();
    }

    public void setAnnualSalaryProperty(int annualSalary) {
        this.annualSalaryProperty.set(annualSalary);
        this.annualSalary = annualSalary;
    }
}
