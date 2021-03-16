/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author asus
 */
//import java.io.IOException;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.mime.MultipartEntityBuilder;
//import org.apache.http.entity.mime.content.FileBody;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.util.EntityUtils;
public class httprequest {
  
//
//    String uploadFile(String url, File file) throws IOException
//    {
//        HttpPost post = new HttpPost(url);
//        post.setHeader("Accept", "application/json");
//        _addAuthHeader(post);
//        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
//        // fileParamName should be replaced with parameter name your REST API expect.
//        builder.addPart("fileParamName", new FileBody(file));
//        //builder.addPart("optionalParam", new StringBody("true", ContentType.create("text/plain", Consts.ASCII)));
//        post.setEntity(builder.build());
//        HttpResponse response = getClient().execute(post);;
//        int httpStatus = response.getStatusLine().getStatusCode();
//        String responseMsg = EntityUtils.toString(response.getEntity(), "UTF-8");
//
//        // If the returned HTTP response code is not in 200 series then
//        // throw the error
//        if (httpStatus < 200 || httpStatus > 300) {
//            throw new IOException("HTTP " + httpStatus + " - Error during upload of file: " + responseMsg);
//        }
//
//        return responseMsg;
//    }


    }
