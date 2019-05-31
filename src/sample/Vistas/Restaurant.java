package sample.Vistas;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.Modelos.*;
import javax.swing.*;
import java.io.IOException;

public class Restaurant extends Stage {
    private TableView<V_FacturasDAO> tbvOrden;
    private Scene escena;
    private BorderPane panel;
    private Button login;
    private Button TomarOrden,addOrden;
    private Button factura;
    private Label noMesa,lugares;
    private VBox vbox;
    private HBox hbox;
    private TextField usuario;
    private PasswordField password;
    private  ComboBox mesas,mesas2;
    private ObservableList<MesaDAO> lista= FXCollections.observableArrayList();
    private ObservableList<MesaDAO> lista2= FXCollections .observableArrayList();
    private ObservableList<MesaDAO> lugar= FXCollections .observableArrayList();
    private ObservableList<UserDAO> listUser= FXCollections.observableArrayList();
    private ObservableList<V_FacturasDAO> listFac= FXCollections.observableArrayList();
    private ObservableList<FacturaDAO> Fact= FXCollections.observableArrayList();
    private OrdenDAO ob;
    private MesaDAO obj;
    private String name;
    public Restaurant(){
        CearVista();
        escena = new Scene(panel,800,500);
        setTitle("Restaurant");
        setScene(escena);
        setMaximized(true);
        show();
    }
    private void CearVista() {
        panel = new BorderPane();
        vbox = new VBox();
        hbox = new HBox();

        login = new Button("Login Administrador");
        login.setPrefSize(250,60);
        login.setStyle("-fx-font: 20 arial; -fx-base: #34aaba");
        login.setOnAction(event -> login());

        factura=new Button("Facturar");
        factura.setPrefSize(150,60);
        factura.setStyle("-fx-font: 20 arial; -fx-base: #ba4539");
        factura.setOnAction(event -> {
            try {
                factura();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        lugares= new Label("");

        noMesa= new Label("No Mesa: ");
        mesas= new ComboBox();
        mesas2= new ComboBox();

        lista = new MesaDAO().SELECCION_MESA(1);
        lista2= new MesaDAO().SELECCION_MESA(2);

        for (int i = 0; i < lista.size(); i++) {
            mesas.getItems().add(lista.get(i).getIdMesa());
        }
        for (int i = 0; i < lista2.size(); i++) {
            mesas2.getItems().add(lista2.get(i).getIdMesa());
        }

        mesas.setOnAction(event -> {
            lugar = new MesaDAO().BUSCAR((Integer) mesas.getValue());
            lugares.setText("Lugares: "+lugar.get(0).getLugares());});

        TomarOrden = new Button("Tomar Orden");
        TomarOrden.setPrefSize(150,60);
        TomarOrden.setStyle("-fx-font: 20 arial; -fx-base: #67ba37");
        TomarOrden.setOnAction(event -> TomarOrden());

        addOrden= new Button("Añadir Orden");
        addOrden.setPrefSize(150,60);
        addOrden.setStyle("-fx-font: 20 arial; -fx-base: #67ba37");
        addOrden.setOnAction(event -> addOrden());

        panel.setTop(login);
        tblOrdenes();
        hbox.getChildren().addAll(noMesa,mesas,lugares,TomarOrden);
        vbox.getChildren().addAll(hbox);
        hbox= new HBox();
        hbox.getChildren().addAll(mesas2,addOrden);
        hbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(hbox,factura);
        hbox.setSpacing(20);
        vbox.setSpacing(30);
        vbox.setAlignment(Pos.BOTTOM_CENTER);
        panel.setCenter(vbox);

    }
    private void addOrden() {
        String mesa=""+mesas2.getValue();
        if(!mesa.equals("null")){
            MenuRestaurante ob = new MenuRestaurante((Integer) mesas2.getValue());
            tbvOrden.refresh();
            close();
        }
        else{
            JOptionPane.showMessageDialog(null,"Selecciona una mesa");
        }
    }
    private void factura() throws IOException {
        String mesa=""+mesas2.getValue();
        String Cabezera,Factura="",mensaje;
        int tamaño,log;
        double total=0;
        if(!mesa.equals("null")){
            obj= new MesaDAO();
            ob= new OrdenDAO();
            int idmesa=(Integer) mesas2.getValue();
            listFac= new V_FacturasDAO().FACTURAS(idmesa);
            tamaño=listFac.size();
            for (int i = 0; i < tamaño; i++) {
                total=total+listFac.get(i).getPrecio();
                Factura=Factura+"\n   "+listFac.get(i).getIdPlatillo()+" - "+listFac.get(i).getNombre()
                +"     $"+listFac.get(i).getPrecio();
            }
            Factura=Factura+"\n-------------------------Total=$"+total+
                    "\n----------------------------------------------"+
                    "\n               GRACIAS POR SU VISITA";
            FacturaDAO obf= new FacturaDAO();
            obf.INSERTAR(total);
            Fact= new FacturaDAO().SELECCION_FAC();
            log=Fact.size()-1;
            Cabezera="    FOLIO: "+Fact.get(log).getFolio()+
                    "\n          "+Fact.get(log).getFecha()+"             "+Fact.get(log).getHora()
                    +"\n----------------------------------------------";
            mensaje=Cabezera+"\n"+Factura;
            name="Factura"+Fact.get(log).getFolio()+".pdf";

           CrearPDF pdf= new CrearPDF();
           pdf.crearPDF(name,mensaje);
            ob.ACTUALIZAR(idmesa);
            obj.ACTUALIZAR(idmesa,1);
            close();
            Restaurant obj2= new Restaurant();
            System.out.println(mensaje);
        }
        else{
            JOptionPane.showMessageDialog(null,"Selecciona una mesa para Facturar");
        }

    }
    private void tblOrdenes(){

        TableColumn<V_FacturasDAO, Integer> tbcId = new TableColumn<>("ID Orden");
        tbcId.setCellValueFactory(new PropertyValueFactory<>("idFolio"));

        TableColumn<V_FacturasDAO, Integer> tbcidM = new TableColumn<>("Mesa");
        tbcidM.setCellValueFactory(new PropertyValueFactory<>("idMesa"));

        TableColumn<V_FacturasDAO, Integer> tbcIdPla = new TableColumn<>("ID Platillo");
        tbcIdPla.setCellValueFactory(new PropertyValueFactory<>("idPlatillo"));

        TableColumn<V_FacturasDAO, String> tbcNombre = new TableColumn<>("Nombre");
        tbcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        TableColumn<V_FacturasDAO, Double> tbcPre = new TableColumn<>("Precio");
        tbcPre.setCellValueFactory(new PropertyValueFactory<>("precio"));

        TableColumn<V_FacturasDAO, String> tbcfecha=new TableColumn<>("Fecha");
        tbcfecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));

        TableColumn<V_FacturasDAO, String> tbcEstado = new TableColumn<>("ID Estado");
        tbcEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));

        TableColumn<V_FacturasDAO, String> tbcdescr = new TableColumn<>("Estado");
        tbcdescr.setCellValueFactory(new PropertyValueFactory<>("descr"));

        tbvOrden = new TableView<>();
        tbvOrden.getColumns().addAll(tbcId,tbcidM,tbcIdPla,tbcNombre,tbcPre,tbcfecha,tbcEstado,tbcdescr);
        tbvOrden.setItems(new V_FacturasDAO().SELECCIONAR());
        vbox.getChildren().addAll(tbvOrden);
    }
    private void login() {
        Button loggin;
        Button cancelar;
        Label user;
        Label pass;
        Stage stage = new Stage();
        BorderPane pane = new BorderPane();
        Scene scene = new Scene(pane,300,180);
        HBox box;
        VBox vox = new VBox();

        loggin = new Button("Login");
        loggin.setStyle("-fx-font: 20 arial; -fx-base: #34aaba");
        loggin.setOnAction(event -> {
            if (!usuario.getText().equals("") && !password.getText().equals("")){
                int valor;
                valor=Integer.parseInt(usuario.getText());

                listUser= new UserDAO().SELECCION_USER(valor);
                if(listUser.size()!=0){
                    if (password.getText().equals(listUser.get(0).getPassword())){
                        JOptionPane.showMessageDialog(null,"Welcome "+listUser.get(0).getName());
                        Administrador ob = new Administrador();
                        stage.close();
                    }else{
                        JOptionPane.showMessageDialog(null,"Password Incorrecta");
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"Usuario no existe");
                }
            }else {
                JOptionPane.showMessageDialog(null,"Faltan Datos");
            }
        });
        cancelar = new Button("Salir");
        cancelar.setStyle("-fx-font: 20 arial; -fx-base: #ba4057");
        cancelar.setOnAction(event -> stage.close());


        user=new Label("Usuario:    ");
        pass=new Label("Contraseña: ");
        usuario=new TextField();
        usuario.setPromptText("Usuario");
        password=new PasswordField();
        password.setPromptText("Contraseña");

        box=new HBox();
        box.getChildren().addAll(user,usuario);
        box.setAlignment(Pos.CENTER);
        vox.getChildren().addAll(box);
        box=new HBox();
        box.getChildren().addAll(pass,password);
        box.setAlignment(Pos.CENTER);
        vox.getChildren().addAll(box);
        box=new HBox();
        box.getChildren().addAll(cancelar,loggin);
        box.setSpacing(20);
        box.setAlignment(Pos.CENTER);
        vox.getChildren().addAll(box);
        vox.setSpacing(10);
        pane.setCenter(vox);

        stage.setScene(scene);
        stage.setTitle("Login");
        stage.show();

    }
    private void TomarOrden() {
        String mesa=""+mesas.getValue();
        if(!mesa.equals("null")){
            MenuRestaurante ob = new MenuRestaurante((Integer)mesas.getValue());
            tbvOrden.refresh();
            close();
        }
        else{
            JOptionPane.showMessageDialog(null,"Selecciona una Mesa");
        }


    }
}

