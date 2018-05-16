package Facturacion;

import Reportes.ClaseProducto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public interface ProductoInterfaz {
    
    public ObservableList<ClaseProducto> PRODUCTLIST = FXCollections.observableArrayList();   
}
