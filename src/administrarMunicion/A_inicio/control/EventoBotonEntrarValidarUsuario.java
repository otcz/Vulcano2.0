package administrarMunicion.A_inicio.control;

import administrarMunicion.A_inicio.modelo.ValidarUsuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventoBotonEntrarValidarUsuario implements ActionListener {

    JTextField usuario, clave;

    public EventoBotonEntrarValidarUsuario(JTextField usuario, JTextField clave) {
        this.usuario = usuario;
        this.clave = clave;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ValidarUsuario validarUsuario = new ValidarUsuario();
        validarUsuario.ValidarUsuario(usuario.getText(), clave.getText());
    }
}
