/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import BD.ConexionBD;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Marlon Mendez
 */
public class OlvidoContraseniaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        final Tooltip tooltipButon = new Tooltip();
        tooltipButon.setText("Regresar a Login");
        volver.setTooltip(tooltipButon);
    }
    //Declaración de Botones
    @FXML
    private TextField txtDpi;
    @FXML
    private TextField txtNomUsuario;
    @FXML
    private PasswordField txtcontrasenia;
    @FXML
    private Button btnbuscar;
    @FXML
    private Button volver;
    //Instancia Conexión BD
    ConexionBD conectar = new ConexionBD();
    Connection con = conectar.conexion();
    PreparedStatement preparar;
    PreparedStatement preparar2;
    ResultSet result;
    ResultSet result2;
    //VariablesGlobales
    String dpi;
    //Sentencia SQL
    String consultar;
    String actualizar;
    //Variables para Cambio de escenas
    Node node;
    Stage stage;
    Parent parent;
    Scene root;

    @FXML
    private void ConsultaryModificar(ActionEvent event) throws IOException {
        con = conectar.conexion();
        consultar = "SELECT * FROM usuario WHERE dpi_empleado='".concat(txtDpi.getText()).concat("'");

        try {
            preparar = con.prepareStatement(consultar);
            result = preparar.executeQuery();

            while (result.next()) {
                dpi = (result.getString(2));
            }
            if (dpi.equals(txtDpi.getText())) {
                actualizar = "UPDATE usuario SET contraseña='".concat(txtcontrasenia.getText()).concat("'");
                try {
                    preparar2 = con.prepareStatement(actualizar);
                    preparar2.executeUpdate();
                    txtDpi.setText("");
                    txtcontrasenia.setText("");
                    JOptionPane.showMessageDialog(null, "SE HA MODIFICADO EXITOSAMENTE LA CONTRASEÑA");
                    node = (Node) event.getSource();
                    stage = (Stage) node.getScene().getWindow();
                    parent = FXMLLoader.load(getClass().getResource("/Login/F_Login.fxml"));
                    Scene scene = new Scene(parent);
                    stage.setScene(scene);
                    stage.centerOnScreen();
                    stage.setResizable(false);
                    stage.setTitle("Loggin");
                    stage.show();
                } catch (SQLException ex) {

                    JOptionPane.showMessageDialog(null, "NO SE PUDO REALIZAR EL CAMBIO", "Error", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "EL NUMERO DE DPI QUE INGRESÓ NO ESTA REGISTRADO", "Error", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERROR AL LEER CODIGO", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    @FXML
    private void btnVolver(ActionEvent event) throws IOException {
        node = (Node) event.getSource();
        stage = (Stage) node.getScene().getWindow();
        parent = FXMLLoader.load(getClass().getResource("/Login/F_Login.fxml"));
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.setTitle("Login");
        stage.show();
    }
}
