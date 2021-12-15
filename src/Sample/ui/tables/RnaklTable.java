package Sample.ui.tables;


import Sample.mainObjects.Rnakl;
import Sample.ui.Database;
import Sample.ui.menus.RnaklsMenu;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class RnaklTable extends TableView<Rnakl> {

    public RnaklTable(ObservableList<Rnakl> rnakls, Database database)
    {
        setWidth(400);

        TableColumn<Rnakl, Integer> date2Column = new TableColumn<>("Дата");
        TableColumn<Rnakl, String> perechen2Column = new TableColumn<>("Перечень инвентаря");
        TableColumn<Rnakl, Integer> rasxodColumn = new TableColumn<>("Расход инвентаря");
        TableColumn<Rnakl, String> fio2Column = new TableColumn<>("ФИО");
        TableColumn<Rnakl, String> doljn2Column = new TableColumn<>("Должность");

        date2Column.setCellValueFactory(new PropertyValueFactory<>("date2"));
        perechen2Column.setCellValueFactory(new PropertyValueFactory<>("perechen2"));
        rasxodColumn.setCellValueFactory(new PropertyValueFactory<>("rasxod"));
        fio2Column.setCellValueFactory(new PropertyValueFactory<>("fio2"));
        doljn2Column.setCellValueFactory(new PropertyValueFactory<>("doljn2"));


        getColumns().addAll(date2Column, perechen2Column, rasxodColumn, fio2Column, doljn2Column);

        setItems(rnakls);

        RnaklsMenu rnaklsMenu = new RnaklsMenu();

        setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.isSecondaryButtonDown())
                {
                    rnaklsMenu.setContent(getItems(), getSelectionModel().getSelectedItem(), database);
                    rnaklsMenu.show(RnaklTable.this, event.getScreenX(), event.getScreenY());
                }
            }
        });
    }
}
