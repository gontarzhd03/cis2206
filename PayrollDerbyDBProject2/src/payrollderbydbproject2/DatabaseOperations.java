/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package payrollderbydbproject2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author hgontarz
 */
public class DatabaseOperations {
    /**
     * 
     * @return
     * @throws SQLException 
     */
    private static Connection openConnection() throws SQLException {
        String dbProtocol = "jdbc:derby:";
        String dbDirectory = "./data/";
        String dbName = "PayrollMaster";
        String connectionURL = dbProtocol + dbDirectory + dbName;

//        System.setProperty("derby.system.home", dbDirectory);
        Connection connection1 = DriverManager.getConnection(connectionURL, "Admin", "MuCis");
        return connection1;
    }
    /**
     * 
     * @param employee 
     */
    static void insert(Employee employee) {        
        String insertStatementEmployee = "INSERT INTO Payroll (EmployeeNumber, Name, TerritoryNumber, AnnualSalary) VALUES (" + 
                employee.getEmployeeNumber() + ", '" +
                employee.getEmployeeName() + "', " +
                employee.getTerritoryNumber() + ", " +
                employee.getAnnualSalary() + ")";
        try(Connection connection1 = openConnection()) {
            Statement statement = connection1.createStatement();
            statement.executeUpdate(insertStatementEmployee);
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }
    /**
     * 
     * @param employeeNumber
     * @return 
     */
    static Employee retrieve(int employeeNumber) {
        String query1 = "SELECT * FROM Payroll WHERE EmployeeNumber = " + employeeNumber;
        Employee employee = new Employee();
        try(Connection connection1 = openConnection()) {
            Statement statement = connection1.createStatement();
            ResultSet rs1 = statement.executeQuery(query1);
            if(rs1.next()) {
                employee.setEmployeeNumber(rs1.getInt(1));
                employee.setEmployeeName(rs1.getString(2));
                employee.setTerritoryNumber(rs1.getInt(3));
                employee.setAnnualSalary(rs1.getInt(4));
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        return employee;
    }
    /**
     * 
     * @return 
     */
    static Employees retrieveAll() {
        String query1 = "SELECT * FROM Payroll";
        Employees employees = new Employees();
        try(Connection connection1 = openConnection()) {
            Statement statement = connection1.createStatement();
            ResultSet rs1 = statement.executeQuery(query1);
            while(rs1.next()) {
                Employee employeeNext = new Employee();
                employeeNext.setEmployeeNumber(rs1.getInt(1));
                employeeNext.setEmployeeName(rs1.getString(2));
                employeeNext.setTerritoryNumber(rs1.getInt(3));
                employeeNext.setAnnualSalary(rs1.getInt(4));
                employees.add(employeeNext);
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        return employees;
    }
    /**
     * 
     * @param employeeName
     * @param employeeNumber 
     */
    static void modifyName(String employeeName, int employeeNumber) {
        String modifyStatement = "UPDATE Payroll SET Name = '" + 
               employeeName + "' WHERE EmployeeNumber = " + employeeNumber;
        
        try(Connection connection1 = openConnection()) {
            Statement statement = connection1.createStatement();
            statement.executeUpdate(modifyStatement);
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }
    /**
     * 
     * @param territoryNumber
     * @param employeeNumber 
     */
    static void modifyTerritoryNumber(int territoryNumber, int employeeNumber) {
        String modifyStatement = "UPDATE Payroll SET TerritoryNumber = " + 
               territoryNumber + " WHERE EmployeeNumber = " + employeeNumber;
        
        try(Connection connection1 = openConnection()) {
            Statement statement = connection1.createStatement();
            statement.executeUpdate(modifyStatement);
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }
    /**
     * 
     * @param annualSalary
     * @param employeeNumber 
     */
    static void modifyAnnualSalary(int annualSalary, int employeeNumber) {
        String modifyStatement = "UPDATE Payroll SET AnnualSalary = " + 
               annualSalary + " WHERE EmployeeNumber = " + employeeNumber;
        
        try(Connection connection1 = openConnection()) {
            Statement statement = connection1.createStatement();
            statement.executeUpdate(modifyStatement);
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }
    /**
     * 
     * @param employeeNumber 
     */
    static void delete(int employeeNumber) {
        String deleteStatement = "DELETE FROM Payroll WHERE EmployeeNumber = " + employeeNumber;
        
        try(Connection connection1 = openConnection()) {
            Statement statement = connection1.createStatement();
            statement.executeUpdate(deleteStatement);
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }
}
