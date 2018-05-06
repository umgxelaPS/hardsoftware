/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proveedores;

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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author DAVID
 */
public class EliminarProveedoresController implements Initializable {

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private TextField txtId;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtDireccion;

    @FXML
    private TextField txtTelefono;

    @FXML
    private Button btnLimpiar;

    @FXML
    private Button btnConsultar;

    @FXML
    private Button btnEliminar;
    
    @FXML
    private Button btnVolver;

    @FXML
    private Label Cerrar;
    
    //Instancia Conexión BD
    ConexionBD conectar = new ConexionBD();
    Connection con=conectar.conexion();
    PreparedStatement preparar;
    ResultSet result;
    
    //VariablesGlobales
    int id;
    String nombre;
    String direccion;
    int telefono;
    
    //Sentencia SQL
    String consultar;
    
    //Variables para Cambio de escenas
    Node node;
    Stage stage;
    Parent parent;
    Scene root;

    
    @FXML
    void Cerrar(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    void Limpiar(ActionEvent event) {
        txtId.setText("");
        txtNombre.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
    }

    @FXML
    void btnConsultar(ActionEvent event) {
        con=conectar.conexion();
        
        consultar="SELECT nombre,direccion,telefono FROM proveedor WHERE id_proveedor='".concat(txtId.getText()).concat("'");
        
        try {
            preparar=con.prepareStatement(consultar);
            result=preparar.executeQuery();
            
            while(result.next()){
                txtNombre.setText(result.getString(1));
                txtDireccion.setText(result.getString(2));
                txtTelefono.setText(result.getString(3));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EliminarProveedoresController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Registro no encontrado", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    @FXML
    void btnEliminar(ActionEvent event){
        con=conectar.conexion();
        
        consultar="DELETE FROM proveedor WHERE id_proveedor='".concat(txtId.getText()).concat("'");
        
        try {
            preparar=con.prepareStatement(consultar);
            preparar.executeUpdate();
            JOptionPane.showMessageDialog(null,"Registro Eliminado");
            
            txtId.setText("");
            txtNombre.setText("");
            txtDireccion.setText("");
            txtTelefono.setText("");
            
        } catch (SQLException ex) {
            Logger.getLogger(EliminarProveedoresController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Registro no encontrado", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    @FXML
    void btnVolver(ActionEvent event) throws IOException {
        node=(Node) event.getSource();
        stage=(Stage) node.getScene().getWindow();
        
        parent=FXMLLoader.load(getClass().getResource("/Proveedores/RegistroProveedores.fxml"));
        
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Registro Proveedores");
        stage.show();
    }
    
    
}
