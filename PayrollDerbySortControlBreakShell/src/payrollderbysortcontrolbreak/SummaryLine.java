/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package payrollderbysortcontrolbreak;

import java.text.DecimalFormat;

/**
 *
 * @author Clarence
 */
public class SummaryLine {
    private int [] summaryRecordOutMarks = {24, 41, 42, 44, 54};
    private DecimalFormat totalAnnualSalaryFormat = new DecimalFormat("#,###,##0");
    private String summaryString = "Total Territory ";

    public String summaryLineToString(int totalAnnualSalary, int controlBreakTerritoryNumber, int numberOfSpaces){    
        StringBuilder summaryRecordOut = new StringBuilder(BlankRecord.moveSpaces(numberOfSpaces));
        String territoryNumberString = String.valueOf(controlBreakTerritoryNumber);
        String totalAnnualSalaryString = totalAnnualSalaryFormat.format(totalAnnualSalary);
        summaryRecordOut.replace(summaryRecordOutMarks[0], summaryRecordOutMarks[1], summaryString);
        summaryRecordOut.replace(summaryRecordOutMarks[2] - territoryNumberString.length(), summaryRecordOutMarks[2], territoryNumberString);
        summaryRecordOut.replace(summaryRecordOutMarks[3], summaryRecordOutMarks[3], "$");
        summaryRecordOut.replace(summaryRecordOutMarks[4] - totalAnnualSalaryString.length(), summaryRecordOutMarks[4], totalAnnualSalaryString);
        return summaryRecordOut.toString();
    }    
}
