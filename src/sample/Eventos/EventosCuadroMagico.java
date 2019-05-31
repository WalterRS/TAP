package sample.Eventos;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;


public class EventosCuadroMagico extends Stage {

    private int N;
    private Button[][] buttons;
    private Scene scene;
    private GridPane panel;
    private RandomAccessFile archivo;


    public EventosCuadroMagico(int n)  {
        this.N = n;
        try {
            crearArchivo();
            llenarArchivo();
        } catch (IOException e) {
            e.printStackTrace();
        }
        CrearGUI();

        scene = new Scene(panel, 400, 400);
        setScene(scene);
        setTitle("Cuadro Magico");
        show();
    }

    private void CrearGUI()  {
        panel = new GridPane();
        buttons = new Button[N][N];
        try {
            archivo = new RandomAccessFile("archivo.walt", "rw");

        for (int j = 0; j < N; j++) {
            for (int i = 0; i < N; i++) {
                buttons[i][j] = new Button();
                buttons[i][j].setStyle("-fx-font: 15 arial; -fx-base: #F40D00;");
                buttons[i][j].setMaxSize(60, 60);
                buttons[i][j].setLineSpacing(10);
                buttons[i][j].setText(String.valueOf(archivo.readInt()));
                panel.add(buttons[i][j], i, j);
            }
        }
        archivo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        panel.setAlignment(Pos.CENTER);
        panel.setStyle("-fx-background-color: #ffa895");

    }
    private void llenarArchivo() throws IOException {
        int x = N / 2, y = 0;
        RandomAccessFile ar = new RandomAccessFile("archivo.walt", "rw");

        System.out.println("Llenar inicio: ("+x+","+y+")");
        for (int i = 1; i <= N * N; i++) {

            System.out.println("Iteracion " + i + ", Llenar en: ("+x+","+y+")");
            if(casillaDisponible(x, y)) {
                System.out.println("Iteracion " + i + ", Casilla no disponible");
                y = (y + 1 > N - 1) ? 0 : y + 1;
                y = (y + 1 > N - 1) ? 0 : y + 1;
                x = (x - 1 < 0) ? N - 1 : x - 1;
                System.out.println("Iteracion " + i + ", Nueva casilla: ("+x+","+y+")");
            }

            ar.seek(convertirMatrizAVector(x, y) * 4);
            ar.writeInt(i);

            x++; y--;

            x = (x > N - 1) ? 0 : x;
            y = (y < 0) ? N - 1 : y;

            System.out.println("Iteracion " + i + ", Cambio a: ("+x+","+y+")");
            System.out.println("-------------------------------------");
        }

        ar.close();
    }

    private void crearArchivo() throws IOException {
        RandomAccessFile ar = new RandomAccessFile("archivo.walt", "rw");
        for (int i = 0; i < N * N; i++)
            ar.writeInt(0);
        ar.close();
    }

    private boolean casillaDisponible(int x, int y) throws IOException {
        boolean disp = true;
        RandomAccessFile ar = new RandomAccessFile("archivo.walt", "rw");
        ar.seek((convertirMatrizAVector(x, y)) * 4);
        if(ar.readInt() == 0)
            disp = false;
        ar.close();
        return disp;
    }

    private int convertirMatrizAVector(int x, int y){
        return (y*N) + x;
    }

}
