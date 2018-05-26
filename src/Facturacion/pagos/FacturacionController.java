/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facturacion.pagos;

import BD.ConexionBD;
import Empleados.RegistroEmpleadosController;
import Facturacion.articulo;
import Reportes.ClaseProducto;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author martin
 */
public class FacturacionController implements Initializable {

    @FXML
    private TextField totalAmountField;
    @FXML
    private TextField paidAmountField;
    @FXML
    private double netPrice;
    private ObservableList<articulo> items;
    private modeloEmpleado employeeModel;
    private modeloProducto productModel;
    private modeloVentas salesModel;
    private modelofactura invoiceModel;
    private pago payment;

    private double xOffset = 0;
    private double yOffset = 0;
    //Variables para Cambio de escenas
    Node node;
    Stage stage;
    Parent parent;
    Scene root;
    //Instancia Conexión BD
    ConexionBD conectar = new ConexionBD();
    Connection con = conectar.conexion();
    PreparedStatement preparar;
    ResultSet result;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        totalAmountField.setText(String.valueOf(netPrice));
        System.out.println("Total en facturacioncontroller: " + netPrice);
    }

    @FXML
    private void closeAction(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private void confirmAction(ActionEvent event) throws IOException {
        try {
            if (validateInput()) {
                double paid = Double.parseDouble(paidAmountField.getText().trim());
                double retail = Math.abs(paid - netPrice);
                int nitCliente = 0, modopago = 0;
                String cliente = "", fecha = "";

                String invoiceId = String.valueOf(new Timestamp(System.currentTimeMillis()).getTime());
                System.out.println("Factuacion controller: " + invoiceId);
for (articulo i : items){
    System.out.println("Item: "+i.getIdItem()+"\nProducto:"+i.getItemName());
}
                /* fact venta = new fact(
                Integer.parseInt(invoiceId), nitCliente, modopago, cliente, fecha
                );*/
                //Sentencia SQL**************
                String capturadol = "";
                String capturado2 = "";
                String sql = "SELECT MAX(no_factura) AS no_factura FROM factura";

                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    capturadol = rs.getString("no_factura");

                }
                System.out.println("No factura"+capturadol);
                //***************
             /*/  String sql;

                //Guardar Registros en BD
                sql = "INSERT INTO bd_hardsoftware.factura (no_factura,nit_cliente,nombre_cliente,fecha_emision,modo_pago) VALUES (?,?,?,?,?);";

                preparar = con.prepareStatement(sql);
                preparar.setInt(1, Integer.parseInt(invoiceId));
                preparar.setInt(2, nitCliente);
                preparar.setInt(3, modopago);
                preparar.setString(4, cliente);
                preparar.setString(5, fecha);
                preparar.executeUpdate();
*/
                //invoiceModel.saveInvoice(invoice);
                //*****Importantisimo************

                /* for (articulo i : items) {
                
                Producto p = productModel.getProductByName(i.getItemName());
                double quantity = p.getQuantity() - i.getQuantity();
                p.setQuantity(quantity);
                productModel.decreaseProduct(p);
                
                Sale sale = new Sale(
                invoiceModel.getInvoice(invoiceId),
                productModel.getProductByName(i.getItemName()),
                i.getQuantity(),
                i.getUnitPrice(),
                i.getTotal()
                );
                
                salesModel.saveSale(sale);
                }*/
                FXMLLoader loader = new FXMLLoader((getClass().getResource("/Facturacion/pagos/confirmar.fxml")));
                ConfirmarController controller = new ConfirmarController();
                //Datos importante
                //controller.setData(retail, items, invoiceId);
                loader.setController(controller);
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                root.setOnMousePressed((MouseEvent e) -> {
                    xOffset = e.getSceneX();
                    yOffset = e.getSceneY();
                });
                root.setOnMouseDragged((MouseEvent e) -> {
                    stage.setX(e.getScreenX() - xOffset);
                    stage.setY(e.getScreenY() - yOffset);
                });
                stage.setTitle("Confirmar");
                //stage.getIcons().add(new Image("Imagenes/logo.png"));
                stage.setScene(scene);
                stage.show();
                //Variables para Cambio de escenas

            }
            /*
       node=(Node) event.getSource();
        stage=(Stage) node.getScene().getWindow();
        
        parent=FXMLLoader.load(getClass().getResource("/Facturacion/pagos/confirmar.fxml"));
        
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Registro Empleados");
        stage.show();
             */
        } catch (Exception e) {
            System.out.println("Error en Facturacion controller:" + e);
        }

    }

    public void setData(double netPrice, ObservableList<articulo> items, pago payment) {

        this.netPrice = netPrice;
        this.items = FXCollections.observableArrayList(items);
        this.payment = payment;
    }

    private boolean validateInput() {

        String errorMessage = "";

        if (paidAmountField.getText() == null || paidAmountField.getText().length() == 0) {
            errorMessage += "Entrda no valida!\n";
        } else if (Double.parseDouble(paidAmountField.getText()) < netPrice) {
            errorMessage += "Entrada insuficiente!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alerta");
            alert.setHeaderText("Por favor ingrese un monto valido");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            paidAmountField.setText("");

            return false;
        }
    }

}
