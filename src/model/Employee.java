/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import main.Logable;

/**
 *
 * @author hanks
 */
public class Employee extends Person implements Logable {
 
    public static final int EMPLOYEE_ID = 123;
    public static final String PASSWORD = "test"; 
     public Employee() {
        super("empleado");
    }

    @Override
    public boolean login(int id, String contraseña) {
                return id == EMPLOYEE_ID 
                        && contraseña.equalsIgnoreCase(PASSWORD);

    }
}
