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
    protected int id; 
    protected String name;
    protected String contraseña; 
     
    //id debe ser 123 
    //contraseña debe ser test 
    public Person(int id, String name, String contraseña) {
        this.id = id;
        this.name = name;
        this.contraseña = contraseña;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    
    //metodo abstracto que hereda a las clases hija 
    public abstract boolean login (int id,String name, String contraseña); 
    
    
}
