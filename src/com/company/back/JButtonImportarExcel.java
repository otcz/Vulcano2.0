package com.company.back;
import com.company.back.excel.BuscadorArchivosExcel;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.luciad.view.ILcdLayer;
public class JButtonImportarExcel extends JButton implements ActionListener {
    private int c = 0;
    private BuscadorArchivosExcel buscadorArchivosDispositivo = null;
    public ILcdLayer layer = null;

    public JButtonImportarExcel() {
        setIcon(new ImageIcon((getClass().getResource("Importar.png"))));
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(this)) {
            if (buscadorArchivosDispositivo == null) {
                buscadorArchivosDispositivo = new BuscadorArchivosExcel();
                buscadorArchivosDispositivo.setVisible(true);
                buscadorArchivosDispositivo.setLocationRelativeTo(this);
            } else {
                buscadorArchivosDispositivo.removerTodasFilas();
                buscadorArchivosDispositivo.setLocationRelativeTo(this);
                buscadorArchivosDispositivo.setVisible(true);
                buscadorArchivosDispositivo.reiniciarBarra();
            }
        }
    }

//GET Y SET_________________________________________________________________________________________________________
    public ILcdLayer getLayer() {
        return layer;
    }

    public void setLayer(ILcdLayer layer) {
        this.layer = layer;
    }


}
