/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PayrollDatabaseOperations;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author hgontarz
 */
public class PayrollDatabaseOperations {

    private static String dbProtocol = "jdbc:derby:";
    private static String dbDirectory = "./data/PayrollDerbyDatabase";
    private static String dbName = "PayrollMaster";
    private static String dbExtra = "";
    private static String userName = "Admin";
    private static String passWord = "MuCis";

    public static void setConnection(String dbDirectoryIn, String deameIn) {
        dbDirectory = dbDirectoryIn;
        dbName = deameIn;
    }

    public static void setConnection(String dbDirectoryIn, String deameIn, String userNameIn, String passWordIn) {
        dbDirectory = dbDirectoryIn;
        dbName = deameIn;
        userName = userNameIn;
        passWord = passWordIn;
    }

    public static void setConnection(String dbProtocolIn, String dbDirectoryIn, String deameIn, String dbExtraIn, String userNameIn, String passWordIn) {
        dbProtocol = dbProtocolIn;
        dbDirectory = dbDirectoryIn;
        dbName = deameIn;
        dbExtra = dbExtraIn;
        userName = userNameIn;
        passWord = passWordIn;
    }

    private static Connection openConnection() throws SQLException {
        String connectionURL = dbProtocol + dbDirectory + dbName + dbExtra;

        Connection connectionl = DriverManager.getConnection(connectionURL, userName, passWord);

        return connectionl;
    }

    public static Employees retrieveA11() {
        String query1 = "SELECT * FROM Payroll";
        Employees employees = new Employees();
        try (Connection connection1 = openConnection()) {
            Statement statement = connection1.createStatement();
            ResultSet rs1 = statement.executeQuery(query1);
            while (rs1.next()) {
                Employee employeeNext = new Employee();
                employeeNext.setEmployeeNumber(rs1.getInt(1));
                employeeNext.setEmployeeName(rs1.getString(2));
                employeeNext.setTerritoryNumber(rs1.getInt(3));
                employeeNext.setAnnualSalary(rs1.getInt(4));
                employees.add(employeeNext);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return employees;
    }

    public static Employee retrieve(int employeeNumber) {
        String query1 = "SELECT * FROM Payroll NHERE EmployeeNumber = ? ";
        Employee employeeNext = new Employee();
        try (Connection connection1 = openConnection()) {
            PreparedStatement statement = connection1.prepareStatement(query1);
            statement.setInt(1, employeeNumber);
            ResultSet rs1 = statement.executeQuery();
            if (rs1.next()) {
                employeeNext.setEmployeeNumber(rs1.getInt(1));
                employeeNext.setEmployeeName(rs1.getString(2));
                employeeNext.setTerritoryNumber(rs1.getInt(3));
                employeeNext.setAnnualSalary(rs1.getInt(4));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return employeeNext;
    }

    public static void insert(Employee employee) {
        String insertStatementEmployee
                = "INSERT INTO Payroll (EmployeeNumber, Name, TerritoryNumber, AnnualSalary) VALUES(?, ?, ?, ?)";
        try (Connection connection1 = openConnection()) {
            PreparedStatement statement = connection1.prepareStatement(insertStatementEmployee);
            statement.setInt(1, employee.getEmployeeNumber());
            statement.setString(2, employee.getEmployeeName());
            statement.setInt(3, employee.getTerritoryNumber());
            statement.setInt(4, employee.getAnnualSalary());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    public static void modifyTerritoryNumber(int modifyFieldValue, int conditionFieldValue) {
        String modifyStatement
                = "UPDATE Payroll SET TerritoryNumber = ? WHERE EmployeeNumber = ? ";
        try (Connection connection1 = openConnection()) {
            PreparedStatement statement = connection1.prepareStatement(modifyStatement);
            statement.setInt(1, modifyFieldValue);
            statement.setInt(2, conditionFieldValue);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    public static void modifyAnnualSalary(int modifyFieldValue, int conditionFieldValue) {
        String modifyStatement
                = "UPDATE Payroll SET AnnualSalary = ? WHERE EmployeeNumber = ? ";
        try (Connection connection1 = openConnection()) {
            PreparedStatement statement = connection1.prepareStatement(modifyStatement);
            statement.setInt(1, modifyFieldValue);
            statement.setInt(2, conditionFieldValue);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    public static void modifyName(String modifyFieldValue, int conditionFieldValue) {
        String modifyStatement
                = "UPDATE Payroll SET Name = ? WHERE EmployeeNumber = ? ";
        try (Connection connection1 = openConnection()) {
            PreparedStatement statement = connection1.prepareStatement(modifyStatement);
            statement.setString(1, modifyFieldValue);
            statement.setInt(2, conditionFieldValue);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    public static void delete(int conditionFieldValue) {
        String deleteStatement = "DELETE FROM Payroll WHERE EmployeeNumber = ? ";
        try (Connection connection1 = openConnection()) {
            PreparedStatement statement = connection1.prepareStatement(deleteStatement);
            statement.setInt(1, conditionFieldValue);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    public static ArrayList getFieldNames() {
        String query1 = "SELECT * FROM Payroll";
        ArrayList<String> columnNames = new ArrayList<String>();
        try (Connection connection1 = openConnection()) {
            Statement statement = connection1.createStatement();
            ResultSet rs1 = statement.executeQuery(query1);
            ResultSetMetaData rsm1 = rs1.getMetaData();
            for (int columnNumber = 1; columnNumber <= rsm1.getColumnCount(); columnNumber++) {
                columnNames.add(rsm1.getColumnName(columnNumber));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return columnNames;
    }
}
