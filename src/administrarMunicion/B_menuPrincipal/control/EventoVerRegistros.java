package administrarMunicion.B_menuPrincipal.control;

import administrarMunicion.E_verRegistro.vista.VerRegistro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventoVerRegistros implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        VerRegistro verRegistro = VerRegistro.getInstancia();
        verRegistro.setVisible(true);
    }
}
