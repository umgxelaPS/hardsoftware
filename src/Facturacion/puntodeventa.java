/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facturacion;

import BD.ConexionBD;
import static Facturacion.ProductoInterfaz.PRODUCTLIST;
import Productos.ConsultaProductosController;
import Reportes.ClaseProducto;
import com.gluonhq.charm.glisten.control.NavigationDrawer.Item;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author martin
 */
public class puntodeventa implements Initializable {

    @FXML
    private TextField searchField;
    @FXML
    private TableView<ClaseProducto> productTableView;
    @FXML
    private TableColumn<ClaseProducto, String> productColumn;
    @FXML
    private TableView<?> listTableView;
    @FXML
    private TableColumn<?, ?> itemColumn;
    @FXML
    private TableColumn<?, ?> priceColumn;
    @FXML
    private TableColumn<?, ?> quantityColumn;
    @FXML
    private TableColumn<?, ?> totalColumn;
    @FXML
    private TextField productField;
    @FXML
    private TextField priceField;
    @FXML
    private TextArea descriptionArea;
    @FXML
    private TextField quantityField;
    @FXML
    private Label quantityLabel;
    @FXML
    private Button addButton;
    @FXML
    private Button removeButton;
    @FXML
    private Button resetButton;
    @FXML
    private TextField subTotalField;
    @FXML
    private TextField vatField;
    @FXML
    private TextField discountField;
    @FXML
    private TextField netPayableField;
    @FXML
    private Button paymentButton;
    @FXML
    private ObservableList<ClaseProducto> ITEMLIST;
  //Variables para Cambio de escenas
    Node node;
    Stage stage;
    Parent parent;
    Scene root;
    
    //Instancia Conexión BD
    ConexionBD conectar = new ConexionBD();
    Connection con=conectar.conexion();
    PreparedStatement preparar;
    ResultSet result;
    
    //VariablesGlobales
    int idProveedor;
    String nombre;
    String marca;
    String modelo;
    float precio_compra;
    float precio_venta;
    int cantidad;
    //Sentencia SQL
    String consultar;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        con=conectar.conexion();
        
          productColumn.setCellValueFactory(new PropertyValueFactory<ClaseProducto,String>("nombre"));
        Informacion();
  productColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        productTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showDetails(newValue));
        productTableView.setItems(ITEMLIST);
        filterData();
        
    }    
 public void Informacion(){
        ITEMLIST=FXCollections.observableArrayList();
        
        try {
            String query="SELECT * FROM producto";
            result=con.createStatement().executeQuery(query);
            while(result.next()){
                ClaseProducto producto = new ClaseProducto();
                //producto.idProducto.set(result.getInt("id_producto"));
               // producto.idProveedor.set(result.getInt("id_proveedor"));
                producto.nombre.set(result.getString("nombre"));
                System.out.println("XD"+result.getString("nombre"));
               // producto.marca.set(result.getString("marca"));
               // producto.modelo.set(result.getString("modelo"));
               // producto.PrecioC.set(result.getFloat("precio_compra"));
               // producto.PrecioV.set(result.getFloat("precio_venta"));
               // producto.cantidad.set(result.getInt("cantidad"));
                ITEMLIST.add(producto);
        }
            productTableView.setItems(ITEMLIST);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"Error al cargar los datos");
        }
 }
    @FXML
    private void logoutAction(ActionEvent event) throws IOException {
        node=(Node) event.getSource();
        stage=(Stage) node.getScene().getWindow();
        
        parent=FXMLLoader.load(getClass().getResource("/Dashboard/Dashboard.fxml"));
        
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.setTitle("Loggin");
        stage.show();
    }

    @FXML
    private void addAction(ActionEvent event) {
    }

    @FXML
    private void removeAction(ActionEvent event) {
    }

    @FXML
    private void resetAction(ActionEvent event) {
    }

    @FXML
    private void paymentAction(ActionEvent event) {
    }
  private void filterData() {
        FilteredList<ClaseProducto> searchedData = new FilteredList<>(ITEMLIST, e -> true);
   ClaseProducto producto = new ClaseProducto();
        searchField.setOnKeyReleased(e -> {
            searchField.textProperty().addListener((observable, oldValue, newValue) -> {
                searchedData.setPredicate(product -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (producto.getNombre().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (producto.getMarca().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    return false;
                });
            });

            SortedList<ClaseProducto> sortedData = new SortedList<>(searchedData);
            sortedData.comparatorProperty().bind(productTableView.comparatorProperty());
            productTableView.setItems(sortedData);
        });
    }  

    private void showDetails(ClaseProducto newValue) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
