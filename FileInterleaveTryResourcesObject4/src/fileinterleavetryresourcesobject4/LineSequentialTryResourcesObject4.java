/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileinterleavetryresourcesobject4;
import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author hgontarz
 */
public class LineSequentialTryResourcesObject4 {
   private BufferedReader fileIn;
   private PrintWriter fileOut;
   private String ioMode;
   /**
   *
   * @param inFile file name of file on disk to read from
   * @return inputLines array list of strings read from file
   */
   protected LineSequentialTryResourcesObject4(String ioFile, String ioMode) {
      try {
         if(ioMode == "input") {
            fileIn = new BufferedReader(new FileReader(ioFile));
         }
         else {
            fileOut = new PrintWriter(new BufferedWriter(new FileWriter(ioFile)));
         }
         this.ioMode = ioMode;
      }
      catch(IOException e) {
         System.out.println("10 Error" + e.getMessage());
      }
   }

   protected String read() {
      String inputLine = "";

      try {
         inputLine = this.fileIn.readLine();
      }
      catch(IOException e) {
         System.out.println("IO Error" + e.getMessage());
      }
      return inputLine;
   }

   protected void write(String outputLine) {
      this.fileOut.println(outputLine);
   }

   protected ArrayList <String> readAll() {
      String inputLine = "";

      ArrayList <String> inputLines = new ArrayList <String> ();
      try {
         while((inputLine = this.fileIn.readLine()) != null) {
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
   protected void writeall(ArrayList <String> outputLines) {
      for(int i = 0; i < outputLines.size(); i++) {
         this.fileOut.println(outputLines.get(i));
      }
   }

   protected void close() {
      try {
         if(this.ioMode == "input") {
            this.fileIn.close();
         }
         else {
            this.fileOut.close();
         }
      }
      catch(IOException e){
         System.out.println("IO Error" + e.getMessage());
      }
   }
}
