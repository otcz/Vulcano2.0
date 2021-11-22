package administrarMunicion.D_nuevoTipoMunicion.modelo;

import java.util.ArrayList;

public class TipoMuni {
    ArrayList<TipoMuni> lotes = new ArrayList<>();
    String lote;
    String TipoSistema;
    String tipoMuni;


    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getTipoSistema() {
        return TipoSistema;
    }

    public void setTipoSistema(String tipoSistema) {
        TipoSistema = tipoSistema;
    }

    public String getTipoMuni() {
        return tipoMuni;
    }

    public void setTipoMuni(String tipoMuni) {
        this.tipoMuni = tipoMuni;
    }

    public ArrayList<TipoMuni> getLotes() {
        return lotes;
    }

    public void setLotes(ArrayList<TipoMuni> lotes) {
        this.lotes = lotes;
    }
}
