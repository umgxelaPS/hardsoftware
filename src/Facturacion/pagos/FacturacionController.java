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
import javafx.scene.control.TextField;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void closeAction(ActionEvent event) {
    }

    @FXML
    private void confirmAction(ActionEvent event) {
    }
    
}
