/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xcode.controllers;

import animatefx.animation.Bounce;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;
import xcode.service.RatigoService;
import xcode.utils.ConnexionDB;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class StatOController implements Initializable {

    @FXML
    private Button event;
    @FXML
    private Button role;
    @FXML
    private Button gest;
    @FXML
    private Button mdp;
    @FXML
    private Button form;
    @FXML
    private Button oeuvres;
    @FXML
    private Button favoris;
    @FXML
    private PieChart piechart;

    
private final ObservableList<PieChart.Data> pdata= FXCollections.observableArrayList();
ObservableList<PieChart.Data> piechartdata;
ArrayList<Integer> nb=new ArrayList<>();
ArrayList<String> vend=new ArrayList<>();

BorderPane root;
   
    private ResultSet rs=null,rs1=null;
    private PreparedStatement pst,pst1;
    
     private Connection cnx;
    @FXML
    private BarChart<String, Float> barchar;
    @FXML
    private NumberAxis noto;
    @FXML
    private CategoryAxis domo;
    @FXML
    private BarChart<String, Float>bc;
  
   
      
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          
//        **piechart**
        FadeTransition f = new FadeTransition(Duration.seconds(2),piechart);
          f.setFromValue(0);
          f.setToValue(1);
          f.play();
        cnx = ConnexionDB.getInstance().getConnection();
        laodData();
        piechart.setData(piechartdata);
        
////        **barchar 1**

        RatigoService r = new RatigoService();
        
        
        XYChart.Series<String, Float> series = r.firstP();
        series.setName("Le premier ");
        XYChart.Series<String, Float> series1 = r.secondP();
        series1.setName("Le deuxième");
         barchar.getData().addAll(series,series1);
         
        
          
////        **barchar 2**    
        
         XYChart.Series<String, Float> serie = r.domainrate();
        serie.setName("qte des oeuvres ");
        
        bc.getData().addAll(serie);
       
        
        


}
private void laodData(){
        piechartdata=FXCollections.observableArrayList();
    try {
        
        pst=cnx.prepareStatement("SELECT user_id, COUNT(oeuvrage_id) as nboeuvre FROM oeuvrage group by user_id");
           
      
        rs=pst.executeQuery();
       
        while(rs.next() )
        {
              pst1=cnx.prepareStatement("select * from user WHERE user_id='"+rs.getInt("user_id")+"'");
        rs1=pst1.executeQuery();
           
        while(rs1.next())
        {
            int i=Integer.valueOf(rs.getString("nboeuvre"));
            piechartdata.add(new PieChart.Data(rs1.getString("nom"),i));
            nb.add(i);
            vend.add(rs1.getString("nom"));
        }
        }
    } catch (SQLException ex) {
        Logger.getLogger(StatOController.class.getName()).log(Level.SEVERE, null, ex);
    }
   
        // TODO
   
        // TODO
    }    
}