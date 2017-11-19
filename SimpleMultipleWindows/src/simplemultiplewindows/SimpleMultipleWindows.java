/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplemultiplewindows;

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
public class SimpleMultipleWindows extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        TextArea textDocument = new TextArea();
        Button inputButton = new Button("Enter");
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(new Label("Menu"));
        borderPane.setCenter(textDocument);
        borderPane.setBottom(inputButton);
        Scene scene = new Scene(borderPane, 400, 400);
        Window2 window2 = new Window2();

        inputButton.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                textDocument.setText(window2.getInputValue());
            }
        });
        
        primaryStage.setTitle("Multiple Windows");
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
