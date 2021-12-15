package Sample.mainObjects;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Sklad {
    private int number, phone, date3;
    private String name;

    public Sklad(int number, String name, int phone, int date3) {

        this.number = number;
        this.phone = phone;
        this.name = name;
        this.date3 = date3;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) { this.name =name; }

    public int getNumber() { return number; }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getDate3() {
        return date3;
    }

    public void setDate3(int date3) {
        this.date3 = date3;
    }


    public void deleteFromDatabase(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();

        statement.execute("DELETE FROM sklad WHERE name='" + name + "' AND number='" + number +
                "' AND phone=" + phone + " AND date3='" + date3 + "'");
    }



    public static final ObservableList<Sklad> loadFromDatabase(Statement statement) throws SQLException {
        ObservableList<Sklad> sklads = FXCollections.observableArrayList();

        ResultSet resultSet = statement.executeQuery("SELECT DISTINCT * FROM sklad");

        while (resultSet.next()) {
            Sklad sklad = new Sklad(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getInt(4)

            );

            sklads.add(sklad);
        }

        return sklads;
    }

    public void loadToDatabase(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();

        statement.execute("INSERT INTO sklad VALUES ('" + number + "', '" + name + "', '" + phone +
                "', '" + date3 + "')");
    }

    public void editInDatabase(Sklad newSklad, Connection connection) throws SQLException {

        Statement statement = connection.createStatement();

        statement.execute("UPDATE sklad SET number=" + newSklad.getNumber() + ", phone=" + newSklad.getPhone() + ", name='" + newSklad.getName() + "', date3=" + newSklad.getDate3() + " WHERE number=" +
                number + " AND phone=" + phone + " AND name='" + name + "'  AND date3='" + date3 + "'");

    }
}

















