/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author Sergio Cruz
 */
public class Consultar extends javax.swing.JInternalFrame {

    /**
     * Creates new form ConsultarPaciente
     */
    public Consultar() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tbdPaneHistorias = new javax.swing.JTabbedPane();
        pndHistorias = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHistorias = new javax.swing.JTable();
        pndPacientes = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPacientes = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        pndServicios = new javax.swing.JPanel();
        tbdPaneServicio = new javax.swing.JTabbedPane();
        pndServicios1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblServicios = new javax.swing.JTable();
        pndHospitalizacion = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblHospitalizaciones = new javax.swing.JTable();
        pndLabs = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblLabs = new javax.swing.JTable();
        lblTotal = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();

        tbdPaneHistorias.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        tbdPaneHistorias.setToolTipText("");

        tblHistorias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Historia", "ID Medico", "ID Paciente", "Fecha", "Valor", "Descripción"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblHistorias);

        javax.swing.GroupLayout pndHistoriasLayout = new javax.swing.GroupLayout(pndHistorias);
        pndHistorias.setLayout(pndHistoriasLayout);
        pndHistoriasLayout.setHorizontalGroup(
            pndHistoriasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pndHistoriasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 532, Short.MAX_VALUE)
                .addContainerGap())
        );
        pndHistoriasLayout.setVerticalGroup(
            pndHistoriasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pndHistoriasLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
                .addContainerGap())
        );

        tbdPaneHistorias.addTab("Historias Clinicas", pndHistorias);

        tblPacientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Paciente", "Nombre", "Dirección", "Tipo Afiliación", "Telefono"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblPacientes);

        javax.swing.GroupLayout pndPacientesLayout = new javax.swing.GroupLayout(pndPacientes);
        pndPacientes.setLayout(pndPacientesLayout);
        pndPacientesLayout.setHorizontalGroup(
            pndPacientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pndPacientesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 532, Short.MAX_VALUE)
                .addContainerGap())
        );
        pndPacientesLayout.setVerticalGroup(
            pndPacientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pndPacientesLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        tbdPaneHistorias.addTab("Pacientes", pndPacientes);

        tblServicios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "ID Servicio", "ID Paciente", "Nombre Servicio"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tblServicios);

        javax.swing.GroupLayout pndServicios1Layout = new javax.swing.GroupLayout(pndServicios1);
        pndServicios1.setLayout(pndServicios1Layout);
        pndServicios1Layout.setHorizontalGroup(
            pndServicios1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pndServicios1Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 518, Short.MAX_VALUE)
                .addContainerGap())
        );
        pndServicios1Layout.setVerticalGroup(
            pndServicios1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pndServicios1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68))
        );

        tbdPaneServicio.addTab("Servicios", pndServicios1);

        tblHospitalizaciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "ID Servicio", "Fecha Salida"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tblHospitalizaciones);

        javax.swing.GroupLayout pndHospitalizacionLayout = new javax.swing.GroupLayout(pndHospitalizacion);
        pndHospitalizacion.setLayout(pndHospitalizacionLayout);
        pndHospitalizacionLayout.setHorizontalGroup(
            pndHospitalizacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pndHospitalizacionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 512, Short.MAX_VALUE)
                .addContainerGap())
        );
        pndHospitalizacionLayout.setVerticalGroup(
            pndHospitalizacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pndHospitalizacionLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tbdPaneServicio.addTab("Hospitalizaciones", pndHospitalizacion);

        tblLabs.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID_Servicio", "Codigo Examen", "Laboratorio", "Descripción", "Valor"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(tblLabs);

        javax.swing.GroupLayout pndLabsLayout = new javax.swing.GroupLayout(pndLabs);
        pndLabs.setLayout(pndLabsLayout);
        pndLabsLayout.setHorizontalGroup(
            pndLabsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pndLabsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 512, Short.MAX_VALUE)
                .addContainerGap())
        );
        pndLabsLayout.setVerticalGroup(
            pndLabsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pndLabsLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tbdPaneServicio.addTab("Laboratorios", pndLabs);

        javax.swing.GroupLayout pndServiciosLayout = new javax.swing.GroupLayout(pndServicios);
        pndServicios.setLayout(pndServiciosLayout);
        pndServiciosLayout.setHorizontalGroup(
            pndServiciosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pndServiciosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tbdPaneServicio))
        );
        pndServiciosLayout.setVerticalGroup(
            pndServiciosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pndServiciosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tbdPaneServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pndServicios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pndServicios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tbdPaneHistorias.addTab("Servicios", jPanel5);

        lblTotal.setText("Total:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(tbdPaneHistorias, javax.swing.GroupLayout.PREFERRED_SIZE, 660, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(272, 272, 272)
                        .addComponent(lblTotal)
                        .addGap(35, 35, 35)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(tbdPaneHistorias, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotal)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public JPanel getjPanel5() {
        return jPanel5;
    }

    public void setjPanel5(JPanel jPanel5) {
        this.jPanel5 = jPanel5;
    }

    public JScrollPane getjScrollPane1() {
        return jScrollPane1;
    }

    public void setjScrollPane1(JScrollPane jScrollPane1) {
        this.jScrollPane1 = jScrollPane1;
    }

    public JScrollPane getjScrollPane2() {
        return jScrollPane2;
    }

    public void setjScrollPane2(JScrollPane jScrollPane2) {
        this.jScrollPane2 = jScrollPane2;
    }

    public JScrollPane getjScrollPane3() {
        return jScrollPane3;
    }

    public void setjScrollPane3(JScrollPane jScrollPane3) {
        this.jScrollPane3 = jScrollPane3;
    }

    public JScrollPane getjScrollPane4() {
        return jScrollPane4;
    }

    public void setjScrollPane4(JScrollPane jScrollPane4) {
        this.jScrollPane4 = jScrollPane4;
    }

    public JScrollPane getjScrollPane5() {
        return jScrollPane5;
    }

    public void setjScrollPane5(JScrollPane jScrollPane5) {
        this.jScrollPane5 = jScrollPane5;
    }

    public JLabel getLblTotal() {
        return lblTotal;
    }

    public void setLblTotal(JLabel lblTotal) {
        this.lblTotal = lblTotal;
    }

    public JPanel getPndHistorias() {
        return pndHistorias;
    }

    public void setPndHistorias(JPanel pndHistorias) {
        this.pndHistorias = pndHistorias;
    }

    public JPanel getPndHospitalizacion() {
        return pndHospitalizacion;
    }

    public void setPndHospitalizacion(JPanel pndHospitalizacion) {
        this.pndHospitalizacion = pndHospitalizacion;
    }

    public JPanel getPndLabs() {
        return pndLabs;
    }

    public void setPndLabs(JPanel pndLabs) {
        this.pndLabs = pndLabs;
    }

    public JPanel getPndPacientes() {
        return pndPacientes;
    }

    public void setPndPacientes(JPanel pndPacientes) {
        this.pndPacientes = pndPacientes;
    }

    public JPanel getPndServicios() {
        return pndServicios;
    }

    public void setPndServicios(JPanel pndServicios) {
        this.pndServicios = pndServicios;
    }

    public JPanel getPndServicios1() {
        return pndServicios1;
    }

    public void setPndServicios1(JPanel pndServicios1) {
        this.pndServicios1 = pndServicios1;
    }

    public JTabbedPane getTbdPaneHistorias() {
        return tbdPaneHistorias;
    }

    public void setTbdPaneHistorias(JTabbedPane tbdPaneHistorias) {
        this.tbdPaneHistorias = tbdPaneHistorias;
    }

    public JTabbedPane getTbdPaneServicio() {
        return tbdPaneServicio;
    }

    public void setTbdPaneServicio(JTabbedPane tbdPaneServicio) {
        this.tbdPaneServicio = tbdPaneServicio;
    }

    public JTable getTblHistorias() {
        return tblHistorias;
    }

    public void setTblHistorias(JTable tblHistorias) {
        this.tblHistorias = tblHistorias;
    }

    public JTable getTblHospitalizaciones() {
        return tblHospitalizaciones;
    }

    public void setTblHospitalizaciones(JTable tblHospitalizaciones) {
        this.tblHospitalizaciones = tblHospitalizaciones;
    }

    public JTable getTblLabs() {
        return tblLabs;
    }

    public void setTblLabs(JTable tblLabs) {
        this.tblLabs = tblLabs;
    }

    public JTable getTblPacientes() {
        return tblPacientes;
    }

    public void setTblPacientes(JTable tblPacientes) {
        this.tblPacientes = tblPacientes;
    }

    public JTable getTblServicios() {
        return tblServicios;
    }

    public void setTblServicios(JTable tblServicios) {
        this.tblServicios = tblServicios;
    }

    public JTextField getTxtTotal() {
        return txtTotal;
    }

    public void setTxtTotal(JTextField txtTotal) {
        this.txtTotal = txtTotal;
    }
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JPanel pndHistorias;
    private javax.swing.JPanel pndHospitalizacion;
    private javax.swing.JPanel pndLabs;
    private javax.swing.JPanel pndPacientes;
    private javax.swing.JPanel pndServicios;
    private javax.swing.JPanel pndServicios1;
    private javax.swing.JTabbedPane tbdPaneHistorias;
    private javax.swing.JTabbedPane tbdPaneServicio;
    private javax.swing.JTable tblHistorias;
    private javax.swing.JTable tblHospitalizaciones;
    private javax.swing.JTable tblLabs;
    private javax.swing.JTable tblPacientes;
    private javax.swing.JTable tblServicios;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
