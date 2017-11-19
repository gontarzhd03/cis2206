/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpletableeditor;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 *
 * @author 55gontarhd03
 */
public class SimpleTableEditor extends Application {
    int numberOfRows = 1;
    int numberOfColumns = 1;
    Button inputButton = new Button("Enter");
    TextField inputRowTextField = new TextField(Integer.toString(numberOfRows));
    TextField inputColumnTextField = new TextField(Integer.toString(numberOfColumns));
    GridPane gridPane = new GridPane();
    ScrollPane scrollPane = new ScrollPane();
    BorderPane borderPane = new BorderPane();
    Scene scene = new Scene(borderPane, 400, 400);
    
    @Override
    public void start(Stage primaryStage) {
        String rowDimensionRequest = "Enter Row Dimension";
        String columnDimensionRequest = "Enter Column Dimension";
        
        drawVisualInterface(rowDimensionRequest, columnDimensionRequest);
        attachCode();
        
        primaryStage.setTitle("Simple Table Editor - Untitled");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    void drawVisualInterface(String rowDimensionRequest, String columnDimensionRequest) {
        setupDimensionsRequest(rowDimensionRequest, columnDimensionRequest);
        setupGrid();
        
        scrollPane.setContent(gridPane);
        borderPane.setCenter(scrollPane);
    }
    void setupDimensionsRequest(String rowDimensionRequest, String columnDimensionRequest) {
        VBox dimensionsPanel = new VBox();
        VBox rowSubPanel = new VBox();
        VBox columnSubPanel = new VBox();
        HBox dimensionSubPanel = new HBox();
        HBox enterSubPanel = new HBox();
        
        Label inputRowLabel = new Label(rowDimensionRequest);
        Label inputColumnLabel = new Label(columnDimensionRequest);
        rowSubPanel.getChildren().add(inputRowTextField);
        rowSubPanel.getChildren().add(inputRowLabel);
        
        columnSubPanel.getChildren().add(inputColumnTextField);
        columnSubPanel.getChildren().add(inputColumnLabel);
        
        dimensionSubPanel.getChildren().add(rowSubPanel);
        dimensionSubPanel.getChildren().add(columnSubPanel);
        dimensionSubPanel.setAlignment(Pos.CENTER);
        
        enterSubPanel.setHgrow(inputButton, Priority.ALWAYS);
        inputButton.setMaxWidth(scene.getWidth()/2);
        enterSubPanel.setAlignment(Pos.CENTER);
        enterSubPanel.getChildren().add(inputButton);
        
        dimensionsPanel.getChildren().add(dimensionSubPanel);
        dimensionsPanel.getChildren().add(enterSubPanel);
        borderPane.setTop(dimensionsPanel);
    }
    void setupGrid() {
        char c = 'A';
        
        for(int j = 1; j <= numberOfColumns; j++) {
            Label nextLabel = new Label(Character.toString(c));
            c++;
            nextLabel.setStyle("-fx-label-padding: 0 0 0 60");
            gridPane.add(nextLabel, j, 0);
        }
        for(int i = 1; i <= numberOfRows; i++) {
            gridPane.add(new Label(Integer.toString(i)), 0, i);
            for(int j = 1; j <= numberOfColumns; j++) {
                gridPane.add(new TextField(), j, i);
            }
        }
    }
    void attachCode() {
        inputButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                numberOfRows = Integer.valueOf(inputRowTextField.getText());
                numberOfColumns = Integer.valueOf(inputColumnTextField.getText());
                setupGrid();
                borderPane.setTop(null);
            }
        });
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
