package service;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
//import javax.swing.text.html.HTML;

public class FileUploader {
   // private static String ScriptsURL = "http://localhost/Formation/";
     private static String ScriptsURL = "http://localhost/PI/";
    public static String upload(String path){
        try {
            HttpURLConnection httpUrlConnection = (HttpURLConnection)new URL(ScriptsURL+"upload.php").openConnection();// url class: to open a connection to the file we want to download.
            httpUrlConnection.setDoOutput(true);
            httpUrlConnection.setRequestMethod("POST");
            httpUrlConnection.setRequestProperty("Content-Type", "mutipart/form-data");
            OutputStream os = httpUrlConnection.getOutputStream();
            Thread.sleep(1000);
            BufferedInputStream fis = new BufferedInputStream(new FileInputStream(path));
            
            int totalByte = fis.available();
            int byteTrasferred;
            for (int i = 0; i < totalByte; i++) {
                os.write(fis.read());
                byteTrasferred = i + 1;
            }
            
            os.close();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            httpUrlConnection.getInputStream()));
            
            String s = null;
            String returnedPath = "";
            while ((s = in.readLine()) != null) {
                if(s!=null)
                    returnedPath+=s;
            }
            in.close();
            fis.close();
            return returnedPath;
        } catch (InterruptedException ex) {
            Logger.getLogger(FileUploader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileUploader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileUploader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static boolean delete(String filename){
        try {
            URL url = new URL(ScriptsURL+"delete.php?filename="+filename);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            
            return (connection.getResponseCode() == 200?true:false);
        } catch (MalformedURLException ex) {
            Logger.getLogger(FileUploader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileUploader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
