/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;
import service.FileDownloader;
    import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
 
//import javax.swing.JOptionPane;

/**
 *
 * @author HELA
 */
public class Download {

 
/**
 * Execute file download in a background thread and update the progress.
 * @author www.codejava.net
 *
 */
//public class DownloadTask extends SwingWorker<Void, Void> {
    private static final int BUFFER_SIZE = 4096;   
    private String downloadURL;
    private String saveDirectory;
  //  private SwingFileDownloadHTTP gui;
    
    public Download( String downloadURL, String saveDirectory) {
       // this.gui = gui;
        this.downloadURL = downloadURL;
        this.saveDirectory = saveDirectory;
    }
     
    /**
     * Executed in background thread
     */
    
    public Void doInBackground() throws Exception {
        try {
            FileDownloader util = new FileDownloader();
            util.downloadFile(downloadURL);
             
            // set file information on the GUI
           // gui.setFileInfo(util.getFileName(), util.getContentLength());
             
            String saveFilePath = saveDirectory + File.separator + util.getFileName();
 
            InputStream inputStream = util.getInputStream();
            // opens an output stream to save into file
            FileOutputStream outputStream = new FileOutputStream(saveFilePath);
 
            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead = -1;
            long totalBytesRead = 0;
            int percentCompleted = 0;
            long fileSize = util.getContentLength();
 
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
                totalBytesRead += bytesRead;
                percentCompleted = (int) (totalBytesRead * 100 / fileSize);
 
               // setProgress(percentCompleted);         
            }
 
            outputStream.close();
 
            util.disconnect();
        } catch (IOException ex) {
            ex.getMessage();
            ex.printStackTrace();
            //setProgress(0);
           // cancel(true);          
        }
        return null;
    }
 
    /**
     * Executed in Swing's event dispatching thread
     */
//    @Override
//    protected void done() {
//        if (!isCancelled()) {
//            JOptionPane.showMessageDialog(gui,
//                    "File has been downloaded successfully!", "Message",
//                    JOptionPane.INFORMATION_MESSAGE);
//        }
//    }  
}
    

