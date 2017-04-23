package guibuilder;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Gesture;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import medecine3d.*;

/**
 *
 * @author Matthieu
 */
public class GUIBuilder {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
       // Create a sample listener and controller
        LeapListener listener = new LeapListener();
        Controller controller = new Controller();
        controller.enableGesture( Gesture.Type.TYPE_KEY_TAP );
        
        // Have the sample listener receive events from the controller
        controller.addListener(listener);

        controller.config().setFloat("Gesture.KeyTap.MinDownVelocity", 30.0f);
        controller.config().setFloat("Gesture.KeyTap.HistorySeconds", .05f);
        controller.config().setFloat("Gesture.KeyTap.MinDistance", 2.0f);
        controller.config().save();
        // Keep this process running until Enter is pressed
        try {
            Medecine3D.execute();
        } catch (SQLException ex) {
            Logger.getLogger(GUIBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Interface GuiBuilder = new Interface(controller,listener);
        GuiBuilder.setVisible(true);
        
        System.out.println("Press Enter to quit...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // Remove the sample listener when done
        controller.removeListener(listener);
    }
    
}
