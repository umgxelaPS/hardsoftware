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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author DAVID
 */
public class ConsultaClientesController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML   
    private void Cerrar(MouseEvent event){
        System.exit(0);
    }
    
    //Declaración de Botones
    @FXML  
    public Button btnConsulta;
    @FXML  
    public Button btnNuevo;
    @FXML  
    public Button btnVolver;
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
    ResultSet result;
    //String para la consulta
    String consultar;
    //VariablesGlobales
    int nit;
    String nombre;
    String apellido;
    String direccion;
    int telefono;
    
    //Variables para Cambio de escenas
    Node node;
    Stage stage;
    Parent parent;
    Scene root; 
    
    @FXML   
    private void Consulta(ActionEvent event){
        con=conectar.conexion();
        
        txtNombre.setEditable(false);
        txtApellido.setEditable(false);
        txtDireccion.setEditable(false);
        txtTelefono.setEditable(false);
        
        consultar="SELECT nombre,apellido,direccion,telefono FROM cliente WHERE nit='".concat(txtNit.getText()).concat("'");
        
        try {
            preparar=con.prepareStatement(consultar);
            result=preparar.executeQuery();
            
            while(result.next()){
                txtNombre.setText(result.getString(1));
                txtApellido.setText(result.getString(2));
                txtDireccion.setText(result.getString(3));
                txtTelefono.setText(result.getString(4));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaClientesController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Registro no encontrado", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    @FXML   
    private void NuevaConsulta(ActionEvent event){
        txtNit.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        
    }
    
    @FXML
    private void btnVolver(ActionEvent event) throws IOException{
        node=(Node) event.getSource();
        stage=(Stage) node.getScene().getWindow();
        
        parent=FXMLLoader.load(getClass().getResource("/Clientes/RegistroClientes.fxml"));
        
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Registro Clientes");
        stage.show();
        
    }
}
