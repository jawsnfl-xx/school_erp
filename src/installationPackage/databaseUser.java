package installationPackage;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import schoolerp.SchoolERP;

public class databaseUser {
    public static String hostname;
    //Method to create user and password for database connection
    public void createUser(){
    
    }
    //Create database School
    public void createDatabase(){
        
    }
    //Create tables for the database
    public void createTables(){
        
    }
    
    //Get host for the database
    public void getdbHost(){
        try {
            InetAddress addr = InetAddress.getLocalHost();
            hostname = addr.getHostName();
        } catch (UnknownHostException ex) {
            Logger.getLogger(SchoolERP.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Store the name in a static variable that can be accessed from another class
        System.out.println(hostname);
    }
}
