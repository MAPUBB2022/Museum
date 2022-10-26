package views;

import classes.Exhibit;

import java.util.ArrayList;
import classes.*;

public class ViewExhibit {

    public static void displayExhibit (Exhibit exhibit) {
            String s = "";
            s += "This Exhibit is called " + exhibit.getName() + "\n";
            s += "It was made in " + exhibit.getCreation().getYear() + "\n";
            if (Artifact.class.isAssignableFrom(exhibit.getClass())) {
                s += "It comes originally from " + ((Artifact) exhibit).getOrigin() + "\n";
            } else if (Statue.class.isAssignableFrom(exhibit.getClass())) {
                s += "The sculpture is made by " + ((Statue) exhibit).getSculptor().getName() + "\n";
            } else {
                s += "The painting is made by " + ((Painting) exhibit).getPainter().getName() + "\n";
            }
            s += "You can see it in " + exhibit.getLocation().getName() + "\n";
            System.out.println(s);
        }
}
