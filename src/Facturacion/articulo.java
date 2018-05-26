package Facturacion;

public class articulo {
    private String idItem;
    private String itemName;
    private double unitPrice;
    private double quantity;
    private double total;

    public articulo() {
    }

    public articulo(String idItem,String itemName, double unitPrice, double quantity, double total) {
        this.idItem=idItem;
        this.itemName = itemName;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.total = total;
    }

    public String getIdItem() {
        return idItem;
    }

    public void setIdItem(String idItem) {
        this.idItem = idItem;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Item{" + "itemName=" + itemName + 
                ", unitPrice=" + unitPrice + 
                ", quantity=" + quantity + 
                ", total=" + total + '}';
    }
}
