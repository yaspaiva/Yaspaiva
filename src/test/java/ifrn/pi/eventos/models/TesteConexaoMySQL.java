package ifrn.pi.eventos.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TesteConexaoMySQL {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/eventos";
        String usuario = "yasmim";
        String senha = "drica123@@";

        try {
            Connection conexao = DriverManager.getConnection(url, usuario, senha);
            System.out.println("Conexão com o MySQL estabelecida com sucesso!");
            conexao.close();
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao MySQL: " + e.getMessage());
        }
    }
}
