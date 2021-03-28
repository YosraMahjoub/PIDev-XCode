/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Configuration.Constants;
import entities.Event;
import entities.Reservation;
import entities.User;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import service.EventService;
import service.ReservationService;

/**
 * FXML Controller class
 *
 * @author hedi
 */
public class ValidationEvenementAdminController implements Initializable {

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
    private ScrollPane ValidateEventScrollPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            getEvents();
        } catch (SQLException ex) {
            Logger.getLogger(ValidationEvenementAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    
      
    
    public void getEvents() throws SQLException {
        String stylep = "-fx-effect: dropshadow(three-pass-box, derive(cadetblue, -20%), 10, 0, 5, 5);\n  -fx-background-color: white \n ;-fx-border-color: #b0b1b2 \n ; -fx-border-width: 1; \n -fx-border-radius: 10;  ";
        List<Event> myEvents = new ArrayList<Event>();
        EventService eventService = new EventService();

        myEvents = eventService.Afficher();
        GridPane MyEventsGrid = new GridPane();
        MyEventsGrid.setHgap(20);
        MyEventsGrid.setVgap(20);
        MyEventsGrid.getChildren().clear();
        for (int i = 0; i < myEvents.size(); i++) {
             int xpostion = 0;
            int ypostion = 0;
            int division = (i / 2) + 1;

            //    MyEventsGrid.add(container, postion, i);
            if (i == 0) {
                division = 1;
                xpostion = 1;
            } else if (i == 1) {
                division = 1;
                xpostion = 2;
            } else {
                if (i % 2 == 0) {
                    xpostion = 1;
                  //  division++;
                } else {
                    xpostion = 2;
                  // division++;

                }
            }

            MyEventsGrid.add(EventCard(myEvents.get(i)), xpostion, division);

        }
        ValidateEventScrollPane.setContent(MyEventsGrid);

    }
    
    public VBox EventCard(Event event) throws SQLException{
                String stylep = "-fx-effect: dropshadow(three-pass-box, derive(cadetblue, -20%), 10, 0, 5, 5);\n  -fx-background-color: white \n ;-fx-border-color: #b0b1b2 \n ; -fx-border-width: 1; \n -fx-border-radius: 10;  ";
     //declaration des styles
                
                String styleData = " -fx-font : 14px \"serief\";  \n  -fx-text-alignment: left ; \n   -fx-text-background-color: #4e4e52;";
                String Styledetails = " -fx-font : 20px \"serief\";  \n  -fx-text-alignment: center ; \n   -fx-text-background-color: #1c1cff; \n ";
                String StyleEnAttente = " -fx-font : 20px \"serief\";  \n  -fx-text-alignment: center ; \n   -fx-text-background-color: #e0cb2b ; \n ";
                String StyleRefusee = " -fx-font : 20px \"serief\";  \n  -fx-text-alignment: center ; \n   -fx-text-background-color: #ba1300 ; \n ";
                String StyleAcceptee = " -fx-font : 20px \"serief\";  \n  -fx-text-alignment: center ; \n   -fx-text-background-color: #64de72; \n ";
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
       
            
                  ImageView membresIcon=new ImageView(new Image(new File(Constants.EVENT_ICONS_PATH+"community.png").toURI().toString()));
            membresIcon.setFitWidth(20);
            membresIcon.setFitHeight(20);
       
            //fin declaration des icones
            
       EventService eventService=new EventService();
         VBox container = new VBox();
            container.setMinWidth(380);
            container.setMaxWidth(380);
              container.setMinHeight(255);
            container.setMaxHeight(255);
            container.setStyle(stylep);
            
              HBox secondContainer = new HBox();
            secondContainer.setMinWidth(330);
            secondContainer.setMaxWidth(330);
              secondContainer.setMinHeight(190);
            secondContainer.setMaxHeight(190);
        
            
            
                VBox dataContainer = new VBox();
            dataContainer.setMinWidth(170);
            dataContainer.setMaxWidth(170);
              dataContainer.setMinHeight(190);
            dataContainer.setMaxHeight(190);
          
            
             HBox actionContainer = new HBox();
            actionContainer.setMinWidth(330);
            actionContainer.setMaxWidth(330);
              actionContainer.setMinHeight(35);
            actionContainer.setMaxHeight(35);
           
               
            
               HBox titleContainer = new HBox();
            actionContainer.setMinWidth(330);
            actionContainer.setMaxWidth(330);
              actionContainer.setMinHeight(30);
            actionContainer.setMaxHeight(30);
          

            
            ImageView eventPicture = new ImageView();
            eventPicture.setFitWidth(container.getMaxWidth());
            eventPicture.setFitWidth(160);
            eventPicture.setFitHeight(160);
            
            File file=new File(Constants.EVENT_PICTURES_PATH+event.getPicture());
            Image image =new Image(file.toURI().toString());
            eventPicture.setImage(image);

            Label eventTitle = new Label(event.getTitle());
            eventTitle.setStyle(styleTitle);
            eventTitle.setTextAlignment(TextAlignment.CENTER);
            eventTitle.setMinWidth(container.getMaxWidth());
            titleContainer.getChildren().add(eventTitle);

            
            
            //1
            Label eventDescription = new Label(event.getDescription());
            eventDescription.setTextAlignment(TextAlignment.LEFT);
            eventDescription.setMinWidth(dataContainer.getMaxWidth() - 5);
HBox eventDescriptionHBox=new HBox();
eventDescriptionHBox.getChildren().add(descriptionIcon);
eventDescriptionHBox.getChildren().add(eventDescription);
          
            //3 4
            VBox eventDatePlace = new VBox();
            eventDatePlace.setMinWidth(dataContainer.getMaxWidth());
            eventDatePlace.setSpacing(dataContainer.getMaxWidth() / 7);

            Label eventDate = new Label(event.getDate().toString());
HBox eventDateHBox= new HBox();
HBox eventPlaceHBox= new HBox();
            Label eventPlace = new Label(event.getEventPlace().getTitle());

            eventDateHBox.getChildren().add(dateIcon);
            eventDateHBox.getChildren().add(eventDate);
            eventPlaceHBox.getChildren().add(placeIcon);
            eventPlaceHBox.getChildren().add(eventPlace);
            eventDatePlace.getChildren().add(eventDateHBox);
            eventDatePlace.getChildren().add(eventPlaceHBox);
            
            

         
//5
            Label eventPrice = new Label(String.valueOf(event.getPrice())+" dt");
              
          
            HBox eventPriceData=new HBox();
            eventPriceData.getChildren().add(priceeIcon);
            eventPriceData.getChildren().add(eventPrice);
            
            //6
              Label placesRestantes = new Label(String.valueOf((event.getCapacite()-eventService.getTotalParticipantsEvent(event.getId())))+" places restantes");
         HBox   placesRestantesHBox=new HBox();
         placesRestantesHBox.getChildren().add(membresIcon);
         placesRestantesHBox.getChildren().add(placesRestantes);
            
            HBox evState = new HBox();
            Label stateLabel=new Label("Etat: ");
            
            
            Label state=new Label();
            Label goToDetails=new Label("Détails");
            goToDetails.setStyle(Styledetails);
            
            evState.getChildren().add(stateLabel);
            evState.getChildren().add(state);
            
            Button approuver = new Button("Approuver");
            Button refuser = new Button("Refuser");
          approuver.setStyle(StyleAdd);
          approuver.setMaxWidth(110);
          approuver.setMaxWidth(110);
          refuser.setStyle(StyleDelete);

           actionContainer.getChildren().add(goToDetails);
           actionContainer.getChildren().add(approuver);
           actionContainer.getChildren().add(refuser);

           Label artisteLabel=new Label("Artiste");
          Label artiste=new Label(event.getArtist().getNom()+" "+event.getArtist().getPrenom());
        //   Label artiste=new Label("");
           HBox artistContainer=new HBox();
           artistContainer.getChildren().add(artisteLabel);
           artistContainer.getChildren().add(artiste);
           
           
           dataContainer.getChildren().add(artistContainer);
           dataContainer.getChildren().add(eventDescriptionHBox);
           dataContainer.getChildren().add(placesRestantesHBox);
           dataContainer.getChildren().add(eventDatePlace);
           dataContainer.getChildren().add(eventPriceData);
           dataContainer.getChildren().add(evState);
           secondContainer.getChildren().add(eventPicture);
           secondContainer.getChildren().add(dataContainer);
           
           container.getChildren().add(titleContainer);
             container.getChildren().add(secondContainer);
               container.getChildren().add(actionContainer);
        
if(eventService.getEventState(event.getId())==0){
                state.setText("En atente");
                state.setStyle(StyleEnAttente);
                
            }else if(eventService.getEventState(event.getId())==1){
                state.setText("Approuvée");
                                state.setStyle(StyleAcceptee);
                approuver.setDisable(true);
                refuser.setDisable(false);
                
            }else{
                state.setText("Refusé");
                                state.setStyle(StyleRefusee);
                refuser.setDisable(true);
                approuver.setDisable(false);
            }
            
    
            
            approuver.setOnMouseClicked((ev) -> {
                //to change with current user
           
            
                try {
                    
                 eventService.changeEventState(event.getId(), 1);

                    getEvents();
                } catch (SQLException ex) {
                    Logger.getLogger(Afficher_mesévenementsController.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            });
            
               refuser.setOnMouseClicked((ev) -> {
                //to change with current user
           
            
                try {
                    
                 eventService.changeEventState(event.getId(), 2);

                    getEvents();
                } catch (SQLException ex) {
                    Logger.getLogger(Afficher_mesévenementsController.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            });
            
            
       
        
           
        
            
            return container;
           
    }

    
    
    
}
