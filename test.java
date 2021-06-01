// Yonwoo Choi, yc4ny CS 2110 HW4

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*; 
import java.util.*;

public class test extends JPanel {
    public static void main(final String[] args) {
        File a = new File("/WaterFall.jpg");
        System.out.println(a.getAbsolutePath());
        PhotographContainer photoContainer = new Album("Images");
        Photograph p1 = new Photograph("WaterFall", "/Images/WaterFall.jpg","2020-01-04", 3);
        Photograph p2 = new Photograph("Sunset", "Images/Sunset.jpg", "2009-03-28", 2);
        p1.loadImageData(p1.getFilename());
        photoContainer.addPhoto(p1);
        photoContainer.addPhoto(p2);
        Icon hi = new ImageIcon(p1.imageData);
        JLabel image1 = new JLabel(hi);
        
        JFrame f = new JFrame("Editor");

        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());

        JPanel topRow = new JPanel();
        topRow.setLayout(new FlowLayout());
        
        JButton exit = new JButton("Exit");
        JButton prev = new JButton("Previous");
        JButton next = new JButton("Next");

        topRow.add(new JLabel("Test Libary Image X of X"));
        topRow.add(exit);
        topRow.add(prev);
        topRow.add(next);
        topRow.add(new JRadioButton("Sort by Date"));
        topRow.add(new JRadioButton("Sort by Caption"));
        topRow.add(new JRadioButton("Sort by Rating"));

        // LEFT GRID
        JPanel leftGrid = new JPanel();
        leftGrid.setLayout(new BoxLayout(leftGrid, BoxLayout.Y_AXIS));

        JLabel c1 = new JLabel();
        c1.setLayout(new FlowLayout());
        c1.add(image1);
        c1.add(new JLabel("CAPTION "));
        c1.setSize(100, 50);
        leftGrid.add(c1);

        // Right Image View
        JPanel imagePanel = new JPanel();
        // Image VIew Component

        // Bottom Layout
        JPanel bottomRow = new JPanel();
        bottomRow.setLayout(new FlowLayout());

        bottomRow.add(new JLabel("Rating"));
        bottomRow.add(new JRadioButton("1"));
        bottomRow.add(new JRadioButton("2"));
        bottomRow.add(new JRadioButton("3"));
        bottomRow.add(new JRadioButton("4"));
        bottomRow.add(new JRadioButton("5"));

        content.add(topRow, BorderLayout.NORTH);
        content.add(leftGrid, BorderLayout.WEST);
        content.add(imagePanel, BorderLayout.CENTER);
        content.add(bottomRow, BorderLayout.SOUTH);

        f.setContentPane(content);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(800, 600);
        f.setVisible(true);
        exit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        

    }
}
