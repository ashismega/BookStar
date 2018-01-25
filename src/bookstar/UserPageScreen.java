
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstar;

import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author 072660210
 */
public class UserPageScreen extends javax.swing.JFrame {

    private static Student s;

    //Sorted Ratings
    String sortedAverage[][] = sortbyRating(arraylistToArray(averageData(fileToArrayList("rateReview.txt"))));
    File ratingReview = new File("rateReview.txt");

    /**
     * Creates new form UserPageScreen
     *
     * @param s Student logged in
     */
    public UserPageScreen(Student s) {
        try{
            //checks if file exists, and if it doesnt it creates
            ratingReview.createNewFile();
        }
        catch(IOException ex){
            JOptionPane.showMessageDialog(this, "INPUT/OUTPUT EXCEPTION", "Input/Output Error", JOptionPane.ERROR_MESSAGE);
        }
        
        this.s = s;
        initComponents();
        
        //attains the suggested books
        String[] top = suggestedBooks();  
        try {       

        //Top rated book 1
        topRatedBooks(jLabel4, jLabel9, jButton3, 0);
        //Top rated book 2
        topRatedBooks(jLabel5, jLabel10, jButton4, 1);
        //Top rated book 3
        topRatedBooks(jLabel6, jLabel11, jButton5, 2);

        //Friend 1
        friendRecommend(top, jLabel7, jLabel13, jLabel15, jLabel20, jLabel22, jButton2, jButton7,0);

        //Friend 2
        friendRecommend(top, jLabel8, jLabel14, jLabel16, jLabel21, jLabel19, jButton6, jButton8,3);
        } catch (NullPointerException ex) {
            
        }
    }

    /**
     * Update jLabels related to recommended friends and books.
     *
     * @param top The standing of the friend.
     * @param name ID of the friend
     * @param one Recommended book 1
     * @param two Recommended book 2
     * @param imageOne Recommended book image 1
     * @param imageTwo Recommended book image 2
     * @param buttonOne Recommended book button 1
     * @param buttonTwo Recommended book button 1
     */
    public void friendRecommend(String[] top, JLabel name, JLabel one, JLabel two, JLabel imageOne, JLabel imageTwo, JButton buttonOne, JButton buttonTwo, int num) {
        try {
            // if any string is equal to "" it makes it equal to null
            for(int i=0; i<top.length; i++){
                if(top[i].equals("")){
                    top[i] = null;
                }
            }
            
            try{
                //Friend name
                name.setText(top[num]);
            }
            catch(NullPointerException ex){
                name.setText("NO FRIEND");
            }
            
            try{
                //Recommended book 1
                one.setText(top[1+num]);
                //Image of book 1
                imageOne.setIcon(new ImageIcon(addImage(searchBook(top[1+num])[10])));
                //View book 1
                buttonOne.setEnabled(true);
            }
            catch(NullPointerException ex){
                one.setText("NO BOOK AVAILABLE");
                buttonOne.setEnabled(false);
            }
            
            try{
                //Recommended book 2
                two.setText(top[2+num]);
                //Image of book 2
                imageTwo.setIcon(new ImageIcon(addImage(searchBook(top[2+num])[10])));
                //View book 2
                buttonTwo.setEnabled(true);
            }
            catch(NullPointerException ex){
                two.setText("NO BOOK AVAILABLE");
                buttonTwo.setEnabled(false);
            }

            
            
        } catch (NullPointerException ex) {
            //if null pointer caught makes everything as if no data was available
            
            //Friend name
            name.setText("NO FRIEND");

            //Recommended book 1
            one.setText("NO BOOK");
            //Recommended book 2
            two.setText("NO BOOK");

            //Image of book 1
            imageOne.setText("NO BOOK");
            //Image of book 2
            imageTwo.setText("NO BOOK");

            //View book 1
            buttonOne.setEnabled(false);
            //View book 2
            buttonTwo.setEnabled(false);
            
        }
    }

    /**
     * Update icons and title of jLabels to the top book.
     *
     * @param title The jLabel to add the title to.
     * @param image The jLabel to add the image to.
     * @param button The button to view the book.
     * @param num The standing of the book.
     */
    public void topRatedBooks(JLabel title, JLabel image, JButton button, int num) {
        try {
            //Book title
            title.setText(sortedAverage[num][0]);
            //Book image
            image.setIcon(new ImageIcon(addImage(searchBook(sortedAverage[num][0])[10])));
            //Enable button
            button.setEnabled(true);
        } catch (ArrayIndexOutOfBoundsException ex) {
            //No book title
            title.setText("NO RATED BOOKS");
            //No book image
            image.setText("NO RATED BOOKS");
            //Disable the button
            button.setEnabled(false);
        }
    }

    /**
     * Given the link of the book image, return the Image of the book.
     *
     * @param imageLink The link of the image.
     * @return Book image.
     */
    public Image addImage(String imageLink) {
        URL url;
        try {
            //Url of book image
            url = new URL(imageLink);
            //Image of the url
            return ImageIO.read(url).getScaledInstance(150, 150, Image.SCALE_DEFAULT);
        } catch (IOException ex) {
            //Error with obtaining the image
            System.out.println("Image search error");
            return null;
        }
    }

    /**
     * Read and file and create an array where each line in the file is an
     * element in the array.
     *
     * @param fileName The file name.
     * @return The array with each line the file being an element in the array.
     */
    public ArrayList<ArrayList<String>> fileToArrayList(String fileName) {
        
        //Arraylist for holding the book title and rating
        ArrayList<ArrayList<String>> temp = new ArrayList();
        //File with the review's of the books
        File ratingReview = new File(fileName);

        Scanner sc = null;
        //Name of Book
        temp.add(new ArrayList<String>());
        //Rating of Book
        temp.add(new ArrayList<String>());

        try {
            ratingReview.createNewFile();
            //Scanner for reading the file
            sc = new Scanner(ratingReview);
            //Add each line to the arraylist
            while (sc.hasNextLine()) {
                String info = sc.nextLine();
                if(info.toLowerCase().trim().equalsIgnoreCase("")){
                   break; 
                }
                String[] reviewInfo = info.split("~");
                temp.get(0).add(reviewInfo[0]);
                temp.get(1).add(reviewInfo[3]);
            }
            if (sc != null) {
                sc.close();
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "File Error", "File Error", JOptionPane.ERROR_MESSAGE);
        }
        return temp;
    }

    /**
     * Given a 2D arraylist, convert it to a 2Darray.
     *
     * @param temp The arraylist to convert.
     * @return The converted 2D array
     */
    public String[][] arraylistToArray(ArrayList<ArrayList<String>> temp) {
        //Store the data
        String[][] info = new String[temp.get(1).size()][2];

        //Store the arraylist data into the array
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < temp.get(1).size(); j++) {
                info[j][i] = temp.get(i).get(j);
            }
        }
        return info;
    }

    /**
     * Given the a list of ratings and book titles, determine the average
     * ratings of duplicate books.
     *
     * @param allData All the data to average.
     * @return The averaged data.
     */
    public ArrayList<ArrayList<String>> averageData(ArrayList<ArrayList<String>> allData) {

        //Unsorted books from the file
        ArrayList<ArrayList<String>> unsortedAveraged = new ArrayList();

        //Name of Book
        unsortedAveraged.add(new ArrayList<String>());
        //Average Rating of Book
        unsortedAveraged.add(new ArrayList<String>());

        //Loop through each book in the given arraylist
        for (int i = 0; i < allData.get(0).size(); i++) {
            //Check if the book title already exists
            if (!(unsortedAveraged.get(0).contains(allData.get(0).get(i)))) {
                //Add title
                unsortedAveraged.get(0).add(allData.get(0).get(i));
                //Add the average
                unsortedAveraged.get(1).add(Double.toString(averageBookRating(allData, allData.get(0).get(i))));
            }
        }
        return unsortedAveraged;
    }

    /**
     * Given the file data and the title of a book, determine the average rating
     * of that book.
     *
     * @param unsortedAveraged The full unsorted average data of books with
     * titles and ratings.
     * @param title The book title.
     * @return The average rating of the given book title.
     */
    public double averageBookRating(ArrayList<ArrayList<String>> unsortedAveraged, String title) {
        //Number of ratings of that book
        double numRating = 0;
        //Total ratings of that book
        double totalRating = 0;
        //Calculate the number of ratings and the total rating sum
        for (int i = 0; i < unsortedAveraged.get(0).size(); i++) {
            if (unsortedAveraged.get(0).get(i).equals(title)) {
                //Add to the number of ratings
                numRating++;
                //Add the rating of the book
                totalRating += Integer.parseInt(unsortedAveraged.get(1).get(i));
            }
        }
        //Average rating
        return totalRating / numRating;
    }

    /**
     * Sort a 2-D string array by its rating, rows store booktitle, columns
     * store rating, sort rating by greatest to least
     *
     * @param unsorted Unsorted 2-D String array of reviews
     * @return a sorted 2-D String Array
     */
    public String[][] sortbyRating(String[][] unsorted) {
        //sort array using Comparator class
        Arrays.sort(unsorted, new Comparator<String[]>() {
            @Override
            //implemented method for comparator
            public int compare(final String[] first, final String[] second) {
                return Double.valueOf(second[1]).compareTo(Double.valueOf(first[1]));
            }
        });
        //to 2-D String Array
        String[][] sorted = Arrays.copyOf(unsorted, unsorted.length);
        return sorted;
    }

    /**
     * Given a book title, search and obtain the book's information from google
     * books.
     *
     * @param title The title of the book.
     * @return The books information
     */
    public String[] searchBook(String title) {
        try {
            //Geting book information from google books helper class
            OnlineBookInfo o = new OnlineBookInfo();
            //Title to search
            o.search = title;
            //The books information
            return o.bookInformation(o.bookHTML(o.pageHTML(o.createLink())));
        } catch (NullPointerException ex) {
            return null;
        }
    }

    /**
     * Searches a for user ratings.
     * Searches for a users ratings using the
     * String param "s" taken in.
     * Returns the users ratings.
     * @return String array of reviews made by user
     */
    private String[] searchUserRatings() {
        //initialize Scanner and Arraylist
        Scanner scan = null;
        ArrayList userRatings = new ArrayList();
        try {
            //scan reviews file
            scan = new Scanner(ratingReview);
            //while there are more reviews
            while (scan.hasNext()) {
                //take the next line and delimit it
                String line = scan.nextLine();
                if(line.toLowerCase().trim().equalsIgnoreCase("")){
                   break; 
                }
                String[] lineArr = line.split("~");
                //if the student id in reviews is the same as current id
                if (lineArr[2].equals(s.getStudentNumber())) {
                    //put the book title and rating into a string
                    String bookRate = (lineArr[0] + "~" + lineArr[3]);
                    //add it into an arraylist
                    userRatings.add(bookRate);
                }

            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "FILE NOT FOUND", "Missing File Error", JOptionPane.ERROR_MESSAGE);
        }
        //closes the scanner if not null
        if (scan != null) {
            scan.close();
        }
        
        //output string array of user ratings
        Object[] array = userRatings.toArray();
        String[] a = new String[array.length];

        for (int i = 0; i < array.length; i++) {
            if (array[i] instanceof String) {
                a[i] = (String) array[i];

            }

        }

        return a;
    }
    
    /**
     * Searches a for user ratings.
     * Searches for a users ratings using the
     * String param "s" taken in.
     * Returns the users ratings.
     * @param s     the user to search by
     * @return      the ratings
     */
    private String[] searchUserRatings(String s) {
        //initialize Scanner and Arraylist
        Scanner scan = null;
        ArrayList userRatings = new ArrayList();
        try {
            //scan reviews file
            scan = new Scanner(ratingReview);
            //while there are more reviews
            while (scan.hasNext()) {
                //take the next line and delimit it
                String line = scan.nextLine();
                String[] lineArr = line.split("~");
                //if the student id in reviews is the same as current id
                if (lineArr[2].equals(s)) {
                    //put the book title and rating into a string
                    String bookRate = (lineArr[0] + "~" + lineArr[3]);
                    //add it into an arraylist
                    userRatings.add(bookRate);
                }

            }
        } catch (FileNotFoundException ex) {
            //if the files not found, send out a error message
            JOptionPane.showMessageDialog(this, "FILE NOT FOUND", "Missing File Error", JOptionPane.ERROR_MESSAGE);
        }
        //close the scanner
        if (scan != null) {
            scan.close();
        }
        //output string array of user ratings
        Object[] array = userRatings.toArray();
        String[] a = new String[array.length];
        
        for (int i = 0; i < array.length; i++) {
            if (array[i] instanceof String) {
                a[i] = (String) array[i];

            }

        }

        return a;
    }
    /**
     * Gives suggested books from friends.
     * Returns suggested books by friends you have
     * matched with along with friends user id.
     * @return  String array of user and their suggested books
     */
    public String[] suggestedBooks(){
        //gets the top two friends
        String[] top = topFriends();
        //initializes a string array that will contain the friends and suggested books
        String[] suggested = new String[6];
        //for loop that runs twice, one for each suggested friends, in otherwords runs for 2 friends
        for(int i=0;i!=2;i++){
            //string array for the records and ratings of the users friends
            String[] info; 
            String[] ratings;
            
            try{
                // splits friend record to get UserID,match score,and book names 
                info = top[i].split("~");
                //searches for books and ratings of friend
                ratings = searchUserRatings(info[0]);
            }
            catch(NullPointerException ex){
                //return null if top or the friends rating or record is equal to null
                return null;
            }
            //keeps track of the top 2 ratings for one person
            int one = 0;
            int two = 0;
            
            //top two books associated with those ratings
            String oneB = "";
            String twoB = "";
            
            //if the book name is rated or not
            boolean good = true;
            //goes through all rating+bookname of the friend
            for(String val: ratings){
                //splits into value into an array
                String[] data = val.split("~");
                //sets all the users books into an array
                String[] recordBooks = info[2].split(",");
                //goes through each book
                for(String books: recordBooks){
                    //ifone book the user rated matches with the book name, break out
                    if( books.equals(data[0]) ){
                        good = false;
                        break;
                    }   
                }
                
                
                if(good){
                    //sees if the rating is higher then a previous book entered
                    //sets new highest rating and book name if higher
                    if(one < Integer.parseInt(data[1])){
                        one = Integer.parseInt(data[1]);
                        oneB = data[0];
                    }
                    //tries again for second book
                    else if(two < Integer.parseInt(data[1])){
                        two = Integer.parseInt(data[1]);
                        twoB = data[0];
                    } 
                }   
            }
            
            // if suggestable books were found
            if(oneB.trim().equalsIgnoreCase("")){
                //since suggested array at index 0 and 3 are userID's and everything else are book names(1,2,4,5)
                //enters UserID and books
                suggested[3*i] = info[0];
                suggested[(3*i)+1] = oneB;
                suggested[(3*i)+2] = twoB;
            }
            
        }    
        return suggested;
    }
    
    /**
     * Grabs the top two friends
     * @return  String array of top 2 friends
     */
    private String[] topFriends(){
        
        //gets top friends
        String[] s = friendsCalc();
        //variables to keep track of top two match scors
        int one = 0;
        int two = 0;
        //variables to keep track of the records for the top two
        String[] top = {"", ""};
        //returns null if amount of friends returns less than 2
        if (s.length < 2) {
            return null;
        }
        else if(s.length==2){
            //if its equal to 2, just adds both of them to top and returns
            top[0] = s[0];
            top[1] = s[1];
            return top;
        }
        
        //enhanced forloop that grabs the record of another user each run
        for (String val : s) {
            //splits the record
            String[] temp = val.split("~");
            //if the match score is bigger then the current highest
            //records the user record and sets new highest match score
            if ( (Integer.parseInt(temp[1]) > one) ) {
                top[0] = val;
                one = Integer.parseInt(temp[1]);
            //tries set a second highest if first wasnt higher
            } else if (Integer.parseInt(temp[1]) > two) {
                top[1] = val;
                two = Integer.parseInt(temp[1]);
            }
        }  
        //returns the records of the top 2
        return top;
    }
    /**
     * Calculates how alike you are to other users.
     * Calculates how much your reviews match 
     * up with other users and how similar you are.
     * @return      String array of calculated users
     */
    private String[] friendsCalc() {
        //gets the users reviews
        String[] reviews = searchUserRatings();
        //if the reviews are null return null
        if (reviews == null) {

            return null;
        }
        //create a hashmap
        Map<String, String[]> map = new HashMap<>();
        
        //loop to go through the reviews
        for (int i = 0; i != reviews.length; i++) {
            //splits one review into record taht contains all data for one review in an array
            String[] record = reviews[i].split("~");
            
            //book title
            String book = record[0];
            //review score
            int review = Integer.parseInt(record[1]);
            //declares and initializes scanner
            Scanner sc = null;
            try {
                //sets scanner to ratingReview file
                sc = new Scanner(ratingReview);
                //while there is a next line
                while (sc.hasNext()) {
                    //string that contains a file record
                    String tempS = sc.nextLine();
                    //array that holds that record in an array
                    String[] tempRecord = tempS.split("~");
                    
                    //compares the book titles for each, if equal continues
                    if ( (tempRecord[0].equals(book)) ) {
                        //compares student numers, if not equal continues
                        if (!(s.getStudentNumber().equals(tempRecord[2]))) {
                            
                            //compares two differnt students that rated the same book
                            //determines match score
                            try {
                                //gets values for the student id listed in temp record
                                String[] vals = map.get(tempRecord[2]);
                                //determines match score by multiplying reviews, adds old match score
                                vals[0] = ((review * Integer.parseInt(tempRecord[3])) + Integer.parseInt(vals[0])) + "";
                                //adds books matched with
                                vals[1] = vals[1] + "," + book;

                            } catch (NullPointerException ex) {
                                //if key does not exist, creates key and adds value
                                String[] vals = {review * Integer.parseInt(tempRecord[3]) + "", "~" + book};
                                map.put(tempRecord[2], vals);

                            }
                        }
                    }
                }
                
            } catch (IOException ex) {
                
                JOptionPane.showMessageDialog(this, "Error While Searching", "Search Error", JOptionPane.ERROR_MESSAGE);
            }
            //close scanner
            if (sc != null) {
                sc.close();
            }
            
        }
        
        //converts map to an array that includes the keys, and the values(match score and book titles)
        String[] key = map.keySet().toArray(new String[0]);

        String[][] value = map.values().toArray(new String[0][0]);
        String[] result = new String[map.size()];
        
        for (int i = 0; i < map.size(); i++) {
            result[i] = key[i] + "~";
            for (int j = 0; j < 2; j++) {
                result[i] += value[i][j];

            }
        }

        return result;
    }

    /**
     * Once book is found, enable the screen to display the books profile.
     *
     * @param title The title of the book.
     */
    public void bookProfileEnable(String title) {
        
        try {
            //Set the book's profile to visible
            new BookProfile(searchBook(title.trim()), s).setVisible(true);
            //Set this screen visible to false
            this.setVisible(false);
        } catch (ArrayIndexOutOfBoundsException ex) {
            //Error when searching for the book
            JOptionPane.showMessageDialog(this, "Error While Searching", "Search Error", JOptionPane.ERROR_MESSAGE);
        }
        catch(NullPointerException ex){

        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 760));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Search:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 30, -1, 23));

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton1.setText("LOGOUT");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 30, 110, -1));

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 30, 210, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Highest Rated Books");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 200, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Your Recommendations");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, -1, 28));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, 780, 10));

        jLabel4.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Book1");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 250, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Book2");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 140, 240, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Book3");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 140, 250, -1));

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton3.setText("View Book");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 350, 160, -1));

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton4.setText("View Book");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 350, 170, -1));

        jButton5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton5.setText("View Book");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 350, 170, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Friend1");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 460, 290, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Friend2");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 460, 290, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 180, 180));

        jLabel10.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 160, 190, 180));

        jLabel11.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 160, 190, 180));
        getContentPane().add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 770, 10));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel12.setText("BOOKSTAR");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, -1, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Recommended1a");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 490, 160, -1));

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton2.setText("View Book");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 690, -1, -1));

        jLabel14.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Recommended2a");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 490, 150, -1));

        jButton6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton6.setText("View Book");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 690, -1, -1));

        jLabel15.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Recommended1b");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 490, 160, -1));

        jButton7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton7.setText("View Book");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 690, -1, -1));

        jLabel16.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Recommended2b");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 490, 160, -1));

        jButton8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton8.setText("View Book");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 690, -1, -1));

        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 510, 130, 170));

        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 510, 130, 170));

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 510, 130, 170));

        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 510, 130, 170));

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bookstar/images/logo.png"))); // NOI18N
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 80, 80));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new MainFrame().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
        //Search using search bar
        bookProfileEnable(jTextField1.getText());
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        //1a
        bookProfileEnable(jLabel13.getText());
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        //Search by the second highest rated books
        try{
            bookProfileEnable(sortedAverage[1][0]);
        }
        catch(ArrayIndexOutOfBoundsException ex){
            
        }
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        //Search by the first highest rated books
        try{
            bookProfileEnable(sortedAverage[0][0]);
        }
        catch(ArrayIndexOutOfBoundsException ex){
            
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        //Search by the third highest rated books
        try{
            bookProfileEnable(sortedAverage[2][0]);
        }
        catch(ArrayIndexOutOfBoundsException ex){
            
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        //1b
        bookProfileEnable(jLabel15.getText());
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        //2a
        bookProfileEnable(jLabel14.getText());
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        //2b
        bookProfileEnable(jLabel16.getText());
    }//GEN-LAST:event_jButton8ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UserPageScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserPageScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserPageScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserPageScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserPageScreen(s).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
