package co.edu.unimagdalena.apmoviles.estacionesdegasolina;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class ListadoGasolineras extends AppCompatActivity {

    ListView listado;

    GasolineraController gasolineraController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_gasolineras);
        listado = (ListView)findViewById(R.id.listaGasolineras);
        gasolineraController = new GasolineraController(this);
        Cursor c = gasolineraController.allGasolineras();
        GasolineraCursorAdapter gCursor = new GasolineraCursorAdapter(this, c, false);
        listado.setAdapter(gCursor);
        listado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }
}