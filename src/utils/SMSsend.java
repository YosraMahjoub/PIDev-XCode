/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author asus
 */
public class SMSsend {
    public static void SMSsend(String number, String text){
        try {
            // Construct data
            String apiKey = "apikey=" + "Z9xR8muzohA-SJ5M16qJHNrzhZ2a0FYFkH3FsakMGa";
            String message = "&message=" + "hi";
            String sender = "&sender=" + "mimi";
            String numbers = "&numbers=" + number;

            // Send data
            HttpURLConnection conn = (HttpURLConnection) new URL("https://api.txtlocal.com/send/?").openConnection();
            String data = apiKey + numbers + message + sender;
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
            conn.getOutputStream().write(data.getBytes("UTF-8"));
            final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            final StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = rd.readLine()) != null) {
                    //stringBuffer.append(line);
                    System.out.println("message = "+line);
            }
            rd.close();
            //return stringBuffer.toString();
        } catch (Exception e) {
                System.out.println("Error SMS "+e);
                //return "Error "+e;
        }
    }
    
}
