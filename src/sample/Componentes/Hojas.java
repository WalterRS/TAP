package sample.Componentes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ProgressBar;
import sample.Vistas.Impresion;

public class Hojas extends Thread{
    private ProgressBar pgbCarril;
    private int hojas;
    ObservableList<Impresion> lista = FXCollections.observableArrayList();
    public Hojas(int hojs, ProgressBar pgbCarril, ObservableList<Impresion> list){
        this.hojas=hojs;
        this.pgbCarril=pgbCarril;
        this.lista=list;
    }

    @Override
    public void run() {
        super.run();
   int val=0;
        try {
            do {
                double avance=0;
                while (avance<1){
                    avance+=1.0/hojas;
                    pgbCarril.setProgress(avance);
                    sleep((long)(Math.random()*3000));
                }
                pgbCarril.setProgress(0);
                lista.remove(0);
                val=lista.size();
                if (val!=0){
                    Actualizar();
                    hojas=lista.get(0).noHojas;
                }
            }while (val!=0);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void Actualizar(){
        /*No. Doc / Nombre Archivo / No Hojas / Status / Computadora Origen*/
        /*Los estatus posibles son: Imprimiendo / En espera*/
        Impresion ob = new Impresion();
        ob.noDoc=lista.get(0).noDoc;
        ob.name=lista.get(0).name;
        ob.noHojas=lista.get(0).noHojas;
        ob.status=("Imprimiendo");
        ob.origen=lista.get(0).origen;
        lista.set(0,ob);
    }
}
