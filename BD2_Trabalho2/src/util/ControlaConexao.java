/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import dao.BDMensagensPadrao;
import excecao.BDException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author rogeriocarmine
 */
public class ControlaConexao {
    private static String login;
    private static String senha;
    private static String host;
    private static String porta;

    public static void setLogin(String login) {
        ControlaConexao.login = login;
    }

    public static void setSenha(String senha) {
        ControlaConexao.senha = senha;
    }

    public static void setHost(String host) {
        ControlaConexao.host = host;
    }

    public static void setPorta(String porta) {
        ControlaConexao.porta = porta;
    }
    
    public static Connection getConexao() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://" + host + ":" + porta, login, senha);
    }

    public static void fecharResultSet(ResultSet resultados) throws BDException{
        if(resultados!=null){
            try{
                resultados.close();
            }catch(SQLException ex){
                throw new BDException(BDMensagensPadrao.FECHAR_RESULTSET_ERRO, ex);
            }
        }
    }
    
    
    public static void fecharInstrucao(PreparedStatement instrucao) throws BDException{
        if(instrucao!=null){
            try{
                instrucao.close();
            }catch(SQLException ex){
                throw new BDException(BDMensagensPadrao.FECHAR_INSTRUCAO_ERRO, ex);
            }
        }
    }
    
    public static void fecharInstrucaoStatement(Statement instrucao) throws BDException{
        if(instrucao!=null){
            try{
                instrucao.close();
            }catch(SQLException ex){
                throw new BDException(BDMensagensPadrao.FECHAR_INSTRUCAO_ERRO, ex);
            }
        }
    }
    
    public static void fecharConexao(Connection conexao) throws BDException {
        if(conexao!=null){
            try{
                conexao.close();
            }catch(SQLException ex){
                throw new BDException(BDMensagensPadrao.FECHAR_CONEXAO_ERRO, ex);
            }
        }
    }
    
}
