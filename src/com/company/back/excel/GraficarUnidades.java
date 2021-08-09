package com.company.back.excel;

import com.company.back.PruebaDelete;
import com.luciad.internal.lucy.gui.TLinAccessiblePopupMenuButton;
import com.luciad.lucy.gui.TLcyAlwaysFitJToolBar;
import com.luciad.model.ILcdModel;
import com.luciad.model.TLcdVectorModel;
import com.luciad.reference.TLcdGeodeticReference;
import com.luciad.shape.shape2D.TLcdLonLatPoint;
import com.luciad.symbology.milstd2525b.model.ILcdMS2525bCoded;
import com.luciad.symbology.milstd2525b.model.TLcdEditableMS2525bObject;
import com.luciad.view.ILcdLayer;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.company.ShowReadMeAddOn.getiLcyLucyEnv;

public class GraficarUnidades {
    private ArrayList objects;
    private TLcdEditableMS2525bObject[] editableMS2525bObjects;
    private ArrayList MS2525c, nombreLibro, sistemasArtilleria, nombreExcelImportadoPoraCadaUnidad, nombreExcelesValidosParaGraficar;
    private JProgressBar jbBarraProgreso;
    private JLabel lbProgreso;
    private List listaCapa;
    private Thread thread;
    private long velocidadGraficacion = 0;
    private boolean esValidoParaGraficar = false;
    private String[] sistemas = {"155", "105", "120", "EAGLE", "NIMROD"};

    public GraficarUnidades(ArrayList objects) {
        MS2525c = new ArrayList();
        nombreExcelImportadoPoraCadaUnidad = new ArrayList();
        nombreExcelesValidosParaGraficar = new ArrayList();
        nombreLibro = new ArrayList();
        setObjects(objects);
        esValidoParaGraficar = obtenerSimbologiaParaGraficar();
        listaCapa = listadoCapaSeleccionada();
    }

    //Proceso para generar la capa y simbologia ordenada por el excel(es)_______________________________________________
    public boolean obtenerSimbologiaParaGraficar() {
        try {
            for (int i = 0; i < getObjects().size(); i++) {
                LeerCamposDesdeExcelParaGraficacion unidad = (LeerCamposDesdeExcelParaGraficacion) getObjects().get(i);
                for (int j = 0; j < unidad.getNombre().size(); j++) {
                    TLcdLonLatPoint punto = new TLcdLonLatPoint(-
                            latLonAdouble(unidad.getLon().get(j).toString()),
                            latLonAdouble(unidad.getLat().get(j).toString()));
                    TLcdVectorModel vectorModel = new TLcdVectorModel();
                    vectorModel.setModelReference(new TLcdGeodeticReference());
                    TLcdEditableMS2525bObject MS2525c = new TLcdEditableMS2525bObject(caracteresSimbolo(unidad, j));
                    MS2525c.move2D(punto);
                    MS2525c.putTextModifier(ILcdMS2525bCoded.sHigherFormation, unidad.getUnidadSuperior().get(j).toString());
                    MS2525c.putTextModifier(ILcdMS2525bCoded.sUniqueDesignation, unidad.getNombre().get(j).toString());
                    MS2525c.putTextModifier(ILcdMS2525bCoded.sDateTimeGroup, unidad.getFecha().get(j).toString());
                    MS2525c.putTextModifier(ILcdMS2525bCoded.sAltitudeDepth, unidad.getAltura().get(j).toString());
                    MS2525c.putTextModifier(ILcdMS2525bCoded.sMovementDirection, unidad.getAzimut().get(j).toString());
                    MS2525c.putTextModifier(ILcdMS2525bCoded.sCombatEffectiveness, unidad.getEficiencia().get(j).toString());
                    MS2525c.putTextModifier(ILcdMS2525bCoded.sAdditionalInformation, unidad.getInformacion().get(j).toString());
                    MS2525c.putTextModifier(ILcdMS2525bCoded.sStaffComments, unidad.getComentario().get(j).toString());
                    MS2525c.putTextModifier(ILcdMS2525bCoded.sSpeedLabel, unidad.getSpeed().get(j).toString());
                    MS2525c.putTextModifier(ILcdMS2525bCoded.sReinforcedOrReduced, situacionParaGraficar(unidad.getSituacion().get(j).toString()));
                    getMS2525c().add(MS2525c);
                    getNombreExcelImportadoPoraCadaUnidad().add(unidad.getNombreExcelImportadoParaCadaUnidad().get(j).toString());

                }
                getNombreExcelesValidosParaGraficar().add(unidad.getNombreExcelesValidosParaGraficar());
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List listadoCapaSeleccionada() {
        return getiLcyLucyEnv().getCombinedMapManager().getActiveMapComponent().getSelectedLayersAsList();

    }

    public boolean esCapaValida(ILcdLayer capa) {
        if (!capa.getModel().getModelDescriptor().getTypeName().equalsIgnoreCase("MilStd2525b")) {
            int respuesta = JOptionPane.showConfirmDialog(null, "No es posible crear dispositivo en capa " +
                    capa.getLabel() + " (" + capa.getModel().getModelDescriptor().getTypeName() + ") \n" +
                    "debe seleccionar una capa valida (MS2525c)\n" +
                    "Crear nueva capa MS2525c?", "Capa invalida ", JOptionPane.YES_NO_OPTION);
            if (respuesta == 0) {
                crearCapaMS2525c();
                listaCapa = listadoCapaSeleccionada();
                return true;
            }
        } else {
            return true;
        }


        return false;
    }

    public boolean crearCapaMS2525c() {
        TLcyAlwaysFitJToolBar alwaysFitJToolBar = (TLcyAlwaysFitJToolBar) getiLcyLucyEnv().getCombinedMapManager().getActiveMapComponent().getLayerControl().getActionBar().getComponent();
        JButton button = (JButton) alwaysFitJToolBar.getComponent(0);
        TLinAccessiblePopupMenuButton box = (TLinAccessiblePopupMenuButton) button.getComponent(1).getParent();
        JPopupMenu popupMenu = box.getPopupMenu();
        JMenuItem jMenuItem = (JMenuItem) popupMenu.getComponent(1);
        jMenuItem.doClick();
        return true;
    }

    public void agregarMS2525cParaCapaSelecionada() {
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                if (esValidoParaGraficar) {
                    setVelocidadGraficacion(velocidad(getMS2525c().size()));
                    for (int j = 0; j < listaCapa.size(); j++) {
                        if (esCapaValida((ILcdLayer) listaCapa.get(j))) {
                            for (int i = 0; i < getMS2525c().size(); i++) {
                                try {
                                    ILcdLayer capa = (ILcdLayer) listaCapa.get(j);
                                    double dbCantidadUnidades = getMS2525c().size();
                                    int valorProgreso = (int) ((100 / dbCantidadUnidades) * (i + 1));
                                    capa.getModel().addElement(getMS2525c().get(i), 0);
                                    Object DD = new PruebaDelete();
                                    capa.getModel().addElement(DD, 0);
                                    System.out.println(((PruebaDelete)DD).getPrueba() + " OK PC");

                                    getJbBarraProgreso().setForeground(new Color(0xF6393939, true));
                                    getLbProgreso().setText("Importando  unidades de Excel-->\"" + new File(getNombreExcelImportadoPoraCadaUnidad().get(i).toString()).getName() + "\" a  capa-->\"" + capa.getLabel() + "\"  " + valorProgreso + "%…  ");
                                    getLbProgreso().setToolTipText("Excel-->\"" + new File(getNombreExcelImportadoPoraCadaUnidad().get(i).toString()).getName() + "\" a  capa-->\"" + capa.getLabel() + "\"  " + valorProgreso + "%");
                                    getJbBarraProgreso().setString(((TLcdEditableMS2525bObject) getMS2525c().get(i)).getTextModifierValue(ILcdMS2525bCoded.sUniqueDesignation) + "  " + valorProgreso + "%…  ");
                                    getJbBarraProgreso().setValue(valorProgreso);
                                    thread.sleep(getVelocidadGraficacion());

                                } catch (InterruptedException e) {

                                }
                            }
                            dispositivoGraficadoCorrectamente(getLbProgreso());
                        }
                    }
                }
            }
        });
        thread.start();

    }

    public void dispositivoGraficadoCorrectamente(JLabel labelProgreso) {
        String nombre = "";
        for (int i = 0; i < getNombreExcelesValidosParaGraficar().size(); i++) {
            nombre = nombre + "  " + getNombreExcelesValidosParaGraficar().get(i);
        }
        getJbBarraProgreso().setString("100%");
        labelProgreso.setText("El proceso finalizo, Dispositivo importado correctamente: " + nombre);
        labelProgreso.setToolTipText("Importado correctamente: " + nombre);
        getJbBarraProgreso().setForeground(new Color(0xDA2A4809, true));
    }

    public long velocidad(int cantidadUnidades) {
        if (cantidadUnidades >= 1 && cantidadUnidades <= 10) {
            return 1700;
        } else if (cantidadUnidades >= 11 && cantidadUnidades <= 25) {
            return 1000;
        } else if (cantidadUnidades >= 26 && cantidadUnidades <= 50) {
            return 500;
        } else if (cantidadUnidades >= 51 && cantidadUnidades <= 100) {
            return 250;
        } else if (cantidadUnidades >= 101 && cantidadUnidades <= 200) {
            return 125;
        } else if (cantidadUnidades >= 201 && cantidadUnidades <= 300) {
            return 80;
        } else if (cantidadUnidades >= 301 && cantidadUnidades <= 400) {
            return 60;
        } else if (cantidadUnidades >= 401 && cantidadUnidades <= 500) {
            return 50;
        } else if (cantidadUnidades >= 501 && cantidadUnidades <= 600) {
            return 40;
        } else if (cantidadUnidades >= 601 && cantidadUnidades <= 700) {
            return 35;
        } else if (cantidadUnidades >= 701 && cantidadUnidades <= 800) {
            return 31;
        } else if (cantidadUnidades >= 801 && cantidadUnidades <= 900) {
            return 27;
        } else if (cantidadUnidades > 901) {
            return 25;
        }

      return  200;
    }

    //Metodos auxiliares para conformar las caracterisicas del simbolo__________________________________________________
    public String caracteresSimbolo(LeerCamposDesdeExcelParaGraficacion unidad, int i) {
        String CAMPO1 = "S";
        String FILIACION2 = filiacionParaGraficar(unidad.getFiliacion().get(i).toString());
        String CAMPO3 = "G";
        String ESTADO4 = estadoParaGraficar(unidad.getEstado().get(i).toString());
        String CAMPO5 = ifValor1EsArtDemeValor2SinoValor3(unidad.getNombre().get(i).toString(), "E", "U");
        String CAMPO6 = ifValor1EsArtDemeValor2SinoValor3(unidad.getNombre().get(i).toString(), "W", "C");
        String TIPOUNIDADARMA7 = campo7(unidad.getNombre().get(i).toString(), unidad.getArma().get(i).toString());
        String CAMPO8 = ifValor1EsArtDemeValor2SinoValor3(unidad.getNombre().get(i).toString(), new String[]{"M", "L", "L", "A", "T"}, "-");
        String CAMPO9 = ifValor1EsArtDemeValor2SinoValor3(unidad.getNombre().get(i).toString(), new String[]{"-", "-", "-", "S", "L"}, "-");
        String CAMPO10 = "-";
        String CAMPO11 = "-";
        String ESCALON12 = escalonParaGraficar(unidad.getEscalon().get(i).toString());
        String PAIS13_14 = unidad.getPais().get(i).toString();
        String ORDENBATALLA15 = ordenBatallaParaGraficar(unidad.getOrdenBatalla().get(i).toString());
        return CAMPO1 + FILIACION2 + CAMPO3 + ESTADO4 + CAMPO5 + CAMPO6 + TIPOUNIDADARMA7 + CAMPO8 + CAMPO9 + CAMPO10 +
                CAMPO11 + ESCALON12 + PAIS13_14 + ORDENBATALLA15;


    }

    public String filiacionParaGraficar(String filiacion) {
        switch (filiacion) {
            case "AMIGO":
                return "F";
            case "HOSTIL":
                return "H";
            case "DESCONOCIDO":
                return "U";
            case "NEUTRAL":
                return "N";
            case "ASUME COMO AMIGO":
                return "A";
            case "SOSPECHOSO":
                return "S";
            default:
                return "U";
        }
    }

    public String estadoParaGraficar(String estado) {
        switch (estado) {
            case "INDEFINIDO":
                return "-";
            case "PRESENTE":
                return "P";
            case "ANTICIPADO O PLANEADO":
                return "A";
            default:
                return "";
        }
    }

    public String campo7(String nombre, String Arma_O_Especialidad) {
        String esObus_O_Mortero_O_DefenseAerea = ifValor1EsArtDemeValor2SinoValor3(nombre, new String[]{"H", "H", "O", "M", "M"}, "NO ART");
        if (esObus_O_Mortero_O_DefenseAerea.equalsIgnoreCase("NO ART")) {
            switch (Arma_O_Especialidad) {
                case "ART":
                    return "F";
                case "INF":
                    return "I";
                case "CAB":
                    return "A";
                case "ING":
                    return "E";
            }
        }

        return esObus_O_Mortero_O_DefenseAerea;

    }

    public String ifValor1EsArtDemeValor2SinoValor3(String valor1, String valor2, String valor3) {
        String[] parts = valor1.split(" ");
        for (int i = 0; i < parts.length; i++) {
            for (int i1 = 0; i1 < getSistemas().length; i1++) {
                if (getSistemas()[i1].equalsIgnoreCase(parts[i])) {
                    return valor2;
                }
            }
        }
        return valor3;
    }

    public String ifValor1EsArtDemeValor2SinoValor3(String valor1, String[] valor2, String valor3) {
        String[] parts = valor1.split(" ");
        for (int i = 0; i < parts.length; i++) {
            for (int i1 = 0; i1 < getSistemas().length; i1++) {
                if (getSistemas()[i1].equalsIgnoreCase(parts[i])) {
                    return valor2[i1];
                }
            }
        }
        return valor3;
    }

    public String ordenBatallaParaGraficar(String ordenBatalla) {
        switch (ordenBatalla) {
            case "INDEFINIDO":
                return "-";
            case "AÉREO ":
                return "A";
            case "CIVIL OB":
                return "C";
            case "CONTROL MARCADORES":
                return "X";
            case "ELECTR\u00d3NICO OB":
                return "E";
            case "GRUPO OB":
                return "G";
            case "MAR\u00cdTIMO OB":
                return "N";
            case "FUERZA ESTRAT\u00c9GICA RELATIVA":
                return "S";
            default:
                return "-";
        }
    }

    public String escalonParaGraficar(String escalon) {
        switch (escalon.trim()) {
            case "INDEFINIDO":
                return "-";
            case "EQUIPO COMBATE":
                return "A";
            case "ESCUADRA":
                return "B";
            case "SECCI\u00d3N":
                return "C";
            case "PELOT\u00d3N":
                return "D";
            case "BATER\u00cdA":
                return "E";
            case "BATALL\u00d3N":
                return "F";
            case "REGIMIENTO O GRUPO":
                return "G";
            case "BRIGADA":
                return "H";
            case "DIVISI\u00d3N":
                return "I";
            case "EJ\u00c9RCITO":
                return "K";
            case "MOVILIDAD RASTREADA":
                return "MQ";
            default:
                return "-";

        }
    }

    public String situacionParaGraficar(String situacion) {
        switch (situacion.trim()) {
            case "":
                return "-";
            case "AGREGADO":
                return "D";
            case "SEGREGADO":
                return "R";
            default:
                return "-";
        }
    }

    public double latLonAdouble(String lon) {
        String[] split = lon.split(" ");
        double g = Double.parseDouble(split[0]);
        double m = Double.parseDouble(split[1]);
        double s = Double.parseDouble(split[2]);
        return (g + (m / 60) + (s / 3600));
    }


    //GET Y SET_________________________________________________________________________________________________________
    public ArrayList getObjects() {
        return objects;
    }

    public void setObjects(ArrayList objects) {
        this.objects = objects;
    }

    public boolean isEsValidoParaGraficar() {
        return esValidoParaGraficar;
    }

    public void setEsValidoParaGraficar(boolean esValidoParaGraficar) {
        this.esValidoParaGraficar = esValidoParaGraficar;
    }

    public ArrayList getMS2525c() {
        return MS2525c;
    }

    public void setMS2525c(ArrayList MS2525c) {
        this.MS2525c = MS2525c;
    }

    public TLcdEditableMS2525bObject[] getEditableMS2525bObjects() {
        return editableMS2525bObjects;
    }

    public void setEditableMS2525bObjects(TLcdEditableMS2525bObject[] editableMS2525bObjects) {
        this.editableMS2525bObjects = editableMS2525bObjects;
    }

    public JProgressBar getJbBarraProgreso() {
        return jbBarraProgreso;
    }

    public void setJbBarraProgreso(JProgressBar jbBarraProgreso) {
        this.jbBarraProgreso = jbBarraProgreso;
    }

    public JLabel getLbProgreso() {
        return lbProgreso;
    }

    public void setLbProgreso(JLabel lbProgreso) {
        this.lbProgreso = lbProgreso;
    }

    public ArrayList getNombreLibro() {
        return nombreLibro;
    }

    public void setNombreLibro(ArrayList nombreLibro) {
        this.nombreLibro = nombreLibro;
    }

    public long getVelocidadGraficacion() {
        return velocidadGraficacion;
    }

    public void setVelocidadGraficacion(long velocidadGraficacion) {
        this.velocidadGraficacion = velocidadGraficacion;
    }

    public ArrayList getSistemasArtilleria() {
        return sistemasArtilleria;
    }

    public void setSistemasArtilleria(ArrayList sistemasArtilleria) {
        this.sistemasArtilleria = sistemasArtilleria;
    }

    public ArrayList getNombreExcelImportadoPoraCadaUnidad() {
        return nombreExcelImportadoPoraCadaUnidad;
    }

    public void setNombreExcelImportadoPoraCadaUnidad(ArrayList nombreExcelImportadoPoraCadaUnidad) {
        this.nombreExcelImportadoPoraCadaUnidad = nombreExcelImportadoPoraCadaUnidad;
    }

    public ArrayList getNombreExcelesValidosParaGraficar() {
        return nombreExcelesValidosParaGraficar;
    }

    public void setNombreExcelesValidosParaGraficar(ArrayList nombreExcelesValidosParaGraficar) {
        this.nombreExcelesValidosParaGraficar = nombreExcelesValidosParaGraficar;
    }

    public List getListaCapa() {
        return listaCapa;
    }

    public void setListaCapa(List listaCapa) {
        this.listaCapa = listaCapa;
    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    public String[] getSistemas() {
        return sistemas;
    }

    public void setSistemas(String[] sistemas) {
        this.sistemas = sistemas;
    }


}
