package administrarMunicion.B_menuPrincipal.control;

import administrarMunicion.F_estadisticaLoteMuni.vista.LoteMuni;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventoEstadisticaLoteMuni implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
       // LoteMuni loteMuni = LoteMuni.getInstancia();
        LoteMuni loteMuni = new LoteMuni();
        loteMuni.setVisible(true);
    }
}
