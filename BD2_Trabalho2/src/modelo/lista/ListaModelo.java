package modelo.lista;

import java.util.List;
import javax.swing.AbstractListModel;

public class ListaModelo extends AbstractListModel{
    private final List<String> lista;

    public ListaModelo(List<String> lista) {
        this.lista = lista;
    }

    @Override
    public int getSize() {
        return lista.size();
    }

    @Override
    public Object getElementAt(int index) {
        return lista.get(index);
    }
}
