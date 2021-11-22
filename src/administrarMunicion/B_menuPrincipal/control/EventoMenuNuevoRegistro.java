package administrarMunicion.B_menuPrincipal.control;

import administrarMunicion.B_menuPrincipal.vista.Principal;
import administrarMunicion.C_nuevoRegistro.vista.NuevoRegistro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventoMenuNuevoRegistro implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        NuevoRegistro nuevoRegistro = NuevoRegistro.getInstancia();
        Principal principal = Principal.getInstancia();
        principal.setVisible(false);
        nuevoRegistro.setVisible(true);
    }
}
