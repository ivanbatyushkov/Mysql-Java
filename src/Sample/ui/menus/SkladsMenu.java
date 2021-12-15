package Sample.ui.menus;


import Sample.mainObjects.Sklad;
import Sample.ui.Database;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SkladsMenu extends ContextMenu {


    private ObservableList<Sklad> sklads;
    private Sklad sklad;
    private Database database;


    public SkladsMenu() {
        MenuItem createNewItem = new MenuItem("New");
        MenuItem editItem = new MenuItem("Edit");
        MenuItem deleteItem = new MenuItem("Delete");
        MenuItem sortItem = new MenuItem("Search by data and sort by name");

        sortItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = new Stage();
                VBox vBox = new VBox();
                final Scene scene = new Scene(vBox, vBox.getMinWidth(), vBox.getMinHeight());
                ArrayList<Sklad> sorted_sklads = new ArrayList<>();

                TextField date3Field = new TextField();
                date3Field.setPromptText("Дата");

                Button ok = new Button("Ok");
                ok.setOnAction(event1 -> {
                    Connection connection = database.getConnection();
                    try {
                        Statement statement =  connection.createStatement();

                        ResultSet resultSet = statement.executeQuery("SELECT DISTINCT * FROM sklad WHERE date3 =" + Integer.parseInt(date3Field.getText()) + ";");

                        while (resultSet.next()) {
                            Sklad sklad = new Sklad(
                                    resultSet.getInt(1),
                                    resultSet.getString(2),
                                    resultSet.getInt(3),
                                    resultSet.getInt(4)

                            );

                            sorted_sklads.add(sklad);
                        }

                        Collections.sort(sorted_sklads, new Comparator<Sklad>() {
                            @Override
                            public int compare(Sklad o1, Sklad o2) {
                                return o1.getName().compareTo(o2.getName());
                            }
                        });

                        VBox sortedSkladsBox = new VBox();
                        sorted_sklads.forEach((a) ->
                        {
                            Label label = new Label(
                                    "Name - " + a.getName() + " Data - " + a.getDate3()
                            );
                            sortedSkladsBox.getChildren().add(label);
                        });

                        scene.setRoot(sortedSkladsBox);


                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
                Button cancel = new Button("Cancel");
                cancel.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        stage.close();
                    }
                });
                vBox.getChildren().addAll(date3Field, cancel, ok);
                stage.setScene(scene);
                stage.show();
            }
        });

        createNewItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = new Stage();
                VBox vBox = new VBox();

                TextField numberField = new TextField();
                numberField.setPromptText("Номер склада");
                TextField phoneField = new TextField();
                phoneField.setPromptText("Телефон склада");
                TextField nameField = new TextField();
                nameField.setPromptText("Название склада");
                TextField date3Field = new TextField();
                date3Field.setPromptText("Дата");

                Button ok = new Button("Ok");
                ok.setOnAction(event1 -> {
                    Sklad sklad = new Sklad(Integer.parseInt(numberField.getText()), nameField.getText(), Integer.parseInt(phoneField.getText()), Integer.parseInt(date3Field.getText()));
                    try {
                        sklad.loadToDatabase(database.getConnection());
                        database.clearData();
                        database.loadData();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    stage.close();
                });
                Button cancel = new Button("Cancel");
                cancel.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        stage.close();
                    }
                });
                vBox.getChildren().addAll(numberField, phoneField, nameField, date3Field, cancel, ok);
                Scene scene = new Scene(vBox, vBox.getMinWidth(), vBox.getMinHeight());
                stage.setScene(scene);
                stage.show();
            }
        });

        editItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = new Stage();
                VBox vBox = new VBox();
                TextField numberField = new TextField();
                numberField.setPromptText("Номер склада");
                numberField.setText(Integer.toString(sklad.getNumber()));

                TextField nameField = new TextField();
                nameField.setPromptText("Название склада");
                nameField.setText(sklad.getName());

                TextField phoneField = new TextField();
                phoneField.setPromptText("Телефон склада");
                phoneField.setText(Integer.toString(sklad.getPhone()));

                TextField date3Field = new TextField();
                date3Field.setPromptText("Дата");
                date3Field.setText(Integer.toString(sklad.getDate3()));



                Button ok = new Button("Ok");
                ok.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            sklad.editInDatabase(new Sklad(Integer.parseInt(numberField.getText()), nameField.getText(), Integer.parseInt(phoneField.getText()), Integer.parseInt(date3Field.getText())), database.getConnection());
                            database.clearData();
                            database.loadData();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        stage.close();
                    }
                });
                Button cancel = new Button("Cancel");
                cancel.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        stage.close();
                    }
                });
                vBox.getChildren().addAll(numberField, phoneField, nameField, date3Field, cancel, ok);
                Scene scene = new Scene(vBox, vBox.getMinWidth(), vBox.getMinHeight());
                stage.setScene(scene);
                stage.show();
            }
        });




        deleteItem.setOnAction(event -> {
            try {
                sklad.deleteFromDatabase(database.getConnection());
                database.clearData();
                database.loadData();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });


        getItems().addAll(createNewItem, editItem, deleteItem, sortItem);
    }

    public void setContent(ObservableList<Sklad> sklads, Sklad sklad, Database database) {
        this.sklads = sklads;
        this.sklad = sklad;
        this.database = database;
    }
}

