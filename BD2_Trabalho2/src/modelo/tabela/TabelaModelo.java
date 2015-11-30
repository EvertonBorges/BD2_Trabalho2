package modelo.tabela;

import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TabelaModelo extends AbstractTableModel{
    private final List<List<String>> campos;

    public TabelaModelo(List<List<String>> campos) {
        this.campos = campos;
    }

    @Override
    public int getRowCount() {
        return campos.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        List<String> linha = campos.get(rowIndex);
        switch (columnIndex) {
            case 0: return linha.get(0);
            case 1: return linha.get(1);
            case 2: return linha.get(2);
            case 3: return linha.get(3);
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: return "DATABASE";
            case 1: return "TABELA";
            case 2: return "ATRIBUTO";
            case 3: return "TIPO";
            default: return "";
        }
    }
}