package administrarMunicion.E_verRegistro.control;

import administrarMunicion.C_nuevoRegistro.controlador.AlmacenMuni;
import administrarMunicion.C_nuevoRegistro.modelo.Municion;
import administrarMunicion.E_verRegistro.vista.VerRegistro;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class PonerRegistrosEnTabla {

    public void municiones() {
        ArrayList<Municion> municions = AlmacenMuni.getArrayList();
        VerRegistro verRegistro = VerRegistro.getInstancia();
        DefaultTableModel defaultTableModel = (DefaultTableModel) verRegistro.getJtRegistros().getModel();
        for (int i = 0; i < defaultTableModel.getRowCount(); i++) {
            defaultTableModel.removeRow(i);
        }

        for (int i = 0; i < municions.size(); i++) {
            Municion municion = municions.get(i);
            defaultTableModel.addRow(municion.municio());
        }
    }

}
