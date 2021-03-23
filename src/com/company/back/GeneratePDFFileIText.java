package com.company.back;

import com.company.balistic.Configuracion;
import com.company.balistic.Posicion;
import com.company.balistic.Trayectoria;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;

public class GeneratePDFFileIText {
    // Fonts definitions (Definición de fuentes).
    private static final Font capituloFuente = FontFactory.getFont(FontFactory.HELVETICA, 26, Font.BOLDITALIC);
    private static final Font parrafoFuente = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL);
    private static final Font categoryFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
    private static final Font blueFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);
    private static final Font pequeñaNegrita = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
    public String sDistancia;
    public String sAzimut;
    public String sIntervalo;
    public String sSituacion;
    public String sOrientacion;
    public String sCuadrante;
    public String sTiempoVuelo;
    public String scarga;


    Posicion posicion;
    Trayectoria trayectoria;
    Configuracion configuracion;
    private static final String iTextExampleImage = "src/images/logo_small_white.png";


    public GeneratePDFFileIText() {

    }

    public void generarPDF() {
        try {
            String ruraParaGuardarPDF = rutaParaGuardarPDF();
            Document document = new Document(PageSize.A4, 35, 30, 50, 50);
            FileOutputStream fileOutputStream = new FileOutputStream(ruraParaGuardarPDF);
            PdfWriter writer = PdfWriter.getInstance(document, fileOutputStream);
            writer.getPageEvent();
            document.open();
            document.addTitle("T(Exportamos la tabla a PDF)");
            document.addSubject("Usando iText (usando iText");
            document.addKeywords("Java, PDF, iText");
            document.addAuthor("Código Xules");
            document.addCreator("Código Xules");
            Image image = null;
            image = Image.getInstance((getClass().getResource("logo_small_white.png")));
            image.scaleAbsolute(30f, 40f);
            image.setAlignment(Element.ALIGN_CENTER);
            PdfPTable table = new PdfPTable(2);
            PdfPCell cell = new PdfPCell(new Paragraph("                                              DATOS DE TIRO "));
            cell.setColspan(2);
            table.addCell(cell);
            table.addCell("Distancia ");
            table.addCell(" " + getsDistancia());
            table.addCell("Azimut  ");
            table.addCell(" " + getsDistancia());
            table.addCell("Intervalo vertical  ");
            table.addCell(" " + getsIntervalo());
            table.addCell("Situaci\u00f3n  ");
            table.addCell(" " + getsSituacion());
            table.addCell("Orientaci\u00f3n  ");
            table.addCell(" " + getsOrientacion());
            table.addCell("Cuadrante ");
            table.addCell(" " + getsCuadrante());
            table.addCell("Tiempo de Vuelo");
            table.addCell(" " + getsTiempoVuelo());
            table.addCell("Carga ");
            table.addCell(" " + getScarga());

            // Creacion del parrafo.
            Paragraph paragraph = new Paragraph();
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(new Phrase("                              FUERZAS MILITARES DE COLOMBIA ", categoryFont));
            document.add(new Phrase("                                                                                 EJERCITO NACIONAL ", categoryFont));
            document.add(Chunk.NEWLINE);
            document.add(image);
            document.add(new Phrase("               JEFATURA DE ESTADO MAYOR DE OPERACIONES ", categoryFont));
            document.add(new Phrase("                                                  COMANDO DE APOYO DE FUEGOS ", categoryFont));
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
// Agregar contenido con su respectiva fuente
            // document.add(new Phrase("Distancia : " + getsDistancia(), categoryFont));
            //  document.add(Chunk.NEWLINE);
            //  document.add(new Phrase(" Azimut : " + getsAzimut(), categoryFont));
            // document.add(Chunk.NEWLINE);
            // document.add(new Phrase(" Intervalo vertical: " + getsIntervalo(), categoryFont));
            //  document.add(Chunk.NEWLINE);
            //  document.add(new Phrase(" Cuadrante : " + getsCuadrante(), categoryFont));
            // document.add(Chunk.NEWLINE);
            // document.add(new Phrase(" Tiempo de Vuelo : " + getsTiempoVuelo(), categoryFont));
            // document.add(Chunk.NEWLINE);
            // document.add(new Phrase(" Carga : " + getScarga(), categoryFont));
            // document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);

// Agregar el parrafo al documento
            document.add(paragraph);
            document.add(table);
            document.close();
            File file = new File(ruraParaGuardarPDF);
            Desktop.getDesktop().open(file);


        } catch (DocumentException | IOException documentException) {
            System.out.println("(Se ha producido un error al generar un documento): " + documentException);
        }

    }

    public String rutaParaGuardarPDF() {

        JFileChooser file = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Bolet\u00edn de fuegos ", "pdf");
        file.setFileFilter(filter);
        file.setFileSelectionMode(JFileChooser.CUSTOM_DIALOG);
        file.setApproveButtonText("Guardar bolet\u00edn ");
        file.setApproveButtonToolTipText("Guardar bolet\u00edn de fuegos");
        int returnSelection = file.showOpenDialog(null);
        if (returnSelection == JFileChooser.APPROVE_OPTION) {
            if (file.getSelectedFile().toString().endsWith(".pdf")) {
                return file.getSelectedFile().getAbsoluteFile().getPath();
            } else {
                return file.getSelectedFile().getAbsoluteFile().getPath() + ".pdf";
            }

        }
        return null;
    }


    public String getsDistancia() {
        return sDistancia;
    }

    public void setsDistancia(String sDistancia) {
        this.sDistancia = sDistancia;
    }

    public String getsAzimut() {
        return sAzimut;
    }

    public void setsAzimut(String sAzimut) {
        this.sAzimut = sAzimut;
    }

    public String getsIntervalo() {
        return sIntervalo;
    }

    public void setsIntervalo(String sIntervalo) {
        this.sIntervalo = sIntervalo;
    }

    public String getsCuadrante() {
        return sCuadrante;
    }

    public void setsCuadrante(String sCuadrante) {
        this.sCuadrante = sCuadrante;
    }

    public String getScarga() {
        return scarga;
    }

    public void setScarga(String scarga) {
        this.scarga = scarga;
    }

    public String getsTiempoVuelo() {
        return sTiempoVuelo;
    }

    public void setsTiempoVuelo(String sTiempoVuelo) {
        this.sTiempoVuelo = sTiempoVuelo;
    }

    public String getsSituacion() {
        return sSituacion;
    }

    public void setsSituacion(String sSituacion) {
        this.sSituacion = sSituacion;
    }

    public String getsOrientacion() {
        return sOrientacion;
    }

    public void setsOrientacion(String sOrientacion) {
        this.sOrientacion = sOrientacion;
    }


}
