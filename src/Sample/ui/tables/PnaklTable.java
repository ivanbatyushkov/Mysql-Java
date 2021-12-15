package Sample.ui.tables;

import Sample.mainObjects.Pnakl;
import Sample.ui.Database;
import Sample.ui.menus.PnaklsMenu;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.time.LocalDate;

public class PnaklTable extends TableView<Pnakl> {

    public PnaklTable(ObservableList<Pnakl> pnakls, Database database)
    {
        setWidth(400);

        TableColumn<Pnakl, Integer> date1Column = new TableColumn<>("Дата");
        TableColumn<Pnakl, String> perechen1Column = new TableColumn<>("Перечень инвентаря");
        TableColumn<Pnakl, Integer> kolvoColumn = new TableColumn<>("Количество инвентаря");
        TableColumn<Pnakl, String> fio1Column = new TableColumn<>("ФИО");
        TableColumn<Pnakl, String> doljn1Column = new TableColumn<>("Должность");

        date1Column.setCellValueFactory(new PropertyValueFactory<>("date1"));
        perechen1Column.setCellValueFactory(new PropertyValueFactory<>("perechen1"));
        kolvoColumn.setCellValueFactory(new PropertyValueFactory<>("kolvo"));
        fio1Column.setCellValueFactory(new PropertyValueFactory<>("fio1"));
        doljn1Column.setCellValueFactory(new PropertyValueFactory<>("doljn1"));


        getColumns().addAll(date1Column, perechen1Column, kolvoColumn, fio1Column, doljn1Column);

        setItems(pnakls);

        PnaklsMenu pnaklsMenu = new PnaklsMenu();

        setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.isSecondaryButtonDown())
                {
                    pnaklsMenu.setContent(getItems(), getSelectionModel().getSelectedItem(), database);
                    pnaklsMenu.show(PnaklTable.this, event.getScreenX(), event.getScreenY());
                }
            }
        });
    }
}

