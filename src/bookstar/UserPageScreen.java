
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

        jLabel4.setText(sortedAverage[0][0]);
        jLabel5.setText(sortedAverage[1][0]);
        jLabel6.setText(sortedAverage[2][0]);

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

    public void searchBook(String title) {
        try {
            OnlineBookInfo o = new OnlineBookInfo();
            o.search = title;
            new BookProfile(o.bookInformation(o.bookHTML(o.pageHTML(o.createLink()))), s).setVisible(true);
            this.setVisible(false);
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(this, "Error", "Search Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public String[] searchUserRatings(){
        Scanner scan = null;
        ArrayList userRatings = new ArrayList();
        try {
            scan = new Scanner(ratingReview);
            while (scan.hasNext()) {
                String line = scan.nextLine();
                String[] lineArr = line.split("~");
                if(lineArr[2].equals(s.getStudentNumber())){
                    String bookRate = (lineArr[0]+"~"+lineArr[3]);
                    userRatings.add(bookRate);
                }
            }
        } catch (FileNotFoundException ex) {
        }
        if (scan != null) {
            scan.close();
        }
        String[] array = (String[])userRatings.toArray();
        return array;
    }
    
    public String[] topFriends(String[] reviews){
        Map<String, String[]> map = new HashMap<>();
        for(int i=0;i!=reviews.length-1;i++){
            String[] record = reviews[i].split("~");
            String book = record[0];
            int review = Integer.parseInt(record[1]);
        
            try {
                Scanner sc = new Scanner(ratingReview);
                while(sc.hasNext()){
                    String tempS = sc.nextLine();
                    String[] tempRecord = tempS.split("~");
                    if(tempRecord[0].equals(book)){
                        if( !(s.getStudentNumber().equals(tempRecord[3])) ){
                            try{
                                String[] vals = map.get(tempRecord[3]);
                                vals[0] = ( (review*Integer.parseInt(tempRecord[3])) + Integer.parseInt(vals[0]) ) + "";
                                vals[1] = vals[1] + "~" + book;
                            }
                            catch(NullPointerException ex){
                                String[] vals = {review*Integer.parseInt(tempRecord[3]) + "", book };
                                map.put(tempRecord[3], vals);
                            }
                        }
                    }
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error While Searching", "Search Error", JOptionPane.ERROR_MESSAGE);
            }
                
           
            
        }
        
        return null;
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(600, 500));
        setResizable(false);

        jLabel1.setText("Search");

        jButton1.setText("LOGOUT");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Highest Rated Books");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Your Recommendations");

        jButton2.setText("RECOMMEND FRIEND");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel4.setText("Book1");

        jLabel5.setText("Book2");

        jLabel6.setText("Book3");

        jButton3.setText("View Book");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("View Book");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("View Book");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jSeparator1)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 370, Short.MAX_VALUE)
                                .addComponent(jButton1))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton2)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(161, 161, 161)
                .addComponent(jLabel6)
                .addGap(105, 105, 105))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jButton3)
                .addGap(122, 122, 122)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton5)
                .addGap(75, 75, 75))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4))
                .addGap(138, 138, 138)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 164, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addContainerGap())
        );

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

        // CHANGE LATER
        try {
            OnlineBookInfo o = new OnlineBookInfo();
            o.search = jTextField1.getText();
            new BookProfile(o.bookInformation(o.bookHTML(o.pageHTML(o.createLink()))), s).setVisible(true);
            this.setVisible(false);
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(this, "NOT A BARCODE", "Barcode error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        searchBook(sortedAverage[1][0]);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        searchBook(sortedAverage[0][0]);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        searchBook(sortedAverage[3][0]);
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
