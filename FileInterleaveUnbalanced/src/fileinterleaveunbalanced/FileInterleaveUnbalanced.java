/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileinterleaveunbalanced;
import dao.LineSequential;
/**
 *
 * @author 55gontarhd03
 */
public class FileInterleaveUnbalanced {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String inFile1 = "./data/PayrollMasterA.DAT";
        String inFile2 = "./data/PayrollMasterB.DAT";
        String outFile = "./data/PayrollMaster.DAT";
        String inFileStreamName1 = "PayrollMasterA.DAT";
        String inFileStreamName2 = "PayrollMasterB.DAT";
        String outFileStreamName = "PayrollMaster.DAT";
        String inputLine1 = "";
        String inputLine2 = "";
        
        LineSequential.open(inFile1, inFileStreamName1, "input");
        LineSequential.open(inFile2, inFileStreamName2, "input");
        LineSequential.open(outFile, outFileStreamName, "output");
        while(
                ((inputLine1 = LineSequential.read(inFileStreamName1)) != null) &
                ((inputLine2 = LineSequential.read(inFileStreamName2)) != null)
             ) {
            LineSequential.write(outFileStreamName, inputLine1);
            LineSequential.write(outFileStreamName, inputLine2);
        }
        
        LineSequential.close(inFileStreamName1, "input");
        LineSequential.close(inFileStreamName2, "input");
        LineSequential.close(outFileStreamName, "output");
        System.out.println("File Complete");
    }
    
}
