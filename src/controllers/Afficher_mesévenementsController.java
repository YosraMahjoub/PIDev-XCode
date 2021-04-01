/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Configuration.Constants;
import entities.Event;
import entities.EventPlace;
import entities.User;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import service.EventPlaceService;
import service.EventService;
import service.UserService;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class Afficher_mesévenementsController implements Initializable {

    
    private String AddeventPiturePath="";
    private String UpdateeventPiturePath="";
    Event eventToUpdate=new Event();
    Event eventDetails=new Event();
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
    private ScrollPane myEventsScrollPane;
    @FXML
    private VBox myeventsPage;
    @FXML
    private VBox myeventsAdd;
    @FXML
    private TextField addEventTitle;
    @FXML
    private TextField AddEventDescription;
    @FXML
    private DatePicker AddEventDate;
    @FXML
    private ComboBox<EventPlace> AddEventcomboPlace;
    @FXML
    private TextField AddEventNbr;
    @FXML
    private ImageView AddEventPicturePreview;
    @FXML
    private Button AddEventSubmit;
    @FXML
    private Label AddEventOpenFIlePicker;
   
    @FXML
    private Button AddEventCancel;
    @FXML
    private VBox myeventsUpdate;
    @FXML
    private TextField updateEventTitle;
    @FXML
    private TextField UpdateEventDescription;
    @FXML
    private DatePicker UpdateEventDate;
    @FXML
    private ComboBox<EventPlace> UpdateEventcomboPlace;
    @FXML
    private TextField UpdateEventNbr;
    @FXML
    private ImageView UpdateEventPicturePreview;
    @FXML
    private Button UpdateEventSubmit;
    @FXML
    private Label UpdateEventOpenFIlePicker;
    @FXML
    private Button UpdateEventCancel;
    @FXML
    private Button goToMyEventsAdd;
    @FXML
    private TextField AddEventPrice;
    @FXML
    private TextField UpdateEventPrice;
    @FXML
    private VBox myeventsDetails;
    @FXML
    private Button DetailEventCancel;
    @FXML
    private Label DetailEventTitle;
    @FXML
    private ImageView DetailEventPicture;
    @FXML
    private Label DetailEventDescription;
    @FXML
    private Button DetailEventOpenMap;
    @FXML
    private Label DetailEventNbr;
    @FXML
    private Label DetailEventDate;
    @FXML
    private Label DetailEventPrice;
    @FXML
    private Label DetailEventPlaceTitle;
    @FXML
    private Button MapEventCancel;
    @FXML
    private VBox myeventsDetailsMap;
    @FXML
    private WebView myeventsDetailsMapWebView;
    
    EventPlace selectedEventPlace=new EventPlace();
    @FXML
    private Button btninfo;
    @FXML
    private Button btngérer;
    @FXML
    private Button btnmdp;
    @FXML
    private Button btnformation;
    @FXML
    private Button btneven;
    @FXML
    private Button btnoeuvres;
    @FXML
    private Button btnfavories;
    @FXML
    private Button apprentissage;
    @FXML
    private Button reclamation;
    @FXML
    private Button btnsupprimer;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            closeAllWindows();
myeventsPage.setVisible(true);
            goToMyEventsAdd.setOnMouseClicked((e) -> {
           /*     Callback<ListView<EventPlace>, ListCell<EventPlace>> factory = lv -> new ListCell<EventPlace>() {
    @Override
    protected void updateItem(EventPlace item, boolean empty) {
        super.updateItem(item, empty);
        setText(empty ? "" : item.getTitle());
    }
};*/
                    EventPlaceService eventPlaceService=new EventPlaceService();
                try {
                    ObservableList<EventPlace> eventsPlacesList = FXCollections.observableList(eventPlaceService.Afficher());
                    
                    AddEventcomboPlace.itemsProperty().setValue(eventsPlacesList);
                    
                    
                     AddEventcomboPlace.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("Product Name: " + newValue.getTitle());
            System.out.println("Product Description: " + newValue.getDescription());
            System.out.println("Product Id: " + newValue.getId());
            selectedEventPlace=newValue;
        });

      //  convertComboDisplayList();
       AddEventcomboPlace.setConverter(new StringConverter<EventPlace>() {
            @Override
            public String toString(EventPlace product) {
                return product.getTitle();
            }

            @Override
            public EventPlace fromString(final String string) {
                return AddEventcomboPlace.getItems().stream().filter(product -> product.getTitle().equals(string)).findFirst().orElse(null);
            }
        });
                    

                } catch (SQLException ex) {
                    Logger.getLogger(Afficher_mesévenementsController.class.getName()).log(Level.SEVERE, null, ex);
                }

                
//AddEventcomboPlace.setCellFactory(factory);
//AddEventcomboPlace.setButtonCell(factory.call(null));
                closeAllWindows();
                try {
                    getMyEvents();
                } catch (SQLException ex) {
                    Logger.getLogger(Afficher_mesévenementsController.class.getName()).log(Level.SEVERE, null, ex);
                }
                myeventsAdd.setVisible(true);
            });
            
            UpdateEventCancel.setOnMouseClicked((e) -> {
                 closeAllWindows();
                try {
                    getMyEvents();
                } catch (SQLException ex) {
                    Logger.getLogger(Afficher_mesévenementsController.class.getName()).log(Level.SEVERE, null, ex);
                }
                myeventsPage.setVisible(true);
            });
            
           AddEventCancel.setOnMouseClicked((e) -> {
                 closeAllWindows();
                try {
                    getMyEvents();
                } catch (SQLException ex) {
                    Logger.getLogger(Afficher_mesévenementsController.class.getName()).log(Level.SEVERE, null, ex);
                }
                myeventsPage.setVisible(true);
            });
           
           AddEventOpenFIlePicker.setOnMouseClicked((e) -> {
                  FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("All files (*.*)", "*.*");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(null);
   
      
       Image preview=new Image(file.toURI().toString());
       AddEventPicturePreview.setImage(preview);
      AddeventPiturePath=file.toString();
      
      
               
           });
           
           AddEventSubmit.setOnMouseClicked((e) -> {
               
             if(selectedEventPlace!=null ){  
               
               
               
                  Date date=new Date();
               //copy picture to pictures folder
                 String chemin = Constants.EVENT_PICTURES_PATH + date.getTime()+ ".png";
       
            
        String imagenom =date.getTime() + ".png";

                try {
                    //get current date time with Date()
                    
                    Files.copy(Paths.get(AddeventPiturePath),
                            Paths.get(chemin), StandardCopyOption.REPLACE_EXISTING
                    );  } catch (IOException ex) {
                    Logger.getLogger(Afficher_mesévenementsController.class.getName()).log(Level.SEVERE, null, ex);
                }
        //get all fields
        
        ZoneId defaultZoneId = ZoneId.systemDefault();
        // change user to current user 
        User user=new User();
        user=UserService.getCurrentUser();
        //change place to selected comboplace
              
     
        Event event=new Event(addEventTitle.getText().toString(), AddEventDescription.getText().toString(),imagenom,Double.valueOf(AddEventPrice.getText().toString()),Date.from(AddEventDate.getValue().atStartOfDay(defaultZoneId).toInstant()),user, selectedEventPlace,new Date(),"", Integer.valueOf(AddEventNbr.getText().toString()));
             
        EventService eventService =new EventService();
                try {
                    eventService.Ajouter(event);
                    getMyEvents();
                    closeAllWindows();
myeventsPage.setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Afficher_mesévenementsController.class.getName()).log(Level.SEVERE, null, ex);
                }
           }else{
                 Alert alert =new Alert(Alert.AlertType.ERROR, "Vous devez choisir le lieu de l' évenement", ButtonType.OK);
                 alert.show();
             }
           });
           
           
           //update event
                 UpdateEventOpenFIlePicker.setOnMouseClicked((e) -> {
                  FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("All files (*.*)", "*.*");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(null);
   
      
       Image preview=new Image(file.toURI().toString());
       UpdateEventPicturePreview.setImage(preview);
      UpdateeventPiturePath=file.toString();
      
      
               
           });
           
           UpdateEventSubmit.setOnMouseClicked((e) -> {
               
               
               
               
               
                  Date date=new Date();
               //copy picture to pictures folder
                 String chemin = Constants.EVENT_PICTURES_PATH + date.getTime()+ ".png";
       
            
        String imagenom =date.getTime() + ".png";

                try {
                    //get current date time with Date()
                    
                    Files.copy(Paths.get(UpdateeventPiturePath),
                            Paths.get(chemin), StandardCopyOption.REPLACE_EXISTING
                    );  } catch (IOException ex) {
                    Logger.getLogger(Afficher_mesévenementsController.class.getName()).log(Level.SEVERE, null, ex);
                }
        //get all fields
        
        ZoneId defaultZoneId = ZoneId.systemDefault();
        // change user to current user 
        User user=new User();
        //change place to selected comboplace
             user=UserService.getCurrentUser();
        Event event=new Event(updateEventTitle.getText().toString(), UpdateEventDescription.getText().toString(),imagenom,Double.valueOf(UpdateEventPrice.getText().toString()),Date.from(UpdateEventDate.getValue().atStartOfDay(defaultZoneId).toInstant()),user, selectedEventPlace,new Date(),"", Integer.valueOf(UpdateEventNbr.getText().toString()));
             event.setId(eventToUpdate.getId());
        EventService eventService =new EventService();
                try {
                    eventService.Modifier(event);
                    getMyEvents();
                    closeAllWindows();
myeventsPage.setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Afficher_mesévenementsController.class.getName()).log(Level.SEVERE, null, ex);
                }
        
           });
           
           
            // TODO
            getMyEvents();
            
        } catch (SQLException ex) {
            Logger.getLogger(Afficher_mesévenementsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getMyEvents() throws SQLException {
        String stylep = "-fx-effect: dropshadow(three-pass-box, derive(cadetblue, -20%), 10, 0, 5, 5);\n  -fx-background-color: white \n ;-fx-border-color: #b0b1b2 \n ; -fx-border-width: 1; \n -fx-border-radius: 10;  ";
        List<Event> myEvents = new ArrayList<Event>();
        EventService eventService = new EventService();

        myEvents = eventService.MesEvenements(UserService.getCurrentUser().getUser_id());
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
        myEventsScrollPane.setContent(MyEventsGrid);

    }
    
    public VBox EventCard(Event event){
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
            
            
       EventService eventService=new EventService();
         VBox container = new VBox();
            container.setMinWidth(190);
            container.setMaxWidth(190);

            container.setStyle(stylep);

            ImageView eventPicture = new ImageView();
            eventPicture.setFitWidth(container.getMaxWidth());
            eventPicture.setFitHeight(110);
            File file=new File(Constants.EVENT_PICTURES_PATH+event.getPicture());
            Image image =new Image(file.toURI().toString());
            eventPicture.setImage(image);

            Label eventTitle = new Label(event.getTitle());
         //   eventTitle.setMinWidth(container.getMaxWidth());
           eventTitle.setMaxWidth(container.getMaxWidth());
            eventTitle.setTextAlignment(TextAlignment.CENTER);
eventTitle.setStyle(styleTitle);
            
            
            Label eventDescriptionLabel = new Label(" "+event.getDescription());
            eventDescriptionLabel.setTextAlignment(TextAlignment.LEFT);
            eventDescriptionLabel.setStyle(styleData);
            eventDescriptionLabel.setMinWidth(container.getMaxWidth() - 10);
          
HBox eventDescription=new HBox();
eventDescription.getChildren().add(descriptionIcon);
eventDescription.getChildren().add(eventDescriptionLabel);


            VBox eventDatePlace = new VBox();
            eventDatePlace.setMinWidth(container.getMaxWidth());
           

            Label eventDate = new Label(" "+ event.getDate().toString());
           
            eventDate.setMaxWidth(container.getMaxWidth()-20);
   eventDate.setStyle(styleData);
        
            HBox eventDateHBOX=new HBox();
            eventDateHBOX.getChildren().add(dateIcon);
            eventDateHBOX.getChildren().add(eventDate);
            
            Label eventPlace = new Label(" "+event.getEventPlace().getTitle());
            eventPlace.setMinWidth(container.getMaxWidth());
            eventPlace.setStyle(styleData);
            
   
            HBox eventPlaceHBOX=new HBox();
            eventPlaceHBOX.getChildren().add(placeIcon);
            eventPlaceHBOX.getChildren().add(eventPlace);
            
            eventDatePlace.getChildren().add(eventDateHBOX);
            eventDatePlace.getChildren().add(eventPlaceHBOX);

            VBox eventPriceAndDetails = new VBox();
            eventPriceAndDetails.setMinWidth(container.getMaxWidth());

            Label eventPrice = new Label(String.valueOf(event.getPrice()));
            eventPrice.setStyle(styleData);
  
            HBox eventPriceHBOX=new HBox();
            eventPriceHBOX.getChildren().add(priceeIcon);
            eventPriceHBOX.getChildren().add(eventPrice);
            

            Label goToDetails = new Label("Details");
           goToDetails.setStyle(Styledetails);

            eventPriceAndDetails.getChildren().add(eventPriceHBOX);
            eventPriceAndDetails.getChildren().add(goToDetails);

            HBox eventActions = new HBox();
            Button update = new Button("Modifier");
            Button delete = new Button("Supprimer");
            update.setStyle(StyleUpdate);
            delete.setStyle(StyleDelete);
            delete.setMinWidth(90);
            update.setMinWidth(90);

            eventActions.getChildren().add(update);
            eventActions.getChildren().add(delete);

            container.getChildren().add(eventPicture);
            container.getChildren().add(eventTitle);
            container.getChildren().add(eventDescription);
            container.getChildren().add(eventDatePlace);
            container.getChildren().add(eventPriceAndDetails);
            container.getChildren().add(eventActions);

    
            
            delete.setOnMouseClicked((ev) -> {
            
                try {
                    eventService.Supprimer(event);
                    getMyEvents();
                } catch (SQLException ex) {
                    Logger.getLogger(Afficher_mesévenementsController.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            });
            
            
            update.setOnMouseClicked((ev) -> {
            
                
                                    EventPlaceService eventPlaceService=new EventPlaceService();
                try {
                    ObservableList<EventPlace> eventsPlacesList = FXCollections.observableList(eventPlaceService.Afficher());
                    
                    UpdateEventcomboPlace.itemsProperty().setValue(eventsPlacesList);
                    
                    
                     UpdateEventcomboPlace.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("Product Name: " + newValue.getTitle());
            System.out.println("Product Description: " + newValue.getDescription());
            System.out.println("Product Id: " + newValue.getId());
            selectedEventPlace=newValue;
        });

      //  convertComboDisplayList();
       UpdateEventcomboPlace.setConverter(new StringConverter<EventPlace>() {
            @Override
            public String toString(EventPlace product) {
                return product.getTitle();
            }

            @Override
            public EventPlace fromString(final String string) {
                return UpdateEventcomboPlace.getItems().stream().filter(product -> product.getTitle().equals(string)).findFirst().orElse(null);
            }
        });
                    

                } catch (SQLException ex) {
                    Logger.getLogger(Afficher_mesévenementsController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
                
                
              closeAllWindows();
              eventToUpdate=event;
              myeventsUpdate.setVisible(true);
              updateEventTitle.setText(event.getTitle());
              UpdateEventDescription.setText(event.getDescription());
              UpdateEventDate.setValue( Instant.ofEpochMilli(event.getDate().getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
              UpdateEventNbr.setText(String.valueOf(event.getCapacite()));
              UpdateEventPrice.setText(String.valueOf(event.getPrice()));
              File file1 =new File( Constants.EVENT_PICTURES_PATH+event.getPicture());
              Image image1=new Image(file.toURI().toString());
              UpdateEventPicturePreview.setImage(image1);
             
              
            });
            
           goToDetails.setOnMouseClicked((e) -> {
               closeAllWindows();
               eventDetails=event;
               DetailEventTitle.setText(eventDetails.getTitle());
               DetailEventDescription.setText(eventDetails.getDescription());
               DetailEventPlaceTitle.setText(eventDetails.getEventPlace().getTitle());
               DetailEventPrice.setText(String.valueOf(eventDetails.getPrice()));
               DetailEventNbr.setText(String.valueOf(eventDetails.getCapacite()));
               DetailEventDate.setText(String.valueOf(eventDetails.getDate().toString()));
               File f2=new File(Constants.EVENT_PICTURES_PATH+eventDetails.getPicture());
               Image img2=new Image(f2.toURI().toString());
               DetailEventPicture.setImage(img2);
               
               myeventsDetails.setVisible(true);
           });
           DetailEventCancel.setOnMouseClicked((e) -> {
               closeAllWindows();
               myeventsPage.setVisible(true);
           });
           MapEventCancel.setOnMouseClicked((e) -> {
                 closeAllWindows();
              
               DetailEventTitle.setText(eventDetails.getTitle());
               DetailEventDescription.setText(eventDetails.getDescription());
               DetailEventPlaceTitle.setText(eventDetails.getEventPlace().getTitle());
               DetailEventPrice.setText(String.valueOf(eventDetails.getPrice()));
               DetailEventNbr.setText(String.valueOf(eventDetails.getCapacite()));
               DetailEventDate.setText(String.valueOf(eventDetails.getDate().toString()));
               File f2=new File(Constants.EVENT_PICTURES_PATH+eventDetails.getPicture());
               Image img2=new Image(f2.toURI().toString());
               DetailEventPicture.setImage(img2);
               
               myeventsDetails.setVisible(true);
           });
           
           DetailEventOpenMap.setOnMouseClicked((e) -> {
               
                        WebEngine webEngine = myeventsDetailsMapWebView.getEngine();
                           final URL urlGoogleMaps = getClass().getResource("/Configuration/OSM.html");
           // webEngine.load(urlGoogleMaps.toExternalForm());
               System.out.println("event place:"+event.getEventPlace().toString());
               System.out.println("long:"+event.getEventPlace().getLongitude()+"\n latitude: "+event.getEventPlace().getLatitude());
           webEngine.loadContent(Constants.setMapData(event.getEventPlace().getLongitude(),event.getEventPlace().getLatitude(),event.getEventPlace().getTitle()));
               
               closeAllWindows();
               //to do map
               myeventsDetailsMap.setVisible(true);
           });
            
            return container;
           
    }

    
    public void closeAllWindows(){
        myeventsAdd.setVisible(false);
        myeventsUpdate.setVisible(false);
        myeventsPage.setVisible(false);
        myeventsDetails.setVisible(false);
        myeventsDetailsMap.setVisible(false);
        
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