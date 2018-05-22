/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Empleados;

import BD.ConexionBD;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author DAVID
 */
public class ConsultaEmpleadosController implements Initializable {

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    public TextField txtDpi;
    @FXML
    public TextField txtNombre;
    @FXML
    public TextField txtApellido;
    @FXML
    public DatePicker txtNacimiento;
    @FXML
    public TextField txtCargo;
    @FXML
    public TextField txtSalario;
    @FXML
    public TextField txtDireccion;
    @FXML
    public TextField txtTelefono;
    
    //Instancia Conexión BD
    ConexionBD conectar = new ConexionBD();
    Connection con=conectar.conexion();
    PreparedStatement preparar;
    ResultSet result;
    
    //VariablesGlobales
    String dpi;
    String nombre;
    String apellido;
    String nacimiento;
    String cargo;
    String salario;
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
    private void Consulta(ActionEvent event){
        con=conectar.conexion();
        
        txtNombre.setEditable(false);
        txtApellido.setEditable(false);
        txtNacimiento.setEditable(false);
        txtCargo.setEditable(false);
        txtSalario.setEditable(false);
        txtDireccion.setEditable(false);
        txtTelefono.setEditable(false);
        
        consultar="SELECT nombre,apellido,fecha_nacimiento,cargo,salario,direccion,telefono FROM empleado WHERE dpi='".concat(txtDpi.getText()).concat("'");
        
        try {
            preparar=con.prepareStatement(consultar);
            result=preparar.executeQuery();
            
            while(result.next()){
                txtNombre.setText(result.getString(1));
                txtApellido.setText(result.getString(2));
                txtNacimiento.setValue(LocalDate.parse(result.getString(3)));
                txtCargo.setText(result.getString(4));
                txtSalario.setText(result.getString(5));
                txtDireccion.setText(result.getString(6));
                txtTelefono.setText(result.getString(7));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaEmpleadosController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Registro no encontrado");
        }
    }
    
    @FXML   
    private void NuevaConsulta(ActionEvent event){
        txtDpi.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtNacimiento.setValue(null);
        txtCargo.setText("");
        txtSalario.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        
    }
    
    @FXML
    private void btnVolver(ActionEvent event) throws IOException{
        node=(Node) event.getSource();
        stage=(Stage) node.getScene().getWindow();
        
        parent=FXMLLoader.load(getClass().getResource("/Empleados/RegistroEmpleados.fxml"));
        
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Registro Empleados");
        stage.show();
        
    }
    
    @FXML   
    private void Cerrar(MouseEvent event){
        System.exit(0);
    }
    
    
}
