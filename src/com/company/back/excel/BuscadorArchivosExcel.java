package com.company.back.excel;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

import static com.company.ShowReadMeAddOn.getiLcyLucyEnv;

public class BuscadorArchivosExcel extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JButton btnSeleccionarArchivos, btnImportarDispositivo, btnImportarBlancos, btnVerFormatoExcel;
    private JTable jtTabla;
    private DefaultTableModel dtModeloTabla;
    private JPanel jpNorte1, jpCentro1, jpSur;
    private JLabel lbProgresoProceso;
    private JProgressBar jbBarraProgreso;
    private JScrollPane jsBarraDezlizamiento;
    private String[] sItems = {"Nombre archivo", "Unidad Amiga", "Unidad Hostil", "Otra Unidad", "Importar"};
    private ArrayList arrayRutas;
    private JFileChooser buscador = null;

    public BuscadorArchivosExcel() {
        arrayRutas = new ArrayList();
        setName("Buscador");
        setTitle("Importar - Visualizaci\u00f3n (" + getiLcyLucyEnv().getCombinedMapManager().getActiveMapComponent().getMapName() + ")");
        setResizable(false);
        jpCentro1 = new JPanel();
        jsBarraDezlizamiento = new JScrollPane();
        jtTabla = new JTable();
        jpNorte1 = new JPanel();
        btnSeleccionarArchivos = new JButton();
        btnImportarDispositivo = new JButton();
        btnImportarBlancos = new JButton();
        btnVerFormatoExcel = new JButton();
        jpSur = new JPanel();
        jbBarraProgreso = new JProgressBar();
        lbProgresoProceso = new JLabel();
        dtModeloTabla = new DefaultTableModel(sItems, 0) {
            Class[] tipoDatos = new Class[]{String.class, Integer.class, Integer.class, Integer.class, Boolean.class};
            boolean[] configPuedenSerEditables = new boolean[]{false, false, false, false, true};

            public Class getColumnClass(int columnIndex) {
                return tipoDatos[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return configPuedenSerEditables[columnIndex];
            }
        };

        DefaultTableCellRenderer dtCellRender = new DefaultTableCellRenderer();
        dtCellRender.setHorizontalAlignment(SwingConstants.CENTER);
        jtTabla.setModel(dtModeloTabla);
        jtTabla.setFont(new Font("Arial", 1, 12));
        jtTabla.setSelectionBackground(new Color(87, 87, 87));
        jtTabla.setSelectionForeground(new Color(75, 255, 26));
        jtTabla.setFillsViewportHeight(true);
        jtTabla.setOpaque(true);
        for (int i = 0; i < jtTabla.getColumnModel().getColumnCount() - 1; i++) {
            jtTabla.getColumnModel().getColumn(i).setCellRenderer(dtCellRender);
        }
        jbBarraProgreso.setStringPainted(true);
        jbBarraProgreso.setForeground(new Color(0x303030));
        jbBarraProgreso.setFont(new Font("Arial", Font.BOLD, 14));
        btnSeleccionarArchivos.setText("Buscar dispositivo");
        btnImportarDispositivo.setText("Importar dispositivo");
        btnImportarBlancos.setText("Importar blancos");
        btnVerFormatoExcel.setText("Ver formato");
        lbProgresoProceso.setText(" Progreso");
        lbProgresoProceso.setFont(new Font("Arial", Font.BOLD, 14));
        btnSeleccionarArchivos = new JButton("Seleccionar archivos");
        jsBarraDezlizamiento.setViewportView(jtTabla);

        jpCentro1.setLayout(new BorderLayout());
        jpCentro1.setPreferredSize(new Dimension(655, 300));
        jpCentro1.add(jsBarraDezlizamiento, BorderLayout.CENTER);
        getContentPane().add(jpCentro1, BorderLayout.CENTER);


        jpNorte1.setLayout(new GridLayout());
        jpNorte1.setPreferredSize(new Dimension(30, 30));
        jpNorte1.add(btnSeleccionarArchivos);
        jpNorte1.add(btnImportarDispositivo);
        jpNorte1.add(btnImportarBlancos);
        jpNorte1.add(btnVerFormatoExcel);
        getContentPane().add(jpNorte1, BorderLayout.PAGE_START);


        jpSur.setLayout(new BorderLayout());
        jpSur.setPreferredSize(new Dimension(70, 70));
        jpSur.add(jbBarraProgreso, BorderLayout.CENTER);
        jpSur.add(lbProgresoProceso, BorderLayout.PAGE_START);
        getContentPane().add(jpSur, BorderLayout.PAGE_END);
        pack();

        btnImportarDispositivo.addActionListener(this);
        btnImportarBlancos.addActionListener(this);
        btnSeleccionarArchivos.addActionListener(this);
        btnVerFormatoExcel.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btnVerFormatoExcel)) {
            guardarFormatoExcel();
        } else if (e.getSource().equals(btnSeleccionarArchivos)) {
            abrirBuscador();
        } else if (e.getSource().equals(btnImportarDispositivo)) {
            iniciarProcesoPrincipalImpotar(VerificarExcel.DISPOSITIVO);
        } else if (e.getSource().equals(btnImportarBlancos)) {
            iniciarProcesoPrincipalImpotar(VerificarExcel.BLANCOS);
        }

    }

    public void iniciarProcesoPrincipalImpotar(String Dispositivo_O_Blanco) {
        if (hayExcelParaLeer()) {
            reiniciarBarra();
            VerificarExcel leerExcel = new VerificarExcel(rutasFinales());
            leerExcel.setjTable(getJtTabla());
            leerExcel.vericar_E_IniciarCopiadoDelDispositivo(Dispositivo_O_Blanco);
            if (leerExcel.validoParaFraficar()) {
                if (Dispositivo_O_Blanco.equalsIgnoreCase(VerificarExcel.DISPOSITIVO)) {
                    GraficarUnidades graficarUnidades = new GraficarUnidades(leerExcel.getObjectsExcel());
                    graficarUnidades.setJbBarraProgreso(getJbBarraProgreso());
                    graficarUnidades.setLbProgreso(getLbProgresoProceso());
                    graficarUnidades.agregarMS2525cParaCapaSelecionada();
                } else if (Dispositivo_O_Blanco.equalsIgnoreCase(VerificarExcel.BLANCOS)) {
                    GraficarBlancos graficarBlancos = new GraficarBlancos(leerExcel.getObjectsExcel());
                    graficarBlancos.setJbBarraProgreso(getJbBarraProgreso());
                    graficarBlancos.setLbProgreso(getLbProgresoProceso());
                    graficarBlancos.agregarMS2525cParaCapaSelecionada();
                }

            }


        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un documento de Excel valido");
        }
    }

    public boolean exiteRuta(String absolutePath) {
        for (int i = 0; i < getArrayRutas().size(); i++) {
            if (getArrayRutas().get(i).equals(absolutePath)) {
                return true;
            }
        }
        return false;
    }

    public void abrirBuscador() {
        buscador = new JFileChooser();
        buscador.setFileSelectionMode(JFileChooser.FILES_ONLY);
        buscador.setMultiSelectionEnabled(true);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Hojas de Excel", "xlsx");
        buscador.setFileFilter(filter);
        int returnSelection = buscador.showOpenDialog(this);
        if (returnSelection == JFileChooser.APPROVE_OPTION) {
            for (int i = 0; i < buscador.getSelectedFiles().length; i++) {
                if (!exiteRuta(buscador.getSelectedFiles()[i].getAbsolutePath())) {
                    getArrayRutas().add(buscador.getSelectedFiles()[i].getAbsolutePath());
                    getDtModeloTabla().addRow(new Object[]{buscador.getSelectedFiles()[i].getName(), 0, 0, 0, true});
                }


            }

        }
    }

    public boolean guardarFormatoExcel() {

//Se verifica la existencia de la carpeta documentos, y garantizo que este para recibir el archivo Excel
        String rutaDocuments = System.getProperty("user.home") + File.separatorChar + "My Documents";
        File vulcano = new File(rutaDocuments + "\\Vulcano");
        if (!vulcano.exists()) {
            vulcano.mkdirs();
        }

        try {
            InputStream in = getClass().getResourceAsStream("/amarillo.jpg");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));


            FileInputStream inputStream = new FileInputStream(new File(getClass().getResource("/Formato.xlsx").getPath()));
            Workbook libro = null;
            try {
                libro = new XSSFWorkbook(OPCPackage.openOrCreate(new File(getClass().getResource("/Formato.xlsx").getPath())));
            } catch (InvalidFormatException e) {
                e.printStackTrace();
            }
            File archivoNuevo = new File("Formato Excel cargar dispositivo.xlsx");
            FileOutputStream salida = new FileOutputStream(archivoNuevo);
            libro.write(salida);
            salida.close();
            Desktop.getDesktop().open(archivoNuevo);
        } catch (IOException e) {
            return false;
        }


        return true;
    }

    public ArrayList rutasFinales() {
        ArrayList list = new ArrayList();
        for (int i = 0; i < getJtTabla().getRowCount(); i++) {
            Boolean seleccion = (Boolean) getDtModeloTabla().getValueAt(i, 4);
            if (seleccion == Boolean.TRUE) {
                list.add(getArrayRutas().get(i));
            }
        }
        return list;
    }

    public void reiniciarBarra() {
        getJbBarraProgreso().setForeground(new Color(0xF6393939, true));
        getLbProgresoProceso().setText(" Progreso");
        getJbBarraProgreso().setValue(0);
        getJbBarraProgreso().setString("0%");
    }

    public boolean hayExcelParaLeer() {
        ArrayList list = new ArrayList();
        for (int i = 0; i < getJtTabla().getRowCount(); i++) {
            Boolean seleccion = (Boolean) getDtModeloTabla().getValueAt(i, 4);
            if (seleccion == Boolean.TRUE) {
                return true;
            }
        }
        return false;
    }

    public void removerTodasFilas() {
        for (int i = getJtTabla().getRowCount() - 1; i >= 0; i--) {
            getDtModeloTabla().removeRow(i);
            getArrayRutas().removeAll(getArrayRutas());
        }

    }

    //GET Y SET_________________________________________________________________________________________________________
    public ArrayList getArrayRutas() {
        return arrayRutas;
    }

    public void setArrayRutas(ArrayList arrayRutas) {
        this.arrayRutas = arrayRutas;
    }

    public JTable getJtTabla() {
        return jtTabla;
    }

    public void setJtTabla(JTable jtTabla) {
        this.jtTabla = jtTabla;
    }

    public DefaultTableModel getDtModeloTabla() {
        return dtModeloTabla;
    }

    public void setDtModeloTabla(DefaultTableModel dtModeloTabla) {
        this.dtModeloTabla = dtModeloTabla;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public JButton getBtnSeleccionarArchivos() {
        return btnSeleccionarArchivos;
    }

    public void setBtnSeleccionarArchivos(JButton btnSeleccionarArchivos) {
        this.btnSeleccionarArchivos = btnSeleccionarArchivos;
    }

    public JButton getBtnImportarDispositivo() {
        return btnImportarDispositivo;
    }

    public void setBtnImportarDispositivo(JButton btnImportarDispositivo) {
        this.btnImportarDispositivo = btnImportarDispositivo;
    }

    public JPanel getJpNorte1() {
        return jpNorte1;
    }

    public void setJpNorte1(JPanel jpNorte1) {
        this.jpNorte1 = jpNorte1;
    }

    public JPanel getJpCentro1() {
        return jpCentro1;
    }

    public void setJpCentro1(JPanel jpCentro1) {
        this.jpCentro1 = jpCentro1;
    }

    public JPanel getJpSur() {
        return jpSur;
    }

    public void setJpSur(JPanel jpSur) {
        this.jpSur = jpSur;
    }

    public JLabel getLbProgresoProceso() {
        return lbProgresoProceso;
    }

    public void setLbProgresoProceso(JLabel lbProgresoProceso) {
        this.lbProgresoProceso = lbProgresoProceso;
    }

    public JProgressBar getJbBarraProgreso() {
        return jbBarraProgreso;
    }

    public void setJbBarraProgreso(JProgressBar jbBarraProgreso) {
        this.jbBarraProgreso = jbBarraProgreso;
    }

    public JScrollPane getJsBarraDezlizamiento() {
        return jsBarraDezlizamiento;
    }

    public void setJsBarraDezlizamiento(JScrollPane jsBarraDezlizamiento) {
        this.jsBarraDezlizamiento = jsBarraDezlizamiento;
    }

    public String[] getsItems() {
        return sItems;
    }

    public void setsItems(String[] sItems) {
        this.sItems = sItems;
    }

    public JFileChooser getBuscador() {
        return buscador;
    }

    public void setBuscador(JFileChooser buscador) {
        this.buscador = buscador;
    }
}
