package principal;

import dao.ConsultarInformationSchema;
import excecao.BDException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ConsultarInformationSchema teste = new ConsultarInformationSchema();
        List<String> tabelas = null;
        
        try{
            tabelas = teste.tabelas();
        } catch (BDException ex) {
            System.out.println("Erro");
        }
        
        for (String tabela: tabelas){
            System.out.println("-> " + tabela);
        }
    }
}