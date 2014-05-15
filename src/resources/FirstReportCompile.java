package resources;

import java.awt.Dimension;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JFrame;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.swing.JRViewer;
public class FirstReportCompile {

    public static void main(String[] args) throws SQLException {
        try {
            Connection con = new schoolerp.dbConnection().returnCon();
            
            System.out.println("Compiling report...");
            JasperReport me = JasperCompileManager.compileReport("src\\resources\\teachers.jrxml");
            
            JasperPrint jp = JasperFillManager.fillReport(me,null,con);
            JRViewer jv = new JRViewer(jp);
            
            //Insert viewer to a JFrame to make it showable
            JFrame jf = new JFrame();
            jf.getContentPane().add(jv);
            jf.validate();
            jf.setVisible(true);
            jf.setSize(new Dimension(800,600));
            jf.setLocation(300,100);
            jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            System.out.println("Done!");
        } catch (JRException e) {
            e.printStackTrace();
        }
    }
}