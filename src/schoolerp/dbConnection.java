package schoolerp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnection {

    public Connection returnCon() throws SQLException {
        //The localhost part can be changed to ip address of machine with database
        //in a client-server architecture.
       String url = "jdbc:derby://localhost:1527/school";
       String user = "school";
       String password = "school";
       Connection con = DriverManager.getConnection( url, user, password );
        
       return con;
    }
        
}
