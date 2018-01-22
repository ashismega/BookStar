/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstar;

/**
 *
 * @author 072660210
 */
public class Book {
    
    private String Name;
    private String Rating;
    
    public Book (){
        
    }
    
    public Book(String Name, String Rating){
        this.Name = Name;
        this.Rating = Rating;
    }

    /**
     * @return the Name
     */
    public String getName() {
        return Name;
    }

    /**
     * @param Name the Name to set
     */
    public void setName(String Name) {
        this.Name = Name;
    }

    /**
     * @return the Rating
     */
    public String getRating() {
        return Rating;
    }

    /**
     * @param Rating the Rating to set
     */
    public void setRating(String Rating) {
        this.Rating = Rating;
    }
    
    
}
