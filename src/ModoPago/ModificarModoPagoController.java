/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModoPago;

import BD.ConexionBD;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author DAVID
 */
public class ModificarModoPagoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtDescripcion;

    @FXML
    private JFXButton btnLimpiar;

    @FXML
    private JFXButton btnConsultar;
    
    @FXML
    private JFXButton btnEditar;

    @FXML
    private JFXButton btnVolver;
    
    //Instancia Conexión BD
    ConexionBD conectar = new ConexionBD();
    Connection con=conectar.conexion();
    PreparedStatement preparar;
    ResultSet result;
    
    //VariablesGlobales
    int id;
    String descripcion;
    
    //Sentencia SQL
    String consultar;
    
    //Variables para Cambio de escenas
    Node node;
    Stage stage;
    Parent parent;
    Scene root;
   
    @FXML
    void btnConsultar(ActionEvent event) throws IOException {
        con=conectar.conexion();
        
        consultar="SELECT nombre FROM modo_pago WHERE id_pago='".concat(txtId.getText()).concat("'");
        
        try {
            preparar=con.prepareStatement(consultar);
            result=preparar.executeQuery();
            
            while(result.next()){
                txtDescripcion.setText(result.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModificarModoPagoController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Registro no encontrado");
        }
        
    }

    @FXML
    void btnEditar(ActionEvent event) {
        con=conectar.conexion();
        
        consultar="UPDATE modo_pago SET nombre=? WHERE id_pago='".concat(txtId.getText()).concat("'");
        try {
            preparar=con.prepareStatement(consultar);
            
            preparar.setString(1, txtDescripcion.getText());
            
            preparar.executeUpdate();
            
            JOptionPane.showMessageDialog(null,"Registro Modificado");
            
            txtId.setText("");
            txtDescripcion.setText("");
            
        } catch (SQLException ex) {
            Logger.getLogger(ModificarModoPagoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    void btnLimpiar(ActionEvent event) {
        txtId.setText("");
        txtDescripcion.setText("");
    }

    @FXML
    void btnVolver(ActionEvent event) throws IOException {
        node=(Node) event.getSource();
        stage=(Stage) node.getScene().getWindow();
        
        parent=FXMLLoader.load(getClass().getResource("/ModoPago/RegistroModoPago.fxml"));
        
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Registro Modo Pago");
        stage.show();

    }
    @FXML   
    void Cerrar(MouseEvent event){
        System.exit(0);
    }
}
