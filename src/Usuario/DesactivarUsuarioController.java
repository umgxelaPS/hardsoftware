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
public class DesactivarUsuarioController implements Initializable {
       @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     
    //Declaración de Botones
    @FXML
    private TextField  txtdpi;
    @FXML
    private TextField  txtestado;
    @FXML
    private Button volver;
    @FXML
    private Button btnnuevo;

    @FXML
    private Button btnConsultar;
    @FXML
    private Button btnDesactivar;

    
    //Instancia Conexión BD
    ConexionBD conectar = new ConexionBD();
    Connection con=conectar.conexion();
    PreparedStatement preparar;
    ResultSet result;
    //VariablesGlobales
   int numusuario;
    String dpi;
    String usuario;
    String contrasenia;
    String rol;
    String estado;
        //Sentencia SQL
    String consultar;
    //Variables para Cambio de escenas
    Node node;
    Stage stage;
    Parent parent;
    Scene root; 
    
    @FXML   
    private void ConsultarUsuario(ActionEvent event){
        con=conectar.conexion();
        
        
        consultar="SELECT estado FROM usuario WHERE dpi_empleado='".concat(txtdpi.getText()).concat("'");
        
        try {
            preparar=con.prepareStatement(consultar);
            result=preparar.executeQuery();
            
            while(result.next()){
                txtestado.setText(result.getString(1));

            }
        } catch (SQLException ex) {
            Logger.getLogger(DesactivarUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Registro no encontrado", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    @FXML   
    private void DesactivarUsuario(ActionEvent event){
        con=conectar.conexion();
        
        consultar="UPDATE usuario SET estado=? WHERE dpi_empleado='".concat(txtdpi.getText()).concat("'");
        try {
            preparar=con.prepareStatement(consultar);
            
            preparar.setString(1, txtestado.getText());
            
            preparar.executeUpdate();
            
            JOptionPane.showMessageDialog(null,"Estado Modificado");
            
            txtdpi.setText("");
            txtestado.setText("");
            
        } catch (SQLException ex) {
            Logger.getLogger(DesactivarUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Error al desactivar el usuario");
        }
    }
    
    @FXML    
    private void NuevaConsulta(ActionEvent event){
        txtdpi.clear();
        txtestado.clear();
    }
    
    @FXML
    private void btnVolver(ActionEvent event) throws IOException{
        node=(Node) event.getSource();
        stage=(Stage) node.getScene().getWindow();
        
        parent=FXMLLoader.load(getClass().getResource("/Usuario/F_Usuarios.fxml"));
        
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.setTitle("Registro Usuarios");
        stage.show();
        
    }
    
}
