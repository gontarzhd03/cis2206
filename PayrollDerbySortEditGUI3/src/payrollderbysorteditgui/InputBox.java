/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package payrollderbysorteditgui;

import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 *
 * @author hgontarz
 */
public class InputBox extends Stage{
    private String inputValue;

    InputBox(String dimensionRequest) {
        drawVisualInterface(dimensionRequest);
    }

    private void drawVisualInterface(String dimensionRequest) {
        this.setTitle(dimensionRequest);
        HBox subPanel = new HBox();
        Scene scene = new Scene(subPanel, 500, 35);

        Label inputLabel = new Label(dimensionRequest);
        TextField inputTextField = new TextField("2");
        Button inputButton = new Button("Enter");
        Button cancelButton = new Button("Cancel");
        subPanel.getChildren().add(inputLabel);
        subPanel.getChildren().add(inputTextField);
        subPanel.getChildren().add(inputButton);
        subPanel.getChildren().add(cancelButton);

        inputButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                inputValue = inputTextField.getText();
                hide();
            }
        });
        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
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
