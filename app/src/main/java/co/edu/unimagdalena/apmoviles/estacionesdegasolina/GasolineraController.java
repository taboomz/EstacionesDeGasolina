package co.edu.unimagdalena.apmoviles.estacionesdegasolina;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class GasolineraController {
    private BaseDatos db;
    private Context c;

    public GasolineraController(Context c) {
        this.db = new BaseDatos(c,1);
        this.c = c;
    }

    public void agregarGasolinera(Gasolinera g){
        try {
            SQLiteDatabase sql = db.getWritableDatabase();
            ContentValues valores = new ContentValues();
            valores.put(DefBD.col_id, g.getId());
            valores.put(DefBD.col_nombre, g.getNombre());
            valores.put(DefBD.col_empresa, g.getEmpresa());
            valores.put(DefBD.col_departamento, g.getDepartamento());
            valores.put(DefBD.col_municipio, g.getMunicipio());
            valores.put(DefBD.col_longitud, g.getLongitud());
            valores.put(DefBD.col_latitud, g.getLatitud());

            long id = sql.insert(DefBD.tabla_gasolineras, null, valores);
            Toast.makeText(c, "¡Estación registrada!", Toast.LENGTH_LONG).show();
            sql.close();
        }
        catch(Exception ex){
            Toast.makeText(c, "Error al agregar estación." + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public boolean buscarGasolinera(String id){
        String args[] = new String[] {id};
        String col[] = new String[] {DefBD.col_id,DefBD.col_nombre};
        SQLiteDatabase sql = db.getReadableDatabase();
        Cursor c = sql.query(DefBD.tabla_gasolineras,null,"id=?",args,null,null,null);
        if (c.getCount() > 0){
            db.close();
            return true;
        }
        else{
            db.close();
            return false;
        }
    }
    public Cursor allGasolineras(){
        try{
            SQLiteDatabase sql = db.getReadableDatabase();
            Cursor cur = sql.rawQuery("select id as _id , nombre, empresa, departamento, municipio, longitud, latitud from gasolineras", null);
            return cur;
        }
        catch (Exception ex){
            Toast.makeText(c, "Error al consultar estación. " + ex.getMessage(), Toast.LENGTH_LONG).show();
            return null;
        }
    }
}
