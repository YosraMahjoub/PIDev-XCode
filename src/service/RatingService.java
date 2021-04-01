/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Configuration.DataSource;
import Iservice.InterfaceService;
import entities.EventPlace;
import entities.Rating;
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
 * @author ASUS
 */
public class RatingService implements InterfaceService<Rating>{
    
      public Connection connexion = DataSource.getInstance().getConn();
    Statement statement;
    
    public RatingService(){
           try {
            statement = connexion.createStatement();

        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void Ajouter(Rating rating) throws SQLException {
  String req = "INSERT INTO `rating`( `user_id`, `evenement_id`, `rate`)  VALUES (?,?,?)";
        try {
            PreparedStatement preparedStatement = connexion.prepareStatement(req);

           preparedStatement.setInt(1, rating.getUser().getUser_id());
             preparedStatement.setInt(2, rating.getEvent().getId());
            preparedStatement.setDouble(3, rating.getRate());
          
         
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("execption when processing add event, error is: " + e.toString());
        }
    }

    @Override
    public void Modifier(Rating rating) throws SQLException {
         String req = "UPDATE `rating` SET `user_id`=?,`evenement_id`=?,`rate`=? WHERE id=?";
        try {
            PreparedStatement preparedStatement = connexion.prepareStatement(req);

           preparedStatement.setInt(1, rating.getUser().getUser_id());
             preparedStatement.setInt(2, rating.getEvent().getId());
            preparedStatement.setDouble(3, rating.getRate());
              preparedStatement.setInt(4, rating.getId());
          
         
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("execption when processing add event, error is: " + e.toString());
        }
    }

    @Override
    public void Supprimer(Rating rating) throws SQLException {

          String req = "DELETE FROM `rating` WHERE id=?";
        try {
            PreparedStatement preparedStatement = connexion.prepareStatement(req);

           preparedStatement.setInt(1, rating.getId());
         
          
         
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("execption when processing add event, error is: " + e.toString());
        }
    }

    @Override
    public List<Rating> Afficher() throws SQLException {
        List<Rating> AllRatings = new ArrayList<Rating>();
        EventService eventService=new EventService();
        try {

            statement = connexion.createStatement();
            ResultSet rs = statement.executeQuery("Select * from rating");
            while (rs.next()) {
                UserService us=new UserService();
                Rating rating = new Rating(rs.getInt(1), eventService.AfficherParId(rs.getInt(3)),rs.getDouble(4) ,us.displayById(rs.getInt(2)));

                AllRatings.add(rating);

            }

            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return AllRatings;

    }

    @Override
    public Rating AfficherParId(int id) throws SQLException {
        
                Rating rating = new Rating();
        EventService eventService=new EventService();
        try {

            statement = connexion.createStatement();
            ResultSet rs = statement.executeQuery("Select * from rating where id="+id);
            while (rs.next()) {
                   UserService us=new UserService();
                Rating rating1 = new Rating(rs.getInt(1), eventService.AfficherParId(rs.getInt(3)),rs.getDouble(4) ,                      us.displayById(rs.getInt(2))
);

               rating=rating1;

            }

            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rating;

    }

 
}
