package administrarMunicion.B_menuPrincipal.vista;

import administrarMunicion.B_menuPrincipal.control.*;

import javax.swing.*;
import java.awt.*;

public class Principal extends JFrame {
    private JLabel lbFondo;
    private JMenuBar jMenuBar1;
    private JPanel jPanel2;
    private JMenu jmArchivo, jmArchivo1, jmArchivo2;
    private JMenuItem jmCrearTipoMunicion, jmCrearTipoMunicion1, jmVerRegistro, jmiNuevoRegistro, jmiCrearMunicion1, jmiCrearMunicion2;

    public static Principal principal = null;

    public Principal() {
        setExtendedState(MAXIMIZED_BOTH);
        jPanel2 = new JPanel();
        lbFondo = new JLabel();
        jMenuBar1 = new JMenuBar();
        jmArchivo = new JMenu();
        jmiNuevoRegistro = new JMenuItem();
        jmCrearTipoMunicion = new JMenuItem();
        jmVerRegistro = new JMenuItem();
        jmArchivo1 = new JMenu();
        jmiCrearMunicion1 = new JMenuItem();
        jmCrearTipoMunicion1 = new JMenuItem();
        jmArchivo2 = new JMenu();
        jmiCrearMunicion2 = new JMenuItem();
        setExtendedState(0);

        jPanel2.setBackground(new Color(153, 153, 153));
        jPanel2.setLayout(new BorderLayout());

        lbFondo.setIcon(new ImageIcon(getClass().getResource("/administrarMunicion/imagen/blue2-1686530_960_720 (1).jpg")));
        lbFondo.setText("NUEVO REGISTRO");
        lbFondo.setToolTipText("Crear nuevo registro");
        jPanel2.add(lbFondo, BorderLayout.CENTER);

        getContentPane().add(jPanel2, BorderLayout.CENTER);

        jMenuBar1.setPreferredSize(new Dimension(66, 50));

        jmArchivo.setBackground(new Color(1, 135, 134));
        jmArchivo.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        jmArchivo.setIcon(new ImageIcon(getClass().getResource("/administrarMunicion/imagen/Archivo.png"))); // NOI18N
        jmArchivo.setText("ARCHIVO     ");
        jmArchivo.setAlignmentX(2.0F);
        jmArchivo.setFont(new Font("Roboto", 1, 12)); // NOI18N

        jmiNuevoRegistro.setFont(new Font("Roboto", 0, 12)); // NOI18N
        jmiNuevoRegistro.setIcon(new ImageIcon(getClass().getResource("/administrarMunicion/imagen/Nuevo.png"))); // NOI18N
        jmiNuevoRegistro.setText("NUEVO REGISTRO");
        jmiNuevoRegistro.setActionCommand("");
        jmiNuevoRegistro.setAlignmentX(2.0F);
        jmiNuevoRegistro.setAlignmentY(2.0F);
        jmiNuevoRegistro.setMaximumSize(new Dimension(200, 40));
        jmiNuevoRegistro.setMinimumSize(new Dimension(200, 40));
        jmiNuevoRegistro.setPreferredSize(new Dimension(200, 40));

        jmArchivo.add(jmiNuevoRegistro);

        jmCrearTipoMunicion.setFont(new Font("Roboto", 0, 12)); // NOI18N
        jmCrearTipoMunicion.setIcon(new ImageIcon(getClass().getResource("/administrarMunicion/imagen/cajaMuni32.png"))); // NOI18N
        jmCrearTipoMunicion.setText("NUEVO TIPO MUNICION");
        jmCrearTipoMunicion.setAlignmentX(2.0F);
        jmCrearTipoMunicion.setAlignmentY(2.0F);
        jmCrearTipoMunicion.setAutoscrolls(true);
        jmCrearTipoMunicion.setMaximumSize(new Dimension(200, 40));
        jmCrearTipoMunicion.setMinimumSize(new Dimension(200, 40));
        jmCrearTipoMunicion.setPreferredSize(new Dimension(200, 40));

        jmArchivo.add(jmCrearTipoMunicion);

        jmVerRegistro.setFont(new Font("Roboto", 0, 12)); // NOI18N
        jmVerRegistro.setIcon(new ImageIcon(getClass().getResource("/administrarMunicion/imagen/VerRegistro.png"))); // NOI18N
        jmVerRegistro.setText("VER REGISTRO");
        jmVerRegistro.setActionCommand("");
        jmVerRegistro.setAlignmentX(2.0F);
        jmVerRegistro.setAlignmentY(2.0F);
        jmVerRegistro.setAutoscrolls(true);
        jmVerRegistro.setMaximumSize(new Dimension(200, 40));
        jmVerRegistro.setMinimumSize(new Dimension(200, 40));
        jmVerRegistro.setPreferredSize(new Dimension(200, 40));

        jmArchivo.add(jmVerRegistro);

        jMenuBar1.add(jmArchivo);

        jmArchivo1.setBackground(new Color(1, 135, 134));
        jmArchivo1.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        jmArchivo1.setIcon(new ImageIcon(getClass().getResource("/administrarMunicion/imagen/estadistica.png"))); // NOI18N
        jmArchivo1.setText("ADMINISTRAR");
        jmArchivo1.setAlignmentX(2.0F);
        jmArchivo1.setFont(new Font("Roboto", 1, 12)); // NOI18N

        jmiCrearMunicion1.setFont(new Font("Roboto", 0, 12)); // NOI18N
        jmiCrearMunicion1.setIcon(new ImageIcon(getClass().getResource("/administrarMunicion/imagen/Nuevo.png"))); // NOI18N
        jmiCrearMunicion1.setText("LOTE MUNI");
        jmiCrearMunicion1.setActionCommand("");
        jmiCrearMunicion1.setAlignmentX(2.0F);
        jmiCrearMunicion1.setAlignmentY(2.0F);
        jmiCrearMunicion1.setMaximumSize(new Dimension(200, 40));
        jmiCrearMunicion1.setMinimumSize(new Dimension(200, 40));
        jmiCrearMunicion1.setPreferredSize(new Dimension(200, 40));

        jmArchivo1.add(jmiCrearMunicion1);

        jmCrearTipoMunicion1.setFont(new Font("Roboto", 0, 12)); // NOI18N
        jmCrearTipoMunicion1.setIcon(new ImageIcon(getClass().getResource("/administrarMunicion/imagen/cajaMuni32.png"))); // NOI18N
        jmCrearTipoMunicion1.setText("TIPO MUNI");
        jmCrearTipoMunicion1.setActionCommand("");
        jmCrearTipoMunicion1.setAlignmentX(2.0F);
        jmCrearTipoMunicion1.setAlignmentY(2.0F);
        jmCrearTipoMunicion1.setAutoscrolls(true);
        jmCrearTipoMunicion1.setMaximumSize(new Dimension(200, 40));
        jmCrearTipoMunicion1.setMinimumSize(new Dimension(200, 40));
        jmCrearTipoMunicion1.setPreferredSize(new Dimension(200, 40));
        jmArchivo1.add(jmCrearTipoMunicion1);
        jMenuBar1.add(jmArchivo1);

        jmArchivo2.setBackground(new Color(1, 135, 134));
        jmArchivo2.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        jmArchivo2.setIcon(new ImageIcon(getClass().getResource("/administrarMunicion/imagen/ayuda.png"))); // NOI18N
        jmArchivo2.setText("AYUDA");
        jmArchivo2.setAlignmentX(2.0F);
        jmArchivo2.setFont(new Font("Roboto", 1, 12)); // NOI18N

        jmiCrearMunicion2.setFont(new Font("Roboto", 0, 12)); // NOI18N
        jmiCrearMunicion2.setIcon(new ImageIcon(getClass().getResource("/administrarMunicion/imagen/Nuevo.png"))); // NOI18N
        jmiCrearMunicion2.setText("SOPORTE TÉCNICO");
        jmiCrearMunicion2.setActionCommand("");
        jmiCrearMunicion2.setAlignmentX(2.0F);
        jmiCrearMunicion2.setAlignmentY(2.0F);
        jmiCrearMunicion2.setMaximumSize(new Dimension(200, 40));
        jmiCrearMunicion2.setMinimumSize(new Dimension(200, 40));
        jmiCrearMunicion2.setPreferredSize(new Dimension(200, 40));
        jmArchivo2.add(jmiCrearMunicion2);
        jMenuBar1.add(jmArchivo2);
        setJMenuBar(jMenuBar1);

        jmiNuevoRegistro.addActionListener(new EventoMenuNuevoRegistro());
        jmCrearTipoMunicion.addActionListener(new EventoCrearTipoMuni());
        jmVerRegistro.addActionListener(new EventoVerRegistros());
        jmiCrearMunicion1.addActionListener(new EventoEstadisticaLoteMuni());
        jmCrearTipoMunicion1.addActionListener(new EventoEstadisticaTipoMuni());
        pack();


    }

    public static Principal getInstancia() {
        if (principal == null) {
            principal = new Principal();
        } else {
            return principal;
        }
        return principal;
    }

}
