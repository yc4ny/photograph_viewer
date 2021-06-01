// Yonwoo Choi, yc4ny CS 2110 HW4

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Photograph implements Comparable<Photograph> {
    private String caption;
    private String filename;
    private String dateTaken;
    private int rating;
    protected BufferedImage imageData;

    
    // Constructor that takes a caption and filename
    public Photograph(String caption, String filename) {
        this.caption = caption;
        this.filename = filename;
        dateTaken = "1901-01-01";
        rating = 0;
    }

    // Constructor that takes a caption, filename, dateTaken, and rating
    public Photograph(String caption, String filename, String dateTaken, int rating) {
        if (dateTaken.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
            this.dateTaken = dateTaken;
        } else {
            this.dateTaken = "1901-01-01";
        }

        if (rating >= 0 && rating <= 5) {
            this.rating = rating;
        } else {
            this.rating = 0;
        }
        this.caption = caption;
        this.filename = filename;
    }

//Accessor & Mutator Methods 
    
    public String getCaption() {
        return caption;
    }

    public String getFilename() {
        return filename;
    }

    public String getDateTaken() {
        return dateTaken;
    }

    public int getRating() {
        return rating;
    }

    public void setDateTaken(String dateTaken) {
        dateTaken = this.dateTaken;
    }

    public void setRating(int rating) {
        rating = this.rating;
    }

    
     //This compares the caption and the filename of the two given Photographs and returns true if they have the exact same
     // return True if they have the exact same caption as well as filename, false if otherwise
  
    public boolean equals(Object o) {
        if (o instanceof Photograph) {
            Photograph other = (Photograph) o;
            return getCaption() == other.getCaption() && getFilename() == other.getFilename();
        } else {
            return false;
        }
    }

    //This generates the values of the fields caption and filename, in a string type
    //return String that shows the caption as well as filename
    
    public String toString() {
        return "caption: " + caption + "; File Name: " + filename;
    }

    //Provides the string that shows the values of the field
    @Override
    public int hashCode() {
        return this.filename.hashCode();
    }

    
    //Compares the date when the current Photograph object was taken with p. Returns negative if p's is earlier.
    
    public int compareTo(Photograph p) {
        return this.dateTaken.compareTo(p.dateTaken);
    }
    
    public boolean loadImageData(String filename) {
        try {
            imageData = ImageIO.read(new File(filename));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
