package business;

import model.Cliente.Cliente;
import model.Moto.Moto;

public class Locacao {
    public int CD_LOCACAO;
    public Moto moto;
    public Cliente cliente;
    public String DT_RETIRADA;
    public String LC_RETIRADA;
    public String DT_DEVOLUCAO;
    public String LC_DEVOLUCAO;

    public Locacao(Moto moto, Cliente cliente){
        this.moto = moto;
        this.cliente = cliente;
    }

    public Locacao(){
        
    }
}
