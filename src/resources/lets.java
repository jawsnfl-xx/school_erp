package resources;
import java.sql.Connection;
import java.sql.SQLException;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class lets {
    String fileName = "src/resources/teachers.jasper";
    String outputName ="src/resources/teachers.pdf";
    HashMap hm = new HashMap();
     public void make(){
        try {
            Connection con = new schoolerp.dbConnection().returnCon();
            
            JasperPrint print = JasperFillManager.fillReport(fileName,hm,con);
            
            JRExporter exporter = new net.sf.jasperreports.engine.export.JRPdfExporter();
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,outputName);
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
            exporter.exportReport();
            System.out.println("Created file: " + outputName);
            
        } catch (JRException ex) {
            Logger.getLogger(lets.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(lets.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void main(String[] args){
        new lets().make();
    }
    
   
}
