package services;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import business.MotoDAO;
import business.Categoria.*;
import model.Message;
import model.Categoria.Acessorio;
import model.Moto.AcessorioMoto;
import model.Moto.Moto;


public class MotoService {
    public Moto getMoto(int CD_MOTO){
        Moto motos;
        MotoDAO conectaMotos = new MotoDAO();
        AcessorioDAO conectaAcessorios = new AcessorioDAO();
        try {
            motos = new Moto(conectaMotos.getMoto(CD_MOTO).get(0));
            motos.Acessorios = conectaAcessorios.listarAcessoriosPorMoto(motos.CD_MOTO);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro \n" + e.toString());
            return null;
        }
        return motos;
    }

    public ArrayList<Moto> listarMotos(){
        ArrayList<Moto> motos = new ArrayList<Moto>();
        MotoDAO conectaMotos = new MotoDAO();
        AcessorioDAO conectaAcessorios = new AcessorioDAO();
        try {
            motos = conectaMotos.listarMotos();
            for (Moto moto : motos) {
                moto.Acessorios = conectaAcessorios.listarAcessoriosPorMoto(moto.CD_MOTO);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro \n" + e.toString());
        }

        return motos;
    }

    public ArrayList<model.Categoria.Moto> listarCatMotos(){
        ArrayList<model.Categoria.Moto> motos = new ArrayList<model.Categoria.Moto>();
        business.Categoria.MotoDAO conectaMotos = new business.Categoria.MotoDAO();
        try {
            motos = conectaMotos.listarMotos();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro \n" + e.toString());
        }

        return motos;
    }

    public Message adicionarMoto(Moto moto){
        Message messageMoto;
        Message messageAcessorio;
        MotoDAO motoDAO = new MotoDAO();
        AcessorioDAO acessorioDAO = new AcessorioDAO();
        messageMoto = motoDAO.adicionaMoto(moto);
        try {
            for (Acessorio acessorio : moto.Acessorios) {
                AcessorioMoto am = new AcessorioMoto(messageMoto.codigo, acessorio.CD_ACESSORIO);
                messageAcessorio = acessorioDAO.inserirAcessorioMoto(am);
                if(!messageAcessorio.status){
                    messageMoto = messageAcessorio;
                    break;
                }
            }
        } catch (Exception e) {
        }
        return messageMoto;
    }

    public Message atualizaMoto(Moto moto){
        Message messageMoto;
        Message messageAcessorio;
        MotoDAO motoDAO = new MotoDAO();
        AcessorioDAO acessorioDAO = new AcessorioDAO();
        messageMoto = motoDAO.atualizaMoto(moto);
        try {
            messageAcessorio = acessorioDAO.removeAcessorioMoto(moto.CD_MOTO);
                if(!messageAcessorio.status){
                    Exception era = new Exception();
                    messageAcessorio.message+= " removeacessorio: " + era;
                    messageMoto = messageAcessorio;
                }
            for (Acessorio acessorio : moto.Acessorios) {
                AcessorioMoto am = new AcessorioMoto(messageMoto.codigo, acessorio.CD_ACESSORIO);
                messageAcessorio = acessorioDAO.inserirAcessorioMoto(am);
                if(!messageAcessorio.status){
                    messageMoto = messageAcessorio;
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("errorservice: "+e);
            return null;
        }
        return messageMoto;
    }

    public Boolean removeMoto(int CD_MOTO){
        Boolean respostaMoto;
        Boolean respostaAcessorio;
        MotoDAO motoDAO = new MotoDAO();
        AcessorioDAO acessorioDAO = new AcessorioDAO();
        try {
            respostaMoto = motoDAO.removeMoto(CD_MOTO);
            System.out.println(respostaMoto);
            respostaAcessorio = acessorioDAO.removeAcessorioMoto(CD_MOTO).status;  
            if(!respostaAcessorio && !respostaMoto){
                Exception removM = new Exception("Erro ao remover moto");
                removM.notify();
            }           
        } catch (Exception e) {
            System.out.println("errorservice: "+e);
            return null;
        }
        return respostaMoto;
    }
}
