package sample.Vistas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.Eventos.EventosOrdenes;
import sample.Modelos.MesaDAO;
import sample.Modelos.OrdenDAO;
import sample.Modelos.PlatilloDAO;

public class MenuRestaurante extends Stage {

    private TableView<PlatilloDAO> tbvPlatillos;
    private Scene scene;
    private TabPane tabPane;
    private Tab menuBebidas, menuComidas, menuPostres, menuKids, menuEntradas;
    private BorderPane panel;
    private HBox hbox;
    private VBox vbox,vboxG;
    private Button[] btnAddOrden;
    private Button btnTerminar;
    private ImageView img;
    private Label txt;
    private ObservableList<PlatilloDAO> list= FXCollections.observableArrayList();
    private ScrollPane corre;
    OrdenDAO ob;
    MesaDAO obj;
    private int idmes;
    public MenuRestaurante(int id) {

        idmes=id;

        panel = new BorderPane();
        scene = new Scene(panel, 800, 500, Color.BROWN);
        scene.getStylesheets().add(getClass().getResource("/sample/CSS/EstilosRestaurant.css").toExternalForm());
        CrearGUI();
        setTitle("Menu");
        setScene(scene);
        setMaximized(true);
        show();

    }

    private void CrearGUI() {


        tabPane = new TabPane();

        //Crear Menus

        menuBebidas = new Tab("Bebidas ");
        menuComidas = new Tab("Comidas");
        menuPostres = new Tab("Postres");
        menuKids = new Tab("Kids");
        menuEntradas = new Tab("Entradas");


        EsenaMenu();

        //Añadei todos los menus al panel
        tabPane.getTabs().addAll(menuEntradas, menuComidas, menuBebidas, menuKids, menuPostres);

        panel.setTop(tabPane);

    }

    private void EsenaMenu() {

        for (int i = 1; i <= 5; i++) {
            list= new PlatilloDAO().SELECCIONAR(i);
            Esena(i);
            if (i==1)
                menuEntradas.setContent(vboxG);
            if(i==2)
                menuComidas.setContent(vboxG);
            if (i==3)
                menuBebidas.setContent(vboxG);
            if (i==4)
                menuKids.setContent(vboxG);
            if (i==5)
                menuPostres.setContent(vboxG);
        }


    }

    public void Esena(int n) {

        int tamaño = new PlatilloDAO().SELECCIONAR(n).size();
        btnAddOrden = new Button[tamaño];
        corre = new ScrollPane();
        hbox = new HBox();
        for (int j = 0; j < tamaño; j++) {
            vbox = new VBox();
            img = new ImageView();
            img.setImage(new Image(list.get(j).getImagen()));
            img.setFitHeight(250);
            img.setFitWidth(250);
            vbox.getChildren().add(img);
            vbox.setAlignment(Pos.CENTER);
            txt = new Label(list.get(j).getNombre()+"\n"+list.get(j).getPrecio());
            txt.setStyle("-fx-font: 20 arial; -fx-color: RED");
            vbox.getChildren().add(txt);
            vbox.setAlignment(Pos.CENTER);
            btnAddOrden[j] = new Button("Añadir Orden");
            btnAddOrden[j].setStyle("-fx-font: 15 arial; -fx-base: #ba2c3a");
            btnAddOrden[j].setPrefSize(150, 70);
            btnAddOrden[j].addEventHandler(MouseEvent.MOUSE_CLICKED,new EventosOrdenes(idmes,list.get(j).getIdPlatillo(),list.get(j).getPrecio()));
            vbox.getChildren().addAll(btnAddOrden[j]);
            vbox.setAlignment(Pos.CENTER);
            hbox.setSpacing(15);
            hbox.setAlignment(Pos.CENTER);
            hbox.getChildren().addAll(vbox);
            corre.setContent(hbox);
        }
        vboxG=new VBox();
        vboxG.getChildren().add(corre);
        btnTerminar = new Button("Terminar Orden");
        btnTerminar.setPrefSize(250, 100);
        btnTerminar.setStyle("-fx-font: 20 arial; -fx-base: #67ba37");
        btnTerminar.setOnAction(event -> {
            Restaurant obj2= new Restaurant();
            close();});
        vboxG.getChildren().addAll(btnTerminar);
        vboxG.setSpacing(15);
        vboxG.setAlignment(Pos.BOTTOM_CENTER);
    }

}