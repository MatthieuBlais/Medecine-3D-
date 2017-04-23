package guibuilder;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.leapmotion.leap.*;
import com.sun.j3d.utils.universe.SimpleUniverse;
import javax.swing.JFrame;
import static ppe.Listen.grabHand;
import static ppe.Listen.rotationHand;
import static ppe.Listen.swipeGesture;



/**
 *
 * @author Matthieu
 */
public class LeapListener extends Listener {
	
	//Just to control the speed, it can be changed accordingly to needs
	private int SLOW = 25;
	//Screen resolution, it should match the current screen resolution for more precise movements
	private int SCREEN_X = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
	private int SCREEN_Y = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
	private int fingers_count = 0;
	//MODE = 0: Menu control
    //MODE = 1: 3D Object handling
    private int mode = 0;
	private boolean Lclicked = false;
    private SimpleUniverse su;
    private JFrame jframe;

    /* SETTER - GETTER */
    public void setFrame(JFrame f){
        jframe = f;
    }

    /** SET UNIVERSE **/
    public void setSimple(SimpleUniverse s){
        su = s;
    }


    /** LISTENERS **/
    public void onInit(Controller controller) {
        System.out.println("Initialized");
        System.out.println("Current screen resolution: " + SCREEN_X +"x" + SCREEN_Y);
    }

    public void onConnect(Controller controller) {
        System.out.println("Connected");
    }

    
    
    public void onDisconnect(Controller controller) {
        System.out.println("Disconnected");
    }

    public void onExit(Controller controller) {
        System.out.println("Exited");
        System.exit(0);
    }

    public void onFrame(Controller controller) {
        // Get the most recent frame and report some basic information
        Frame frame = controller.frame();

        if(mode==0){
            int numGestures = frame.gestures().count();
        	for (int i=0; i < numGestures; i++) {
        	    if(frame.gestures().get(i).type() == Gesture.Type.TYPE_KEY_TAP && !Lclicked) {
                    System.out.println("Geste : " + numGestures);
    	    		clickMouse(0);
            		releaseMouse(0);
            		Lclicked = true;
        	    	slow();
        	    }
    	   }
           Lclicked = false;
        
           if (frame.fingers().count()>0) {
          
            // Get fingers
            FingerList fingers = frame.fingers();
            fingers_count = frame.fingers().count();
            System.out.println("finger : "+fingers_count);

                // Calculate the hand's average finger tip position
                Vector avgPos = Vector.zero();
                for (Finger finger : fingers) {
                    avgPos = avgPos.plus(finger.tipPosition());
                }
                avgPos = avgPos.divide(fingers.count());
              
                moveMouse(avgPos.getX()*15, SCREEN_Y - avgPos.getY()*5);	               
            slow();
        }
        }
        else{
            rotationHand(frame, controller.frame(30));
            grabHand(frame);
            swipeGesture(frame, this, su, jframe);
        }
    }
    
    public void setMode(int a){
        mode = a;
    }
    
    // Slows down the frame rate
    private void slow(){
    	try {
			Thread.sleep(SLOW);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
    
    public void moveMouse(float x, float y)
    {
    	 Robot mouseHandler;
    	try {		
    		mouseHandler = new Robot();
    		mouseHandler.mouseMove((int)x, (int)y);	
    	} catch (AWTException e) {
                e.printStackTrace();
    	}
    }
    
    
    public void clickMouse(int value)
    {
    	int input;
    	input = InputEvent.BUTTON1_MASK; 
    	Robot mouseHandler;
    	try {	
        	mouseHandler = new Robot();
        	mouseHandler.mousePress(input);			
    	} catch (AWTException e) {
    		e.printStackTrace();
    	} 
    }
 

    public void releaseMouse(int value)
    {
    	int input;
        input = InputEvent.BUTTON1_MASK;
    	Robot mouseHandler;
    	try {				
            mouseHandler = new Robot();
            mouseHandler.mouseRelease(input);
					
    	} catch (AWTException e) {
                e.printStackTrace();
    	}
    }   
    
    
}