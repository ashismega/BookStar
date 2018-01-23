
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
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        this.s = s;
        initComponents();

        topRatedBooks(jLabel4, jLabel9, jButton3, 0);
        topRatedBooks(jLabel5, jLabel10, jButton4, 1);
        topRatedBooks(jLabel6, jLabel11, jButton5, 2);
    }

    public void topRatedBooks(JLabel title, JLabel image, JButton button, int num) {
        try {
            title.setText(sortedAverage[num][0]);
            image.setIcon(new ImageIcon(addImage(searchBook(sortedAverage[num][0])[10])));
            button.setVisible(true);
        } catch (ArrayIndexOutOfBoundsException ex) {
            title.setText("NO RATED BOOKS");
            image.setText("NO RATED BOOKS");
            button.setEnabled(false);
        }
    }

    public Image addImage(String imageLink) {
        URL url;
        try {
            url = new URL(imageLink);
            return ImageIO.read(url).getScaledInstance(150, 150, Image.SCALE_DEFAULT);
        } catch (MalformedURLException ex) {
            Logger.getLogger(BookProfile.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (IOException ex) {
            Logger.getLogger(BookProfile.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
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
        String[] array = (String[]) userRatings.toArray();
        return array;
    }

    public String[] topFriends(String[] reviews) {
        Map<String, String[]> map = new HashMap<>();
        for (int i = 0; i != reviews.length - 1; i++) {
            String[] record = reviews[i].split("~");
            String book = record[0];
            int review = Integer.parseInt(record[1]);

            try {
                Scanner sc = new Scanner(ratingReview);
                while (sc.hasNext()) {
                    String tempS = sc.nextLine();
                    String[] tempRecord = tempS.split("~");
                    if (tempRecord[0].equals(book)) {
                        if (!(s.getStudentNumber().equals(tempRecord[3]))) {
                            try {
                                String[] vals = map.get(tempRecord[3]);
                                vals[0] = ((review * Integer.parseInt(tempRecord[3])) + Integer.parseInt(vals[0])) + "";
                                vals[1] = vals[1] + "~" + book;
                            } catch (NullPointerException ex) {
                                String[] vals = {review * Integer.parseInt(tempRecord[3]) + "", book};
                                map.put(tempRecord[3], vals);
                            }
                        }
                    }
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error While Searching", "Search Error", JOptionPane.ERROR_MESSAGE);
            }

        }
        System.out.println(map.values());
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 650));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Search");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, 23));

        jButton1.setText("LOGOUT");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(703, 10, 90, -1));

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, 100, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Highest Rated Books");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 52, 165, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Your Recommendations");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, -1, 28));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 780, 10));

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Book1");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 250, -1));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Book2");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 90, 240, -1));

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Book3");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 90, 250, -1));

        jButton3.setText("View Book");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 300, 160, -1));

        jButton4.setText("View Book");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 300, 170, -1));

        jButton5.setText("View Book");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 300, 170, -1));

        jLabel7.setText("Friend1");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 440, -1, -1));

        jLabel8.setText("Friend2");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 560, -1, -1));

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 160, 180));

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 110, 170, 180));

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 110, 170, 180));

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

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        try {
            new BookProfile(searchBook(sortedAverage[1][0]), s).setVisible(true);
            this.setVisible(false);
        } catch (ArrayIndexOutOfBoundsException ex) {
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        try {
            new BookProfile(searchBook(sortedAverage[0][0]), s).setVisible(true);
            this.setVisible(false);
        } catch (ArrayIndexOutOfBoundsException ex) {
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        try {
            new BookProfile(searchBook(sortedAverage[3][0]), s).setVisible(true);
            this.setVisible(false);
        } catch (ArrayIndexOutOfBoundsException ex) {
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
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
