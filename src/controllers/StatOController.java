/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import animatefx.animation.Bounce;
import java.io.IOException;
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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import service.RatigoService;
import utils.ConnexionDB;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class StatOController implements Initializable {

    @FXML
    private Button gest;
    @FXML
    private Button mdp;
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
    @FXML
    private Button oeuvres;
    @FXML
    private Button home;
    @FXML
    private Button emploi;
    @FXML
    private Button form;
    @FXML
    private Button events;
    @FXML
    private Button profil;
    @FXML
    private Button Deconnexion;
    @FXML
    private Button infos;
    @FXML
    private Button supp;
    @FXML
    private Button reclam;
    @FXML
    private Button statO;
    @FXML
    private Button users;
    @FXML
    private Button statF;
  
   
      
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
        
        for (PieChart.Data data : piechart.getData()){
            data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                  
                    int inputs = JOptionPane.showConfirmDialog(null,"vendeur : "+data.getName()+ "  a "+(int)data.getPieValue()+" oeuvres.","Voulez vous voir ce profil",JOptionPane.YES_NO_OPTION);
                    System.out.println(inputs);
                          if (inputs == 0){
                              
                        try {
                            Parent page1 = FXMLLoader.load(getClass().getResource("/views/affmesoeuvres.fxml"));
                            Scene scene = new Scene(page1);
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(scene);
                            stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(StatOController.class.getName()).log(Level.SEVERE, null, ex);
                        }
          
                          }
                    }
            });
                    
        }
        
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


    @FXML
    private void accueil(ActionEvent event) {
    }

    @FXML
    private void allerauxoeuvres(ActionEvent event) {
    }

    @FXML
    private void gotoemploi(ActionEvent event) {
    }

    @FXML
    private void gotoform(ActionEvent event) {
    }

    @FXML
    private void gotoevents(ActionEvent event) {
    }

    @FXML
    private void gotoprofil(ActionEvent event) {
    }

    @FXML
    private void deconnecter(ActionEvent event) {
    }

    @FXML
    private void informations(ActionEvent event) {
    }

    @FXML
    private void gereprofil(ActionEvent event) {
    }

    @FXML
    private void suppimerCompte(ActionEvent event) {
    }

    @FXML
    private void gotoreclam(ActionEvent event) {
    }

    @FXML
    private void gotostatO(ActionEvent event) {
    }

    @FXML
    private void changermdp(ActionEvent event) {
    }

    @FXML
    private void gotouser(ActionEvent event) {
    }

    @FXML
    private void gotostatF(ActionEvent event) {
    }
}