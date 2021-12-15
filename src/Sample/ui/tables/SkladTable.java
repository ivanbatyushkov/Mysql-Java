package Sample.ui.tables;

import Sample.mainObjects.Sklad;
import Sample.ui.Database;
import Sample.ui.menus.SkladsMenu;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.sql.Connection;
import java.sql.SQLException;

public class SkladTable extends TableView<Sklad> {

    public SkladTable(ObservableList<Sklad> sklads, Database database)
    {
        setWidth(400);

        TableColumn<Sklad, Integer> numberColumn = new TableColumn<>("Номер склада");
        TableColumn<Sklad, Integer> nameColumn = new TableColumn<>("Телефон склада");
        TableColumn<Sklad, String> phoneColumn = new TableColumn<>("Название склада");
        TableColumn<Sklad, Integer> date3Column = new TableColumn<>("Дата");

        numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        date3Column.setCellValueFactory(new PropertyValueFactory<>("date3"));


        getColumns().addAll(numberColumn, nameColumn, phoneColumn, date3Column);

        setItems(sklads);

        SkladsMenu skladsMenu = new SkladsMenu();

        setOnMousePressed(event -> {
            if(event.isSecondaryButtonDown())
            {
                skladsMenu.setContent(getItems(), getSelectionModel().getSelectedItem(), database);
                skladsMenu.show(SkladTable.this, event.getScreenX(), event.getScreenY());
            }
        });
    }

}
