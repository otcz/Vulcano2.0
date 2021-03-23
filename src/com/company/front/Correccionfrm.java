package com.company.front;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Correccionfrm extends JFrame {


    public JTextField txtAlargar,txtAcortar,txtDerecha,txtIzquierda,txtSubir,txtBajar;
    public JPanel panel2;
    public JLabel etiAlar,etiAcor,etiDere,etiIzq,etiSub,etiBaj;
    public JButton BtnGuardar,BtnEliminar;

    public Correccionfrm() {
        setSize(600, 400);
        setTitle("CORRECCIONES");
        setResizable(false);
        setLocation(400,0);

        Panel2();
    }

    private void Panel2() {
        panel2 = new JPanel();
        this.getContentPane().add(panel2);
        panel2.setBounds(10, 500, 450, 550);
        panel2.setLayout(null);
        //panel2.setBackground(Color.blue);
        cajaTextos();
        BotonesCorreccion();

    }

    private void cajaTextos() {
        txtAlargar = new JTextField();
        txtAlargar.setBounds(110, 60, 80, 20);
        txtAlargar.setVisible(true);
        panel2.add(txtAlargar);
        etiAlar = new JLabel("Alargar");
        etiAlar.setBounds(110,40,80,20);
        etiAlar.setFont(new Font("arial", Font.BOLD, 14));
        panel2.add(etiAlar);


        txtAcortar = new JTextField();
        txtAcortar.setBounds(110, 250, 80, 20);
        txtAcortar.setVisible(true);
        panel2.add(txtAcortar);
        etiAcor = new JLabel("Acortar");
        etiAcor.setBounds(110,230,80,20);
        etiAcor.setFont(new Font("arial", Font.BOLD, 14));
        panel2.add(etiAcor);

        txtDerecha = new JTextField();
        txtDerecha.setBounds(30, 155, 80, 20);
        txtDerecha.setVisible(true);
        panel2.add(txtDerecha);
        etiDere = new JLabel("Derecha");
        etiDere.setBounds(30,135,80,20);
        etiDere.setFont(new Font("arial", Font.BOLD, 14));
        panel2.add(etiDere);

        txtIzquierda = new JTextField();
        txtIzquierda.setBounds(190, 155, 80, 20);
        txtIzquierda.setVisible(true);
        panel2.add(txtIzquierda);
        etiIzq = new JLabel("Izquierda");
        etiIzq.setBounds(190,135,80,20);
        etiIzq.setFont(new Font("arial", Font.BOLD, 14));
        panel2.add(etiIzq);


        txtSubir = new JTextField();
        txtSubir.setBounds(410, 50, 80, 20);
        txtSubir.setVisible(true);
        panel2.add(txtSubir);
        etiSub = new JLabel("Subir");
        etiSub.setBounds(410,30,80,20);
        etiSub.setFont(new Font("arial", Font.BOLD, 14));
        panel2.add(etiSub);

        txtBajar= new JTextField();
        txtBajar.setBounds(410, 250, 80, 20);
        txtBajar.setVisible(true);
        panel2.add(txtBajar);
        etiBaj = new JLabel("Bajar");
        etiBaj.setBounds(410,230,80,20);
        etiBaj.setFont(new Font("arial", Font.BOLD, 14));
        panel2.add(etiBaj);

    }

    private void BotonesCorreccion() {

        BtnGuardar = new JButton("Guardar");
        BtnGuardar.setBounds(280, 290, 100, 20);
        BtnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        panel2.add(BtnGuardar);


        BtnEliminar = new JButton("Eliminar");
        BtnEliminar.setBounds(390, 290, 100, 20);
        BtnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        panel2.add(BtnEliminar);
    }

    public void paint(Graphics g) {
        super.paint(g);
        //g.drawLine(150, 155, 50, 255);
        //g.drawLine(140, 155, 50, 355);

    }
}
