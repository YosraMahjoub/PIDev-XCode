/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Configuration.Constants;
import entities.EventPlace;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import service.EventPlaceService;

/**
 * FXML Controller class
 *
 * @author hedi
 */
public class GestionLieuAdminController implements Initializable {

    @FXML
    private Button btninsert1;
    @FXML
    private Button btninsert11;
    @FXML
    private Button btninsert111;
    @FXML
    private Button btninsert1111;
    @FXML
    private Button btninsert11111;
    @FXML
    private Button btninsert111111;
    @FXML
    private Button btninsert1111111;
    @FXML
    private Button btninsert112;
    @FXML
    private Button btninsert111112;
    @FXML
    private VBox eventPlacesPage;
    @FXML
    private Button goToeventPlacesAdd;
    @FXML
    private ScrollPane EventPlacesScrollPane;
    @FXML
    private VBox eventPlacesAdd;
    @FXML
    private TextField addEventPlaceTitle;
    @FXML
    private TextField AddEventPlaceDescription;
    @FXML
    private Button AddEventPlaceSubmit;
    @FXML
    private Button AddEventPlaceCancel;
    @FXML
    private WebView AddEventPlacesWebView;
    @FXML
    private VBox eventPlacesUpdate;
    @FXML
    private VBox eventPlacesAdminDetails;
    @FXML
    private Label EventPlacesDetailsTitle;
    @FXML
    private WebView EventPlacesDetailsWebview;
    @FXML
    private Label EventPlaceDetailsDescription;
    @FXML
    private TextField UpdateEventPlaceTitle;
    @FXML
    private TextField UpdateEventPlaceDescription;
    @FXML
    private Button UpdateEventPlaceSubmit;
    @FXML
    private Button UpdateEventPlaceCancel;
    @FXML
    private WebView UpdateEventPlacesWebView;
    double longitude = 0;
    double latitude = 0;
    @FXML
    private Button EventPlaceDetailsCancel;
    @FXML
    private HBox hi;
    @FXML
    private Button emploi;
    @FXML
    private Button form;
    @FXML
    private Button events;
    @FXML
    private Button oeuvres;
    @FXML
    private Button profil;
    @FXML
    private Button Deconnexion;
    @FXML
    private HBox hello;
    @FXML
    private Button home;
    @FXML
    private Button btninfo;
    @FXML
    private Button btnprofil;
    @FXML
    private Button btnmdp;
    @FXML
    private Button btnuser;
    @FXML
    private Button btnformation;
    @FXML
    private Button btnevent;
    @FXML
    private Button btneventplace;
    @FXML
    private Button btnoeuvres;
    @FXML
    private Button reclamation;
    @FXML
    private Button btnsupp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        closeAllWindows();
        eventPlacesPage.setVisible(true);
        try {
            getAllPlaces();
        } catch (SQLException ex) {
            Logger.getLogger(GestionLieuAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        goToeventPlacesAdd.setOnMouseClicked((e) -> {
            WebEngine webEngine2 = AddEventPlacesWebView.getEngine();
            // webEngine2.loadContent(Constants.setMapData(10.337973,36.850505,""));
            webEngine2.loadContent(Constants.setMapEmpty());

            webEngine2.setOnAlert((eventAlert) -> {
                if (eventAlert.getData().contains("LatLng")) {
                    String data = eventAlert.getData().replace("LatLng(", "");
                    data = data.replace(")", "");
                    data = data.replace(")", "");
                    longitude = Double.valueOf(data.substring(data.indexOf(",") + 1, data.length()));
                    latitude = Double.valueOf(data.substring(0, data.indexOf(",")));

                    System.out.println("long:" + String.valueOf(longitude) + " latitude: " + String.valueOf(latitude));
                }
            });

            closeAllWindows();
            eventPlacesAdd.setVisible(true);
        });
        AddEventPlaceCancel.setOnMouseClicked((e) -> {
            try {
                getAllPlaces();
            } catch (SQLException ex) {
                Logger.getLogger(GestionLieuAdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
            closeAllWindows();
            eventPlacesPage.setVisible(true);
        });
        
          EventPlaceDetailsCancel.setOnMouseClicked((e) -> {
            try {
                getAllPlaces();
            } catch (SQLException ex) {
                Logger.getLogger(GestionLieuAdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
            closeAllWindows();
            eventPlacesPage.setVisible(true);
        });
          
          
              AddEventPlaceSubmit.setOnMouseClicked((e) -> {
            System.out.println("clicked");
            if (longitude == latitude && longitude == 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Vous devez choisir les coordonnées à travers la carte !", ButtonType.OK);
                alert.show();
            } else {
                EventPlace newEventPlace = new EventPlace(addEventPlaceTitle.getText().toString(), AddEventPlaceDescription.getText().toString(), longitude, latitude);
                EventPlaceService eventPlaceService2 = new EventPlaceService();
                try {
                    eventPlaceService2.Ajouter(newEventPlace);
                } catch (SQLException ex) {
                    Logger.getLogger(GestionLieuAdminController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
          
    }

    public void closeAllWindows() {
        eventPlacesAdminDetails.setVisible(false);
        eventPlacesAdd.setVisible(false);
        eventPlacesPage.setVisible(false);
        eventPlacesUpdate.setVisible(false);

    }

    public void getAllPlaces() throws SQLException {
        EventPlaceService eventPlaceService = new EventPlaceService();
        List<EventPlace> places = new ArrayList<EventPlace>();
        places = eventPlaceService.Afficher();
        GridPane MyEventsGrid = new GridPane();
        MyEventsGrid.setHgap(20);
        MyEventsGrid.setVgap(20);
        MyEventsGrid.getChildren().clear();
        for (int i = 0; i < places.size(); i++) {
            MyEventsGrid.add(PlaceCard(places.get(i)), 1, i + 1);
        }
        EventPlacesScrollPane.setContent(MyEventsGrid);

    }

    public HBox PlaceCard(EventPlace eventPlace) {
        String stylep = "-fx-effect: dropshadow(three-pass-box, derive(cadetblue, -20%), 10, 0, 5, 5);\n  -fx-background-color: white \n ;-fx-border-color: #b0b1b2 \n ; -fx-border-width: 1; \n -fx-border-radius: 10;  ";
  
             //declaration des styles
                
                String styleData = " -fx-font : 14px \"serief\";  \n  -fx-text-alignment: left ; \n   -fx-text-background-color: #4e4e52;";
                String Styledetails = " -fx-font : 20px \"serief\";  \n  -fx-text-alignment: center ; \n   -fx-text-background-color: #1c1cff; \n ";
                String StyleDelete= "    -fx-effect:dropshadow(one-pass-box,black,8,0.0,2,0);  \n  -fx-border-radius:20;  \n -fx-font:bold10pt\"Arial\" ; \n -fx-background-color: #ba1300 ;\n   -fx-text-fill: #ffffff ;  ";
                String StyleAdd ="    -fx-effect:dropshadow(one-pass-box,black,8,0.0,2,0);  \n  -fx-border-radius:20;  \n -fx-font:bold10pt\"Arial\" ; \n -fx-background-color: #64de72 ;\n   -fx-text-fill: #ffffff ;  ";
                String StyleUpdate ="    -fx-effect:dropshadow(one-pass-box,black,8,0.0,2,0);  \n  -fx-border-radius:20;  \n -fx-font:bold10pt\"Arial\" ; \n -fx-background-color: #e0cb2b ;\n   -fx-text-fill: #ffffff ;  ";
                String styleTitle = "fx-font-weight:700; \n   -fx-font-family: \"Broadway\";\n  -fx-alignment: center ;\n -fx-text-alignment: center ; \n   -fx-text-background-color: #0b0082;";
                //fin declaration des styles
                
                //declarationdes icones
                  ImageView descriptionIcon=new ImageView(new Image(new File(Constants.EVENT_ICONS_PATH+"description.png").toURI().toString()));
            descriptionIcon.setFitWidth(20);
            descriptionIcon.setFitHeight(20);
                
                 ImageView dateIcon=new ImageView(new Image(new File(Constants.EVENT_ICONS_PATH+"calendar.png").toURI().toString()));
            dateIcon.setFitWidth(20);
            dateIcon.setFitHeight(20);    
            
             ImageView placeIcon=new ImageView(new Image(new File(Constants.EVENT_ICONS_PATH+"place-localizer.png").toURI().toString()));
            placeIcon.setFitWidth(20);
            placeIcon.setFitHeight(20);
            
             ImageView priceeIcon=new ImageView(new Image(new File(Constants.EVENT_ICONS_PATH+"dollar-tag.png").toURI().toString()));
            priceeIcon.setFitWidth(20);
            priceeIcon.setFitHeight(20);
            
            //fin declaration des icones
            
        
        
        HBox container = new HBox();
        container.setMaxWidth(440);
        container.setMinWidth(440);
        container.setMaxHeight(120);
        container.setMinHeight(120);
        container.setStyle(stylep);

        WebView webView = new WebView();
        webView.setMaxWidth(150);
        webView.setMinWidth(150);
        webView.setMaxHeight(120);
        webView.setMinHeight(120);
        WebEngine webEngine = webView.getEngine();
        webEngine.loadContent(Constants.setMapData(eventPlace.getLongitude(), eventPlace.getLatitude(), eventPlace.getTitle()));

        HBox Titlecontainer = new HBox();
        Label titleLabel = new Label("Titre:");
        Label titre = new Label(eventPlace.getTitle());
        titre.setStyle(styleTitle);
        Titlecontainer.getChildren().add(titleLabel);
        Titlecontainer.getChildren().add(titre);

        HBox Descriptioncontainer = new HBox();
        
        Label description = new Label(eventPlace.getDescription());
        description.setStyle(Styledetails);
        Descriptioncontainer.getChildren().add(descriptionIcon);
        Descriptioncontainer.getChildren().add(description);

        VBox ContentData = new VBox();
        ContentData.setMaxWidth(215);
        ContentData.setMinWidth(215);
        ContentData.setMaxHeight(120);
        ContentData.setMinHeight(120);

        ContentData.getChildren().add(Titlecontainer);
        ContentData.getChildren().add(Descriptioncontainer);

        VBox ContentActions = new VBox();
        ContentActions.setMaxWidth(215);
        ContentActions.setMinWidth(215);
        ContentActions.setMaxHeight(120);
        ContentActions.setMinHeight(120);

        Button Delete = new Button("Supprimer");
        Button Update = new Button("Modifier");
        Button Details = new Button("Détails");
        Delete.setStyle(StyleDelete);
        Update.setStyle(StyleUpdate);
        ContentActions.getChildren().add(Details);
      //  ContentActions.getChildren().add(Update);
        ContentActions.getChildren().add(Delete);

        container.getChildren().add(webView);
        container.getChildren().add(ContentData);
        container.getChildren().add(ContentActions);

        EventPlaceService eventPlaceService = new EventPlaceService();

        Delete.setOnMouseClicked((e) -> {
            try {
                eventPlaceService.Supprimer(eventPlace);
                getAllPlaces();
            } catch (SQLException ex) {
                Logger.getLogger(GestionLieuAdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        Details.setOnMouseClicked((e) -> {
            EventPlaceDetailsDescription.setText(eventPlace.getDescription());
            EventPlacesDetailsTitle.setText(eventPlace.getTitle());

            WebEngine webEngineDetails = EventPlacesDetailsWebview.getEngine();
            webEngineDetails.loadContent(Constants.setMapData(eventPlace.getLongitude(), eventPlace.getLatitude(), eventPlace.getTitle()));

            closeAllWindows();
            eventPlacesAdminDetails.setVisible(true);
        });
        /*   Update.setOnMouseClicked((e) -> {
                
           closeAllWindows();
           eventPlacesUpdate.setVisible(true);
          });
         */

    

        return container;
    }

    @FXML
    private void gotoemploi(ActionEvent event) {
    }

    @FXML
    private void gotoform(ActionEvent event) {
    }

    @FXML
    private void gotoevents(ActionEvent event) {
    
        try {
                
                Parent page1 = FXMLLoader.load(getClass().getResource("/views/AccueilUtilisateur.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(UserprofilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }

    @FXML
    private void allerauxoeuvres(ActionEvent event) {
    }

    @FXML
    private void gotoprofil(ActionEvent event) {
    }

    @FXML
    private void deconnecter(ActionEvent event) {
    }

    @FXML
    private void accueil(ActionEvent event) {
    }

}
