package administrarMunicion.C_nuevoRegistro.controlador;

import administrarMunicion.C_nuevoRegistro.modelo.Municion;
import administrarMunicion.C_nuevoRegistro.vista.NuevoRegistro;

public class RecoletarDatosMuni {

    public Municion recolectar() {
        Municion municion = new Municion();
        NuevoRegistro nuevoRegistro = NuevoRegistro.getInstancia();
        if (nuevoRegistro.getTextLote().getText().trim().isEmpty() | nuevoRegistro.getTextCantidad().getText().trim().isEmpty() |
                nuevoRegistro.getCbTipoMuni().getSelectedItem().toString().trim().isEmpty() | nuevoRegistro.getTextFecha().getText().trim().isEmpty() |
                nuevoRegistro.getTextFecha().getText().trim().isEmpty() | nuevoRegistro.getTextUbicacion().getText().trim().isEmpty()) {
            return null;
        } else {

            municion.setsLoteMuni(nuevoRegistro.getTextLote().getText());
            municion.setsCantidad(nuevoRegistro.getTextCantidad().getText());
            municion.setsTipoMuni(nuevoRegistro.getCbTipoMuni().getSelectedItem().toString());
            municion.setsFecha(nuevoRegistro.getTextFecha().getText());
            municion.setsUbicacion(nuevoRegistro.getTextUbicacion().getText());
            return municion;
        }
    }
}
