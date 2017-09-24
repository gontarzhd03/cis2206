/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileinterleavetryresources;
import java.util.ArrayList;

/**
 *
 * @author hgontarz
 */
public class FileInterleaveTryResources {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      String inFile1 = "./data/PayrollMasterA.dat";
      String inFile2 = "./data/PayrollMasterB.dat";
      String outFile = "./data/PayrollMaster.dat";

      ArrayList <String> inputLinesl = LineSequentialTryResources.readAll(inFile1);
      ArrayList <String> inputLines2 = LineSequentialTryResources.readAll(inFile2);
      ArrayList <String> outputLines = new ArrayList <String> ();

      for(int i = 0; i < inputLinesl.size(); i++) {
         outputLines.add(inputLinesl.get(i)) ;
         outputLines.add(inputLines2.get(i));
      }
      LineSequentialTryResources.writeall(outFile, outputLines);
      //LineSequential.close(inFileStrearnName1, "input");
      //LineSequential.close(inFileStrearnName2, "input");
      //LineSequential.close(outFileStreamNarne, "output");
      System.out.println("File Complete");
    }
    
}
