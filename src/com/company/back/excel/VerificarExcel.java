package com.company.back.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

public class VerificarExcel {
    private int limiteError = 20;
    private int numeroCelda = 0, numeroFila = 0;
    private XSSFSheet hoja = null;
    private XSSFWorkbook libro = null;
    private ArrayList rutasExcel = null;
    private ArrayList objectsExcel = null;
    private JTable jTable = null;

    public static String DISPOSITIVO = "DISPOSITIVO", BLANCOS = "BLANCOS", UNIDADSUPERIOR = "UNIDAD SUPERIOR", NOMBREBLANCO = "NOMBRE BLANCO";

    public VerificarExcel(ArrayList rutasExcel) {
        setRutasExcel(rutasExcel);
        objectsExcel = new ArrayList();
        setLimiteError(10);
    }

    public void vericar_E_IniciarCopiadoDelDispositivo(String Dispositivo_O_Blanco) {
        try {
            for (int i = 0; i < getRutasExcel().size(); i++) {
                FileInputStream fileInputStream = new FileInputStream(getRutasExcel().get(i).toString());
                XSSFWorkbook libro = new XSSFWorkbook(fileInputStream);
                hoja = numeroPagina(libro, Dispositivo_O_Blanco);
                if (hoja != null) {
                    LeerCamposDesdeExcelParaGraficacion copiarUnidades = new LeerCamposDesdeExcelParaGraficacion();
                    copiarUnidades.setHoja(getHoja());
                    copiarUnidades.NombreExcelImpotado(getRutasExcel().get(i).toString());
                    if (copiarUnidades.esHojaExcelEsperada(UNIDADSUPERIOR) & Dispositivo_O_Blanco.equalsIgnoreCase(DISPOSITIVO)) {
                        copiarUnidades.procesoLeerCamposDesdeExcelParaGraficacionDispositivo();
                    } else if (copiarUnidades.esHojaExcelEsperada(NOMBREBLANCO) & Dispositivo_O_Blanco.equalsIgnoreCase(BLANCOS)) {
                        copiarUnidades.procesoLeerCamposDesdeExcelParaGraficacionBlanco();
                    }
                    if (copiarUnidades.getCampoverificar().isEmpty()) {
                        copiarUnidades.setNombreExcelesValidosParaGraficar((new File(getRutasExcel().get(i).toString()).getName()));
                        getObjectsExcel().add(copiarUnidades);
                        contarUnidadesAmigaHostil(getjTable(), i, copiarUnidades.getAmigo(), copiarUnidades.getHostil(), copiarUnidades.getOtraFiliacion());
                    } else {
                        copiarUnidades.camposParaVerificar();
                        quitarRutaExcelNoValidaExcelTabla(getjTable(), new File(getRutasExcel().get(i).toString()).getName(), i);
                    }

                }
                else {
                    JOptionPane.showMessageDialog(null, "No se encontro dispositivo o  blancos para graficar");
                }

                fileInputStream.close();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al leer Excel");
        }
    }

    public XSSFSheet numeroPagina(XSSFWorkbook libro, String nombre) {
        for (int i = 0; i < libro.getNumberOfSheets(); i++) {
            if (libro.getSheetAt(i).getSheetName().equalsIgnoreCase(nombre)) {
                return libro.getSheetAt(i);
            }
        }
        return libro.getSheetAt(0);
    }

    public boolean esExcelformatoCorrecto() {
        for (int i = 0; i <= getLimiteError(); i++) {
            for (int j = 0; j <= getLimiteError(); j++) {
                if (getHoja().getRow(j) != null && getHoja().getRow(j).getCell(i) != null) {
                    Cell cellValue = getHoja().getRow(j).getCell(i);
                    if (cellValue.getCellType() == Cell.CELL_TYPE_STRING && getHoja().getRow(j).getCell(i).getStringCellValue().trim().equalsIgnoreCase(UNIDADSUPERIOR) ||
                            cellValue.getCellType() == Cell.CELL_TYPE_STRING && getHoja().getRow(j).getCell(i).getStringCellValue().trim().equalsIgnoreCase(NOMBREBLANCO)) {
                        setNumeroCelda(i);
                        setNumeroFila(j);
                        return true;
                    }

                }
            }
        }
        return false;
    }

    public boolean validoParaFraficar() {
        if (getObjectsExcel().isEmpty()) {
            return false;
        }
        return true;
    }

    public void quitarRutaExcelNoValidaExcelTabla(JTable table, String nombre, int fila) {
        for (int i = 0; i < table.getRowCount(); i++) {
            if (table.getValueAt(fila, 0).toString().equalsIgnoreCase(nombre)) {
                table.setValueAt(false, fila, 4);
            }
        }
    }

    public void contarUnidadesAmigaHostil(JTable table, int fila, int amigo, int hostil, int otraFIliacion) {
        table.setValueAt(amigo, fila, 1);
        table.setValueAt(hostil, fila, 2);
        table.setValueAt(otraFIliacion, fila, 3);
    }

    //GET Y SET_________________________________________________________________________________________________________

    public ArrayList getRutasExcel() {
        return rutasExcel;
    }

    public void setRutasExcel(ArrayList rutasExcel) {
        this.rutasExcel = rutasExcel;
    }

    public int getLimiteError() {
        return limiteError;
    }

    public void setLimiteError(int limiteError) {
        this.limiteError = limiteError;
    }

    public XSSFSheet getHoja() {
        return hoja;
    }

    public int getNumeroCelda() {
        return numeroCelda;
    }

    public void setNumeroCelda(int numeroCelda) {
        this.numeroCelda = numeroCelda;
    }

    public int getNumeroFila() {
        return numeroFila;
    }

    public void setNumeroFila(int numeroFila) {
        this.numeroFila = numeroFila;
    }

    public ArrayList getObjectsExcel() {
        return objectsExcel;
    }

    public JTable getjTable() {
        return jTable;
    }

    public void setjTable(JTable jTable) {
        this.jTable = jTable;
    }
}
