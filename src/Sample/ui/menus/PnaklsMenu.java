package Sample.ui.menus;

import Sample.mainObjects.Pnakl;
import Sample.ui.Database;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class PnaklsMenu extends ContextMenu {

    private ObservableList<Pnakl> pnakls;
    private Pnakl pnakl;
    private Database database;

    public PnaklsMenu()
    {
        MenuItem createNewItem = new MenuItem("New");
        MenuItem editItem = new MenuItem("Edit");
        MenuItem deleteItem = new MenuItem("Delete");

        createNewItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = new Stage();
                VBox vBox = new VBox();
                TextField date1Field = new TextField();
                date1Field.setPromptText("Дата");
                TextField perechen1Field = new TextField();
                perechen1Field.setPromptText("Перечень инвентаря");
                TextField kolvoField = new TextField();
                kolvoField.setPromptText("Количество инвентаря");
                TextField fio1Field = new TextField();
                fio1Field.setPromptText("ФИО");
                TextField doljn1Field = new TextField();
                doljn1Field.setPromptText("Должность");

                Button ok = new Button("Ok");
                ok.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Pnakl pnakl = new Pnakl(Integer.parseInt(date1Field.getText()), perechen1Field.getText(), Integer.parseInt(kolvoField.getText()), fio1Field.getText(), doljn1Field.getText());
                        try {
                            pnakl.loadToDatabase(database.getConnection());
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
                vBox.getChildren().addAll(date1Field, perechen1Field, kolvoField, fio1Field, doljn1Field, cancel, ok);
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
                TextField date1Field = new TextField();
                date1Field.setPromptText("Дата");
                date1Field.setText(Integer.toString(pnakl.getDate1()));
                TextField perechen1Field = new TextField();
                perechen1Field.setPromptText("Перечень инвентаря");
                perechen1Field.setText(pnakl.getPerechen1());
                TextField kolvoField = new TextField();
                kolvoField.setPromptText("Количество инвентаря");
                kolvoField.setText(Integer.toString(pnakl.getKolvo()));
                TextField fio1Field = new TextField();
                fio1Field.setPromptText("ФИО");
                fio1Field.setText(pnakl.getFio1());
                TextField doljn1Field = new TextField();
                doljn1Field.setPromptText("Должность");
                doljn1Field.setText(pnakl.getDoljn1());



                Button ok = new Button("Ok");
                ok.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            pnakl.editInDatabase(new Pnakl(Integer.parseInt(date1Field.getText()), perechen1Field.getText(), Integer.parseInt(kolvoField.getText()),fio1Field.getText(), doljn1Field.getText()), database.getConnection());
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
                vBox.getChildren().addAll(date1Field, perechen1Field, kolvoField, fio1Field, doljn1Field, cancel, ok);
                Scene scene = new Scene(vBox, vBox.getMinWidth(), vBox.getMinHeight());
                stage.setScene(scene);
                stage.show();
            }
        });

        deleteItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    pnakl.deleteFromDatabase(database.getConnection());
                    database.clearData();
                    database.loadData();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });



        getItems().addAll(createNewItem, editItem, deleteItem);
    }

    public void setContent(ObservableList<Pnakl> pnakls, Pnakl pnakl, Database database)
    {
        this.pnakls = pnakls;
        this.pnakl = pnakl;
        this.database = database;
    }
}

