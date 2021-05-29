package model.Cliente;

public class Cliente {
    public int CD_CLIENTE;
    public String NM_CLIENTE;
    public String DT_NASCIMENTO;
    public String NR_CNH;
    public String ST_ATIVO;

    public Cliente(){
        this.ST_ATIVO = "S";
    }

    public Cliente(int CD_CLIENTE, String NM_CLIENTE, 
    String DT_NASCIMENTO, String NR_CNH, String ST_ATIVO){
        this.CD_CLIENTE = CD_CLIENTE;
        this.NM_CLIENTE = NM_CLIENTE;
        this.DT_NASCIMENTO = DT_NASCIMENTO;
        this.NR_CNH = NR_CNH;
        this.ST_ATIVO = ST_ATIVO;
    }

    public String toString(){
        String str = String.format("{'CD_CLIENTE': %d, 'NM_CLIENTE': %s, 'DT_NASCIMENTO': %s, 'NR_CNH': %s, 'ST_ATIVO': %s}",
                                    this.CD_CLIENTE, this.NM_CLIENTE, this.DT_NASCIMENTO, this.NR_CNH, this.ST_ATIVO);

        return str;
    }
}
