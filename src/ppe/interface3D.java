/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ppe;

import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.objectfile.ObjectFile;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.image.TextureLoader;
import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Color;
import static java.awt.Color.blue;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.media.j3d.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.vecmath.*;

/**
 *
 * @author Matthieu Blais
 */
public class interface3D extends JFrame{
    
    private SimpleUniverse simpleU; 
    private BranchGroup scene;
    private Transform3D translation;
    private float transx;
    private float transy;
    private float transz;
    //The canvas to be drawn upon.
    public Canvas3D myCanvas3D;
    ObjectFile myOBJ = new ObjectFile();
    Scene myOBJScene = null;
    private JPanel pannel;
    
    public interface3D() {
        
    myCanvas3D = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
    // Creation d'un objet SimpleUniverse
     simpleU = new SimpleUniverse(myCanvas3D);

    // Positionnement du point d'observation pour avoir une vue correcte de la
    // scene 3D
    simpleU.getViewingPlatform().setNominalViewingTransform();
    initUserPosition();
    translation = new Transform3D();
    
    transx=0.2f;
    transy=-0.5f;
    transz=0.3f;
   
    pannel = new JPanel();
    pannel.setLayout(new BorderLayout());
    
//   pannel.removeAll();
    pannel.add(myCanvas3D);
  //  pannel.validate();
  //  pannel.repaint();
    
  }
    
    public JPanel getPanel(){
        return pannel;
    }
    
    public SimpleUniverse getSimple(){
        return simpleU;
    }
    
    public void createBranch(String path){
        // Creation de la scene 3D qui contient tous les objets 3D que l'on veut
        // visualiser
        
        scene = createSceneGraph(path);
        // Compilation de la scene 3D
        scene.compile();
    }
    
    public Background background(){
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0),100.0);
         Background bg = new Background();
    bg.setApplicationBounds(bounds);
    BranchGroup backGeoBranch = new BranchGroup();
    Sphere sphereObj = new Sphere(1.1f, Sphere.GENERATE_NORMALS
        | Sphere.GENERATE_NORMALS_INWARD
        | Sphere.GENERATE_TEXTURE_COORDS, 45);
    backGeoBranch.addChild(sphereObj);
    bg.setGeometry(backGeoBranch);
    return bg;
    }
    
    public void addBranch(){
        //Attachement de la scene 3D a l'objet SimpleUniverse
        simpleU.addBranchGraph(scene);
    }
     
     public void modifyTranslation(){
          translation.setTranslation(new Vector3f(transx,transy,transz));
     }
     
    
    /**
   * Creation de la scene 3D
   * @return scene 3D
   */
  public BranchGroup createSceneGraph(String path) {

Background background = new Background();

BufferedImage buffer=null;
        try {
            buffer = ImageIO.read(new File("background.jpg"));
            TextureLoader myLoader = new TextureLoader( buffer );
        } catch (IOException ex) {
            Logger.getLogger(interface3D.class.getName()).log(Level.SEVERE, null, ex);
        }

TextureLoader myLoader = new TextureLoader( buffer );
ImageComponent2D myImage = myLoader.getImage( );
background.setColor(new Color3f(0f, 0f, 0f));


Scene scene2 = null;
 Shape3D shape = null;
 ObjectFile objFileloader = new ObjectFile( ObjectFile.RESIZE );

 try
 {
     System.out.println("test path : " + path);
  scene2 = objFileloader.load( path);
  System.out.println("ok");
 }
 catch (Exception e)
 {
  scene2 = null;
  System.out.println("pasok "+ e.getMessage());
  System.err.println(e);
  System.exit(1);
 }


//on crée le Bg principal
BranchGroup objRoot=new BranchGroup();
BranchGroup jesaispas = new BranchGroup();
jesaispas.addChild(new Box());
BoundingSphere myBounds = new BoundingSphere(
    new Point3d( ), 1000.0 );
background.setApplicationBounds( myBounds );
//background.setGeometry(jesaispas);
// on créer le TG qui servira à la rotation du behavior1
Transform3D t3d = new Transform3D();
    t3d.set( new Vector3f(0,3.5f,15)); 
TransformGroup TG = new TransformGroup();
TG.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
TG.addChild(scene2.getSceneGroup());
objRoot.addChild(background);
//objRoot.addChild(createFloor());
objRoot.addChild(createPlaf());
objRoot.addChild(TG);

//------------ fin de creation d'une porte --------------------
//------- début de ajout de l'interaction ---------------------

BehaviorService behav=new BehaviorService(TG);
behav.setSchedulingBounds(new BoundingSphere());
TG.addChild(behav);

DirectionalLight light = new DirectionalLight(true, new Color3f(1.0f,
    1.0f, 1.0f), new Vector3f(-0.6f, 0.5f, -1.0f));

  light.setInfluencingBounds(new BoundingSphere(new Point3d(), 10000.0));
  
  Light light2 = null;
  
   light2 = new SpotLight (new Color3f(1.0f,
    1.0f, 1.0f),
                             new Point3f (-1.5f, 2f, 2f),      // position
                             new Point3f (1f, 0, 0),      // atténuation
                             new Vector3f (1f, -1, -1),   // direction
                             (float)Math.PI / 4,          // angle
                             92);                         // concentration
    light2.setInfluencingBounds (new BoundingSphere (new Point3d (), 10.0));
    
    Light light3 = new SpotLight (new Color3f(1.0f,
    1.0f, 1.0f),
                             new Point3f (-2f, -2f, 2f),      // position
                             new Point3f (1f, 0, 0),      // atténuation
                             new Vector3f (-1f, 0, 1),   // direction
                             (float)Math.PI / 4,          // angle
                             92);                         // concentration
    
   light3.setInfluencingBounds (new BoundingSphere (new Point3d (), 10.0));
   
   Light light4 = new SpotLight (new Color3f(0.0f,
    0.0f, 0.0f),
                             new Point3f (0f, 2f, 10f),      // position
                             new Point3f (1f, 0, 0),      // atténuation
                             new Vector3f (-1f, 0, 1),   // direction
                             (float)Math.PI / 4,          // angle
                             92);                         // concentration
    
   light4.setInfluencingBounds (new BoundingSphere (new Point3d (), 10.0));

    objRoot.addChild(light);
        objRoot.addChild(light2);
       objRoot.addChild(light3);  
      objRoot.addChild(light4);   
//------- fin de ajout de l'interaction ------------------------
return objRoot;

  }
    
    public void setTranslation(float x, float y, float z){
        transx = x;
        transy = y;
        transz = z;
    }
    
    public Shape3D createFloor(){
        
       BranchGroup floorBG;
        floorBG = new BranchGroup();
        int FLOOR_LEN = 16;
        ArrayList Coords = new ArrayList();
        
        
      for(int z=-FLOOR_LEN/2; z <= (FLOOR_LEN/2)-1; z++) {
      for(int x=-FLOOR_LEN/2; x <= (FLOOR_LEN/2)-1; x++) {
        
          createCoords(x, z, Coords);
      }
    }
        //floorBG.addChild(  );
        
     return new ColouredTiles(Coords, new Color3f(1.0f, 1.0f, 1.0f));
        
    }
    
      private void createCoords(int x, int z, ArrayList coords)
  // Coords for a single blue or green square, 
  // its left hand corner at (x,0,z)
  {
    // points created in counter-clockwise order
    Point3f p1 = new Point3f(x, -5.0f, z+1.0f);
    Point3f p2 = new Point3f(x+1.0f, -5.0f, z+1.0f);
    Point3f p3 = new Point3f(x+1.0f, -5.0f, z);
    Point3f p4 = new Point3f(x, -5.0f, z);   
    coords.add(p1); coords.add(p2); 
    coords.add(p3); coords.add(p4);
  }  // end of createCoords()
    
      
      public  TransformGroup createPlaf() {
        
       BranchGroup floorBG;
        floorBG = new BranchGroup();
      
        Transform3D scale = new Transform3D();
    scale.setScale(3);
    TransformGroup transformGroup = new TransformGroup(scale);
  Box colorBox = new Box();
        // Creation apparence couleur rouge de la face avant du cube
    // (remplissage Gouraud)
    Appearance appearanceFront = new Appearance();
    appearanceFront.setColoringAttributes(
      new ColoringAttributes(new Color3f(Color.WHITE),
                             ColoringAttributes.SHADE_FLAT));

    // Creation apparence couleur verte de la face arriere du cube
    // (remplissage Gouraud)
    Appearance appearanceBack = new Appearance();
    appearanceBack.setColoringAttributes(
      new ColoringAttributes(new Color3f(Color.GRAY),
                             ColoringAttributes.SHADE_FLAT));

    // Creation apparence couleur jaune de la face gauche du cube
    // (remplissage Gouraud)
    Appearance appearanceLeft = new Appearance();
    appearanceLeft.setColoringAttributes(
      new ColoringAttributes(new Color3f(Color.BLACK),
                             ColoringAttributes.SHADE_FLAT));

 
      
       // transformGroup.addChild(colorBox);
    return transformGroup;
    
    // return new ColouredTiles(coords, new Color3f(1.0f, 1.0f, 1.0f));
    }
    
      private void createCoords2(int x, int z, ArrayList coords)
  // Coords for a single blue or green square, 
  // its left hand corner at (x,0,z)
  {
    // points created in counter-clockwise order
    Point3f p1 = new Point3f(x, 4.0f, z+1.0f);
    Point3f p2 = new Point3f(x+1.0f, 4.0f, z+1.0f);
    Point3f p3 = new Point3f(x+1.0f, 4.0f, z);
    Point3f p4 = new Point3f(x, 4.0f, z);   
    coords.add(p1); coords.add(p2); 
    coords.add(p3); coords.add(p4);
  }  // end of createCoords()
        
      
      
      
        private void initUserPosition()
  // Set the user's initial viewpoint using lookAt()
  {
    ViewingPlatform vp = simpleU.getViewingPlatform();
    TransformGroup steerTG = vp.getViewPlatformTransform();

    Transform3D t3d = new Transform3D();
    steerTG.getTransform(t3d);

    // args are: viewer posn, where looking, up direction
    t3d.lookAt( new Point3d(0,3,7), new Point3d(0,0,0), new Vector3d(0,1,0));
    t3d.invert();

    steerTG.setTransform(t3d);
  }  // end of initUserPosition()
}

