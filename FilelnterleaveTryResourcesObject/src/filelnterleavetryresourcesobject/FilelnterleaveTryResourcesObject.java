/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filelnterleavetryresourcesobject;
import java.io.IOException;

/**
 *
 * @author hgontarz
 */
public class FilelnterleaveTryResourcesObject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      String inFile1 = "./data/PayrollMasterA.dat";
      String inFile2 = "./data/PayrollMasterB.dat";
      String outFile = "./data/PayrollMaster.dat";
      String inputLine;

      try(LineSequentialTryResourcesObject inFileStream1 = new LineSequentialTryResourcesObject(inFile1, "input");
          LineSequentialTryResourcesObject inFileStream2 = new LineSequentialTryResourcesObject(inFile2, "input");
          LineSequentialTryResourcesObject outFileStream = new LineSequentialTryResourcesObject(outFile, "output");) {
          while((inputLine = inFileStream1.read()) != null) {
             outFileStream.write(inputLine);
             inputLine = inFileStream2.read();
             outFileStream.write(inputLine);
          }
      }
      catch(IOException e) {
         System.out.println("IO Error" + e.getMessage());
      }
      System.out.println("File Complete");
    }
    
}
