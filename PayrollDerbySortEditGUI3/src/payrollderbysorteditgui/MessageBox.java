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
public class MessageBox extends Stage {
    private boolean buttonChoice = true;

    MessageBox(String Message) {
        drawVisualInterface(Message);
    }

    private void drawVisualInterface(String Message) {
        this.setTitle(Message);
        HBox subPanel = new HBox();
        Scene scene = new Scene(subPanel, 450, 35);

        Label inputLabel = new Label(Message);
        Button commitButton = new Button("Commit");
        Button cancelButton = new Button("Cancel");
        subPanel.getChildren().add(inputLabel);
        subPanel.getChildren().add(commitButton);
        subPanel.getChildren().add(cancelButton);

        commitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                buttonChoice = true;
                hide();
            }
        });

        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                buttonChoice = false;
                hide();
            }
        });
        this.setScene(scene);
        this.show();
    }

    public boolean getButtonChoice() {
        return buttonChoice;
    }
}
