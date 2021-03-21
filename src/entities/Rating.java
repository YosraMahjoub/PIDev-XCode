/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import entities.Event;

/**
 *
 * @author ASUS
 */
public class Rating {
    private int Id ;
    private Event event ;
    private double Rate ;
    private User user ;

    public Rating() {
    }

    public Rating(Event event, double Rate, User user) {
        this.event = event;
        this.Rate = Rate;
        this.user = user;
    }

    public Rating(int Id, Event event, double Rate, User user) {
        this.Id = Id;
        this.event = event;
        this.Rate = Rate;
        this.user = user;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public double getRate() {
        return Rate;
    }

    public void setRate(double Rate) {
        this.Rate = Rate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
