package services;

import java.util.ArrayList;

import business.Categoria.AcessorioDAO;
import model.Categoria.Acessorio;

public class AcessorioService {
    public ArrayList<Acessorio> listarAcessorios(){
        ArrayList<Acessorio> Acessorios;
        AcessorioDAO acessorioDAO = new AcessorioDAO();
        Acessorios = acessorioDAO.listarAcessorios();
        return Acessorios;
    }
}
