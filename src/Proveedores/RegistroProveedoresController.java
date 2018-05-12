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
public class RegistroProveedoresController implements Initializable {

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

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
    private Button btnGuardar;

    @FXML
    private Button btnConsultar;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnEliminar;
    
    @FXML  
    public Button btnVolver;

    
    //Instancia Conexión BD
    ConexionBD conectar = new ConexionBD();
    Connection con=conectar.conexion();
    PreparedStatement preparar;
    
    //VariablesGlobales
    int id;
    String nombre;
    String direccion;
    int telefono;
    
    //Sentencia SQL
    String sql;
    
    //Variables para Cambio de escenas
    Node node;
    Stage stage;
    Parent parent;
    Scene root;


    @FXML
    void Limpiar(ActionEvent event) {
        txtId.setText("");
        txtNombre.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
    }

    @FXML
    void btnConsultar(ActionEvent event) throws IOException {
        node=(Node) event.getSource();
        stage=(Stage) node.getScene().getWindow();
        
        parent=FXMLLoader.load(getClass().getResource("/Proveedores/ConsultaProveedores.fxml"));
        
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Consulta Proveedores");
        stage.show();
    }

    @FXML
    void btnEditar(ActionEvent event) throws IOException {
        node=(Node) event.getSource();
        stage=(Stage) node.getScene().getWindow();
        
        parent=FXMLLoader.load(getClass().getResource("/Proveedores/ModificarProveedores.fxml"));
        
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Modificar Proveedores");
        stage.show();

    }

    @FXML
    void btnEliminar(ActionEvent event) throws IOException {
        node=(Node) event.getSource();
        stage=(Stage) node.getScene().getWindow();
        
        parent=FXMLLoader.load(getClass().getResource("/Proveedores/EliminarProveedores.fxml"));
        
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Eliminar Proveedores");
        stage.show();
    }

    @FXML
    void btnGuardar(ActionEvent event) {
        id=Integer.parseInt(txtId.getText());
        nombre=txtNombre.getText();
        direccion=txtDireccion.getText();
        telefono=Integer.parseInt(txtTelefono.getText());
        
        //Guardar Registros en BD
        sql="INSERT INTO proveedor(id_proveedor,nombre,direccion,telefono) VALUES(?,?,?,?)";
        
        try {
            preparar = con.prepareStatement(sql);
            preparar.setInt(1, id);
            preparar.setString(2, nombre);
            preparar.setString(3, direccion);
            preparar.setInt(4, telefono);
            preparar.executeUpdate();
            
            JOptionPane.showMessageDialog(null,"Registro Guardado");
            
            txtId.setText("");
            txtNombre.setText("");
            txtDireccion.setText("");
            txtTelefono.setText("");
        } catch (SQLException ex) {
            Logger.getLogger(RegistroProveedoresController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Eror, Usuario no registrado");
        }  

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
