/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package texteditor;

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
