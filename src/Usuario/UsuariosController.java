/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Usuario;

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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author DAVID
 */
public class UsuariosController implements Initializable {
       @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     
    //Declaración de Botones
    @FXML
    private TextField  txtdpi;
    @FXML
    private TextField  txtusuario;
    @FXML
    private PasswordField  txtcontrasenia;
    @FXML
    private TextField  txtrol;
    @FXML
    private TextField  txtestado;
    @FXML
    private Button volver;
    @FXML
    private Button btnnuevo;
    @FXML  
    public Button btnGuardar;

    @FXML
    private Button eliminar;
    @FXML
    private Button buscar;
    
    //Instancia Conexión BD
    ConexionBD conectar = new ConexionBD();
    Connection con=conectar.conexion();
    PreparedStatement preparar;
    //VariablesGlobales
   int numusuario;
    String dpi;
    String usuario;
    String contrasenia;
    String rol;
    String estado;
        //Sentencia SQL
    String sql;
    //Variables para Cambio de escenas
    Node node;
    Stage stage;
    Parent parent;
    Scene root; 
    
    @FXML   
    private void GuardarUsuario(ActionEvent event){
        dpi=txtdpi.getText();
        usuario=txtusuario.getText();
        contrasenia=txtcontrasenia.getText();
        rol=txtrol.getText();
        estado=txtestado.getText();
               
        //Guardar Registros en BD
        sql="INSERT INTO usuario(dpi_empleado,usuario,contraseña,tipo_usuario,estado) VALUES(?,?,?,?,?)";
        
        try {
            preparar = con.prepareStatement(sql);
            preparar.setString(1,dpi);
            preparar.setString(2,usuario);
            preparar.setString(3,contrasenia);
            preparar.setString(4,rol);
            preparar.setString(5,estado);
            preparar.executeUpdate();
            
            JOptionPane.showMessageDialog(null,"Registro Guardado Exitosamiente ");
            
            txtdpi.setText("");
            txtusuario.setText("");
            txtcontrasenia.setText("");
            txtrol.setText("");
            txtestado.setText("");

        } catch (SQLException ex) {
            Logger.getLogger(UsuariosController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Error al guardar el registro ");
        }  
    }
    
    @FXML   
    private void Nuevo(ActionEvent event){
        txtdpi.setText("");
        txtusuario.setText("");
        txtcontrasenia.setText("");
        txtrol.setText("");
        txtestado.setText("");
    }
    
    @FXML
    private void btnVolver(ActionEvent event) throws IOException{
        node=(Node) event.getSource();
        stage=(Stage) node.getScene().getWindow();
        
        parent=FXMLLoader.load(getClass().getResource("/Login/F_Login.fxml"));
        
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.setTitle("Loggin");
        stage.show();
        
    }
    
    @FXML
    private void btnConsultar(ActionEvent event)throws IOException{
        node=(Node) event.getSource();
        stage=(Stage) node.getScene().getWindow();
        
        parent=FXMLLoader.load(getClass().getResource("/Usuario/ConsultaUsuario.fxml"));
        
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.setTitle("Consultar Usuario");
        stage.show();
        
    }
    
    @FXML
    private void btnDesactivar(ActionEvent event)throws IOException{
        node=(Node) event.getSource();
        stage=(Stage) node.getScene().getWindow();
        
        parent=FXMLLoader.load(getClass().getResource("/Usuario/DesactivarUsuario.fxml"));
        
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.setTitle("Desactivar Usuario");
        stage.show();
        
    }
    
}
