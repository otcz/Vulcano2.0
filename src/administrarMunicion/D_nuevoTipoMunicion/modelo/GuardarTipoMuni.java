package administrarMunicion.D_nuevoTipoMunicion.modelo;

import administrarMunicion.C_nuevoRegistro.vista.NuevoRegistro;
import administrarMunicion.D_nuevoTipoMunicion.control.AlmacenTipoMuni;
import administrarMunicion.D_nuevoTipoMunicion.vista.CrearNuevoTipoMunicion;

import javax.swing.*;

public class GuardarTipoMuni {
    public GuardarTipoMuni() {
        CrearNuevoTipoMunicion crearNuevoTipoMunicion = CrearNuevoTipoMunicion.getInstancia();
        if (!crearNuevoTipoMunicion.getTextTipoLote().getText().trim().isEmpty()) {
            TipoMuni tipoMuni = new TipoMuni();
            tipoMuni.setLote(crearNuevoTipoMunicion.getTextTipoLote().getText());
            AlmacenTipoMuni.addTipoMuni(tipoMuni);
            crearNuevoTipoMunicion.getCbTiposMuni().addItem(crearNuevoTipoMunicion.getTextTipoLote().getText());
            NuevoRegistro nuevoRegistro = NuevoRegistro.getInstancia();
            nuevoRegistro.getCbTipoMuni().addItem(crearNuevoTipoMunicion.getTextTipoLote().getText());
            JOptionPane.showMessageDialog(null, "Registro exitoso!", "GUARDAR TIPO MUNICIÓN", JOptionPane.DEFAULT_OPTION,
                    new ImageIcon(getClass().getResource("/administrarMunicion/imagen/Guardar.png")));
            crearNuevoTipoMunicion.getCbTiposMuni().showPopup();

        } else {
            JOptionPane.showMessageDialog(null, "Debes completar toda la información", "GUARDAR TIPO MUNICIÓN", JOptionPane.DEFAULT_OPTION,
                    new ImageIcon(getClass().getResource("/administrarMunicion/imagen/xRoja.png")));
        }
    }

}
