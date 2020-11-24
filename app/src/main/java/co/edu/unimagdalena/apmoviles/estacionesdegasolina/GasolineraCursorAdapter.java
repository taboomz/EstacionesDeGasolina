package co.edu.unimagdalena.apmoviles.estacionesdegasolina;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class GasolineraCursorAdapter  extends CursorAdapter {
    public GasolineraCursorAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.fila_gasolinera, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView gas = view.findViewById(R.id.txtGasolinera);
        TextView emp = view.findViewById(R.id.txtEmpresa);
        TextView dep = view.findViewById(R.id.txtDepartamento);
        TextView mun = view.findViewById(R.id.txtMunicipio);
        TextView ubi = view.findViewById(R.id.txtUbicacion);

        String gasolinera = cursor.getString(1);
        String empresa = cursor.getString(2);
        String departamento = cursor.getString(3);
        String municipio = cursor.getString(4);
        String longitud = cursor.getString(5);
        String latitud = cursor.getString(6);

        gas.setText("Gasolinera: " + gasolinera);
        emp.setText("Empresa: " + empresa);
        dep.setText("Depsrtamento del " + departamento);
        mun.setText("Municiio del " + municipio);
        ubi.setText("Ubicacion: " + "(" + longitud + ", "+ latitud + ")");

    }
}