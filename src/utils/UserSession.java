/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.HashSet;
import java.util.Set;

public final class UserSession {

    private static UserSession instance;
    
    
   private int user_id, num_tel;
    private String nom, prenom , adresse, email,username,role, password, image, bio;
    
   

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

   

  
    public UserSession(String nom , String prenom ,String role,String adresse ,String username, String password,String email,String image, String bio){
    
    this.num_tel= num_tel;
    this.nom=nom;
    this.prenom=prenom;
    this.username=username;
    this.password=password;
    this.email=email;
    this.adresse=adresse;
    this.role=role;
    this.image=image;
    this.bio=bio;
    }

    public static UserSession getInstance(String nom , String prenom ,String role,String adresse ,String username, String password,String email,String image, String bio) {
        if(instance == null) {
            System.out.println("ok");
            instance = new UserSession(nom,prenom,role,adresse,username,password,email,image,bio);
        }
        System.out.println("non");
        return instance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getUserName() {
        return username;
    }

    public String getRoles() {
        return role;
    }

    public void cleanUserSession() {
        username = "";
        email="";
        password="";
        role = "";
        nom="";
        prenom="";
        image="";
        instance=null;
    }

    @Override
    public String toString() {
        return "UserSession{" + "userName=" + username + ", email=" + email + ", password=" + password + ", roles=" + role + ", nom=" + nom + ", prenom=" + prenom + ", image=" + image + '}';
    }

   
 

    

    
}
