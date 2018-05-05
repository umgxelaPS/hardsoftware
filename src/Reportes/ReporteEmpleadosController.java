/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reportes;

import BD.ConexionBD;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author DAVID
 */
public class ReporteEmpleadosController implements Initializable {

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        
        //Enlazar columnas con atributos
        c_dpi.setCellValueFactory(new PropertyValueFactory<ClaseEmpleado,Double>("DPI"));
        nombre.setCellValueFactory(new PropertyValueFactory<ClaseEmpleado,String>("nombre"));
        apellido.setCellValueFactory(new PropertyValueFactory<ClaseEmpleado,String>("apellido"));
        nacimiento.setCellValueFactory(new PropertyValueFactory<ClaseEmpleado,String>("nacimiento"));
        cargo.setCellValueFactory(new PropertyValueFactory<ClaseEmpleado,String>("cargo"));
        salario.setCellValueFactory(new PropertyValueFactory<ClaseEmpleado,Number>("salario"));
        direccion.setCellValueFactory(new PropertyValueFactory<ClaseEmpleado,String>("direccion"));
        telefono.setCellValueFactory(new PropertyValueFactory<ClaseEmpleado,Number>("telefono"));
        
        //LLenar Informacion
        Informacion();
        
    }
    
    @FXML
    private Button btnVolver;
    //Tabla
    @FXML
    private TableView<ClaseEmpleado> Tabla;
    
    //Columnas
    @FXML
    private TableColumn<ClaseEmpleado, Double> c_dpi;

    @FXML
    private TableColumn<ClaseEmpleado, String> nombre;

    @FXML
    private TableColumn<ClaseEmpleado, String> apellido;

    @FXML
    private TableColumn<ClaseEmpleado, String> nacimiento;

    @FXML
    private TableColumn<ClaseEmpleado, String> cargo;

    @FXML
    private TableColumn<ClaseEmpleado, Number> salario;

    @FXML
    private TableColumn<ClaseEmpleado, String> direccion;

    @FXML
    private TableColumn<ClaseEmpleado, Number> telefono;
    
    private ObservableList<ClaseEmpleado> listaEmpleados;
    
     //Instancia Conexión BD
    ConexionBD conectar = new ConexionBD();
    Connection con=conectar.conexion();
    PreparedStatement preparar;
    ResultSet result;
    
    public void Informacion(){
        listaEmpleados=FXCollections.observableArrayList();
        
        try {
            String query="SELECT * FROM empleado";
            result=con.createStatement().executeQuery(query);
            while(result.next()){
                ClaseEmpleado empleado = new ClaseEmpleado();
                empleado.DPI.set(result.getDouble("dpi"));
                empleado.nombre.set(result.getString("nombre"));
                empleado.apellido.set(result.getString("apellido"));
                empleado.nacimiento.set(result.getString("fecha_nacimiento"));
                empleado.cargo.set(result.getString("cargo"));
                empleado.salario.set(result.getInt("salario"));
                empleado.direccion.set(result.getString("direccion"));
                empleado.telefono.set(result.getInt("telefono"));
                listaEmpleados.add(empleado);
        }
            Tabla.setItems(listaEmpleados);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"Error al cargar los datos");
        }
            
    }
    
    //Variables para Cambio de escenas
    Node node;
    Stage stage;
    Parent parent;
    Scene root;
    
    @FXML
    void btnVolver(ActionEvent event) throws IOException {
        node=(Node) event.getSource();
        stage=(Stage) node.getScene().getWindow();
        
        parent=FXMLLoader.load(getClass().getResource("/Reportes/Reportes.fxml"));
        
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Reportes");
        stage.show();
        
    }
  
}
