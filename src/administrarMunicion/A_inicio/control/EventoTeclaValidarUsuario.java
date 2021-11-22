package administrarMunicion.A_inicio.control;

import administrarMunicion.A_inicio.modelo.ValidarUsuario;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class EventoTeclaValidarUsuario implements KeyListener {
    int KeyEventTipo;
    JTextField usuario, clave;

    public EventoTeclaValidarUsuario(int keyEventTipo, JTextField usuario, JTextField clave) {
        KeyEventTipo = keyEventTipo;
        this.usuario = usuario;
        this.clave = clave;
    }



    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == getKeyEventTipo()) {
            new ValidarUsuario().ValidarUsuario(getUsuario().getText(), getClave().getText());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public int getKeyEventTipo() {
        return KeyEventTipo;
    }

    public void setKeyEventTipo(int keyEventTipo) {
        KeyEventTipo = keyEventTipo;
    }

    public JTextField getUsuario() {
        return usuario;
    }

    public void setUsuario(JTextField usuario) {
        this.usuario = usuario;
    }

    public JTextField getClave() {
        return clave;
    }

    public void setClave(JTextField clave) {
        this.clave = clave;
    }
}
