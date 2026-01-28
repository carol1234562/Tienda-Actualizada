package model;

import java.util.ArrayList;

public class Sale {
//declaracion de productos como arraylist
    private Client clientName;
    private ArrayList<Product> products;
    private double amount;

    public Sale(String clientName, double amount) {
        super();
        this.amount = amount;
    }
   
    public Client getClient() {
        return clientName;
    }

    public void setClient(Client client) {
        this.clientName = client;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Venta [Cliente = " + clientName + " , Monto = " + amount + " ]";
    }

}
