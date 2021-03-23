package com.company;
import com.company.balistic.Posicion;
import com.company.front.ControlCapa;
import com.company.front.ToolBar;
import com.luciad.lucy.ILcyLucyEnv;
import com.luciad.lucy.addons.ALcyPreferencesAddOn;
import com.luciad.lucy.gui.ALcyApplicationPaneTool;
import com.luciad.lucy.gui.ILcyApplicationPane;
import javax.swing.*;
import java.awt.*;


public class ShowReadMeAddOn extends ALcyPreferencesAddOn {
    Posicion posicion;
    private static ShowReadMeAddOn showReadMeAddOn;
    public static ALcyApplicationPaneTool fApplicationPaneTool;
    public static JPanel content = new JPanel(new BorderLayout());
    public static ILcyLucyEnv iLcyLucyEnv;



    public ShowReadMeAddOn() {
        super("samples.lucy.showreadme.", "ShowReadMeAddOn.");

    }

    @Override
    public void plugInto(ILcyLucyEnv aLucyEnv) {
        super.plugInto(aLucyEnv);
        fApplicationPaneTool = new MyApplicationPaneTool();
        fApplicationPaneTool.plugInto(aLucyEnv);
        setiLcyLucyEnv(aLucyEnv);
        new ToolBar();
        new ControlCapa();
    }

    @Override
    public void unplugFrom(ILcyLucyEnv aLucyEnv) {
        super.unplugFrom(aLucyEnv);
        fApplicationPaneTool.unplugFrom(aLucyEnv);
        fApplicationPaneTool = null;
    }

    private String getTextFileName() {
        return getPreferences().getString("ShowReadMeAddOn.ReadMeFile", null);
    }

    public class MyApplicationPaneTool extends ALcyApplicationPaneTool {

        @Override
        public ILcyApplicationPane getApplicationPane() {
            return super.getApplicationPane();
        }

        public MyApplicationPaneTool() {
            super(ShowReadMeAddOn.this.getPreferences(),
                    ShowReadMeAddOn.this.getLongPrefix(),
                    ShowReadMeAddOn.this.getShortPrefix());
        }

        @Override
        protected Component createContent() {
            content.setBackground(Color.getColor("#727272"));
            setContent(content);
            return content;

        }


    }

    public static ShowReadMeAddOn getSingletonInstance() {
        if (showReadMeAddOn == null) {
            showReadMeAddOn = new ShowReadMeAddOn();
        } else {

        }
        return showReadMeAddOn;
    }

    public Posicion getPosicion() {
        return posicion;
    }

    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }

    public static ILcyLucyEnv getiLcyLucyEnv() {
        return iLcyLucyEnv;
    }

    public static void setiLcyLucyEnv(ILcyLucyEnv iLcyLucyEnv) {
        ShowReadMeAddOn.iLcyLucyEnv = iLcyLucyEnv;
    }

    public static JPanel getContent() {
        return content;
    }

    public static void setContent(JPanel content) {
        ShowReadMeAddOn.content = content;
    }

    public ALcyApplicationPaneTool getfApplicationPaneTool() {
        return fApplicationPaneTool;
    }

    public void setfApplicationPaneTool(ALcyApplicationPaneTool fApplicationPaneTool) {
        this.fApplicationPaneTool = fApplicationPaneTool;
    }
}
