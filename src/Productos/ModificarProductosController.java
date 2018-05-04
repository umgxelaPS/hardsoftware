/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Productos;

import BD.ConexionBD;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author DAVID
 */
public class ModificarProductosController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboMarca.setValue(null);
        comboMarca.setItems(Marcas);
    }    
    
    
    
    ObservableList<String> Marcas = FXCollections.observableArrayList("Samsung","Dell","HP","Cannon","Epson");
            
    @FXML
    private JFXTextField txtID;
    
    @FXML
    private JFXTextField txtProveedor;

    @FXML
    private JFXTextField txtNombre;

    @FXML
    private JFXComboBox comboMarca;

    @FXML
    private JFXTextField txtModelo;

    @FXML
    private JFXTextField txtPrecioCompra;

    @FXML
    private JFXTextField txtPrecioVenta;

    @FXML
    private JFXTextField txtCantidad;

    @FXML
    private JFXButton btnLimpiar;

    @FXML
    private JFXButton btnConsultar;
    
    @FXML
    private JFXButton btnEditar;

    @FXML
    private JFXButton btnVolver;

    @FXML
    private Label Cerrar;


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
    
    //Variables para Cambio de escenas
    Node node;
    Stage stage;
    Parent parent;
    Scene root; 
    
    @FXML
    void btnConsultar(ActionEvent event) {
        con=conectar.conexion();
        
        consultar="SELECT id_proveedor,nombre,marca,modelo,precio_compra,precio_venta,cantidad "
                + "FROM producto WHERE id_producto='".concat(txtID.getText()).concat("'");
        
        try {
            preparar=con.prepareStatement(consultar);
            result=preparar.executeQuery();
            
            while(result.next()){
                txtProveedor.setText(result.getString(1));
                txtNombre.setText(result.getString(2));
                comboMarca.setValue(result.getString(3));
                txtModelo.setText(result.getString(4));
                txtPrecioCompra.setText(result.getString(5));
                txtPrecioVenta.setText(result.getString(6));
                txtCantidad.setText(result.getString(7));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModificarProductosController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Registro no encontrado", "Error", JOptionPane.WARNING_MESSAGE);
        }

    }
    
    @FXML
    void btnEditar(ActionEvent event) {
        con=conectar.conexion();
        
        consultar="UPDATE producto SET  id_proveedor=?,nombre=?,marca=?,"
                + "modelo=?,precio_compra=?,precio_venta=?,cantidad=? "
                + "WHERE id_producto='".concat(txtID.getText()).concat("'");
        try {
            preparar=con.prepareStatement(consultar);
            
            preparar.setString(1, txtProveedor.getText());
            preparar.setString(2, txtNombre.getText());
            preparar.setString(3, comboMarca.getValue().toString());
            preparar.setString(4, txtModelo.getText());
            preparar.setString(5, txtPrecioCompra.getText());
            preparar.setString(6, txtPrecioVenta.getText());
            preparar.setString(7, txtCantidad.getText());
            
            preparar.executeUpdate();
            
            JOptionPane.showMessageDialog(null,"Registro Modificado");
            
            txtID.clear();
            txtProveedor.clear();
            txtNombre.clear();
            comboMarca.setValue(null);
            txtModelo.clear();
            txtPrecioCompra.clear();
            txtPrecioVenta.clear();
            txtCantidad.clear();
            
        } catch (SQLException ex) {
            Logger.getLogger(ModificarProductosController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Registro no Modificado");
        }
    
    }
    
    @FXML
    void btnLimpiar(ActionEvent event) {
        txtID.clear();
        txtProveedor.clear();
        txtNombre.clear();
        comboMarca.setValue(null);
        txtModelo.clear();
        txtPrecioCompra.clear();
        txtPrecioVenta.clear();
        txtCantidad.clear();
    }
    
    

    @FXML
    void btnVolver(ActionEvent event) throws IOException {
        node=(Node) event.getSource();
        stage=(Stage) node.getScene().getWindow();
        
        parent=FXMLLoader.load(getClass().getResource("/Productos/RegistroProductos.fxml"));
        
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.setTitle("Registro Productos");
        stage.show();

    }
    
    @FXML
    void Cerrar(MouseEvent event) {
        System.exit(0);
    }
}
