/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author wtfmi
 */
public class Sortings extends Application {
    SortingsController my_controller;
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SortingsView.fxml"));
        Parent root = loader.load();

        my_controller = loader.getController();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        //stage.setResizable(false);
        stage.show();
        my_controller.setData();

    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
