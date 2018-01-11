/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstar;

/**
 *
 * @author 348676487
 */
public class Student {

    private String firstName;
    private String lastName;
    private String studentNumber;
    private String password;

    public Student (){
        
    }
    public Student (String fName, String lName, String sNumber, String pass){
        firstName = fName;
        lastName = lName;
        studentNumber = sNumber;
        password = pass;
    }
    
    @Override
    public String toString() {
        return getFirstName() + "," + getLastName() + "," + getStudentNumber() + "," + getPassword();
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the studentNumber
     */
    public String getStudentNumber() {
        return studentNumber;
    }

    /**
     * @param studentNumber the studentNumber to set
     */
    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
}
