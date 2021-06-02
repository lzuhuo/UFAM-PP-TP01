package business;
import java.sql.*;
import java.util.ArrayList;

import db.Config;
import model.Message;
import model.Moto.Locacao;
import model.Moto.Moto;
import model.Moto.Opcional;

public class LocacaoDAO extends Config{

    public ArrayList<String> getDSMoto(String DT_INICIO, String DT_FIM){
        ArrayList<String> motos = new ArrayList<String>();
        try{
            Statement st = conexao.createStatement();
            String sql = " SELECT MO.DS_MARCA FROM motos AS MO                                " +
                         " WHERE MO.CD_MOTO NOT IN                                            " +
                         " (   SELECT LO.CD_MOTO FROM locacoes AS LO                          " +
                         "     WHERE ST_LOCACAO = 'R'                                         " +
                         "     AND CAST('" + DT_INICIO + "' AS DATE) BETWEEN                  " +
                         "     CAST(LO.DT_RETIRADA AS DATE) AND CAST(LO.DT_DEVOLUCAO AS DATE) " +
                         "     AND CAST('" + DT_FIM + "' AS DATE) BETWEEN                     " +
                         "     CAST(LO.DT_RETIRADA AS DATE) AND CAST(LO.DT_DEVOLUCAO AS DATE) " +
                         " )   AND MO.ST_ATIVO='S' GROUP BY 1                                 " ;
            

            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                motos.add(rs.getString("DS_MARCA"));
            }
            rs.close();
            return motos;
        }catch( SQLException e){
            System.out.println(e);
            return null;
        }
    }

    public ArrayList<Moto> getModelMoto(String DT_INICIO, String DT_FIM, String DS_MARCA){
        ArrayList<Moto> motos = new ArrayList<Moto>();
        try{
            Statement st = conexao.createStatement();
            String sql = " SELECT * FROM motos AS MO                                          " +
                         " WHERE MO.CD_MOTO NOT IN                                            " +
                         " (   SELECT LO.CD_MOTO FROM locacoes AS LO                          " +
                         "     WHERE ST_LOCACAO = 'R'                                         " +
                         "     AND CAST('" + DT_INICIO + "' AS DATE) BETWEEN                  " +
                         "     CAST(LO.DT_RETIRADA AS DATE) AND CAST(LO.DT_DEVOLUCAO AS DATE) " +
                         "     AND CAST('" + DT_FIM + "' AS DATE) BETWEEN                     " +
                         "     CAST(LO.DT_RETIRADA AS DATE) AND CAST(LO.DT_DEVOLUCAO AS DATE) " +
                         " )   AND MO.DS_MARCA = '" + DS_MARCA + "' AND MO.ST_ATIVO='S'       " +
                         "     GROUP BY 1 LIMIT 1        " ;
            

            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                motos.add(new Moto( rs.getInt("CD_MOTO"), rs.getString("DS_MARCA"), 
                                    rs.getString("DS_MODELO"), rs.getFloat("VL_CUSTO")
                                    ));
            }
            rs.close();
            return motos;
        }catch( SQLException e){
            System.out.println(e);
            return null;
        }
    }

    public ArrayList<Opcional> listaOpcionais(){
        ArrayList<Opcional> opcionais = new ArrayList<Opcional>();
        try{
            Statement st = conexao.createStatement();
            String sql = " SELECT * FROM opcionais " +
                         " WHERE ST_ATIVO = 'S' " ;
            

            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                opcionais.add(new Opcional( rs.getInt("CD_OPCIONAL"), rs.getString("DS_OPCIONAL"), 
                                    rs.getFloat("VL_OPCIONAL")
                                    ));
            }
            rs.close();
            return opcionais;
        }catch( SQLException e){
            System.out.println(e);
            return null;
        }
    }

    public Message adicionaLocacao(Locacao l){
        Message resp;
        int codigo = -1;
        try{
            Statement st = conexao.createStatement();
            st.executeUpdate("INSERT INTO locacoes VALUES (NULL ," +
                            "" + l.CD_MOTO + "," + 
                            "" + l.CD_CLIENTE + "," + 
                            "'" + l.DT_RETIRADA + "'," + 
                            "'" + l.LC_RETIRADA + "'," + 
                            "'" + l.DT_DEVOLUCAO + "'," + 
                            "'" + l.LC_DEVOLUCAO + "'," + 
                            "'" + l.ST_LOCACAO + "'" + 
                            ")"    
                            );
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                codigo = rs.getInt(1);
            }
            resp = new Message(true, "success",codigo);
            rs.close();
            return resp;
        }catch (SQLException e) {
            resp = new Message(false, "error:CA+L" + e.toString(), codigo);
            return resp;
        }
    }

    public Message adicionaOpcional(int CD_LOCACAO, int CD_OPCIONAL){
        Message resp;
        int codigo = -1;
        try{
            Statement st = conexao.createStatement();
            st.executeUpdate("INSERT INTO locacoes_opcionais VALUES (" +
                            "" + CD_LOCACAO + "," + 
                            "" + CD_OPCIONAL + "" +
                            ")"    
                            );
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                codigo = rs.getInt(1);
            }
            resp = new Message(true, "success",codigo);
            rs.close();
            return resp;
        }catch (SQLException e) {
            resp = new Message(false, "error:CA+LO" + e.toString(), codigo);
            return resp;
        }
    }
}
