package administrarMunicion.C_nuevoRegistro.controlador;

import administrarMunicion.B_menuPrincipal.vista.Principal;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class AbrirVentanaPrincipal implements WindowListener {

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        Principal principal = Principal.getInstancia();
        principal.setVisible(true);
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
