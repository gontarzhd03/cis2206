package texteditorproj;

import javafx.event.*;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

/**
 *
 * @author hgontarz
 */
public class EditMenu {

    static Menu CreateEditMenu(TextArea textDocument) {
        final Menu editMenu = new Menu("Edit");

        MenuItem copy = new MenuItem("Copy");
        MenuItem paste = new MenuItem("Paste");

        editMenu.getItems().add(copy);
        editMenu.getItems().add(paste);

        copy.setOnAction((ActionEvent e) -> {
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent c1 = new ClipboardContent();
            if (textDocument.isFocused()) {
                String textString = textDocument.getSelectedText();
                if (textString != null) {
                    c1.putString(textString);
                    clipboard.setContent(c1);
                }
            }
        });

        paste.setOnAction((ActionEvent e) -> {
            Clipboard clipboard = Clipboard.getSystemClipboard();
            if (clipboard.hasString()) {
                textDocument.appendText(clipboard.getString());
            }
        });
        return editMenu;
    }
}
