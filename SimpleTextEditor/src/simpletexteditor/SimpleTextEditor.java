/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpletexteditor;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 *
 * @author 55gontarhd03
 */
public class SimpleTextEditor extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        TextArea textDocument = new TextArea();
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(new Label("Menu"));
        borderPane.setCenter(textDocument);
        Scene scene = new Scene(borderPane, 400, 400);
        
        primaryStage.setTitle("Text Editor - Untitled");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
