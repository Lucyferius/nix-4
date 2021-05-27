package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CsvTable {
    Map<String, Integer> header;
    List<List<String>> rows = new ArrayList<>();

    public CsvTable(Map<String, Integer> header){
        this.header = header;
    }

    public List<String> getRow(int index){
        return rows.get(index);
    }
    public List<String> getAllColumn(int index){
        List<String> columnValues = new ArrayList<>();
        for (List<String> row: rows){
            columnValues.add(row.get(index));
        }
        return columnValues;
    }
    public String getValue(int row, String column){
        // строки и колонки начинаются с 0
        return  rows.get(row).get(header.get(column));
    }
    public String getValue(int row, int column){
        // строки и колонки начинаются с 0
        return rows.get(row).get(column);
    }
    public void addRow(List<String> row){
        rows.add(row);
    }
    public List<List<String>> getRows(){
        return rows;
    }
    public List<String> getHeader(){
        return new ArrayList<>(header.keySet());
    }
    public int getHeaderColumnIndex(String name){
        return header.get(name);
    }
}
