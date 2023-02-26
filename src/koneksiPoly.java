
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Muh Israjab DS
 */
public class koneksiPoly {
     private String url="jdbc:mysql://localhost/db_tokoac";
 private String username_laragon = "root";
 private String password_laragon = "";
 private Connection con;
 
 
 public void connect(){
     try {
         con = DriverManager.getConnection(url, username_laragon, password_laragon);
         System.out.println("Databases Connect Success!");
     } catch (Exception e) {
         JOptionPane.showMessageDialog(null, e.getMessage());
     }
 
 }

    public Connection getCon() {
        return con;
    }
    
}
