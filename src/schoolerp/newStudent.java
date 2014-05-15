package schoolerp;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class newStudent extends javax.swing.JFrame {

    Connection con = null;
    Boolean edit = false;
    
    public newStudent() {
        try {
            initComponents();
            this.setLocation(300,100);
            con = new dbConnection().returnCon();
            getClasses(con);
            getDorms(con);
        } catch (SQLException ex) {
            Logger.getLogger(newStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public newStudent(ResultSet rst) {
       try {
            initComponents();
            this.setLocation(300,100);
            con = new dbConnection().returnCon();
            getClasses(con);
            getDorms(con);
            edit = true;
            detailsLoad(rst);
        } catch (SQLException ex) {
            Logger.getLogger(newStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void detailsLoad(ResultSet rst) {
        try {
            String adm = null,
                    guardian = null,
                    residence = null,
                    address = null,
                    contact = null,
                    first = null,
                    middle = null,
                    last = null,
                    klass = null,
                    dorm = null,
                    sport = null;

            while (rst.next()) {
                adm = rst.getString("ADMNO");
                guardian = rst.getString("GUARDIANNAME");
                residence = rst.getString("RESIDENCE");
                address = rst.getString("ADDRESS");
                contact = rst.getString("CONTACTNO");
                first = rst.getString("FIRSTNAME");
                middle = rst.getString("SECONDNAME");
                last = rst.getString("THIRDNAME");
                klass = rst.getString("THECLASS");
                dorm = rst.getString("THEDORM");
                sport = rst.getString("SPORT");
            }

            this.txtAdmNo.setText(adm);
            this.txtGuardian.setText(guardian);
            this.txtResidence.setText(residence);

            this.txtAddress.setText(address);
            this.txtPhone.setText(contact);
            this.txtFirst.setText(first);
            this.txtMiddle.setText(middle);
            this.txtLast.setText(last);
            this.cboClass.setSelectedItem(klass);
            this.cboDorm.setSelectedItem(dorm);
            this.cboSports.setSelectedItem(sport);
            
        } catch (SQLException ex) {
            Logger.getLogger(newStudent.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
    private void getClasses(Connection c)throws SQLException{
        String klasName;
        String klas = "SELECT * FROM SCHOOL.classes";
        
        Statement stmtKlas = c.createStatement();
        ResultSet rstKlas = stmtKlas.executeQuery(klas);
        
        while(rstKlas.next()){
            klasName = rstKlas.getString("CLASSNAME");
            this.cboClass.addItem(klasName);
        }
    }
    private void getDorms(Connection d) throws SQLException{
        String dormName;
        String dorm = "SELECT * FROM SCHOOL.dorms";
        
        Statement stmtdorm = d.createStatement();
        ResultSet rstDorm = stmtdorm.executeQuery(dorm);
        
        while(rstDorm.next()){
            dormName = rstDorm.getString("DORMNAME");
            this.cboDorm.addItem(dormName);
        }                
    }
    private void saving(Connection s)throws SQLException{
        String save = "INSERT INTO SCHOOL.students (ADMNO,GUARDIANNAMES,RESIDENCE,"
                + "ADDRESS,CONTACTNO,FIRSTNAME,SECONDNAME,THIRDNAME,THECLASS,THEDORM,"
                + "SPORT) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pstmt = s.prepareStatement(save);
        boolean proceed = true;
        
        String adm = this.txtAdmNo.getText();
        String guardian = this.txtGuardian.getText();
        String residence = this.txtResidence.getText();
        String address = this.txtAddress.getText();
        String contact = this.txtPhone.getText();
        String first = this.txtFirst.getText();
        String middle = this.txtMiddle.getText();
        String last = this.txtLast.getText();
        String klass = this.cboClass.getSelectedItem().toString();
        String dorm = this.cboDorm.getSelectedItem().toString();
        String sport = this.cboSports.getSelectedItem().toString();
        
        String[] input = {adm,guardian,residence, address,contact,first,middle,
                            last,klass,dorm,sport};

        for (String in : input){
           if (in.equals("") == true){
              JOptionPane.showMessageDialog(null, "All Textboxes must be filled before you can proceed");
              proceed = false;
              break;  
            }
        }
        if (proceed ==true){
            pstmt.setString(1,adm);
            pstmt.setString(2,guardian);
            pstmt.setString(3,residence);
            pstmt.setString(4,address);
            pstmt.setString(5,contact);
            pstmt.setString(6,first);
            pstmt.setString(7,middle);
            pstmt.setString(8,last);
            pstmt.setString(9,klass);
            pstmt.setString(10,dorm);
            pstmt.setString(11,sport);  
        }
        int result = pstmt.executeUpdate();
            
        if (result != 0) {
            new admin().setVisible(true);
            this.setVisible(false);
        }
        
    }
    private void delete(Connection d){
        try {
            String id = this.txtAdmNo.getText();
            
            if(id == null){
                JOptionPane.showMessageDialog(null,"Please Enter the student ID");
            }
            
            String retrieve = "DELETE * FROM STUDENTS WHERE ADMNO = '"+id+"' ";
            
            Connection con = new dbConnection().returnCon();
            Statement stmt = con.createStatement();
            stmt.executeUpdate(retrieve);            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error Occured Please try again");
        }
        
    }
    private void update(){
        try {
            delete(con);
            saving(con);
        } catch (SQLException ex) {
            Logger.getLogger(newStudent.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblGurdians = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtGuardian = new javax.swing.JTextField();
        txtResidence = new javax.swing.JTextField();
        txtAddress = new javax.swing.JTextField();
        txtPhone = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtAdmNo = new javax.swing.JTextField();
        txtFirst = new javax.swing.JTextField();
        cboClass = new javax.swing.JComboBox();
        cboDorm = new javax.swing.JComboBox();
        cboSports = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtMiddle = new javax.swing.JTextField();
        txtLast = new javax.swing.JTextField();
        btnOK = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblGurdians.setText("GUARDIANS");

        jLabel1.setText("Guardian Names");

        jLabel2.setText("Residential Area");

        jLabel3.setText("Address");

        jLabel4.setText("Phone NO");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addComponent(lblGurdians))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(76, 76, 76)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtGuardian)
                            .addComponent(txtResidence)
                            .addComponent(txtAddress)
                            .addComponent(txtPhone, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblGurdians)
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtGuardian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtResidence, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(168, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel5.setText("STUDENTS");

        jLabel6.setText("Admission Number");

        jLabel7.setText("First Name");

        jLabel8.setText("Class");

        jLabel9.setText("Dorm");

        jLabel10.setText("Sport");

        cboSports.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Football", "Netball", "Volleyball", "Basketball", "LawnTennis", "TableTennis", "Badminton", "Rugby", "Handball", " " }));
        cboSports.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboSportsActionPerformed(evt);
            }
        });

        jLabel11.setText("Middle Name");

        jLabel12.setText("Last Name");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel12)
                            .addComponent(jLabel8)
                            .addComponent(jLabel11)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addGap(46, 46, 46)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtFirst, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboSports, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboDorm, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtLast, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                            .addComponent(cboClass, javax.swing.GroupLayout.Alignment.LEADING, 0, 178, Short.MAX_VALUE)
                            .addComponent(txtMiddle, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                            .addComponent(txtAdmNo))))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAdmNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFirst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMiddle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLast, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboClass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboDorm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboSports, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addContainerGap(106, Short.MAX_VALUE))
        );

        btnOK.setText("OK");
        btnOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOKActionPerformed(evt);
            }
        });

        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(598, Short.MAX_VALUE)
                .addComponent(btnOK, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 50, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnOK, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(btnExit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKActionPerformed
        if(edit == false){
        try {
            saving(con);
        } catch (SQLException ex) {
            Logger.getLogger(newStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
        }else{
            int p = JOptionPane.showConfirmDialog(null,"Are you sure you want to make the changes","Confirm",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
            if (p == JOptionPane.YES_OPTION){
                update();
                new students().setVisible(true);
                this.setVisible(false);
            }
            else{
                new students().setVisible(true);
                this.setVisible(false);
            }
        }
    }//GEN-LAST:event_btnOKActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        new students().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnExitActionPerformed

    private void cboSportsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboSportsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboSportsActionPerformed
/*private void saveImage() throws FileNotFoundException{
        int status = upload.showDialog(this,"Upload");
        
        //Process the results.
        if (status == JFileChooser.APPROVE_OPTION) {
            file = upload.getSelectedFile();
        }
        
        ImageIcon logo = new ImageIcon(file.getPath());
        
        if (logo != null) {
            if (logo.getIconWidth() > 90) {
                finalLogo = new ImageIcon(logo.getImage().
                                          getScaledInstance(90, -1,
                                                      Image.SCALE_DEFAULT));
            } else { //no need to miniaturize
                finalLogo = logo;
            }
        }
        
       this.lbLogo.setIcon(finalLogo);
        
       io = new FileInputStream(file);         
        
    }*/
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(newStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(newStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(newStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(newStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new newStudent().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnOK;
    private javax.swing.JComboBox cboClass;
    private javax.swing.JComboBox cboDorm;
    private javax.swing.JComboBox cboSports;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblGurdians;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtAdmNo;
    private javax.swing.JTextField txtFirst;
    private javax.swing.JTextField txtGuardian;
    private javax.swing.JTextField txtLast;
    private javax.swing.JTextField txtMiddle;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtResidence;
    // End of variables declaration//GEN-END:variables
}
