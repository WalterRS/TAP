package sample.Eventos;

import javafx.event.Event;
import javafx.event.EventHandler;
import sample.Modelos.MesaDAO;
import sample.Modelos.OrdenDAO;

public class EventosOrdenes implements EventHandler {
    MesaDAO obj;
    OrdenDAO ob;
    int idmesa,idplatillo;
    double precio;
    public EventosOrdenes(int idmes,int idpla,double pre) {
        this.idmesa=idmes;
        this.idplatillo=idpla;
        this.precio=pre;
    }
    @Override
    public void handle(Event event) {
        obj= new MesaDAO();
        ob= new OrdenDAO();
        ob.INSERTAR(idmesa,idplatillo,1,precio);
        obj.ACTUALIZAR(idmesa,2);
    }
}
