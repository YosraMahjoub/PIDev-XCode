/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.User;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import service.UserService;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class Affichage_utilisateursController implements Initializable {

    @FXML
    private TextField recho;
    @FXML
    private Button rechbtn;
    @FXML
    private GridPane grid;
    //private MyListener myListener;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        UserService pdao = new UserService();
//        List<User> listUser =new ArrayList<>();
//        listUser.addAll(pdao.displayvendeurs());
//        if (listUser.size() > 0) {
//                setChosenU(listUser.get(0));
//                myListener = new MyListener() {
//                    @Override
//                    public void onClickListener(MouseEvent event ,Oeuvre oeuvre) {
//
//                        setChosenO(oeuvre);
//                    }
//                };
//            }
//        int column = 0;
//            int row = 1;
//           try {
//                for (int i = 0; i < listUser.size(); i++) {
//
//                   FXMLLoader fxmlLoader = new FXMLLoader();
//                   fxmlLoader.setLocation(getClass().getResource("/xcode/views/affuser.fxml"));
//                   AnchorPane anchorPane = fxmlLoader.load();
//
//                    AffuserController itemController = fxmlLoader.getController();
//                itemController.setData(listUser.get(i),myListener);
//
//                if (column == 3) {
//                    column = 0;
//                    row++;
//                }
//                grid.add(anchorPane, column++, row); //(child,column,row)
//                //set grid width
//                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
//                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
//                grid.setMaxWidth(Region.USE_PREF_SIZE);
//
//                //set grid height
//                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
//                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
//                grid.setMaxHeight(Region.USE_PREF_SIZE);
//                GridPane.setMargin(anchorPane, new Insets(10));
//                }
//                } catch (IOException ex) {
//                   Logger.getLogger(AffmesoeuvesController.class.getName()).log(Level.SEVERE, null, ex);
//               }
//    }
//private void setChosenU(User) {
//        nomO.setText(o.getNom());
//        prixo.setText((o.getPrix())+"DT");
//        descO.setText(o.getDescription());
//                File newFile2 = new File("C:\\xampp\\htdocs\\PI\\IMG\\" + o.getImg());
//
//        //image = new Image(getClass().getResourceAsStream(o.getImg()));
//        imgO.setImage(new Image(newFile2.toURI().toString()));
//        oi=o;
//    }    
    
}
}
