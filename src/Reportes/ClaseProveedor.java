/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reportes;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


/**
 *
 * @author DAVID
 */
public class ClaseProveedor {    
    
    public SimpleIntegerProperty idProveedor = new SimpleIntegerProperty();
    public SimpleStringProperty nombre = new SimpleStringProperty();
    public SimpleStringProperty direccion= new SimpleStringProperty();
    public SimpleIntegerProperty telefono = new SimpleIntegerProperty();
    

    public Integer getIdProveedor(){
	return idProveedor.get();
    }

    public void setidProveedor(Integer idProveedor){
        this.idProveedor = new SimpleIntegerProperty(idProveedor);
    }

    
    public String getNombre(){
	return nombre.get();
    }

    public void setNombre(String nombre){
	this.nombre = new SimpleStringProperty(nombre);
    }
    
    public String getDireccion(){
	return direccion.get();
    }

    public void setDireccion(String direccion){
	this.direccion = new SimpleStringProperty(direccion);
    }
    
    public Integer getTelefono(){
	return telefono.get();
    }

    public void setTelefono(Integer telefono){
        this.telefono = new SimpleIntegerProperty(telefono);
    }
    
}
