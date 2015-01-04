/**
 * @(#)draw.java
 *
 * draw application
 *
 * @author 
 * @version 1.00 2015/1/3
 */
 
/* BasicFrame.java

  This is a really simple graphics program.
  It opens a frame on the screen with a single
  line drawn across it.

  It's not very polished, but it demonstrates
  a graphical program as simply as possible.mag-27Apr2008
*/

// Import the basic graphics classes.
import java.awt.*;
import javax.swing.*;

public class draw extends JFrame{

  // Create a constructor method
  public draw(){
    // All we do is call JFrame's constructor.
    // We don't need anything special for this
    // program.
    super();
  }

  	
    // Change this number to make fractal more coarse (larger) or
    // less coarse (smaller).  Numbers bigger than 9 make the
    // program run extremely slow.  This number should be positive
    // and non-zero.
    protected int steps = 7;
    protected void windowClosed() {
        
        // TODO: Check if it is safe to close the application
        
        // Exit application.
        System.exit(0);
    }
    // The JFrame object that is being drawn to has the point of origin
    // (0,0) in the upper left corner of the window.  This needs to
    // be taken into account when calculating the required angles and
    // lengths.  Normal Cartesian coordinates use the lower left corner.
    // Keep this in mind when reviewing the math.
    private void DrawLine(double x1, double y1, double x2, double y2, double steps, Graphics g2){
        // when the number of steps is less than 1, terminate the recursion.  This will stop
        // the segmenation of the line and simply draw the line.
        if(steps <= 1){
            g2.drawLine((int)x1,(int)y1,(int)x2,(int)y2);
        }
        else
        {
            // segment the current line into 4 segments.  Each segment has
            // length equal to 1/3 of the original line.  The outer third
            // segments are drawn along the direction of the original line.
            // The middle segment is thrown out and replaced with 2 lines
            // drawn as part of an isosolece triangle.  It will look 
            // something like this.
            //    original line                    segmented line.
            //                                         /\
            //    __________      =====>          ____/  \____
            double length,dx,dy,temp;
            double sixtydeg = Math.PI / 3.0;
            double onetwentydeg = sixtydeg * 2.0;
            
            // divide x/y length of line into 1/3 of original lenth
            dx = (x2 - x1) / 3.0;
            dy = (y2 - y1) / 3.0;
            
            // we will need to subtract 60 degrees for the fist line
            // and 120 degrees for the second line.  This will require
            // calculation of the current angle of the line.  The arctangent
            // function is used for this.
            //
            // The 2 new lines will need the 1/3 line length.  The square
            // root of the sum of the squares of the dx/dy is used for
            // this 
            temp   = Math.atan2(dy,dx);
            length = Math.sqrt((dx*dx) + (dy*dy));

            // The first line is draw 1/3 of the length of the original line, 
            // using the same start x/y coordinate of the original line.
            DrawLine(x1,      y1,
                     x1 + dx, y1 + dy,
                     steps - 1,g2);
            // The second line is drawn at a 60 degree angle with a length
            // of 1/3 of the original line.
            DrawLine(x1 + dx, y1 + dy, 
            	      x1 + dx + (length * Math.cos(temp - sixtydeg)), y1 + dy + (length * Math.sin(temp - sixtydeg)), 
                     steps - 1,g2);
            // The second line is drawn at a 120 degree angle with a length
            // of 1/3 of the original line.
            DrawLine(x1 + (2.0 * dx) + (length * Math.cos(temp - onetwentydeg)),y1 + (2.0 * dy) + (length * Math.sin(temp - onetwentydeg)),
                     x1 + (2.0 * dx),y1 + (2.0 * dy),
                     steps - 1,g2);
            // The last line is draw 1/3 of the length of the original line, 
            // using the same end x/y coordinate of the original line.
            DrawLine(x1+(2.0 * dx),y1+(2.0 * dy),x2,y2, steps - 1,g2);
        }
    }
    // The following methods are instance methods.
    /* Create a paint() method to override the one in JFrame.
       This is where the drawing happens. 
       We don't have to call it in our program, it gets called
       automatically whenever the frame needs to be redrawn,
       like when it it made visible or moved or whatever.*/
    public void paint(Graphics g){
       // These draw the 3 lines of the isosoleces triangle
       // used in the recursive procedure.
       DrawLine( 75,200,625,200, steps,g); 
       DrawLine(625,200,350,676, steps,g); 
       DrawLine(350,676, 75,200, steps,g); 

    }
  
    public static void main(String arg[]){
        // create an identifier named 'window' and
        // apply it to a new BasicFrame object
        // created using our constructor, above.
        draw frame = new draw();

        // Use the setSize method that our BasicFrame
        // object inherited to make the frame
        // 700 pixels wide and high.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700,700);

        // Make the window show on the screen.
        frame.setVisible(true);
    }
}
