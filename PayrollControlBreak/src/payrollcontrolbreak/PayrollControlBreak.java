/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package payrollcontrolbreak;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import dao.LineSequential;
/**
 *
 * @author hgontarz
 */
public class PayrollControlBreak {
    static int [] employeeRecordInMarks = {5, 25, 27, 29, 35};
    static int [] employeeRecordOutMarks = {5, 9, 29, 37, 39, 46, 54};
    static String inFile = "./data/PayrollMaster.DAT";
    static String inFileStreamName = "PayrollMaster.DAT";
    static String outFile = "./data/PayrollOut.DAT";
    static String outFileStreamName = "PayrollOut.DAT";
    static String inputLine = "";
    static String blankRecord = moveSpaces(80);
    static SimpleDateFormat dateFormatStandard = new SimpleDateFormat("M/d/YYYY");

    static Date today = new Date();
    static DecimalFormat annualSalaryFormat = new DecimalFormat("###,##0");
    static Employee employee = new Employee();
    static StringBuilder employeeRecordOut;
    static String heading1String = moveSpaces(19) + "Payroll Listing" + moveSpaces(16) + dateFormatStandard.format(today) + moveSpaces(22);
    static String heading2String = "EMP. NO. EMPLOYEE NAME" + moveSpaces(12) + "TERR NO." + moveSpaces(2) + "ANNUAL SALARY" + moveSpaces(23);
    
    static int controlBreakTerritoryNumber;
    static int totalAnnualSalary;
    static StringBuilder summaryRecordOut;
    static int [] summaryRecordOutMarks = {24,41,42,44,54};
    static DecimalFormat totalAnnualSalaryFormat = new DecimalFormat("#,###,##0");
    static String summaryString = "Total Territory";
    /**
     * @param args the command line arguments
     */
    static void initialization() {
        LineSequential.open(inFile, inFileStreamName, "input");
        LineSequential.open(outFile, outFileStreamName, "output");
        writeHeadings();
    }
    static void writeHeadings() {
        LineSequential.write(outFileStreamName, blankRecord);
        LineSequential.write(outFileStreamName, heading1String);
        LineSequential.write(outFileStreamName, blankRecord);
        LineSequential.write(outFileStreamName, heading2String);
        LineSequential.write(outFileStreamName, blankRecord);
    }
    static void initializeEmployeeFields() {
        employee.setEmployeeNumber(Integer.valueOf(inputLine.substring(0, employeeRecordInMarks[0])));
        employee.setEmployeeName(inputLine.substring(employeeRecordInMarks[0], employeeRecordInMarks[1]));
        employee.setTerritoryNumber(Integer.valueOf(inputLine.substring(employeeRecordInMarks[1], employeeRecordInMarks[2])));
        employee.setAnnualSalary(Integer.valueOf(inputLine.substring(employeeRecordInMarks[3], employeeRecordInMarks[4])));
    }
    static void makeEmployeeRecord() {
        String employeeNumberString = String.valueOf(employee.getEmployeeNumber());
        String employeeName = employee.getEmployeeName();
        String territoryNumberString = String.valueOf(employee.getTerritoryNumber());
        String annualSalaryString = annualSalaryFormat.format(employee.getAnnualSalary());
        employeeRecordOut.replace(employeeRecordOutMarks[0] - employeeNumberString.length(), employeeRecordOutMarks[0], employeeNumberString);
        employeeRecordOut.replace(employeeRecordOutMarks[1], employeeRecordOutMarks[2], employeeName);
        employeeRecordOut.replace(employeeRecordOutMarks[4] - territoryNumberString.length(), employeeRecordOutMarks[4], territoryNumberString);
        employeeRecordOut.replace(employeeRecordOutMarks[5], employeeRecordOutMarks[5] + 1, "$");
        employeeRecordOut.replace(employeeRecordOutMarks[6] - annualSalaryString.length(), employeeRecordOutMarks[6], annualSalaryString);
    }
    static void terminationRoutine() {
        LineSequential.close(inFileStreamName, "input");
        LineSequential.close(outFileStreamName, "output");
        System.out.println("Report Conversion Complete");
    }
    static void moveFields() {
        employeeRecordOut = new StringBuilder(blankRecord);
        initializeEmployeeFields();
        if(controlBreakTerritoryNumber != employee.getTerritoryNumber()) {
            performControlBreak();
        }
        makeEmployeeRecord();
        LineSequential.write(outFileStreamName, employeeRecordOut.toString());
        totalAnnualSalary += employee.getAnnualSalary();
    }
    static String moveSpaces(int numberOfSpaces) {
        StringBuilder sb1 = new StringBuilder(numberOfSpaces);
        for(int i = 0; i < numberOfSpaces; i++) {
            sb1.append(" ");
        }
        return sb1.toString();
    }
    static void makeSummaryLine() {
        String territoryNumberString = String.valueOf(controlBreakTerritoryNumber);
        String totalSalaryString = totalAnnualSalaryFormat.format(totalAnnualSalary);
        summaryRecordOut = new StringBuilder(blankRecord);
        summaryRecordOut.replace(summaryRecordOutMarks[0], summaryRecordOutMarks[0] + summaryString.length(), summaryString);
        summaryRecordOut.replace(summaryRecordOutMarks[2] - territoryNumberString.length(), summaryRecordOutMarks[2], territoryNumberString);
        summaryRecordOut.replace(summaryRecordOutMarks[3], summaryRecordOutMarks[3] + 1, "$");
        summaryRecordOut.replace(summaryRecordOutMarks[4] - totalSalaryString.length(), summaryRecordOutMarks[4], totalSalaryString);
    }
    static void performControlBreak() {
        makeSummaryLine();
        LineSequential.write(outFileStreamName, summaryRecordOut.toString());
        LineSequential.write(outFileStreamName, blankRecord);
        controlBreakTerritoryNumber = employee.getTerritoryNumber();
        totalAnnualSalary = 0;
    }
    static void setControlBreakField() {
        inputLine = LineSequential.read(inFileStreamName);
        employeeRecordOut = new StringBuilder(blankRecord);
        initializeEmployeeFields();
        makeEmployeeRecord();
        LineSequential.write(outFileStreamName, employeeRecordOut.toString());
        controlBreakTerritoryNumber = employee.getTerritoryNumber();
        totalAnnualSalary = employee.getAnnualSalary();
    }
    
    public static void main(String[] args) {
        initialization();
        setControlBreakField();
        while((inputLine = LineSequential.read(inFileStreamName)) != null) {
            moveFields();
        }
        performControlBreak();
        terminationRoutine();
    }
    
}
