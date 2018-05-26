/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facturacion.pagos;

/**
 *
 * @author Marlon Mendez
 */
public class fact {
    
    private int numeroFactura;
    private int nitCliente,modo_pago; 
    private String nombreCliente;
    private double subtotal_factura,IVA,total_factura;
    private String fecha_emision;

    public fact(int numeroFactura, int nitCliente, int modo_pago, String nombreCliente, String fecha_emision) {
        this.numeroFactura = numeroFactura;
        this.nitCliente = nitCliente;
        this.modo_pago = modo_pago;
        this.nombreCliente = nombreCliente;
        this.fecha_emision = fecha_emision;
    }

    

    public int getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(int numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public int getNitCliente() {
        return nitCliente;
    }

    public void setNitCliente(int nitCliente) {
        this.nitCliente = nitCliente;
    }

    public int getModo_pago() {
        return modo_pago;
    }

    public void setModo_pago(int modo_pago) {
        this.modo_pago = modo_pago;
    }

    public double getSubtotal_factura() {
        return subtotal_factura;
    }

    public void setSubtotal_factura(double subtotal_factura) {
        this.subtotal_factura = subtotal_factura;
    }

    public double getIVA() {
        return IVA;
    }

    public void setIVA(double IVA) {
        this.IVA = IVA;
    }

    public double getTotal_factura() {
        return total_factura;
    }

    public void setTotal_factura(double total_factura) {
        this.total_factura = total_factura;
    }

    public String getFecha_emision() {
        return fecha_emision;
    }

    public void setFecha_emision(String fecha_emision) {
        this.fecha_emision = fecha_emision;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }
    
}
