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
public class ConsultaUsuarioController implements Initializable {
       @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     
    //Declaración de Botones
    @FXML
    private TextField  txtdpi;
    @FXML
    private TextField  txtid;
    @FXML
    private TextField  txtusuario;
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
        con=conectar.conexion();
        txtid.setEditable(false);
        txtusuario.setEditable(false);
        txtrol.setEditable(false);
        txtestado.setEditable(false);
        
        consultar="SELECT id_usuario,usuario,tipo_usuario,estado FROM usuario WHERE dpi_empleado='".concat(txtdpi.getText()).concat("'");
        
        try {
            preparar=con.prepareStatement(consultar);
            result=preparar.executeQuery();
            
            while(result.next()){
                txtid.setText(result.getString(1));
                txtusuario.setText(result.getString(2));
                txtrol.setText(result.getString(3));
                txtestado.setText(result.getString(4));

            }
        } catch (SQLException ex) {
            Logger.getLogger(DesactivarUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Registro no encontrado", "Error", JOptionPane.WARNING_MESSAGE);
        }
        
    }
    
    @FXML   
    private void Nuevo(ActionEvent event){
        txtdpi.setText("");
        txtusuario.setText("");
        txtid.setText("");
        txtrol.setText("");
        txtestado.setText("");
    }
    
    
}
