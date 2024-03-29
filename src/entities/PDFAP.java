/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import utils.ConnexionDB;

/**
 *
 * @author Mega-PC
 */
public class PDFAP {
    
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 35,
            Font.BOLD);
    private static Font facFont = new Font(Font.FontFamily.TIMES_ROMAN, 20,
            Font.BOLD);
    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL, BaseColor.RED);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);
    
    
            PreparedStatement pst;
            Connection connection=ConnexionDB.getInstance().getConnection();
    
    public void PDFCreator(){
        
        try{
            
            ResultSet rs;
                        
            String rnomu="SELECT Max(c.commande_id), u.user_id , u.nom, u.prenom, c.commande_id, c.user_id FROM user u, commande c WHERE c.user_id=u.user_id";
            pst=connection.prepareStatement(rnomu);
            rs=pst.executeQuery();
            Document document= new Document();
            while(rs.next()){
            String file_name="C:\\Users\\pc\\Documents\\NetBeansProjects\\PIDev-XCode\\src\\PDFGen\\"+rs.getString("Max(c.commande_id)")+"_"+rs.getString("u.nom")+"_"+rs.getString("u.prenom")+".pdf";
            PdfWriter.getInstance(document, new FileOutputStream(file_name));
            }
            document.open();
            
            
            System.out.println("PDF Généré");
            
            
            
            String filename = "C:\\Users\\pc\\Documents\\NetBeansProjects\\PIDev-XCode\\src\\img\\fanny.png";
            Image image = Image.getInstance(filename);
            image.setAlignment(Element.ALIGN_TOP);
            document.add(image);
            
            
            
            
            Paragraph preface = new Paragraph();
            
            preface.add(Chunk.NEWLINE);
            preface.add(Chunk.NEWLINE);
            preface.add(new Paragraph("Facture", catFont));
            preface.add(Chunk.NEWLINE);
            preface.add(Chunk.NEWLINE);
               DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd à HH:mm");  
   LocalDateTime now = LocalDateTime.now();  
            preface.add(new Paragraph(
                "Achat effectué le : " + dtf.format(now),smallBold));
           
            preface.add(new Paragraph(
                "Veuillez conserver ce document jusqu'à ce que votre achat soit terminé.",
                redFont));
            
            preface.add(Chunk.NEWLINE);
            preface.add(Chunk.NEWLINE);
            preface.add(Chunk.NEWLINE);

            try{
            String rq="SELECT Max(c.commande_id), c.commande_id,c.user_id, u.user_id, u.nom, u.prenom, u.adresse, u.num_tel FROM commande c , user u WHERE u.user_id=c.user_id";
            pst=connection.prepareStatement(rq);
            rs=pst.executeQuery(); 
            
            while(rs.next()){
            preface.add(new Paragraph(
            "Destinataire : "+" ",facFont));
            preface.add(new Paragraph
            ("Commande N° : "+" "+rs.getInt("Max(c.commande_id)")));
            preface.add(new Paragraph(
            "Nom Client : "+" "+rs.getString("u.nom")+" "+rs.getString("u.prenom")));
            preface.add(new Paragraph(
            "Num telephone : "+" "+rs.getInt("u.num_tel")));
            preface.add(new Paragraph(
            "Adresse : "+" "+rs.getString("u.adresse")));
            }
            document.add(preface);
            
            } catch (Exception e) {
            e.printStackTrace();
            }
            document.add( Chunk.NEWLINE );
            document.add( Chunk.NEWLINE );


                  
            try{

            String req="SELECT c.commande_id , p.oeuvrage_id, o.nom, p.quantite, o.prix FROM commande c "
                    + "INNER JOIN panier p ON c.commande_id=p.commande_id "
                    + "INNER JOIN oeuvrage o ON o.oeuvrage_id=p.oeuvrage_id where c.commande_id = (SELECT Max(commande_id) as maxi from panier)";
            pst=connection.prepareStatement(req);
            rs=pst.executeQuery();
            Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
            float[] colsWidth = {1f, 1f, 1f,1f}; // Code 1
            PdfPTable table = new PdfPTable(colsWidth);
            table.setWidthPercentage(100); // Code 2
            table.setHorizontalAlignment(Element.ALIGN_LEFT);//Code 3
            Phrase Ref = new Phrase ("Ref", boldFont);
            Phrase Oeuvrages = new Phrase("Oeuvrages", boldFont );
            Phrase Quantite = new Phrase("Quantite", boldFont );
            Phrase Prix = new Phrase("Prix unitaire", boldFont );
            table.addCell(Ref);
            table.addCell(Oeuvrages);
            table.addCell(Quantite);
            table.addCell(Prix);
            while(rs.next()){
            table.addCell(" "+rs.getInt("p.oeuvrage_id"));
            table.addCell(" "+rs.getString("o.nom"));
            table.addCell(" "+rs.getInt("p.quantite"));
            table.addCell(" "+rs.getInt("o.Prix")+" TND");
            }
            document.add(table);
            document.add(Chunk.NEWLINE);
            
            
            
            try{
            String ra="SELECT c.commande_id , c.PrixTot FROM commande c where c.commande_id = (SELECT Max(commande_id) as maxi from commande)";
            pst=connection.prepareStatement(ra);
            rs=pst.executeQuery();
            Paragraph pa = new Paragraph();
            while(rs.next()){
             pa.add(" Prix Total :"+rs.getInt("c.PrixTot")+" TND");   
            }
             pa.setAlignment(Element.ALIGN_RIGHT);
             document.add(pa);
             
            document.close();
            System.out.println("Done");
            }catch (Exception e){
                e.printStackTrace();
            }
            } catch (Exception e) {
            e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
}
}


