/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Cours;
import service.TextFileReader;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HELA
 */
public class AfficherCoursFiles implements Initializable {

    @FXML
    private TextArea linesTextArea;

    /**
     * Initializes the controller class.
     */
    private TextFileReader reader = new TextFileReader();
    private static final Logger LOG = Logger.getLogger(AfficherCoursController.class.getName());
    static Cours c ;
    @FXML 
            Button showFile;
    @FXML
    TextField  urlTextField;
        private Future<List<String>> future;
	private ExecutorService executorService = Executors.newSingleThreadExecutor();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        future = executorService.submit(new Callable<List<String>>() {
			public List<String> call() throws Exception {
				return reader.read(new File ("C:\\xampp\\htdocs\\PI\\IMG\\" + AfficherCoursController.aa.getFile())
                                );
			}
		});
		
		List<String> lines;
        try {
            lines = future.get();

		executorService.shutdownNow();
		linesTextArea.clear();
		for (String line : lines ) {
			linesTextArea.appendText(line + "\n");
		}
                        } catch (InterruptedException ex) {
            Logger.getLogger(AfficherCoursFiles.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(AfficherCoursFiles.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void backFor(ActionEvent event) {
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/views/CoursListe.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AfficherCoursController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
