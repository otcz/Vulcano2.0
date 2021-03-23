package com.company.front;

import com.company.back.JButtonTrayectoria;
import com.luciad.text.TLcdLonLatPointFormat;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class DatosTirofrm extends JFrame {

    public JLabel lbDistancia, lbAzimut, lbSituacion, lbOrientacion, lbCuadrante, lbTiempoVuelo, lbCoordenadasTitulo,
            lbCarga, lbBlanco, lbArma, lbObservadorAdelantado, lbMunicion, lbTrayectoria, lbIntervalo,
            lbPrintTitulo, lbCoordenadasN, lbCoordenadasS, lbTexBlanco, lbTexArma, lbTexSituacion, lbTexOrientacion, lbTexCuadrante,
            lbTexCoordenadasN, lbTexCoordenadasS, lbTexMunicion1A, lbTexIntervalo, lbTexTiempoVuelo, lbTexCarga, lbTexCoordenadasOA,
            lbTexCoordenadasBlanco, lbTexCoordenadasArma;

    public JFormattedTextField texResultadoCoordenadasA, texResultadoCoordenadasB, texResultadoCoordenadasC;

    public JComboBox cbBlanco, cbObservadorAdelantado, cbTrayectoria, cbMunicion, cbCode, cbArma;
    public JPanel pPanel;
    public JButton btnGuardar, btnEliminar, btnCorreccion, btnCalcular, btnImprimir;
    public TLcdLonLatPointFormat formato = new TLcdLonLatPointFormat(TLcdLonLatPointFormat.DEFAULT2);

    public  JButtonTrayectoria btnTrayectoria;

    public DatosTirofrm() {
        setSize(450, 750);
        setTitle("DATOS DE TIRO ESTANDAR");
        setResizable(false);
        setVisible(true);
        Panel1();
        setBackground(Color.BLUE);

    }

    private void Panel1() {

        pPanel = new JPanel();
        this.getContentPane().add(pPanel);
        pPanel.setBounds(0, 0, 450, 850);
        pPanel.setLayout(null);
        pPanel.setOpaque(true);
        EtiquetasComboBox();
        EtiquetasCoordendas();
        Combobox();
        etiDatos();
        Spinner();
        BotonesArma();
        EtiquetasResultadosDatos();
        resultadoEtiCoordenadas();


        //paintComponent(Graphics g);
    }

    private void EtiquetasComboBox() {

        lbPrintTitulo = new JLabel("Datos de tiro");
        lbPrintTitulo.setBounds(10, 1, 400, 30);
        lbPrintTitulo.setVisible(true);
        lbPrintTitulo.setFont(new Font("britannic bold", Font.BOLD, 20));
        pPanel.add(lbPrintTitulo);

        lbBlanco = new JLabel("Blanco:");
        lbBlanco.setFont(new Font("arial", Font.BOLD, 16));
        lbBlanco.setBounds(10, 50, 180, 20);
        pPanel.add(lbBlanco);

        lbArma = new JLabel("Arma:");
        lbArma.setFont(new Font("arial", Font.BOLD, 16));
        lbArma.setBounds(10, 80, 180, 20);
        pPanel.add(lbArma);

        lbObservadorAdelantado = new JLabel("O.A:");
        lbObservadorAdelantado.setFont(new Font("arial", Font.BOLD, 16));
        lbObservadorAdelantado.setBounds(10, 110, 180, 20);
        pPanel.add(lbObservadorAdelantado);

        lbMunicion = new JLabel("Municion:");
        lbMunicion.setFont(new Font("arial", Font.BOLD, 16));
        lbMunicion.setBounds(10, 140, 180, 20);
        pPanel.add(lbMunicion);

        lbTrayectoria = new JLabel("Trayectoria:");
        lbTrayectoria.setFont(new Font("arial", Font.BOLD, 16));
        lbTrayectoria.setBounds(10, 170, 180, 20);
        pPanel.add(lbTrayectoria);


    }

    private void Combobox() {

        //ComboBox de la  etiqueta tamaño
        cbBlanco = new JComboBox(new String[]{"Item 1", "Item 2", "Item 3", "Item 4"});
        cbBlanco.setBounds(110, 50, 180, 20);
        pPanel.add(cbBlanco);
        cbArma = new JComboBox(new String[]{"Item 1", "Item 2", "Item 3", "Item 4"});
        cbArma.setBounds(110, 80, 180, 20);
        cbArma.setVisible(true);
        pPanel.add(cbArma);
        cbObservadorAdelantado = new JComboBox(new String[]{"Item 1", "Item 2", "Item 3", "Item 4"});
        cbObservadorAdelantado.setBounds(110, 110, 180, 20);
        pPanel.add(cbObservadorAdelantado);
        cbMunicion = new JComboBox(new String[]{"Item 1", "Item 2", "Item 3", "Item 4"});
        cbMunicion.setBounds(110, 140, 180, 20);
        pPanel.add(cbMunicion);
        cbTrayectoria = new JComboBox(new String[]{"Item 1", "Item 2", "Item 3", "Item 4"});
        cbTrayectoria.setBounds(110, 170, 180, 20);
        pPanel.add(cbTrayectoria);

    }

    private void EtiquetasCoordendas() {

        lbCoordenadasTitulo = new JLabel("Coordenadas");
        lbCoordenadasTitulo.setBounds(150, 200, 150, 20);
        lbCoordenadasTitulo.setVisible(true);
        lbCoordenadasTitulo.setFont(new Font("britannic bold", Font.BOLD, 20));
        pPanel.add(lbCoordenadasTitulo);


        lbTexCoordenadasOA = new JLabel("OA");
        lbTexCoordenadasOA.setFont(new Font("arial", Font.BOLD, 16));
        lbTexCoordenadasOA.setBounds(10, 230, 180, 20);
        pPanel.add(lbTexCoordenadasOA);

        lbTexCoordenadasBlanco = new JLabel("Blanco");

        lbTexCoordenadasBlanco.setFont(new Font("arial", Font.BOLD, 16));
        lbTexCoordenadasBlanco.setBounds(10, 260, 180, 20);
        pPanel.add(lbTexCoordenadasBlanco);

        lbTexCoordenadasArma = new JLabel("Arma");
        lbTexCoordenadasArma.setFont(new Font("arial", Font.BOLD, 16));
        lbTexCoordenadasArma.setBounds(10, 290, 180, 20);
        pPanel.add(lbTexCoordenadasArma);
    }

    private void resultadoEtiCoordenadas() {
        texResultadoCoordenadasA = new JFormattedTextField(formato);
        texResultadoCoordenadasA.setBounds(110, 230, 180, 20);
        pPanel.add(texResultadoCoordenadasA);
        texResultadoCoordenadasA.setText(formato.format(0, 0));

        texResultadoCoordenadasB = new JFormattedTextField(formato);
        texResultadoCoordenadasB.setBounds(110, 260, 180, 20);
        pPanel.add(texResultadoCoordenadasB);
        texResultadoCoordenadasB.setText(formato.format(0, 0));


        texResultadoCoordenadasC = new JFormattedTextField(formato);
        texResultadoCoordenadasC.setBounds(110, 290, 180, 20);
        pPanel.add(texResultadoCoordenadasC);
        texResultadoCoordenadasC.setText(formato.format(0, 0));


    }

    private void etiDatos() {

        lbTexArma = new JLabel();
        lbTexArma.setBounds(110, 340, 180, 20);
        pPanel.add(lbTexArma);

        lbTexIntervalo = new JLabel();
        lbTexIntervalo.setBounds(110, 370, 180, 20);
        pPanel.add(lbTexIntervalo);

        lbTexSituacion = new JLabel();
        lbTexSituacion.setBounds(110, 400, 180, 20);
        pPanel.add(lbTexSituacion);

        lbTexOrientacion = new JLabel();
        lbTexOrientacion.setBounds(110, 430, 180, 20);
        pPanel.add(lbTexOrientacion);

        lbTexCuadrante = new JLabel();
        lbTexCuadrante.setBounds(110, 460, 180, 20);
        pPanel.add(lbTexCuadrante);

        lbTexTiempoVuelo = new JLabel();
        lbTexTiempoVuelo.setBounds(110, 490, 180, 20);
        pPanel.add(lbTexTiempoVuelo);

        lbTexCarga = new JLabel();
        lbTexCarga.setBounds(110, 520, 180, 20);
        pPanel.add(lbTexCarga);

        lbTexCoordenadasS = new JLabel();
        lbTexCoordenadasS.setBounds(110, 550, 180, 20);
        pPanel.add(lbTexCoordenadasS);
    }

    private void EtiquetasResultadosDatos() {

        lbDistancia = new JLabel("Distancia:");
        lbDistancia.setFont(new Font("arial", Font.BOLD, 16));
        lbDistancia.setBounds(10, 340, 180, 20);
        pPanel.add(lbDistancia);

        JLabel Distancia = new JLabel();
        Distancia.setFont(new Font("arial", Font.ITALIC, 22));
        Distancia.setBounds(110, 340, 180, 20);
        pPanel.add(Distancia);

        lbAzimut = new JLabel("Azimut:");
        lbAzimut.setFont(new Font("arial", Font.BOLD, 16));
        lbAzimut.setBounds(10, 370, 180, 20);
        pPanel.add(lbAzimut);

        JLabel Azimut = new JLabel();
        Azimut.setFont(new Font("arial", Font.ITALIC, 22));
        Azimut.setBounds(110, 370, 180, 20);
        pPanel.add(Azimut);

        lbIntervalo = new JLabel("Intervalo:");
        lbIntervalo.setFont(new Font("arial", Font.BOLD, 16));
        lbIntervalo.setBounds(10, 400, 180, 20);
        pPanel.add(lbIntervalo);

        JLabel Intervalo = new JLabel();
        Intervalo.setFont(new Font("arial", Font.ITALIC, 22));
        Intervalo.setBounds(110, 400, 180, 20);
        pPanel.add(Intervalo);

        lbSituacion = new JLabel("Situacion:");
        lbSituacion.setFont(new Font("arial", Font.BOLD, 16));
        lbSituacion.setBounds(10, 430, 180, 20);
        pPanel.add(lbSituacion);

        JLabel situacion = new JLabel();
        situacion.setFont(new Font("arial", Font.ITALIC, 22));
        situacion.setBounds(110, 430, 180, 20);
        pPanel.add(situacion);

        lbOrientacion = new JLabel("Orientación:");
        lbOrientacion.setFont(new Font("arial", Font.BOLD, 16));
        lbOrientacion.setBounds(10, 460, 180, 20);
        pPanel.add(lbOrientacion);

        JLabel Orientacion = new JLabel();
        Orientacion.setFont(new Font("arial", Font.ITALIC, 22));
        Orientacion.setBounds(110, 460, 180, 20);
        pPanel.add(Orientacion);

        lbCuadrante = new JLabel("Cuadrante:");
        lbCuadrante.setFont(new Font("arial", Font.BOLD, 16));
        lbCuadrante.setBounds(10, 490, 180, 20);
        pPanel.add(lbCuadrante);

        JLabel Cuadrante = new JLabel();
        Cuadrante.setFont(new Font("arial", Font.ITALIC, 22));
        Cuadrante.setBounds(110, 490, 180, 20);
        pPanel.add(Cuadrante);

        lbTiempoVuelo = new JLabel("Tiempo de V:");
        lbTiempoVuelo.setFont(new Font("arial", Font.BOLD, 16));
        lbTiempoVuelo.setBounds(10, 520, 180, 20);
        pPanel.add(lbTiempoVuelo);

        JLabel TiempoVuelo = new JLabel();
        TiempoVuelo.setFont(new Font("arial", Font.ITALIC, 22));
        TiempoVuelo.setBounds(110, 520, 180, 20);
        pPanel.add(TiempoVuelo);

        lbCarga = new JLabel("Carga:");
        lbCarga.setFont(new Font("arial", Font.BOLD, 16));
        lbCarga.setBounds(10, 550, 180, 20);
        pPanel.add(lbCarga);

        JLabel Carga = new JLabel();
        Carga.setFont(new Font("arial", Font.ITALIC, 22));
        Carga.setBounds(110, 550, 180, 20);
        pPanel.add(Carga);

    }

    private void Spinner() {


    }

    private void BotonesArma() {

        btnCalcular = new JButton("Calcular");
        btnCalcular.setBounds(300, 400, 100, 20);
        pPanel.add(btnCalcular);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(300, 430, 100, 20);
        pPanel.add(btnEliminar);

        btnImprimir = new JButton(new ImageIcon(getClass().getResource("impresoraPDF.png")));
        btnImprimir.setBounds(300, 330, 100, 60);
        pPanel.add(btnImprimir);

        btnCorreccion = new JButton("Correccion");
        btnCorreccion.setBounds(300, 460, 100, 20);
        btnCorreccion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Correccionfrm Nuevo = new Correccionfrm();
                Nuevo.setVisible(true);
            }
        });
        pPanel.add(btnCorreccion);


        btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(300, 490, 100, 20);
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListadoDatosfrm Nuevo = new ListadoDatosfrm();
                Nuevo.setVisible(true);
            }
        });
        pPanel.add(btnGuardar);

        btnTrayectoria= new JButtonTrayectoria();
        btnTrayectoria.setEnabled(false);
        btnTrayectoria.setDatosTirofrm(this);
        btnTrayectoria.setBounds(300, 530, 100, 40);

        pPanel.add(btnTrayectoria);

    }

    public void paint(Graphics g) {
        super.paint(g);
        g.drawLine(450, 255, 50, 255);
        g.drawLine(450, 355, 50, 355);

    }



}



