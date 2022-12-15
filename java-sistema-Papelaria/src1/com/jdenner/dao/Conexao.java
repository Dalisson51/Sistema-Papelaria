package com.jdenner.dao;

import java.sql.*;


public class Conexao {

    private static final String SERVIDOR = "localhost";
    private static final String PORTA = "3306";
    private static final String BANCO_DE_DADOS = "dbPapelaria";
    private static final String USUARIO = "root";
    private static final String SENHA = "root";
    private static final String URL = "jdbc:mysql://" + SERVIDOR + ":" + PORTA + "/" + BANCO_DE_DADOS;

    private static Connection con = null;

    public Conexao() throws ClassNotFoundException, SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(URL, USUARIO, SENHA);
            con.setAutoCommit(false);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            throw new ClassNotFoundException("O driver de conexão não foi adicionado ao projeto.\n" + e.getMessage());
        } catch (SQLException e) {
            throw new SQLException("Problemas nos parâmetros de conexão.\n" + e.getMessage());
        }
    }

    public Connection getConexao() {
        return con;
    }

    public void confirmar() throws SQLException {
        try {
            con.commit();
        } catch (SQLException e) {
            throw new SQLException("Problemas na instrução SQL.\n" + e.getMessage());
        } finally {
            con.close();
        }
    }
}
