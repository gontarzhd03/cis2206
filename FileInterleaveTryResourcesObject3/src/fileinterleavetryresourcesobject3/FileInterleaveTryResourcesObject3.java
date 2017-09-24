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
public class FileInterleaveTryResourcesObject3 {
   static void interleaveFiles(String inFile1, String inFile2, String outFile) {
      try(
         LineSequentialTryResourcesObject3 inFileStream1 = new LineSequentialTryResourcesObject3(inFile1, "input");
         LineSequentialTryResourcesObject3 inFileStream2 = new LineSequentialTryResourcesObject3(inFile2, "input");
         LineSequentialTryResourcesObject3 outFileStream = new LineSequentialTryResourcesObject3(outFile, "output");
      ) {
         readWrite(inFileStream1, inFileStream2, outFileStream);
      }
   }

   static void readWrite(LineSequentialTryResourcesObject3 inFileStream1, LineSequentialTryResourcesObject3 inFileStream2, LineSequentialTryResourcesObject3 outFileStream) {
      String inputLine;

      while((inputLine = inFileStream1.read()) != null) {
         outFileStream.write(inputLine);
         inputLine = inFileStream2.read();
         outFileStream.write(inputLine);
      }
   }
}
