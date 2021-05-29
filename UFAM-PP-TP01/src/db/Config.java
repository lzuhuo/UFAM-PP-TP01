package db;

import java.sql.*;

public class Config {
    private static String javaDB = System.getenv("PWD") + "/UFAM-PP-TP01/src/db/moto.db";
    
    private static String url = "jdbc:sqlite:/home/oliveira/Documentos/UFAM/PP/UFAM-PP-TP01/UFAM-PP-TP01/src/db/moto.db";
    protected static Connection conexao = null;

    public Config(){
        if(conexao == null) conecta();
    }

    private static boolean conecta(){
        System.out.println(javaDB);
        try{
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
            conexao.close();
            System.out.println("Desconexao com sucesso!");
            return true;
        }catch (SQLException e){
            System.out.println("Desconexao falhou!");
            return false;
        }
    }
}
