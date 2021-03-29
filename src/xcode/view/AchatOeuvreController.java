/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xcode.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Mega-PC
 */
public class AchatOeuvreController implements Initializable {

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
    private Slider prixo;
    @FXML
    private RadioButton nouvo;
    @FXML
    private ToggleGroup etat;
    @FXML
    private RadioButton rato;
    @FXML
    private Button filter;
    @FXML
    private TextField recho;
    @FXML
    private Button rechbtn;
    @FXML
    private ScrollPane scrollO;
    @FXML
    private GridPane grid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
