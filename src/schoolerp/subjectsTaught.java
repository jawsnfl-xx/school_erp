/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolerp;

import installationPackage.subjects;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class subjectsTaught extends javax.swing.JFrame {

    DefaultTableModel mode = null;
    
    public subjectsTaught() {
        initComponents();
        this.setLocation(300,150);
    }

    public subjectsTaught(String name){
        initComponents();
        mode = (DefaultTableModel)this.jtblSubjects.getModel();
        this.txtNames.setText(name);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtNames = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblSubjects = new javax.swing.JTable();
        btnOK = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("ENTER SUBJECT NAME AND TICK FORMS TAUGHT BY ");

        txtNames.setEditable(false);

        jtblSubjects.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null}
            },
            new String [] {
                "SUBJECT NAME", "FORM 1", "FORM 2", "FORM 3", "FORM 4"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jtblSubjects.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtblSubjectsKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jtblSubjects);
        jtblSubjects.getColumnModel().getColumn(0).setResizable(false);
        jtblSubjects.getColumnModel().getColumn(0).setPreferredWidth(150);
        jtblSubjects.getColumnModel().getColumn(1).setResizable(false);
        jtblSubjects.getColumnModel().getColumn(2).setResizable(false);
        jtblSubjects.getColumnModel().getColumn(3).setResizable(false);

        btnOK.setText("OK");
        btnOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOKActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnOK, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addGap(42, 42, 42)
                            .addComponent(txtNames, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 553, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNames, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnOK, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKActionPerformed
        save();
        new teachersLounge().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnOKActionPerformed

    private void jtblSubjectsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtblSubjectsKeyPressed
        if (evt.getKeyCode() == 10) { //When enter is pressed            
            addRow();
        } else if (evt.getKeyCode() == 127) { //when delete is pressed
            delete();
        }
    }//GEN-LAST:event_jtblSubjectsKeyPressed

    private void addRow() {               
        mode.addRow(new Object[]{null}); 
    }
    private void delete() {
        mode.removeRow(jtblSubjects.getSelectedRow());
    }
    private void save(){
        try {
            String saving = "INSERT INTO SCHOOL.subjects "
                    + "(SUBJECT,TEACHERNAME,FORM1,FORM2,FORM3,FORM4) VALUES(?,?,?,?,?,?)";
                    
            dbMethod(saving);
            
        } catch (SQLException ex) {
            Logger.getLogger(subjects.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void dbMethod(String sql) throws SQLException{
        Connection p = new dbConnection().returnCon();
        
        PreparedStatement pstmt = p.prepareStatement(sql);
        
        String tname = this.txtNames.getText();
        String sname;
        Boolean form1;
        Boolean form2;
        Boolean form3;
        Boolean form4;
        
        int count = mode.getRowCount();
        
        for(int i = 0; i < count; i++){
            sname = (String) mode.getValueAt(i,0);
            form1 = (Boolean) mode.getValueAt(i,1);
            form2 = (Boolean) mode.getValueAt(i,2);
            form3= (Boolean) mode.getValueAt(i,3);
            form4 = (Boolean) mode.getValueAt(i,4);
            
            pstmt.setString(1,sname);
            pstmt.setString(2,tname);
            pstmt.setBoolean(3,form1);
            pstmt.setBoolean(4,form2);
            pstmt.setBoolean(5,form3);
            pstmt.setBoolean(6,form4);
            
            int exec = pstmt.executeUpdate();
            
            if (exec == 0){
                JOptionPane.showMessageDialog(null,"Saving Error occurred please try again");
                break;                
            }
        }
    }
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
            java.util.logging.Logger.getLogger(subjectsTaught.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(subjectsTaught.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(subjectsTaught.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(subjectsTaught.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new subjectsTaught().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOK;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtblSubjects;
    private javax.swing.JTextField txtNames;
    // End of variables declaration//GEN-END:variables
}
