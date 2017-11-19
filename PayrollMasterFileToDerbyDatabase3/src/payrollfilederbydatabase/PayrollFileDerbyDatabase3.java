package payrollfilederbydatabase;

import dao.LineSequential; 
import java.sql.*;

public class PayrollFileDerbyDatabase3 {

    static Connection connection1 = null;

    public static void main(String[] args) {
        String inFile = "./data/PayrollMaster.dat";
        String inFileStreamName = "payrollMaster";
        int [] employeeRecordInMarks = {5, 25, 27, 29, 35};

        Employee employee = new Employee();
        moveFields(employee, employeeRecordInMarks, inFileStreamName, inFile);
    }

    static void moveFields(Employee employee, int []employeeRecordInMarks, String inFileStreamName, String inFile) {
        LineSequential.open(inFile, inFileStreamName, "input");
        moveFieldToDatabase(employee, employeeRecordInMarks, inFileStreamName);
        LineSequential.close(inFileStreamName, "input");
        System.out.println("Database is complete.");
    }
    
    static void moveFieldToDatabase(Employee employee, int []employeeRecordInMarks, String inFileStreamName) {
        String inputLine;

        try(Connection connection1 = openConnection()) {
            while((inputLine = LineSequential.read(inFileStreamName)) != null) {
                initializeEmployeeFields(employee, inputLine, employeeRecordInMarks);
                insert(employee);
            }
        }
        catch (SQLException e) {
            System.out.println(e.toString());
        }
    }
    static void initializeEmployeeFields(Employee employee, String inputLine, int []employeeRecordInMarks) {
        employee.setEmployeeNumber(Integer.valueOf(inputLine.substring(0, employeeRecordInMarks[0])));
        employee.setEmployeeName(inputLine.substring(employeeRecordInMarks[0], employeeRecordInMarks[1]));
        employee.setTerritoryNumber(Integer.valueOf(inputLine.substring(employeeRecordInMarks[1], employeeRecordInMarks[2])));
        employee.setAnnualSalary(Integer.valueOf(inputLine.substring(employeeRecordInMarks[3], employeeRecordInMarks[4])));
    }
    
    static Connection openConnection() throws SQLException {
        String dbProtocol = "jdbc:derby:";
        String dbDirectory = "./data/";
        String dbName = "PayrollMaster";
        String connectionURL = dbProtocol + dbDirectory + dbName;

        System.setProperty("derby.system.home", dbDirectory);

        connection1 = DriverManager.getConnection(connectionURL, "Admin", "MuCis");
        return connection1;
    }

    static void insert(Employee employee) throws SQLException {
        String insertStatementEmployee = "INSERT INTO Payroll (EmployeeNumber, Name, TerritoryNumber, AnnualSalary) VALUES (" + 
                employee.getEmployeeNumber() + ", '" +
                employee.getEmployeeName() + "', " +
                employee.getTerritoryNumber() + ", " +
                employee.getAnnualSalary() + ")";

        Statement statement = connection1.createStatement();
        statement.executeUpdate(insertStatementEmployee);
    }    
}
