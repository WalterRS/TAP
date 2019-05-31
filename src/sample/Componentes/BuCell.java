package sample.Componentes;

import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import sample.Modelos.PlatilloDAO;
import sample.Vistas.Platillo;

public class BuCell extends TableCell<PlatilloDAO,String> {
    private Button celdaButton;
    private int opc;
    PlatilloDAO objp;

    public  BuCell(int opc){
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
        objp = BuCell.this.getTableView().getItems().get(BuCell.this.getIndex());
        new Platillo(BuCell.this.getTableView()).setPlatilloDAO(objp);

    }
    private void Eliminar() {
        objp = BuCell.this.getTableView().getItems().get(BuCell.this.getIndex());
        objp.ELIMINAR();

        BuCell.this.getTableView().setItems(objp.SELECCIONAR(0));
        BuCell.this.getTableView().refresh();
    }

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if (!empty){
            setGraphic(celdaButton);
        }
    }
}
