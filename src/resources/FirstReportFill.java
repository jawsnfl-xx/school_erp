
package resources;

import java.awt.Dimension;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import javax.swing.JFrame;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;

public class FirstReportFill {

    public void showReport() throws SQLException{
        Connection con = new schoolerp.dbConnection().returnCon();

        try {
            String reportName = "src\\resources\\teachers.jasper";
            
            JasperPrint me = JasperFillManager.fillReport(reportName, null, con);
            
            JRViewer jv = new JRViewer(me);
            
            //Insert viewer to a JFrame to make it showable
            JFrame jf = new JFrame();
            jf.getContentPane().add(jv);
            jf.validate();
            jf.setVisible(true);
            jf.setSize(new Dimension(800,600));
            jf.setLocation(300,100);
            jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        } catch (JRException e) {
            e.printStackTrace();
        }
    }
}