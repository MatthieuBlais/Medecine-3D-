/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medecine3d;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Victor
 */
public class Medecine3D {

   
    /**
     * @param args the command line arguments
     */
 
  
    private final Connection connection;    
    
  public Medecine3D(String url, String user, String password) throws SQLException {
    	
    	connection = DriverManager.getConnection(url, user, password);
    }

  
    public List<Post> getPost() throws SQLException {
                Statement stmt2 = connection.createStatement();
                ResultSet result2 = null;
                List<Post> rlist= new ArrayList<Post>(); 
    		Statement stmt = connection.createStatement();
    		ResultSet result = stmt.executeQuery("SELECT ID,post_title,post_content,post_author FROM wp_posts WHERE post_status='publish' AND "
                        + "post_type='post'" );
    	
    				while (result.next()) {
                                    
                    result2=stmt2.executeQuery("SELECT name from wp_terms "
                            + "NATURAL JOIN wp_term_relationships "
                            + "NATURAL JOIN wp_term_taxonomy WHERE object_id="+result.getInt(1));
                    result2.next(); 
                System.out.println(result2.getString(1)+"test ");
    		rlist.add(new Post(result.getString(2), result.getString(3), result.getInt(4),result2.getString(1)));
    		}
    		return rlist;
    }
    
    public static void execute() throws SQLException {
        Medecine3D medecin;
        
        medecin = new Medecine3D("jdbc:mysql://mysql1.alwaysdata.com:3306/medecine3d_ppe","103873","azerty123456");
        List<Post> lpost;
        lpost = medecin.getPost();
        for (Post p : lpost)
        {
            p.breakDesc();
        }
        Post pp = new Post();
    }
    
    
}
