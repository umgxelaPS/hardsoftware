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
public class ReporteClientesController implements Initializable {

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        
        //Enlazar columnas con atributos
        C_Nit.setCellValueFactory(new PropertyValueFactory<ClaseCliente,String>("Nit_C"));
        nombre.setCellValueFactory(new PropertyValueFactory<ClaseCliente,String>("nombre"));
        apellido.setCellValueFactory(new PropertyValueFactory<ClaseCliente,String>("apellido"));
        direccion.setCellValueFactory(new PropertyValueFactory<ClaseCliente,String>("direccion"));
        telefono.setCellValueFactory(new PropertyValueFactory<ClaseCliente,Number>("telefono"));
        
        //LLenar Informacion
        Informacion();
        
    }
    
    
    //Tabla
    @FXML
    private TableView<ClaseCliente> Tabla;
    
    //Columnas
    @FXML
    private TableColumn<ClaseCliente, String> C_Nit;

    @FXML
    private TableColumn<ClaseCliente, String> nombre;

    @FXML
    private TableColumn<ClaseCliente, String> apellido;

    @FXML
    private TableColumn<ClaseCliente, String> direccion;

    @FXML
    private TableColumn<ClaseCliente, Number> telefono;

    @FXML
    private Button btnVolver;

    
    private ObservableList<ClaseCliente> listaClientes;
    
     //Instancia Conexión BD
    ConexionBD conectar = new ConexionBD();
    Connection con=conectar.conexion();
    PreparedStatement preparar;
    ResultSet result;
    
    public void Informacion(){
        listaClientes=FXCollections.observableArrayList();
        
        try {
            String query="SELECT * FROM cliente";
            result=con.createStatement().executeQuery(query);
            while(result.next()){
                ClaseCliente cliente = new ClaseCliente();
                cliente.Nit_C.set(result.getString("nit"));
                cliente.nombre.set(result.getString("nombre"));
                cliente.apellido.set(result.getString("apellido"));
                cliente.direccion.set(result.getString("direccion"));
                cliente.telefono.set(result.getInt("telefono"));
                listaClientes.add(cliente);
        }
            Tabla.setItems(listaClientes);
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
