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
public class ReporteProductosController implements Initializable {

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        
        //Enlazar columnas con atributos
        id_producto.setCellValueFactory(new PropertyValueFactory<ClaseProducto,String>("idProducto"));
        id_proveedor.setCellValueFactory(new PropertyValueFactory<ClaseProducto,Number>("idProveedor"));
        nombre.setCellValueFactory(new PropertyValueFactory<ClaseProducto,String>("nombre"));
        marca.setCellValueFactory(new PropertyValueFactory<ClaseProducto,String>("marca"));
        modelo.setCellValueFactory(new PropertyValueFactory<ClaseProducto,String>("modelo"));
        precio_compra.setCellValueFactory(new PropertyValueFactory<ClaseProducto,Float>("PrecioC"));
        precio_venta.setCellValueFactory(new PropertyValueFactory<ClaseProducto,Float>("PrecioV"));
        cantidad.setCellValueFactory(new PropertyValueFactory<ClaseProducto,Number>("cantidad"));
        
        //LLenar Informacion
        Informacion();
        
    }
    
    @FXML
    private Button btnVolver;
    //Tabla
    @FXML
    private TableView<ClaseProducto> Tabla;
    
    //Columnas
    @FXML
    private TableColumn<ClaseProducto, String> id_producto;
    @FXML
    private TableColumn<ClaseProducto, Number> id_proveedor;
    @FXML
    private TableColumn<ClaseProducto, String> nombre;
    @FXML
    private TableColumn<ClaseProducto, String> marca;
    @FXML
    private TableColumn<ClaseProducto, String> modelo;
    @FXML
    private TableColumn<ClaseProducto, Float> precio_compra;
    @FXML
    private TableColumn<ClaseProducto, Float> precio_venta;
    @FXML
    private TableColumn<ClaseProducto, Number> cantidad;
    
    private ObservableList<ClaseProducto> listaProductos;
    
     //Instancia Conexión BD
    ConexionBD conectar = new ConexionBD();
    Connection con=conectar.conexion();
    PreparedStatement preparar;
    ResultSet result;
    
    public void Informacion(){
        listaProductos=FXCollections.observableArrayList();
        
        try {
            String query="SELECT * FROM producto";
            result=con.createStatement().executeQuery(query);
            while(result.next()){
                ClaseProducto producto = new ClaseProducto();
                producto.idProducto.set(result.getString("id_producto"));
                producto.idProveedor.set(result.getInt("id_proveedor"));
                producto.nombre.set(result.getString("nombre"));
                producto.marca.set(result.getString("marca"));
                producto.modelo.set(result.getString("modelo"));
                producto.PrecioC.set(result.getFloat("precio_compra"));
                producto.PrecioV.set(result.getFloat("precio_venta"));
                producto.cantidad.set(result.getInt("cantidad"));
                listaProductos.add(producto);
        }
            Tabla.setItems(listaProductos);
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
