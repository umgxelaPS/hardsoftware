/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facturacion.pagos;

import Facturacion.articulo;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author martin
 */
public class ConfirmarController implements Initializable {

    @FXML
    private TextArea billingArea;
    @FXML
    private Label retailLabel;

    private double retail;
    private ObservableList<articulo> items;
    private String barcode;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        retailLabel.setText("Cambio: Q " + retail);

        StringBuilder details = new StringBuilder("Item Name\t\t" + "Cost\t\t" + "Quantity\t\t" + "Total\n");

        /* for (Producto i : items) {
        details.append(i.getItemName())
        .append("\t\t\t")
        .append(i.getUnitPrice())
        .append("\t\t\t")
        .append(i.getQuantity())
        .append("\t\t\t")
        .append(i.getTotal())
        .append("\n");
        }*/
    }    

    @FXML
    private void doneAction(ActionEvent event) {
         billingArea.setText("");
       // PrintInvoice pi = new PrintInvoice(items, barcode);
       // pi.generateReport();
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    void setData(double retail, ObservableList<articulo> items, String invoiceId) {
     /*
        this.retail = retail;
        this.items = FXCollections.observableArrayList(items);
        this.barcode = barcode;
        */   
    }
    
}
