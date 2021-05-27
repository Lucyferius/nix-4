package util;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import model.CsvTable;

public class CsvParser {
    public static CsvTable parse(String file){
        CsvTable csvTable = null;
        try (InputStream inputStream = CsvParser.class.getClassLoader().getResourceAsStream(file);
             CSVReader reader = new CSVReader(new InputStreamReader(inputStream))) {
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
        } catch (CsvException e) {
            e.printStackTrace();
        }
        return csvTable;
    }

}
