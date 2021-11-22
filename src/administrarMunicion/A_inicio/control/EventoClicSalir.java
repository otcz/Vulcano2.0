package administrarMunicion.A_inicio.control;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class EventoClicSalir implements MouseListener {


    @Override
    public void mouseClicked(MouseEvent e) {
        System.exit(0);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        JLabel label = (JLabel) e.getSource();
        label.setFont(new Font("Tahoma", 1, 14)); // NOI18N
        label.setText("PRECIONA PARA SALIR");


    }

    @Override
    public void mouseExited(MouseEvent e) {
        JLabel label = (JLabel) e.getSource();
        label.setFont(new Font("Tahoma", 1, 12)); // NOI18N
        label.setText("SALIR");
    }
}
