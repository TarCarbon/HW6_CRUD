package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReaderSQL {
    public String getQuery(String file) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String sql = "", line;
            while ((line = bufferedReader.readLine()) != null) sql += (line + "\n");
        return sql;
    }


    public List<String> dataFromPopulateSQL(String insertQueryLine) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("sql/populate_db.sql"));
        String line;
        List<String> list = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
            if (line.equals(insertQueryLine.strip())) {
                while (!line.contains(";")) {
                    line = reader.readLine();
                    String clientName = line.replaceAll("[^A-Za-z0-9-,]", "");
                    list.add(clientName);
                }
            }
        }
        reader.close();
        return list;
    }

}
