/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Configuration.DataSource;
import entities.Event;
import entities.EventPlace;
import java.sql.Connection;
import java.sql.Date;
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
public class EventPlaceService implements Iservice.InterfaceService<EventPlace>{

      public Connection connexion = DataSource.getInstance().getConn();
    Statement statement;
    
    
    public EventPlaceService(){
     try {
            statement = connexion.createStatement();

        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void Ajouter(EventPlace eventPlace) throws SQLException {
   String req = "INSERT INTO `lieu_evenement`(`titre`, `description`, `longitude`, `latitude`) "
           + " VALUES (?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connexion.prepareStatement(req);

           preparedStatement.setString(1, eventPlace.getTitle());
             preparedStatement.setString(2, eventPlace.getDescription());
            preparedStatement.setDouble(3, eventPlace.getLongitude());
            preparedStatement.setDouble(4, eventPlace.getLatitude());
         
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("execption when processing add event, error is: " + e.toString());
        }

    }

    @Override
    public void Modifier(EventPlace eventPlace) throws SQLException {
  String req = "UPDATE `lieu_evenement` SET `titre`=?,`description`=?,`longitude`=?,`latitude`=? WHERE where id=?";
        try {
            PreparedStatement preparedStatement = connexion.prepareStatement(req);

           preparedStatement.setString(1, eventPlace.getTitle());
             preparedStatement.setString(2, eventPlace.getDescription());
            preparedStatement.setDouble(3, eventPlace.getLongitude());
            preparedStatement.setDouble(4, eventPlace.getLatitude());
                  preparedStatement.setInt(5, eventPlace.getId());
         
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("execption when processing add event, error is: " + e.toString());
        }


    }

    @Override
    public void Supprimer(EventPlace eventPlace) throws SQLException {

        String req = "DELETE FROM `lieu_evenement` WHERE id=?";
        try {
            PreparedStatement preparedStatement = connexion.prepareStatement(req);

            preparedStatement.setInt(1, eventPlace.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("execption when processing add event, error is: " + e.toString());
        }

    }

    
    
    @Override
    public List<EventPlace> Afficher() throws SQLException {
  List<EventPlace> AllEventsEventPlaces = new ArrayList<EventPlace>();
        try {

            statement = connexion.createStatement();
            ResultSet rs = statement.executeQuery("Select * from lieu_evenement");
            while (rs.next()) {
                EventPlace eventPlace = new EventPlace(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getDouble(5));

                AllEventsEventPlaces.add(eventPlace);

            }

            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return AllEventsEventPlaces;

    }

    @Override
    public EventPlace AfficherParId(int id) throws SQLException {
      EventPlace eventPlace=new EventPlace();
        try {

            statement = connexion.createStatement();
            ResultSet rs = statement.executeQuery("Select * from lieu_evenement");
            while (rs.next()) {
                EventPlace eventPlace1 = new EventPlace(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getDouble(5));

                eventPlace=eventPlace1;

            }

            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return eventPlace;

    }
    
}
