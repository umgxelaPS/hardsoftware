/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Usuario;

import Clientes.*;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DAVID
 */
public class UsuariosController implements Initializable {
    
    //Declaración de Botones
    @FXML
    private Button volver;
    @FXML
    private Button nuevo;
    @FXML
    private Button guardar;
    @FXML
    private Button editar;
    @FXML
    private Button eliminar;
    @FXML
    private Button buscar;
    

    //Variables para Cambio de escenas
    Node node;
    Stage stage;
    Parent parent;
    Scene root; 
    
    @FXML
    private void btnVolver(ActionEvent event) throws IOException{
        node=(Node) event.getSource();
        stage=(Stage) node.getScene().getWindow();
        
        parent=FXMLLoader.load(getClass().getResource("/Loggin/F_Loggin.fxml"));
        
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Registro Clientes");
        stage.show();
        
    }
    
    @FXML   
    private void Cerrar(MouseEvent event){
        System.exit(0);
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
}
