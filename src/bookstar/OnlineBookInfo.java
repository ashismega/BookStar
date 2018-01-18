/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * @author 348676487
 */
public class OnlineBookInfo {
    
    String search;    
  
    public String createLink(){
        return "https://www.googleapis.com/books/v1/volumes?q="+search;
    }

    public String html(String link) {
        URL url;
        InputStream is = null;
        BufferedReader br;

        String fullcontent = null;

        try {
            url = new URL(link);
            URLConnection conn = url.openConnection();
            is = url.openStream();  // throws an IOException
            br = new BufferedReader(new InputStreamReader(is));

            while (br.readLine() != null) {
                fullcontent += br.readLine() + "\n";
            }
            is.close();

        } catch (MalformedURLException mue) {
            System.out.println(mue);
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
        return fullcontent;
    }

    public String extractContent(String text) {
        String[] starting = text.split("\"title\": \"");
        String[] ending = null;
        String returnStr = "";

        if (starting.length > 1) {
            ending = starting[1].split("\",");
            System.out.println(ending[0]);
        }

        return ending[0];
    }

}