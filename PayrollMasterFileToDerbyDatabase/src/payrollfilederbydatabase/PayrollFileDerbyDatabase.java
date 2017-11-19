package payrollfilederbydatabase;

import dao.LineSequential; 
import java.sql.*;

public class PayrollFileDerbyDatabase {

    static Connection connection1 = null;

    public static void main(String[] args) {
        String inFile = "./data/PayrollMaster.dat";
        String inFileStreamName = "payrollMaster";
        int [] employeeRecordInMarks = {5, 25, 27, 29, 35};
        String inputLine;

        Employee employee = new Employee();
        
        initialization(inFile, inFileStreamName);
        while((inputLine = LineSequential.read(inFileStreamName)) != null) {
            moveFields(employee, inputLine, employeeRecordInMarks);
        }
        terminationRoutine(inFileStreamName);
    }
    
    static void initialization(String inFile, String inFileStreamName) {
        LineSequential.open(inFile, inFileStreamName, "input");
        openConnection();
    }

    static void moveFields( Employee employee, String inputLine, int []employeeRecordInMarks) {
        initializeEmployeeFields(employee, inputLine, employeeRecordInMarks);
        insert(employee);
    }
    
    static void initializeEmployeeFields(Employee employee, String inputLine, int []employeeRecordInMarks) {
        employee.setEmployeeNumber(Integer.valueOf(inputLine.substring(0, employeeRecordInMarks[0])));
        employee.setEmployeeName(inputLine.substring(employeeRecordInMarks[0], employeeRecordInMarks[1]));
        employee.setTerritoryNumber(Integer.valueOf(inputLine.substring(employeeRecordInMarks[1], employeeRecordInMarks[2])));
        employee.setAnnualSalary(Integer.valueOf(inputLine.substring(employeeRecordInMarks[3], employeeRecordInMarks[4])));
    }

    static void terminationRoutine(String inFileStreamName) {
        LineSequential.close(inFileStreamName, "input");
        close();
        System.out.println("Database is complete.");
    }
    
    static void openConnection() {
        String dbProtocol = "jdbc:derby:";
        String dbDirectory = "./data/";
        String dbName = "PayrollMaster";
        String connectionURL = dbProtocol + dbDirectory + dbName;

        System.setProperty("derby.system.home", dbDirectory);
        try {
            connection1 = DriverManager.getConnection(connectionURL, "Admin", "MuCis");

        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }

    static void insert(Employee employee) {        
        String insertStatementEmployee = "INSERT INTO Payroll (EmployeeNumber, Name, TerritoryNumber, AnnualSalary) VALUES (" + 
                employee.getEmployeeNumber() + ", '" +
                employee.getEmployeeName() + "', " +
                employee.getTerritoryNumber() + ", " +
                employee.getAnnualSalary() + ")";
        try {
            Statement statement = connection1.createStatement();
            statement.executeUpdate(insertStatementEmployee);
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }

    static void close() {
        try {
            String shutDownURL = "jdbc:derby:;shutdown = true";
            DriverManager.getConnection(shutDownURL);
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }
    
}
