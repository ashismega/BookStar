/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstar;

//Imports for the Current Frame
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

    //students file is initialized and holds all user accounts in the system
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
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jPasswordField1 = new javax.swing.JPasswordField();
        jPasswordField2 = new javax.swing.JPasswordField();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        getContentPane().add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 450, 340, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("REGISTRATION ");
        jLabel1.setToolTipText("");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 370, -1));

        jTextField5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        getContentPane().add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 580, 340, -1));

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 340, 30));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("LAST NAME");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 550, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("PASSWORD");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("FIRST NAME");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("STUDENT NUMBER");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, -1, -1));

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton1.setText("CREATE");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 640, 130, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("LOGIN");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 100, 350, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("STUDENT NUMBER");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 290, -1, -1));

        jTextField4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        getContentPane().add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 320, 410, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setText("PASSWORD");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 410, -1, -1));

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton2.setText("LOGIN");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 500, 130, -1));

        jPasswordField1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        getContentPane().add(jPasswordField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 440, 410, -1));

        jPasswordField2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        getContentPane().add(jPasswordField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, 340, -1));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 820, 10));

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 90, 10, 620));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel9.setText("BOOKSTAR");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, -1, -1));
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, -1, -1));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bookstar/images/logo.png"))); // NOI18N
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 0, 80, 80));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bookstar/images/logo.png"))); // NOI18N
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 80, 80));

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
            if (jTextField4.getText().trim().equals(studentInfo[0]) && encrypt(jPasswordField1.getText().trim()).equals(studentInfo[1])) {
                System.out.println("Hello " + jTextField4.getText().trim() + " Welcome Back");
                //Do I need to create a new Object?
                Student newUser = new Student(jTextField4.getText().trim(), jPasswordField1.getText().trim(), studentInfo[2], studentInfo[3]);
                this.setVisible(false);
                new UserPageScreen(newUser).setVisible(true);
                //Close scanner if not null
                if (s != null) {
                    s.close();
                }
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "INVALID PASSWORD/USERNAME", "Login Error", JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        //Check if any credentials are missing(CHECK), if there are any duplicate accounts(CHECK) and if the passwords contains bad words(CHECK)
        //Initializes delimiter
        String delimiter = "~";
        //Initializes PrintWriter
        PrintWriter pw = null;
        //Checks if a student number only uses numbers, if invalid exit method and ask to use a valid student number
        if (checkValidSNumber(jTextField1.getText().trim()) == false){
            return;
        }
        //Checks if any credentials are missing, if any are missing exit method and ask to fill out all credentials
        if (checkCredentials(jTextField1.getText().trim(), jPasswordField2.getText().trim(), jTextField3.getText().trim(), jTextField5.getText().trim()) == false) {
            return;
        }
        //Checks if a username is taken, if taken exit method and ask to use a different username 
        if (checkDupAccount(jTextField1.getText().trim()) == false) {
            return;
        }
        //Checks if a password is valid, if invalid exit method and ask to use a valid password
        if (checkPass(jPasswordField2.getText().trim()) == false) {
            return;
        }
        //Checks if a student number is valid in length, if invalid exit method and ask to use a valid student number
        if (checkSNumber(jTextField1.getText().trim()) == false) {
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
        Student newUser = new Student(jTextField1.getText().trim(), jPasswordField2.getText().trim(), jTextField3.getText().trim(), jTextField5.getText().trim());
        //Writes to the file the information associated with the newUser
        pw.println(newUser.getStudentNumber() + delimiter + encrypt(newUser.getPassword()) + delimiter + newUser.getFirstName() + delimiter + newUser.getLastName());
        //Closes PrintWriter
        pw.close();
        jTextField1.setText("");
        jPasswordField2.setText("");
        jTextField3.setText("");
        jTextField5.setText("");
        System.out.println("You have Successfully created your Account!");


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed
     /**
     * Checks if all account credentials are filled in. 
     * If any credentials are blank or missing, return false, otherwise return 
     * true. Used to check if any account credentials are properly filled in.
     * @param username The username of the account
     * @param password The password of the account
     * @param Fname The first name of the account
     * @param Lname The last name of the account
     * @return true if account credentials are filled in false if at least one
     * credential is not properly filled in
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
     * Checks if an account username is taken in a User file. Returns true if
     * the username is not taken. If a username is already taken, return false.
     * Method is used to check if any duplicate accounts are in a file.
     *
     * @param username The username for checking if taken or not
     * @return true if password is valid false if password is invalid (contains
     * invalid words)
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
            String[] userInfo = info.split("~");
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
     * Checks if a password is valid according to a file with a list of
     * "invalid" words and checking if the password contains any such invalid
     * words. Returns true if the password does not contains any invalid words
     * or false if the password does contain invalid words. Method is used to
     * check if a password is valid.
     *
     * @param passWord The password for checking if valid or not
     * @return true if password is valid false if password is invalid (contains
     * invalid words)
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
    /**
     * Checks if a Student number long enough in length.
     * @param sNumber The student number to be checked
     * @return true if student number is valid in length
     *          false if student number is not sufficient in length
     */
    public boolean checkSNumber(String sNumber) {
        if (sNumber.length() != 9 && sNumber.length() != 10) {
            JOptionPane.showMessageDialog(this, "INVALID STUDENT NUMBER ID", "Student ID Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    
    /**
     * Checks if a Student number is all numbers and contains no letters.
     * @param sNumber The student number to be checked 
     * @return true if the student number is all numbers
     *          false if the student number contains letters
     */         
    public boolean checkValidSNumber (String sNumber) {
        for(int i = 0; i<sNumber.length();i++){
            if (sNumber.charAt(i)<0 || sNumber.charAt(i)>9){
                JOptionPane.showMessageDialog(this, "INVALID STUDENT NUMBER ID", "Student ID Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
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
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField jPasswordField2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    // End of variables declaration//GEN-END:variables
}
