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
    
    public String tipoAtributo(String banco, String tabela, String atributo) throws BDException{
        Connection conexao = null;
        PreparedStatement instrucao = null;
        ResultSet resultados = null;
        String tipo = null;
        
        String sql = "SELECT COLUMN_TYPE FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = ? AND TABLE_NAME = ? AND COLUMN_NAME = ?";
        
        try {
            conexao = ControlaConexao.getConexao();
            instrucao = conexao.prepareStatement(sql);
            instrucao.setString(1, banco);
            instrucao.setString(2, tabela);
            instrucao.setString(3, atributo);
            resultados = instrucao.executeQuery();
            while(resultados.next()){
                tipo = resultados.getString("COLUMN_TYPE");
            }
            return tipo;
        } catch (SQLException ex) {
            System.out.println("Erro Mensagem: " + ex.getMessage());
            throw new BDException(BDMensagensPadrao.INSTRUCAO_ERRO, ex);
        } finally {
            ControlaConexao.fecharConexao(conexao);
            ControlaConexao.fecharInstrucaoStatement(instrucao);
            ControlaConexao.fecharResultSet(resultados);
        }
    }
    
    public String tamanhoBanco(String banco) throws BDException{
        Connection conexao = null;
        PreparedStatement instrucao = null;
        ResultSet resultados = null;
        String length = null;
        
        String sql = "CALL bd_apbd_egow.SP_TAMANHO_BANCO(?)";
        
        try {
            conexao = ControlaConexao.getConexao();
            instrucao = conexao.prepareCall(sql);
            instrucao.setString(1, banco);
            resultados = instrucao.executeQuery();
            while(resultados.next()){
                length = resultados.getString("DATABASE_LENGTH");
            }
            return length;
        } catch (SQLException ex) {
            System.out.println("Erro Mensagem: " + ex.getMessage());
            throw new BDException(BDMensagensPadrao.INSTRUCAO_ERRO, ex);
        } finally {
            ControlaConexao.fecharConexao(conexao);
            ControlaConexao.fecharInstrucaoStatement(instrucao);
            ControlaConexao.fecharResultSet(resultados);
        }
    }
    
    public List<String> tamanhoTabela(String banco, String tabela) throws BDException{
        Connection conexao = null;
        PreparedStatement instrucao = null;
        ResultSet resultados = null;
        List<String> tamanhos = new ArrayList<>();
        
        String sql = "CALL bd_apbd_egow.SP_TAMANHO_TABELA(?, ?)";
        
        try {
            conexao = ControlaConexao.getConexao();
            instrucao = conexao.prepareCall(sql);
            instrucao.setString(1, banco);
            instrucao.setString(2, tabela);
            resultados = instrucao.executeQuery();
            while(resultados.next()){
                String data_length = resultados.getString("DATA_LENGTH");
                String index_length = resultados.getString("INDEX_LENGTH");
                tamanhos.add(data_length);
                tamanhos.add(index_length);
            }
            return tamanhos;
        } catch (SQLException ex) {
            System.out.println("Erro Mensagem: " + ex.getMessage());
            throw new BDException(BDMensagensPadrao.INSTRUCAO_ERRO, ex);
        } finally {
            ControlaConexao.fecharConexao(conexao);
            ControlaConexao.fecharInstrucaoStatement(instrucao);
            ControlaConexao.fecharResultSet(resultados);
        }
    }
    
    public List<String> relacionamento(String banco, String tabela1, String tabela2) throws BDException{
        Connection conexao = null;
        PreparedStatement instrucao = null;
        ResultSet resultados = null;
        List<String> relacionamentos = new ArrayList<>();
        
        String sql = "CALL bd_apbd_egow.SP_RELACIONAMENTOS(?, ?, ?)";
        
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