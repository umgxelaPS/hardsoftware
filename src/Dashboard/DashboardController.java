/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dashboard;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

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
        // TODO
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


    

    public void setNode(Node node){
        pane.getChildren().clear();
        pane.getChildren().add((Node)node);
    }
    
    public void createPage(AnchorPane home, String loc) throws Exception{
        home=FXMLLoader.load(getClass().getResource(loc));
        setNode(home);
    }
    
    
    @FXML
    void btnClientes(ActionEvent event) throws Exception {
        this.createPage(homePane, "/Clientes/RegistroClientes.fxml");
    }

    @FXML
    void btnEmpleados(ActionEvent event) throws Exception {
        this.createPage(homePane, "/Empleados/RegistroEmpleados.fxml");
    }

    @FXML
    void btnProductos(ActionEvent event) throws Exception {
        this.createPage(homePane, "/Productos/RegistroProductos.fxml");
    }

    @FXML
    void btnProveedores(ActionEvent event) throws Exception {
        this.createPage(homePane, "/Proveedores/RegistroProveedores.fxml");
    }
    
    @FXML
    void btnModoPago(ActionEvent event) throws Exception {
        this.createPage(homePane, "/ModoPago/RegistroModoPago.fxml");
    }

    @FXML
    void btnReportes(ActionEvent event) {

    }
    
    @FXML
    void btnVentas(ActionEvent event) {

    }

    @FXML
    void btnSalir(ActionEvent event) {
        System.exit(0);
    }
}
