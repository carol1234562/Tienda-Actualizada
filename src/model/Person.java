/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author hanks
 */
public abstract class Person {
    //protected significa que puede ser accedido 
    //por la misma clase, subclases 
    protected String name;
     
    //id debe ser 123 
    //contraseña debe ser test 
    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
