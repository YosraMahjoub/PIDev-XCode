/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.chart.XYChart;
import entities.Oeuvre;
import entities.RatingO;
import utils.ConnexionDB;

/**
 *
 * @author pc
 */
public class RatigoService {
    Connection conx = ConnexionDB.getInstance().getConnection();
    
       public void creerRo(float a, Oeuvre o) {
         String req = "INSERT INTO `rating_oeuvre`(`user_id`, `oeuvrage_id`, `note`) VALUES (?,?,?)";
        try {
            PreparedStatement ps = conx.prepareStatement(req);
            
            ps.setInt(1,1);
            ps.setInt(2, o.getOeuvrage_id());
            ps.setFloat(3, a);
             ps.executeUpdate();
           
        } catch (SQLException ex) {
            Logger.getLogger(OeuvrageService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       public void modifierRo(float a, Oeuvre o) {
         String req = "UPDATE `rating_oeuvre` SET `note`=? WHERE oeuvrage_id ="+o.getOeuvrage_id();
        try {
            PreparedStatement ps = conx.prepareStatement(req);
            
           
            ps.setFloat(1, a);
             ps.executeUpdate();
           
        } catch (SQLException ex) {
            Logger.getLogger(OeuvrageService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void supprimerRo(RatingO r) {
        String req = "DELETE FROM `rating_oeuvre` WHERE `rating_oeuvre_id`=?";
           
        try {
            PreparedStatement ps = conx.prepareStatement(req);
            ps.setInt(1,r.getRating_oeuvre_id());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OeuvrageService.class.getName()).log(Level.SEVERE, null, ex);
        }  
}
    
 
public float pourcentage( Oeuvre o) {
            String req = "SELECT COUNT(user_id) AS Nb,SUM(note) AS NOTE FROM rating_oeuvre GROUP BY oeuvrage_id HAVING oeuvrage_id ="+o.getOeuvrage_id();
            float a = 0;
            float b = 0;
          try {  
            Statement ste = conx.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            if (rs.next()) {
                
                b =rs.getFloat("nb");
                a =rs.getFloat("NOTE");
                
            }
          } catch (SQLException ex) {
            Logger.getLogger(RatigoService.class.getName()).log(Level.SEVERE, null, ex);
        }
return a/b;
}
public int nbu( Oeuvre o) {
            String req = "SELECT COUNT(user_id) AS Nb FROM rating_oeuvre GROUP BY oeuvrage_id HAVING oeuvrage_id ="+o.getOeuvrage_id();
            
            int b = 0;
          try {  
            Statement ste = conx.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            if (rs.next()) {
                
                b =rs.getInt("nb");
           }
          } catch (SQLException ex) {
            Logger.getLogger(RatigoService.class.getName()).log(Level.SEVERE, null, ex);
        }
return b;
}
public int israted( Oeuvre o) {
            String req = "   SELECT user_id,count(oeuvrage_id) as nb FROM rating_oeuvre GROUP BY oeuvrage_id HAVING oeuvrage_id="+o.getOeuvrage_id()+" and user_id=1";
             int b = 0;
          try {  
            Statement ste = conx.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            if (rs.next()) {
                  b =rs.getInt("nb");
           }
            
          } catch (SQLException ex) {
            Logger.getLogger(RatigoService.class.getName()).log(Level.SEVERE, null, ex);
        }
return b;
}
public List<Oeuvre> afflr() {
            String req = "SELECT isvalid , o.user_id ,r.oeuvrage_id, `nom`, `domaine`, `prix`, `quantité`, `description`, `image`, AVG(`note`) as rate FROM `rating_oeuvre` r INNER join oeuvrage o on r.oeuvrage_id = o.oeuvrage_id GROUP by r.oeuvrage_id ORDER by rate DESC; ";
         List<Oeuvre> listO =new ArrayList<>();
        
        try {
           Statement ste = conx.createStatement();
        
            ResultSet rs = ste.executeQuery(req);
            
            
            while(rs.next()){
                Oeuvre o1 =new Oeuvre();
                o1.setOeuvrage_id(rs.getInt("Oeuvrage_id"));
                o1.setNom(rs.getString("nom"));
                o1.setDoamine(rs.getString("domaine"));
                o1.setPrix(rs.getFloat("prix"));
                o1.setQuantite(rs.getFloat("quantité"));
                o1.setDescription(rs.getString("description"));
                o1.setImg(rs.getString("image"));
                o1.setIsvalid(rs.getInt("isvalid"));
              o1.setRate(rs.getFloat("rate"));
                listO.add(o1);
                System.out.println("YBDA");
                listO.forEach(System.out::println);
                System.out.println("YOUFA");
            }
}catch (SQLException ex) {
            Logger.getLogger(OeuvrageService.class.getName()).log(Level.SEVERE, null, ex);   
        }
        
        return listO;

}
public  XYChart.Series<String, Float>  domainrate() {
        
            XYChart.Series<String, Float> serie = new XYChart.Series<>();
            
          try {  
            String req="SELECT COUNT(oeuvrage_id) as nboeuvre , domaine FROM oeuvrage group by domaine";
            
            Statement ste=conx.createStatement();
            ResultSet rs=ste.executeQuery(req);
            while (rs.next()){
                serie.getData().add(new XYChart.Data<>(rs.getString("domaine"), rs.getFloat("nboeuvre")));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(RatigoService.class.getName()).log(Level.SEVERE, null, ex);
        }
    return serie;
}

public  XYChart.Series<String, Float>  firstP() {
        
            XYChart.Series<String, Float> serie = new XYChart.Series<>();
            
          try {  
            String req="select domaine, oeuvrage_id , max(rate) as mrate from (select o.domaine, r.oeuvrage_id , AVG(`note`) as rate FROM `rating_oeuvre` r INNER join oeuvrage o on r.oeuvrage_id = o.oeuvrage_id group by oeuvrage_id ORDER by rate)as t1 group by domaine";
            
            Statement ste=conx.createStatement();
            ResultSet rs=ste.executeQuery(req);
            while (rs.next()){
                serie.getData().add(new XYChart.Data<>(rs.getString("domaine"), rs.getFloat("mrate")));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(RatigoService.class.getName()).log(Level.SEVERE, null, ex);
        }
    return serie;
}

public  XYChart.Series<String, Float>  secondP() {
          XYChart.Series<String, Float> serie = new XYChart.Series<>();
          
          try {  
            String req="select domaine, oeuvrage_id , rate from (select o.domaine, r.oeuvrage_id , AVG(`note`) as rate FROM `rating_oeuvre` r INNER join oeuvrage o on r.oeuvrage_id = o.oeuvrage_id group by oeuvrage_id having  o.domaine = 'artisanat ' ORDER by rate DESC LIMIT 1 OFFSET 1 )\n" +
"as t1 UNION\n" +
"select domaine, oeuvrage_id , rate from (select o.domaine, r.oeuvrage_id , AVG(`note`) as rate FROM `rating_oeuvre` r INNER join oeuvrage o on r.oeuvrage_id = o.oeuvrage_id group by oeuvrage_id having  o.domaine = 'Peinture ' ORDER by rate DESC LIMIT 1 OFFSET 1)\n" +
"as t2\n" +
"UNION\n" +
"select domaine, oeuvrage_id , rate from (select o.domaine, r.oeuvrage_id , AVG(`note`) as rate FROM `rating_oeuvre` r INNER join oeuvrage o on r.oeuvrage_id = o.oeuvrage_id group by oeuvrage_id having  o.domaine = 'sculpture ' ORDER by rate DESC LIMIT 1 OFFSET 1 )\n" +
"as t3\n" +
"UNION\n" +
"select domaine, oeuvrage_id , rate from (select o.domaine, r.oeuvrage_id , AVG(`note`) as rate FROM `rating_oeuvre` r INNER join oeuvrage o on r.oeuvrage_id = o.oeuvrage_id group by oeuvrage_id having  o.domaine = 'litérature ' ORDER by rate DESC LIMIT 1 OFFSET 1)\n" +
"as t4\n" +
"UNION\n" +
"select domaine, oeuvrage_id , rate from (select o.domaine, r.oeuvrage_id , AVG(`note`) as rate FROM `rating_oeuvre` r INNER join oeuvrage o on r.oeuvrage_id = o.oeuvrage_id group by oeuvrage_id having  o.domaine = 'Décoration ' ORDER by rate desc LIMIT 1 OFFSET 1)\n" +
"as t4";
            
            Statement ste=conx.createStatement();
            ResultSet rs=ste.executeQuery(req);
            while (rs.next()){
                serie.getData().add(new XYChart.Data<>(rs.getString("domaine"), rs.getFloat("rate")));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(RatigoService.class.getName()).log(Level.SEVERE, null, ex);
        }
    return serie;
}



}