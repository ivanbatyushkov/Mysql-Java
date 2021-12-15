package Sample.ui.menus;

import Sample.mainObjects.Pnakl;
import Sample.mainObjects.Rnakl;
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

public class RnaklsMenu extends ContextMenu {

    private ObservableList<Rnakl> rnakls;
    private Rnakl rnakl;
    private Database database;

    public RnaklsMenu()
    {
        MenuItem createNewItem = new MenuItem("New");
        MenuItem editItem = new MenuItem("Edit");
        MenuItem deleteItem = new MenuItem("Delete");

        createNewItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = new Stage();
                VBox vBox = new VBox();
                TextField date2Field = new TextField();
                date2Field.setPromptText("data");
                TextField perechen2Field = new TextField();
                perechen2Field.setPromptText("perechen inventarya");
                TextField rasxodField = new TextField();
                rasxodField.setPromptText("rasxod");
                TextField fio2Field = new TextField();
                fio2Field.setPromptText("fio2");
                TextField doljn2Field = new TextField();
                doljn2Field.setPromptText("doljnost2");

                Button ok = new Button("Ok");
                ok.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Rnakl rnakl = new Rnakl(Integer.parseInt(date2Field.getText()), perechen2Field.getText(), Integer.parseInt(rasxodField.getText()),
                                fio2Field.getText(), doljn2Field.getText());
                        try {
                            rnakl.loadToDatabase(database.getConnection());
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
                vBox.getChildren().addAll(date2Field, perechen2Field, rasxodField, fio2Field, doljn2Field, cancel, ok);
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
                TextField date2Field = new TextField();
                date2Field.setPromptText("data2");
                date2Field.setText(Integer.toString(rnakl.getDate2()));
                TextField perechen2Field = new TextField();
                perechen2Field.setPromptText("perechen2");
                perechen2Field.setText(rnakl.getPerechen2());
                TextField rasxodField = new TextField();
                rasxodField.setPromptText("rasx");
                rasxodField.setText(Integer.toString(rnakl.getRasxod()));
                TextField fio2Field = new TextField();
                fio2Field.setPromptText("fio2");
                fio2Field.setText(rnakl.getFio2());
                TextField doljn2Field = new TextField();
                doljn2Field.setPromptText("doljn2");
                doljn2Field.setText(rnakl.getDoljn2());



                Button ok = new Button("Ok");
                ok.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            rnakl.editInDatabase(new Rnakl(Integer.parseInt(date2Field.getText()), perechen2Field.getText(), Integer.parseInt(rasxodField.getText()),fio2Field.getText(), doljn2Field.getText()), database.getConnection());
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
                vBox.getChildren().addAll(date2Field, perechen2Field, rasxodField, fio2Field, doljn2Field, cancel, ok);
                Scene scene = new Scene(vBox, vBox.getMinWidth(), vBox.getMinHeight());
                stage.setScene(scene);
                stage.show();
            }
        });

        deleteItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    rnakl.deleteFromDatabase(database.getConnection());
                    database.clearData();
                    database.loadData();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });



        getItems().addAll(createNewItem, editItem, deleteItem);
    }

    public void setContent(ObservableList<Rnakl> rnakls, Rnakl rnakl, Database database)
    {
        this.rnakls = rnakls;
        this.rnakl = rnakl;
        this.database = database;
    }
}

