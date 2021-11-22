package administrarMunicion.C_nuevoRegistro.controlador;

import administrarMunicion.C_nuevoRegistro.modelo.Municion;
import administrarMunicion.C_nuevoRegistro.vista.NuevoRegistro;
import administrarMunicion.E_verRegistro.control.PonerRegistrosEnTabla;

import javax.swing.table.DefaultTableModel;

public class MostrarDatosMuniEnTabla {
    Municion municion;

    public MostrarDatosMuniEnTabla(Municion municion) {
        this.municion = municion;
    }

    public void addMuniToTable() {
        NuevoRegistro nuevoRegistro = NuevoRegistro.getInstancia();
        DefaultTableModel model = (DefaultTableModel) nuevoRegistro.getJtResgistro().getModel();
        model.addRow(getMunicion().municio());
        nuevoRegistro.limpiarCampos();
        PonerRegistrosEnTabla ponerRegistrosEnTabla= new PonerRegistrosEnTabla();
        ponerRegistrosEnTabla.municiones();
    }

    public Municion getMunicion() {
        return municion;
    }

    public void setMunicion(Municion municion) {
        this.municion = municion;
    }
}
