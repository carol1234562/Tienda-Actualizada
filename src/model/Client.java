/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import main.Payable;

/**
 *
 * @author hanks
 */
public class Client extends Person implements Payable {
    
    private int menberid; 
    private Amount balance; 
//definir constantes finales 
    public static final int MEMBER_ID =456; 
    public static final double BALANCE = 50.00; 
    
  public Client(String name) {
        super(name);
        this.menberid = MEMBER_ID;
        this.balance = new Amount(BALANCE);
    }
    @Override
    public boolean pay(Amount amount){
        double nuevoSaldo = balance.getValue() - amount.getValue();
        balance.setValue(nuevoSaldo);
        return nuevoSaldo >=0; 
    }

    public int getMenberid() {
        return menberid;
    }

    public void setMenberid(int menberid) {
        this.menberid = menberid;
    }

    public Amount getBalance() {
        return balance;
    }

    public void setBalance(Amount balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "ID = " + menberid + ", Total = " + balance + " , " ;
    }
    
}
