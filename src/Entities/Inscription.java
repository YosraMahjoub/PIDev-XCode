/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Utils.DB;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

/**
 *
 * @author HELA
 */
public class Inscription {
    public Users u1 = new Users();
  public Formation f = new Formation();
  public int isinscrit;
    SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
Date date = new Date(System.currentTimeMillis());
//System.out.println(formatter.format(date));

    public Inscription(Users u1, Formation f) {
        this.u1 = u1;
        this.f = f;
    }

    public Inscription() {
        
    }

    public Date getDate() {
        return date;
    }

    public void setU1(Users u1) {
        this.u1 = u1;
    }

    public void setF(Formation f) {
        this.f = f;
    }

    public void setFormatter(SimpleDateFormat formatter) {
        this.formatter = formatter;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setIsinscrit(int isinscrit) {
        this.isinscrit = isinscrit;
    }




   
     
}
