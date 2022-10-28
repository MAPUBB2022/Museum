package views;

import classes.Artifact;
import classes.Exhibit;
import classes.Painting;
import classes.Statue;

import java.util.Calendar;

public class ViewExhibit {
    public static void displayExhibit(Exhibit exhibit) {
        String s = "";
        s += "This Exhibit is called " + exhibit.getName() + "\n";
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(exhibit.getCreation());
        s += "It was made in " + calendar.get(Calendar.YEAR) + "\n";
        if (Artifact.class.isAssignableFrom(exhibit.getClass())) {
            s += "It comes originally from " + ((Artifact) exhibit).getOrigin() + "\n";
        } else if (Statue.class.isAssignableFrom(exhibit.getClass())) {
            s += "The sculpture is made by " + ((Statue) exhibit).getSculptor().getName() + "\n";
        } else {
            s += "The painting is made by " + ((Painting) exhibit).getPainter().getName() + "\n";
        }
        s += "You can see it in " + exhibit.getLocation().getName() + "\n";
        s += "The price of this exhibit is " + exhibit.getPrice() + "\n";
        System.out.println(s);
    }
}
