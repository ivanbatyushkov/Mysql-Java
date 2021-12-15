package Sample.service;

//import Sample.mainObjects.Diet;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//
//import java.sql.*;
//
//public class DietDataService {
//    Connection connection;
//    public DietDataService(Connection connection) {
//        this.connection = connection;
//    }
//
//    public void deleteById(int id) throws SQLException {
//        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM diets WHERE id=? ");
//        preparedStatement.setInt(1,id);
//        preparedStatement.execute();
//    }
//
//    public void deleteById(int id) throws SQLException {
//        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM diets WHERE id=? ");
//        preparedStatement.setInt(1,id);
//        preparedStatement.execute();
//    }
//
//
//
//
//    public ObservableList<Diet> load() throws SQLException {
//        ObservableList<Diet> diets = FXCollections.observableArrayList();
//        Statement statement = connection.createStatement();
//        ResultSet resultSet = statement.executeQuery("SELECT DISTINCT * FROM diets");
//
//        while (resultSet.next()) {
//            Diet diet = new Diet(
//                    resultSet.getInt(1),
//                    resultSet.getInt(2),
//                    resultSet.getString(3),
//                    resultSet.getString(4)
//
//
//            );
//
//            diets.add(diet);
//        }
//
//        return diets;
//    }
//
//
//    public void searchByName(String name){
//
//    }
//
///*
//    public static final ObservableList<Diet> loadFromDatabase(Statement statement) throws SQLException {
//        ObservableList<Diet> diets = FXCollections.observableArrayList();
//
//        ResultSet resultSet = statement.executeQuery("SELECT DISTINCT * FROM diets");
//
//        while (resultSet.next()) {
//            Diet diet = new Diet(
//                    resultSet.getInt(1),
//                    resultSet.getInt(2),
//                    resultSet.getString(3),
//                    resultSet.getString(4)
//
//
//            );
//
//            diets.add(diet);
//        }
//
//        return diets;
//    }
//
//    public void loadToDatabase(Connection connection) throws SQLException {
//        Statement statement = connection.createStatement();
//
//        statement.execute("INSERT INTO diets VALUES ('" + id + "', '" + dietID + "', '" + dietName +
//                "', '" + dietType + "')");
//    }
//
//    public void editInDatabase(Diet newDiet, Connection connection) throws SQLException {
//
//        Statement statement = connection.createStatement();
//
//        statement.execute("UPDATE diets SET id=" + newDiet.getId() + ", dietID=" + newDiet.getDietID() + ", dietName='" + newDiet.getDietName() + "', dietType='" + newDiet.getDietType() + "' WHERE id=" +
//                id + " AND dietID=" + dietID + " AND dietName='" + dietName + "' AND dietType='" + dietType + "'");
//
//    }*/
//}
