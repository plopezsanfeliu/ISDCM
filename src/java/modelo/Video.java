/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Pau
 */
public class Video {
    
    private final String title;
    private final String author;
    private final String date;
    private final String duration;
    private final String description;
    private final String format;
    private final String path;
    private final DB db;
    
    public Video(String title, String author, String date, String duration,
            String description, String format, String path) {
        this.title = title;
        this.author = author;
        this.date = date;
        this.duration = duration;
        this.description = description;
        this.format = format;
        this.path = path;
        this.db = new DB();
    }
    
    private boolean validateVideo() {
        boolean valid = false;
        
        if (this.title.length() > 2 && this.title.length() < 101
                && this.author.length() > 1 && this.author.length() < 101
                && this.date.length() == 10
                && this.duration.length() == 5
                && this.description.length() > 24 && this.description.length() < 256
                && this.format.length() > 1 && this.format.length() < 6
                && this.path.length() > 4 && this.path.length() < 301) {
            valid = true;
        }
        
        return valid;
    }
    
    public int createDBVideo() {
        int errCode = 0;
        
        if(this.validateVideo()) {
            db.createDBVideo(this.title, this.author, this.date, this.duration, 
                    this.description, this.format, this.path);
        }
        else {
            errCode = 1;
        }
        
        return errCode;
    }
    
}
