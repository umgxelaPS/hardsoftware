/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reportes;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


/**
 *
 * @author DAVID
 */
public class ClaseProducto {    
    
    public SimpleIntegerProperty idProducto = new SimpleIntegerProperty();
    public SimpleIntegerProperty idProveedor = new SimpleIntegerProperty();
    public SimpleStringProperty nombre = new SimpleStringProperty();
    public SimpleStringProperty marca= new SimpleStringProperty();
    public SimpleStringProperty modelo= new SimpleStringProperty();
    public SimpleFloatProperty PrecioC = new SimpleFloatProperty();
    public SimpleFloatProperty PrecioV = new SimpleFloatProperty();
    public SimpleIntegerProperty cantidad = new SimpleIntegerProperty();
    

    public Integer getIdProducto(){
	return idProducto.get();
    }

    public void setIdProducto(Integer idProducto){
        this.idProducto = new SimpleIntegerProperty(idProducto);
    }
    
    public Integer getIdProveedor(){
	return idProveedor.get();
    }

    public void setIdProveedor(Integer idProveedor){
        this.idProveedor = new SimpleIntegerProperty(idProveedor);
    }
    
    public String getNombre(){
	return nombre.get();
    }

    public void setNombre(String nombre){
	this.nombre = new SimpleStringProperty(nombre);
    }
    
    public String getMarca(){
	return marca.get();
    }

    public void setMarca(String marca){
	this.marca = new SimpleStringProperty(marca);
    }
    
    public String getModelo(){
	return modelo.get();
    }

    public void setModelo(String modelo){
	this.modelo = new SimpleStringProperty(modelo);
    }
    
    public Float getPrecioC(){
	return PrecioC.get();
    }

    public void setPrecioC(Float PrecioC){
	this.PrecioC = new SimpleFloatProperty(PrecioC);
    }
    
    public Float getPrecioV(){
	return PrecioV.get();
    }

    public void setPrecioV(Float PrecioV){
	this.PrecioV = new SimpleFloatProperty(PrecioV);
    }
    
    public Integer getCantidad(){
	return cantidad.get();
    }

    public void setIdCantidad(Integer cantidad){
        this.cantidad = new SimpleIntegerProperty(cantidad);
    }
    
}
