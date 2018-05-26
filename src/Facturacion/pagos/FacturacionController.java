/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facturacion.pagos;

import Facturacion.articulo;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
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
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

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
      try{
         
           if (validateInput()) {
                System.out.println("DATO");
          double paid = Double.parseDouble(paidAmountField.getText().trim());
          double retail = Math.abs(paid - netPrice);
          
               
          String invoiceId = String.valueOf(new Timestamp(System.currentTimeMillis()).getTime());
          
          System.out.println("Pago: "+paid+"\nVenta: "+retail+"\nInvocedID: "+invoiceId);
 
          //Nit cliente,nombre cliente,fechapago,modopago,subtotalfac,iva,totalfac
          /*
          factura invoice = new factura(
          invoiceId,
          employeeModel.getEmployee(2),
          payment.getSubTotal(),
          payment.getVat(),
          payment.getDiscount(),
          payment.getPayable(),
          paid,
          retail
          );*/

            //invoiceModel.saveInvoice(invoice);
            //*****Importantisimo************
            //items.forEach((i) -> {
                /*Producto p = productModel.getProductByName(i.getItemName());
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
                
                salesModel.saveSale(sale);*/
             //   System.out.println("Facturacion controller: "+i.getItemName());
           //});
            /*try {
                 FXMLLoader loader = new FXMLLoader((getClass().getResource("/Facturacion/pagos/confirmar.fxml")));
                ConfirmarController controller = new ConfirmarController();
                //Datos importante
                controller.setData(retail, items, invoiceId);
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
                stage.setTitle("Confirm");
                stage.getIcons().add(new Image("/Imagenes/logo.png"));
                stage.setScene(scene);
                stage.show();
            //Variables para Cambio de escenas
       
            } catch (Exception e) {
                System.out.println("Error en Facturacion Controller: " + e);
            }*/
            
        }
      /*
       node=(Node) event.getSource();
        stage=(Stage) node.getScene().getWindow();
        
        parent=FXMLLoader.load(getClass().getResource("/Facturacion/pagos/confirmar.fxml"));
        
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Registro Empleados");
        stage.show();  */
      }catch(Exception e){
          System.out.println("Facturacion controller: "+e);
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
