
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstar;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author 072660210
 */
public class UserPageScreen extends javax.swing.JFrame {

    private static Student s;

    /**
     * Creates new form UserPageScreen
     *
     * @param s Student logged in
     */
    public UserPageScreen(Student s) {
        this.s = s;
        initComponents();

        //All books and the ratings 
        ArrayList<ArrayList<String>> full = fileToArrayList("rateReview.txt");
        System.out.println(Arrays.deepToString(arraylistToArray(full)));

        ArrayList<ArrayList<String>> unsortedAverage = averageData(full);
        System.out.println(Arrays.deepToString(arraylistToArray(unsortedAverage)));

        String sortedAverage[][] = sortbyRating(arraylistToArray(unsortedAverage));
        System.out.println(Arrays.deepToString(sortedAverage));
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 170, Short.MAX_VALUE)
                                .addComponent(jButton1))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton2))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addGap(84, 84, 84)
                        .addComponent(jLabel6)
                        .addGap(95, 95, 95))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addGap(51, 51, 51)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
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
