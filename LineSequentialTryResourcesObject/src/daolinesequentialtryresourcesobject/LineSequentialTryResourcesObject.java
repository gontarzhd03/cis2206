/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daolinesequentialtryresourcesobject;
import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author 55gontarhd03
 */
public class LineSequentialTryResourcesObject implements AutoCloseable {
    private BufferedReader fileIn;
    private PrintWriter fileOut;
    private String ioMode;
    /**
     *
     * @param inFile  file name of file on disk to read from
     * @return inputLines  array list of strings read from file
     */
    public LineSequentialTryResourcesObject(String ioFile, String ioMode)throws IOException{
        if(ioMode == "input"){
            fileIn = new BufferedReader(new FileReader(ioFile));
        }
        else{
            fileOut = new PrintWriter(
					new BufferedWriter(
						new FileWriter(ioFile)));
        }
        this.ioMode = ioMode;
    }

    public String read()throws IOException{
        return this.fileIn.readLine();  
    }
    
    public void write(String outputLine)throws IOException{
        this.fileOut.println(outputLine);  
    }

    public ArrayList <String> readAll()throws IOException{
        String inputLine = "";
        ArrayList <String> inputLines = new  ArrayList <String> ();
            while((inputLine = this.fileIn.readLine()) != null){
                inputLines.add(inputLine);
            }
        return inputLines;
    }

    /**
     *
     * @param outFile  file name of file on disk to write to
     * @param outputLines  array list of strings to be written
     */

    public void writeall(ArrayList <String> outputLines){
        for(int i = 0; i < outputLines.size(); i++){
            this.fileOut.println(outputLines.get(i));
        }
    }
    
    public void close()throws IOException{
        if(this.ioMode == "input"){
            this.fileIn.close();
        }
        else{
            this.fileOut.close();
        }
    } 
}
