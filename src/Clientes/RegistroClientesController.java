/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clientes;

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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author DAVID
 */
public class RegistroClientesController implements Initializable {

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
    //Declaración de textos
    @FXML
    public TextField txtNit;
    @FXML
    public TextField txtNombre;
    @FXML
    public TextField txtApellido;
    @FXML
    public TextField txtDireccion;
    @FXML
    public TextField txtTelefono;
    
    //Instancia Conexión BD
    ConexionBD conectar = new ConexionBD();
    Connection con=conectar.conexion();
    PreparedStatement preparar;
    
    //VariablesGlobales
    int nit;
    String nombre;
    String apellido;
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
    private void Cerrar(MouseEvent event){
        System.exit(0);
    }
    
    @FXML   
    private void GuardarCliente(ActionEvent event){
        nit=Integer.parseInt(txtNit.getText());
        nombre=txtNombre.getText();
        apellido=txtApellido.getText();
        direccion=txtDireccion.getText();
        telefono=Integer.parseInt(txtTelefono.getText());
        
        //Guardar Registros en BD
        sql="INSERT INTO cliente(nit,nombre,apellido,direccion,telefono) VALUES(?,?,?,?,?)";
        
        try {
            preparar = con.prepareStatement(sql);
            preparar.setInt(1, nit);
            preparar.setString(2, nombre);
            preparar.setString(3, apellido);
            preparar.setString(4, direccion);
            preparar.setInt(5, telefono);
            preparar.executeUpdate();
            
            JOptionPane.showMessageDialog(null,"Registro Guardado");
            
            txtNit.setText("");
            txtNombre.setText("");
            txtApellido.setText("");
            txtDireccion.setText("");
            txtTelefono.setText("");
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaClientesController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Eror, Usuario no registrado");
        }  
    }
    
    @FXML   
    private void NuevoCliente(ActionEvent event){
        txtNit.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        
    }
    
    @FXML
    private void btnConsultar(ActionEvent event) throws IOException{
        node=(Node) event.getSource();
        stage=(Stage) node.getScene().getWindow();
        
        parent=FXMLLoader.load(getClass().getResource("/Clientes/ConsultaClientes.fxml"));
        
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Consulta Clientes");
        stage.show();
        
    }
    
    @FXML
    private void btnModificar(ActionEvent event) throws IOException{
        node=(Node) event.getSource();
        stage=(Stage) node.getScene().getWindow();
        
        parent=FXMLLoader.load(getClass().getResource("/Clientes/ModificarClientes.fxml"));
        
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Modificar Clientes");
        stage.show();
        
    }
    
    @FXML
    private void btnEliminar(ActionEvent event) throws IOException{
        node=(Node) event.getSource();
        stage=(Stage) node.getScene().getWindow();
        
        parent=FXMLLoader.load(getClass().getResource("/Clientes/EliminarClientes.fxml"));
        
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Eliminar Clientes");
        stage.show();
        
    }
}
