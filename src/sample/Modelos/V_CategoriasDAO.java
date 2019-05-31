package sample.Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class V_CategoriasDAO {
    int idFolio;
    int idPlatillo;
    Double precio;
    int idCategoria;
    String nombre;
    String fecha;

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getIdFolio() {
        return idFolio;
    }

    public void setIdFolio(int idFolio) {
        this.idFolio = idFolio;
    }

    public int getIdPlatillo() {
        return idPlatillo;
    }

    public void setIdPlatillo(int idPlatillo) {
        this.idPlatillo = idPlatillo;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public ObservableList<V_CategoriasDAO> SELECCIONAR(int cat,String fechaInicial){
        ObservableList<V_CategoriasDAO> lista2= FXCollections.observableArrayList();
        V_CategoriasDAO obPDAO= null;
        String consulta="SELECT * FROM V_Categorias WHERE idCategoria="+cat
                +" and fecha between '"+fechaInicial+"' and curdate()";
        try{
            Statement stmt= MySQL_Restaurnt.con.createStatement();
            ResultSet resu=stmt.executeQuery(consulta);
            while (resu.next()){
                obPDAO= new V_CategoriasDAO();
                obPDAO.idFolio=resu.getInt("idFolio");
                obPDAO.idPlatillo=resu.getInt("idPlatillo");
                obPDAO.nombre= resu.getString("nombre");
                obPDAO.precio= resu.getDouble("precio");
                obPDAO.idCategoria= resu.getInt("idCategoria");
                obPDAO.nombre= resu.getString("nombre");
                obPDAO.fecha= resu.getString("fecha");
                lista2.add(obPDAO);

            }
        }catch (Exception e){}
        return lista2;
    }
    public ObservableList<V_CategoriasDAO> SELECCIONARQUINCENA(String fechaInicial,String fechaFinal){
        ObservableList<V_CategoriasDAO> lista= FXCollections.observableArrayList();
        V_CategoriasDAO obPDAO= null;
        String consulta="SELECT * FROM V_Categorias WHERE fecha between '"+fechaInicial+"' and '"+fechaFinal+"'";

        try{
            Statement stmt= MySQL_Restaurnt.con.createStatement();
            ResultSet resu=stmt.executeQuery(consulta);
            while (resu.next()){
                obPDAO= new V_CategoriasDAO();
                obPDAO.idFolio=resu.getInt("idFolio");
                obPDAO.idPlatillo=resu.getInt("idPlatillo");
                obPDAO.nombre= resu.getString("nombre");
                obPDAO.precio= resu.getDouble("precio");
                obPDAO.idCategoria= resu.getInt("idCategoria");
                obPDAO.nombre= resu.getString("nombre");
                obPDAO.fecha= resu.getString("fecha");
                lista.add(obPDAO);
            }
        }catch (Exception e){}
        return lista;
    }
}
