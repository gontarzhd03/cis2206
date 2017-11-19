/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multiplewindows;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
/**
 *
 * @author 55gontarhd03
 */
public class MultipleWindows extends Application {
    GridPane gridPane = new GridPane();
    
    @Override
    public void start(Stage primaryStage) {
        String rowDimensionRequest = "Enter Row Dimension";
        String columnDimensionRequest = "Enter Column Dimension";
        
        BorderPane borderPane = new BorderPane();
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(gridPane);
        borderPane.setCenter(scrollPane);
        
        Scene scene = new Scene(borderPane, 400, 400);
        primaryStage.setOnShown(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
               setupRowDimensionsRequest(rowDimensionRequest, columnDimensionRequest);
            }
        });
        
        primaryStage.setTitle("Multiple Windows");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    void setupRowDimensionsRequest(String rowDimensionRequest, String columnDimensionRequest) {
        InputBox inputRowDialog = new InputBox(rowDimensionRequest);
        inputRowDialog.setOnHiding(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
                setupColumnDimensionsRequest(columnDimensionRequest, Integer.parseInt(inputRowDialog.getInputValue()));
            }
        });
    }
    void setupColumnDimensionsRequest(String columnDimensionRequest, int rowDimension) {
        InputBox inputColumnDialog = new InputBox(columnDimensionRequest);
        inputColumnDialog.setOnHiding(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
                setupGrid(rowDimension, Integer.parseInt(inputColumnDialog.getInputValue()));
            }
        });
    }
    void setupGrid(int numberOfRows, int numberOfColumns) {
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
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
