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

    public String createLink() {
        String[] words = search.split(" ");
        String term = "";
        for (int i = 0; i < words.length; i++) {
            term += words[i] + "+";
        }
        return "https://www.googleapis.com/books/v1/volumes?q=" + term.substring(0, term.length() - 1);
    }

    /**
     * Using a given google books link, return the page's HTML content (has
     * multiple books).
     *
     * @param link The google link to obtain the book's information from
     * @return page The page's HTML content
     */
    public String pageHTML(String link) {
        URL url;
        InputStream is = null;
        BufferedReader br;
        //Store the html content of the page
        String page = null;

        try {
            url = new URL(link);
            URLConnection conn = url.openConnection();
            is = url.openStream();  // throws an IOException
            br = new BufferedReader(new InputStreamReader(is));

            //The lines read from the page
            String read = "";
            //Check if it is ull
            while (read != null) {
                //Read the page's next line
                read = br.readLine();
                //Add the line to the string
                page += read + "\n";
            }
            //Close the inputstream
            is.close();

        } catch (MalformedURLException mue) {
            System.out.println(mue);
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
        //Return the page's HTML content
        return page;
    }

    /**
     * Given the HTML content of a google book's page, extract the first book.
     *
     * @param page HTML content of a google book's page
     * @return The first book from the search
     */
    public String bookHTML(String page) {
        //Book not found
        if (fieldHTML(page, "\"totalItems\": ", "\n" + "}").equals("0")) {
            throw new NullPointerException();
        } //Book found
        else {
            //Seperate the books on the html page  
            String[] book = page.split("\"kind\": \"");
            //Return the first book
            return book[2];
        }
    }

    /**
     * Given a book's HTML content, extract the title, subtitle, authors,
     * publisher, published date, description, ISBN-13, ISBN-10, page count,
     * categories, thumbnail, and average rating.
     *
     * @param book A book's HTML content
     * @return bookInfo Book information of the given book
     */
    public String[] bookInformation(String book) {

        //Store the first book's information
        String[] bookInfo = new String[12];

        //Add the title
        bookInfo[0] = fieldHTML(book, "\"title\": \"", "\",");

        //Add the subtitle
        bookInfo[1] = fieldHTML(book, "\"subtitle\": \"", "\",");
        //Add the author
        bookInfo[2] = fieldHTML(book, "\"authors\": \\[\n" + "     \"", "\"\n" + "    ]");
        //Add the publisher
        bookInfo[3] = fieldHTML(book, "\"publisher\": \"", "\",");
        //Add the published date
        bookInfo[4] = fieldHTML(book, "\"publishedDate\": \"", "\",");
        //Add the description
        bookInfo[5] = fieldHTML(book, "\"description\": \"", "\",");
        //Add the isbn_13
        bookInfo[6] = fieldHTML(book, "\"type\": \"ISBN_13\",\n" + "      \"identifier\": \"", "\"\n" + "     }");
        //Add the isbn_10
        bookInfo[7] = fieldHTML(book, "\"type\": \"ISBN_10\",\n" + "      \"identifier\": \"", "\"\n" + "     }");
        //Add the page count
        bookInfo[8] = fieldHTML(book, "\"pageCount\": ", ",");

        //Add the categories
        bookInfo[9] = fieldHTML(book, "\"categories\": \\[\n" + "     \"", "\"\n" + "    ]");
        //Add the image
        bookInfo[10] = fieldHTML(book, "\"thumbnail\": \"", "\"\n" + "    }");
        //Add the google average rating
        bookInfo[11] = fieldHTML(book, "averageRating\": ", ",");

        //Return the information of the first book found
        return bookInfo;
    }

    /**
     * Given a books HTML content and the starting/ending information
     * surrounding the needed text, extract a specfic piece of information.
     *
     * @param book The HTML content of the book
     * @param start The information before the required text
     * @param end The information after the required test
     * @return information The extracted information required
     */
    public String fieldHTML(String book, String start, String end) {
        //Split the html based on the first part surrounding the required text
        String[] starting;
        starting = book.split(start);
        String[] ending = null;

        //Field exists
        if (starting.length > 1) {
            //Split the html based on the last part surrounding therequired text
            ending = starting[1].split(end);
        } //Field does not exists
        else {
            return "N/A";
        }
        //Store the extracted information
        String information = ending[0];

        //Return the extracted information
        return information;
    }

}
