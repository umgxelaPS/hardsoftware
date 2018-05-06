/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dashboard;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DAVID
 */
public class DashboardController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }   
    AnchorPane homePane;
    
    @FXML
    private Pane pane;
    
    @FXML
    private JFXButton btnClientes;

    @FXML
    private JFXButton btnEmpleados;

    @FXML
    private JFXButton btnProveedores;

    @FXML
    private JFXButton btnProductos;

    @FXML
    private JFXButton btnVentas;

    @FXML
    private JFXButton btnModoPago;
    
    @FXML
    private JFXButton btnReportes;

    @FXML
    private JFXButton btnSalir;
    
    //Variables para Cambio de escenas
    Node node;
    Stage stage;
    Parent parent;
    Scene root;
//    public void setNode(Node node){
//        pane.getChildren().clear();
//        pane.getChildren().add((Node)node);
//    }
//    
//    public void createPage(AnchorPane home, String loc) throws Exception{
//        home=FXMLLoader.load(getClass().getResource(loc));
//        setNode(home);
//    }
    
    
    @FXML
    void btnClientes(ActionEvent event) throws IOException{
        node=(Node) event.getSource();
        stage=(Stage) node.getScene().getWindow();
        
        parent=FXMLLoader.load(getClass().getResource("/Clientes/RegistroClientes.fxml"));
        
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Registro Clientes");
        stage.show();
    }

    @FXML
    void btnEmpleados(ActionEvent event) throws IOException{
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
    void btnProductos(ActionEvent event) throws IOException{
        node=(Node) event.getSource();
        stage=(Stage) node.getScene().getWindow();
        
        parent=FXMLLoader.load(getClass().getResource("/Productos/RegistroProductos.fxml"));
        
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Registro Productos");
        stage.show();
    }

    @FXML
    void btnProveedores(ActionEvent event) throws IOException{
        node=(Node) event.getSource();
        stage=(Stage) node.getScene().getWindow();
        
        parent=FXMLLoader.load(getClass().getResource("/Proveedores/RegistroProveedores.fxml"));
        
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Registro Proveedores");
        stage.show();
    }
    
    @FXML
    void btnModoPago(ActionEvent event) throws IOException{
        node=(Node) event.getSource();
        stage=(Stage) node.getScene().getWindow();
        
        parent=FXMLLoader.load(getClass().getResource("/ModoPago/RegistroModoPago.fxml"));
        
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Registro Modo Pago");
        stage.show();
    }

    @FXML
    void btnReportes(ActionEvent event) throws IOException{
        node=(Node) event.getSource();
        stage=(Stage) node.getScene().getWindow();
        
        parent=FXMLLoader.load(getClass().getResource("/Reportes/Reportes.fxml"));
        
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Reportes");
        stage.show();
    }
    
    @FXML
    void btnVentas(ActionEvent event) {
        
    }

    @FXML
    void btnSalir(ActionEvent event) {
        System.exit(0);
    }
}
