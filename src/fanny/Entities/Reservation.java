/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fanny.Entities;

import fanny.Entities.Event;
import fanny.Entities.User;
import java.util.Date;

/**
 *
 * @author ASUS
 */
public class Reservation {
    
    private int Id ;
    private User Participant;
    private Event event ;
    private String Numero;
    private Date DateReservation;
    

    public Reservation() {
    }

    public Reservation(User Participant, Event event,String Numero,Date DateReservation) {
        this.Participant = Participant;
        this.event = event;
    }

    public Reservation(int Id, User Participant, Event event,String Numero,Date DateReservation) {
        this.Id = Id;
        this.Participant = Participant;
        this.event = event;
        this.DateReservation=DateReservation;
        this.Numero=Numero;
    }

    public int getId() {
        return Id;
    }

    public String getNumero() {
        return Numero;
    }

    public void setNumero(String Numero) {
        this.Numero = Numero;
    }

    public Date getDateReservation() {
        return DateReservation;
    }

    public void setDateReservation(Date DateReservation) {
        this.DateReservation = DateReservation;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public User getParticipant() {
        return Participant;
    }

    public void setParticipant(User Participant) {
        this.Participant = Participant;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
