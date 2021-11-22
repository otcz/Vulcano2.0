package administrarMunicion.C_nuevoRegistro.controlador;

import administrarMunicion.C_nuevoRegistro.modelo.Municion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventoBotonRegistrar implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        Municion municion = new RecoletarDatosMuni().recolectar();
        if (municion != null) {
            MostrarDatosMuniEnTabla mostrarDatosMuniEnTabla = new MostrarDatosMuniEnTabla(municion);

            AlmacenMuni.addMuni(municion);
            mostrarDatosMuniEnTabla.addMuniToTable();
        } else {
            JOptionPane.showMessageDialog(null, "Debes completar toda la información", "GUARDAR MUNICIÓN", JOptionPane.DEFAULT_OPTION,
                    new ImageIcon(getClass().getResource("/administrarMunicion/imagen/xRoja.png")));
        }
    }
}
