package com.jdenner.dao;

import com.jdenner.to.Cliente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Classe de acesso a dados do cliente
 * 
 */
public class ClienteDAO implements IDAO<Cliente> {

    @Override
    public void inserir(Cliente cliente) throws Exception {
        Conexao c = new Conexao();
        String sql = "INSERT INTO TBCLIENTE (NOME, CPF) VALUES (?, ?)";
        PreparedStatement ps = c.getConexao().prepareStatement(sql);
        ps.setString(1, cliente.getNome());
        ps.setString(2, cliente.getCpf());      
        ps.execute();
        c.confirmar();
    }

    @Override
    public void alterar(Cliente cliente) throws Exception {
        Conexao c = new Conexao();
        String sql = "UPDATE TBCLIENTE SET NOME=?, CPF=? WHERE CODIGO=?";
        PreparedStatement ps = c.getConexao().prepareStatement(sql);
        ps.setString(1, cliente.getNome());
        ps.setString(2, cliente.getCpf());        
        ps.setInt(3, cliente.getCodigo());
        ps.execute();
        c.confirmar();
    }

    @Override
    public void excluir(Cliente cliente) throws Exception {
        Conexao c = new Conexao();
        String sql = "DELETE FROM TBCLIENTE WHERE CODIGO=?";
        PreparedStatement ps = c.getConexao().prepareStatement(sql);
        ps.setInt(1, cliente.getCodigo());
        ps.execute();
        c.confirmar();
    }

    @Override
    public ArrayList<Cliente> listarTodos() throws Exception {
        Conexao c = new Conexao();
        String sql = "SELECT * FROM TBCLIENTE ORDER BY NOME";
        PreparedStatement ps = c.getConexao().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        ArrayList listaClientes = new ArrayList();
        while (rs.next()) {
            Cliente cliente = new Cliente();
            cliente.setCodigo(rs.getInt("CODIGO"));
            cliente.setNome(rs.getString("NOME"));
            cliente.setCpf(rs.getString("CPF"));            
            listaClientes.add(cliente);
        }

        return listaClientes;
    }

    @Override
    public Cliente recuperar(int codigo) throws Exception {
        Conexao c = new Conexao();
        String sql = "SELECT * FROM TBCLIENTE WHERE CODIGO=?";
        PreparedStatement ps = c.getConexao().prepareStatement(sql);
        ps.setInt(1, codigo);
        ResultSet rs = ps.executeQuery();

        Cliente cliente = new Cliente();
        if (rs.next()) {
            cliente.setCodigo(rs.getInt("CODIGO"));
            cliente.setNome(rs.getString("NOME"));
            cliente.setCpf(rs.getString("CPF"));            
        }

        return cliente;
    }
}
