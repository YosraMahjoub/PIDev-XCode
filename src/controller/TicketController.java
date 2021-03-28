/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import entities.Event;
import entities.User;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class TicketController implements Initializable {

    @FXML
    private ImageView eventTicketPicture;
    @FXML
    private Label eventTicketTItle;
    @FXML
    private Label eventTicketDate;
    @FXML
    private Label eventTicketPlace;
    @FXML
    private Label eventTicketDescription;
    @FXML
    private Label eventTicketUser;
    @FXML
    private Label eventTicketPrice;
    @FXML
    private Button eventTicketClose;
    @FXML
    private Button eventTicketPrint;
    @FXML
    private AnchorPane container;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        Event  event= new Event();
        User user= new User();
        
        event=AccueilUtilisateurController.ticketEventData;
        user=AccueilUtilisateurController.ticketEventUserData;
        
        
        
        
        eventTicketTItle.setText(event.getTitle());
        eventTicketDescription.setText(event.getDescription());
        eventTicketPlace.setText(event.getEventPlace().getTitle());
        eventTicketDate.setText(event.getDate().toString());
        eventTicketUser.setText(user.getNom()+ " "+user.getPrenom());
        eventTicketPrice.setText(String.valueOf(event.getPrice())+" dt");
        
        
        //generate qr code
        
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
        String myWeb = "ticket au nom de Mr "+user.getNom()+" "+ user.getPrenom()+"\n evenement: "+event.getTitle();
        int width = 300;
        int height = 300;
        String fileType = "png";
        
        BufferedImage bufferedImage = null;
        try {
            BitMatrix byteMatrix = qrCodeWriter.encode(myWeb, BarcodeFormat.QR_CODE, width, height);
            bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            bufferedImage.createGraphics();
            
            Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, width, height);
            graphics.setColor(Color.BLACK);
            
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }
            
            System.out.println("Success...");
            
        } catch (WriterException ex) {
            Logger.getLogger(TicketController.class.getName()).log(Level.SEVERE, null, ex);
        }
        eventTicketPicture.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
        
        //end generate qr
        
        
        
        
        eventTicketClose.setOnMouseClicked((ev) -> {
        
             Stage stage = (Stage) eventTicketClose.getScene().getWindow();
    // do what you have to do
    stage.close();
            
        });
        
        eventTicketPrint.setOnMouseClicked((ev) -> {
        
            PrinterJob job = PrinterJob.createPrinterJob();
 if(job != null){
     eventTicketClose.setVisible(false);
     eventTicketPrint.setVisible(false);
   job.printPage(container);
   job.endJob();
     eventTicketClose.setVisible(true);
     eventTicketPrint.setVisible(true);
     
 }
        });
    }    
    
}
