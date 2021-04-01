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
public class RatingEntity {
    double value;
    int user_id;
    //int formation_id;
   private  Users u1;
    public Formation f = new Formation();

    public void setF(Formation f) {
        this.f = f;
    }

    public Formation getF() {
        return f;
    }

    public Users getU1() {
        return u1;
    }

    public void setU1(Users u1) {
        this.u1 = u1;
    }
   

    public int getUser_id() {
        return user_id;
    }


    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }


    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
    
}
