package database;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.JSpinner;

public class GlavnaForma extends javax.swing.JFrame {
    public void prikaziCitaoce(){

    try{

        Connection con = Konekcija.connect();

        String sql = "SELECT * FROM citalac";

        PreparedStatement ps = con.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        DefaultTableModel model =
                (DefaultTableModel) tblCitaoci.getModel();

        model.setRowCount(0);

        while(rs.next()){

            model.addRow(new Object[]{

                rs.getInt("CitalacID"),
                rs.getString("MaticniBroj"),
                rs.getString("Ime"),
                rs.getString("Prezime"),
                rs.getString("Adresa")

            });

        }

    }catch(Exception e){

        System.out.println(e);

    }

}
    public void ucitajCombo(){

    try{

        Connection con = Konekcija.connect();

        String sql = "SELECT * FROM citalac";

        PreparedStatement ps = con.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        while(rs.next()){

            cmbCitalac.addItem(
                    rs.getInt("CitalacID")
                    + " - " +
                    rs.getString("Ime")
                    + " " +
                    rs.getString("Prezime")
            );

        }

    }catch(Exception e){

        System.out.println(e);

    }

}
public void pregledIznajmljivanja(){

    try{

        Connection con = Konekcija.connect();

        String selected =
                cmbCitalac.getSelectedItem().toString();

        String id =
                selected.split("-")[0].trim();

        String sql =
        "SELECT CONCAT(citalac.Ime,' ',citalac.Prezime) AS Citalac, " +
        "YEAR(na_citanju.DatumUzimanja) AS Godina, " +
        "COUNT(*) AS BrojIznajmljivanja, " +
        "SUM(CASE WHEN na_citanju.DatumVracanja IS NULL THEN 1 ELSE 0 END) AS NijeVracen " +
        "FROM na_citanju " +
        "INNER JOIN citalac " +
        "ON na_citanju.CitalacID = citalac.CitalacID " +
        "WHERE na_citanju.CitalacID=? " +
        "AND YEAR(na_citanju.DatumUzimanja) >= ? " +
        "AND YEAR(na_citanju.DatumUzimanja) <= ? " +
        "GROUP BY YEAR(na_citanju.DatumUzimanja)";

        PreparedStatement ps =
                con.prepareStatement(sql);

        ps.setString(1, id);
        ps.setInt(2, (Integer) spOd.getValue());
        ps.setInt(3, (Integer) spDo.getValue());

        ResultSet rs = ps.executeQuery();

        DefaultTableModel model =
                (DefaultTableModel) tblPregled.getModel();

        model.setRowCount(0);

        while(rs.next()){

            model.addRow(new Object[]{

                rs.getString("Citalac"),
                rs.getInt("Godina"),
                rs.getInt("BrojIznajmljivanja"),
                rs.getInt("NijeVracen")

            });

        }
        
        if(model.getRowCount() == 0){

    model.addRow(new Object[]{

        "Nema podataka",
        "-",
        0,
        0

    });

}

    }catch(Exception e){

        System.out.println(e);

    }

}
public void prikaziGrafik(){
  if(cmbCitalac.getSelectedItem() == null){
    return;
}
    try{

        Connection con = Konekcija.connect();

        String selected =
                cmbCitalac.getSelectedItem().toString();

        String id =
                selected.split("-")[0].trim();

String sql =
"SELECT YEAR(DatumUzimanja) AS Godina, " +
"COUNT(*) AS Broj " +
"FROM na_citanju " +
"WHERE CitalacID=? " +
"AND YEAR(DatumUzimanja) >= ? " +
"AND YEAR(DatumUzimanja) <= ? " +
"GROUP BY YEAR(DatumUzimanja)";

        PreparedStatement ps =
                con.prepareStatement(sql);
        ps.setInt(2, (Integer) spOd.getValue());
ps.setInt(3, (Integer) spDo.getValue());

        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();

        DefaultCategoryDataset dataset =
                new DefaultCategoryDataset();

        while(rs.next()){

            dataset.setValue(

                    rs.getInt("Broj"),
                    "Iznajmljivanja",
                    rs.getString("Godina")

            );

        }

        JFreeChart chart =
                ChartFactory.createBarChart(

                        "Pregled iznajmljivanja",
                        "Godina",
                        "Broj",
                        dataset

                );

        ChartPanel cp = new ChartPanel(chart);
        cp.setPreferredSize(new java.awt.Dimension(390, 350));

        panelGrafik.removeAll();

        panelGrafik.setLayout(
                new java.awt.BorderLayout());

        panelGrafik.add(cp);

        panelGrafik.validate();
        
        if(dataset.getRowCount() == 0){

    dataset.setValue(0,
            "Iznajmljivanja",
            "Nema podataka");

}

    }catch(Exception e){

        System.out.println(e);

    }

}
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(GlavnaForma.class.getName());

    /**
     * Creates new form GlavnaForma
     */
    public GlavnaForma() {
        initComponents();
        
        spOd.setEditor(new JSpinner.NumberEditor(spOd, "#"));
spDo.setEditor(new JSpinner.NumberEditor(spDo, "#"));
        
         prikaziCitaoce();
        ucitajCombo();
        pregledIznajmljivanja();
        prikaziGrafik();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnUpisi = new javax.swing.JButton();
        btnIzadji = new javax.swing.JButton();
        txtBrojKartice = new javax.swing.JTextField();
        txtJMBG = new javax.swing.JTextField();
        txtIme = new javax.swing.JTextField();
        txtPrezime = new javax.swing.JTextField();
        txtAdresa = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCitaoci = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cmbCitalac = new javax.swing.JComboBox<>();
        spOd = new javax.swing.JSpinner();
        spDo = new javax.swing.JSpinner();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblPregled = new javax.swing.JTable();
        btnPrikazi = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        panelGrafik = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanel1ComponentShown(evt);
            }
        });

        jLabel1.setText("Broj clanske karte");

        jLabel2.setText("JMBG");

        jLabel3.setText("Ime");

        jLabel4.setText("Prezime");

        jLabel5.setText("Adresa");

        btnUpisi.setText("Upisi citaoca");
        btnUpisi.addActionListener(this::btnUpisiActionPerformed);

        btnIzadji.setText("Izadji");
        btnIzadji.addActionListener(this::btnIzadjiActionPerformed);

        tblCitaoci.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Clanska karta", "JMBG", "Ime", "Prezime", "Adresa"
            }
        ));
        jScrollPane2.setViewportView(tblCitaoci);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBrojKartice, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtJMBG, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                            .addComponent(txtIme)
                            .addComponent(txtPrezime)
                            .addComponent(txtAdresa)))
                    .addComponent(btnIzadji, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnUpisi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(145, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtBrojKartice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtJMBG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtIme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtPrezime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtAdresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addComponent(btnUpisi, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnIzadji, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(52, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Citaoci", jPanel1);

        jLabel6.setText("Vremenski period");

        jLabel7.setText("Citalac");

        jLabel8.setText("od");

        cmbCitalac.addActionListener(this::cmbCitalacActionPerformed);

        spOd.setModel(new javax.swing.SpinnerNumberModel(2015, 2000, 2030, 1));
        spOd.addChangeListener(this::spOdStateChanged);

        spDo.setModel(new javax.swing.SpinnerNumberModel(2025, 2000, 2030, 1));

        jLabel9.setText("do");

        tblPregled.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Citalac", "Godina", "Broj iznajmljivanja", "Nije vracen"
            }
        ));
        jScrollPane3.setViewportView(tblPregled);

        btnPrikazi.setText("Prikazi");
        btnPrikazi.addActionListener(this::btnPrikaziActionPerformed);

        jButton2.setText("Izadji");
        jButton2.addActionListener(this::jButton2ActionPerformed);

        javax.swing.GroupLayout panelGrafikLayout = new javax.swing.GroupLayout(panelGrafik);
        panelGrafik.setLayout(panelGrafikLayout);
        panelGrafikLayout.setHorizontalGroup(
            panelGrafikLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 305, Short.MAX_VALUE)
        );
        panelGrafikLayout.setVerticalGroup(
            panelGrafikLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnPrikazi, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(150, 150, 150)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(37, 37, 37)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(spOd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(spDo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(37, 37, 37)
                                .addComponent(cmbCitalac, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(18, 18, 18)
                .addComponent(panelGrafik, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(90, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8)
                            .addComponent(spOd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spDo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(cmbCitalac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panelGrafik, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPrikazi)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Pregled iznajmljivanja", jPanel4);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText("APLIKACIJA ZA EVIDENCIJU BIBLIOTEKE\n\nOva aplikacija služi za evidenciju članova biblioteke i pregled iznajmljivanja knjiga.\n\nMogućnosti aplikacije:\n- pregled svih čitalaca\n- unos novih čitalaca\n- pregled statistike iznajmljivanja\n- prikaz broja nevraćenih knjiga\n\nUPUTSTVO ZA KORIŠĆENJE\n\n1. U prvom tabu moguće je pregledati i unositi nove čitaoce.\n2. U drugom tabu bira se čitalac i vremenski period za pregled statistike iznajmljivanja.\n3. Dugme \"Prikaži\" prikazuje statistiku za izabranog čitaoca.\n4. Dugme \"Izađi\" zatvara aplikaciju.");
        jScrollPane4.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 806, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(95, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("O aplikaciji", jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel1ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel1ComponentShown

    }//GEN-LAST:event_jPanel1ComponentShown

    private void btnUpisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpisiActionPerformed
        try{

    Connection con = Konekcija.connect();

    String sql =
    "INSERT INTO citalac(MaticniBroj,Ime,Prezime,Adresa)"
    + " VALUES(?,?,?,?)";

    PreparedStatement ps = con.prepareStatement(sql);

    ps.setString(1, txtJMBG.getText());
    ps.setString(2, txtIme.getText());
    ps.setString(3, txtPrezime.getText());
    ps.setString(4, txtAdresa.getText());

    ps.executeUpdate();
    
    String sqlID =
"SELECT MAX(CitalacID) AS ID FROM citalac";

PreparedStatement psID =
        con.prepareStatement(sqlID);

ResultSet rsID = psID.executeQuery();

int noviID = 0;

if(rsID.next()){

    noviID = rsID.getInt("ID");

}

String sqlUnos =
"INSERT INTO na_citanju(CitalacID,KnjigaID,DatumUzimanja,DatumVracanja) VALUES " +
"(?,?,?,?), (?,?,?,?), (?,?,?,?)";

PreparedStatement psUnos =
        con.prepareStatement(sqlUnos);

psUnos.setInt(1, noviID);
psUnos.setInt(2, 1);
psUnos.setString(3, "2022-02-10");
psUnos.setString(4, "2022-02-20");

psUnos.setInt(5, noviID);
psUnos.setInt(6, 2);
psUnos.setString(7, "2023-05-01");
psUnos.setString(8, null);

psUnos.setInt(9, noviID);
psUnos.setInt(10, 3);
psUnos.setString(11, "2024-01-15");
psUnos.setString(12, "2024-01-30");

psUnos.executeUpdate();
    

    JOptionPane.showMessageDialog(this,
            "Uspešan upis");

    prikaziCitaoce();
    
    cmbCitalac.removeAllItems();
ucitajCombo();

}catch(Exception e){

    System.out.println(e);

}
    }//GEN-LAST:event_btnUpisiActionPerformed

    private void btnIzadjiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIzadjiActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnIzadjiActionPerformed

    private void btnPrikaziActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrikaziActionPerformed
        pregledIznajmljivanja();
        prikaziGrafik();
    }//GEN-LAST:event_btnPrikaziActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void spOdStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spOdStateChanged

    }//GEN-LAST:event_spOdStateChanged

    private void cmbCitalacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCitalacActionPerformed

    }//GEN-LAST:event_cmbCitalacActionPerformed

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new GlavnaForma().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIzadji;
    private javax.swing.JButton btnPrikazi;
    private javax.swing.JButton btnUpisi;
    private javax.swing.JComboBox<String> cmbCitalac;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JPanel panelGrafik;
    private javax.swing.JSpinner spDo;
    private javax.swing.JSpinner spOd;
    private javax.swing.JTable tblCitaoci;
    private javax.swing.JTable tblPregled;
    private javax.swing.JTextField txtAdresa;
    private javax.swing.JTextField txtBrojKartice;
    private javax.swing.JTextField txtIme;
    private javax.swing.JTextField txtJMBG;
    private javax.swing.JTextField txtPrezime;
    // End of variables declaration//GEN-END:variables
}
