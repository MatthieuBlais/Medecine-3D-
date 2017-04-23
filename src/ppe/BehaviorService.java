/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ppe;

import javax.media.j3d.Behavior;
import javax.media.j3d.WakeupOnAWTEvent;
import java.awt.event.KeyEvent;
import java.awt.AWTEvent;
import java.util.Enumeration;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.WakeupCriterion;


/**
 *
 * @author Matthieu Blais
 */
public class BehaviorService extends Behavior{
    
    // variable de travail
private TransformGroup targetTG;
private Transform3D rotation = new Transform3D();
private Transform3D rotationY = new Transform3D();
private Transform3D rotationZ = new Transform3D();
private Transform3D homothetie = new Transform3D();
private double anglex = 0.0;
private double angley =0.0;
private double anglez = 0.0;
private float scale= 1;
// constructeur
BehaviorService(TransformGroup targetTG)
{
this.targetTG = targetTG;
}

// on définit les évenemenst à détecter
public void initialize()
{
this.wakeupOn(new WakeupOnAWTEvent(KeyEvent.KEY_PRESSED));
}

// on définit l'action de ces évenements
public void processStimulus(Enumeration criteria)
{

  WakeupOnAWTEvent ev;
  WakeupCriterion genericEvt;
  AWTEvent[] events;
  
  while (criteria.hasMoreElements())
  {
   genericEvt = (WakeupCriterion) criteria.nextElement();
   
   if (genericEvt instanceof WakeupOnAWTEvent)
   {
    ev = (WakeupOnAWTEvent) genericEvt;
    events = ev.getAWTEvent();
    processAWTEvent(events);
   }
  }

// on se remet en attente
this.wakeupOn(new WakeupOnAWTEvent(KeyEvent.KEY_PRESSED));

}

private void processAWTEvent(AWTEvent[] events)
 {
  for( int n = 0; n < events.length; n++)
  {
   if( events[n] instanceof KeyEvent)
   {
    KeyEvent eventKey = (KeyEvent) events[n];
    
    if( eventKey.getID() == KeyEvent.KEY_PRESSED )
    {
     int keyCode = eventKey.getKeyCode();
     int keyChar = eventKey.getKeyChar();
     
     switch (keyCode)
     {
      case KeyEvent.VK_3:
       angley -= 0.01;
       rotationZ.rotZ(anglez);
       rotation.rotX(anglex);
       rotationY.rotY(angley);
       rotationY.mul(rotationZ);
       rotation.mul(rotationY);
       rotation.setScale(scale);
       targetTG.setTransform(rotation);
      break;
      
      case KeyEvent.VK_2:
       angley += 0.01;
       rotationZ.rotZ(anglez);
       rotation.rotX(anglex);
       rotationY.rotY(angley);
       rotationY.mul(rotationZ);
       rotation.mul(rotationY);
       rotation.setScale(scale);
       targetTG.setTransform(rotation);
      break;
          
      case KeyEvent.VK_4:
       anglex -= 0.01;
       rotationZ.rotZ(anglez);
       rotation.rotX(anglex);
       rotationY.rotY(angley);
       rotationY.mul(rotationZ);
       rotation.mul(rotationY);
       rotation.setScale(scale);
       targetTG.setTransform(rotation);
      break;
      
      case KeyEvent.VK_5:
       anglex += 0.01;
       rotationZ.rotZ(anglez);
       rotation.rotX(anglex);
       rotationY.rotY(angley);
       rotationY.mul(rotationZ);
       rotation.mul(rotationY);
       rotation.setScale(scale);
       targetTG.setTransform(rotation);
      break;
          
      case KeyEvent.VK_0:
       anglez -= 0.01;
       rotationZ.rotZ(anglez);
       rotation.rotX(anglex);
       rotationY.rotY(angley);
       rotationY.mul(rotationZ);
       rotation.mul(rotationY);
       rotation.setScale(scale);
       targetTG.setTransform(rotation);
      break;
      
      case KeyEvent.VK_1:
       anglez += 0.01;
       rotation.rotX(anglex);
       rotationY.rotY(angley);
       rotationZ.rotZ(anglez);
       rotationY.mul(rotationZ);
       rotation.mul(rotationY);
       rotation.setScale(scale);
       targetTG.setTransform(rotation);
      break;    
       
      case KeyEvent.VK_6:
          if(scale<4)
          scale += 0.003;
          rotation.rotX(anglex);
          rotationY.rotY(angley);
          rotationZ.rotZ(anglez);
          rotationY.mul(rotationZ);
          rotation.mul(rotationY);
          rotation.setScale(scale);
          targetTG.setTransform(rotation);
          break;
          
     case KeyEvent.VK_7:
          if(scale>0)
          scale -= 0.003;
          rotation.rotX(anglex);
          rotationY.rotY(angley);
          rotationZ.rotZ(anglez);
          rotationY.mul(rotationZ);
          rotation.mul(rotationY);
          rotation.setScale(scale);
          targetTG.setTransform(rotation);
          break;
     }
     
   //  rotationY.mul(rotationZ);
  //   rotation.mul(rotationY);
    // 
     
    // 
     
    
    }
   }
  }
 }


    
}
