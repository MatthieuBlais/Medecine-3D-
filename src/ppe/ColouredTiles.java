/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ppe;

import javax.media.j3d.*;
import javax.vecmath.*;
import java.util.ArrayList;

/*
* @author Matthieu
**/
public class ColouredTiles extends Shape3D 
{
  private QuadArray plane;


  public ColouredTiles(ArrayList coords, Color3f col) 
  {
    plane = new QuadArray(coords.size(), 
				GeometryArray.COORDINATES | GeometryArray.COLOR_3 );
    createGeometry(coords, col);
    createAppearance();
  }    


  private void createGeometry(ArrayList coords, Color3f col)
  { 
    int numPoints = coords.size();

    Point3f[] points = new Point3f[numPoints];
    coords.toArray( points );
    plane.setCoordinates(0, points);

    Color3f cols[] = new Color3f[numPoints];
    for(float i=0; i < numPoints; i++)
      cols[(int)i] = new Color3f(1f, 1f, 1f);;
    plane.setColors(0, cols);

    setGeometry(plane);
  }  // end of createGeometry()


  private void createAppearance()
  {
    Appearance app = new Appearance();

    PolygonAttributes pa = new PolygonAttributes();
    pa.setCullFace(PolygonAttributes.CULL_NONE);   
      // so can see the ColouredTiles from both sides
    app.setPolygonAttributes(pa);

    setAppearance(app);
  }  // end of createAppearance()


} // end of ColouredTiles class
