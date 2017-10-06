package fileinterleavemap3;

import java.io.*;
import java.util.TreeMap;

/**
 *
 * @author hgontarz
 */
public class LineSequentialMap3 {
    private static TreeMap <String, BufferedReader> readFilesMap = new TreeMap<String, BufferedReader> ();
    private static TreeMap <String, PrintWriter> writeFilesMap = new TreeMap<String, PrintWriter> ();
    
/**
 * 
 * @param fileName
 * @param fileStream
 * @param ioMode 
 */
    public static void open(String fileName, String fileStream, String ioMode) {
        try {
            if(ioMode == "input") {
               readFilesMap.put(fileStream, new BufferedReader(new FileReader(fileName)));
            }
            else {
               writeFilesMap.put(fileStream, new PrintWriter(new BufferedWriter(new FileWriter(fileName))));
            }
        }
        catch(IOException e) {
           System.out.println("IO Error" + e.getMessage());
        }
    }

/**
 * 
 * @param readFileStream
 * @return 
 */
    public static String read(String readFileStream) {
        String inputLine = "";

        try {
           inputLine = readFilesMap.get(readFileStream).readLine();
        }
        catch(IOException e) {
           System.out.println("IO Error" + e.getMessage());
        }
        return inputLine;
    }

/**
 * 
 * @param writeFileStream
 * @param outputLine 
 */
    public static void write(String writeFileStream, String outputLine) {
       writeFilesMap.get(writeFileStream).println(outputLine);
    }
/**
 * 
 * @param fileStream
 * @param ioMode 
 */
    public static void close(String fileStream, String ioMode) {
        try {
            if(ioMode == "input") {
               readFilesMap.get(fileStream).close();
            }
            else {
               writeFilesMap.get(fileStream).close();
            }
        }
        catch(IOException e) {
           System.out.println("IO Error" + e.getMessage());
        }
    }
}
