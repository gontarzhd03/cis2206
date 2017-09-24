/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileinterleavetryresourcesobject3;

/**
 *
 * @author hgontarz
 */
public class FileInterleaveTryResourcesObjectDriver3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      String inFile1 = "./data/PayrollMasterA.dat";
      String inFile2 = "./data/PayrollMasterB.dat";
      String outFile = "./data/PayrollMaster.dat";
      
      FileInterleaveTryResourcesObject3.interleaveFiles(inFile1, inFile2, outFile);
      System.out.println("File Complete");
    }
    
}
