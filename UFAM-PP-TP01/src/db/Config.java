package db;

import java.sql.*;

public class Config {
    private static String javaDB = "./UFAM-PP-TP01/src/db/moto.db";
    
    
    private static String url = "jdbc:sqlite:"+ javaDB;
    protected static Connection conexao = null;

    public Config(){
        if(conexao == null) conecta();
    }

    private static boolean conecta(){
        try{
        	System.out.println(javaDB);
            conexao = DriverManager.getConnection(url);
            System.out.println("Conexao com sucesso!");
            return true;
        }catch (SQLException e){
            System.out.println("Conexao falhou!");
            return false;
        }
    }

    public static boolean desconecta(){
        try{
            System.out.println("Desconexao com sucesso!");
            conexao.close();
            return true;
        }catch (SQLException e){
            System.out.println("Desconexao falhou!");
            return false;
        }
    }
}
