/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package payrolldualcontrolbreak;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import dao.LineSequential;
/**
 *
 * @author hgontarz
 */
public class PayrollDualControlBreak {
    static int [] employeeRecordInMarks = {5, 25, 27, 29, 35};
    static int [] employeeRecordOutMarks = {5, 9, 29, 35, 46, 52, 60};
    static String inFile = "./data/Pr1.DAT";
    static String inFileStreamName = "Pr1.DAT";
    static String outFile = "./data/PayrollOut2.DAT";
    static String outFileStreamName = "PayrollOut2.DAT";
    static String inputLine = "";
    static String blankRecord = moveSpaces(80);
    static SimpleDateFormat dateFormatStandard = new SimpleDateFormat("M/d/YYYY");

    static Date today = new Date();
    static DecimalFormat annualSalaryFormat = new DecimalFormat("###,##0");
    static Employee employee = new Employee();
    static StringBuilder employeeRecordOut;
    static String heading1String = moveSpaces(19) + "Payroll Listing" + moveSpaces(16) + dateFormatStandard.format(today) + moveSpaces(20);
    static String heading2String = "EMP. NO. EMPLOYEE NAME" + moveSpaces(8) + "TERR NO." + moveSpaces(2) + "DIST NO." + moveSpaces(2) + "ANNUAL SALARY" + moveSpaces(17);
    
    static int controlBreakTerritoryNumber;
    static int totalAnnualSalary;
    static StringBuilder summaryRecordOut;
    static int [] summaryRecordOutMarks = {24, 41, 42, 44, 54};
    static DecimalFormat totalAnnualSalaryFormat = new DecimalFormat("#,###,##0");
    static String summaryString = "Total Territory";
    
    static int controlBreakDistrictNumber;
    static int totalDistrictSalary;
    static StringBuilder summaryDistrictRecordOut;
    static int [] summaryDistrictRecordOutMarks = {24, 41, 42, 44, 54};
    static String summaryDistrictString = "Total District";
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
    static void setControlBreakField() {
        inputLine = LineSequential.read(inFileStreamName);
        employeeRecordOut = new StringBuilder(blankRecord);
        initializeEmployeeFields();
        makeEmployeeRecord();
        LineSequential.write(outFileStreamName, employeeRecordOut.toString());
        controlBreakDistrictNumber = employee.getDistrictNumber();
        totalDistrictSalary = employee.getAnnualSalary();
        controlBreakTerritoryNumber = employee.getTerritoryNumber();
        totalAnnualSalary = 0;
    }
    static void initializeEmployeeFields() {
        employee.setEmployeeNumber(Integer.valueOf(inputLine.substring(0, employeeRecordInMarks[0])));
        employee.setEmployeeName(inputLine.substring(employeeRecordInMarks[0], employeeRecordInMarks[1]));
        employee.setTerritoryNumber(Integer.valueOf(inputLine.substring(employeeRecordInMarks[1], employeeRecordInMarks[2])));
        employee.setDistrictNumber(Integer.valueOf(inputLine.substring(employeeRecordInMarks[2], employeeRecordInMarks[3])));
        employee.setAnnualSalary(Integer.valueOf(inputLine.substring(employeeRecordInMarks[3], employeeRecordInMarks[4])));
    }
    static void makeEmployeeRecord() {
        String employeeNumberString = String.valueOf(employee.getEmployeeNumber());
        String employeeName = employee.getEmployeeName();
        String territoryNumberString = String.valueOf(employee.getTerritoryNumber());
        String districtNumberString = String.valueOf(employee.getDistrictNumber());
        String annualSalaryString = annualSalaryFormat.format(employee.getAnnualSalary());
        employeeRecordOut.replace(employeeRecordOutMarks[0] - employeeNumberString.length(), employeeRecordOutMarks[0], employeeNumberString);
        employeeRecordOut.replace(employeeRecordOutMarks[1], employeeRecordOutMarks[2], employeeName);
        employeeRecordOut.replace(employeeRecordOutMarks[3] - territoryNumberString.length(), employeeRecordOutMarks[3], territoryNumberString);
        employeeRecordOut.replace(employeeRecordOutMarks[4] - districtNumberString.length(), employeeRecordOutMarks[4], districtNumberString);
        employeeRecordOut.replace(employeeRecordOutMarks[5], employeeRecordOutMarks[5] + 1, "$");
        employeeRecordOut.replace(employeeRecordOutMarks[6] - annualSalaryString.length(), employeeRecordOutMarks[6], annualSalaryString);
    }
    static void moveFields() {
        employeeRecordOut = new StringBuilder(blankRecord);
        initializeEmployeeFields();
        if(controlBreakTerritoryNumber != employee.getTerritoryNumber()) {
            performMajorControlBreak();
        }
        else if(controlBreakDistrictNumber != employee.getDistrictNumber()) {
            performMinorControlBreak();
        }
        makeEmployeeRecord();
        LineSequential.write(outFileStreamName, employeeRecordOut.toString());
        totalDistrictSalary += employee.getAnnualSalary();
    }
    static void performMajorControlBreak() {
        performMinorControlBreak();
        makeSummaryLine2();
        LineSequential.write(outFileStreamName, summaryRecordOut.toString());
        LineSequential.write(outFileStreamName, blankRecord);
        controlBreakTerritoryNumber = employee.getTerritoryNumber();
        totalAnnualSalary = 0;
    }
    static void performMinorControlBreak() {        
        makeSummaryLine1();
        LineSequential.write(outFileStreamName, summaryRecordOut.toString());
        LineSequential.write(outFileStreamName, blankRecord);
        controlBreakDistrictNumber = employee.getDistrictNumber();
        totalAnnualSalary += totalDistrictSalary;
        totalDistrictSalary = 0;
    }
    static void makeSummaryLine1() {
        String districtNumberString = String.valueOf(controlBreakDistrictNumber);
        String totalDistrictSalaryString = totalAnnualSalaryFormat.format(totalDistrictSalary);
        summaryRecordOut = new StringBuilder(blankRecord);
        summaryRecordOut.replace(summaryRecordOutMarks[0], summaryRecordOutMarks[0] + summaryDistrictString.length(), summaryDistrictString);
        summaryRecordOut.replace(summaryRecordOutMarks[2] - districtNumberString.length(), summaryRecordOutMarks[2], districtNumberString);
        summaryRecordOut.replace(summaryRecordOutMarks[3], summaryRecordOutMarks[3] + 1, "$");
        summaryRecordOut.replace(summaryRecordOutMarks[4] - totalDistrictSalaryString.length(), summaryRecordOutMarks[4], totalDistrictSalaryString);
    }
    static void makeSummaryLine2() {
        String territoryNumberString = String.valueOf(controlBreakTerritoryNumber);
        String totalSalaryString = totalAnnualSalaryFormat.format(totalAnnualSalary);
        summaryRecordOut = new StringBuilder(blankRecord);
        summaryRecordOut.replace(summaryRecordOutMarks[0], summaryRecordOutMarks[0] + summaryString.length(), summaryString);
        summaryRecordOut.replace(summaryRecordOutMarks[2] - territoryNumberString.length(), summaryRecordOutMarks[2], territoryNumberString);
        summaryRecordOut.replace(summaryRecordOutMarks[3], summaryRecordOutMarks[3] + 1, "$");
        summaryRecordOut.replace(summaryRecordOutMarks[4] - totalSalaryString.length(), summaryRecordOutMarks[4], totalSalaryString);
    }
    static void terminationRoutine() {
        LineSequential.close(inFileStreamName, "input");
        LineSequential.close(outFileStreamName, "output");
        System.out.println("Report Conversion Complete");
    }
    static String moveSpaces(int numberOfSpaces) {
        StringBuilder sb1 = new StringBuilder(numberOfSpaces);
        for(int i = 0; i < numberOfSpaces; i++) {
            sb1.append(" ");
        }
        return sb1.toString();
    }
    
    public static void main(String[] args) {
        initialization();
        setControlBreakField();
        while((inputLine = LineSequential.read(inFileStreamName)) != null) {
            moveFields();
        }
        performMajorControlBreak();
        terminationRoutine();
    }
    
}
