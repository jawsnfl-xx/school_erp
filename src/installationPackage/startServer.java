package installationPackage;

import java.net.InetAddress;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.derby.drda.NetworkServerControl;

public class startServer {
    public void start(){
        try {
            NetworkServerControl server = new NetworkServerControl(InetAddress.getByName("localhost"),1527);
            
            server.start(null);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"Server not started");
        } 
    }
    public void startSystem(){
         try {
            Connection p = new dbConnection().returnCon();
            
            String check = "SELECT * FROM SCHOOL.INSTALLED";
            
            Statement stmt = p.createStatement();
            ResultSet rst = stmt.executeQuery(check);
            
            while(rst.next()){
                if("Done".equals(rst.getString("VERIFY"))){
                    new schoolerp.login().setVisible(true);
                }else if ("yet".equals(rst.getString("VERIFY"))) {
                    new beginInstallation().setVisible(true);
                }                    
            }           
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Please make sure to start the server from netbeans");
        }
    }
    
}
