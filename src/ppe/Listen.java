/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ppe;
import com.leapmotion.leap.*;
import com.leapmotion.leap.Finger.Type;
import com.sun.j3d.utils.universe.SimpleUniverse;
import guibuilder.Categories1;
import guibuilder.LeapListener;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Matthieu Blais
 */
public class Listen extends Listener {
    
    final static int NO_ACTION = 0;
    final static int ZOOM_IN = 1;
    final static int ZOOM_OUT = -1;
    final static int ZOOM_IN_FAST = 2;
    final static int ZOOM_OUT_FAST = -2;
    final static int SWIPE_LEFT = 3;
    final static int SWIPE_RIGHT = -3;
    final static Vector VECTOR_NULL=new Vector(0,0,0);
    
    private Controller controller;
    private LeapListener listener1;
    private JPanel jPanel1;
    private SimpleUniverse su;
    
    public Listen(LeapListener l, JPanel j){
        listener1 = l;
        jPanel1 = j;
    }
    
    @Override
    public void onConnect(Controller controller) {
        System.out.println("Connected");
    }
    
    @Override
    public void onFrame(Controller controller) {
    
    this.controller = controller;
    Frame frame = this.controller.frame();

    rotationHand(frame, this.controller.frame(30));

    grabHand(frame);
   // keyTapGesture(frame);
    
    }
    
    public void setSimple(SimpleUniverse s){
        su = s;
    }
    
    public static int grabHand(Frame frame){
        //get list of detected hands
        HandList hands = frame.hands();
        Hand rightHand = null;
        
        //get right hand
        for (int i=0; i<hands.count();i++){
            if(hands.get(i).isRight()){
                rightHand = hands.get(i);
                break;
            }
        }
        //No right hand so no grab
        if(rightHand==null || hands.count()!=1){
           // System.out.println("No right Hand");
            return NO_ACTION;
        }
        //great confidence of the detection of right hand 
       if(rightHand.confidence()>0.7){
           
           if(rightHand.grabStrength()==1){
               System.out.println("Closed");
               new SimuKey(KeyEvent.VK_6);
               return ZOOM_IN_FAST;
           }
           else if(rightHand.grabStrength() == 0){
               System.out.println("Opened");
               new SimuKey(KeyEvent.VK_7);
               return ZOOM_OUT_FAST;
           }
           else if(rightHand.grabStrength()>=0.75){
               System.out.println("Almost Closed");
               new SimuKey(KeyEvent.VK_6);
               return ZOOM_IN;
           }        
           else if (rightHand.grabStrength()<=0.25){
               System.out.println("Almost Opened");
               return ZOOM_OUT;
           } 
       }
        
       //No Grab
       //System.out.println("No Zoom");
       return NO_ACTION; 
        
    }
   
    public static int swipeGesture(Frame frame, LeapListener l, SimpleUniverse s, JFrame f){
        GestureList gesturelist = frame.gestures();
        SwipeGesture gesture = null;
        
        if (!gesturelist.isEmpty()){
       
            for (int i=0; i< gesturelist.count(); i++) {
               
                if(gesturelist.get(i).type() == Gesture.Type.TYPE_SWIPE) {
                    gesture = new SwipeGesture(gesturelist.get(i));
                    break;
               }
            }
            
           if(gesture != null){
                l.setMode(0);
                
                f.dispose();
                /*Vector currentSwipePosition = gesture.position();
                Vector swipeStart = gesture.startPosition();
            
                if(currentSwipePosition.getX()<swipeStart.getX()){
                    System.out.println("Right -> Left");
                    return SWIPE_LEFT;
                }
                if(currentSwipePosition.getX()>swipeStart.getX()){
                    System.out.println("Left -> Right");
                    return SWIPE_RIGHT;
                }*/
            }
        }
        
        return NO_ACTION;
    }
    
    public Vector keyTapGesture(Frame frame){
        GestureList gesturelist = frame.gestures();
        KeyTapGesture gesture=null;
        
        if (!gesturelist.isEmpty()){
           
            for (int i=0; i< gesturelist.count(); i++) {
               
                if(gesturelist.get(i).type() == Gesture.Type.TYPE_KEY_TAP) {
                    gesture = new KeyTapGesture(gesturelist.get(i));
                    break;
               }
            }
            
            if(gesture != null){
                return gesture.position();
            }
        }
        
        return null;
    }
    
   
    public static Vector rotationHand(Frame frame, Frame previous){
        //get list of detected hands
        HandList hands = frame.hands();
        Hand leftHand = null;
        HandList previousHands = previous.hands();
        Hand previousLeftHand = null;
           
        //get left hand
        for (int i=0; i<hands.count();i++){
            if(hands.get(i).isLeft()){
                leftHand = hands.get(i);
                break;
            }
        }
        for (int i=0; i<previousHands.count();i++){
            if(previousHands.get(i).isLeft()){
                previousLeftHand = previousHands.get(i);
                break;
            }
        }
        if(leftHand==null || hands.count()>1 || previousLeftHand==null || previousHands.count()>1){
            System.out.println("No left Hand or Two hands detected");
            return VECTOR_NULL;
        }
        
        float pinch = leftHand.pinchStrength();
        
        if(pinch>0.9){
            
            Vector currentPosition = leftHand.palmPosition();
            Vector previousPosition = previousLeftHand.palmPosition();
            
            Vector difference = currentPosition.minus(previousPosition);
            
            System.out.println(difference.toString());
            
            float a = difference.getX();
            float b = difference.getY();
            float c = difference.getZ();
            
            System.out.println(b);
            
            if (a<-5.0){
                new SimuKey(KeyEvent.VK_3);
            }
            if (a>5)
                new SimuKey(KeyEvent.VK_2);
            
            if(b>5)
                new SimuKey(KeyEvent.VK_4);
            if(b<-5)
                new SimuKey(KeyEvent.VK_5);
            
            if(c<-5)
                new SimuKey(KeyEvent.VK_0);
            if(c>5)
                new SimuKey(KeyEvent.VK_1);
            
            
           // new SimuKey();
            return difference;
        }
        return VECTOR_NULL;
    }
       
    public Vector translationHand(Frame frame, Frame previous){
        //get list of detected hands
        HandList hands = frame.hands();
        Hand leftHand = null;
        HandList previousHands = previous.hands();
        Hand previousLeftHand = null;
           
        //get left hand
        for (int i=0; i<hands.count();i++){
            if(hands.get(i).isLeft()){
                leftHand = hands.get(i);
                break;
            }
        }
        for (int i=0; i<previousHands.count();i++){
            if(previousHands.get(i).isLeft()){
                previousLeftHand = previousHands.get(i);
                break;
            }
        }
        if(leftHand==null || hands.count()!=2 || previousLeftHand==null || previousHands.count()!=2){
            System.out.println("No 2 hands detected");
            return VECTOR_NULL;
        }
        
        float pinch = leftHand.pinchStrength();
        
        if(pinch==1){
            
            Vector currentPosition = leftHand.palmPosition();
            Vector previousPosition = previousLeftHand.palmPosition();
            
            Vector difference = currentPosition.minus(previousPosition);
            
            System.out.println(difference.toString());
            return difference;
        }
        return VECTOR_NULL;
    }
}
