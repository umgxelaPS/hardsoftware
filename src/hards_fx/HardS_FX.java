/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hards_fx;

import Clientes.RegistroClientesController;
import Loggin.LogginController;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author DAVID
 */
public class HardS_FX extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        //Quitar Login
        Parent root = FXMLLoader.load(getClass().getResource("/Dashboard/Dashboard.fxml"));
        
        stage.initStyle(StageStyle.TRANSPARENT);
        Scene scene = new Scene(root);
        stage.setTitle("Loggin");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    
    public static void main(String[] args) {
        launch(args);
    }
    
}
