/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fanny.Controllers;

import fanny.Entities.Event;
import fanny.Entities.EventPlace;
import fanny.Entities.User;
import fanny.Services.EventPlaceService;
import fanny.Services.EventService;
import java.net.URL;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ZoomEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ArtistMainPageController implements Initializable {

    @FXML
    private AnchorPane eventLM;
    @FXML
    private AnchorPane EventManagerPage;
    @FXML
    private TextField AddEventTitle;
    @FXML
    private TextArea AddEventDescription;
    @FXML
    private DatePicker AddEventDate;
    @FXML
    private TextField AddEventPrice;
    @FXML
    private ComboBox<EventPlace> AddEventPlace;
    @FXML
    private Button AddEventPicker;
    @FXML
    private ImageView AddEventPicturePreview;
    @FXML
    private Button AddEventSave;
    @FXML
    private TableColumn<?, ?> evenement_id;
    @FXML
    private TableColumn<?, ?> date_creation;
    @FXML
    private TableColumn<?, ?> date_evenement;
    @FXML
    private TableColumn<?, ?> prix;
    @FXML
    private TableColumn<?, ?> titre;

    /**
     * Initializes the controller class.
     */
    
    ObservableList<Event> list = FXCollections.observableArrayList(
    
   //   new Event("test", "", "", 0, 12, "", "", 15, "", 0)
    );
    @FXML
    private TableColumn<Event, String> lieu_event_tv;
    @FXML
    private AnchorPane EventDetails;
    @FXML
    private TableView<?> table_view_events;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            EventsTableView();
        } catch (SQLException ex) {
            Logger.getLogger(ArtistMainPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            fillComboBoxPlaces();
        } catch (SQLException ex) {
            Logger.getLogger(ArtistMainPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void eventLM(MouseEvent event) {
   hideAll();
   EventManagerPage.setVisible(true);
    }
    
    
    public void hideAll(){
        EventManagerPage.setVisible(false);
    }

    @FXML
    private void AddEventOpenPicker(ZoomEvent event) {
    }

    @FXML
    private void AddEventSave(MouseEvent event) throws SQLException {
        
        ZoneId defaultZoneId = ZoneId.systemDefault();
        Event e=new Event(AddEventTitle.getText().toString(),AddEventDescription.getText().toString(),"picture.png",Double.valueOf(AddEventPrice.getText().toString()),Date.from(AddEventDate.getValue().atStartOfDay(defaultZoneId).toInstant()),new User(1,"khairi","bennour","ARTIST","123456"),new EventPlace(1,"","",123f,123f),new Date(),"il manque le domaine",100);
        EventService es=new EventService();
        es.Ajouter(e);
        /*
        e.setArtist(new User(1,"khairi","bennour","ARTIST",""));
        e.setTitle(AddEventTitle.getText().toString());
        e.setDate(new Date());
        e.setDescription("fff");
        e.setPrice(12.1f);
        e.setPicture("picture.png");
        e.setEventPlace(new EventPlace(1,"any","desc",123f,124f));
        
*/
        this.EventsTableView();
        
    }
    
    public void EventsTableView() throws SQLException{
        EventService eventService=new EventService();
                 ObservableList  data = FXCollections.observableArrayList(eventService.Afficher());
               table_view_events.setItems(data);
               evenement_id.setCellValueFactory(new PropertyValueFactory<>("Id"));
               date_creation.setCellValueFactory(new PropertyValueFactory<>("creationDate"));
               date_evenement.setCellValueFactory(new PropertyValueFactory<>("date"));
               prix.setCellValueFactory(new PropertyValueFactory<>("Price"));
                   lieu_event_tv.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Event, String>, ObservableValue<String>>() {
            
                       
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Event, String> param) {
                return new SimpleStringProperty(param.getValue().getEventPlace().getTitle());
            }
        });
                   table_view_events.refresh();
        /*
         trec.setCellValueFactory(new PropertyValueFactory<>("titre"));
         tob.setCellValueFactory(new PropertyValueFactory<>("objet"));
         tcon.setCellValueFactory(new PropertyValueFactory<>("contenue"));
         tnom.setCellValueFactory(new PropertyValueFactory<>("nomutilisateur"));
*/
  
    }
    
    public void fillComboBoxPlaces() throws SQLException{
        EventPlaceService eventPlaceService=new EventPlaceService();
       // List
        ObservableList<EventPlace> items = FXCollections.observableArrayList(eventPlaceService.Afficher());
       AddEventPlace.setItems(items);
      
       
        
       
           /*
               @Override
            public ListCell<Site> call(ListView<Site> p) {
                
                final ListCell<Site> cell = new ListCell<Site>(){

                    @Override
                    protected void updateItem(Site t, boolean bln) {
                        super.updateItem(t, bln);
                        
                        if(t != null){
                            setText(t.name + ":" + t.webaddr);
                        }else{
                            setText(null);
                        }
                    }
 
                };
                
                return cell;
            }
            }
           */
// Set the ComboBox to use the items list

// Allow the user to update the 
    }
}
