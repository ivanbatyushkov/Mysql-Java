package Sample.mainObjects;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class Rnakl{
    private int date2, rasxod;
    private String perechen2, fio2, doljn2;

    public Rnakl (int date2, String perechen2, int rasxod, String fio2, String doljn2) {

        this.date2 = date2;
        this.perechen2 = perechen2;
        this.rasxod = rasxod;
        this.fio2 = fio2;
        this.doljn2 = doljn2;
    }

    public int getDate2() {
        return date2;
    }

    public void setPetKind(int date2) {
        this.date2 = date2;
    }

    public String getPerechen2() {
        return perechen2;
    }

    public void setPerechen2(String perechen2) {
        this.perechen2 = perechen2;
    }

    public int getRasxod() {
        return rasxod;
    }

    public void setRasxod(int rasxod) {
        this.rasxod = rasxod;
    }

    public String getFio2() {
        return fio2;
    }

    public void setFio2(String fio2) {
        this.fio2 = fio2;
    }

    public String getDoljn2() {
        return doljn2;
    }

    public void setDoljn2(String doljn2) {
        this.doljn2 = doljn2;
    }




    public void deleteFromDatabase(Connection connection) throws SQLException
    {
        Statement statement = connection.createStatement();

        statement.execute("DELETE FROM rasxnakl WHERE date2=" + date2 + " AND perechen2='" + perechen2 +
                "' AND rasxod=" + rasxod + " AND fio2='" + fio2 + "' AND doljn2='" + doljn2 +
                "' ");
    }

    public static ObservableList<Rnakl> load(Statement statement) throws SQLException {
        ObservableList<Rnakl> rnakls = FXCollections.observableArrayList();


        ResultSet resultSet = statement.executeQuery("SELECT DISTINCT * FROM rasxnakl WHERE date2=" + "30022018" + " AND fio2='" + "Петров'");

        while (resultSet.next())
        {
            Rnakl rnakl = new Rnakl(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            );

            rnakls.add(rnakl);
        }

        return rnakls;
    }




    public static final ObservableList<Rnakl> loadFromDatabase(Statement statement) throws SQLException {
        ObservableList<Rnakl> rnakls = FXCollections.observableArrayList();

        ResultSet resultSet = statement.executeQuery("SELECT DISTINCT * FROM rasxnakl");

        while (resultSet.next())
        {
            Rnakl rnakl = new Rnakl(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getString(4),
                    resultSet.getString(5)


            );

            rnakls.add(rnakl);
        }

        return rnakls;
    }

    public void loadToDatabase(Connection connection) throws SQLException
    {
        Statement statement = connection.createStatement();

        statement.execute("INSERT INTO rasxnakl VALUES ('" + date2 + "', '" + perechen2 + "', '" + rasxod +
                "', '" + fio2 + "', '" + doljn2  +"')");
    }

    public void editInDatabase(Rnakl newRnakl, Connection connection) throws SQLException {

        Statement statement = connection.createStatement();

        statement.execute("UPDATE rasxnakl SET date2='" + newRnakl.getDate2() + "', perechen2='" + newRnakl.getPerechen2() + "', rasxod='" + newRnakl.getRasxod() + "', fio2='" + newRnakl.getFio2() + "', doljn2='" + newRnakl.getDoljn2() + "' WHERE date2='" +
                date2 + "' AND perechen2='" + perechen2 + "' AND rasxod='" + rasxod + "' AND fio2='" + fio2 + "' AND doljn2='" + doljn2 + "'");

    }}















