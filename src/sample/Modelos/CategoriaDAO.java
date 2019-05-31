package sample.Modelos;

public class CategoriaDAO {
    public static int idCategoria;
    public static String nomCategoria;

    public static int getIdCategoria() {
        return idCategoria;
    }

    public static void setIdCategoria(int idCategoria) {
        CategoriaDAO.idCategoria = idCategoria;
    }

    public static String getNomCategoria() {
        return nomCategoria;
    }

    public static void setNomCategoria(String nomCategoria) {
        CategoriaDAO.nomCategoria = nomCategoria;
    }
}
