// Yonwoo Choi
 
import java.util.ArrayList;
import java.util.HashSet;

public class PhotoLibrary extends PhotographContainer {

    private int id;
    private HashSet<Album> albums;


    public PhotoLibrary(String name, int id) {
        super(name);
        this.id = id;
    }

    // Accessor, Mutator Methods
    
    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public HashSet<Album> getAlbums() {
        return albums;
    }

    public boolean createAlbum(String albumName) {
        for (Album a : this.albums) {
            if (a.getName().equals(albumName)) {
                return false;
            }
        }
        Album newAlb = new Album(albumName);
        albums.add(newAlb);
        return true;
    }

    public boolean removeAlbum(String albumName) {
        Album i = null;
        for (Album a : this.albums) {
            if (a.getName().equals(albumName)) {
                i = a;
            }
        }
        if (i == null) {
            return false;
        }
        albums.remove(i);
        return true;
    }
    
    // Method to add a photograph object to an album	
    
    public boolean addPhotoToAlbum(Photograph p, String albumName) {
        Album i = null;
        for (Album a : this.albums) {
            if (a.getName().equals(albumName)) {
                i = a;
            }
        }
        if (i == null) {
            return false;
        }
        if (this.photos.contains(p)) {
            return i.addPhoto(p);
        }
        return false;
    }
    
    // Boolean method to remove Photograph object p from the album
    
    public boolean removePhotoFromAlbum(Photograph p, String albumName) {
        Album i = null;
        for (Album a : this.albums) {
            if (a.getName().equals(albumName)) {
                i = a;
            }
        }
        if (i == null) {
            return false;
        }
        return i.removePhoto(p);
    }

    public Album getAlbumByName(String albumName) {
        Album i = null;
        for (Album a : this.albums) {
            if (a.getName().equals(albumName)) {
                i = a;
            }
        }
        return i;
    }

    
    //Delete p in the object's list of Photographs if p is already in the current PhotoLibrary object's list

    public boolean erasePhoto(Photograph p) {
        if (!photos.contains(p)) {
            return false;
        }
        if (photos.contains(p)) {
            photos.remove(p);

        }
        for (Album a : albums) {
            a.removePhoto(p);

        }
        return true;
    }

    
     //Returns true if object's id value is equal to the actual id value of the PhotoLibrary object passed to the method

    public boolean equals(Object o) {
        if (o instanceof PhotoLibrary) {
            PhotoLibrary other = (PhotoLibrary) o;
            return id == other.id;
        } else {
            return false;
        }
    }

    
    //toString Method
    
    public String toString() {
        int count = 1;
        String libName = "Name of Library: " + name;
        libName += "\nLibrary ID: ";
        libName += id;
        libName += "\nPhotos: ";
        for (Photograph p : photos) {
            libName += "\nPhoto ";
            libName += count;
            libName += "\n" + p;
            count++;
        }
        String albName = "Name of Albums: \n";
        for (Album a : albums) {
            albName += a.getName() + "\n";
        }
        return libName + " " + albName;
    }

    
    //Determines the ArrayList<Photograph> of the photos that both a and b have posted to their feeds.

    public static ArrayList<Photograph> commonPhotos(PhotoLibrary a, PhotoLibrary b) {
        ArrayList<Photograph> photosA = a.getPhotos();
        ArrayList<Photograph> photosB = b.getPhotos();
        ArrayList<Photograph> commonPhotos = new ArrayList<Photograph>();
        for (Photograph photo1 : photosA) {
            for (Photograph photo2 : photosB) {
                if (photo1.equals(photo2)) {
                    commonPhotos.add(photo1);
                }
            }
        }
        return commonPhotos;
    }

    
    //Measures how similar of photo feeds both a and b have posted in their feeds.
    public static double similarity(PhotoLibrary a, PhotoLibrary b) {
        ArrayList<Photograph> commonPhotos = commonPhotos(a, b);
        ArrayList<Photograph> photosA = a.getPhotos();
        ArrayList<Photograph> photosB = b.getPhotos();
        double x = 0;
        if (photosA.size() > photosB.size()) {
            x = photosB.size();
        } else {
            x = photosA.size();
        }
        if (x == 0) {
            return 0.0;
        }
        return commonPhotos.size() / x;
    }
}
