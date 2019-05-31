package sample.Vistas;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.Eventos.EventosCuadroMagico;

import javax.swing.*;

public class CuadroMagico  extends Stage {
    private VBox vBox;
    private Scene esena;
    public static int N=0;
    private TextField txt;
    private Button Generar;

    public CuadroMagico(){

        CrearGUI();
        esena = new Scene(vBox,200,100);
        setScene(esena);
        setTitle("Cuadro Magico");
        show();

    }

    private void CrearGUI() {
        vBox = new VBox();
        vBox.setSpacing(18);
        txt= new TextField();
        txt.setPromptText("Introduce Tamaño");
        Generar = new Button("Generar");
        Generar.setOnAction(event -> GenerarCuadro(txt.getText()));
        vBox.getChildren().add(txt);
        vBox.getChildren().add(Generar);


    }

    private void GenerarCuadro(String txt) {

        if(!txt.equals(""))
        {
            N=Integer.parseInt(txt);
            if (N%2!=0){
                EventosCuadroMagico ob=new EventosCuadroMagico(N);
                this.close();
            }else {
                JOptionPane.showMessageDialog(null,"Dato no es Impar");
            }
        }else{
            JOptionPane.showMessageDialog(null,"Faltan Datos ");
        }

    }
}
