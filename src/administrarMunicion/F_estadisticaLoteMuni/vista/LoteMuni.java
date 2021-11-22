/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package administrarMunicion.F_estadisticaLoteMuni.vista;


import administrarMunicion.C_nuevoRegistro.controlador.AlmacenMuni;
import administrarMunicion.C_nuevoRegistro.modelo.Municion;
import administrarMunicion.E_verRegistro.modelo.TituloTabla;
import administrarMunicion.F_estadisticaLoteMuni.modelo.Jtable;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class LoteMuni extends JFrame {

    private JScrollPane jScrollPane1;
    private JTable jtRegistros;
    private JPanel pnInferior;
    private JPanel pnPrincipal;
    private JPanel pnSuperior;
    public static LoteMuni loteMuni;


    public LoteMuni() {
        initComponents();
        DefaultPieDataset defaultpiedataset = new DefaultPieDataset();
        for (int i = 0; i < AlmacenMuni.getArrayList().size(); i++) {
            Municion municion = AlmacenMuni.getArrayList().get(i);
            defaultpiedataset.setValue(municion.getsLoteMuni(), Double.parseDouble(municion.getsCantidad()));
            DefaultTableModel model = (DefaultTableModel) jtRegistros.getModel();
            model.addRow(municion.municio());
        }
        // Fuente de Datos



        // Creando el Grafico
        JFreeChart chart = ChartFactory.createPieChart3D("ESTADISTICA LOTE", defaultpiedataset, true, true, false);
        PiePlot3D pieplot3d = (PiePlot3D) chart.getPlot();
        pieplot3d.setDepthFactor(0.5);
        pieplot3d.setStartAngle(290D);
        pieplot3d.setDirection(Rotation.CLOCKWISE);
        pieplot3d.setForegroundAlpha(0.5F);

        // Mostrar Grafico
        ChartPanel chartPanel = new ChartPanel(chart);

        pnSuperior.add(chartPanel);
    }


    private void initComponents() {

        pnPrincipal = new JPanel();
        pnSuperior = new JPanel();
        pnInferior = new JPanel();
        jScrollPane1 = new JScrollPane();
        jtRegistros = new Jtable(TituloTabla.Nuevo_registro, new Object[0][0]);

        setBackground(new Color(1, 135, 134));
        setForeground(new Color(1, 135, 134));
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.LINE_AXIS));

        pnPrincipal.setBackground(new Color(1, 135, 134));
        pnPrincipal.setLayout(new BoxLayout(pnPrincipal, BoxLayout.Y_AXIS));

        pnSuperior.setBackground(new Color(1, 135, 134));
        pnSuperior.setLayout(new BorderLayout());
        pnPrincipal.add(pnSuperior);

        pnInferior.setBackground(new Color(255, 255, 255));
        pnInferior.setLayout(new BorderLayout());

        jScrollPane1.setBackground(new Color(1, 135, 134));

        jScrollPane1.setViewportView(jtRegistros);

        pnInferior.add(jScrollPane1, BorderLayout.CENTER);

        pnPrincipal.add(pnInferior);

        getContentPane().add(pnPrincipal);

        pack();
    }

    public static LoteMuni getInstancia() {
        if (loteMuni == null) {
            loteMuni = new LoteMuni();
        } else {
            return loteMuni;
        }
        return loteMuni;
    }
}
