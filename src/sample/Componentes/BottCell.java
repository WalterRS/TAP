package sample.Componentes;

import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import sample.Modelos.V_FacturasDAO;
import sample.Vistas.Ordenes;

import javax.swing.*;

public class BottCell extends TableCell<V_FacturasDAO,String> {
    private Button celdaButton;
    private int opc;
    V_FacturasDAO objp;

    public  BottCell(int opc){
        this.opc = opc;


        if( this.opc == 1){
            celdaButton=new Button("Editar");
            celdaButton.setStyle("-fx-font: 12 arial; -fx-base: #2ca4ba");
                celdaButton.setOnAction(event -> Editar());
        }
        else{
            celdaButton= new Button("Eliminar");
            celdaButton.setStyle("-fx-font: 12 arial; -fx-base: #ba4539");
            celdaButton.setOnAction(event -> Eliminar());
        }
    }

    private void Editar() {
        // Aqui va el codigo para mostrar el formulario de edicion

        objp = BottCell.this.getTableView().getItems().get(BottCell.this.getIndex());
        if(!objp.getDescr().equals("TERMINADA")){
            new Ordenes(BottCell.this.getTableView()).setOrdenDAO(objp);
            BottCell.this.getTableView().setItems(objp.SELECCIONAR());
            BottCell.this.getTableView().refresh();
        }else{
            JOptionPane.showMessageDialog(null,"Platillo no Disponible");
        }


    }
    private void Eliminar() {

        objp = BottCell.this.getTableView().getItems().get(BottCell.this.getIndex());
        if(!objp.getDescr().equals("TERMINADA")){
            objp.ELIMINAR();
            BottCell.this.getTableView().setItems(objp.SELECCIONAR());
            BottCell.this.getTableView().refresh();
        }else{
            JOptionPane.showMessageDialog(null,"Platillo no Disponible");
        }


    }

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if (!empty){
            setGraphic(celdaButton);
        }
    }
}
