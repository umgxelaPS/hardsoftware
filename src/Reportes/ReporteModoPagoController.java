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
public class ReporteModoPagoController implements Initializable {

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        
        //Enlazar columnas con atributos
        id.setCellValueFactory(new PropertyValueFactory<ClaseModoPago,Number>("ID"));
        descripcion.setCellValueFactory(new PropertyValueFactory<ClaseModoPago,String>("Descripcion"));
        
        //LLenar Informacion
        Informacion();
        
    }
    
    
    //Tabla
    @FXML
    private TableView<ClaseModoPago> Tabla;
    
    //Columnas
    @FXML
    private TableColumn<ClaseModoPago, Number> id;

    @FXML
    private TableColumn<ClaseModoPago, String> descripcion;

    @FXML
    private Button btnVolver;

    
    private ObservableList<ClaseModoPago> listaModos;
    
     //Instancia Conexión BD
    ConexionBD conectar = new ConexionBD();
    Connection con=conectar.conexion();
    PreparedStatement preparar;
    ResultSet result;
    
    public void Informacion(){
        listaModos=FXCollections.observableArrayList();
        
        try {
            String query="SELECT * FROM modo_pago";
            result=con.createStatement().executeQuery(query);
            while(result.next()){
                ClaseModoPago pago = new ClaseModoPago();
                pago.ID.set(result.getInt("id_pago"));
                pago.Descripcion.set(result.getString("nombre"));
                listaModos.add(pago);
        }
            Tabla.setItems(listaModos);
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
