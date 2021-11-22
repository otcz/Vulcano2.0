package administrarMunicion.D_nuevoTipoMunicion.control;

import administrarMunicion.D_nuevoTipoMunicion.modelo.GuardarTipoMuni;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventoBotonGuardarLote implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        new GuardarTipoMuni();
    }
}
