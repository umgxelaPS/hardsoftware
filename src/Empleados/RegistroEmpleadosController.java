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
public class RegistroEmpleadosController implements Initializable {

   
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
    public TextField txtNacimiento;
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
    String sql;
    
    //Variables para Cambio de escenas
    Node node;
    Stage stage;
    Parent parent;
    Scene root;
    
    @FXML   
    private void GuardarEmpleado(ActionEvent event){
        dpi=txtDpi.getText();
        nombre=txtNombre.getText();
        apellido=txtApellido.getText();
        nacimiento=txtNacimiento.getText();
        cargo=txtCargo.getText();
        salario=txtSalario.getText();
        direccion=txtDireccion.getText();
        telefono=Integer.parseInt(txtTelefono.getText());
        
        //Guardar Registros en BD
        sql="INSERT INTO empleado(dpi,nombre,apellido,fecha_nacimiento,cargo,salario,direccion,telefono) VALUES(?,?,?,?,?,?,?,?)";
        
        try {
            preparar = con.prepareStatement(sql);
            preparar.setString(1, dpi);
            preparar.setString(2, nombre);
            preparar.setString(3, apellido);
            preparar.setString(4,nacimiento);
            preparar.setString(5,cargo);
            preparar.setString(6,salario);
            preparar.setString(7, direccion);
            preparar.setInt(8, telefono);
            preparar.executeUpdate();
            
            JOptionPane.showMessageDialog(null,"Registro Guardado");
            
            txtDpi.setText("");
            txtNombre.setText("");
            txtApellido.setText("");
            txtNacimiento.setText("");
            txtCargo.setText("");
            txtSalario.setText("");
            txtDireccion.setText("");
            txtTelefono.setText("");
        } catch (SQLException ex) {
            Logger.getLogger(RegistroEmpleadosController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Eror, Usuario no registrado");
        }  
    }
    
    @FXML   
    private void NuevoEmpleado(ActionEvent event){
        txtDpi.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtNacimiento.setText("");
        txtCargo.setText("");
        txtSalario.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        
    }
    
    @FXML
    private void btnConsultar(ActionEvent event) throws IOException{
        node=(Node) event.getSource();
        stage=(Stage) node.getScene().getWindow();
        
        parent=FXMLLoader.load(getClass().getResource("/Empleados/ConsultaEmpleados.fxml"));
        
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Consulta Empleados");
        stage.show();
        
    }
    
    @FXML
    private void btnModificar(ActionEvent event) throws IOException{
        node=(Node) event.getSource();
        stage=(Stage) node.getScene().getWindow();
        
        parent=FXMLLoader.load(getClass().getResource("/Empleados/ModificarEmpleados.fxml"));
        
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Modificar Empleados");
        stage.show();
        
    }
    
    @FXML
    private void btnEliminar(ActionEvent event) throws IOException{
        node=(Node) event.getSource();
        stage=(Stage) node.getScene().getWindow();
        
        parent=FXMLLoader.load(getClass().getResource("/Empleados/EliminarEmpleados.fxml"));
        
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Modificar Empleados");
        stage.show();
        
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
