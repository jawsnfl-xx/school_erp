package installationPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class dbConnection {
    
    
    
    public Connection returnCon() throws SQLException {
        Connection con =null;
      
            String url = "jdbc:derby://localhost:1527/school";
            String user = "school";
            String password = "school";
            con = DriverManager.getConnection( url, user, password );    
        
        return con;
    }
}
