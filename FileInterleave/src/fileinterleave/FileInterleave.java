/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileinterleave;
import dao.LineSequential;
/**
 *
 * @author 55gontarhd03
 */
public class FileInterleave {

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
        String inputLine = "";
        
        LineSequential.open(inFile1, inFileStreamName1, "input");
        LineSequential.open(inFile2, inFileStreamName2, "input");
        LineSequential.open(outFile, outFileStreamName, "output");
        while((inputLine = LineSequential.read(inFileStreamName1)) != null) {
            LineSequential.write(outFileStreamName, inputLine);
            inputLine = LineSequential.read(inFileStreamName2);
            LineSequential.write(outFileStreamName, inputLine);
        }
        
        LineSequential.close(inFileStreamName1, "input");
        LineSequential.close(inFileStreamName2, "input");
        LineSequential.close(outFileStreamName, "output");
        System.out.println("File Complete");
    }
    
}
