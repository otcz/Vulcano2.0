package administrarMunicion.C_nuevoRegistro.controlador;

import administrarMunicion.D_nuevoTipoMunicion.vista.CrearNuevoTipoMunicion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventoBotonNuevoTipoMuni implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        CrearNuevoTipoMunicion crearNuevoTipoMunicion = CrearNuevoTipoMunicion.getInstancia();
        crearNuevoTipoMunicion.setVisible(true);
    }
}
