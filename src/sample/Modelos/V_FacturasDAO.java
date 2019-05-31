package sample.Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class V_FacturasDAO {
    private int idFolio;
    private int idMesa;
    private int estado;
    private int idPlatillo;
    private String nombre;
    private double precio;
    private String descr;

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    private String fecha;

    public int getIdFolio() {
        return idFolio;
    }

    public void setIdFolio(int idFolio) {
        this.idFolio = idFolio;
    }

    public int getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(int idMesa) {
        this.idMesa = idMesa;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getIdPlatillo() {
        return idPlatillo;
    }

    public void setIdPlatillo(int idPlatillo) {
        this.idPlatillo = idPlatillo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }
    public ObservableList<V_FacturasDAO> SELECCIONAR(){
        ObservableList<V_FacturasDAO> lista= FXCollections.observableArrayList();
        V_FacturasDAO obPDAO= null;
        String consulta="SELECT * FROM V_Facturas ORDER BY idFolio DESC";
        try{
            Statement stmt= MySQL_Restaurnt.con.createStatement();
            ResultSet resu=stmt.executeQuery(consulta);
            while (resu.next()){
                obPDAO= new V_FacturasDAO();
                obPDAO.idFolio=resu.getInt("idFolio");
                obPDAO.idMesa=resu.getInt("idMesa");
                obPDAO.estado= resu.getInt("estado");
                obPDAO.idPlatillo=resu.getInt("idPlatillo");
                obPDAO.nombre= resu.getString("nombre");
                obPDAO.precio= resu.getDouble("precio");
                obPDAO.fecha= resu.getString("fecha");
                obPDAO.descr= resu.getString("descr");
                lista.add(obPDAO);
            }
        }catch (Exception e){}
        return lista;
    }
    public ObservableList<V_FacturasDAO> FACTURAS(int id){
        ObservableList<V_FacturasDAO> lista= FXCollections.observableArrayList();
        V_FacturasDAO obPDAO= null;
        String consulta="SELECT idPlatillo,nombre,precio FROM V_Facturas WHERE estado=1 and idMesa="+id;
        try{
            Statement stmt= MySQL_Restaurnt.con.createStatement();
            ResultSet resu=stmt.executeQuery(consulta);
            while (resu.next()){
                obPDAO= new V_FacturasDAO();
                obPDAO.idPlatillo=resu.getInt("idPlatillo");
                obPDAO.nombre= resu.getString("nombre");
                obPDAO.precio= resu.getDouble("precio");
                lista.add(obPDAO);
            }
        }catch (Exception e){}
        return lista;
    }
    public void ELIMINAR(){
        String consulta = "DELETE FROM tblorden WHERE idFolio = "+idFolio;
        try {
            Statement stmt= MySQL_Restaurnt.con.createStatement();
            stmt.executeUpdate(consulta);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void ACTUALIZAR(){
        String consulta = "UPDATE tblorden SET idPlatillo = "+idPlatillo +",idMesa="+idMesa+",precio="+precio+" where idFolio="+idFolio;
        try {
            Statement stmt= MySQL_Restaurnt.con.createStatement();
            stmt.executeUpdate(consulta);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
