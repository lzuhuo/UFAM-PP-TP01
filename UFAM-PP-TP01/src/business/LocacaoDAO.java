package business;
import java.sql.*;
import java.util.ArrayList;

import db.Config;
import model.Message;
import model.Moto.Moto;

public class LocacaoDAO extends Config{
    
    public ArrayList<String> getDSMoto(String DT_INICIO, String DT_FIM){
        ArrayList<String> resp = new ArrayList<String>();
        try{
            Statement st = conexao.createStatement();
            String sql =" -------------- CONSULTA DE MOTOS SEM AGENDAMENTO -------------- " +
                        " SELECT MO.* FROM locacoes AS LO                                 " +
                        " INNER JOIN motos AS MO ON MO.CD_MOTO = LO.CD_MOTO               " +
                        " WHERE LO.ST_LOCACAO <> 'R'                                      " +
                        " AND CAST('" + DT_INICIO + "' AS DATE) NOT BETWEEN               " +
                        " CAST(LO.DT_RETIRADA AS DATE) AND CAST(LO.DT_DEVOLUCAO AS DATE)  " +
                        " AND CAST('" + DT_FIM + "' AS DATE) NOT BETWEEN                  " +
                        " CAST(LO.DT_RETIRADA AS DATE) AND CAST(LO.DT_DEVOLUCAO AS DATE)  " ;

            st.executeQuery(sql);
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                resp.add(rs.getString("DS_MARCA"));
            }
            
            rs.close();
            return resp;
        }catch (SQLException e) {
            resp = null;
            return resp;
        }
    }

    //CAM
    public Message atualizaMoto(Moto m){
        Message resp;
        int codigo = -1;
        try{
            System.out.println(m.toString());
            Statement st = conexao.createStatement();
            st.executeUpdate("UPDATE motos SET " +
                            "CD_MOTO=" + m.CD_MOTO + ", " + 
                            "CD_CATEGORIA=" + m.CATMOTO.CD_CATEGORIA + ", " + 
                            "DS_MARCA='" + m.DS_MARCA + "', " + 
                            "DS_MODELO='" + m.DS_MODELO + "', " + 
                            "NR_ANO=" + m.NR_ANO + ", " + 
                            "CD_MOTOR=" + m.TP_MOTOR.CD_MOTOR + ", " + 
                            "CP_TANQUE=" + m.CP_TANQUE + ", " + 
                            "AV_CONSUMO=" + m.AV_CONSUMO + ", " +
                            "VL_CUSTO=" + m.VL_CUSTO + ", " + 
                            "ST_ATIVO='" + m.ST_ATIVO + "' " + 
                            "WHERE CD_MOTO=" + m.CD_MOTO    
                            );
            
            resp = new Message(true, "success",m.CD_MOTO );
            
            return resp;
        }catch (SQLException e) {
            resp = new Message(false, "error:CAM" + e.toString(), codigo);
            return resp;
        }
    }

    public Boolean removeMoto(int CD_MOTO){
        try{
            Statement st = conexao.createStatement();
            String sql =    " DELETE FROM motos" +
                            " WHERE CD_MOTO=" + CD_MOTO;
            st.executeUpdate(sql);
            return true;  
        }catch( SQLException e){ return false;}
    }
}
