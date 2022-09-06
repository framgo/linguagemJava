package linguagemJava.persist;

import linguagemJava.service.ProdutoService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
* @author: Lucas Araujo
* @since: 05/09/2022
 * @version 1.0
 * @description: Classe responsavel por fazer a conexão com o banco de dados
* */

public class DAO {

     public DAO(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
            System.err.println("Class not found. Error: " + cnfe.getMessage());
        }
     }

     protected Connection getConnection() throws SQLException {
         return DriverManager.getConnection("jdbc:mysql://localhost/estoque", "root", "");
     }

    public static void main(String[] args) { // testando se a conexão do banco foi feita com sucesso
        var dao = new DAO();

        try {
            dao.getConnection();
            System.out.println("Conexao aberta");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}


