/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Poruka;

/**
 *
 * @author Korisnik
 */
public class ModelTabelePoruke extends AbstractTableModel {

    private List<Poruka> poruke = new ArrayList<>();
    private String[] kolone = {"Vreme", "Tekst", "Posiljalac", "Primalac"};

    public ModelTabelePoruke(List<Poruka> lista) {
        this.poruke = lista;
    }

    @Override
    public int getRowCount() {
        if (poruke.isEmpty()) {
            return 0;
        }
        return poruke.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Poruka p = poruke.get(rowIndex);
        switch (columnIndex) {
            case 0:
                LocalDateTime s = p.getVreme();
                String sati = s.getHour() + "";
                String minuti = s.getMinute() + "";
                return sati + ":" + minuti;
            case 1:
                return p.getPoruka();
            case 2:
                return p.getPosiljalac().getIme();
            case 3:
                return p.getPrimalac().getIme();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public void refresh() {
        fireTableDataChanged();
    }

}
