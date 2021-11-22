package administrarMunicion.C_nuevoRegistro.vista;


import administrarMunicion.C_nuevoRegistro.controlador.AbrirVentanaPrincipal;
import administrarMunicion.C_nuevoRegistro.controlador.EventoBotonNuevoTipoMuni;
import administrarMunicion.C_nuevoRegistro.controlador.EventoBotonRegistrar;
import administrarMunicion.C_nuevoRegistro.controlador.EventoTeclaSiguienteComponente;
import administrarMunicion.C_nuevoRegistro.modelo.Jtable;
import administrarMunicion.C_nuevoRegistro.modelo.TituloTabla;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.KeyEvent;

public class NuevoRegistro extends JFrame {

    private JComboBox<String> cbTipoMuni;
    private JLabel jLabel3;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JTextArea jTextArea1;
    private JSeparator jsDivisor;
    private JTable jtResgistro;
    private JLabel lbTitulo;
    private JPanel pnPropiedades;
    private JPanel pnTabla;
    private JTextField textCantidad;
    private JTextField textFecha;
    private JTextField textLote;
    private JButton btnNuevoTipoMuni;
    private JButton btnRegistrar;
    private JTextField textUbicacion;
    public static NuevoRegistro nuevoRegistro;

    public NuevoRegistro() {
        setExtendedState(MAXIMIZED_BOTH);
        initComponents();
    }


    private void initComponents() {
        GridBagConstraints gridBagConstraints;
        jScrollPane1 = new JScrollPane();
        jTextArea1 = new JTextArea();
        pnPropiedades = new JPanel();
        textLote = new JTextField();
        btnNuevoTipoMuni = new JButton();
        lbTitulo = new JLabel();
        cbTipoMuni = new JComboBox<>();
        jLabel3 = new JLabel();
        textCantidad = new JTextField();
        jsDivisor = new JSeparator();
        btnRegistrar = new JButton();
        textUbicacion = new JTextField();
        textFecha = new JTextField();
        pnTabla = new JPanel();
        jScrollPane2 = new JScrollPane();
        jtResgistro = new Jtable(TituloTabla.Nuevo_registro, new Object[0][0]);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.LINE_AXIS));

        pnPropiedades.setBackground(new Color(1, 135, 134));
        pnPropiedades.setMaximumSize(new Dimension(320, 1920));
        pnPropiedades.setLayout(new GridBagLayout());

        textLote.setBackground(new Color(1, 135, 134));
        textLote.setFont(new Font("Roboto", 1, 14)); // NOI18N
        textLote.setForeground(new Color(255, 255, 255));
        textLote.setBorder(BorderFactory.createTitledBorder(null, "LOTE", TitledBorder.LEFT, TitledBorder.ABOVE_TOP, new Font("Roboto", 1, 14), new Color(255, 255, 255))); // NOI18N
        textLote.setMaximumSize(new Dimension(300, 30));
        textLote.setMinimumSize(new Dimension(300, 30));
        textLote.setPreferredSize(new Dimension(300, 30));

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 40;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(18, 12, 0, 12);
        pnPropiedades.add(textLote, gridBagConstraints);

        btnNuevoTipoMuni.setBackground(new Color(1, 135, 134));
        btnNuevoTipoMuni.setFont(new Font("Roboto", 1, 14)); // NOI18N
        btnNuevoTipoMuni.setForeground(new Color(255, 255, 255));
        btnNuevoTipoMuni.setText("NUEVO TIPO MUNICIÓN");
        btnNuevoTipoMuni.setMaximumSize(new Dimension(300, 30));
        btnNuevoTipoMuni.setMinimumSize(new Dimension(300, 30));
        btnNuevoTipoMuni.setPreferredSize(new Dimension(300, 30));

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 14;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(18, 12, 0, 12);
        pnPropiedades.add(btnNuevoTipoMuni, gridBagConstraints);

        lbTitulo.setBackground(new Color(255, 255, 255));
        lbTitulo.setFont(new Font("Roboto", 1, 24)); // NOI18N
        lbTitulo.setForeground(new Color(255, 255, 255));
        lbTitulo.setText("CREAR NUEVO REGISTRO");
        lbTitulo.setMaximumSize(new Dimension(300, 30));
        lbTitulo.setMinimumSize(new Dimension(300, 30));
        lbTitulo.setPreferredSize(new Dimension(300, 30));

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(72, 12, 0, 12);
        pnPropiedades.add(lbTitulo, gridBagConstraints);

        cbTipoMuni.setBackground(new Color(1, 135, 134));
        cbTipoMuni.setFont(new Font("Roboto", 1, 14)); // NOI18N
        cbTipoMuni.setForeground(new Color(255, 255, 255));
        cbTipoMuni.setMaximumRowCount(5);
        cbTipoMuni.setModel(new DefaultComboBoxModel<>(new String[]{"105 MM HE M1", "105 MM ER 50 BB","105 MM FUMIGENAS  M64", "105 MM ILUMINACION  M64","120 MM PRERAYADAS M209","120 MM  PARA MORTERO BRANDT"}));
        cbTipoMuni.setAlignmentX(1.5F);
        cbTipoMuni.setAlignmentY(1.5F);
        cbTipoMuni.setBorder(BorderFactory.createTitledBorder(null, "", TitledBorder.LEFT, TitledBorder.ABOVE_TOP, new Font("Roboto", 1, 14), new Color(255, 255, 255))); // NOI18N
        cbTipoMuni.setMaximumSize(new Dimension(300, 30));
        cbTipoMuni.setMinimumSize(new Dimension(300, 30));
        cbTipoMuni.setPreferredSize(new Dimension(300, 30));

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 42;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(34, 12, 0, 12);
        pnPropiedades.add(cbTipoMuni, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        pnPropiedades.add(jLabel3, gridBagConstraints);

        textCantidad.setBackground(new Color(1, 135, 134));
        textCantidad.setFont(new Font("Roboto", 1, 14)); // NOI18N
        textCantidad.setForeground(new Color(255, 255, 255));
        textCantidad.setBorder(BorderFactory.createTitledBorder(null, "CANTIDAD", TitledBorder.LEFT, TitledBorder.ABOVE_TOP, new Font("Roboto", 1, 14), new Color(255, 255, 255))); // NOI18N
        textCantidad.setMaximumSize(new Dimension(300, 30));
        textCantidad.setMinimumSize(new Dimension(300, 30));
        textCantidad.setName(""); // NOI18N
        textCantidad.setPreferredSize(new Dimension(300, 30));

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 40;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(18, 12, 0, 12);
        pnPropiedades.add(textCantidad, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.ipadx = 308;
        gridBagConstraints.ipady = 11;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(18, 13, 62, 12);
        pnPropiedades.add(jsDivisor, gridBagConstraints);

        btnRegistrar.setBackground(new Color(1, 135, 134));
        btnRegistrar.setFont(new Font("Roboto", 1, 14)); // NOI18N
        btnRegistrar.setForeground(new Color(255, 255, 255));
        btnRegistrar.setText("REGISTAR");
        btnRegistrar.setMaximumSize(new Dimension(300, 30));
        btnRegistrar.setMinimumSize(new Dimension(300, 30));
        btnRegistrar.setPreferredSize(new Dimension(300, 30));

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 14;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(18, 12, 0, 12);
        pnPropiedades.add(btnRegistrar, gridBagConstraints);

        textUbicacion.setBackground(new Color(1, 135, 134));
        textUbicacion.setFont(new Font("Roboto", 1, 14)); // NOI18N
        textUbicacion.setForeground(new Color(255, 255, 255));
        textUbicacion.setBorder(BorderFactory.createTitledBorder(null, "UNIDAD/UBICACIÓN", TitledBorder.LEFT, TitledBorder.ABOVE_TOP, new Font("Roboto", 1, 14), new Color(255, 255, 255))); // NOI18N
        textUbicacion.setMaximumSize(new Dimension(300, 30));
        textUbicacion.setMinimumSize(new Dimension(300, 30));
        textUbicacion.setName(""); // NOI18N
        textUbicacion.setPreferredSize(new Dimension(300, 30));

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 40;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(18, 12, 0, 12);
        pnPropiedades.add(textUbicacion, gridBagConstraints);

        textFecha.setBackground(new Color(1, 135, 134));
        textFecha.setFont(new Font("Roboto", 1, 14)); // NOI18N
        textFecha.setForeground(new Color(255, 255, 255));
        textFecha.setBorder(BorderFactory.createTitledBorder(null, "FECHA", TitledBorder.LEFT, TitledBorder.ABOVE_TOP, new Font("Roboto", 1, 14), new Color(255, 255, 255))); // NOI18N
        textFecha.setMaximumSize(new Dimension(300, 30));
        textFecha.setMinimumSize(new Dimension(300, 30));
        textFecha.setName(""); // NOI18N
        textFecha.setPreferredSize(new Dimension(300, 30));

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 40;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(18, 12, 0, 12);
        pnPropiedades.add(textFecha, gridBagConstraints);

        getContentPane().add(pnPropiedades);

        pnTabla.setBackground(new Color(255, 255, 255));
        pnTabla.setMaximumSize(null);
        pnTabla.setPreferredSize(new Dimension(1344, 0));
        pnTabla.setLayout(new GridLayout(1, 0));
        jScrollPane2.setViewportView(jtResgistro);
        pnTabla.add(jScrollPane2);
        getContentPane().add(pnTabla);

        textLote.addKeyListener(new EventoTeclaSiguienteComponente(KeyEvent.VK_ENTER, textCantidad));
        textCantidad.addKeyListener(new EventoTeclaSiguienteComponente(KeyEvent.VK_ENTER, textFecha));
        textFecha.addKeyListener(new EventoTeclaSiguienteComponente(KeyEvent.VK_ENTER, textUbicacion));
        textUbicacion.addKeyListener(new EventoTeclaSiguienteComponente(KeyEvent.VK_ENTER, btnRegistrar));
        btnRegistrar.addActionListener(new EventoBotonRegistrar());
        btnNuevoTipoMuni.addActionListener(new EventoBotonNuevoTipoMuni());
        addWindowListener(new AbrirVentanaPrincipal());
        pack();
    }

    public static NuevoRegistro getInstancia() {
        if (nuevoRegistro == null) {
            nuevoRegistro = new NuevoRegistro();
        } else {
            return nuevoRegistro;
        }
        return nuevoRegistro;
    }

    public void limpiarCampos() {
        textCantidad.setText("");
        textLote.setText("");
        textFecha.setText("");
        textUbicacion.setText("");
    }

    public JComboBox<String> getCbTipoMuni() {
        return cbTipoMuni;
    }

    public void setCbTipoMuni(JComboBox<String> cbTipoMuni) {
        this.cbTipoMuni = cbTipoMuni;
    }

    public JLabel getjLabel3() {
        return jLabel3;
    }

    public void setjLabel3(JLabel jLabel3) {
        this.jLabel3 = jLabel3;
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

    public JTextArea getjTextArea1() {
        return jTextArea1;
    }

    public void setjTextArea1(JTextArea jTextArea1) {
        this.jTextArea1 = jTextArea1;
    }

    public JSeparator getJsDivisor() {
        return jsDivisor;
    }

    public void setJsDivisor(JSeparator jsDivisor) {
        this.jsDivisor = jsDivisor;
    }

    public JTable getJtResgistro() {
        return jtResgistro;
    }

    public void setJtResgistro(JTable jtResgistro) {
        this.jtResgistro = jtResgistro;
    }

    public JLabel getLbTitulo() {
        return lbTitulo;
    }

    public void setLbTitulo(JLabel lbTitulo) {
        this.lbTitulo = lbTitulo;
    }

    public JPanel getPnPropiedades() {
        return pnPropiedades;
    }

    public void setPnPropiedades(JPanel pnPropiedades) {
        this.pnPropiedades = pnPropiedades;
    }

    public JPanel getPnTabla() {
        return pnTabla;
    }

    public void setPnTabla(JPanel pnTabla) {
        this.pnTabla = pnTabla;
    }

    public JTextField getTextCantidad() {
        return textCantidad;
    }

    public void setTextCantidad(JTextField textCantidad) {
        this.textCantidad = textCantidad;
    }

    public JTextField getTextFecha() {
        return textFecha;
    }

    public void setTextFecha(JTextField textFecha) {
        this.textFecha = textFecha;
    }

    public JTextField getTextLote() {
        return textLote;
    }

    public void setTextLote(JTextField textLote) {
        this.textLote = textLote;
    }

    public JButton getBtnNuevoTipoMuni() {
        return btnNuevoTipoMuni;
    }

    public void setBtnNuevoTipoMuni(JButton btnNuevoTipoMuni) {
        this.btnNuevoTipoMuni = btnNuevoTipoMuni;
    }

    public JButton getBtnRegistrar() {
        return btnRegistrar;
    }

    public void setBtnRegistrar(JButton btnRegistrar) {
        this.btnRegistrar = btnRegistrar;
    }

    public JTextField getTextUbicacion() {
        return textUbicacion;
    }

    public void setTextUbicacion(JTextField textUbicacion) {
        this.textUbicacion = textUbicacion;
    }
}
