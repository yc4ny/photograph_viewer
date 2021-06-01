// Yonwoo Choi, yc4ny CS 2110 HW4

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class PhotoViewer extends JFrame {
    private PhotographContainer imageLibrary;
    private FlowLayout layout = new FlowLayout();
    
    // a private static PhotoViewer named "myViewer"
    static private PhotoViewer myViewer;
    
    // JButtons, next and previous
    private JButton next;
    private JButton prev;

    // JLabel for the radioButtons
    private JLabel changeRatingButtonLabel;
    
    // Button Models
    private ButtonModel buttonMod1;
    private ButtonModel buttonMod2;
    private ButtonModel buttonMod3;
    private ButtonModel buttonMod4;
    private ButtonModel buttonMod5;
    
    // ButtonGroup to group the five buttons
    private ButtonGroup buttonGroup;
    
    // Buttons for sorting by rating, date and caption
    private JRadioButton sortByRating;
    private JRadioButton sortByDate;
    private JRadioButton sortByCaption;

    // Radio Butttons for ratings.
    private JRadioButton radioButton1;
    private JRadioButton radioButton2;
    private JRadioButton radioButton3;
    private JRadioButton radioButton4;
    private JRadioButton radioButton5;

    // JLabel for displaying the photos on the main screen.
    
    private JLabel mainPhoto;
    
    // Thumbnail Photos displayed on the page
    private JButton thumbButton1;
    private JButton thumbButton2;
    private JButton thumbButton3;
    private JButton thumbButton4;
    private JButton thumbButton5;

    // ArrayLists for thumb-nails, captions, dates, and ratings
    private ArrayList<JButton> thumbArrList;
    private ArrayList<JLabel> capArrList;
    private ArrayList<JLabel> dateArrList;
    private ArrayList<JLabel> rateArrList;

    // MainRating JLabel
    private JLabel mainRating;

    public int currentPhoto = 0;

    // Accesor method
    public PhotographContainer getImageLibrary() {
        return imageLibrary;
    }

    // Mutator method 
    public void setImageLibrary(PhotographContainer imageLibrary) {
        imageLibrary = this.imageLibrary;
    }

    
     //According to the sorting methods, the ArrayList is organized

    public void positionReset(ArrayList<Photograph> p) {
        int i = 0;
        for (Photograph t : p) {
            ImageIcon name = new ImageIcon(t.getFilename());
            Image thumbButton1 = name.getImage();
            Image modifiedImg3 = thumbButton1.getScaledInstance(80, 60, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            name = new ImageIcon(modifiedImg3);
            thumbArrList.get(i).setIcon(name);
            capArrList.get(i).setText("Caption: " + t.getCaption());
            dateArrList.get(i).setText("Date: " + t.getDateTaken());
            rateArrList.get(i).setText("Rating: " + t.getRating() + "");
            i++;
        }

        myViewer.imageLibrary.photos = p;
        ImageIcon pictureMain = new ImageIcon(myViewer.imageLibrary.getPhotos().get(0).getFilename());
        Image pictureMod = pictureMain.getImage();
        Image modifiedImg = pictureMod.getScaledInstance(400, 400, java.awt.Image.SCALE_SMOOTH);
        pictureMain = new ImageIcon(modifiedImg);

        mainPhoto.setIcon(pictureMain);
        currentPhoto = 0;
        rateModifier();
        mainRating.setText("Rating: " + myViewer.imageLibrary.getPhotos().get(currentPhoto).getRating());
    }

    public class thumbNailListener implements ActionListener {
        
         //When the user clicks on the thumbnail, the main photograph changes to that thumbnail.
       
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("click")) {
                JButton pressedButton = (JButton) e.getSource();
                if (pressedButton.equals(thumbButton1)) {
                    currentPhoto = 0;
                } else if (pressedButton.equals(thumbButton2)) {
                    currentPhoto = 1;
                } else if (pressedButton.equals(thumbButton3)) {
                    currentPhoto = 2;
                } else if (pressedButton.equals(thumbButton4)) {
                    currentPhoto = 3;
                } else if (pressedButton.equals(thumbButton5)) {
                    currentPhoto = 4;
                }
                ImageIcon pictureMain = new ImageIcon(myViewer.imageLibrary.getPhotos().get(currentPhoto).getFilename());
                Image pictureMod = pictureMain.getImage();
                Image modifiedImg = pictureMod.getScaledInstance(400, 400, java.awt.Image.SCALE_SMOOTH);
                pictureMain = new ImageIcon(modifiedImg);

                mainPhoto.setIcon(pictureMain);
                rateModifier();
                mainRating.setText("Rating: " + myViewer.imageLibrary.getPhotos().get(currentPhoto).getRating());
            }
        }
    }

    public void rateModifier() {
        switch (myViewer.imageLibrary.getPhotos().get(currentPhoto).getRating()) {
            case 1:
                buttonGroup.setSelected(buttonMod1, true);
                break;
            case 2:
                buttonGroup.setSelected(buttonMod2, true);
                break;
            case 3:
                buttonGroup.setSelected(buttonMod3, true);
                break;
            case 4:
                buttonGroup.setSelected(buttonMod4, true);
                break;
            case 5:
                buttonGroup.setSelected(buttonMod5, true);
                break;
        }
    }

    public void addComponentsToPane(Container pane) {
        /**
         * This is what I will be using to add components so that everything needed gets displayed accurately
         *
         * @param pane of type Container
         * @return This void method will return nothing
         */

        // Photo Panel
        JPanel photoJPanel = new JPanel();
        photoJPanel.setLayout(new BoxLayout(photoJPanel, BoxLayout.X_AXIS));
        ImageIcon imageIcon = new ImageIcon(myViewer.imageLibrary.getPhotos().get(0).getFilename());
        Image pictureMod = imageIcon.getImage();
        Image modifiedImg = pictureMod.getScaledInstance(400, 400, java.awt.Image.SCALE_SMOOTH);
        mainPhoto = new JLabel(new ImageIcon(modifiedImg));
        mainRating = new JLabel("Rating: " + myViewer.imageLibrary.getPhotos().get(0).getRating());
        photoJPanel.add(mainPhoto);
        JPanel controlJPanel = new JPanel();
        controlJPanel.setLayout(layout);

        class prevButtonListener implements ActionListener {

            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("click")) {
                    if (currentPhoto > 0) {
                        currentPhoto--;
                    } else if (currentPhoto == 0) {
                        currentPhoto = 4;
                    }
                    ImageIcon pictureMain = new ImageIcon(myViewer.imageLibrary.getPhotos().get(currentPhoto).getFilename());
                    Image pictureMod = pictureMain.getImage();
                    Image modifiedImg = pictureMod.getScaledInstance(400, 400, java.awt.Image.SCALE_SMOOTH);
                    pictureMain = new ImageIcon(modifiedImg);

                    mainPhoto.setIcon(pictureMain);
                    rateModifier();
                    mainRating.setText("Rating: " + myViewer.imageLibrary.getPhotos().get(currentPhoto).getRating());
                }
            }
        }

        class nextButtonListener implements ActionListener {
 
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("click")) {
                    if (currentPhoto < 4) {
                        currentPhoto++;
                    } else if (currentPhoto == 4) {
                        currentPhoto = 0;
                    }
                    ImageIcon pictureMain = new ImageIcon(myViewer.imageLibrary.getPhotos().get(currentPhoto).getFilename());
                    Image pictureMod = pictureMain.getImage();
                    Image modifiedImg = pictureMod.getScaledInstance(400, 400, java.awt.Image.SCALE_SMOOTH);
                    pictureMain = new ImageIcon(modifiedImg);

                    mainPhoto.setIcon(pictureMain);
                    rateModifier();
                    mainRating.setText("Rating: " + myViewer.imageLibrary.getPhotos().get(currentPhoto).getRating());
                }
            }
        }
        class sortByCapButtonListener implements ActionListener {
            /**
             * When the user clicks "Sort by Caption" button, then the photos become rearranged based on the first word of the
             * photo's caption, in alphabetical order
             */
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("click")) {
              
                    ArrayList<Photograph> rearrArrList = new ArrayList<>();
                    for (Photograph p : myViewer.imageLibrary.getPhotos()) {
                        if (rearrArrList.size() == 0) {
                            rearrArrList.add(p);
                        } else {
                            for (int i = 0; i < rearrArrList.size(); i++) {
                                if (p.getCaption().compareTo(rearrArrList.get(i).getCaption()) <= 0) {
                                    rearrArrList.add(i, p);
                                    break;
                                }
                                if (i == rearrArrList.size() - 1) {
                                    rearrArrList.add(p);
                                    break;
                                }
                            }
                        }
                    }
                    positionReset(rearrArrList);
                }
            }
        }

        class sortByDateButtonListener implements ActionListener {

            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("click")) {
                    ArrayList<Photograph> rearrArrList = new ArrayList<>();
                    for (Photograph p : myViewer.imageLibrary.getPhotos()) {
                        if (rearrArrList.size() == 0) {
                            rearrArrList.add(p);
                        } else {
                            for (int i = 0; i < rearrArrList.size(); i++) {
                                if (p.getDateTaken().compareTo(rearrArrList.get(i).getDateTaken()) <= 0) {
                                    rearrArrList.add(i, p);
                                    break;
                                }
                                if (i == rearrArrList.size() - 1) {
                                    rearrArrList.add(p);
                                    break;
                                }
                            }
                        }
                    }
                    positionReset(rearrArrList);
                }
            }
        }
        class sortByRatingButtonListener implements ActionListener {

            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("click")) {
                    ArrayList<Photograph> rearrArrList = new ArrayList<>();
                    for (Photograph p : myViewer.imageLibrary.getPhotos()) {
                        if (rearrArrList.size() == 0) {
                            rearrArrList.add(p);
                        } else {
                            for (int i = 0; i < rearrArrList.size(); i++) {
                                if (p.getRating() >= rearrArrList.get(i).getRating()) {
                                    rearrArrList.add(i, p);
                                    break;
                                }
                                if (i == rearrArrList.size() - 1) {
                                    rearrArrList.add(p);
                                    break;
                                }
                            }
                        }
                    }
                    positionReset(rearrArrList);
                }
            }
        }

        // JButtons for the next and previous buttons
        prev = new JButton("< Previous");
        prev.setActionCommand("click");
        prev.addActionListener(new prevButtonListener());

        next = new JButton("Next >");
        next.setActionCommand("click");
        next.addActionListener(new nextButtonListener());

        ButtonGroup buttonGroup2 = new ButtonGroup();

        sortByRating = new JRadioButton("Sort by Rating");
        buttonGroup2.add(sortByRating);
        sortByRating.setActionCommand("click");
        sortByRating.addActionListener(new sortByRatingButtonListener());

        sortByDate = new JRadioButton("Sort by Date");
        buttonGroup2.add(sortByDate);
        sortByDate.setActionCommand("click");
        sortByDate.addActionListener(new sortByDateButtonListener());

        sortByCaption = new JRadioButton("Sort by Caption");
        buttonGroup2.add(sortByCaption);
        sortByCaption.setActionCommand("click");
        sortByCaption.addActionListener(new sortByCapButtonListener());

        // Adding buttons to the controlJPanel
        controlJPanel.add(prev);
        controlJPanel.add(next);
        controlJPanel.add(sortByCaption);
        controlJPanel.add(sortByDate);
        controlJPanel.add(sortByRating);

        // Thumbnail Panel
        JPanel thumbPanel = new JPanel();
        thumbPanel.setLayout(new BoxLayout(thumbPanel, BoxLayout.Y_AXIS));
        ImageIcon thumbnail;

        thumbArrList = new ArrayList<>();
        thumbArrList.add(thumbButton1);
        thumbArrList.add(thumbButton2);
        thumbArrList.add(thumbButton3);
        thumbArrList.add(thumbButton4);
        thumbArrList.add(thumbButton5);

        // ArrayList of captions, dates, and ratings
        capArrList = new ArrayList<>();
        dateArrList = new ArrayList<>();
        rateArrList = new ArrayList<>();

 
        ArrayList<JButton> thumbNailArrList2 = new ArrayList<JButton>();
        int ind = 0;
        for (Photograph p : myViewer.imageLibrary.getPhotos()) {
            thumbnail = new ImageIcon(p.getFilename());
            Image thumbImg = thumbnail.getImage();
            Image modifiedImg2 = thumbImg.getScaledInstance(80, 60, java.awt.Image.SCALE_SMOOTH);
            thumbnail = new ImageIcon(modifiedImg2);
            thumbNailArrList2.add(ind, new JButton(thumbnail));
            capArrList.add(ind, new JLabel("Caption: " + p.getCaption()));
            dateArrList.add(ind, new JLabel("Date: " + p.getDateTaken()));
            rateArrList.add(ind, new JLabel("Rating: " + p.getRating() + ""));
            ind++;
        }

        thumbButton1 = thumbNailArrList2.get(0);
        thumbButton2 = thumbNailArrList2.get(1);
        thumbButton3 = thumbNailArrList2.get(2);
        thumbButton4 = thumbNailArrList2.get(3);
        thumbButton5 = thumbNailArrList2.get(4);
        thumbArrList.add(0, thumbButton1);
        thumbArrList.add(1, thumbButton2);
        thumbArrList.add(2, thumbButton3);
        thumbArrList.add(3, thumbButton4);
        thumbArrList.add(4, thumbButton5);

        for (int i = 0; i < 5; i++) {
            thumbPanel.add(thumbArrList.get(i));
            thumbPanel.add("Caption: ", capArrList.get(i));
            thumbPanel.add("Date: ", dateArrList.get(i));
            thumbPanel.add("Rating", rateArrList.get(i));
        }

        thumbButton1.setActionCommand("click");
        thumbButton2.setActionCommand("click");
        thumbButton3.setActionCommand("click");
        thumbButton4.setActionCommand("click");
        thumbButton5.setActionCommand("click");
        thumbButton1.addActionListener(new thumbNailListener());
        thumbButton2.addActionListener(new thumbNailListener());
        thumbButton3.addActionListener(new thumbNailListener());
        thumbButton4.addActionListener(new thumbNailListener());
        thumbButton5.addActionListener(new thumbNailListener());

        // Rating Panel
        JPanel ratingJPanel = new JPanel();
        ratingJPanel.setLayout(layout);

        changeRatingButtonLabel = new JLabel("Change Rating: ");
        radioButton1 = new JRadioButton("1");
        radioButton2 = new JRadioButton("2");
        radioButton3 = new JRadioButton("3");
        radioButton4 = new JRadioButton("4");
        radioButton5 = new JRadioButton("5");

        buttonGroup = new ButtonGroup();
        buttonGroup.add(radioButton1);
        buttonGroup.add(radioButton2);
        buttonGroup.add(radioButton3);
        buttonGroup.add(radioButton4);
        buttonGroup.add(radioButton5);

        buttonMod1 = radioButton1.getModel();
        buttonMod2 = radioButton2.getModel();
        buttonMod3 = radioButton3.getModel();
        buttonMod4 = radioButton4.getModel();
        buttonMod5 = radioButton5.getModel();

 
        radioButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myViewer.imageLibrary.getPhotos().get(currentPhoto).setRating(1);
                rateArrList.get(currentPhoto).setText("Rating: 1");
                mainRating.setText("Rating: " + myViewer.imageLibrary.getPhotos().get(currentPhoto).getRating());
            }
        });
        radioButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myViewer.imageLibrary.getPhotos().get(currentPhoto).setRating(2);
                rateArrList.get(currentPhoto).setText("Rating: 2");
                mainRating.setText("Rating: " + myViewer.imageLibrary.getPhotos().get(currentPhoto).getRating());
            }
        });
        radioButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myViewer.imageLibrary.getPhotos().get(currentPhoto).setRating(3);
                rateArrList.get(currentPhoto).setText("Rating: 3");
                mainRating.setText("Rating: " + myViewer.imageLibrary.getPhotos().get(currentPhoto).getRating());
            }
        });
        radioButton4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myViewer.imageLibrary.getPhotos().get(currentPhoto).setRating(4);
                rateArrList.get(currentPhoto).setText("Rating: 4");
                mainRating.setText("Rating: " + myViewer.imageLibrary.getPhotos().get(currentPhoto).getRating());
            }
        });
        radioButton5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myViewer.imageLibrary.getPhotos().get(currentPhoto).setRating(5);
                rateArrList.get(currentPhoto).setText("Rating: 5");
                mainRating.setText("Rating: " + myViewer.imageLibrary.getPhotos().get(currentPhoto).getRating());
            }
        });

        // Adding radioButtons to the rate JPanel
        ratingJPanel.add(changeRatingButtonLabel);
        ratingJPanel.add(radioButton1);
        ratingJPanel.add(radioButton2);
        ratingJPanel.add(radioButton3);
        ratingJPanel.add(radioButton4);
        ratingJPanel.add(radioButton5);

        rateModifier();


        pane.add(controlJPanel, BorderLayout.NORTH);
        pane.add(ratingJPanel, BorderLayout.SOUTH);
        pane.add(photoJPanel, BorderLayout.EAST);
        pane.add(thumbPanel, BorderLayout.WEST);
    }

    private void createAndShowGUI() {
        // Setting up the window
        PhotoViewer frame = new PhotoViewer();
        frame.setTitle("Photograph Viewer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addComponentsToPane(frame.getContentPane());
        
        // Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        String current;
        try {
            current = new java.io.File(".").getCanonicalPath();
            System.out.println("Current dir:" + current);
        } catch (IOException e) {
            e.printStackTrace();
        }

        myViewer = new PhotoViewer();
        String imageDirectory = "./src/Images/";

        Photograph p1 = new Photograph("Forest View", imageDirectory + "Forest.jpg", "1998-01-04", 5);
        Photograph p2 = new Photograph("Mountain Hiking", imageDirectory + "Mountain.jpg", "1980-10-23", 4);
        Photograph p3 = new Photograph("Here comes Spring!", imageDirectory + "Spring.jpg", "2000-01-01", 1);
        Photograph p4 = new Photograph("Glooming Sunset", imageDirectory + "Sunset.jpg", "2009-03-28", 2);
        Photograph p5 = new Photograph("Fresh WaterFall", imageDirectory + "WaterFall.jpg", "2020-01-04", 3);

        myViewer.imageLibrary = new PhotoLibrary("My Library", 1575);

        myViewer.imageLibrary.addPhoto(p1);
        myViewer.imageLibrary.addPhoto(p2);
        myViewer.imageLibrary.addPhoto(p3);
        myViewer.imageLibrary.addPhoto(p4);
        myViewer.imageLibrary.addPhoto(p5);

        Collections.sort(myViewer.imageLibrary.photos);
        javax.swing.SwingUtilities.invokeLater(() -> myViewer.createAndShowGUI());
    }
}
