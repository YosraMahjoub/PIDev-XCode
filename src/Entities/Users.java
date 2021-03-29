/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author HELA
 */
public class Users {
    private int user_id;
    private String nom;
    private String prenom;
    private String username;
    private int password;
    private String role;
    private String adresse;
    private int num_tel;
    private String email;
    private String image; 
    
    
public Users(){}

    public Users(int user_id) {
        this.user_id = user_id;
    }


    public Users( String nom, String prenom,String username, int password, String role, String adresse, int num_tel, String email, String image) {
        
        this.nom = nom;
        this.prenom = prenom;
        this.username=username;
        this.password = password;
        this.role = role;
        this.adresse = adresse;
        this.num_tel = num_tel;
        this.email = email;
        this.image = image;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public int getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getAdresse() {
        return adresse;
    }

    public int getNum_tel() {
        return num_tel;
    }

    public String getEmail() {
        return email;
    }

    public String getImage() {
        return image;
    }

    public String getUsername() {
        return username;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setNum_tel(int num_tel) {
        this.num_tel = num_tel;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    
    
    
    
    
}
