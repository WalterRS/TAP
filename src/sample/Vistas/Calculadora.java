package sample.Vistas;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.Eventos.EventosCalculadora;


public class Calculadora extends Stage {
    public static TextField txtOperation;
    private int posBtn;
    private VBox vBox;
    private HBox[] arHbox;
    private Button[] arBtns;
    private String[] valores = {"7","8","9","+","4","5","6","-","1","2","3","*","0",".","=","/"};
    private Scene escena;
    private Button Clear;

    public Calculadora(){

        CrearGUI();
        escena = new Scene(vBox, 250 ,  300);
        setScene(escena);
        setTitle("Calculadorcita");
        show();

    }
    private void CrearGUI () {
        vBox = new VBox();
        vBox.setSpacing(10);
        txtOperation= new TextField();
        txtOperation.setAlignment(Pos.BASELINE_RIGHT);
        txtOperation.setEditable(false);
        vBox.getChildren().add(txtOperation);

        arHbox = new HBox[4];
        arBtns=new Button[16];
        Clear = new Button("CE");
        Clear.setOnAction(event -> Clear());
        Clear.setPrefSize(245,60);
        Clear.setStyle("-fx-font: 15 arial; -fx-base: #F40D00;");
        vBox.getChildren().addAll(Clear);
        int posBtn=0;
        for(int i=0; i<arHbox.length;i++){
            arHbox[i]= new HBox();
            for(int j=0; j<4; j++){
                arBtns[posBtn]= new Button(valores[posBtn]);
                arBtns[posBtn].setPrefSize(60,60);
                arBtns[posBtn].setStyle("-fx-font: 15 arial");
                arBtns[posBtn].addEventHandler(MouseEvent.MOUSE_CLICKED,new EventosCalculadora(valores[posBtn],txtOperation));
                arHbox[i].getChildren().add(arBtns[posBtn]);
                arHbox[i].setSpacing(5);
                posBtn++;
            }
        }

        vBox.getChildren().addAll(arHbox[0],arHbox[1],arHbox[2],arHbox[3]);

    }
    private void Clear(){
        EventosCalculadora.txtOperation.setText("");
        EventosCalculadora.suma1="";
        EventosCalculadora.suma2="";
        EventosCalculadora.simbolo="";
        EventosCalculadora.bandValor=false;
        EventosCalculadora.bandPunto=false;
        EventosCalculadora.banderaIgual=false;
        EventosCalculadora.banderaError=false;
    }
}

