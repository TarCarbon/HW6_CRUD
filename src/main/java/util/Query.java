package util;

public class Query {
    public static final String ADD_CLIENT = "INSERT INTO client (name) VALUES(?);";
    public static final String GET_CLIENT_ID_BY_NAME = "SELECT ID FROM client WHERE name = ?;";
    public static final String GET_CLIENT_BY_ID = "SELECT name FROM client WHERE ID=?;";
    public static final String SET_CLIENT_NAME = "UPDATE client SET name=? WHERE ID=?;";
    public static final String DELETE_CLIENT = "DELETE FROM client WHERE ID=?;";
    public static final String GET_ALL_CLIENTS = "SELECT * FROM client;";

}
