/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xcode.entity;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import xcode.DBaseC.ConnectionDB;

/**
 *
 * @author Mega-PC
 */
public class PDFAP {
    
    public void PDFCreator(){
        
        try{
            String file_name="C:\\Users\\Mega-PC\\Desktop\\XCode\\src\\xcode\\QRCODES\\TEST.pdf";
            Document document= new Document();
            PdfWriter.getInstance(document, new FileOutputStream(file_name));
            document.open();
            
            PreparedStatement pst;
            Connection connection=ConnectionDB.getInstance().getCnx();
            ResultSet rs;
            
            String req="select c.commande_id ,u.nom, o.nom , o.Prix, u.user_id, c.user_id, o.oeuvrage_id from oeuvrage o, commande c, user u where u.user_id =c.user_id AND u.user_id = '1'";
            pst=connection.prepareStatement(req);
            rs=pst.executeQuery();
            
            while(rs.next()){
                Paragraph para=new Paragraph (rs.getInt("c.commande_id")+" "+rs.getString("u.nom")+" "+rs.getString("o.nom")+" "+rs.getInt("o.Prix"));
                document.add(para);
                document.add(new Paragraph(" "));
            }
            document.close();
            System.out.println("Done");
        } catch (Exception e){
            System.err.println(e);
        }
        
    }
}
