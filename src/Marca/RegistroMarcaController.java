/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Marca;

import Clientes.*;
import BD.ConexionBD;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author DAVID
 */
public class RegistroMarcaController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    
    //Declaración de Botones
    @FXML  
    public Button btnGuardar;
    @FXML  
    public Button btnNuevo;
    @FXML  
    public Button btnConsultar;
    @FXML  
    public Button btnModificar;
    @FXML  
    public Button btnEliminar;
    @FXML  
    public Button btnVolver;
    //Declaración de textos
    @FXML
    public TextField txtID;
    @FXML
    public TextField txtNombre;

    
    //Instancia Conexión BD
    ConexionBD conectar = new ConexionBD();
    Connection con=conectar.conexion();
    PreparedStatement preparar;
    
    //VariablesGlobales
    int id;
    String nombre;

    
    //Sentencia SQL
    String sql;
    
    //Variables para Cambio de escenas
    Node node;
    Stage stage;
    Parent parent;
    Scene root;
    
    
    @FXML   
    private void GuardarMarca(ActionEvent event){
        id=Integer.parseInt(txtID.getText());
        nombre=txtNombre.getText();
        
        //Guardar Registros en BD
        sql="INSERT INTO marca(id_marca,nombre) VALUES(?,?)";
        
        try {
            preparar = con.prepareStatement(sql);
            preparar.setInt(1, id);
            preparar.setString(2, nombre);
            preparar.executeUpdate();
            
            JOptionPane.showMessageDialog(null,"Registro Guardado");
            
            txtID.setText("");
            txtNombre.setText("");
        } catch (SQLException ex) {
            Logger.getLogger(RegistroMarcaController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Error al guardar el registro");
        }  
    }
    
    @FXML   
    private void NuevaMarca(ActionEvent event){
        txtID.setText("");
        txtNombre.setText("");
        
    }
    
    @FXML
    private void btnConsultar(ActionEvent event) throws IOException{
        node=(Node) event.getSource();
        stage=(Stage) node.getScene().getWindow();
        
        parent=FXMLLoader.load(getClass().getResource("/Marca/ConsultaMarca.fxml"));
        
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Consulta Marca");
        stage.show();
        
    }
    
    @FXML
    private void btnModificar(ActionEvent event) throws IOException{
        node=(Node) event.getSource();
        stage=(Stage) node.getScene().getWindow();
        
        parent=FXMLLoader.load(getClass().getResource("/Marca/ModificarMarca.fxml"));
        
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Modificar Marca");
        stage.show();
        
    }
    
    @FXML
    private void btnEliminar(ActionEvent event) throws IOException{
        node=(Node) event.getSource();
        stage=(Stage) node.getScene().getWindow();
        
        parent=FXMLLoader.load(getClass().getResource("/Marca/EliminarMarca.fxml"));
        
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Eliminar Marca");
        stage.show();
        
    }
    
    @FXML
    private void btnVolver(ActionEvent event) throws IOException{
        node=(Node) event.getSource();
        stage=(Stage) node.getScene().getWindow();
        
        parent=FXMLLoader.load(getClass().getResource("/Dashboard/Dashboard.fxml"));
        
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Dashboard");
        stage.show();
        
    }
}
