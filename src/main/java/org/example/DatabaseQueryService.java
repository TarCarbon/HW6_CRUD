
package org.example;

import entity.BaseModel;
import entity.YoungerEldersWorkers;
import util.FileReaderSQL;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DatabaseQueryService {

    public List<BaseModel> findMaxSalaryWorker() throws IOException, SQLException {
        List<BaseModel> workers = new ArrayList<>();
        ResultSet rs = getResult(new FileReaderSQL().getQuery("sql/find_max_salary_worker.sql"));
        while(rs.next()){
            workers.add(BaseModel.builder()
                    .name(rs.getString("NAME"))
                    .count(rs.getInt("SALARY"))
                    .build());
        }
        return workers;
    }

    public List<BaseModel> findMaxProjectsClient() throws IOException, SQLException{
        List<BaseModel> maxProjectsClient = new ArrayList<>();
        ResultSet rs = getResult(new FileReaderSQL().getQuery("sql/find_max_projects_client"));
        while(rs.next()) {
            maxProjectsClient.add(BaseModel.builder()
                        .name(rs.getString("NAME"))
                        .count(rs.getInt("PROJECT_COUNT"))
                        .build());
        }
        return maxProjectsClient;
    }

    public List<BaseModel> findLongestProject () throws IOException, SQLException{
        List<BaseModel> longestProject = new ArrayList<>();
        ResultSet rs = getResult(new FileReaderSQL().getQuery("sql/find_longest_project.sql"));
        while (rs.next()) {
            longestProject.add(BaseModel.builder()
                    .name(rs.getString("NAME"))
                    .count(rs.getInt("MONTH_COUNT"))
                    .build());
        }
        return longestProject;
    }

    public List<BaseModel> projectPrices() throws IOException, SQLException{
        List<BaseModel> projectPrices = new ArrayList<>();
        ResultSet rs = getResult(new FileReaderSQL().getQuery("sql/print_project_prices"));
        while (rs.next()){
            projectPrices.add(BaseModel.builder()
                    .name(rs.getString("NAME"))
                    .count(rs.getInt("PRICE"))
                    .build());
        }
        return projectPrices;
    }
    public List<YoungerEldersWorkers> findYoungestEldestWorker () throws IOException, SQLException {
        List<YoungerEldersWorkers> youngerEldersWorkers = new ArrayList<>();
        ResultSet rs = getResult(new FileReaderSQL().getQuery("sql/find_youngest_eldest_workers.sql"));
            while(rs.next()){
                youngerEldersWorkers.add(YoungerEldersWorkers.builder()
                        .type(rs.getString("TYPE"))
                        .name(rs.getString("NAME"))
                        .birthday(LocalDate.parse(rs.getString("BIRTHDAY")))
                        .build());
            }
        return youngerEldersWorkers;
    }

    private static ResultSet getResult(String query){
        try(Statement statement = Database.getInstance().getConnection().createStatement()){
                ResultSet resultSet = statement.executeQuery(query);
                statement.close();
           return resultSet;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

