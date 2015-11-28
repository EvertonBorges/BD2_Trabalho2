package modelo.lista;

import java.util.List;
import javax.swing.AbstractListModel;

public class ListaTabelaModelo extends AbstractListModel{
    private final String tabela;
    private final List<String> campos;

    public ListaTabelaModelo(String tabela, List<String> campos) {
        this.tabela = tabela;
        this.campos = campos;
    }
    
    @Override
    public int getSize() {
        return (campos.size() + 1);
    }

    @Override
    public Object getElementAt(int index) {
        switch (index) {
            case 0: return tabela.toUpperCase();
            default: return campos.get(index - 1).toLowerCase();
        }
    }
}