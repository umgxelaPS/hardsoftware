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
public class ClaseModoPago {    
    
    public SimpleIntegerProperty ID = new SimpleIntegerProperty();
    public SimpleStringProperty Descripcion = new SimpleStringProperty();

    

    public Integer getID(){
	return ID.get();
    }

    public void setID(Integer ID){
        this.ID = new SimpleIntegerProperty(ID);
    }

    
    public String getDescripcion(){
	return Descripcion.get();
    }

    public void setDescripcion(String Descripcion){
	this.Descripcion = new SimpleStringProperty(Descripcion);
    }
    
   
    
}
