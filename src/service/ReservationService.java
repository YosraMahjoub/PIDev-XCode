/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Configuration.DataSource;
import Iservice.InterfaceService;
import entities.Rating;
import entities.Reservation;
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
public class ReservationService implements InterfaceService<Reservation>{
    
      public Connection connexion = DataSource.getInstance().getConn();
    Statement statement;

    public ReservationService(){
           try {
            statement = connexion.createStatement();

        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    @Override
    public void Ajouter(Reservation reservation) throws SQLException {

        {
  String req = "INSERT INTO `reservation`( `user_id`, `evenement_id`, `num_reserv`, `date`) VALUES (?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connexion.prepareStatement(req);

           preparedStatement.setInt(1, reservation.getParticipant().getUser_id());
             preparedStatement.setInt(2, reservation.getEvent().getId());
            preparedStatement.setString(3, reservation.getNumero());
            preparedStatement.setDate(4, new Date(reservation.getDateReservation().getTime()));
          
         
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("execption when processing add event, error is: " + e.toString());
        }
    }

    }

    @Override
    public void Modifier(Reservation reservation) throws SQLException {
          String req ="UPDATE `reservation` SET `user_id`=?,`evenement_id`=?,`num_reserv`=?,`date`=? WHERE reservation_id=?";

     try {
            PreparedStatement preparedStatement = connexion.prepareStatement(req);

           preparedStatement.setInt(1, reservation.getParticipant().getUser_id());
             preparedStatement.setInt(2, reservation.getEvent().getId());
            preparedStatement.setString(3, reservation.getNumero());
            preparedStatement.setDate(4, new Date(reservation.getDateReservation().getTime()));
                       preparedStatement.setInt(5, reservation.getId());

          
         
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("execption when processing add event, error is: " + e.toString());
        }
    }

    @Override
    public void Supprimer(Reservation reservation) throws SQLException {
             String req = "DELETE FROM `reservation` WHERE reservation_id=?";
        try {
            PreparedStatement preparedStatement = connexion.prepareStatement(req);

           preparedStatement.setInt(1, reservation.getId());
         
          
         
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("execption when processing add event, error is: " + e.toString());
        }
    }

    @Override
    public List<Reservation> Afficher() throws SQLException {
        
        List<Reservation> AllreReservations = new ArrayList<Reservation>();
        EventService eventService=new EventService();
        try {

            statement = connexion.createStatement();
            ResultSet rs = statement.executeQuery("Select * from reservation");
            while (rs.next()) {
                Reservation reservation = new Reservation(rs.getInt(1),                        UserService.getInstance().displayById(rs.getInt(2))
 ,eventService.AfficherParId(rs.getInt(3)),rs.getString(4),new java.util.Date(rs.getDate(5).getTime()));

                AllreReservations.add(reservation);

            }

            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return AllreReservations;
    }

    @Override
    public Reservation AfficherParId(int id) throws SQLException {
    
    
       Reservation reservation = new Reservation();
        EventService eventService=new EventService();
        try {

            statement = connexion.createStatement();
            ResultSet rs = statement.executeQuery("Select * from reservation where id="+id);
            while (rs.next()) {
                Reservation reservation1 = new Reservation(rs.getInt(1),                        UserService.getInstance().displayById(rs.getInt(2))
,eventService.AfficherParId(rs.getInt(3)),rs.getString(4),new java.util.Date(rs.getDate(5).getTime()));

                reservation=reservation1;

            }

            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reservation;
    }
    
    
    public boolean checkReservation(int user_id,int event_id){
        
        boolean ispart=false;
       Reservation reservation = new Reservation();
        EventService eventService=new EventService();
        try {

            statement = connexion.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM `reservation` WHERE evenement_id="+event_id+" and user_id="+user_id);
            while (rs.next()) {
          ispart= true;

            }

            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ispart;
    }
    
      public Reservation getCheckedReservation(int user_id,int event_id){
        
     
       Reservation reservation = new Reservation();
        EventService eventService=new EventService();
        try {

            statement = connexion.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM `reservation` WHERE evenement_id="+event_id+" and user_id="+user_id);
            while (rs.next()) {
                Reservation reservation1 = new Reservation(rs.getInt(1),                        UserService.getInstance().displayById(rs.getInt(2))
,eventService.AfficherParId(rs.getInt(3)),rs.getString(4),new java.util.Date(rs.getDate(5).getTime()));

                reservation=reservation1;

            }

            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reservation;
    }
    
}
