package texteditorproj;

import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 *
 * @author hgontarz
 */
public class StandardMenu {

    static MenuBar CreateStandardMenuBar(TextArea textDocument, Stage aStage) {
        MenuBar standardMenuBar = new MenuBar();

        standardMenuBar.getMenus().addAll(FileMenu.CreateFileMenu(textDocument, aStage));
        standardMenuBar.getMenus().addAll(EditMenu.CreateEditMenu(textDocument));
        return standardMenuBar;
    }
}
