/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;




import java.sql.Connection;
import java.sql.DriverManager;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author DAVID
 */
public class ConexionBD extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
    }
    
    Connection conectar = null;
    public Connection conexion(){
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conectar = DriverManager.getConnection("jdbc:mysql://25.75.85.180:3306/bd_hardsoftware","chavajay","chavajay");

        } catch (Exception e) {
            System.out.println(e);
        }
        return conectar;
    }
    
}
