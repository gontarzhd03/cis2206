/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multiplewindows;

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
public class InputBox extends Stage {
    private String inputValue;

    InputBox(String dimensionRequest) {
        this.setTitle(dimensionRequest);
        HBox subPanel = new HBox();
        Scene scene = new Scene(subPanel, 350, 30);
        
        Label inputLabel = new Label(dimensionRequest);
        TextField inputTextField = new TextField("2");
        Button inputButton = new Button("Enter");
        
        subPanel.getChildren().add(inputLabel);
        subPanel.getChildren().add(inputTextField);
        subPanel.getChildren().add(inputButton);
        
        inputButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                inputValue = inputTextField.getText();
                hide();
            }
        });
        this.setScene(scene);
        this.show();
    }
    
    public String getInputValue() {
        return inputValue;
    }
}
