package sample.Componentes;

import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import sample.Modelos.PeliculaDAO;
import sample.Vistas.Peliculas;

public class ButtonCell extends TableCell<PeliculaDAO,String> {
    private Button celdaButton;
    private int opc;
    PeliculaDAO objp;

    public  ButtonCell(int opc){
        this.opc = opc;

        if( this.opc == 1){
            celdaButton=new Button("Editar");
            celdaButton.setOnAction(event -> Editar());
        }
        else{
            celdaButton= new Button("Eliminar");
            celdaButton.setOnAction(event -> Eliminar());
        }
    }

    private void Editar() {
        // Aqui va el codigo para mostrar el formulario de edicion

        objp = ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
        //System.out.println(objP.getDescPelicula());
        new Peliculas(ButtonCell.this.getTableView()).setPeliculaDAO(objp);

    }
    private void Eliminar() {
        objp = ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
        objp.ELIMINAR();
        ButtonCell.this.getTableView().setItems(objp.SELECCIONAR());
        ButtonCell.this.getTableView().refresh();
    }

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if (!empty){
            setGraphic(celdaButton);
        }
    }
}
