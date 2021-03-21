/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author ASUS
 */
public class Event {
 
    private int Id ;
    private int capacite;
    private String Title ; 
    private String Description ; 
    private String Picture; 
    private double Price ; 
    private Date date ; 
    private User Artist ; 
    private EventPlace eventPlace ; 
    private Date creationDate;
    private String domaine;

    
    public Event() {
    }

    public Event(int Id, String Title, String Description, String Picture, double Price, Date date, User Artist, EventPlace eventPlace, Date creationDate, String domaine,int capacite) {
        this.Id = Id;
        this.Title = Title;
        this.Description = Description;
        this.Picture = Picture;
        this.Price = Price;
        this.date = date;
        this.Artist = Artist;
        this.eventPlace = eventPlace;
        this.creationDate = creationDate;
        this.domaine = domaine;
        this.capacite=capacite;
    }

    public Event(String Title, String Description, String Picture, double Price, Date date, User Artist, EventPlace eventPlace, Date creationDate, String domaine,int capacite) {
        this.Title = Title;
        this.Description = Description;
        this.Picture = Picture;
        this.Price = Price;
        this.date = date;
        this.Artist = Artist;
        this.eventPlace = eventPlace;
        this.creationDate = creationDate;
        this.domaine = domaine;
        this.capacite=capacite;
    }

    
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getDomaine() {
        return domaine;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }
    

   
  
    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getPicture() {
        return Picture;
    }

    public void setPicture(String Picture) {
        this.Picture = Picture;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double Price) {
        this.Price = Price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getArtist() {
        return Artist;
    }

    public void setArtist(User Artist) {
        this.Artist = Artist;
    }

    public EventPlace getEventPlace() {
        return eventPlace;
    }

    public void setEventPlace(EventPlace eventPlace) {
        this.eventPlace = eventPlace;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
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
