package resources;
import java.awt.Dimension;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;
 
/**
 *
 * @author Hans Kristanto
 */
public class MainApplication {
 
    Connection conn = null;
 
    public void initConnection(){

        try {
            conn = new schoolerp.dbConnection().returnCon();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
 
    public void showReport(){
         
        //Path to your .jasper file in your package
        String reportName = "src/resources/teachers.jasper";
         
        //Get a stream to read the file
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(reportName);
 
        try {
     //Fill the report with parameter, connection and the stream reader    
            JasperPrint jp = JasperFillManager.fillReport(is, null, conn);
         
     //Viewer for JasperReport
            JRViewer jv = new JRViewer(jp);
           
     
     //Insert viewer to a JFrame to make it showable
            JFrame jf = new JFrame();
            jf.getContentPane().add(jv);
            jf.validate();
            jf.setVisible(true);
            jf.setSize(new Dimension(800,600));
            jf.setLocation(300,100);
            jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        } catch (JRException ex) {
            ex.printStackTrace();
        }
    }
 
    public static void main(String[] args) {
 
        MainApplication ma = new MainApplication();
        ma.initConnection();
        ma.showReport();
    }
 
}