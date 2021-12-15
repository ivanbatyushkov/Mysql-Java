package Sample.mainObjects;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class Pnakl {
    private int date1, kolvo;
    private String perechen1, fio1, doljn1;
    private Connection connection;

    public Pnakl(int date1, String perechen1, int kolvo, String fio1, String doljn1) {

        this.date1 = date1;
        this.perechen1 = perechen1;
        this.kolvo = kolvo;
        this.fio1 = fio1;
        this.doljn1 = doljn1;
    }

    public int getDate1() {
        return date1;
    }

    public void setDate1(int date1) {
        this.date1 = date1;
    }

    public String getPerechen1() {
        return perechen1;
    }

    public void setPerechen1e(String perechen1) {
        this.perechen1 = perechen1;
    }

    public int getKolvo() {
        return kolvo;
    }

    public void setKolvo(int kolvo) {
        this.kolvo = kolvo;
    }

    public String getFio1() {
        return fio1;
    }

    public void setFio1(String fio1) {
        this.fio1 = fio1;
    }

    public String getDoljn1() {
        return doljn1;
    }

    public void setDoljn1(String doljn1) {
        this.doljn1 = doljn1;
    }

    public void deleteFromDatabase(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();

        statement.execute("DELETE FROM prixnakl WHERE date1=" + date1 + " AND perechen1='" + perechen1 +
                "' AND kolvo=" + kolvo + " AND fio1='" + fio1 + "' AND doljn1='" + doljn1 +
                "' ");
    }

    public static final ObservableList<Pnakl> loadFromDatabase(Statement statement) throws SQLException {
        ObservableList<Pnakl> pnakls = FXCollections.observableArrayList();

        ResultSet resultSet = statement.executeQuery("SELECT DISTINCT * FROM prixnakl");

        while (resultSet.next()) {
            Pnakl pnakl = new Pnakl(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            );

            pnakls.add(pnakl);
        }

        return pnakls;
    }

    public void loadToDatabase(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();

        statement.execute("INSERT INTO prixnakl VALUES ('" + date1 + "', '" + perechen1 + "', '" + kolvo +
                "', '" + fio1 + "', '" + doljn1 + "')");
    }

    public void editInDatabase(Pnakl newPnakl, Connection connection) throws SQLException {

        Statement statement = connection.createStatement();

        statement.execute("UPDATE prixnakl SET date1=" + newPnakl.getDate1() + ", perechen1='" + newPnakl.getPerechen1() + "', kolvo=" + newPnakl.getKolvo() + ", fio1='" + newPnakl.getFio1() + "', doljn1='" + newPnakl.getDoljn1() + "' WHERE date1=" +
                date1 + " AND perechen1='" + perechen1 + "' AND kolvo=" + kolvo + " AND fio1='" + fio1 + "' AND doljn1='" + doljn1 + "'");

    }
}

