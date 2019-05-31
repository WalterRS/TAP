package sample.Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PlatilloDAO {
    private int idPlatillo;
    private String imagen;
    private String nombre;
    private double precio;
    private int idCategoria;

    public int getIdPlatillo() {
        return idPlatillo;
    }

    public void setIdPlatillo(int idPlatillo) {
        this.idPlatillo = idPlatillo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
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

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCetegoria) {
        this.idCategoria = idCetegoria;
    }


    public ObservableList<PlatilloDAO> SELECCIONAR(int id){
        ObservableList<PlatilloDAO> lista= FXCollections.observableArrayList();
        PlatilloDAO obPDAO= null;
        String consulta="";
        if (id==0){
             consulta="SELECT * FROM tblPlatillo";
        }else{
             consulta="SELECT * FROM tblPlatillo WHERE idcategoria="+id;
        }
        try{
            Statement stmt= MySQL_Restaurnt.con.createStatement();
            ResultSet resu=stmt.executeQuery(consulta);
            while (resu.next()){
                obPDAO= new PlatilloDAO();
                obPDAO.idPlatillo= resu.getInt("idPlatillo");
                obPDAO.imagen= resu.getString("imagen");
                obPDAO.nombre= resu.getString("nombre");
                obPDAO.precio= resu.getDouble("precio");
                obPDAO.idCategoria= resu.getInt("idCategoria");
                lista.add(obPDAO);
            }
        }catch (Exception e){}
        return lista;
    }
    public void INSERTAR(){
        String consulta = "INSERT INTO tblplatillo(imagen,nombre,precio,idCategoria) " +
                "VALUES('"+imagen+"','"+nombre+"',"+precio+","+idCategoria+")";
        try {
            Statement stmt = MySQL_Restaurnt.con.createStatement();
            stmt.executeUpdate(consulta);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void ACTUALIZAR(){
        String consulta = "UPDATE tblplatillo SET imagen = '"+imagen+"', nombre = '"+nombre+"'," +
                "precio = "+precio+",idCategoria="+idCategoria+" where" +
                " idPlatillo = "+idPlatillo;
        try {
            Statement stmt = MySQL_Restaurnt.con.createStatement();
            stmt.executeUpdate(consulta);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void ELIMINAR(){
        String consulta = "DELETE FROM tblplatillo WHERE idPlatillo = "+idPlatillo;
        try {
            Statement stmt = MySQL_Restaurnt.con.createStatement();
            stmt.executeUpdate(consulta);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
