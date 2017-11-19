/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package payrollderby3;

import dao.LineSequential;
import payrollderbydatabaseoperations.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Clarence
 */
public class PayrollDerby3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String dbDirectory = "./data/";
        String dbName = "PayrollMaster";
        
        String outFile = "./data/PayrollListing.out";
        String outFileStreamName = "payrollList";
        SimpleDateFormat dateFormatStandard = new SimpleDateFormat("M/d/YYYY");
        String heading1String = BlankRecord.moveSpaces(19) + "Payroll Listing" + BlankRecord.moveSpaces(16) + dateFormatStandard.format( new Date() ) + BlankRecord.moveSpaces(22);
        String heading2String = "EMP. NO. " + "EMPLOYEE NAME" + BlankRecord.moveSpaces(12) + "TERR NO.  " + "ANNUAL SALARY           " + BlankRecord.moveSpaces(21);
        int numberOfSpaces = 80;
        int [] employeeRecordInMarks = {5, 25, 27, 29, 35};

        initialization(dbDirectory, dbName, outFile, outFileStreamName, numberOfSpaces, heading1String, heading2String);
        Employees employees = PayrollDerbyDatabaseOperations.retrieveAll();
        
        for(int i = 0; i < employees.size(); i++){
            moveFields(outFileStreamName, employees.get(i),  numberOfSpaces);
        }
        
        terminationRoutine(outFileStreamName);

    }    
    static void initialization(String dbDirectory, String dbName, String outFile, String outFileStreamName, int numberOfSpances, String heading1String, String heading2String){
        PayrollDerbyDatabaseOperations.setConnection(dbDirectory, dbName);
        LineSequential.open(outFile, outFileStreamName, "output");
        writeHeadings(outFileStreamName, numberOfSpances, heading1String, heading2String);
    }
    
    static void writeHeadings(String outFileStreamName, int numberOfSpances, String heading1String, String heading2String){
        LineSequential.write(outFileStreamName, BlankRecord.moveSpaces(numberOfSpances));
        LineSequential.write(outFileStreamName, heading1String);
        LineSequential.write(outFileStreamName, BlankRecord.moveSpaces(numberOfSpances));
        LineSequential.write(outFileStreamName, heading2String);
        LineSequential.write(outFileStreamName, BlankRecord.moveSpaces(numberOfSpances));
    }

    
    static void moveFields(String outFileStreamName, Employee employee, int numberOfSpaces){
        DetailLine employeeRecordOut = new DetailLine();
        LineSequential.write(outFileStreamName, employeeRecordOut.detailLineToString(employee, numberOfSpaces));
    }
    
    static void initializeEmployeeFields(Employee employee, String inputLine, int []employeeRecordInMarks){
        employee.setEmployeeNumber(Integer.valueOf(inputLine.substring(0, employeeRecordInMarks[0])));
        employee.setEmployeeName(inputLine.substring(employeeRecordInMarks[0], employeeRecordInMarks[1]));
        employee.setTerritoryNumber(Integer.valueOf(inputLine.substring(employeeRecordInMarks[1], employeeRecordInMarks[2])));
        employee.setAnnualSalary(Integer.valueOf(inputLine.substring(employeeRecordInMarks[3], employeeRecordInMarks[4])));
    }
    
    
    static void terminationRoutine(String outFileStreamName){
        LineSequential.close(outFileStreamName, "output");
        System.out.println("File is complete.");
    }
    
    
}
