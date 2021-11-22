package administrarMunicion.B_menuPrincipal.control;

import administrarMunicion.G_estadisticaTipoMuni.vista.TipoMuni;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventoEstadisticaTipoMuni implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
       // TipoMuni tipoMuni = TipoMuni.getInstancia();
        TipoMuni tipoMuni =new TipoMuni();
        tipoMuni.setVisible(true);
    }
}
