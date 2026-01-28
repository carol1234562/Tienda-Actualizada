/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author hanks
 */
public class Employee extends Person {

    public Employee(int id, String name, String contraseña) {
        super(id, name, contraseña);
    }

    @Override
    public boolean login(int id, String name, String contraseña) {
        return this.id == (id)
                && this.name.equalsIgnoreCase(name)
                && this.contraseña.equalsIgnoreCase(contraseña);
    }

}
