package payrollretrievederbydatabase;

import java.sql.*;

public class PayrollRetrieveDerbyDatabase {

//    static Connection connection1 = null;

    public static void main(String[] args) {
        retrieveFields();
    }
    
    static void retrieveFields() {
        Employees employees = retrieve();
        for(int i = 0; i < employees.size(); i++) {
            System.out.println(employees.get(i).getEmployeeNumber());
            System.out.println(employees.get(i).getEmployeeName());
            System.out.println(employees.get(i).getTerritoryNumber());
            System.out.println(employees.get(i).getAnnualSalary());
            System.out.println();
        }
    }

    static Connection openConnection() throws SQLException {
        String dbProtocol = "jdbc:derby:";
        String dbDirectory = "./data/";
        String dbName = "PayrollMaster";
        String connectionURL = dbProtocol + dbDirectory + dbName;

//        System.setProperty("derby.system.home", dbDirectory);

        Connection connection1 = DriverManager.getConnection(connectionURL, "Admin", "MuCis");
        return connection1;
    }
    static Employees retrieve() {
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
/*
    static void close() {
        try {
            String shutDownURL = "jdbc:derby:;shutdown = true";
            DriverManager.getConnection(shutDownURL);
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }
*/
}
