/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Loggin;

import hards_fx.HardS_FX;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DAVID
 */
public class LogginController implements Initializable {

    @FXML
    private Button cerrar;
    @FXML
    private Button usuario;
    @FXML
    private Button cliente;
    
    @FXML   
    private void Cerrar(ActionEvent event){
       System.exit(0);
    }
    
    //Variables para Cambio de escenas
    Node node;
    Stage stage;
    Parent parent;
    Scene root;
    
    @FXML
    private void btnUsuario(ActionEvent event) throws IOException{
        node=(Node) event.getSource();
        stage=(Stage) node.getScene().getWindow();
        
        parent=FXMLLoader.load(getClass().getResource("/Usuario/F_Usuarios.fxml"));
        
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Registro Usuarios");
        stage.show();
        
    }
    
    @FXML
    private void btnCliente(ActionEvent event) throws IOException{
        node=(Node) event.getSource();
        stage=(Stage) node.getScene().getWindow();
        
        parent=FXMLLoader.load(getClass().getResource("/Dashboard/Dashboard.fxml"));
        
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Dashboard");
        stage.show();
        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
}
