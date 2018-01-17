/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author 072660210
 */
public class MainFrame extends javax.swing.JFrame {
    //users file is initialized and holds all user accounts in the system
    File students = new File("Students.txt");
    //badWords file is initialized to check for invalid passwords
    File badWords = new File("dictbadpass.txt");
    /**
     * Creates new instance of MainFrame
     */
    public MainFrame() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField3 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jTextField1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jPasswordField1 = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("    GAME REGISTRATION ");
        jLabel1.setToolTipText("");

        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel5.setText("LAST NAME");

        jLabel3.setText("PASSWORD");

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jLabel4.setText("FIRST NAME");

        jLabel2.setText("STUDENT NUMBER");

        jButton1.setText("CREATE");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("LOGIN");

        jLabel7.setText("STUDENT NUMBER");

        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });

        jLabel8.setText("PASSWORD");

        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });

        jButton2.setText("LOGIN");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPasswordField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(jLabel5))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGap(33, 33, 33)
                                    .addComponent(jLabel3))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGap(31, 31, 31)
                                    .addComponent(jLabel4))
                                .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextField5, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(jLabel2)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(92, 92, 92))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(64, 64, 64))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(69, 69, 69)
                                .addComponent(jLabel6))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jTextField4)
                                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap(48, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(jButton1)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        //Initializes scanner to scan users file
        Scanner s = null;
        try {
            s = new Scanner(students);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "INPUT/OUTPUT EXCEPTION", "Input/Output Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        //Read through the file and check if the login credentials are the same as a user in the "users" file, if there is 
        //a match there will be a login message and a new instance of user is created
        while (s.hasNextLine()) {
            String info = s.nextLine();
            String[] studentInfo = info.split("~");
            if (jTextField4.getText().equals(studentInfo[0]) && encrypt(jPasswordField1.getText()).equals(studentInfo[1])) {
                System.out.println("Hello " + jTextField4.getText() + " Welcome Back");
                //Do I need to create a new Object?
                Student newUser = new Student(jTextField4.getText(), jPasswordField1.getText(), studentInfo[2], studentInfo[3]);
                        this.setVisible(false);
                        new UserPageScreen(newUser).setVisible(true);
                break;
            }
        }
        JOptionPane.showMessageDialog(this, "INVALID PASSWORD/USERNAME", "Login Error", JOptionPane.ERROR_MESSAGE);
        //Close scanner if not null
        if (s != null) {
            s.close();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        //Check if any credentials are missing(CHECK), if there are any duplicate accounts(CHECK) and if the passwords contains bad words(CHECK)
        //Initializes delimiter
        String delimiter = "~";
        //Initializes PrintWriter
        PrintWriter pw = null;
        //Checks if any credentials are missing, if any are missing exit method and ask to fill out all credentials
        if (checkCredentials(jTextField1.getText(), jTextField2.getText(), jTextField3.getText(), jTextField5.getText()) == false) {
            return;
        }
        //Checks if a username is taken, if taken exit method and ask to use a different username 
        if (checkDupAccount(jTextField1.getText()) == false) {
            return;
        }
        //Checks is a password is valid, if invalid exit method and ask to use a valid password
        if (checkPass(jTextField2.getText()) == false) {
            return;
        }
        if (checkSNumber(jTextField1.getText()) == false){
            return;
        }
        //Try to create a PrintWriter
        try {
            pw = new PrintWriter(new FileWriter(students, true));
        } catch (FileNotFoundException ex) {
            //Exits method if users file is not found
            JOptionPane.showMessageDialog(this, "FILE NOT FOUND", "Missing File Error", JOptionPane.ERROR_MESSAGE);
            return;
        } catch (IOException ex) {
            //Exits method is there is an input or output exception
            JOptionPane.showMessageDialog(this, "INPUT/OUTPUT EXCEPTION", "Input/Output Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        //Create a new instance of the user class
        Student newUser = new Student(jTextField1.getText(), jTextField2.getText(), jTextField3.getText(), jTextField5.getText());
        //Writes to the file the information associated with the newUser
        pw.println(newUser.getStudentNumber() + delimiter + encrypt(newUser.getPassword()) + delimiter + newUser.getFirstName() + delimiter + newUser.getLastName());
        //Closes PrintWriter
        pw.close();
        System.out.println("You have Successfully created your Account!");
        

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
        // String passWord = jTextField2.getText();
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
        //String userName = jTextField1.getText();
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
        // String lastName = jTextField5.getText();
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
        // String firstName = jTextField3.getText();
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jPasswordField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordField1ActionPerformed
     /**
     * Checks if all account credentials are filled in. 
     * If any credentials are blank or missing, return false, otherwise return 
     * true. Used to check if any account credentials are properly filled in.
     * @param username The username of the account
     * @param password The password of the account
     * @param Fname The first name of the account
     * @param Lname The last name of the account
     * @return         true if account credentials are filled in
                   false if at least one credential is not properly filled in
     */
    public boolean checkCredentials(String username, String password, String Fname, String Lname) {
        //If credentials are missing, return false, if all are filled out then return true
        if (username.equals("") || password.equals("") || Fname.equals("") || Lname.equals("")) {
            JOptionPane.showMessageDialog(this, "PLEASE FILL IN ALL CREDENTIALS", "Missing Credentials Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    /**
     * Checks if an account username is taken in a User file. Returns true if the 
     * username is not taken. If a username is already taken, return false. 
     * Method is used to check if any duplicate accounts are in a file.
     * @param username The username for checking if taken or not
     * @return         true if password is valid 
                   false if password is invalid (contains invalid words)
     */
    public boolean checkDupAccount(String username) {
        //Initializes a scanner to scan users, if file is missing return false and a message
        Scanner s = null;
        try {
            s = new Scanner(students);
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "FILE NOT FOUND", "Missing File Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        //Read through the file and check if the username created by the user matches with any from the file, if there is a match return false
        while (s.hasNextLine()) {
            String info = s.nextLine();
            String[] userInfo = info.split(",");
            if (username.equals(userInfo[0])) {
                JOptionPane.showMessageDialog(this, "TAKEN USERNAME", "Taken Username Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        //Closes Scanner
        s.close();
        //Return true if the username is not taken
        return true;
    }

    /**
     * Checks if a password is valid according to a file with a list of "invalid" words and
     * checking if the password contains any such invalid words. Returns true
     * if the password does not contains any invalid words or false if the
     * password does contain invalid words. Method is used to check if a
     * password is valid.
     * @param passWord The password for checking if valid or not
     * @return         true if password is valid 
                false if password is invalid (contains invalid words)
     */
    public boolean checkPass(String passWord) {
        //Initializes a scanner to scan badWords, if file is missing return false and a message
        Scanner s = null;
        try {
            s = new Scanner(badWords);
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "FILE NOT FOUND", "Missing File Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        //Read through the file and check if the password created by the user matches with any from the file, if there is a match return false
        while (s.hasNextLine()) {
            String bWords = s.nextLine();
            String[] userInfo = bWords.split("/n");
            for (int i = 0; i < userInfo.length; i++) {
                if (userInfo[i].equals(passWord)) {
                    JOptionPane.showMessageDialog(this, "BAD PASSWORD, CHOOSE ANOTHER!", "Invalid Password Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
        }
        //Closes Scanner
        s.close();
        //Return true if the created password is valid
        return true;
    }
    
    public boolean checkSNumber(String sNumber) {
        if (sNumber.length()!= 9 && sNumber.length()!= 10){
            JOptionPane.showMessageDialog(this, "INVALID STUDENT NUMBER ID", "Student ID Error", JOptionPane.ERROR_MESSAGE);
        return false;
        }
        return true;
    }
    
    /**
     * Encrypts a password(String) using the SHA-256 algorithm (one way
     * encryption). Returns the newly encrypted password. Used for password
     * encryption to store in files.
     *
     * @param passWord Password to be encrypted
     * @return The newly encrypted password
     */
    public String encrypt(String passWord) {
        //Initializes MessageDigest, if there is no such algorithm, return nothing
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException ex) {
            JOptionPane.showMessageDialog(this, "NO ALGORITHM DETECTED", "Missing Algorithm Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        md.update(passWord.getBytes());
        byte byteData[] = md.digest();
        String sb1 = "";
        for (int i = 0; i < byteData.length; ++i) {
            sb1 += (Integer.toHexString((byteData[i] & 0xFF) | 0x100).substring(1, 3));
        }
        return sb1;
    }

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
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
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
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    // End of variables declaration//GEN-END:variables
}
