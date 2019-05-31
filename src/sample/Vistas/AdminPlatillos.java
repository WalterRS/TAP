package sample.Vistas;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import sample.Componentes.BuCell;
import sample.Modelos.PlatilloDAO;

public class AdminPlatillos extends Stage {
    private Scene escena;
    private VBox vBox;
    private TableView<PlatilloDAO> tbvPlatillo;
    private Button btnAgregar;
    public AdminPlatillos(){
        CrearGUI();
        setTitle("Platillos");
        setScene(escena);
        show();
    }
    private void CrearGUI() {
        vBox = new VBox();
        tbvPlatillo = new TableView<>();
        CrearTabla();
        btnAgregar = new Button("Agregar");
        btnAgregar.setOnAction(event -> AgregarPlatillo());
        btnAgregar.setPrefSize(100, 100);
        btnAgregar.setStyle("-fx-font: 20 arial; -fx-base: #709eba");
        vBox.getChildren().addAll(tbvPlatillo,btnAgregar);
        escena = new Scene(vBox,700,200);

    }

    private void AgregarPlatillo() {
        new Platillo(tbvPlatillo);
    }

    private void CrearTabla(){

        TableColumn<PlatilloDAO, Integer> tbcid = new TableColumn<>("ID");
        tbcid.setCellValueFactory(new PropertyValueFactory<>("idPlatillo"));

        TableColumn<PlatilloDAO, String> tbcimg = new TableColumn<>("URL Imagen");
        tbcimg.setCellValueFactory(new PropertyValueFactory<>("imagen"));

        TableColumn<PlatilloDAO, String> tbcname = new TableColumn<>("Nombre");
        tbcname.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        TableColumn<PlatilloDAO, Double> tbcpre = new TableColumn<>("Precio");
        tbcpre.setCellValueFactory(new PropertyValueFactory<>("precio"));

        TableColumn<PlatilloDAO, Integer> tbcIdCategoria = new TableColumn<>("Categoria");
        tbcIdCategoria.setCellValueFactory(new PropertyValueFactory<>("idCategoria"));

        TableColumn<PlatilloDAO,String> tbcEditar = new TableColumn<>("Editar");

        tbcEditar.setCellFactory(new Callback<TableColumn<PlatilloDAO, String>, TableCell<PlatilloDAO, String>>() {
            @Override
            public TableCell<PlatilloDAO, String> call(TableColumn<PlatilloDAO, String> param) {
                return new BuCell(1);
            }
        });
        TableColumn<PlatilloDAO,String> tbcEliminar = new TableColumn<>("Eliminar");
        tbcEliminar.setCellFactory(new Callback<TableColumn<PlatilloDAO, String>, TableCell<PlatilloDAO, String>>() {
            @Override
            public TableCell<PlatilloDAO, String> call(TableColumn<PlatilloDAO, String> param) {
                return new BuCell(2);
            }
        });
        tbvPlatillo.getColumns().addAll(tbcid,tbcimg,tbcname,tbcpre,tbcIdCategoria,tbcEditar,tbcEliminar);
        tbvPlatillo.setItems(new PlatilloDAO().SELECCIONAR(0));
    }
}
