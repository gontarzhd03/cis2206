/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileinterleavetryresources;
import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author hgontarz
 */
public class LineSequentialTryResources {
   /**
   *
   * @param inFile file name of file on disk to read from
   * @return inputLines array list of strings read from file
   */
   static ArrayList <String> readAll(String inFile) {
      String inputLine = "";

       ArrayList <String> inputLines = new ArrayList <String> ();
       try (BufferedReader readFile = new BufferedReader(new FileReader(inFile))) {
          while((inputLine = readFile.readLine()) != null) {
             inputLines.add(inputLine);
          }
       }
       catch(IOException e) {
           System.out.println("IO Error" + e.getMessage());
       }
       return inputLines;
   }

   /**
   *
   * @param outFile file name of file on disk to write to
   * @param outputLines array list of strings to be written
   */
   static void writeall(String outFile, ArrayList <String> outputLines) {
      try (PrintWriter writeFile = new PrintWriter(new BufferedWriter(new FileWriter(outFile)))) {
         for(int i = 0; i < outputLines.size(); i++) {
            writeFile.println(outputLines.get(i));
         }
      }
      catch(IOException e) {
         System.out.println("IO Error" + e.getMessage());
      }
   }
}
