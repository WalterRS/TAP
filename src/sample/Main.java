package sample;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import sample.Componentes.Consumidor;
import sample.Componentes.Productor;
import sample.Componentes.RecursoCompartido;
import sample.Modelos.MySQL;
import sample.Modelos.MySQL_Restaurnt;
import sample.Vistas.*;


public class Main extends Application implements EventHandler {

    private Scene scene;
    private MenuBar menuBar;
    private Menu menuCompetencia1, menuCompetencia2,menuSalir;
    private MenuItem itmCalculadora,itmRestaurante,itmCuadroMagico,itmPeliculas;
    private MenuItem itmCarrera,itmHilosSincrono,itmImpresoras;
    private MenuItem itmSalir,itmNsalir;
    private BorderPane panel;

    @Override
    public void start(Stage primaryStage) throws Exception{

        EscenaPrincipal();
        CreateMenu();
        primaryStage.setTitle("Topicos Avanzados en Programacion");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.addEventHandler(WindowEvent.WINDOW_SHOWN,this);
        primaryStage.show();

    }

    private void EscenaPrincipal()
    {
        panel = new BorderPane();
        scene = new Scene(panel);
        scene.getStylesheets().add(getClass().getResource("/sample/CSS/Bootstrap3.css").toExternalForm());
    }

    public void CreateMenu(){
        menuBar = new MenuBar();

        menuCompetencia1= new Menu("Competencia 1");
        menuCompetencia2 =new Menu("Competencia 2");
        menuSalir=new Menu("Salir");

        itmCalculadora= new MenuItem("Calculadora");
        itmRestaurante=new MenuItem("Restaurante");
        itmCuadroMagico=new MenuItem("Cuadro Magico");
        itmPeliculas=new MenuItem("Peliculas");

        itmCarrera= new MenuItem("Hilos");
        itmHilosSincrono=new MenuItem("Hilos Sincronizados");
        itmImpresoras=new MenuItem("Procesos Impresora");

        itmSalir= new MenuItem("YES");
        itmNsalir=new MenuItem("NO");

        menuCompetencia1.getItems().addAll(itmCalculadora,itmRestaurante,itmCuadroMagico,itmPeliculas);
        menuCompetencia2.getItems().addAll(itmCarrera,itmHilosSincrono,itmImpresoras);
        menuSalir.getItems().addAll(itmSalir,itmNsalir);

        itmCalculadora.setOnAction(event -> EventoItem(1));
        itmRestaurante.setOnAction(event -> EventoItem(2));
        itmCuadroMagico.setOnAction(event -> EventoItem(3));
        itmPeliculas.setOnAction(event -> EventoItem(4));

        itmCarrera.setOnAction(event -> EventoItem2(1));
        itmHilosSincrono.setOnAction(event -> EventoItem2(2));
        itmImpresoras.setOnAction(event -> EventoItem2(3));

        itmSalir.setOnAction(event -> EveSalir());


        menuBar.getMenus().addAll(menuCompetencia1,menuCompetencia2,menuSalir);
        panel.setTop(menuBar);

    }

    private void EventoItem2(int opc) {
        switch (opc){
            case 1:
                Vengadromo obj = new Vengadromo();
                break;
            case 2:
                RecursoCompartido obRC= new RecursoCompartido();
                new Productor(obRC).start();
                new Consumidor(obRC).start();
                break;
            case 3:
                Impresora ob= new Impresora();
                break;
        }
    }

    private void EveSalir() {

        System.exit(0);
    }

    private void EventoItem(int opc) {
        switch (opc){
            case 1:
                Calculadora obj=new Calculadora();
                break;
            case 2:
                Restaurant obj2= new Restaurant();
                break;
            case 3:
                CuadroMagico obj3= new CuadroMagico();
                break;
            case 4:
                ListaPeliculas obj4=new ListaPeliculas();
        }
    }
    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void handle(Event event) {
        MySQL.getConexion();
        MySQL_Restaurnt.getConexion();
        if( MySQL.conn != null ){
            System.out.println("Conexion Peliculas Exitosa!!");
        }else{
            System.out.println("Conexion Peliculas Fallo!!");
        }
        if( MySQL_Restaurnt.con != null ){
            System.out.println("Conexion Restaurante Exitosa!!");
        }else{
            System.out.println("Conexion Restaurante Fallo!!");
        }
    }

}
