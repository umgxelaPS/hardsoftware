/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facturacion;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author martin
 */
public class puntodeventa implements Initializable {

    @FXML
    private TextField searchField;
    @FXML
    private TableView<?> productTableView;
    @FXML
    private TableColumn<?, ?> productColumn;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void logoutAction(ActionEvent event) {
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
    
}
