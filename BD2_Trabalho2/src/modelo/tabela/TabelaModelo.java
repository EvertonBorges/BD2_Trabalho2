package modelo.tabela;

import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TabelaModelo extends AbstractTableModel{
    private final String tabela;
    private final List<String> campos;

    public TabelaModelo(String tabela, List<String> campos) {
        this.tabela = tabela;
        this.campos = campos;
    }

    @Override
    public int getRowCount() {
        return campos.size();
    }

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return campos.get(rowIndex).toLowerCase();
    }

    @Override
    public String getColumnName(int column) {
        return tabela.toUpperCase();
    }
}