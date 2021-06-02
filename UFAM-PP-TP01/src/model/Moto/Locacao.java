package model.Moto;

import java.util.ArrayList;

public class Locacao {
    public int    CD_LOCACAO;
    public int    CD_MOTO;
    public int    CD_CLIENTE;
    public String DT_RETIRADA;
    public String LC_RETIRADA;
    public String DT_DEVOLUCAO;
    public String LC_DEVOLUCAO;
    public String ST_LOCACAO;
    public ArrayList<Opcional> opcional;

    public Locacao(
        int    CD_LOCACAO,
        int    CD_MOTO,
        int    CD_CLIENTE,
        String DT_RETIRADA,
        String LC_RETIRADA,
        String DT_DEVOLUCAO,
        String LC_DEVOLUCAO,
        String ST_LOCACAO,
        ArrayList<Opcional> opcional
    ){
        this.CD_LOCACAO = CD_LOCACAO;
        this.CD_MOTO = CD_MOTO;
        this.CD_CLIENTE = CD_CLIENTE;
        this.DT_RETIRADA = DT_RETIRADA;
        this.LC_RETIRADA = LC_RETIRADA;
        this.DT_DEVOLUCAO = DT_DEVOLUCAO;
        this.LC_DEVOLUCAO = LC_DEVOLUCAO;
        this.ST_LOCACAO = ST_LOCACAO;
        this.opcional = opcional;
    }
}
