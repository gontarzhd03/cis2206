package texteditorproj;

import javafx.print.PrinterJob;
import javafx.scene.text.*;

/**
 *
 * @author hgontarz
 */
public class Printer {
//#########################################################################
//# Print function
//#########################################################################

    static void print(String text) {
        try {
            TextFlow textFlow = new TextFlow(new Text(text));
            PrinterJob job = PrinterJob.createPrinterJob();
            if (job != null) {
                if (job.showPrintDialog(null)) {
                    if (job.printPage(textFlow)) {
                        job.endJob();
                    } else {
                        System.out.println("Failed to print page!");
                    }
                }
            } else {
                System.out.println("Could not create a printer job.");
            }
        } catch (Exception e) {
            System.out.println("Printer exception: " + e.getMessage());
        }
    }
}
