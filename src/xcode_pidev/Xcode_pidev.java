/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xcode_pidev;

import java.io.IOException;
import javafx.application.Application;
import javafx.beans.property.adapter.ReadOnlyJavaBeanDoublePropertyBuilder;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


import utils.ConnexionDB;

/**
 *
 * @author pc
 */
public class Xcode_pidev extends Application {
    
    private Stage primaryStage;
    private Parent parentPage;
    
    @Override
    public void start(Stage primaryStage) throws IOException {
//        Button btn = new Button();
//        btn.setText("Say 'Hello World'");
//       
//        btn.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.println("Hello World!");
//                ConnexionDB ct = ConnexionDB.getInstance();
//                Oeuvre o1 = new Oeuvre(5, "yosra", "peinture", 22, 33, "echriw", "img");
//               OeuvrageService os = new OeuvrageService();
//                os.ajouterO(o1);
//            }
//            
//        });
//        StackPane root = new StackPane();
//        root.getChildren().add(btn);
//        
//        Scene scene = new Scene(root, 300, 250);
//        
//        primaryStage.setTitle("Hello World!");
//        primaryStage.setScene(scene);
//        primaryStage.show();

this.primaryStage = primaryStage;
        this.primaryStage.setTitle("gestion des œuvres");
        
        parentPage = FXMLLoader.load(getClass().getResource("/views/login.fxml"));
        Scene scene = new Scene(parentPage);
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
