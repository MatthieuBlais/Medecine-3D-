/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ppe;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

/**
 *
 * @author Matthieu Blais
 */
public class SimuKey {
    
    private Robot robot;
    
    //To fake a key press
    public SimuKey(int a){
      
	try {
	    robot = new Robot();
	    
	    
	    // Simulate a key press
	    robot.keyPress(a);
	    robot.keyRelease(a);
	} catch (AWTException e) {
	    e.printStackTrace();
	    
	}
    }
    
    
    
}
