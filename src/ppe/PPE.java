/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ppe;


import com.leapmotion.leap.*;
import guibuilder.LeapListener;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;



/**
 *
 * @author Matthieu Blais
 */
public class PPE extends JFrame{

    /**
     * @param args the command line arguments
     */
    private JPanel panel;
    
    //SELECT AN IMAGE WITH A SWIPE MOVEMENT
    public void launchImage(Controller controller, String path, JPanel pannel, LeapListener l) {
        l.setMode(1);
        controller.enableGesture(Gesture.Type.TYPE_SWIPE);
        controller.enableGesture(Gesture.Type.TYPE_KEY_TAP);
        
        
        interface3D inter = new interface3D();
        l.setSimple(inter.getSimple());
        inter.createBranch(path);
        inter.addBranch();
        panel = inter.getPanel();
        l.setFrame(this);
        setSize(1024,720);
        
        add(panel);
        
        setVisible(true);
        //new PPE();
        
    }
    
    public JPanel getPanel(){
        return panel;
    }
    
}
