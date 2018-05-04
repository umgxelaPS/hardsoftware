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
public class RegistroProductosController implements Initializable {

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
    private JFXButton btnGuardar;

    @FXML
    private JFXButton btnEditar;

    @FXML
    private JFXButton btnConsultar;

    @FXML
    private JFXButton btnEliminar;

    @FXML
    private Label Cerrar;


    //Instancia Conexión BD
    ConexionBD conectar = new ConexionBD();
    Connection con=conectar.conexion();
    PreparedStatement preparar;
    
    //VariablesGlobales
    int idProveedor;
    String nombre;
    String marca;
    String modelo;
    float precio_compra;
    float precio_venta;
    int cantidad;
    
    //Sentencia SQL
    String sql;
    
    //Variables para Cambio de escenas
    Node node;
    Stage stage;
    Parent parent;
    Scene root; 
    
    @FXML
    void btnGuardar(ActionEvent event) {
        idProveedor=Integer.parseInt(txtProveedor.getText());
        nombre=txtNombre.getText();
        marca=comboMarca.getValue().toString();
        modelo=txtModelo.getText();
        precio_compra=Float.parseFloat(txtPrecioCompra.getText());
        precio_venta=Float.parseFloat(txtPrecioVenta.getText());
        cantidad=Integer.parseInt(txtCantidad.getText());
        
        
        //Guardar Registros en BD
        sql="INSERT INTO producto(id_proveedor,nombre,marca,modelo,precio_compra,precio_venta,cantidad) VALUES(?,?,?,?,?,?,?)";
        
        try {
            preparar = con.prepareStatement(sql);
            preparar.setInt(1, idProveedor);
            preparar.setString(2, nombre);
            preparar.setString(3, marca);
            preparar.setString(4, modelo);
            preparar.setFloat(5,precio_compra);
            preparar.setFloat(6,precio_venta);
            preparar.setInt(7,cantidad);
            preparar.executeUpdate();
            
            JOptionPane.showMessageDialog(null,"Registro Guardado");
            
            txtProveedor.clear();
            txtNombre.clear();
            comboMarca.setValue(null);
            txtModelo.clear();
            txtPrecioCompra.clear();
            txtPrecioVenta.clear();
            txtCantidad.clear();
        } catch (SQLException ex) {
            Logger.getLogger(RegistroProductosController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Eror, Registro no Guardado");
        }

    }

    @FXML
    void btnLimpiar(ActionEvent event) {
        txtProveedor.clear();
        txtNombre.clear();
        comboMarca.setValue(null);
        txtModelo.clear();
        txtPrecioCompra.clear();
        txtPrecioVenta.clear();
        txtCantidad.clear();
    }
    
    @FXML
    void btnConsultar(ActionEvent event) throws IOException {
        node=(Node) event.getSource();
        stage=(Stage) node.getScene().getWindow();
        
        parent=FXMLLoader.load(getClass().getResource("/Productos/ConsultaProductos.fxml"));
        
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Consulta Productos");
        stage.show();

    }

    @FXML
    void btnEditar(ActionEvent event) throws IOException {
        node=(Node) event.getSource();
        stage=(Stage) node.getScene().getWindow();
        
        parent=FXMLLoader.load(getClass().getResource("/Productos/ModificarProductos.fxml"));
        
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Modificar Productos");
        stage.show();

    }

    @FXML
    void btnEliminar(ActionEvent event) throws IOException {
        node=(Node) event.getSource();
        stage=(Stage) node.getScene().getWindow();
        
        parent=FXMLLoader.load(getClass().getResource("/Productos/EliminarProductos.fxml"));
        
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Eliminar Productos");
        stage.show();
    }
    
    @FXML
    void Cerrar(MouseEvent event) {
        System.exit(0);
    }
}
