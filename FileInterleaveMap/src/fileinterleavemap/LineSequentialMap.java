package fileinterleavemap;

import java.io.*;
import java.util.TreeMap;

/**
 *
 * @author hgontarz
 */
public class LineSequentialMap implements AutoCloseable {
    private static TreeMap <String, BufferedReader> readFilesMap = new TreeMap<String, BufferedReader> ();
    private static TreeMap <String, PrintWriter> writeFilesMap = new TreeMap<String, PrintWriter> ();
    
/**
 * 
 * @param fileName
 * @param fileStream
 * @param ioMode 
 */
    public void add(String fileName, String fileStream, String ioMode) {
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
    public String read(String readFileStream) {
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
    public void write(String writeFileStream, String outputLine) {
       writeFilesMap.get(writeFileStream).println(outputLine);
    }
   
/**
 * 
 */
    public void close() {
        try {
            String key = readFilesMap.firstKey();
            (readFilesMap.get(key)).close();
            for(int i = 1; i < readFilesMap.size(); i++) {
                String nextKey = readFilesMap.higherKey(key);
                (readFilesMap.get(nextKey)).close();
                key = nextKey;
            }
            key = writeFilesMap.firstKey();
            (writeFilesMap.get(key)).close();
            for(int i = 1; i < writeFilesMap.size(); i++) {
                String nextKey = writeFilesMap.higherKey(key);
                (writeFilesMap.get(nextKey)).close();
                key = nextKey;
            }
        }
        catch(IOException e) {
            System.out.println("IO Error" + e.getMessage());
        }
    }
}
