// Yonwoo Choi, yc4ny CS 2110 HW4

import java.util.Comparator;

 //Implements the Comparator interface and compares two photos by rating.
 
public class CompareByRating implements Comparator<Photograph> {
    public int compare(Photograph a, Photograph b) {
        if (a.getRating() > b.getRating())
            return -1;
        else if (a.getRating() < b.getRating()) {
            return 1;
        }
        if (!a.getCaption().equals(b.getCaption())) {
            if (a.getCaption().compareTo(b.getCaption()) < 0) {
                return -1;
            } else if (a.getCaption().compareTo(b.getCaption()) > 0) {
                return 1;
            }
        }
        return 0;
    }
}
