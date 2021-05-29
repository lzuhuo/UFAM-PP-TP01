package business;
import java.sql.*;

import db.Config;
import model.Cliente.Cliente;
import model.Message;

public class ClienteDAO extends Config{
    public void listarClientes(){
        try{
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM motos");
            while(rs.next()){
                System.out.println("Moto: " + rs.getString(2) +
                " (" + rs.getString(3) + ")" +
                " da Yamaha " + rs.getString(4));
            }
        }catch( SQLException e){ }
    }

    public Message adicionaClientes(Cliente c){
        Message resp;
        int codigo = -1;
        try{
            Statement st = conexao.createStatement();
            st.executeUpdate("INSERT INTO clientes VALUES (NULL, '"
            + c.NM_CLIENTE + "'," + " '" + c.DT_NASCIMENTO
            + "', '" + c.NR_CNH + "', '"+ c.ST_ATIVO +"')");

            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                codigo = rs.getInt(1);
            }
            resp = new Message(true, "success",codigo);
            rs.close();
            st.close();
            
            return resp;
        }catch (SQLException e) {
            resp = new Message(false, "error" + e.toString(),codigo);
            return resp;
        }
    }
}
