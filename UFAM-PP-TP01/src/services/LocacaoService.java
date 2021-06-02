package services;

import java.util.ArrayList;

import business.LocacaoDAO;
import model.Moto.Moto;
import model.Moto.Opcional;
import util.Util;

public class LocacaoService {
    private LocacaoDAO locacaoDAO;

    public LocacaoService(){
        this.locacaoDAO = new LocacaoDAO();
    }
    public ArrayList<String> getDSMoto(String DT_INICIO, String DT_FIM){
        ArrayList<String> motos = new ArrayList<String>();
        
        try {
            motos = locacaoDAO.getDSMoto(Util.dataFormatSQL(DT_INICIO), Util.dataFormatSQL(DT_FIM));
        } catch (Exception e) {
            return null;
        }
        return motos;
    }

    public ArrayList<Moto> getModelMoto(String DT_INICIO, String DT_FIM, String DS_MARCA){
        ArrayList<Moto> motos = new ArrayList<Moto>();
        
        try {
            motos = locacaoDAO.getModelMoto(DT_INICIO, DT_FIM, DS_MARCA);
        } catch (Exception e) {
            return null;
        }
        return motos;
    }

    public ArrayList<Opcional> listaOpcionais(){
        ArrayList<Opcional> opcionais = new ArrayList<Opcional>();

        try {
            opcionais = locacaoDAO.listaOpcionais();
        } catch (Exception e) {
            return null;
        }
        return opcionais;
    }
}
