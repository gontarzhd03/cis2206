/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileinterleavetryresourcesobject4;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author hgontarz
 */
public class LineSequentialTryrRsourceOobjects4 implements AutoCloseable {
   private static ArrayList <LineSequentialTryResourcesObject4> readFiles = new ArrayList <LineSequentialTryResourcesObject4> ();
   private static ArrayList <String> readFileNames = new ArrayList <String> ();
   private static ArrayList <LineSequentialTryResourcesObject4> writeFiles = new ArrayList <LineSequentialTryResourcesObject4> ();
   private static ArrayList <String> writeFileNames = new ArrayList <String> ();

   public LineSequentialTryrRsourceOobjects4(ArrayList <String> fileNames, ArrayList <String> ioModes) {
      for(int i = 0; i < fileNames.size(); i++) {
         if(ioModes.get(i) == "input") {
            readFiles.add(new LineSequentialTryResourcesObject4(fileNames.get(i), "input"));
            readFileNames.add(fileNames.get(i));
         }
         else {
            writeFiles.add(new LineSequentialTryResourcesObject4(fileNames.get(i), "output"));
            writeFileNames.add(fileNames.get(i));
         }
      }
   }

   public String read(String readFileName) {
      return (readFiles.get(readFileNames.indexOf(readFileName))).read();
   }

   public void write(String writeFileName, String outputLine) {
      (writeFiles.get(writeFileNames.indexOf(writeFileName))).write(outputLine);
   }

   public void close() {
      for(int i = 0; i < readFiles.size(); i++) {
         readFiles.get(i).close();
      }
      for(int i = 0; i < writeFiles.size(); i++) {
         writeFiles.get(i).close();
      }
   }
}
