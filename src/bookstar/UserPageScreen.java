
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        this.s = s;
        initComponents();
            String string = "";
            String[] ee = friends(searchUserRatings());
            for (int i = 0; i<ee.length; i++){
                string += ee[i] + "\n";

            }
           System.out.println(string);
            
        
        
        try{
        jLabel4.setText(sortedAverage[0][0]);
        jLabel5.setText(sortedAverage[1][0]);
        jLabel6.setText(sortedAverage[2][0]);
        }catch(ArrayIndexOutOfBoundsException ex){}
    }

    /**
     * Read and file and create an array where each line in the file is an
     * element in the array.
     *
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
            //Scanner for reading the file
            sc = new Scanner(ratingReview);
            //Add each line to the arraylist
            while (sc.hasNextLine()) {
                String info = sc.nextLine();
                String[] reviewInfo = info.split("~");
                temp.get(0).add(reviewInfo[0]);
                temp.get(1).add(reviewInfo[3]);
            }
            sc.close();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "INPUT/OUTPUT EXCEPTION", "Input/Output Error", JOptionPane.ERROR_MESSAGE);
        }
        return temp;
    }

    public String[][] arraylistToArray(ArrayList<ArrayList<String>> temp) {
        /////CHANGE THE NAME OF THE LETTER A
        String[][] a = new String[temp.get(1).size()][2];

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < temp.get(1).size(); j++) {
                a[j][i] = temp.get(i).get(j);
            }
        }
        return a;
    }

    public ArrayList<ArrayList<String>> averageData(ArrayList<ArrayList<String>> allData) {

        ArrayList<ArrayList<String>> unsortedAveraged = new ArrayList();

        //Name of Book
        unsortedAveraged.add(new ArrayList<String>());
        //Average Rating of Book
        unsortedAveraged.add(new ArrayList<String>());

        for (int i = 0; i < allData.get(0).size(); i++) {
            if (!(unsortedAveraged.get(0).contains(allData.get(0).get(i)))) {
                //Add title
                unsortedAveraged.get(0).add(allData.get(0).get(i));
                //add the rating
                unsortedAveraged.get(1).add(Double.toString(averageBookRating(allData, allData.get(0).get(i))));
            }
        }

        return unsortedAveraged;
    }

    /**
     * Given the file data and the title of a book, determine the average rating
     * of that book.
     *
     * @param unsortedAveraged
     * @param title
     * @return
     */
    public double averageBookRating(ArrayList<ArrayList<String>> unsortedAveraged, String title) {
        double numRating = 0;
        double totalRating = 0;
        for (int i = 0; i < unsortedAveraged.get(0).size(); i++) {
            if (unsortedAveraged.get(0).get(i).equals(title)) {
                numRating++;
                totalRating += Integer.parseInt(unsortedAveraged.get(1).get(i));
            }
        }

        return totalRating / numRating;
    }

    public String[][] sortbyRating(String[][] unsorted) {
        Arrays.sort(unsorted, new Comparator<String[]>() {
            @Override
            public int compare(final String[] first, final String[] second) {
                return Double.valueOf(second[1]).compareTo(Double.valueOf(first[1]));
            }
        });
        String[][] sorted = Arrays.copyOf(unsorted, unsorted.length);
        return sorted;
    }

    public String[] searchBook(String title) {
        try {
            OnlineBookInfo o = new OnlineBookInfo();
            o.search = title;
            return o.bookInformation(o.bookHTML(o.pageHTML(o.createLink())));
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(this, "Error", "Search Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public String[] searchUserRatings() {
        Scanner scan = null;
        ArrayList userRatings = new ArrayList();
        try {
            scan = new Scanner(ratingReview);
            while (scan.hasNext()) {
                String line = scan.nextLine();
                String[] lineArr = line.split("~");
               
               
                if (lineArr[2].equals(s.getStudentNumber())) {
                    String bookRate = (lineArr[0] + "~" + lineArr[3]);
                    userRatings.add(bookRate);
                }
               
            }
        } catch (FileNotFoundException ex) {
        }
        if (scan != null) {
            scan.close();
        }
        
        Object[] array =  userRatings.toArray();
        String[] a = new String[array.length];
        
        for(int i = 0; i<array.length;i++){
            if(array[i] instanceof String){
               a[i] = (String)array[i];
                
            }
            
        }
        
        
        return a;
    }
    
    public String[] friends(String[] reviews){
        if(reviews == null){
          
            return null;
        }
        Map<String, String[]> map = new HashMap<>();
        
        for(int i=0;i<reviews.length;i++){
           
            
            String[] record = reviews[i].split("~");
            
            String book = record[0];
            int review = Integer.parseInt(record[1]);
            
            try {
                Scanner sc = new Scanner(ratingReview);
                
                while(sc.hasNext()){
                   
                    String tempS = sc.nextLine();
                    String[] tempRecord = tempS.split("~");
                  
                    if(tempRecord[0].equals(book)){
                        if( !(s.getStudentNumber().equals(tempRecord[2])) ){
                           
                            try{
                              
                                String[] vals = map.get(tempRecord[2]);
                                vals[0] = ( (review*Integer.parseInt(tempRecord[3])) + Integer.parseInt(vals[0]) ) + "";
                                vals[1] = vals[1] + "," + book;
                                
                            }
                            catch(NullPointerException ex){
                                
                                String[] vals = {review*Integer.parseInt(tempRecord[3]) + "",  "~" +book };
                                map.put(tempRecord[2], vals);
                                
                            }
                        }
                    }
                }
            } catch (IOException ex) {
                
                JOptionPane.showMessageDialog(this, "Error While Searching", "Search Error", JOptionPane.ERROR_MESSAGE);
            }  
        }
            
        String[] key = map.keySet().toArray(new String[0]);
        
        String[][] value = map.values().toArray(new String[0][0]);
        String[] result = new String[map.size()];
        for (int i = 0; i<map.size(); i++){
            result[i] = key[i];
            for (int j = 0; j<map.size(); j++){
                    result[i] += "~" + value[i][j];
                    
            }      
        }
  
        return result;
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
        jButton2 = new javax.swing.JButton();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(600, 500));
        setResizable(false);

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

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton2.setText("View Book");
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 690, -1, -1));

        jLabel14.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Recommended2a");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 490, 150, -1));

        jButton6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton6.setText("View Book");
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 690, -1, -1));

        jLabel15.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Recommended1b");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 490, 160, -1));

        jButton7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton7.setText("View Book");
        getContentPane().add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 690, -1, -1));

        jLabel16.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Recommended2b");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 490, 160, -1));

        jButton8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton8.setText("View Book");
        getContentPane().add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 690, -1, -1));
        getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 510, 130, 170));
        getContentPane().add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 510, 130, 170));
        getContentPane().add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 510, 130, 170));
        getContentPane().add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 510, 130, 170));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new MainFrame().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
        //For the Barcode

        new BookProfile(searchBook(jTextField1.getText()), s).setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        try {
            new BookProfile(searchBook(sortedAverage[1][0]), s).setVisible(true);
            this.setVisible(false);
        } catch (ArrayIndexOutOfBoundsException ex) {
            JOptionPane.showMessageDialog(this, "Error While Searching", "Search Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        try {
            new BookProfile(searchBook(sortedAverage[0][0]), s).setVisible(true);
            this.setVisible(false);
        } catch (ArrayIndexOutOfBoundsException ex) {
            JOptionPane.showMessageDialog(this, "Error While Searching", "Search Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        try {
            new BookProfile(searchBook(sortedAverage[2][0]), s).setVisible(true);
            this.setVisible(false);
        } catch (ArrayIndexOutOfBoundsException ex) {
            JOptionPane.showMessageDialog(this, "Error While Searching", "Search Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

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
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
