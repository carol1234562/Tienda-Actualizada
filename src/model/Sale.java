package model;

import java.util.ArrayList;

public class Sale {
//declaracion de productos como arraylist
    private Client name;
    private ArrayList<Product> products;
    private double amount;

    public Sale(Client name, ArrayList<Product> products, double amount) {
        this.name = name;
        this.products = products;
        this.amount = amount;
    }

    public Client getName() {
        return name;
    }

    public void setName(Client name) {
        this.name = name;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "Cliente= " + name + 
                "products= " + products 
                + ", amount=" + amount + '}';
    }
    

}