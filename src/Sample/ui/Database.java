package Sample.ui;



import Sample.mainObjects.Pnakl;
import Sample.mainObjects.Rnakl;
import Sample.mainObjects.Sklad;
import Sample.ui.tables.PnaklTable;
import Sample.ui.tables.RnaklTable;
import Sample.ui.tables.SkladTable;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database extends Application {


    private ObservableList<Pnakl> pnakls;
    private ObservableList<Rnakl> rnakls;
    private ObservableList<Sklad> sklads;

    private PnaklTable pnaklTable;
    private RnaklTable rnaklTable;
    private SkladTable skladTable;


    private static final String URL = "jdbc:mysql://localhost:3306/pbz3?useUnicode=true&useSSL=false&useJDBCCompliantTimezoneShift=true" +
            "&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "dufidi";

    private Connection connection;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            if (!connection.isClosed())
                System.out.println("Соединение установлено");

        } catch (SQLException ex) {
            System.err.println("Соединение не установлено");
            ex.printStackTrace(); // Понадобился, чтобы отловить исключения, скрытые выводом на экран предупреждения
            return;
        }

        initData();
        initUi(primaryStage);

        primaryStage.show();
    }



    public void clearData() {
        rnakls.clear();
        pnakls.clear();
        sklads.clear();

    }

    public void loadData() throws SQLException {
        Statement statement = connection.createStatement();
        rnakls.addAll(Rnakl.loadFromDatabase(statement));
        pnakls.addAll(Pnakl.loadFromDatabase(statement));
        sklads.addAll(Sklad.loadFromDatabase(statement));

        statement.close();
    }

    public void load() throws SQLException {
        Statement statement = connection.createStatement();
        rnakls.addAll(Rnakl.load(statement));
        pnakls.addAll(Pnakl.loadFromDatabase(statement));
        sklads.addAll(Sklad.loadFromDatabase(statement));

        statement.close();
    }


    public void initData() throws SQLException {
        Statement statement = connection.createStatement();
        rnakls = FXCollections.observableArrayList();
        pnakls = FXCollections.observableArrayList();
        sklads = FXCollections.observableArrayList();

    }


    public void initUi(Stage stage) throws SQLException {

        GridPane root = new GridPane();

        rnaklTable = new RnaklTable(getRnakls(), this);
        GridPane.setConstraints(rnaklTable, 0, 1);
        pnaklTable = new PnaklTable(getPnakls(), this);
        GridPane.setConstraints(pnaklTable, 0, 1);
        skladTable = new SkladTable(getSklads(), this);
        GridPane.setConstraints(skladTable, 0, 1);


        TabPane tabPane = new TabPane();

        Tab rnaklsTab = new Tab("Rnakls");
        rnaklsTab.setContent(rnaklTable);

        Tab pnaklsTab = new Tab("Pnakls");
        pnaklsTab.setContent(pnaklTable);

        Tab skladsTab = new Tab("Sklads");
        skladsTab.setContent(skladTable);

        tabPane.getTabs().addAll(rnaklsTab, pnaklsTab, skladsTab);


        GridPane.setConstraints(tabPane, 0, 1, 2, 1);



        root.getChildren().addAll(tabPane);

        Scene scene = new Scene(root, root.getPrefWidth(), root.getPrefHeight());
        stage.setScene(scene);
        getConnection();
        clearData();
        loadData();
    }




    public ObservableList<Rnakl> getRnakls() {
        return rnakls;
    }


    public void setRnakls(ObservableList<Rnakl> rnakls) {
        this.rnakls = rnakls;
    }


    public void setRnaklTable(RnaklTable rnaklTable) {
        this.rnaklTable = rnaklTable;
    }

    public RnaklTable getRnaklTable() {
        return rnaklTable;
    }

    public ObservableList<Pnakl> getPnakls() {
        return pnakls;
    }

    public void setPnakls(ObservableList<Pnakl> pnakls) {
        this.pnakls = pnakls;
    }

    public PnaklTable getPnaklTable() {
        return pnaklTable;
    }

    public void setPnaklTable(PnaklTable pnaklTable) {
        this.pnaklTable = pnaklTable;
    }

    public ObservableList<Sklad> getSklads() {
        return sklads;
    }

    public SkladTable getSkladTable() {
        return skladTable;
    }

    public void setSklads(ObservableList<Sklad> sklads) {
        this.sklads = sklads;
    }

    public void setSkladTable(SkladTable skladTable) {
        this.skladTable = skladTable;
    }



        public Connection getConnection () {
            return connection;
        }

        public void setConnection (Connection connection){
            this.connection = connection;
        }
    }




