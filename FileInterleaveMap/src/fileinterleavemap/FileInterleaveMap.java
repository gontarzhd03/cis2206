package fileinterleavemap;

/**
 *
 * @author hgontarz
 */
public class FileInterleaveMap {

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
        
        try(LineSequentialMap lineSequentialMap2 = new LineSequentialMap();) {
            lineSequentialMap2.add(inFile1, inFileStreamName1, "input");
            lineSequentialMap2.add(inFile2, inFileStreamName2, "input");
            lineSequentialMap2.add(outFile, outFileStreamName, "output");

            while((inputLine = lineSequentialMap2.read(inFileStreamName1)) != null) {
                lineSequentialMap2.write(outFileStreamName, inputLine);
                inputLine = lineSequentialMap2.read(inFileStreamName2);
                lineSequentialMap2.write(outFileStreamName, inputLine);
            }
        }
        System.out.println("File Complete");
    }
    
}
