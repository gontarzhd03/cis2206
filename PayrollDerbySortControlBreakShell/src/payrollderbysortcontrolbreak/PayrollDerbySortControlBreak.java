package payrollderbysortcontrolbreak;

import dao.LineSequential;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import payrolldatabaseoperations.*;

public class PayrollDerbySortControlBreak {
    static int controlBreakTerritoryNumber;
    static int totalAnnualSalary;

    public static void main(String[] args) {
        String dbDirectory = "./data/";
        String databaseName = "PayrollMaster";
        String user = "Admin" ;
        String password = "MuCis";
        String outFile = "./data/PayrollListing.out";
        String outFileStreamName = "payrollList";
        SimpleDateFormat dateFormatStandard = new SimpleDateFormat("M/d/YYYY");
        String heading1String = BlankRecord.moveSpaces(19) + "Payroll Listing" + BlankRecord.moveSpaces(16) + dateFormatStandard.format( new Date() ) + BlankRecord.moveSpaces(22);
        String heading2String = "EMP. NO. " + "EMPLOYEE NAME" + BlankRecord.moveSpaces(12) + "TERR NO.  " + "ANNUAL SALARY           " + BlankRecord.moveSpaces(21);
        int numberOfSpaces = 80;
        ArrayList <Employee> employeesArrayList = new ArrayList <Employee> ();
    
        initialization(dbDirectory, databaseName, user, password, outFile, outFileStreamName, numberOfSpaces, heading1String, heading2String);
        Employees employees = PayrollDatabaseOperations.retrieveAll();
        fillSortArrayList(employees, employeesArrayList);
        setControlBreakField(outFileStreamName, employeesArrayList.get(0), numberOfSpaces);
        for(int i = 0; i < employeesArrayList.size(); i++) {
            moveFields(outFileStreamName, employeesArrayList.get(i),  numberOfSpaces);
        }
        performControlBreak(outFileStreamName, numberOfSpaces);
        terminationRoutine(outFileStreamName);
    }    
    
    static void initialization(String dbDirectory, String databaseName, String user, String password, String outFile, String outFileStreamName, int numberOfSpaces, String heading1String, String heading2String){
        PayrollDatabaseOperations.setConnection(dbDirectory, databaseName);
        LineSequential.open(outFile, outFileStreamName, "output");
        writeHeadings(outFileStreamName, numberOfSpaces, heading1String, heading2String);
    }
    
    static void writeHeadings(String outFileStreamName, int numberOfSpaces, String heading1String, String heading2String){
        LineSequential.write(outFileStreamName, BlankRecord.moveSpaces(numberOfSpaces));
        LineSequential.write(outFileStreamName, heading1String);
        LineSequential.write(outFileStreamName, BlankRecord.moveSpaces(numberOfSpaces));
        LineSequential.write(outFileStreamName, heading2String);
        LineSequential.write(outFileStreamName, BlankRecord.moveSpaces(numberOfSpaces));
    }

    static void fillSortArrayList(Employees employees, ArrayList <Employee> employeesArrayList){
        for(int i = 0; i < employees.size(); i++) {
            employeesArrayList.add(employees.get(i));
        }
        TerritoryComparator territoryComparator = new TerritoryComparator();
        employeesArrayList.sort(territoryComparator);
    }

    static void setControlBreakField(String outFileStreamName, Employee employee, int numberOfSpaces){
        DetailLine dtl = new DetailLine();
        String output = dtl.detailLineToString(employee, numberOfSpaces);
        LineSequential.write(outFileStreamName, output);
        
        controlBreakTerritoryNumber = employee.getTerritoryNumber();
        totalAnnualSalary = employee.getAnnualSalary();
    }

    static void moveFields(String outFileStreamName, Employee employee, int numberOfSpaces){
        DetailLine dtl = new DetailLine();
        String output = dtl.detailLineToString(employee, numberOfSpaces);
        if(controlBreakTerritoryNumber != employee.getTerritoryNumber()) {
            performControlBreak(outFileStreamName, numberOfSpaces);
            controlBreakTerritoryNumber = employee.getTerritoryNumber();
        }
        LineSequential.write(outFileStreamName, output);
        totalAnnualSalary += employee.getAnnualSalary();
    }
    
    static void performControlBreak(String outFileStreamName, int numberOfSpaces){
        SummaryLine sum = new SummaryLine();
        String output = sum.summaryLineToString(totalAnnualSalary, controlBreakTerritoryNumber, numberOfSpaces);
        LineSequential.write(outFileStreamName, output);
        LineSequential.write(outFileStreamName, BlankRecord.moveSpaces(numberOfSpaces));
        totalAnnualSalary = 0;
    }
    
    static void terminationRoutine(String outFileStreamName){
        LineSequential.close(outFileStreamName, "output");
        System.out.println("File is complete.");
    }
}
