/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbb_csv_editor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 *
 * @author McKay
 */
public class CBB_CSV_editor extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        FileChooser fileChooser = new FileChooser();
        
        //Creating a GridPane container
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);
        
        //Defining the File button
        Button browse = new Button("Choose File");
        browse.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
                fileChooser.setTitle("Open CSV file");
                fileChooser.getExtensionFilters().add(
                        new ExtensionFilter("CSV File", "*.csv"));
                File selectedFile = fileChooser.showOpenDialog(primaryStage);
                
                //Defining the Submit button
                Button submit = new Button("Submit");
                submit.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                            CBBFile file = new CBBFile(selectedFile);
                            file.cleanFile();
                    }
                });
                GridPane.setConstraints(submit, 1, 0);
                grid.getChildren().add(submit);
           
            }
        });
        GridPane.setConstraints(browse, 0, 1);
        grid.getChildren().add(browse);
        
        
        
        Scene scene = new Scene(grid, 300, 100);
        
        primaryStage.setTitle("CBB CSV Editor");
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
