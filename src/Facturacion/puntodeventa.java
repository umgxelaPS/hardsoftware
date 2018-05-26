/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facturacion;

import Facturacion.pagos.pago;
import Facturacion.pagos.FacturacionController;
import BD.ConexionBD;
import static Facturacion.ProductoInterfaz.PRODUCTLIST;
import Productos.ConsultaProductosController;
import Reportes.ClaseProducto;
import com.gluonhq.charm.glisten.control.NavigationDrawer.Item;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
    private TableView<articulo> listTableView;
    @FXML
    private TableColumn<articulo, String> itemColumn;
    @FXML
    private TableColumn<articulo, Double> priceColumn, quantityColumn, totalColumn;
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
    private ObservableList<articulo> ITEMLIST;
    private ClaseProducto productModel;
    private double xOffset = 0;
    private double yOffset = 0;
    //Variables para Cambio de escenas
    /*Node node;
    Stage stage;
    Parent parent;
    Scene root;*/

    //Instancia Conexión BD
    ConexionBD conectar = new ConexionBD();
    Connection con = conectar.conexion();
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
    private String idProducto;

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        con = conectar.conexion();
        ITEMLIST = FXCollections.observableArrayList();
        productModel = new ClaseProducto();
        productColumn.setCellValueFactory(new PropertyValueFactory<ClaseProducto, String>("nombre"));
        Informacion();

        //Inicio Data
        productColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        productTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showDetails(newValue));
        productTableView.setItems(PRODUCTLIST);

        filterData();
        itemColumn.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        totalColumn.setCellValueFactory(new PropertyValueFactory<>("total"));
        listTableView.setItems(ITEMLIST);

        addButton
                .disableProperty()
                .bind(Bindings.isEmpty(productTableView.getSelectionModel().getSelectedItems()));
        removeButton
                .disableProperty()
                .bind(Bindings.isEmpty(listTableView.getSelectionModel().getSelectedItems()));

    }

    public void Informacion() {
        // PRODUCTLIST = FXCollections.observableArrayList();
        PRODUCTLIST.clear();
        try {
            String query = "SELECT * FROM producto";
            result = con.createStatement().executeQuery(query);
            while (result.next()) {
                ClaseProducto producto = new ClaseProducto();
                producto.idProducto.set(result.getString("id_producto"));
                producto.idProveedor.set(result.getInt("id_proveedor"));
                producto.nombre.set(result.getString("nombre"));
                producto.marca.set(result.getString("marca"));
                producto.modelo.set(result.getString("modelo"));
                producto.PrecioC.set(result.getFloat("precio_compra"));
                producto.PrecioV.set(result.getFloat("precio_venta"));
                producto.cantidad.set(result.getInt("cantidad"));
                //String
                PRODUCTLIST.add(producto);
            }
            productTableView.setItems(PRODUCTLIST);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al cargar los datos");
        }
    }

    @FXML
    private void logoutAction(ActionEvent event) throws IOException {
        Node node;
        Stage stage;
        Parent parent;
        Scene root;
        node = (Node) event.getSource();
        stage = (Stage) node.getScene().getWindow();

        parent = FXMLLoader.load(getClass().getResource("/Dashboard/Dashboard.fxml"));

        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.setTitle("Loggin");
        stage.show();
    }

    @FXML
    private void addAction(ActionEvent event) {
        if (validateInput()) {
            String idProducto = this.getIdProducto();
            String productName = productField.getText();
            double unitPrice = Double.parseDouble(priceField.getText());
            double quantity = Double.parseDouble(quantityField.getText());
            double total = unitPrice * quantity;
            System.out.println("en addaction: "+idProducto);
            ITEMLIST.add(new articulo(idProducto, productName, unitPrice, quantity, total));
            calculation();

            resetAdd();
            productTableView.getSelectionModel().clearSelection();
        }
    }

    @FXML
    private void removeAction(ActionEvent event) {

        int index = listTableView.getSelectionModel().getSelectedIndex();

        if (index > 0) {
            listTableView.getItems().remove(index);
            calculation();
        } else if (index == 0) {
            listTableView.getItems().remove(index);
            resetInvoice();
        }
    }

    @FXML
    private void resetAction(ActionEvent event) {
        resetInterface();
    }

    @FXML
    private void paymentAction(ActionEvent event) throws IOException {
        pago payment = new pago(
                Double.parseDouble(subTotalField.getText().trim()),
                Double.parseDouble(vatField.getText().trim()),
                Double.parseDouble(discountField.getText().trim()),
                Double.parseDouble(netPayableField.getText().trim())
        );

        ObservableList<articulo> sold = listTableView.getItems();

        try {
            /*node = (Node) event.getSource();
            stage = (Stage) node.getScene().getWindow();

            FacturacionController controller = new FacturacionController();
            
            System.out.println("Total en punto de venta: " + netPayableField.getText().trim());
            parent = FXMLLoader.load(getClass().getResource("/Facturacion/pagos/facturacion.fxml"));
            parent.setController(controller);
            controller.setData(Double.parseDouble(netPayableField.getText().trim()), sold, payment);

            /*  root.setOnMousePressed((MouseEvent e) -> {
            xOffset = e.getSceneX();
            yOffset = e.getSceneY();
            });
            root.setOnMouseDragged((MouseEvent e) -> {
            stage.setX(e.getScreenX() - xOffset);
            stage.setY(e.getScreenY() - yOffset);
            });/
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.setTitle("Registro Clientes");
            stage.show();Funciona*/
            FXMLLoader loader = new FXMLLoader((getClass().getResource("/Facturacion/pagos/facturacion.fxml")));
            FacturacionController controller = new FacturacionController();
            loader.setController(controller);
            controller.setData(Double.parseDouble(netPayableField.getText().trim()), sold, payment);
            Parent root = loader.load();
            Stage stage = new Stage();
            root.setOnMousePressed((MouseEvent e) -> {
                xOffset = e.getSceneX();
                yOffset = e.getSceneY();
            });
            root.setOnMouseDragged((MouseEvent e) -> {
                stage.setX(e.getScreenX() - xOffset);
                stage.setY(e.getScreenY() - yOffset);
            });
            Scene scene = new Scene(root);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Pagos");
            stage.initStyle(StageStyle.UNDECORATED);
            //stage.getIcons().add(new Image("../Imagenes/letrero.png"));
            stage.setScene(scene);
            stage.showAndWait();

        } catch (Exception e) {
            System.out.println("Error en punto de venta: " + e);
        }

        resetInterface();
    }

    private void filterData() {
        FilteredList<ClaseProducto> searchedData = new FilteredList<>(PRODUCTLIST, e -> true);

        searchField.setOnKeyReleased(e -> {
            searchField.textProperty().addListener((observable, oldValue, newValue) -> {
                searchedData.setPredicate(producto -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (producto.getNombre().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }else if(producto.getIdProducto().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }
                    else if (producto.getMarca().toLowerCase().contains(lowerCaseFilter)) {
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

    private void showDetails(ClaseProducto product) {
        if (product != null) {
            this.setIdProducto(product.getIdProducto());
            System.out.println("Idproducto in puntodeventa:" + this.getIdProducto());
            quantityField.setDisable(false);
            productField.setText(product.getNombre());
            priceField.setText(String.valueOf(product.getPrecioV()));

            double quantity = product.getCantidad();

            if (quantity > 0) {
                quantityField.setEditable(true);
                quantityField.setStyle(null);
            } else {
                quantityField.setEditable(false);
                quantityField.setStyle("-fx-background-color: red;");
            }
            quantityLabel.setText("Stock: " + String.valueOf(quantity));
            descriptionArea.setText(product.getMarca() + " " + product.getModelo());
        } else {
            productField.setText("");
            priceField.setText("");
            quantityLabel.setText("");
            descriptionArea.setText("");
        }
    }

    private boolean validateInput() {

        String errorMessage = "";

        if (quantityField.getText() == null || quantityField.getText().length() == 0) {
            errorMessage += "Cantidad no Ingresada!\n";
        } else {
            double quantity = Double.parseDouble(quantityField.getText());
            String available = quantityLabel.getText();
            double availableQuantity = Double.parseDouble(available.substring(7));

            if (quantity > availableQuantity) {
                errorMessage += "Fuera de disponibilidad!\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alerta");
            alert.setHeaderText("Por favor ingresa un numero válido de productos");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            quantityField.setText("");

            return false;
        }
    }

    private void calculation() {

        double subTotalPrice = 0.0;
        subTotalPrice = listTableView.getItems().stream().map(
                (item) -> item.getTotal()).reduce(subTotalPrice, (accumulator, _item) -> accumulator + _item);

        if (subTotalPrice > 0) {
            paymentButton.setDisable(false);
            double vat = (double) subTotalPrice * 0.025;
            double netPayablePrice = (double) (Math.abs((subTotalPrice + vat) - 5));

            subTotalField.setText(String.valueOf(subTotalPrice));
            vatField.setText(String.valueOf(vat));
            netPayableField.setText(String.valueOf(netPayablePrice));
        }
    }

    private void resetProductTableSelection() {
        productTableView.getSelectionModel().clearSelection();
    }

    private void resetItemTable() {
        ITEMLIST.clear();
    }

    private void resetAdd() {
        productField.setText("");
        priceField.setText("");
        quantityField.setText("");
        resetQuantityField();
        quantityLabel.setText("Stock: ");
        descriptionArea.setText("");
    }

    private void resetInvoice() {
        subTotalField.setText("0.00");
        vatField.setText("0.00");
        netPayableField.setText("0.00");
    }

    private void resetQuantityField() {
        quantityField.setDisable(true);
    }

    private void resetPaymentButton() {
        paymentButton.setDisable(true);
    }

    private void resetInterface() {
//        Informacion();
        resetPaymentButton();
        resetProductTableSelection();
        resetItemTable();
        resetQuantityField();
        resetAdd();
        resetInvoice();
    }

}
