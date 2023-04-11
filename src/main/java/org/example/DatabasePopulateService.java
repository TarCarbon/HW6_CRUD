package org.example;

import util.FileReaderSQL;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DatabasePopulateService {

    public static void main(String[] args) throws SQLException, IOException {
        insertIntoWorker();
        insertIntoClient();
        insertIntoProject();
        insertIntoProjectWorker();
    }

    private static void insertIntoWorker () throws SQLException, IOException {
        PreparedStatement statement = Database.getInstance().getConnection()
            .prepareStatement("INSERT INTO worker (name, birthday, level, salary) VALUES (?, ?, ?, ?)");
        List<String> workerDataList = new FileReaderSQL().dataFromPopulateSQL("INSERT INTO worker (name, birthday, level, salary) VALUES\n");
            for (int i = 0; i < workerDataList.size(); i++) {
                String[] strArr = workerDataList.get(i).split(",");
                //strArr[0] - name, strArr[1] - birthday, strArr[2] - level, strArr[3] - salary
                statement.setString(1, strArr[0].strip());
                statement.setString(2, strArr[1].strip());
                statement.setString(3, strArr[2].strip());
                statement.setInt(4, Integer.parseInt(strArr[3].strip()));
                statement.addBatch();
                statement.executeBatch();
            }
            statement.close();
    }

    private static void insertIntoClient () throws SQLException, IOException{
        PreparedStatement statement = Database.getInstance().getConnection().prepareStatement("INSERT INTO client (name) VALUES ?");
        List<String> clientDataList = new FileReaderSQL().dataFromPopulateSQL("INSERT INTO client (name) VALUES");
            for (String srt: clientDataList) {
                statement.setString(1, srt.strip());
                statement.addBatch();
                statement.executeBatch();
            }
            statement.close();
    }

    private static void insertIntoProject() throws SQLException, IOException {
       PreparedStatement statement = Database.getInstance().getConnection()
               .prepareStatement("INSERT INTO project (client_id, start_date, finish_date) VALUES ?, ?, ?");
       List<String> projectDataList = new FileReaderSQL()
               .dataFromPopulateSQL("INSERT INTO project (client_id, start_date, finish_date) VALUES");
            for (int i = 0; i < projectDataList.size(); i++) {
                String[] strArr = projectDataList.get(i).split(",");
                //srtArr[0] - client_id, srtArr[1] - start_date, strArr[2] - finish_date
                statement.setInt(1, Integer.parseInt(strArr[0].strip()));
                statement.setString(2, strArr[1].strip());
                statement.setString(3, strArr[2].strip());
                statement.addBatch();
                statement.executeBatch();

            }
        statement.close();
    }

    private static void insertIntoProjectWorker () throws SQLException, IOException {
            PreparedStatement statement = Database.getInstance().getConnection()
                    .prepareStatement("INSERT INTO project_worker (project_id, worker_id) VALUES ?, ?");
        List<String> projectWorkerDataList = new FileReaderSQL()
                .dataFromPopulateSQL("INSERT INTO project_worker (project_id, worker_id) VALUES");
        for (int i = 0; i < projectWorkerDataList.size(); i++) {
            String[] strArr = projectWorkerDataList.get(i).split(",");
                //strArr[0] - project_id, strArr[1] - worker_id
                statement.setInt(1, Integer.parseInt(strArr[0]));
                statement.setInt(1, Integer.parseInt(strArr[1]));
                statement.addBatch();
                statement.executeBatch();
        }
        statement.close();
    }
}

