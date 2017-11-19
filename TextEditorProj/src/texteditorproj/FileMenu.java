package texteditorproj;

import dao.LineSequential;
import java.io.File;
import javafx.event.*;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.FileChooser;

/**
 *
 * @author hgontarz
 */
public class FileMenu {

    static File file = null;

    static Menu CreateFileMenu(TextArea textDocument, Stage aStage) {
        final Menu fileMenu = new Menu("File");

        MenuItem clear = new MenuItem("New");
        MenuItem open = new MenuItem("Open");
        MenuItem save = new MenuItem("Save");
        MenuItem saveAs = new MenuItem("Save As");
        MenuItem close = new MenuItem("Close");
        MenuItem print = new MenuItem("Print");

        fileMenu.getItems().add(clear);
        fileMenu.getItems().add(open);
        fileMenu.getItems().add(save);
        fileMenu.getItems().add(saveAs);
        fileMenu.getItems().add(close);
        fileMenu.getItems().add(print);

        clear.setOnAction((ActionEvent e) -> {
            aStage.setTitle("Text Editor - Untitled");
            textDocument.clear();
            file = null;
        });

        open.setOnAction((ActionEvent e) -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text File", "*.txt"));
            file = fileChooser.showOpenDialog(aStage);
            if (file != null) {
                textDocument.clear();
                String inputLine;
                String fileStream = file.getName();
                aStage.setTitle("Text Editor - " + fileStream);
                LineSequential.open(file.getAbsolutePath(), fileStream, "input");
                while ((inputLine = LineSequential.read(fileStream)) != null) {
                    textDocument.appendText(inputLine + "\n");
                }
                LineSequential.close(fileStream, "input");
            }
        });

        save.setOnAction((ActionEvent e) -> {
            if (file != null) {
                saveOperation(textDocument, aStage);
            } else {
                saveAsOperation(textDocument, aStage);
            }
        });

        saveAs.setOnAction((ActionEvent e) -> {
            saveAsOperation(textDocument, aStage);
        });

        close.setOnAction((ActionEvent e) -> {
            aStage.setTitle("Text Editor — Untitled");
            textDocument.clear();
            file = null;
        });
        print.setOnAction((ActionEvent e) -> {
            Printer.print(textDocument.getText());
        });
        return fileMenu;
    }

    static void saveAsOperation(TextArea textDocument, Stage primaryStage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text File", "*.txt"));
        file = fileChooser.showSaveDialog(primaryStage);
        if (file != null) {
            saveOperation(textDocument, primaryStage);
        }
    }

    static void saveOperation(TextArea textDocument, Stage primaryStage) {
        String fileStream = file.getName();
        primaryStage.setTitle("Text Editor - " + fileStream);
        LineSequential.open(file.getAbsolutePath(), fileStream, "output");
        LineSequential.write(fileStream, textDocument.getText());
        LineSequential.close(fileStream, "output");
    }
}
