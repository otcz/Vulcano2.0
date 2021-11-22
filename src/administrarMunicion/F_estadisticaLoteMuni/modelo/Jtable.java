package administrarMunicion.F_estadisticaLoteMuni.modelo;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class Jtable extends JTable {

    private JScrollPane scrollPaneTabla;
    private ModeloTabla modelo;

    public Jtable(String[] titulos, Object[][] data) {
        scrollPaneTabla = new JScrollPane();
        setBackground(Color.gray);
        setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
        setOpaque(false);
        modelo = new ModeloTabla(data, titulos);
        setModel(modelo);
        getTableHeader().setReorderingAllowed(false);
        setRowHeight(40);
        setGridColor(new Color(1, 1, 1));
        setBackground(new Color(255, 255, 255));
        setSelectionBackground(new Color(1, 135, 134));
        JTableHeader jtableHeader = getTableHeader();
        jtableHeader.setDefaultRenderer(new EncabezadoTabla());
        setTableHeader(jtableHeader);
        scrollPaneTabla.setViewportView(this);


    }



}
