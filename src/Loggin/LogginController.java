/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Loggin;

import BD.ConexionBD;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
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
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author DAVID
 */
public class LogginController implements Initializable {

    @FXML
    private Button cerrar;
    @FXML
    private Button usuario;
    @FXML
    private Button sesion;
    @FXML
    private TextField txtusuario;

    @FXML
    private PasswordField txtpass;
    @FXML   
    private void Cerrar(ActionEvent event){
       System.exit(0);
    }
    
    //Variables para Cambio de escenas
    Node node;
    Stage stage;
    Parent parent;
    Scene root;
    
    @FXML
    private void btnUsuario(ActionEvent event) throws IOException{
        node=(Node) event.getSource();
        stage=(Stage) node.getScene().getWindow();
        
        parent=FXMLLoader.load(getClass().getResource("/Usuario/F_Usuarios.fxml"));
        
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Registro Usuarios");
        stage.show();
        
    }
     //Instancia Conexión BD
    ConexionBD conectar = new ConexionBD();
    Connection con=conectar.conexion();
    PreparedStatement preparar;
    ResultSet result;
    //String para la consulta
    String consultar;
    String nombre;
    String apellido;
    String direccion;
    int telefono;
    

    @FXML
    private void btnLoggin(ActionEvent event) throws IOException{
            String capturarol="";
        String sql="SELECT * FROM usuario WHERE usuario='"+txtusuario.getText()+"' && contraseña='"+txtpass.getText()+"'";
        try {
            
            Statement st =con.createStatement();
            ResultSet rs= st.executeQuery(sql);
            while (rs.next()){
            capturarol=rs.getString("tipo_usuario");
            }
            if (capturarol.equals("vendedor")){
                node=(Node) event.getSource();
        stage=(Stage) node.getScene().getWindow();
        
        parent=FXMLLoader.load(getClass().getResource("/Dashboard/Dashboard.fxml"));
        
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Dashboard");
        stage.show();
            }else{
            JOptionPane.showMessageDialog(null, "USUARIO NO REGISTRADO", "Error", JOptionPane.WARNING_MESSAGE);
            }
            
        } catch (SQLException ex) {
            //Logger.getLogger(LogginController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "USUARIO NO REGISTRADO", "Error", JOptionPane.WARNING_MESSAGE);
        }
        
        
        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
}
