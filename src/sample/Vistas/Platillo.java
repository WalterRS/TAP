package sample.Vistas;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.Modelos.PlatilloDAO;

import javax.swing.*;

public class Platillo extends Stage {

    private TableView<PlatilloDAO> tbvPlatillo;

    private Scene escena;
    private VBox vBox;
    private Label lblTitulo;
    private TextField txtidPlatillo;
    private TextField txtimagen;
    private TextField txtnombre;
    private TextField txtprecio;
    private ComboBox cbIdCategoria;
    private Button btnGuardar;

    private PlatilloDAO objPDAO = null;

    public Platillo(TableView<PlatilloDAO> tbv){
        tbvPlatillo=tbv;
        CrearGUI();
        this.setScene(escena);
        this.setTitle("Altas y Modificaciones de Platillos");
        this.show();
    }

    private void CrearGUI() {
        vBox = new VBox();
        lblTitulo = new Label("Platillo");
        txtidPlatillo = new TextField("");
        txtidPlatillo.setPromptText("ID Platillo");
        txtimagen = new TextField("/sample/imagenes/prueba.png");
        txtimagen.setPromptText("URL Imagen");
        txtnombre= new TextField("");
        txtnombre.setPromptText("Nombre Platillo");
        txtprecio = new TextField("");
        txtprecio.setPromptText("Precio $00.00");
        cbIdCategoria = new ComboBox();
        cbIdCategoria.getItems().add("Entradas");
        cbIdCategoria.getItems().add("Comidas");
        cbIdCategoria.getItems().add("Bebidas");
        cbIdCategoria.getItems().add("Kids");
        cbIdCategoria.getItems().add("Postres");

        btnGuardar = new Button("Guardar");
        btnGuardar.setPrefSize(100, 50);
        btnGuardar.setStyle("-fx-font: 20 arial; -fx-base: #709eba");
        btnGuardar.setOnAction(event -> GuardarPlatillo());
        vBox.getChildren().addAll(lblTitulo,txtidPlatillo,txtimagen,txtnombre,txtprecio,cbIdCategoria,btnGuardar);
        escena = new Scene(vBox,250,300);
    }

    private void GuardarPlatillo() {
        String cb=""+cbIdCategoria.getValue();
        if (!cb.equals("null") && !(txtidPlatillo.getText()).equals("") && !(txtidPlatillo.getText()).equals("")
                && !(txtnombre.getText()).equals("") && !(txtprecio.getText()).equals("")){

            int txtidCategoria = 0;
            int idPlati=Integer.parseInt(txtidPlatillo.getText());
            String imag=txtimagen.getText();
            String nom=txtnombre.getText();
            double prec=Double.parseDouble(txtprecio.getText());

            if (cbIdCategoria.getValue().equals("Entradas"))
                txtidCategoria=1;
            else if (cbIdCategoria.getValue().equals("Comidas"))
                txtidCategoria=2;
            else if (cbIdCategoria.getValue().equals("Bebidas"))
                txtidCategoria=3;
            else if (cbIdCategoria.getValue().equals("Kids"))
                txtidCategoria=4;
            else if (cbIdCategoria.getValue().equals("Postres"))
                txtidCategoria=5;

        if( objPDAO == null ) {
            objPDAO = new PlatilloDAO();
            objPDAO.setIdPlatillo(idPlati);
            objPDAO.setImagen(imag);
            objPDAO.setNombre(nom);
            objPDAO.setPrecio(prec);
            objPDAO.setIdCategoria(txtidCategoria);
            objPDAO.INSERTAR();
        }
        else{
            objPDAO.setImagen(imag);
            objPDAO.setNombre(nom);
            objPDAO.setPrecio(prec);
            objPDAO.setIdCategoria(txtidCategoria);
            objPDAO.ACTUALIZAR();
        }
        tbvPlatillo.setItems(objPDAO.SELECCIONAR(0));
        tbvPlatillo.refresh();
        this.close();
        }else{
            JOptionPane.showMessageDialog(null,"Faltan Datos");
        }
    }

    public void setPlatilloDAO(PlatilloDAO objPDAO){

        this.objPDAO = objPDAO;
        txtidPlatillo.setText(String.valueOf(objPDAO.getIdPlatillo()));
        txtimagen.setText(String.valueOf(objPDAO.getImagen()));
        txtnombre.setText(objPDAO.getNombre());
        txtprecio.setText(String.valueOf(objPDAO.getPrecio()));
    }

}