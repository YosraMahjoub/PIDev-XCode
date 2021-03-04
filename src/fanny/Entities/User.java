/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fanny.Entities;

/**
 *
 * @author ASUS
 */
public class User {
    
    private int Id;
    private String Firstname;
    private String Lastname;
    private String Role;
    private String Passwrd;

    public User() {
    }

    
    
    public User(int Id, String Firstname, String Lastname, String Role, String Passwrd) {
        this.Id = Id;
        this.Firstname = Firstname;
        this.Lastname = Lastname;
        this.Role = Role;
        this.Passwrd = Passwrd;
    }

    public User(String Firstname, String Lastname, String Role, String Passwrd) {
        this.Firstname = Firstname;
        this.Lastname = Lastname;
        this.Role = Role;
        this.Passwrd = Passwrd;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getFirstname() {
        return Firstname;
    }

    public void setFirstname(String Firstname) {
        this.Firstname = Firstname;
    }

    public String getLastname() {
        return Lastname;
    }

    public void setLastname(String Lastname) {
        this.Lastname = Lastname;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }

    public String getPasswrd() {
        return Passwrd;
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj); //To change body of generated methods, choose Tools | Templates.
    }

    public void setPasswrd(String Passwrd) {
        this.Passwrd = Passwrd;
    }

    
}
