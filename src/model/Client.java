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
    
  public Client(int menberid, Amount balance, int id, String name, String contraseña) {
        super(id, name, contraseña);
        this.menberid = MEMBER_ID;
        this.balance = new Amount ((int) BALANCE);
    }
    public boolean pay (Amount amount){
      double saldoActual = balance.getValue();
      //declaramos 
      double nuevoSaldo = saldoActual - amount.getValue(); 
      balance.setValue(nuevoSaldo);
      
      if (nuevoSaldo < 0 ){
          System.out.println("Queda una deuda de " + nuevoSaldo + "euros.");
          return false; 
      }else {
          return true; 
      }
    }
    public int getMenberid() {
        return menberid;
    }

    public void setMenberld(int menberid) {
        this.menberid = menberid;
    }

    public Amount getBalance() {
        return balance;
    }

    public void setBalance(Amount balance) {
        this.balance = balance;
    }
}
