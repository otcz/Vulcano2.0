package administrarMunicion.C_nuevoRegistro.controlador;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class EventoTeclaSiguienteComponente implements KeyListener {
    Object objetoSiguiente;
    int keyEventTipo;


    public EventoTeclaSiguienteComponente(int keyEventTipo, Object objetoSiguiente) {
        this.keyEventTipo = keyEventTipo;
        this.objetoSiguiente = objetoSiguiente;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == getKeyEventTipo()) {
            if (getObjetoSiguiente() instanceof JTextField) {
                JTextField textField = (JTextField) getObjetoSiguiente();
                if (e.getKeyCode() == getKeyEventTipo()) {
                    textField.requestFocus();
                }
            } else if (getObjetoSiguiente() instanceof JButton) {
                JButton button = (JButton) getObjetoSiguiente();
                button.doClick();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public Object getObjetoSiguiente() {
        return objetoSiguiente;
    }

    public void setObjetoSiguiente(Object objetoSiguiente) {
        this.objetoSiguiente = objetoSiguiente;
    }

    public int getKeyEventTipo() {
        return keyEventTipo;
    }

    public void setKeyEventTipo(int keyEventTipo) {
        this.keyEventTipo = keyEventTipo;
    }


}
