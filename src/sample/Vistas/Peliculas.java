package sample.Vistas;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.Modelos.PeliculaDAO;


public class Peliculas extends Stage{

    private TableView<PeliculaDAO> tbvPeliculas;

    private Scene escena;
    private VBox vBox;
    private Label lblTitulo;
    private TextField txtNomPeli;
    private TextField txtDuraPeli;
    private TextField txtSipnosisPeli;
    private TextField txtClasePeli;
    private TextField txtCatPeli;
    private Button btnGuardar;

    private PeliculaDAO objPDAO = null;

    public Peliculas(TableView<PeliculaDAO> tbv){

        tbvPeliculas = tbv;

        CrearGUI();
        this.setScene(escena);
        this.setTitle("Altas y Modificaciones de Películas");
        this.show();
    }

    private void CrearGUI() {

        vBox = new VBox();
        lblTitulo = new Label("PELICULA");
        txtNomPeli = new TextField();
        txtNomPeli.setPromptText("Introduce el nombre");
        txtDuraPeli = new TextField();
        txtDuraPeli.setPromptText("Introduce la duración");
        txtSipnosisPeli = new TextField();
        txtSipnosisPeli.setPromptText("Aquí va la sinopsis :)");
        txtClasePeli = new TextField();
        txtClasePeli.setPromptText("Indica la clase");
        txtCatPeli = new TextField();
        txtCatPeli.setPromptText("Indica la categoria");
        btnGuardar = new Button("Agregar");
        btnGuardar.setOnAction(event -> GuardarPelicula());
        vBox.getChildren().addAll(lblTitulo,txtNomPeli,txtDuraPeli,txtSipnosisPeli,txtClasePeli,txtCatPeli,btnGuardar);
        escena = new Scene(vBox,250,300);
    }

    private void GuardarPelicula() {

        String nomb = txtNomPeli.getText();
        int dura = Integer.parseInt(txtDuraPeli.getText());
        String sino = txtSipnosisPeli.getText();
        String clas = txtClasePeli.getText();
        int cate = Integer.parseInt(txtCatPeli.getText());

        if( objPDAO == null ) {
            objPDAO = new PeliculaDAO();
            objPDAO.setNomPelicula(nomb);
            objPDAO.setDuracion(dura);
            objPDAO.setDescPelicula(sino);
            objPDAO.setClase(clas);
            objPDAO.setIdCategoria(cate);
            objPDAO.INSERTAR();
        }
        else{
            objPDAO.setNomPelicula(nomb);
            objPDAO.setDuracion(dura);
            objPDAO.setDescPelicula(sino);
            objPDAO.setClase(clas);
            objPDAO.setIdCategoria(cate);
            objPDAO.ACTUALIZAR();
        }

        tbvPeliculas.setItems(objPDAO.SELECCIONAR());
        tbvPeliculas.refresh();
        this.close();
    }

    public void setPeliculaDAO(PeliculaDAO objPDAO){

        this.objPDAO = objPDAO;
        txtNomPeli.setText(objPDAO.getNomPelicula());
        txtDuraPeli.setText(String.valueOf(objPDAO.getDuracion()));
        txtSipnosisPeli.setText(objPDAO.getDescPelicula());
        txtClasePeli.setText(objPDAO.getClase());
        txtCatPeli.setText(String.valueOf(objPDAO.getIdCategoria()));
    }

}