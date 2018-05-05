/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reportes;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


/**
 *
 * @author DAVID
 */
public class ClaseEmpleado {    
    
    public SimpleDoubleProperty DPI = new SimpleDoubleProperty();
    public SimpleStringProperty nombre = new SimpleStringProperty();
    public SimpleStringProperty apellido = new SimpleStringProperty();
    public SimpleStringProperty nacimiento= new SimpleStringProperty();
    public SimpleStringProperty cargo= new SimpleStringProperty();
    public SimpleIntegerProperty salario = new SimpleIntegerProperty();
    public SimpleStringProperty direccion = new SimpleStringProperty();
    public SimpleIntegerProperty telefono = new SimpleIntegerProperty();
    

    public Double getDPI(){
	return DPI.get();
    }

    public void setDPI(Double DPI){
        this.DPI = new SimpleDoubleProperty(DPI);
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
    
    public String getNacimiento(){
	return nacimiento.get();
    }

    public void setNacimiento(String nacimiento){
        this.nacimiento = new SimpleStringProperty(nacimiento);
    }
    
    public String getCargo(){
	return cargo.get();
    }

    public void setCargo(String cargo){
        this.cargo = new SimpleStringProperty(cargo);
    }
    
    public Integer getSalario(){
	return salario.get();
    }

    public void setSalario(Integer salario){
        this.salario = new SimpleIntegerProperty(salario);
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
