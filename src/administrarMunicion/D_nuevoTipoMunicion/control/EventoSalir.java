package administrarMunicion.D_nuevoTipoMunicion.control;

import administrarMunicion.D_nuevoTipoMunicion.vista.CrearNuevoTipoMunicion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventoSalir implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        CrearNuevoTipoMunicion crearTipoMuni= CrearNuevoTipoMunicion.getInstancia();
        crearTipoMuni.setVisible(false);
    }
}
