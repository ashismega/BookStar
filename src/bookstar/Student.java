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

    String firstName;
    String lastName;
    int studentNumber;
    String password;
    String [][] ratings;

    @Override
    public String toString() {
        return firstName + "," + lastName + "," + studentNumber + "," + password;
    }
    
    public Student (){
        
    }
    public Student (String fName, String lName, int sNumber, String pass){
        firstName = fName;
        lastName = lName;
        studentNumber = sNumber;
        password = pass;
    }
    

}
