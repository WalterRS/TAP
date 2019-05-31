package sample.Vistas;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import sample.Componentes.BottCell;
import sample.Modelos.V_CategoriasDAO;
import sample.Modelos.V_FacturasDAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

import java.util.Date;

public class Administrador extends Stage {
    private Scene scene;
    private BorderPane panel;
    private TableView<V_FacturasDAO> tbvOrden;
    private VBox vbox;
    private HBox hbox;
    private Button anadirPlatillo;
    ObservableList<V_CategoriasDAO> listCat= FXCollections.observableArrayList();

    public Administrador(){
        panel = new BorderPane();
        scene = new Scene(panel, 800, 500, Color.BROWN);
        CrearGUI();
        setTitle("Admin");
        setScene(scene);
        setMaximized(true);
        show();
    }
    private void CrearGUI() {
        vbox= new VBox();
        hbox= new HBox();
        hbox.setAlignment(Pos.CENTER);
        CrearTableAdmin();
        anadirPlatillo= new Button("Administrar Platillos");
        anadirPlatillo.setPrefSize(250,60);
        anadirPlatillo.setStyle("-fx-font: 20 arial; -fx-base: #ba4539");
        anadirPlatillo.setOnAction(event -> new AdminPlatillos());
        vbox.getChildren().add(anadirPlatillo);
        CrearGraphicQuincenal();
        CrearGraphicCategorias();
        vbox.getChildren().add(hbox);
        vbox.setAlignment(Pos.CENTER);
        panel.setCenter(vbox);
    }
    private void CrearTableAdmin() {
        TableColumn<V_FacturasDAO, Integer> tbcId = new TableColumn<>("ID Orden");
        tbcId.setCellValueFactory(new PropertyValueFactory<>("idFolio"));

        TableColumn<V_FacturasDAO, Integer> tbcidM = new TableColumn<>("Mesa");
        tbcidM.setCellValueFactory(new PropertyValueFactory<>("idMesa"));

        TableColumn<V_FacturasDAO, String> tbcEstado = new TableColumn<>("ID Estado");
        tbcEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));

        TableColumn<V_FacturasDAO, Integer> tbcIdPla = new TableColumn<>("ID Platillo");
        tbcIdPla.setCellValueFactory(new PropertyValueFactory<>("idPlatillo"));

        TableColumn<V_FacturasDAO, String> tbcNombre = new TableColumn<>("Nombre");
        tbcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        TableColumn<V_FacturasDAO, Double> tbcPre = new TableColumn<>("Precio");
        tbcPre.setCellValueFactory(new PropertyValueFactory<>("precio"));

        TableColumn<V_FacturasDAO, String> tbcfecha=new TableColumn<>("Fecha");
        tbcfecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));

        TableColumn<V_FacturasDAO, String> tbcdescr = new TableColumn<>("Estado");
        tbcdescr.setCellValueFactory(new PropertyValueFactory<>("descr"));

        TableColumn<V_FacturasDAO,String> tbcEditar = new TableColumn<>("Editar");
        tbcEditar.setCellFactory(new Callback<TableColumn<V_FacturasDAO, String>, TableCell<V_FacturasDAO, String>>() {
            @Override
            public TableCell<V_FacturasDAO, String> call(TableColumn<V_FacturasDAO, String> param) {
                return new BottCell(1);
            }
        });
        TableColumn<V_FacturasDAO,String> tbcEliminar = new TableColumn<>("Eliminar");
        tbcEliminar.setCellFactory(new Callback<TableColumn<V_FacturasDAO, String>, TableCell<V_FacturasDAO, String>>() {
            @Override
            public TableCell<V_FacturasDAO, String> call(TableColumn<V_FacturasDAO, String> param) {
                return new BottCell(2);
            }
        });

        tbvOrden = new TableView<>();
        tbvOrden.getColumns().addAll(tbcId, tbcidM, tbcIdPla,tbcNombre,tbcEstado,tbcdescr,tbcfecha,tbcPre,tbcEditar,tbcEliminar);
        tbvOrden.setItems(new V_FacturasDAO().SELECCIONAR());
        vbox.getChildren().addAll(tbvOrden);
    }
    private void CrearGraphicCategorias(){
        int entradas,comidas,bebidas,kids,postres;
        String fecha;
        Date d= new Date();
        fecha=(d.getYear()+1900)+"-"+(d.getMonth()+1)+"-01";
        listCat= new V_CategoriasDAO().SELECCIONAR(1,fecha);
        entradas=listCat.size();
        listCat= new V_CategoriasDAO().SELECCIONAR(2,fecha);
        comidas=listCat.size();
        listCat= new V_CategoriasDAO().SELECCIONAR(3,fecha);
        bebidas=listCat.size();
        listCat= new V_CategoriasDAO().SELECCIONAR(4,fecha);
        kids=listCat.size();
        listCat= new V_CategoriasDAO().SELECCIONAR(5,fecha);
        postres=listCat.size();

        ObservableList<PieChart.Data> valueList = FXCollections.observableArrayList(
                new PieChart.Data("1.Entradas: "+entradas, entradas),
                new PieChart.Data("2.Comidas: "+comidas, comidas),
                new PieChart.Data("3.Bebidas: "+bebidas, bebidas),
                new PieChart.Data("4.Kids: "+kids, kids),
                new PieChart.Data("5.Postres: "+postres, postres));
        // create a pieChart with valueList data.
        PieChart pieChart = new PieChart(valueList);
        pieChart.setTitle("Gráfica Comparativa para Platillos");
        //adding pieChart to the root.
        hbox.getChildren().addAll(pieChart);
    }
    private void CrearGraphicQuincenal(){
        int semanaPsada,semanaActual;
        String fechaInicial;
        String fechaMedia;
        String fechaFinal;
        Date d= new Date();

        fechaFinal=(d.getYear()+1900)+"-"+(d.getMonth()+1)+"-"+(d.getDate()-8);
        fechaInicial=(d.getYear()+1900)+"-"+(d.getMonth()+1)+"-"+(d.getDate()-14);
        listCat= new V_CategoriasDAO().SELECCIONARQUINCENA(fechaInicial,fechaFinal);
        semanaPsada=listCat.size();

        fechaFinal=(d.getYear()+1900)+"-"+(d.getMonth()+1)+"-"+d.getDate();
        fechaInicial=(d.getYear()+1900)+"-"+(d.getMonth()+1)+"-"+(d.getDate()-7);
        listCat= new V_CategoriasDAO().SELECCIONARQUINCENA(fechaInicial,fechaFinal);
        semanaActual=listCat.size();
        ObservableList<PieChart.Data> valueList = FXCollections.observableArrayList(
                new PieChart.Data("SEMANA PASADA "+semanaPsada,semanaPsada ),
                new PieChart.Data("SEMANA ACTUAL "+semanaActual,semanaActual ));
        // create a pieChart with valueList data.
        PieChart pieChart = new PieChart(valueList);
        pieChart.setTitle("Gráfica Comparativa de Pedidos");
        //adding pieChart to the root.
        hbox.getChildren().addAll(pieChart);
    }
}
