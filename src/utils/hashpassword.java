/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author asus
 */




public class hashpassword {
    public hashpassword(){
        
    }
    
    
    public String hashPassword(String plainTextPassword){
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
	}
    
    
    
    public boolean checkPass(String plainPassword, String hashedPassword) {
        boolean x ;
        if (BCrypt.checkpw(plainPassword, hashedPassword)){
                x= true; }
        else{
                x=false;
        }
        return x;
    }
}
