/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.lista;

import java.util.List;
import javax.swing.AbstractListModel;

/**
 *
 * @author Borges
 */
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