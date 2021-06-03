package business.Categoria;

import java.sql.*;
import java.util.ArrayList;

import db.Config;
import model.Categoria.Moto;


public class MotoDAO extends Config{
    public ArrayList<Moto> listarMotos(){
        ArrayList<Moto> catMotos = new ArrayList<Moto>();
        try{
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery("SELECT CD_CATEGORIA, DS_CATEGORIA FROM categorias_motos WHERE ST_ATIVO='S'");
            while (rs.next()) {
                catMotos.add(new Moto(rs.getInt(1), rs.getString(2)));
            }
            return catMotos;  
        }catch( SQLException e){ return null;}
    }
}