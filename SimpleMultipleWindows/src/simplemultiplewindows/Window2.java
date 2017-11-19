/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplemultiplewindows;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 *
 * @author 55gontarhd03
 */
public class Window2 extends Stage {
    private String inputValue;
    
    Window2() {
        TextArea textDocument = new TextArea();
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(new Label("Menu"));
        borderPane.setCenter(textDocument);
        Scene scene = new Scene(borderPane, 500, 300);
        textDocument.setOnKeyReleased(new EventHandler<KeyEvent>() {
           @Override
           public void handle(KeyEvent event) {
               inputValue = textDocument.getText();
           }
        });
        this.setTitle("Window2");
        this.setScene(scene);
        this.show();
    }
    
    public String getInputValue() {
        return inputValue;
    }
}
