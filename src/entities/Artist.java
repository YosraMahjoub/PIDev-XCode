/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author asus
 */
public class Artist  extends User{
     
    private String[] catg;
    
    
    public Artist(){
        
    }
    public Artist(int user_id, int num_tel, String nom , String prenom ,String adresse ,String role,String username, String password,String email,String image,String bio, String[] catg){
        super( user_id,  num_tel, nom ,  prenom , adresse ,role,username,password, email,image, bio);
        this.catg=catg;
    }

    /**
     * @return the catg
     */
    public String[] getCatg() {
        return catg;
    }

    /**
     * @param catg the catg to set
     */
    public void setCatg(String[] catg) {
        this.catg = catg;
    }
    
   
}
