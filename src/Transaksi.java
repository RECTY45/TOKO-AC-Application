import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
public final class Transaksi extends javax.swing.JFrame {
    private DefaultTableModel model;
    private PreparedStatement stat,state;
    private ResultSet rs,res;
    koneksiPoly k = new koneksiPoly();
     transaksiPol data = new transaksiPol(); // Polimerisasi Dinamis Fungsi Akan Berjalan Pada Saat Runtime
    public Transaksi() {
        initComponents();
        k.connect();
        data.refreshTable();
        showCombo();
        idtxt.setVisible(false);
    }
      
        public void showCombo() {
            try {
            
      this.stat = k.getCon().prepareStatement("SELECT * FROM kasir");
      state = k.getCon().prepareStatement("SELECT * FROM unit");
      
      rs = stat.executeQuery();
      res = state.executeQuery();
      while(res.next()){
        String merk = res.getString("merk");
         String code = res.getString("kode_unit");
         String id = res.getString("id_unit");
        idunit.addItem("ID : "+ id+ " || "+" Kode Unit : " +code + " || "+ " Merk: " + merk);;
      }
      
      while (rs.next()) {
        String nama_kasir = rs.getString("nama_kasir");
        int no_kasir = rs.getInt("no_kasir");
        int ids = rs.getInt("id_kasir");
        nokasircombo.addItem(" ID: "+ids+" || "+"No. "+no_kasir +  " || "+ "Nama: " + nama_kasir);
      }   } catch (SQLException e) {
     JOptionPane.showMessageDialog(null, e.getMessage());
    }
}
    
    class transaksiPol {
    
        
        public void onDelete(){
            
              if(idtxt.getText().isEmpty()){
           JOptionPane.showMessageDialog(null,"DATA GAGAL DI HAPUS SILAHKAN KLIK DATA PADA TABEL YANG INGIN DI HAPUS !");
       }else{
            try {
            stat = k.getCon().prepareStatement("delete from transaksi where id_transaksi = ?");
            stat.setString(1, idtxt.getText());
            stat.executeUpdate();
            data.refreshTable();
           JOptionPane.showMessageDialog(null,"DATA BERHASIL DI HAPUS !");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
       }
        }
        
        public void onEdit(){
        idtxt.setText(model.getValueAt(tbltoko.getSelectedRow(), 0).toString());
        namapelanggantxt.setText(model.getValueAt(tbltoko.getSelectedRow(), 3).toString());
        alamattxt.setText(model.getValueAt(tbltoko.getSelectedRow(), 4).toString());
        unittxt.setText(model.getValueAt(tbltoko.getSelectedRow(), 7).toString());
        hargatxt.setText(model.getValueAt(tbltoko.getSelectedRow(), 8).toString());
        totaltxt.setText(model.getValueAt(tbltoko.getSelectedRow(), 9).toString());
        }
        
        public void onUpdate(){
             if(idtxt.getText().isEmpty() || namapelanggantxt.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"DATA TIDAK BOLEH KOSONG BRO!","Warning",JOptionPane.WARNING_MESSAGE);
        }else{
            try {     
            stat = k.getCon().prepareStatement("UPDATE transaksi SET unit=?,harga=?,total=?,nama_pelanggan=?,alamat=?,id_unit=?,id_kasir=? WHERE id_transaksi=?");
           stat.setString(1, unittxt.getText());
           stat.setString(2, hargatxt.getText());
           stat.setString(3, totaltxt.getText());
           stat.setString(4, namapelanggantxt.getText());
           stat.setString(5, alamattxt.getText());
           stat.setString(6,idunittxt.getText());
           stat.setString(7,idkasirtxtx.getText());
            stat.setString(8, idtxt.getText());
            stat.executeUpdate();
            data.refreshTable();
            JOptionPane.showMessageDialog(null, "DATA BERHASIL DI UBAH !");
           
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        }
        }
        
        public void onStore(){
            try { 
           stat = k.getCon().prepareStatement("INSERT INTO transaksi VALUES (?,?,?,?,?,?,?,?)");
           stat.setInt(1, 0);
           stat.setString(2, unittxt.getText());
           stat.setString(3, hargatxt.getText());
           stat.setString(4, totaltxt.getText());
           stat.setString(5, namapelanggantxt.getText());
           stat.setString(6, alamattxt.getText());
           stat.setString(7,idunittxt.getText());
           stat.setString(8,idkasirtxtx.getText());
           stat.executeUpdate();
           data.refreshTable();
            JOptionPane.showMessageDialog(null, "DATA BERHASIL DI INPUT !");
            
           
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
        }
        
        public void refreshTable(){
            model = new DefaultTableModel();
            model.addColumn("ID");
            model.addColumn("NO KASIR");
            model.addColumn("NAMA KASIR");
            model.addColumn("NAMA PELANGGAN");
            model.addColumn("ALAMAT ");
            model.addColumn("KODE UNIT");
            model.addColumn("MERK");
            model.addColumn("UNIT");
            model.addColumn("HARGA");
            model.addColumn("TOTAL");
           tbltoko.setModel(model);
           try {
               stat = k.getCon().prepareStatement("SELECT * FROM viewtransaksi");
               rs = stat.executeQuery();
               while(rs.next()){
                   Object[] data = {
                       rs.getString("id_transaksi"),
                       rs.getString("no_kasir"),
                       rs.getString("nama_kasir"),
                       rs.getString("nama_pelanggan"),
                       rs.getString("alamat"),
                       rs.getString("kode_unit"),
                       rs.getString("merk"),
                       rs.getString("unit"),
                       rs.getString("harga"),
                       rs.getString("total"),
                   };
                   model.addRow(data);
               }
        
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
    }
           unittxt.setText("");
           hargatxt.setText("");
           totaltxt.setText("");
           namapelanggantxt.setText("");
           alamattxt.setText("");
           idunittxt.setText("");
           idkasirtxtx.setText("");
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        hargatxt = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        totaltxt = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        namapelanggantxt = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        alamattxt = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbltoko = new javax.swing.JTable();
        btntambah = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnhapus = new javax.swing.JButton();
        unittxt = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        idunit = new javax.swing.JComboBox<>();
        nokasircombo = new javax.swing.JComboBox<>();
        idtxt = new javax.swing.JTextField();
        btncancel = new javax.swing.JButton();
        btnkasirs = new javax.swing.JButton();
        btnunits = new javax.swing.JButton();
        idunittxt = new javax.swing.JTextField();
        idkasirtxtx = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1294, 986));
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setText("TRANSAKSI");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(490, 40, 214, 44);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Unit                     :");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(180, 240, 120, 17);
        getContentPane().add(hargatxt);
        hargatxt.setBounds(340, 280, 570, 40);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Harga                  :");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(180, 290, 130, 20);
        getContentPane().add(totaltxt);
        totaltxt.setBounds(340, 330, 570, 40);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Total                    :");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(180, 340, 140, 20);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Nama Pelanggan :");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(180, 390, 130, 17);
        getContentPane().add(namapelanggantxt);
        namapelanggantxt.setBounds(340, 380, 570, 40);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Alamat                  :");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(180, 440, 130, 17);

        alamattxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alamattxtActionPerformed(evt);
            }
        });
        getContentPane().add(alamattxt);
        alamattxt.setBounds(340, 430, 570, 40);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Data Kasir :");
        getContentPane().add(jLabel11);
        jLabel11.setBounds(940, 310, 110, 17);

        tbltoko.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "", "", "", " ", " ", "   ", " ", " ", "   ", "  "
            }
        ));
        tbltoko.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbltokoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbltoko);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(0, 660, 1290, 330);

        btntambah.setText("TAMBAH");
        btntambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntambahActionPerformed(evt);
            }
        });
        getContentPane().add(btntambah);
        btntambah.setBounds(350, 570, 122, 40);

        btnEdit.setText("UBAH");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });
        getContentPane().add(btnEdit);
        btnEdit.setBounds(530, 570, 140, 40);

        btnhapus.setText("HAPUS");
        btnhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhapusActionPerformed(evt);
            }
        });
        getContentPane().add(btnhapus);
        btnhapus.setBounds(740, 570, 130, 40);
        getContentPane().add(unittxt);
        unittxt.setBounds(340, 230, 570, 40);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Data Unit :");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(940, 220, 130, 17);

        idunit.setFont(new java.awt.Font("Bahnschrift", 1, 14)); // NOI18N
        idunit.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Select --" }));
        idunit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idunitActionPerformed(evt);
            }
        });
        getContentPane().add(idunit);
        idunit.setBounds(940, 250, 340, 40);

        nokasircombo.setFont(new java.awt.Font("Bahnschrift", 1, 14)); // NOI18N
        nokasircombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Select --" }));
        getContentPane().add(nokasircombo);
        nokasircombo.setBounds(940, 340, 340, 40);
        getContentPane().add(idtxt);
        idtxt.setBounds(0, 590, 6, 22);

        btncancel.setText("CANCEL");
        btncancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelActionPerformed(evt);
            }
        });
        getContentPane().add(btncancel);
        btncancel.setBounds(0, 0, 110, 60);

        btnkasirs.setText("Data Kasir");
        btnkasirs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnkasirsActionPerformed(evt);
            }
        });
        getContentPane().add(btnkasirs);
        btnkasirs.setBounds(1130, 0, 160, 40);

        btnunits.setText("Data Unit");
        btnunits.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnunitsActionPerformed(evt);
            }
        });
        getContentPane().add(btnunits);
        btnunits.setBounds(1130, 60, 160, 40);

        idunittxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idunittxtActionPerformed(evt);
            }
        });
        getContentPane().add(idunittxt);
        idunittxt.setBounds(340, 180, 120, 40);
        getContentPane().add(idkasirtxtx);
        idkasirtxtx.setBounds(340, 480, 130, 40);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Check ID :");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(980, 190, 100, 20);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Masukkan Id Unit    :");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(180, 190, 150, 17);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Masukkan Id Kasir   :");
        getContentPane().add(jLabel10);
        jLabel10.setBounds(180, 480, 150, 30);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void alamattxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alamattxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_alamattxtActionPerformed

    private void tbltokoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbltokoMouseClicked
        data.onEdit();
    }//GEN-LAST:event_tbltokoMouseClicked

    private void idunitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idunitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idunitActionPerformed

    private void btnhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhapusActionPerformed
        // TODO add your handling code here:
        data.onDelete();
    }//GEN-LAST:event_btnhapusActionPerformed

    private void btntambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntambahActionPerformed
        // TODO add your handling code here:
        data.onStore();
    }//GEN-LAST:event_btntambahActionPerformed

    private void btncancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelActionPerformed
        login l = new login();
        l.setVisible(true);
        this.setVisible(false);
        dispose();
    }//GEN-LAST:event_btncancelActionPerformed

    private void btnkasirsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnkasirsActionPerformed
        // TODO add your handling code here:
        kasir k = new kasir();
        k.setVisible(true);
        this.setVisible(false);
        dispose();
    }//GEN-LAST:event_btnkasirsActionPerformed

    private void btnunitsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnunitsActionPerformed
        // TODO add your handling code here:
        unit u = new unit();
        u.setVisible(true);
        this.setVisible(false);
        dispose();
    }//GEN-LAST:event_btnunitsActionPerformed

    private void idunittxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idunittxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idunittxtActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        data.onUpdate();
    }//GEN-LAST:event_btnEditActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Transaksi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField alamattxt;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btncancel;
    private javax.swing.JButton btnhapus;
    private javax.swing.JButton btnkasirs;
    private javax.swing.JButton btntambah;
    private javax.swing.JButton btnunits;
    private javax.swing.JTextField hargatxt;
    private javax.swing.JTextField idkasirtxtx;
    private javax.swing.JTextField idtxt;
    private javax.swing.JComboBox<String> idunit;
    private javax.swing.JTextField idunittxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField namapelanggantxt;
    private javax.swing.JComboBox<String> nokasircombo;
    private javax.swing.JTable tbltoko;
    private javax.swing.JTextField totaltxt;
    private javax.swing.JTextField unittxt;
    // End of variables declaration//GEN-END:variables
}
