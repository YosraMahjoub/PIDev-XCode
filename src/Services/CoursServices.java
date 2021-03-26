/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Utils.DB;
import Entities.Cours;
import Entities.Formation;

import IServices.IServicesCours;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HELA
 */
public class CoursServices implements IServicesCours{
      Connection cnx;
    Statement ste;
    PreparedStatement pste;
 //   private static CoursServices instance;

    public CoursServices()  {
          try {
              cnx = DB.getInstance().getConnection();
              ste= cnx.createStatement();
              // pSte =cnx.prepareStatement(sql)
          } catch (SQLException ex) {
              Logger.getLogger(CoursServices.class.getName()).log(Level.SEVERE, null, ex);
          }
    }
    
//    public static CoursServices getInstance() throws SQLException {
//        if(instance==null) 
//            instance=new CoursServices();
//        return instance;
//    }
     @Override
     public void ajouter(Cours c) {
          try {
              //String req = "insert into `cours` ( `titre`, `niveau`,`description`,`durée`,`image`,`formation_id`)  Values ('"+ c.getTitre() +"' , '"+ c.getNiveau() +"','"+ c.getDescription() +"','"+ c.getDuree() +"','"+ c.getFile() +"','"+ c.getF().getFormation_id() + "');";
               String req = "insert into `cours` ( `titre`, `niveau`,`description`,`durée`,`image`,`formation_id`)  Values(?,?,?,?,?,?);";
              PreparedStatement pste = cnx.prepareStatement(req);
               pste.setString(1,c.getTitre());
            pste.setString(2,c.getNiveau());
            pste.setString(3,c.getDescription());
            pste.setString(4,c.getDuree());
            pste.setString(5, c.getFile() );
            
              pste.setInt(6, c.getF().getFormation_id());

              pste.executeUpdate();
          } catch (SQLException ex) {
              System.out.println(ex.getMessage()); 
              ex.printStackTrace();
            //  Logger.getLogger(CoursServices.class.getName()).log(Level.SEVERE, null, ex);
          }
        
     }

//    @Override
//    public void ajouter(Cours c, Formation f) throws SQLException {
//      try {
//              String req = "insert into `cours` ( `titre`, `niveau`,`description`,`durée`,`image`,`formation_id`)  Values ('"+ c.getTitre() +"' , '"+ c.getNiveau() +"','"+ c.getDescription() +"','"+ c.getDuree() +"','"+ c.getFile() +"','"+  5+ "');";
//              
//              ste.executeUpdate(req);
//          } catch (SQLException ex) {
//              System.out.println(ex.getMessage());
//              Logger.getLogger(CoursServices.class.getName()).log(Level.SEVERE, null, ex);
//          } 
//    }

    @Override
    public void delete(Cours c) throws SQLException {
      String req = "delete from formation where " + "formation_id=?";
    pste=cnx.prepareStatement(req);
    }

    @Override
    public void update(Cours c) throws SQLException {
        try {
            
            PreparedStatement pste = cnx.prepareStatement("update cours set titre=?, niveau=?, description=? , duree=? where id_cours=?");
            pste.setString(1,c.getTitre());
            pste.setString(2,c.getNiveau());
            pste.setString(3,c.getDescription());
            pste.setString(4,c.getDuree());
            pste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CoursServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Cours> readAll(Cours c) throws SQLException {
        List <Cours> cours =   new ArrayList<>();
             PreparedStatement pste = cnx.prepareStatement( "select * from cours where titre=? and  niveau=? and description=? and duree=? ");
             pste.setString(1,c.getTitre());
            pste.setString(2,c.getNiveau());
            pste.setString(3,c.getDescription());
            pste.setString(4,c.getDuree());
            pste.executeQuery();       
           ResultSet rs= pste.executeQuery();
                while(rs.next()){
        //    cours.add(new Cours(rs.getString("titre"), rs.getString("niveau"), rs.getString("description"),rs.getString("durée"),rs.getInt("formation_id")));
        //cours.add(c);
                }
           return cours;
    }
    


// 
        public List<Cours> readAll(int i )  {
          List <Cours> cours = new   ArrayList<Cours>();
            try {
           // String req1= "select * from cours  where formation_id=?";
               String req = "select * from cours  where formation_id= "+i;
                       //f.getFormation_id();
             // String req = "select c.titre,c.description,c.durée,c.niveau,c.image,count(c.cours_id),c.formation_id from cours c  join formation f on f.formation_id=c.formation_id group by c.formation_id";
              
              Statement ste = cnx.createStatement();
             // pste.setInt(1, f.getFormation_id());
              ResultSet rs= ste.executeQuery(req);
              
              while(rs.next()){
                  Cours c = new Cours();
                 
                  c.setFormation_id(rs.getInt("formation_id"));
                  c.setTitre(rs.getString("titre"));
                  c.setDescription(rs.getString("description"));
                  c.setDuree(rs.getString("durée"));
                  c.setNiveau(rs.getString("niveau"));
                  c.setFile(rs.getString("image"));
                  cours.add(c);
                  
                  System.out.println("hhhh");
                  cours.forEach(System.out::println);
              }
              
              
          } catch (SQLException ex) {
              Logger.getLogger(CoursServices.class.getName()).log(Level.SEVERE, null, ex);
          }
    return cours;
    
}

    @Override
    public List<Cours> readAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public List<Cours> readAll(Formation f )  {
          List <Cours> cours = new   ArrayList<Cours>();
            try {
           //String req1= "select * from cours  where formation_id=?";
               String req = "select * from cours  where formation_id= "+f.getFormation_id();
                       //f.getFormation_id();
             // String req = "select c.titre,c.description,c.durée,c.niveau,c.image,count(c.cours_id),c.formation_id from cours c  join formation f on f.formation_id=c.formation_id group by c.formation_id";
              
              Statement ste = cnx.createStatement();
             // pste.setInt(1, f.getFormation_id());
              ResultSet rs= ste.executeQuery(req);
              
              while(rs.next()){
                  Cours c = new Cours();
                 
                  c.setFormation_id(rs.getInt("formation_id"));
                  c.setTitre(rs.getString("titre"));
                  c.setDescription(rs.getString("description"));
                  c.setDuree(rs.getString("durée"));
                  c.setNiveau(rs.getString("niveau"));
                  c.setFile(rs.getString("image"));
                  cours.add(c);
                  
                  System.out.println("hhhh");
                  cours.forEach(System.out::println);
              }
              
              
          } catch (SQLException ex) {
              Logger.getLogger(CoursServices.class.getName()).log(Level.SEVERE, null, ex);
          }
             return cours;
    
    }
}
