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
public class ClaseCliente {    
    
    public SimpleIntegerProperty Nit = new SimpleIntegerProperty();
    public SimpleStringProperty nombre = new SimpleStringProperty();
    public SimpleStringProperty apellido= new SimpleStringProperty();
    public SimpleStringProperty direccion= new SimpleStringProperty();
    public SimpleIntegerProperty telefono = new SimpleIntegerProperty();
    

    public Integer getNIt(){
	return Nit.get();
    }

    public void setNit(Integer Nit){
        this.Nit = new SimpleIntegerProperty(Nit);
    }

    
    public String getNombre(){
	return nombre.get();
    }

    public void setNombre(String nombre){
	this.nombre = new SimpleStringProperty(nombre);
    }
    
    public String getApellido(){
	return apellido.get();
    }

    public void setApellido(String apellido){
	this.apellido = new SimpleStringProperty(apellido);
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
