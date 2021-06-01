// Yonwoo Choi, yc4ny CS 2110 HW4

import java.text.SimpleDateFormat;
import java.util.ArrayList;

abstract class PhotographContainer {
   
    protected String name;
    protected ArrayList<Photograph> photos;

    //Accessor Method
    public String getName() {
        return name;
    }

    //Mutator Method
    public void setName(String name) {
        this.name = name;
    }

    
     //An accessor method, returns the reference to the ArrayList of photos
     //return The photos field, created earlier in the set
     
    public ArrayList<Photograph> getPhotos() {
        return photos;
    }

    
     //Add Photograph p to photos only if it does not already exist in the list
     //return True if object has p in its list of photos, false otherwise
     
    public boolean addPhoto(Photograph p) {
        if (!photos.contains(p)) {
            photos.add(p);
            return true;
        }
        return false;
    }

    
     //Checks if the list of photos p is in the current object
     //return True if current object has p in the list of photos, false otherwise
     
    public boolean hasPhoto(Photograph p) {
        if (photos.contains(p)) {
            return true;
        } else {
            return false;
        }
    }

  
     //Remove Photograph p from the album
  
    public boolean removePhoto(Photograph p) {
        if (!photos.contains(p)) {
            return false;
        } else {
            photos.remove(p);
            return true;
        }
    }

    
     //Calculates the number of Photographs in the current album

    public int numPhotographs() {
        return photos.size();
    }

   
     //Compares the current Album object's name value and that of the Album object passed to this method
    
    public boolean equals(Object o) {
        if (o instanceof Album) {
            Album other = (Album) o;
            return name == other.name;
        } else {
            return false;
        }
    }

    //To String method
    public String toString() {
        return "Name: " + name + "Photos: " + photos.toString(); // TODO Make the toString print every photo's name in the photos
                                                                 // hashset
    }
    
    public int hashCode() {
        return name.hashCode();
    }
    
     //Constructor for PhotographContainer that matches that of the Album class

    public PhotographContainer(String name) {
        this.name = name;
        this.photos = new ArrayList<Photograph>();
    }

    
     /*Method that returns arraylist of photos from the photos list that has a rating greater than or equal to given
      parameter */

    public ArrayList<Photograph> getPhotos(int rating) {
        ArrayList<Photograph> p = new ArrayList<Photograph>();

        for (Photograph p1 : photos) {
            if (p1.getRating() < 0 || p1.getRating() > 5) {
                return null;
            }
            if (p1.getRating() >= rating) {
                p.add(p1);
            }
        }
        return p;
    }
    
   //Takes in the photos list that were taken in the given year, returns ArrayList<Photograph>

    public ArrayList<Photograph> getPhotosInYear(int year) {
        ArrayList<Photograph> p = new ArrayList<Photograph>();
        for (Photograph p1 : photos) {
            if (p1.getDateTaken().matches("^\\d{4} - \\d{2} - \\d{2}$")) {
                int y1 = Integer.parseInt(p1.getDateTaken().substring(0, 3));
                if (y1 == year) {
                    p.add(p1);
                }
            } else {
                return null;
            }
        }
        return p;
    }

   
     //Takes the photos list that were taken in the month and year provided

    public ArrayList<Photograph> getPhotosInMonth(int month, int year) {
        ArrayList<Photograph> p = new ArrayList<Photograph>();
        for (Photograph p1 : photos) {
            if (p1.getDateTaken().matches("^\\d{4}-\\d{2}-\\d{2}$")) {
                int y1 = Integer.parseInt(p1.getDateTaken().substring(0, 4));
                int m1 = Integer.parseInt(p1.getDateTaken().substring(5, 7));
                System.out.println(y1);
                System.out.println(m1);
                if (y1 == year && m1 == month) {
                    p.add(p1);
                }
            } else {
                return null;
            }
        }
        return p;
    }

   
     //Takes a photos list that were taken between beginDate and endDate(inclusive)

    public ArrayList<Photograph> getPhotosBetween(String beginDate, String endDate) {
        ArrayList<Photograph> pB = new ArrayList<Photograph>();
        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
        for (Photograph p : this.photos) {
            try {
                if ((d.parse(p.getDateTaken()).before(d.parse(endDate)))
                        && (d.parse(beginDate)).before(d.parse(p.getDateTaken()))) {
                    pB.add(p);
                }
            } catch (Exception e) {
                return null;
            }
        }
        return pB;
    }
}
