package dao;

import excecao.BDException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.ControlaConexao;

public class ConsultarInformationSchema {
    
    public List<String> tabelas() throws BDException{
        Connection conexao=null;
        Statement instrucao=null;
        ResultSet resultados = null;
        List<String> tabelas = new ArrayList<>();
        
        String sql = "SELECT DISTINCT TABLE_NAME "
                   + "FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS "
                   + "WHERE TABLE_SCHEMA = 'bd_apbd';";
        
        try {
            conexao = ControlaConexao.getConexao();
            instrucao = conexao.createStatement();
            resultados = instrucao.executeQuery(sql);
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
}