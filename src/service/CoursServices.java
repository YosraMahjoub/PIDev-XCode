/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import utils.ConnexionDB;
import entities.Cours;
import entities.Formation;

import Iservice.IServicesCours;
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
              cnx = ConnexionDB.getInstance().getConnection();
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
     
     public void ajouter(Cours c ,int i) {
          try {
              //String req = "insert into `cours` ( `titre`, `niveau`,`description`,`durée`,`image`,`formation_id`)  Values ('"+ c.getTitre() +"' , '"+ c.getNiveau() +"','"+ c.getDescription() +"','"+ c.getDuree() +"','"+ c.getFile() +"','"+ c.getF().getFormation_id() + "');";
               String req = "insert into `cours` ( `titre`, `niveau`,`description`,`durée`,`image`,`formation_id`)  Values(?,?,?,?,?,?);";
              PreparedStatement pste = cnx.prepareStatement(req);
               pste.setString(1,c.getTitre());
            pste.setString(2,c.getNiveau());
            pste.setString(3,c.getDescription());
            pste.setString(4,c.getDuree());
            pste.setString(5, c.getFile() );
         pste.setInt(6,i);//prob

              pste.executeUpdate();
              System.out.println("error");
          } catch (SQLException ex) {
              System.out.println(ex.getMessage()); 
              ex.printStackTrace();
            //  Logger.getLogger(CoursServices.class.getName()).log(Level.SEVERE, null, ex);
          }
        
     }
//
    @Override
    public void ajouter(Cours c) throws SQLException {
      try {
     String req = "insert into `cours` ( `titre`, `niveau`,`description`,`durée`,`image`,`formation_id`)  Values ('"+ c.getTitre() +"' , '"+ c.getNiveau() +"','"+ c.getDescription() +"','"+ c.getDuree() +"','"+ c.getFile() +"','"+  5+ "');";
              
              ste.executeUpdate(req);
          } catch (SQLException ex) {
              System.out.println(ex.getMessage());
              Logger.getLogger(CoursServices.class.getName()).log(Level.SEVERE, null, ex);
          } 
    }

            @Override
            public void delete(Cours c) throws SQLException {
              String req = "delete from formation where " + "formation_id=?";
            pste=cnx.prepareStatement(req);
            }

    @Override
    public void update(Cours c) throws SQLException {
        try {
            
            PreparedStatement pste = cnx.prepareStatement("update cours set titre=?, niveau=?, description=? , durée=?, image=? where cours_id="+c.getCours_id());
            pste.setString(1,c.getTitre());
            pste.setString(2,c.getNiveau());
            pste.setString(3,c.getDescription());
            pste.setString(4,c.getDuree());
            pste.setString(5, c.getFile());
            //pste.setInt(6, 4);
            pste.executeUpdate();
            System.out.println("nekhdem");
        } catch (SQLException ex) {
            System.out.println("lllllllleeeel");
            Logger.getLogger(CoursServices.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println( ex.getMessage());    
           
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
                  System.out.println(f.getFormation_id());
                  System.out.println("hhhh");
                  cours.forEach(System.out::println);
              }
              
              
          } catch (SQLException ex) {
              Logger.getLogger(CoursServices.class.getName()).log(Level.SEVERE, null, ex);
          }
             return cours;
           
    
    }
    
   public boolean coursVisible (int f_id){
     boolean x=false;
         String req = "select count(cours_id) as nb from cours where formation_id=?";
        try {
            pste=cnx.prepareStatement(req);
            pste.setInt(1, f_id);
            ResultSet rs= pste.executeQuery();
            if (rs.next()){
            int i= rs.getInt("nb");
          if (i==0) {x= false;}
          else {x=true;}
            }
        } catch (SQLException ex) {
            Logger.getLogger(FormationServices.class.getName()).log(Level.SEVERE, null, ex);
        }
       return x;     
     }
}
