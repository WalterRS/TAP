package sample.Vistas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.Componentes.Hojas;

public class Impresora extends Stage  {
    private Scene scene;
    private BorderPane panel;
    private Button ordenador1,ordenador2;
    private VBox vbox, vbox2;
    private HBox hbox;
    private ImageView imp,pc1,pc2;
    private Label name1,name2;
    private ProgressBar proceso;
    private ObservableList<Impresion> lista;
    private TableView<Impresion> tblImpresiones;
    private TableColumn noDoc;
    private TableColumn name;
    private TableColumn noHojas;
    private TableColumn status;
    private TableColumn origen;
    private int cont=0;

    public Impresora(){

        panel = new BorderPane();
        scene = new Scene(panel, 600, 500, Color.BROWN);
        CrearGUI();
        setTitle("Impresora");
        setScene(scene);
        show();
    }

    private void CrearGUI() {
        vbox = new VBox();
        hbox = new HBox();
        vbox2 = new VBox();
        proceso=new ProgressBar(0);

        imp=new ImageView("/sample/imagenes/impresora.jpg");
        imp.setFitWidth(250);
        imp.setFitHeight(150);

        pc1=new ImageView("/sample/imagenes/pc.jpg");
        pc1.setFitWidth(150);
        pc1.setFitHeight(100);
        name1= new Label("PC 1");
        name1.setStyle("-fx-font-size: 14pt; -fx-opacity: 0.9; -fx-font-weight: bold; -fx-text-fill: red;");

        pc2=new ImageView("/sample/imagenes/pc.jpg");
        pc2.setFitWidth(150);
        pc2.setFitHeight(100);
        name2= new Label("PC 1");
        name2.setStyle("-fx-font-size: 14pt; -fx-opacity: 0.9; -fx-font-weight: bold; -fx-text-fill: blue;");

        ordenador1 = new Button("Impresora");
        ordenador1.setPrefSize(200, 80);
        ordenador1.setStyle("-fx-font: 20 arial; -fx-base: #ba2e35");
        ordenador1.setOnAction(event -> Imprimir(1));

        ordenador2 = new Button("Impresora");
        ordenador2.setPrefSize(200, 80);
        ordenador2.setStyle("-fx-font: 20 arial; -fx-base: #3e56ba");
        ordenador2.setOnAction(event -> Imprimir(2));

        vbox2.getChildren().addAll(name1,pc1,ordenador1);
        hbox.getChildren().addAll(vbox2);
        vbox2 = new VBox();
        vbox2.getChildren().addAll(imp,proceso);
        vbox2.setAlignment(Pos.CENTER);
        hbox.getChildren().add(vbox2);
        vbox2 = new VBox();

        vbox2.getChildren().addAll(name2,pc2,ordenador2);
        hbox.getChildren().add(vbox2);
        hbox.setAlignment(Pos.CENTER);
        vbox.getChildren().add(hbox);
        /*los atributos que debe tener la tabla son: No. Doc / Nombre Archivo / No Hojas / Status / Computadora Origen*/

        lista= FXCollections.observableArrayList();
        tblImpresiones = new TableView<>();
        noDoc= new TableColumn("N° Documento");
        noDoc.setCellValueFactory(new PropertyValueFactory<>("noDoc"));
        noDoc.setPrefWidth(120);
        name= new TableColumn("Nombre");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        name.setPrefWidth(120);
        noHojas=new TableColumn("N° Hojas");
        noHojas.setCellValueFactory(new PropertyValueFactory<>("noHojas"));
        noHojas.setPrefWidth(90);
        status=new TableColumn("Estado");
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        status.setPrefWidth(120);
        origen= new TableColumn("PC de Origen");
        origen.setCellValueFactory(new PropertyValueFactory<>("origen"));
        origen.setPrefWidth(120);

        tblImpresiones.getColumns().addAll(noDoc,name,noHojas,status,origen);
        tblImpresiones.setItems(lista);
        tblImpresiones.refresh();
        vbox.getChildren().add(tblImpresiones);
        panel.setCenter(vbox);

    }
    public void Imprimir(int pc){
        /*Los estatus posibles son: Imprimiendo / En espera*/
        int noH;
        noH=(int) (Math.random() * 20) + 1;
        cont=cont+1;

        if (lista.size()==0){
            Impresion obj = new Impresion();
            obj.noDoc=(cont);
            obj.name=("Documento"+cont);
            obj.noHojas=(noH);
            obj.status=("Imprimiendo");
            obj.origen=(pc);
            lista.add(obj);
            Hojas h= new Hojas(noH,proceso,lista);
            h.start();
        }else{
            Impresion obj = new Impresion();
            obj.noDoc=(cont);
            obj.name=("Documento"+cont);
            obj.noHojas=(noH);
            obj.status=("En espera");
            obj.origen=(pc);
            lista.add(obj);
        }
        tblImpresiones.setItems(lista);
        tblImpresiones.refresh();

    }

}
