/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package payrollderbysortcontrolbreak;

import java.text.DecimalFormat;
import payrolldatabaseoperations.Employee;
/**
 *
 * @author Clarence
 */
public class DetailLine {
    private int [] employeeRecordOutMarks = {5, 9, 29, 37, 39, 46, 54};
    private DecimalFormat annualSalaryFormat = new DecimalFormat("###,##0");

    public String detailLineToString(Employee employee, int numberOfSpaces){    
        StringBuilder employeeRecordOut = new StringBuilder(BlankRecord.moveSpaces(numberOfSpaces));
        String employeeNumberString = String.valueOf(employee.getEmployeeNumber());
        String employeeName = employee.getEmployeeName();
        String territoryNumberString = String.valueOf(employee.getTerritoryNumber());
        String annualSalaryString = annualSalaryFormat.format(employee.getAnnualSalary());
        employeeRecordOut.replace(employeeRecordOutMarks[0] - employeeNumberString.length(), employeeRecordOutMarks[0], employeeNumberString);
        employeeRecordOut.replace(employeeRecordOutMarks[1], employeeRecordOutMarks[2], employeeName);
        employeeRecordOut.replace(employeeRecordOutMarks[4] - territoryNumberString.length(), employeeRecordOutMarks[4], territoryNumberString);
        employeeRecordOut.replace(employeeRecordOutMarks[5], employeeRecordOutMarks[5], "$");
        employeeRecordOut.replace(employeeRecordOutMarks[6] - annualSalaryString.length(), employeeRecordOutMarks[6], annualSalaryString);
        return employeeRecordOut.toString();
    }
}
