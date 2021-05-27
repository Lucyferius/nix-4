package util;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import au.com.bytecode.opencsv.CSVReader;
import model.CsvTable;

public class CsvParser {
    public static CsvTable parse(String file){
        CsvTable csvTable = null;
        try (CSVReader reader = new CSVReader(Files.newBufferedReader(Paths.get(CsvParser.class.getResource(file).toURI())))) {
            List<String[]> csvData = reader.readAll();
            String[] header = csvData.get(0);
            Map<String, Integer> map = new HashMap<>();
            for (int i = 0 ; i<header.length ; i++){
                map.put(header[i], i);
            }
            csvTable = new CsvTable(map);
            for (int i = 1; i < csvData.size(); i++) {
                String[] fieldList = new String[header.length];
                System.arraycopy(csvData.get(i), 0, fieldList, 0, fieldList.length);
                csvTable.addRow(Arrays.asList(fieldList));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return csvTable;
    }

}
