package sample.Vistas;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.Componentes.Vengadores;

public class Vengadromo extends Stage {
    ProgressBar[] pgnCarriles = new ProgressBar[7];

    private VBox vbox;
    private Button btnIniciar;
    private Scene esena;
    private String[] vengadores={"Thor","Capitan Cmerica","Hulk","Black Panther","Spider Man","Dr. Strench"};
    private Vengadores[] thrVengadores = new Vengadores[7];
    private Label nombre;

    public Vengadromo(){
        CrearGUI();
        this.setScene(esena);
        this.setTitle("Corriendo de Thanos");
        this.show();

    }

    private void CrearGUI() {
        vbox = new VBox();

        for (int i=0;i<vengadores.length;i++){
            nombre= new Label();
            nombre.setText(vengadores[i]);
            pgnCarriles[i]= new ProgressBar(0);
            thrVengadores[i] = new Vengadores(vengadores[i],pgnCarriles[i]);
            vbox.getChildren().add(nombre);
            vbox.getChildren().add(pgnCarriles[i]);
        }
        btnIniciar= new Button("Iniciar Carrera");
        btnIniciar.setOnAction(event -> IniciarCarrera());
        vbox.getChildren().add(btnIniciar);
        esena=new Scene(vbox,250,300);
    }

    private void IniciarCarrera() {

        for (int i=0;i<vengadores.length;i++){
            thrVengadores[i].start();
        }
    }
}
