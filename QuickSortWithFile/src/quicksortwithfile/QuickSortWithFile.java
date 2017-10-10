package quicksortwithfile;
import dao.LineSequential;

/**
 *
 * @author hgontarz
 */
public class QuickSortWithFile {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int first = 0;
        int last = 999999;
        double[] x = new double[last + 1];
        String inputFileName = "./data/UniformDeviate.txt";
        String inputStream = "UniformDeviate";
        String outputFileName = "./data/UniformSorted.txt";
        String outputStream = "UniformSorted";

        readFromFile(x, inputFileName, inputStream);
        QuickSortClass.quickSort(x, first, last);
        writeToFile(x, outputFileName, outputStream);
    }
    
/**
 * 
 * @param x
 * @param inputFileName
 * @param inputStream 
 */
    static void readFromFile(double[] x, String inputFileName, String inputStream) {
        LineSequential.open(inputFileName, inputStream, "input");
        String inputLine = new String();
        int observationNumber = 0;
        while((inputLine = LineSequential.read(inputStream)) != null) {
            x[observationNumber] = Double.parseDouble(inputLine);
            observationNumber++;
        }
        LineSequential.close(inputStream, "input");
    }
    
/**
 * 
 * @param x
 * @param outputFileName
 * @param outputStream 
 */
    static void writeToFile(double[] x, String outputFileName, String outputStream) {
        LineSequential.open(outputFileName, outputStream, "output");
        for(int i = 1; i < x.length; i++) {
            LineSequential.write(outputStream, Double.toString(x[i]));
        }
        LineSequential.close(outputStream, "output");
    }
}
