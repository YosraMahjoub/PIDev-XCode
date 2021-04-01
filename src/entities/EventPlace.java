/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author ASUS
 */
public class EventPlace {
    private int Id ;
    private String Title;
    private String Description;
    private double Longitude;
    private double Latitude;

    public EventPlace() {
    }

    public EventPlace(String Title, String Description, double Longitude, double Latitude) {
        this.Title = Title;
        this.Description = Description;
        this.Longitude = Longitude;
        this.Latitude = Latitude;
    }

    public EventPlace(int Id, String Title, String Description, double Longitude, double Latitude) {
        this.Id = Id;
        this.Title = Title;
        this.Description = Description;
        this.Longitude = Longitude;
        this.Latitude = Latitude;
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

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double Longitude) {
        this.Longitude = Longitude;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double Latitude) {
        this.Latitude = Latitude;
    }

    @Override
    public String toString() {
    return "{ long:"+Longitude+"\n latitude:"+Latitude+"\n title:"+Title+"}"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj); //To change body of generated methods, choose Tools | Templates.
    }
    
    
          
    
}
