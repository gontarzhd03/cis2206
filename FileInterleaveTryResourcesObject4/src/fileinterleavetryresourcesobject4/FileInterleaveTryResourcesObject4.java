/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileinterleavetryresourcesobject4;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author hgontarz
 */
public class FileInterleaveTryResourcesObject4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      ArrayList <String> fileNames = new ArrayList <String>();
      ArrayList <String> ioModes = new ArrayList <String> ();
      String inFile1 = "./data/PayrollMasterA.dat";
      String inFile2 = "./data/PayrollMasterB.dat";
      String outFile = "./data/PayrollMaster.dat";
      fileNames.add(inFile1);
      ioModes.add("input");
      fileNames.add(inFile2);
      ioModes.add("input");
      fileNames.add(outFile);
      ioModes.add("output");
      String inputLine;

      try(LineSequentialTryrRsourceOobjects4 fileStreams = new LineSequentialTryrRsourceOobjects4(fileNames, ioModes)) {
         while((inputLine = fileStreams.read(inFile1)) != null) {
            fileStreams.write(outFile, inputLine);
            inputLine = fileStreams.read(inFile2);
            fileStreams.write(outFile, inputLine);
         }
      }
      System.out.println("File Complete");
    }
}
