package fileinterleavemap3;

/**
 *
 * @author hgontarz
 */
public class FileInterleaveMap3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String inFile1 = "./data/PayrollMasterA.dat";
        String inFile2 = "./data/PayrollMasterB.dat";
        String outFile = "./data/PayrollMaster.dat";
        String inFileStreamName1 = "PayrollMasterA.dat";
        String inFileStreamName2 = "PayrollMasterB.dat";
        String outFileStreamName = "PayrollMaster.dat";
        String inputLine = "";
        
        LineSequentialMap3.open(inFile1, inFileStreamName1, "input");
        LineSequentialMap3.open(inFile2, inFileStreamName2, "input");
        LineSequentialMap3.open(outFile, outFileStreamName, "output");
        
        while((inputLine = LineSequentialMap3.read(inFileStreamName1)) != null) {
            LineSequentialMap3.write(outFileStreamName, inputLine);
            inputLine = LineSequentialMap3.read(inFileStreamName2);
            LineSequentialMap3.write(outFileStreamName, inputLine);
        }
        
        LineSequentialMap3.close(inFileStreamName1, "input");
        LineSequentialMap3.close(inFileStreamName2, "input");
        LineSequentialMap3.close(outFileStreamName, "output");
        System.out.println("File Complete");
    }
}
