package co.edu.unimagdalena.apmoviles.estacionesdegasolina;

public class DefBD {
    public static final String nombreDb = "Mapas Gasolineras";
    public static final String tabla_gasolineras = "gasolineras";
    public static final String col_nombre = "nombre";
    public static final String col_empresa = "empresa";
    public static final String col_departamento = "departamento";
    public static final String col_municipio = "municipio";
    public static final String col_id= "id";
    public static final String col_longitud= "longitud";
    public static final String col_latitud = "latitud";


    public static final String create_tabla_gasolinera ="CREATE TABLE IF NOT EXISTS " + DefBD.tabla_gasolineras + " ( " +
            DefBD.col_id + " text primary key, " +
            DefBD.col_nombre + " text," +
            DefBD.col_empresa + " text, " +
            DefBD.col_departamento + " text, " +
            DefBD.col_municipio + " text, " +
            DefBD.col_longitud + " text, " +
            DefBD.col_latitud + " text " + ");";
}
