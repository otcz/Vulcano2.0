package administrarMunicion.F_estadisticaLoteMuni.modelo;

import javax.swing.table.DefaultTableModel;


public class ModeloTabla extends DefaultTableModel {

    String[] titulos;
    Object[][] datos;


    public ModeloTabla(Object[][] datos, String[] titulos) {
        super();
        this.titulos = titulos;
        this.datos = datos;
        setDataVector(datos, titulos);

    }


    public boolean isCellEditable(int row, int column) {

        return false;

    }

}
