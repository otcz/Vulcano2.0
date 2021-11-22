package administrarMunicion.D_nuevoTipoMunicion.vista;

import administrarMunicion.D_nuevoTipoMunicion.control.EventoBotonGuardarLote;
import administrarMunicion.D_nuevoTipoMunicion.control.EventoSalir;
import administrarMunicion.D_nuevoTipoMunicion.control.EventoTeclaSiguienteComponente;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.KeyEvent;


public class CrearNuevoTipoMunicion extends JFrame {

    private JButton btnGuardar;
    private JButton btnSalir;
    private JComboBox<String> cbTiposMuni;
    private JLabel lbTitulo;
    private JPanel pnprincipal;
    private JTextField textTipoLote;
    public static CrearNuevoTipoMunicion crearNuevoTipoMunicion;

    public CrearNuevoTipoMunicion() {
        initComponents();
    }

    private void initComponents() {

        GridBagConstraints gridBagConstraints;

        pnprincipal = new JPanel();
        lbTitulo = new JLabel();
        cbTiposMuni = new JComboBox<>();
        textTipoLote = new JTextField();
        btnSalir = new JButton();
        btnGuardar = new JButton();

        setBackground(new Color(1, 135, 134));

        pnprincipal.setBackground(new Color(1, 135, 134));
        pnprincipal.setLayout(new GridBagLayout());

        lbTitulo.setBackground(new Color(255, 255, 255));
        lbTitulo.setFont(new Font("Roboto", 1, 24)); // NOI18N
        lbTitulo.setForeground(new Color(255, 255, 255));
        lbTitulo.setText("NUEVO/EDITAR  MUNICIÓN");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(35, 28, 0, 25);
        pnprincipal.add(lbTitulo, gridBagConstraints);

        cbTiposMuni.setBackground(new Color(1, 135, 134));
        cbTiposMuni.setFont(new Font("Roboto", 1, 14)); // NOI18N
        cbTiposMuni.setForeground(new Color(255, 255, 255));
        cbTiposMuni.setMaximumRowCount(5);
        cbTiposMuni.setModel(new DefaultComboBoxModel<>(new String[]{"ER50", "M101"}));
        cbTiposMuni.setAlignmentX(1.5F);
        cbTiposMuni.setAlignmentY(1.5F);
        cbTiposMuni.setBorder(BorderFactory.createTitledBorder(null, "", TitledBorder.LEFT, TitledBorder.ABOVE_TOP, new Font("Roboto", 1, 14), new Color(255, 255, 255))); // NOI18N
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 246;
        gridBagConstraints.ipady = 46;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(18, 28, 0, 25);
        pnprincipal.add(cbTiposMuni, gridBagConstraints);

        textTipoLote.setBackground(new Color(1, 135, 134));
        textTipoLote.setFont(new Font("Roboto", 1, 14)); // NOI18N
        textTipoLote.setForeground(new Color(255, 255, 255));
        textTipoLote.setBorder(BorderFactory.createTitledBorder(null, "NOMBRE", TitledBorder.LEFT, TitledBorder.ABOVE_TOP, new Font("Roboto", 1, 14), new Color(255, 255, 255))); // NOI18N

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 296;
        gridBagConstraints.ipady = 21;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(18, 28, 0, 25);
        pnprincipal.add(textTipoLote, gridBagConstraints);

        btnSalir.setBackground(new Color(1, 135, 134));
        btnSalir.setFont(new Font("Roboto", 1, 14)); // NOI18N
        btnSalir.setForeground(new Color(255, 255, 255));
        btnSalir.setText("SALIR");

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 241;
        gridBagConstraints.ipady = 19;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(18, 28, 53, 25);
        pnprincipal.add(btnSalir, gridBagConstraints);

        btnGuardar.setBackground(new Color(1, 135, 134));
        btnGuardar.setFont(new Font("Roboto", 1, 14)); // NOI18N
        btnGuardar.setForeground(new Color(255, 255, 255));
        btnGuardar.setText("GUARDAR");

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 215;
        gridBagConstraints.ipady = 19;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(18, 28, 0, 25);
        pnprincipal.add(btnGuardar, gridBagConstraints);
        getContentPane().add(pnprincipal, BorderLayout.CENTER);
        btnGuardar.addActionListener(new EventoBotonGuardarLote());
        textTipoLote.addKeyListener(new EventoTeclaSiguienteComponente(KeyEvent.VK_ENTER, btnGuardar));
        btnSalir.addActionListener(new EventoSalir());
        pack();
    }

    public static CrearNuevoTipoMunicion getInstancia() {
        if (crearNuevoTipoMunicion == null) {
            crearNuevoTipoMunicion = new CrearNuevoTipoMunicion();
        } else {
            return crearNuevoTipoMunicion;
        }
        return crearNuevoTipoMunicion;
    }

    public JButton getBtnGuardar() {
        return btnGuardar;
    }

    public void setBtnGuardar(JButton btnGuardar) {
        this.btnGuardar = btnGuardar;
    }

    public JButton getBtnSalir() {
        return btnSalir;
    }

    public void setBtnSalir(JButton btnSalir) {
        this.btnSalir = btnSalir;
    }

    public JComboBox<String> getCbTiposMuni() {
        return cbTiposMuni;
    }

    public void setCbTiposMuni(JComboBox<String> cbTiposMuni) {
        this.cbTiposMuni = cbTiposMuni;
    }

    public JLabel getLbTitulo() {
        return lbTitulo;
    }

    public void setLbTitulo(JLabel lbTitulo) {
        this.lbTitulo = lbTitulo;
    }

    public JPanel getPnprincipal() {
        return pnprincipal;
    }

    public void setPnprincipal(JPanel pnprincipal) {
        this.pnprincipal = pnprincipal;
    }

    public JTextField getTextTipoLote() {
        return textTipoLote;
    }

    public void setTextTipoLote(JTextField textTipoLote) {
        this.textTipoLote = textTipoLote;
    }

    public static CrearNuevoTipoMunicion getCrearNuevoTipoMunicion() {
        return crearNuevoTipoMunicion;
    }

    public static void setCrearNuevoTipoMunicion(CrearNuevoTipoMunicion crearNuevoTipoMunicion) {
        CrearNuevoTipoMunicion.crearNuevoTipoMunicion = crearNuevoTipoMunicion;
    }
}
