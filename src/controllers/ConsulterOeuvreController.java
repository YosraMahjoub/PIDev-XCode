/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import Iservice.MyListener;
import entities.ElementPanier;
import entities.Oeuvre;
import entities.PanierHolder;
import service.OeuvrageService;
import service.RatigoService;
import service.cmdservices;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class ConsulterOeuvreController implements Initializable {

    
    @FXML
    private Slider prixo;
    
      @FXML
    private TextField recho;
    @FXML
    private Button rechbtn;
    @FXML
    private ToggleGroup etat;
    @FXML
    private ScrollPane scrollO;
    @FXML
    private GridPane grid;
      
    private Oeuvre oi;
    private Image image;
   private MyListener myListener;
    
    OeuvrageService os = new OeuvrageService();
    RatigoService rs = new RatigoService();
    cmdservices cs = new cmdservices();
    @FXML
    private Button filter;
    @FXML
    private CheckBox peint;
    @FXML
    private CheckBox art;
    @FXML
    private CheckBox deco;
    @FXML
    private CheckBox scul;
    @FXML
    private CheckBox lit;
    @FXML
    private RadioButton nouvo;
    @FXML
    private RadioButton rato;
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
    private Button deconnexion;
    @FXML
    private Button panier;
  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         List<Oeuvre> listOeuvre =new ArrayList<>();
        listOeuvre.addAll(os.afficherLOV());
           if (listOeuvre.size() > 0) {
                
                myListener = new MyListener() {
                    @Override
                    public void onClickListener(MouseEvent event, Oeuvre oeuvre) {
                         try {
                            RatingoController.setOeuvre(oeuvre);
                            Parent page1 = FXMLLoader.load(getClass().getResource("/views/Ratingo.fxml"));
                            Scene scene = new Scene(page1);
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(scene); 
                            stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(RatingoController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                    @Override
                    public void onpressed(ActionEvent event, Oeuvre oeuvre) {
                    
                          
//        changer 1 par id
        
    List<ElementPanier> listEOp=PanierHolder.getInstance().getEP();
                ElementPanier ep=new ElementPanier();
                boolean existsElem=false;
        int i;
        for(i=0;i<listEOp.size();i++)
        {
            if(listEOp.get(i).getOeuv().equals(oeuvre))
            {
                existsElem=true;
                ep=listEOp.get(i);
                
                 break;
            }
        }
        if (oeuvre.getQuantite()==0){
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("hors stock ! ");
            alert.setContentText("hors stock! ");
            alert.showAndWait(); 
        }
        else {
        if(!existsElem)
        {   
            ep.setOeuv(oeuvre);
            ep.setQuantite(1);
            listEOp.add(ep);
            cs.createPanierTemp(1,ep.getOeuv().getOeuvrage_id(),ep.getQuantite());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Element ajouté ! ");
            alert.setContentText("Element ajouté avec sucèes ! ");
            alert.showAndWait(); 
        }
        else
        {   
            if(oeuvre.getQuantite()>ep.getQuantite())
            {
            cs.updatePanierTemp(1,ep.getOeuv().getOeuvrage_id(),1);
             ep.setQuantite(ep.getQuantite()+1);
            
            listEOp.set(i, ep);
            
            
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
         alert.setHeaderText("Element existe ! ");
            alert.setContentText("Element existe deja, quantité incrementé ! ");
            alert.showAndWait(); 
            }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Stock insuffisant ! ");
            alert.setContentText("Stock insuffisant! ");
            alert.showAndWait(); 
            }
        }}
            PanierHolder.getInstance().setEP(listEOp);
                    }

                    @Override
                    public void onClickListener(ElementPanier facture) { }
                };
            }
           int column = 0;
            int row = 1;
           try {
                for (int i = 0; i < listOeuvre.size(); i++) {

                   FXMLLoader fxmlLoader = new FXMLLoader();
                   fxmlLoader.setLocation(getClass().getResource("/views/afficherOP.fxml"));
                   AnchorPane anchorPane = fxmlLoader.load();

                    AfficherOPController itemController = fxmlLoader.getController();
                itemController.setData(listOeuvre.get(i),myListener);
                if (column == 3) {
                    column = 0;
                    row++;
                }
                
                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
                GridPane.setMargin(anchorPane, new Insets(10));
                }
                } catch (IOException ex) {
                   Logger.getLogger(AffmesoeuvesController.class.getName()).log(Level.SEVERE, null, ex);
               }
        float max = os.max();
        prixo.setMin(0);
        prixo.setMax(max+1);
        prixo.setValue(max+1);
        prixo.setShowTickLabels(true);
        prixo.setShowTickMarks(true);
        prixo.setMajorTickUnit(50);
        prixo.setMinorTickCount(5);
        prixo.setBlockIncrement(50);
        
        art.setSelected(true);
        scul.setSelected(true);
        lit.setSelected(true);
        deco.setSelected(true);
        peint.setSelected(true);
    }    
    
    @FXML
    private void rechercheO(ActionEvent event) {
        List<Oeuvre> listOeuvre =new ArrayList<>();
       listOeuvre.addAll(os.afficherLOV());
        boolean a = listOeuvre.stream().anyMatch(o -> o.getNom().equalsIgnoreCase(recho.getText()));
        
        if (recho.getText().isEmpty()) {
           recho.setPromptText("veuillez remplir ce champs d'abord");
           recho.setStyle("-fx-text-inner-color: black;");
        }
        
        else if (a!=true) {
            int column = 0;
            int row = 1;
           try {
                for (int i = 0; i < listOeuvre.size(); i++) {

                   FXMLLoader fxmlLoader = new FXMLLoader();
                   fxmlLoader.setLocation(getClass().getResource("/views/afficherOP.fxml"));
                   AnchorPane anchorPane = fxmlLoader.load();

                    AfficherOPController itemController = fxmlLoader.getController();
                itemController.setData(listOeuvre.get(i),myListener);

                if (column == 3) {
                    column = 0;
                    row++;
                }
                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
                GridPane.setMargin(anchorPane, new Insets(10));
                }
                } catch (IOException ex) {
                   Logger.getLogger(AffmesoeuvesController.class.getName()).log(Level.SEVERE, null, ex);
               }
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Aucun oeuvre avec ce nom");
            alert.showAndWait();
                    }
        
        else {
            List<Oeuvre> rechOeuvre;
            rechOeuvre =  listOeuvre.stream().filter(o -> o.getNom().equalsIgnoreCase(recho.getText())).collect(Collectors.toList());
            System.out.println(rechOeuvre.size() );
            
           if (rechOeuvre.size() > 0) {
               System.out.println(rechOeuvre.get(0));
               
                myListener = new MyListener() {
                    @Override
                    public void onClickListener(MouseEvent event ,Oeuvre oeuvre) {

                            try {
                            RatingoController.setOeuvre(oeuvre);
                            Parent page1 = FXMLLoader.load(getClass().getResource("/views/Ratingo.fxml"));
                            Scene scene = new Scene(page1);
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(scene); 
                            stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(RatingoController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                   @Override
                   public void onpressed(ActionEvent event, Oeuvre oeuvre) { 
                       //        changer 1 par id
        
    List<ElementPanier> listEOp=PanierHolder.getInstance().getEP();
                ElementPanier ep=new ElementPanier();
                boolean existsElem=false;
        int i;
        for(i=0;i<listEOp.size();i++)
        {
            if(listEOp.get(i).getOeuv().equals(oeuvre))
            {
                existsElem=true;
                ep=listEOp.get(i);
                
                 break;
            }
        }
         if (oeuvre.getQuantite()==0){
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("hors stock ! ");
            alert.setContentText("hors stock! ");
            alert.showAndWait(); 
        }
        else {
        if(!existsElem)
        {   
            ep.setOeuv(oeuvre);
            ep.setQuantite(1);
            listEOp.add(ep);
            cs.createPanierTemp(1,ep.getOeuv().getOeuvrage_id(),ep.getQuantite());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Element ajouté ! ");
            alert.setContentText("Element ajouté avec sucèes ! ");
            alert.showAndWait(); 
        }
        else
        {   
            if(oeuvre.getQuantite()>ep.getQuantite())
            {
            cs.updatePanierTemp(1,ep.getOeuv().getOeuvrage_id(),1);
             ep.setQuantite(ep.getQuantite()+1);
            
            listEOp.set(i, ep);
            
            
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
         alert.setHeaderText("Element existe ! ");
            alert.setContentText("Element existe deja, quantité incrementé ! ");
            alert.showAndWait(); 
            }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Stock insuffisant ! ");
            alert.setContentText("Stock insuffisant! ");
            alert.showAndWait(); 
            }
        }
            PanierHolder.getInstance().setEP(listEOp); }}

                   @Override
                   public void onClickListener(ElementPanier facture) {  }
                };
            }
           grid.getChildren().clear();
           int column = 0;
            int row = 1;
           try {
                for (int i = 0; i < rechOeuvre.size(); i++) {

                   FXMLLoader fxmlLoader = new FXMLLoader();
                   fxmlLoader.setLocation(getClass().getResource("/views/afficherOP.fxml"));
                   AnchorPane anchorPane = fxmlLoader.load();

                     AfficherOPController itemController = fxmlLoader.getController();
                itemController.setData(rechOeuvre.get(i),myListener);

                if (column == 3) {
                    column = 0;
                    row++;
                }
                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);
                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
                GridPane.setMargin(anchorPane, new Insets(10));
                }
                
                } catch (IOException ex) {
                   Logger.getLogger(AffmesoeuvesController.class.getName()).log(Level.SEVERE, null, ex);
               }
        
    }
      
    }


    @FXML
    private void filtrer(ActionEvent event) {
        
       List<Oeuvre> listOeuvre =new ArrayList<>();
       listOeuvre.addAll(os.afficherLOV());
       List<Oeuvre> rechOeuvre =new ArrayList<>();
       List<Oeuvre>  la = new ArrayList<>();
      List<Oeuvre>  ll =new ArrayList<>();
        List<Oeuvre>  ls = new ArrayList<>();
        List<Oeuvre>  lo = new ArrayList<>();
        List<Oeuvre>  ld = new ArrayList<>();
                
              
         if ( art.isSelected()){
          la = listOeuvre.stream().filter(o -> o.getDoamine().equals("artisanat")).collect(Collectors.toList()); 
         }   
         if ( scul.isSelected()){
          ls =  listOeuvre.stream().filter(o -> o.getDoamine().equals("sculpture")).collect(Collectors.toList()); 
         }  
          if ( peint.isSelected()){
          lo =  listOeuvre.stream().filter(o -> o.getDoamine().equalsIgnoreCase("Peinture")).collect(Collectors.toList()); 
         }
         if ( lit.isSelected()){
          ll =  listOeuvre.stream().filter(o -> o.getDoamine().equalsIgnoreCase("Litérature")).collect(Collectors.toList()); 
         }   
         if ( deco.isSelected()){
           ld =  listOeuvre.stream().filter(o -> o.getDoamine().equals("décoration")).collect(Collectors.toList()); 
         }
        rechOeuvre= Stream.concat(Stream.concat(Stream.concat(Stream.concat(la.stream(), ls.stream()), ld.stream()),lo.stream()),ll.stream()).collect(Collectors.toList());
        System.out.println(prixo.getValue());
        
        rechOeuvre = rechOeuvre.stream().filter(o -> o.getPrix()< prixo.getValue()).sorted((Oeuvre o1, Oeuvre o2) -> { return o1.getPrix()< o2.getPrix()? 1 
     : o1.getPrix()> o2.getPrix()? -1 : 0;}).collect(Collectors.toList());
        
        if ( nouvo.isSelected()){
           rechOeuvre =  rechOeuvre.stream().limit(5).collect(Collectors.toList());
         }
        else if (rato.isSelected()){
           rechOeuvre.clear();
           rechOeuvre.addAll(rs.afflr());
             
         if ( art.isSelected()){
            la = rechOeuvre.stream().filter(o -> o.getDoamine().equals("artisanat")).collect(Collectors.toList()); 
         }  
          if ( peint.isSelected()){
          lo =  rechOeuvre.stream().filter(o -> o.getDoamine().equalsIgnoreCase("Peinture")).collect(Collectors.toList()); 
         } 
         if ( scul.isSelected()){
            ls =  rechOeuvre.stream().filter(o -> o.getDoamine().equals("sculpture")).collect(Collectors.toList()); 
         }   
         if ( lit.isSelected()){
          ll =  rechOeuvre.stream().filter(o -> o.getDoamine().equals("litérature")).collect(Collectors.toList()); 
         }   
         if ( deco.isSelected()){
           ld =  rechOeuvre.stream().filter(o -> o.getDoamine().equals("décoration")).collect(Collectors.toList()); 
         }
        rechOeuvre= Stream.concat(Stream.concat(Stream.concat(Stream.concat(lo.stream(), ls.stream()), ld.stream()),la.stream()),ll.stream()).collect(Collectors.toList());
       rechOeuvre = rechOeuvre.stream().filter(o -> o.getPrix()< prixo.getValue()).sorted((Oeuvre o1, Oeuvre o2) -> { return o1.getRate()< o2.getRate()? 1 
     : o1.getRate()> o2.getRate()? -1 : 0;}).collect(Collectors.toList());
        }
            System.out.println("aaaaaaaaaa"+rechOeuvre.size() );
            
           if (rechOeuvre.size() > 0) {
               
                myListener = new MyListener() {
                    @Override
                    public void onClickListener(MouseEvent event ,Oeuvre oeuvre) {

                            try {
                            RatingoController.setOeuvre(oeuvre);
                            Parent page1 = FXMLLoader.load(getClass().getResource("/views/Ratingo.fxml"));
                            Scene scene = new Scene(page1);
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(scene); 
                            stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(RatingoController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                    @Override
                    public void onpressed(ActionEvent event, Oeuvre oeuvre) {
                    //        changer 1 par id
        
    List<ElementPanier> listEOp=PanierHolder.getInstance().getEP();
                ElementPanier ep=new ElementPanier();
                boolean existsElem=false;
        int i;
        for(i=0;i<listEOp.size();i++)
        {
            if(listEOp.get(i).getOeuv().equals(oeuvre))
            {
                existsElem=true;
                ep=listEOp.get(i);
                
                 break;
            }
        }
         if (oeuvre.getQuantite()==0){
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("hors stock ! ");
            alert.setContentText("hors stock! ");
            alert.showAndWait(); 
        }
        else {
        if(!existsElem)
        {   
            ep.setOeuv(oeuvre);
            ep.setQuantite(1);
            listEOp.add(ep);
            cs.createPanierTemp(1,ep.getOeuv().getOeuvrage_id(),ep.getQuantite());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Element ajouté ! ");
            alert.setContentText("Element ajouté avec sucèes ! ");
            alert.showAndWait(); 
        }
        else
        {   
            if(oeuvre.getQuantite()>ep.getQuantite())
            {
            cs.updatePanierTemp(1,ep.getOeuv().getOeuvrage_id(),1);
             ep.setQuantite(ep.getQuantite()+1);
            
            listEOp.set(i, ep);
            
            
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
         alert.setHeaderText("Element existe ! ");
            alert.setContentText("Element existe deja, quantité incrementé ! ");
            alert.showAndWait(); 
            }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Stock insuffisant ! ");
            alert.setContentText("Stock insuffisant! ");
            alert.showAndWait(); 
            }
        }
            PanierHolder.getInstance().setEP(listEOp);
                    }}

                    @Override
                    public void onClickListener(ElementPanier facture) { }
                };
            }
           grid.getChildren().clear();
           int column = 0;
            int row = 1;
           try {
                for (int i = 0; i < rechOeuvre.size(); i++) {

                   FXMLLoader fxmlLoader = new FXMLLoader();
                   fxmlLoader.setLocation(getClass().getResource("/views/afficherOP.fxml"));
                   AnchorPane anchorPane = fxmlLoader.load();

                     AfficherOPController itemController = fxmlLoader.getController();
                itemController.setData(rechOeuvre.get(i),myListener);

                if (column == 3) {
                    column = 0;
                    row++;
                }
                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);
                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
                GridPane.setMargin(anchorPane, new Insets(10));
                }
                
                } catch (IOException ex) {
                   Logger.getLogger(AffmesoeuvesController.class.getName()).log(Level.SEVERE, null, ex);
               }        
    
    }

    @FXML
    private void allerauxoeuvres(ActionEvent event) {
        try {
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("/views/consulterlesoeuvres.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);
            
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            
            window.setScene(tableViewScene);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(ConsulterOeuvreController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void acuueil(ActionEvent event) {
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
    private void voirpanier(ActionEvent event) {
        try {
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("/views/Panier.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);
            
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            
            window.setScene(tableViewScene);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(ConsulterOeuvreController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }




    
}
