package services;

import java.util.ArrayList;

import business.LocacaoDAO;
import model.Message;
import model.Moto.Locacao;
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
            motos = locacaoDAO.getModelMoto(Util.dataFormatSQL(DT_INICIO), Util.dataFormatSQL(DT_FIM), DS_MARCA);
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

    public Message adicionaLocacao(Locacao l){
        Message message;

        try {
            message = locacaoDAO.adicionaLocacao(l);
            for (Opcional opcional : l.opcional) {
                message = locacaoDAO.adicionaOpcional(message.codigo, opcional.CD_OPCIONAL);
            }
            return message;
        } catch (Exception e) {
            message = new Message(false, "error service" + e, -1);
            return message;
        }
    }
}

