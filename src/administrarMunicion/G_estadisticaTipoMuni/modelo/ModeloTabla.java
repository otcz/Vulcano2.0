package administrarMunicion.G_estadisticaTipoMuni.modelo;

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
