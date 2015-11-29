package dao;

import excecao.BDException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.ControlaConexao;

public class ConsultarInformationSchema {
    
    public List<String> relacionamento(String banco, String tabela1, String tabela2) throws BDException{
        Connection conexao = null;
        PreparedStatement instrucao = null;
        ResultSet resultados = null;
        List<String> relacionamentos = new ArrayList<>();
        
        String sql = "CALL bd_apbd.SP_EX3(?, ?, ?)";
        
        try {
            conexao = ControlaConexao.getConexao();
            instrucao = conexao.prepareCall(sql);
            instrucao.setString(1, banco);
            instrucao.setString(2, tabela1);
            instrucao.setString(3, tabela2);
            resultados = instrucao.executeQuery();
            while(resultados.next()){
                String ladoN = resultados.getString("LADO N");
                String chaveEstrangeira = resultados.getString("CHAVE ESTRANGEIRA");
                relacionamentos.add(ladoN);
                relacionamentos.add(chaveEstrangeira);
            }
            return relacionamentos;
        } catch (SQLException ex) {
            System.out.println("Erro Mensagem: " + ex.getMessage());
            throw new BDException(BDMensagensPadrao.INSTRUCAO_ERRO, ex);
        } finally {
            ControlaConexao.fecharConexao(conexao);
            ControlaConexao.fecharInstrucaoStatement(instrucao);
            ControlaConexao.fecharResultSet(resultados);
        }
    }
    
    public List<String> tabela(String banco, String tabela) throws BDException{
        Connection conexao = null;
        PreparedStatement instrucao = null;
        ResultSet resultados = null;
        List<String> campos = new ArrayList<>();
        
        String sql = "SELECT DISTINCT COLUMN_NAME "
                   + "FROM INFORMATION_SCHEMA.COLUMNS "
                   + "WHERE TABLE_SCHEMA = ? "
                   + "AND TABLE_NAME LIKE ? ";
        
        try {
            conexao = ControlaConexao.getConexao();
            instrucao = conexao.prepareStatement(sql);
            instrucao.setString(1, banco);
            instrucao.setString(2, "%" + tabela);
            resultados = instrucao.executeQuery();
            while(resultados.next()){
                String campo = resultados.getString("COLUMN_NAME");
                campos.add(campo);
            }
            return campos;
        } catch (SQLException ex) {
            throw new BDException(BDMensagensPadrao.INSTRUCAO_ERRO, ex);
        } finally {
            ControlaConexao.fecharConexao(conexao);
            ControlaConexao.fecharInstrucaoStatement(instrucao);
            ControlaConexao.fecharResultSet(resultados);
        }
    }
    
    public List<String> tabelas(String banco) throws BDException{
        Connection conexao = null;
        PreparedStatement instrucao = null;
        ResultSet resultados = null;
        List<String> tabelas = new ArrayList<>();
        
        String sql = "SELECT DISTINCT TABLE_NAME "
                   + "FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS "
                   + "WHERE TABLE_SCHEMA LIKE ?";
        
        try {
            conexao = ControlaConexao.getConexao();
            instrucao = conexao.prepareStatement(sql);
            instrucao.setString(1, "%" + banco);
            resultados = instrucao.executeQuery();
            while(resultados.next()){
                String tabela = resultados.getString("TABLE_NAME");
                tabelas.add(tabela);
            }
            
            return tabelas;
        } catch (SQLException ex) {
            throw new BDException(BDMensagensPadrao.INSTRUCAO_ERRO, ex);
        } finally {
            ControlaConexao.fecharConexao(conexao);
            ControlaConexao.fecharInstrucaoStatement(instrucao);
            ControlaConexao.fecharResultSet(resultados);
        }
    }
    
    public List<String> bancos() throws BDException{
        Connection conexao=null;
        Statement instrucao=null;
        ResultSet resultados = null;
        List<String> bancos = new ArrayList<>();
        
        String sql = "SELECT DISTINCT CONSTRAINT_SCHEMA "
                   + "FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS ";
        
        try {
            conexao = ControlaConexao.getConexao();
            instrucao = conexao.prepareStatement(sql);
            resultados = instrucao.executeQuery(sql);
            while(resultados.next()){
                String banco = resultados.getString("CONSTRAINT_SCHEMA");
                bancos.add(banco);
            }
            
            return bancos;
        } catch (SQLException ex) {
            throw new BDException(BDMensagensPadrao.INSTRUCAO_ERRO, ex);
        } finally {
            ControlaConexao.fecharConexao(conexao);
            ControlaConexao.fecharInstrucaoStatement(instrucao);
            ControlaConexao.fecharResultSet(resultados);
        }
    }
}