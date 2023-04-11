package org.example;

import entity.Client;
import util.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientService {

    public long create(String name) {

        try (var perpStAdd = Database.getInstance().getConnection().prepareStatement(Query.ADD_CLIENT);
             var perpStGetId = Database.getInstance().getConnection().prepareStatement(Query.GET_CLIENT_ID_BY_NAME)) {

            perpStAdd.setString(1, name);
            perpStAdd.execute();
            perpStGetId.setString(1, name);
            var resultSet = perpStGetId.executeQuery();

            if (!resultSet.next()) {
                return 0;
            }

            return Long.parseLong(resultSet.getString("id"));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public String getById(long id) {

        try (var perpStGet = Database.getInstance().getConnection().prepareStatement(Query.GET_CLIENT_BY_ID)) {

            perpStGet.setInt(1, (int) id);
            var resultSet = perpStGet.executeQuery();

            if (!resultSet.next()) {
                return null;
            }

            return resultSet.getString("name");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void setName(long id, String name) {

        try (var perpStSetName = Database.getInstance().getConnection().prepareStatement(Query.SET_CLIENT_NAME)) {

            perpStSetName.setString(1, name);
            perpStSetName.setInt(2, (int) id);
            perpStSetName.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void deleteById(long id) {

        try (var perpStDelete = Database.getInstance().getConnection().prepareStatement(Query.DELETE_CLIENT)) {

            perpStDelete.setInt(1, (int) id);
            perpStDelete.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Client> listAll() {

        List<Client> clients ;

        try (var stGetClients = Database.getInstance().getConnection().createStatement()) {

            var resultSet = stGetClients.executeQuery(Query.GET_ALL_CLIENTS);
            clients = new ArrayList<>();

            while (resultSet.next()) {
                clients.add(Client.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .build());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return clients;
    }
}