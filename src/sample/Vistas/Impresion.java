package sample.Vistas;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Impresion {
    /*los atributos que debe tener la tabla son: No. Doc / Nombre Archivo / No Hojas / Status / Computadora Origen*/
    public int noDoc;
    public String name;
    public int noHojas;
    public String  status;
    public int origen;


    public int getNoDoc() {
        return noDoc;
    }

    public void setNoDoc(int noDoc) {
        this.noDoc = noDoc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNoHojas() {
        return noHojas;
    }

    public void setNoHojas(int noHojas) {
        this.noHojas = noHojas;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getOrigen() {
        return origen;
    }

    public void setOrigen(int origen) {
        this.origen = origen;
    }
}
