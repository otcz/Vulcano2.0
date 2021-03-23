package com.company.front;

import com.company.back.*;
import com.luciad.lucy.ILcyLucyEnv;

import com.luciad.shape.ILcdPoint;
import com.luciad.shape.shape3D.TLcdLonLatHeightPoint;
import com.luciad.text.TLcdLonLatPointFormat;
import com.luciad.view.ILcdView;
import com.luciad.view.gxy.ILcdGXYView;
import com.luciad.view.lightspeed.ILspView;

import javax.swing.*;


public class ToolBar {
    private static ToolBar toolBar;
    ILcdPoint puntoInicio = new TLcdLonLatHeightPoint(0, 0, 0);
    public boolean salir = false;
    boolean salir2D = false;
    boolean salir3D = false;
    public static ILspView vista3D;
    public static ILcdGXYView vista2D;
    public static JButton botonCDT = new JButton("CDT");
    public JButton btnInicio3D = new JButton("Inicio");
    public JButton btnInicio2D = new JButton("Inicio");
    public ILcyLucyEnv fLucyEnv;
    public JToolBar toolBar2D = new JToolBar();
    public JToolBar toolBar3D = new JToolBar();
    public static ILcdView vistaActiva;
    public Thread thread = null;


    public ToolBar() {
    }

    public ToolBar(ILcyLucyEnv iLcyLucyEnv) {
        TLcdLonLatPointFormat format = new TLcdLonLatPointFormat(TLcdLonLatPointFormat.DEFAULT2);
        setfLucyEnv(iLcyLucyEnv);
        String previousValue = new String(format.format(puntoInicio.getX(), puntoInicio.getY()));
        JFormattedTextField texCoordenadas3D = new JFormattedTextField(format);
        texCoordenadas3D.setText(previousValue);
        texCoordenadas3D.setFocusLostBehavior(JFormattedTextField.COMMIT_OR_REVERT);
        JFormattedTextField texCoordenadas2D = new JFormattedTextField(format);
        texCoordenadas2D.setText(previousValue);
        texCoordenadas2D.setFocusLostBehavior(JFormattedTextField.COMMIT_OR_REVERT);
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                do {
                    thread.interrupt();

                    if (getfLucyEnv().getCombinedMapManager().getActiveMapComponent() != null) {
                        if (getfLucyEnv().getCombinedMapManager().getActiveMapComponent().getMainView() instanceof ILspView && salir3D == false) {
                            toolBar3D = (JToolBar) getfLucyEnv().getCombinedMapManager().getActiveMapComponent().getToolBar().getComponent();
                            toolBar3D.add(texCoordenadas3D);
                            toolBar3D.add(botonCDT);
                            toolBar3D.add(btnInicio3D);
                            texCoordenadas3D.setEnabled(false);
                            salir3D = true;
                            vista3D = (ILspView) getfLucyEnv().getCombinedMapManager().getActiveMapComponent().getMainView();
                            salir = true;
                            vista3D.addLayerSelectionListener(new SeleccionObjetoVista(texCoordenadas3D));
                            vista3D.addLayerModelListener(new EventoModelosVista());
                            vista3D.addLayerModelListener(new ActualizaTexCoordenadasToolBar(texCoordenadas3D));
                            btnInicio3D.addActionListener(new AreaInicio3D(null, vista3D));
                            setVistaActiva(getfLucyEnv().getCombinedMapManager().getActiveMapComponent().getMainView());
                        } else if (getfLucyEnv().getCombinedMapManager().getActiveMapComponent().getMainView() instanceof ILcdGXYView && salir2D == false) {
                            toolBar2D = (JToolBar) fLucyEnv.getCombinedMapManager().getActiveMapComponent().getToolBar().getComponent();
                            toolBar2D = (JToolBar) fLucyEnv.getCombinedMapManager().getActiveMapComponent().getToolBar().getComponent();
                            toolBar2D.add(texCoordenadas2D);
                            toolBar2D.add(botonCDT);
                            toolBar2D.add(btnInicio2D);
                            texCoordenadas2D.setEnabled(false);
                            salir2D = true;
                            vista2D = (ILcdGXYView) fLucyEnv.getCombinedMapManager().getActiveMapComponent().getMainView();
                            salir = true;
                            vista2D.addLayerSelectionListener(new SeleccionObjetoVista(texCoordenadas2D));
                            vista2D.addModelListener(new EventoModelosVista());
                            vista2D.addModelListener(new ActualizaTexCoordenadasToolBar(texCoordenadas2D));
                            btnInicio2D.addActionListener(new AreaInicio2D(null, vista2D));
                            setVistaActiva(getfLucyEnv().getCombinedMapManager().getActiveMapComponent().getMainView());
                        }
                    }

                }
                while (salir3D == false);

            }

        });

    }


    public static ILcdView getVistaActiva() {
        return vistaActiva;
    }

    public static void setVistaActiva(ILcdView vistaActiva) {
        ToolBar.vistaActiva = vistaActiva;
    }

    public static ILspView getVista3D() {
        return vista3D;
    }

    public static void setVista3D(ILspView vista3D) {
        ToolBar.vista3D = vista3D;
    }

    public static ILcdGXYView getVista2D() {
        return vista2D;
    }

    public static void setVista2D(ILcdGXYView vista2D) {
        ToolBar.vista2D = vista2D;
    }

    public ILcyLucyEnv getfLucyEnv() {
        return fLucyEnv;
    }

    public void setfLucyEnv(ILcyLucyEnv fLucyEnv) {
        this.fLucyEnv = fLucyEnv;
    }

    public static ToolBar getSingletonInstance() {
        if (toolBar == null) {
            toolBar = new ToolBar();
        } else {

        }
        return toolBar;
    }


}
