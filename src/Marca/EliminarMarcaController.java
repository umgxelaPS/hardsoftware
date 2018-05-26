/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Marca;

import BD.ConexionBD;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
public class EliminarMarcaController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    
    //Declaración de Botones
    @FXML  
    public Button btnNuevo;
    @FXML  
    public Button btnConsultar;
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
    ResultSet result;
    
    //VariablesGlobales
    int id;
    String nombre;

    
    //Sentencia SQL
    String consultar;
    
    //Variables para Cambio de escenas
    Node node;
    Stage stage;
    Parent parent;
    Scene root;
    
    
    
    
    @FXML   
    private void NuevaConsulta(ActionEvent event){
        txtID.setText("");
        txtNombre.setText("");
        
    }
    
    @FXML
    private void btnConsultar(ActionEvent event){
        con=conectar.conexion();
        
        
        consultar="SELECT nombre FROM marca WHERE id_marca='".concat(txtID.getText()).concat("'");
        
        try {
            preparar=con.prepareStatement(consultar);
            result=preparar.executeQuery();
            
            while(result.next()){
                txtNombre.setText(result.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EliminarMarcaController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Registro no encontrado", "Error", JOptionPane.WARNING_MESSAGE);
        }
        
    }
    
    @FXML
    private void btnEliminar(ActionEvent event){
        con=conectar.conexion();
        
        consultar="DELETE FROM marca WHERE id_marca='".concat(txtID.getText()).concat("'");
        
        try {
            preparar=con.prepareStatement(consultar);
            preparar.executeUpdate();
            JOptionPane.showMessageDialog(null,"Registro Eliminado");
            
            txtID.setText("");
            txtNombre.setText("");
            
        } catch (SQLException ex) {
            Logger.getLogger(EliminarMarcaController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Error al eliminar el registro");
        }
    }
    
    @FXML
    private void btnVolver(ActionEvent event) throws IOException{
        node=(Node) event.getSource();
        stage=(Stage) node.getScene().getWindow();
        
        parent=FXMLLoader.load(getClass().getResource("/Marca/RegistroMarca.fxml"));
        
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Registro Marca");
        stage.show();
        
    }
}
