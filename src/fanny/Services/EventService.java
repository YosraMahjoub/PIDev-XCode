/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fanny.Services;

import fanny.Configuration.DataSource;
import fanny.Entities.Event;
import fanny.Entities.EventPlace;
import fanny.Entities.User;
import fanny.Interfaces.InterfaceService;
import java.lang.reflect.Array;
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
public class EventService implements InterfaceService<Event> {

    public Connection connexion = DataSource.getInstance().getConn();
    Statement statement;

    public EventService() {
        try {
            statement = connexion.createStatement();

        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void Ajouter(Event event) throws SQLException {
        java.util.Date datenow = new java.util.Date();
        String req = "INSERT INTO `evenement`(`date_creation`, `lieu_id`, `prix`, `domaine`,"
                + " `capacité`, `image`, `titre`, `description`, `date_evenement`, "
                + "`id_artiste`) VALUES (?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connexion.prepareStatement(req);

            preparedStatement.setDate(1, new Date(datenow.getTime()));
            preparedStatement.setInt(2, event.getEventPlace().getId());
            preparedStatement.setDouble(3, event.getPrice());
            preparedStatement.setString(4, event.getDomaine());
            preparedStatement.setInt(5, event.getCapacite());
            preparedStatement.setString(6, event.getPicture());
            preparedStatement.setString(7, event.getTitle());
            preparedStatement.setString(8, event.getDescription());

            preparedStatement.setDate(9, new Date(event.getDate().getTime()));
            preparedStatement.setInt(10, event.getArtist().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("execption when processing add event, error is: " + e.toString());
        }

    }

    @Override
    public void Modifier(Event event) throws SQLException {

        String req = "UPDATE `evenement` SET `lieu_id`=?,`prix`=?,`domaine`=?,`capacité`=?,`image`=?,"
                + "`titre`=?,`description`=?,"
                + "`date_evenement`=?,`id_artiste`=? "
                + "WHERE id=?";
        try {
            PreparedStatement preparedStatement = connexion.prepareStatement(req);

            preparedStatement.setInt(1, event.getEventPlace().getId());
            preparedStatement.setDouble(2, event.getPrice());
            preparedStatement.setString(3, event.getDomaine());
            preparedStatement.setInt(4, event.getCapacite());
            preparedStatement.setString(5, event.getPicture());
            preparedStatement.setString(6, event.getTitle());
            preparedStatement.setString(7, event.getDescription());
            preparedStatement.setDate(8, new Date(event.getDate().getTime()));
            preparedStatement.setInt(9, event.getArtist().getId());
            preparedStatement.setInt(10, event.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("execption when processing add event, error is: " + e.toString());
        }
    }

    @Override
    public void Supprimer(Event event) throws SQLException {

        String req = "DELETE FROM `evenement` WHERE id=?";
        try {
            PreparedStatement preparedStatement = connexion.prepareStatement(req);

            preparedStatement.setInt(1, event.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("execption when processing add event, error is: " + e.toString());
        }

    }

    @Override
    public List<Event> Afficher() throws SQLException {
        List<Event> AllEvents = new ArrayList<Event>();
        try {

            statement = connexion.createStatement();
            ResultSet rs = statement.executeQuery("Select * from evenement");
            while (rs.next()) {
                Event event = new Event(rs.getInt(1), rs.getString(8), rs.getString(9), rs.getString(7), rs.getDouble(4), new java.util.Date(rs.getDate(10).getTime()), this.getArtistById(rs.getInt(11)), this.getPlaceById(rs.getInt(3)), new java.util.Date(rs.getDate(2).getTime()), rs.getString(5), rs.getInt(6));

                AllEvents.add(event);

            }

            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return AllEvents;
    }

    @Override
    public Event AfficherParId(int id) throws SQLException {
   
         Event event=new Event();
        try {

            statement = connexion.createStatement();
            ResultSet rs = statement.executeQuery("Select * from evenement where id="+id);
            while (rs.next()) {
                Event event1 = new Event(rs.getInt(1), rs.getString(8), rs.getString(9), rs.getString(7), rs.getDouble(4), new java.util.Date(rs.getDate(10).getTime()), this.getArtistById(rs.getInt(11)), this.getPlaceById(rs.getInt(3)), new java.util.Date(rs.getDate(2).getTime()), rs.getString(5), rs.getInt(6));

              event=event1;

            }

            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return event;
    }

    public User getArtistById(int id) throws SQLException {
        statement = connexion.createStatement();
        User user = new User();
        String req = "SELECT `user_id`, `nom`, `prenom`, `username`, `password`, `role`, `adresse`, `num_tel`, `email`, `catgOeuvrage_id`, `catgFormation_id`, `image` FROM `user` WHERE user_id=" + id;
        try {
            ResultSet rs = statement.executeQuery(req);
            while (rs.next()) {
                // a changer lors de l integration avec la classe user 
                user.setId(rs.getInt(1));
                user.setFirstname(rs.getString(3));
                user.setLastname(rs.getString(2));
                user.setId(rs.getInt(1));
                user.setRole(rs.getString(6));
                user.setPasswrd(rs.getString(5));

            }
            statement.close();

        } catch (Exception e) {
        }
        return user;
    }

    public EventPlace getPlaceById(int id) throws SQLException {
        statement = connexion.createStatement();
        EventPlace eventPlace = new EventPlace();
        String req = "SELECT `id`, `titre`, `description`, `longitude`, `latitude` FROM `lieu_evenement` WHERE id=" + id;
        try {
            ResultSet rs = statement.executeQuery(req);
            while (rs.next()) {

                eventPlace.setId(rs.getInt(1));
                eventPlace.setDescription(rs.getString(3));
                eventPlace.setLatitude(rs.getDouble(5));
                eventPlace.setLatitude(rs.getDouble(4));
                eventPlace.setTitle(rs.getString(2));
            }
            statement.close();
        } catch (Exception e) {
        }
        return eventPlace;
    }

}
