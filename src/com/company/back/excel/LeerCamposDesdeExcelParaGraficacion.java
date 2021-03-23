package com.company.back.excel;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;

import static com.company.back.excel.VerificarExcel.NOMBREBLANCO;
import static com.company.back.excel.VerificarExcel.UNIDADSUPERIOR;

public class LeerCamposDesdeExcelParaGraficacion extends Object {
    private ArrayList unidadSuperior, nombre, lat, lon, filiacion, estado, situacion,tipoBlanco,
            ordenBatalla, escalon, nombreExcelImportadoParaCadaUnidad, fecha, fechaEfectiva, altura, azimut, eficiencia,
            informacion, comentario, speed, arma, pais, campoverificar, rutaExcelVerificar;
    private XSSFSheet hoja = null;
    private int numeroColumna = 0, numeroFila = 0, amigo = 0, hostil = 0, otraFiliacion = 0;
    private String NombreExcelProcesoIndividualActual = "", nombreExcelesValidosParaGraficar = "";

    public LeerCamposDesdeExcelParaGraficacion() {
        unidadSuperior = new ArrayList();
        nombre = new ArrayList();
        lat = new ArrayList();
        lon = new ArrayList();
        filiacion = new ArrayList();
        estado = new ArrayList();
        situacion = new ArrayList();
        tipoBlanco = new ArrayList();
        ordenBatalla = new ArrayList();
        escalon = new ArrayList();
        fecha = new ArrayList();
        fechaEfectiva = new ArrayList();
        altura = new ArrayList();
        azimut = new ArrayList();
        eficiencia = new ArrayList();
        informacion = new ArrayList();
        comentario = new ArrayList();
        speed = new ArrayList();
        arma = new ArrayList();
        pais = new ArrayList();
        nombreExcelImportadoParaCadaUnidad = new ArrayList();
        campoverificar = new ArrayList();
        rutaExcelVerificar = new ArrayList();
    }

    public void NombreExcelImpotado(String nombre) {
        setNombreExcelProcesoIndividualActual(nombre);
        for (int i = getNumeroFila(); i < getHoja().getLastRowNum(); i++) {
            getNombreExcelImportadoParaCadaUnidad().add(nombre);
        }
    }

    public boolean procesoLeerCamposDesdeExcelParaGraficacionDispositivo() {
        try {
            setUnidadSuperior(procesoLeerCamposDesdeExcelParaGraficacionDispositivo(getNumeroColumna()));
            setNombre(procesoLeerCamposDesdeExcelParaGraficacionDispositivo(getNumeroColumna() + 1));
            setLat(coordendas(2, 3, 4));
            setLon(coordendas(5, 6, 7));
            setFiliacion(procesoLeerCamposDesdeExcelParaGraficacionDispositivo(getNumeroColumna() + 8));
            setEstado(procesoLeerCamposDesdeExcelParaGraficacionDispositivo(getNumeroColumna() + 9));
            setSituacion(procesoLeerCamposDesdeExcelParaGraficacionDispositivo(getNumeroColumna() + 10));
            setOrdenBatalla(procesoLeerCamposDesdeExcelParaGraficacionDispositivo(getNumeroColumna() + 11));
            setEscalon(procesoLeerCamposDesdeExcelParaGraficacionDispositivo(getNumeroColumna() + 12));
            setFecha(procesoLeerCamposDesdeExcelParaGraficacionDispositivo(getNumeroColumna() + 13));
            setAltura(procesoLeerCamposDesdeExcelParaGraficacionDispositivo(getNumeroColumna() + 14));
            setAzimut(procesoLeerCamposDesdeExcelParaGraficacionDispositivo(getNumeroColumna() + 15));
            setEficiencia(procesoLeerCamposDesdeExcelParaGraficacionDispositivo(getNumeroColumna() + 16));
            setInformacion(procesoLeerCamposDesdeExcelParaGraficacionDispositivo(getNumeroColumna() + 17));
            setComentario(procesoLeerCamposDesdeExcelParaGraficacionDispositivo(getNumeroColumna() + 18));
            setSpeed(procesoLeerCamposDesdeExcelParaGraficacionDispositivo(getNumeroColumna() + 19));
            setArma(procesoLeerCamposDesdeExcelParaGraficacionDispositivo(getNumeroColumna() + 20));
            setPais(procesoLeerCamposDesdeExcelParaGraficacionDispositivo(getNumeroColumna() + 21));
            filiaciones();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean procesoLeerCamposDesdeExcelParaGraficacionBlanco() {
        try {
            setNombre(procesoLeerCamposDesdeExcelParaGraficacionDispositivo(getNumeroColumna()));
            setLat(coordendas(1, 2, 3));
            setLon(coordendas(4, 5, 6));
            setFiliacion(procesoLeerCamposDesdeExcelParaGraficacionDispositivo(getNumeroColumna() + 7));
            setEstado(procesoLeerCamposDesdeExcelParaGraficacionDispositivo(getNumeroColumna() + 8));
            setTipoBlanco(procesoLeerCamposDesdeExcelParaGraficacionDispositivo(getNumeroColumna() + 9));
            setAltura(procesoLeerCamposDesdeExcelParaGraficacionDispositivo(getNumeroColumna() + 10));
            setFecha(procesoLeerCamposDesdeExcelParaGraficacionDispositivo(getNumeroColumna() + 11));
            setFechaEfectiva(procesoLeerCamposDesdeExcelParaGraficacionDispositivo(getNumeroColumna() + 12));
            setInformacion(procesoLeerCamposDesdeExcelParaGraficacionDispositivo(getNumeroColumna() + 13));
            setPais(procesoLeerCamposDesdeExcelParaGraficacionDispositivo(getNumeroColumna() + 14));
            filiaciones();


        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public ArrayList procesoLeerCamposDesdeExcelParaGraficacionDispositivo(int columna) {
        ArrayList list = new ArrayList();
        for (int i = (getNumeroFila() + 1); i <= getHoja().getLastRowNum(); i++) {
            if (getHoja().getRow(i) != null && getHoja().getRow(i).getCell(columna) != null) {
                if (!getHoja().getRow(i).getCell(columna).toString().isEmpty()) {
                    if (getHoja().getRow(i).getCell(columna).getCellType() == Cell.CELL_TYPE_STRING) {
                        list.add(getHoja().getRow(i).getCell(columna).getStringCellValue());
                    } else if (getHoja().getRow(i).getCell(columna).getCellType() == Cell.CELL_TYPE_FORMULA |
                            getHoja().getRow(i).getCell(columna).getCellType() == Cell.CELL_TYPE_NUMERIC) {
                        list.add(getHoja().getRow(i).getCell(columna).getRawValue());
                    } else {
                        getCampoverificar().add(getHoja().getRow(i).getCell(columna));
                        getRutaExcelVerificar().add(getNombreExcelProcesoIndividualActual());

                    }
                } else {
                    getCampoverificar().add(getHoja().getRow(i).getCell(columna));
                    getRutaExcelVerificar().add(getNombreExcelProcesoIndividualActual());
                }
            }

        }
        return list;
    }

    public ArrayList coordendas(int referenciaGr, int referenciaMt, int referenciaSeg) {
        ArrayList list = new ArrayList();
        for (int i = getNumeroFila() + 1; i <= getHoja().getLastRowNum(); i++) {
            if (getHoja().getRow(i) != null && getHoja().getRow(i).getCell(getNumeroColumna() + referenciaGr) != null
                    && getHoja().getRow(i).getCell(getNumeroColumna() + referenciaMt) != null &&
                    getHoja().getRow(i).getCell(getNumeroColumna() + referenciaSeg) != null) {
                if (!getHoja().getRow(i).getCell(getNumeroColumna() + referenciaGr).toString().isEmpty() &&
                        !getHoja().getRow(i).getCell(getNumeroColumna() + referenciaMt).toString().isEmpty() &&
                        !getHoja().getRow(i).getCell(getNumeroColumna() + referenciaSeg).toString().isEmpty()) {
                    if (getHoja().getRow(i).getCell(getNumeroColumna() + referenciaGr).getCellType() == Cell.CELL_TYPE_NUMERIC &&
                            getHoja().getRow(i).getCell(getNumeroColumna() + referenciaMt).getCellType() == Cell.CELL_TYPE_NUMERIC &&
                            getHoja().getRow(i).getCell(getNumeroColumna() + referenciaSeg).getCellType() == Cell.CELL_TYPE_NUMERIC) {
                        list.add("0" + getHoja().getRow(i).getCell(getNumeroColumna() + referenciaGr).getRawValue() + " " +
                                getHoja().getRow(i).getCell(getNumeroColumna() + referenciaMt).getRawValue() + " " +
                                getHoja().getRow(i).getCell(getNumeroColumna() + referenciaSeg).getRawValue());
                    } else {
                        getCampoverificar().add(getHoja().getRow(i).getCell((getNumeroColumna() + referenciaGr)));
                        getRutaExcelVerificar().add(getNombreExcelProcesoIndividualActual());
                    }
                } else {
                    getCampoverificar().add(getHoja().getRow(i).getCell((getNumeroColumna() + referenciaGr)));
                    getRutaExcelVerificar().add(getNombreExcelProcesoIndividualActual());
                }
            }
        }
        return list;
    }

    public void filiaciones() {
        for (int i = 0; i < getFiliacion().size(); i++) {
            if (getFiliacion().get(i).toString().equalsIgnoreCase("AMIGO")) {
                amigo++;
            } else if (getFiliacion().get(i).toString().equalsIgnoreCase("HOSTIL")) {
                hostil++;
            } else {
                otraFiliacion++;
            }
        }
    }

    public void camposParaVerificar() {
        String correccion = "";
        String nombreExcelCorreccion = "";
        String rutaExcelVerificar = "";
        for (int i = 0; i < getCampoverificar().size(); i++) {
            XSSFCell cells = (XSSFCell) getCampoverificar().get(i);
            correccion = correccion + "\n" + "Celda " + cells.getReference() + " del dispositivo \"" + new File(getRutaExcelVerificar().get(i).toString()).getName() + "\"";
            nombreExcelCorreccion = new File(getRutaExcelVerificar().get(i).toString()).getName();
            rutaExcelVerificar = getRutaExcelVerificar().get(i).toString();
        }

        int respuesta = JOptionPane.showConfirmDialog(null,
                "Error al  cargar dispositivo \"" + nombreExcelCorreccion + "\"\n" +
                        "Esto  puede ocurrir por los siguientes motivos:\n" +
                        "01.Formato de la celda no corresponde a lo esperado \u201cTexto\u201d, \u201d General\u201d o \u201cN\u00famero\u201d.\n" +
                        "02.Dispositivo no se encuentra en la primera p\u00e1gina del Excel a cargar.\n" +
                        "03.Dispositivo tiene campos vac\u00edos o campos activos vac\u00edos.\n" +
                        "Recomendaci\u00f3n:\n" +
                        "Verificar celda(s):\n" +
                        correccion + "\n" + "\u00bfDesea ver un documento de excel con la correcci\u00f3n?\n",
                nombreExcelCorreccion, JOptionPane.YES_NO_OPTION);
        if (respuesta == 0) {
            abrirExcelParaVerificar(getCampoverificar(), rutaExcelVerificar);
        }

    }

    public void abrirExcelParaVerificar(ArrayList celdas, String ruta) {
        for (int i = 0; i < celdas.size(); i++) {
            XSSFCell cells = (XSSFCell) celdas.get(i);
            XSSFCellStyle styleGroup2 = getHoja().getWorkbook().createCellStyle();
            styleGroup2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            styleGroup2.setFillForegroundColor(HSSFColor.RED.index);
            styleGroup2.setBorderBottom(XSSFCellStyle.BORDER_THIN);
            styleGroup2.setBorderTop(XSSFCellStyle.BORDER_THIN);
            styleGroup2.setBorderRight(XSSFCellStyle.BORDER_THIN);
            styleGroup2.setBorderLeft(XSSFCellStyle.BORDER_THIN);
            cells.setCellStyle(styleGroup2);
        }

        try {
            File archivo = new File(rutaExcelVerificar(ruta));
            FileOutputStream salida = new FileOutputStream(archivo);
            getHoja().getWorkbook().write(salida);
            salida.close();
            Desktop.getDesktop().open(archivo);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String rutaExcelVerificar(String ruta) {
        Calendar calen = Calendar.getInstance();
        String fecha = "" + calen.get(Calendar.DAY_OF_MONTH) + calen.get(Calendar.HOUR_OF_DAY) +
                calen.get(Calendar.MINUTE) + calen.get(Calendar.SECOND);
        return ruta.substring(0, ruta.length() - 5) + "(Verificar " + fecha + ").xlsx";
    }


    public boolean esHojaExcelEsperada(String formato) {
        for (int i = 0; i <= 10; i++) {
            for (int j = 0; j <= 10; j++) {
                if (getHoja().getRow(j) != null && getHoja().getRow(j).getCell(i) != null) {
                    Cell cellValue = getHoja().getRow(j).getCell(i);
                    if (cellValue.getCellType() == Cell.CELL_TYPE_STRING && getHoja().getRow(j).getCell(i).getStringCellValue().trim().
                            equalsIgnoreCase(formato)) {
                        setNumeroColumna(i);
                        setNumeroFila(j);
                        return true;
                    }

                }
            }
        }
        return false;
    }

    //GET Y SET_________________________________________________________________________________________________________
    public ArrayList getUnidadSuperior() {
        return unidadSuperior;
    }

    public void setUnidadSuperior(ArrayList unidadSuperior) {
        this.unidadSuperior = unidadSuperior;
    }

    public ArrayList getNombre() {
        return nombre;
    }

    public void setNombre(ArrayList nombre) {
        this.nombre = nombre;
    }

    public ArrayList getLat() {
        return lat;
    }

    public void setLat(ArrayList lat) {
        this.lat = lat;
    }

    public ArrayList getLon() {
        return lon;
    }

    public void setLon(ArrayList lon) {
        this.lon = lon;
    }

    public ArrayList getFiliacion() {
        return filiacion;
    }

    public void setFiliacion(ArrayList filiacion) {
        this.filiacion = filiacion;
    }

    public int getAmigo() {
        return amigo;
    }

    public void setAmigo(int amigo) {
        this.amigo = amigo;
    }

    public int getHostil() {
        return hostil;
    }

    public void setHostil(int hostil) {
        this.hostil = hostil;
    }

    public int getOtraFiliacion() {
        return otraFiliacion;
    }

    public void setOtraFiliacion(int otraFiliacion) {
        this.otraFiliacion = otraFiliacion;
    }

    public ArrayList getEstado() {
        return estado;
    }

    public void setEstado(ArrayList estado) {
        this.estado = estado;
    }

    public ArrayList getSituacion() {
        return situacion;
    }

    public void setSituacion(ArrayList situacion) {
        this.situacion = situacion;
    }

    public ArrayList getTipoBlanco() {
        return tipoBlanco;
    }

    public void setTipoBlanco(ArrayList tipoBlanco) {
        this.tipoBlanco = tipoBlanco;
    }

    public ArrayList getOrdenBatalla() {
        return ordenBatalla;
    }

    public void setOrdenBatalla(ArrayList ordenBatalla) {
        this.ordenBatalla = ordenBatalla;
    }

    public ArrayList getEscalon() {
        return escalon;
    }

    public void setEscalon(ArrayList escalon) {
        this.escalon = escalon;
    }

    public String getNombreExcelesValidosParaGraficar() {
        return nombreExcelesValidosParaGraficar;
    }

    public void setNombreExcelesValidosParaGraficar(String nombreExcelesValidosParaGraficar) {
        this.nombreExcelesValidosParaGraficar = nombreExcelesValidosParaGraficar;
    }

    public ArrayList getNombreExcelImportadoParaCadaUnidad() {
        return nombreExcelImportadoParaCadaUnidad;
    }

    public void setNombreExcelImportadoParaCadaUnidad(ArrayList nombreExcelImportadoParaCadaUnidad) {
        this.nombreExcelImportadoParaCadaUnidad = nombreExcelImportadoParaCadaUnidad;
    }

    public String getNombreExcelProcesoIndividualActual() {
        return NombreExcelProcesoIndividualActual;
    }

    public void setNombreExcelProcesoIndividualActual(String nombreExcelProcesoIndividualActual) {
        this.NombreExcelProcesoIndividualActual = nombreExcelProcesoIndividualActual;
    }

    public ArrayList getFecha() {
        return fecha;
    }

    public void setFecha(ArrayList fecha) {
        this.fecha = fecha;
    }

    public ArrayList getFechaEfectiva() {
        return fechaEfectiva;
    }

    public void setFechaEfectiva(ArrayList fechaEfectiva) {
        this.fechaEfectiva = fechaEfectiva;
    }

    public ArrayList getAltura() {
        return altura;
    }

    public void setAltura(ArrayList altura) {
        this.altura = altura;
    }

    public ArrayList getAzimut() {
        return azimut;
    }

    public void setAzimut(ArrayList azimut) {
        this.azimut = azimut;
    }

    public ArrayList getEficiencia() {
        return eficiencia;
    }

    public void setEficiencia(ArrayList eficiencia) {
        this.eficiencia = eficiencia;
    }

    public ArrayList getInformacion() {
        return informacion;
    }

    public void setInformacion(ArrayList informacion) {
        this.informacion = informacion;
    }

    public ArrayList getComentario() {
        return comentario;
    }

    public void setComentario(ArrayList comentario) {
        this.comentario = comentario;
    }

    public ArrayList getSpeed() {
        return speed;
    }

    public void setSpeed(ArrayList speed) {
        this.speed = speed;
    }

    public ArrayList getArma() {
        return arma;
    }

    public void setArma(ArrayList arma) {
        this.arma = arma;
    }

    public ArrayList getPais() {
        return pais;
    }

    public void setPais(ArrayList pais) {
        this.pais = pais;
    }

    public ArrayList getCampoverificar() {
        return campoverificar;
    }

    public void setCampoverificar(ArrayList campoverificar) {
        this.campoverificar = campoverificar;
    }

    public ArrayList getRutaExcelVerificar() {
        return rutaExcelVerificar;
    }

    public void setRutaExcelVerificar(ArrayList rutaExcelVerificar) {
        this.rutaExcelVerificar = rutaExcelVerificar;
    }

    public XSSFSheet getHoja() {
        return hoja;
    }

    public void setHoja(XSSFSheet hoja) {
        this.hoja = hoja;
    }

    public int getNumeroColumna() {
        return numeroColumna;
    }

    public void setNumeroColumna(int numeroColumna) {
        this.numeroColumna = numeroColumna;
    }

    public int getNumeroFila() {
        return numeroFila;
    }

    public void setNumeroFila(int numeroFila) {
        this.numeroFila = numeroFila;
    }


}
