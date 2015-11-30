package modelo.lista;

import java.util.List;
import javax.swing.AbstractListModel;

public class ListaTabelaModelo extends AbstractListModel{
    private final List<String> atributos;

    public ListaTabelaModelo(List<String> tabela) {
        this.atributos = tabela;
    }
        
    @Override
    public int getSize() {
        return atributos.size();
    }

    @Override
    public Object getElementAt(int index) {
        return atributos.get(index).toLowerCase();
    }
}