package model.Moto;

public class Status {
    public String ST_ATIVO;
    public String DS_ATIVO;

    public Status(String ST_ATIVO, String DS_ATIVO){
        this.ST_ATIVO = ST_ATIVO;
        this.DS_ATIVO = DS_ATIVO;
    }

    public String toString(){
        return String.format("%s",this.DS_ATIVO);
    }
}
