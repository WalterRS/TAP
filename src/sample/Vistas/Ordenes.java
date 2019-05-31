package sample.Vistas;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.Modelos.V_FacturasDAO;

public class Ordenes extends Stage {

    private TableView<V_FacturasDAO> tbvOrden;

    private Scene escena;
    private VBox vBox;
    private Label lblTitulo;
    private TextField txtIdPlatillo;
    private TextField txtPrecio,txtIdFolio,txtMesa;
    private Button btnGuardar;
    private V_FacturasDAO objPDAO = null;

    public  Ordenes (TableView<V_FacturasDAO> tbv){

        tbvOrden = tbv;

        CrearGUI();
        this.setScene(escena);
        this.setTitle("Modificaciones de Ordenes");
        this.show();
    }

    private void CrearGUI() {

        vBox = new VBox();
        lblTitulo = new Label("Orden");
        txtIdFolio= new TextField();
        txtIdFolio.setPromptText("ID Folio");
        txtMesa= new TextField();
        txtMesa.setPromptText("Mesa");
        txtIdPlatillo = new TextField();
        txtIdPlatillo.setPromptText("Ingresa ID Platillo");
        txtPrecio=new TextField();
        txtPrecio.setPromptText("Ingresa Precio");

        btnGuardar = new Button("Guardar");
        btnGuardar.setOnAction(event -> GuardarPlatillo());
        vBox.getChildren().addAll(lblTitulo,txtMesa,txtIdFolio,txtIdPlatillo,txtPrecio,btnGuardar);
        escena = new Scene(vBox,250,200);
    }

    private void GuardarPlatillo() {

        int pla = Integer.parseInt(txtIdPlatillo.getText());
        int mes = Integer.parseInt(txtMesa.getText());
        Double pre= Double.parseDouble(txtPrecio.getText());

        objPDAO.setPrecio(pre);
        objPDAO.setIdPlatillo(pla);
        objPDAO.setIdMesa(mes);
        objPDAO.ACTUALIZAR();

        tbvOrden.setItems(objPDAO.SELECCIONAR());
        tbvOrden.refresh();
        this.close();
    }

    public void setOrdenDAO(V_FacturasDAO objPDAO){

        this.objPDAO = objPDAO;
        txtIdFolio.setText(""+objPDAO.getIdFolio());
        txtMesa.setText(""+objPDAO.getIdMesa());
        txtIdPlatillo.setText(""+objPDAO.getIdPlatillo());
        txtPrecio.setText(""+objPDAO.getPrecio());
    }
}
