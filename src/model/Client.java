/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author hanks
 */
public abstract class Client extends Person {
    
    private int menberld; 
    private Amount balance; 

    public static final int MEMBER_ID =456; 
    public static final double BALANCE = 50.00; 
    
  public Client(int menberld, Amount balance, int id, String name, String contraseña) {
        super(id, name, contraseña);
        this.menberld = MEMBER_ID;
        this.balance = new Amount ((int) BALANCE);
    }
    
    public int getMenberld() {
        return menberld;
    }

    public void setMenberld(int menberld) {
        this.menberld = menberld;
    }

    public Amount getBalance() {
        return balance;
    }

    public void setBalance(Amount balance) {
        this.balance = balance;
    }
    
    

   
    
    
    
}
