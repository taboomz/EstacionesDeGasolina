package co.edu.unimagdalena.apmoviles.estacionesdegasolina;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText gasolinera, empresa, departamento, municipio, longitud, latitud;
    Button guardar, ver, listar;
    Gasolinera g;
    GasolineraController gasolineraController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gasolinera = findViewById(R.id.edtGasolinera);
        empresa = findViewById(R.id.edtEmpresa);
        departamento= findViewById(R.id.edtDepartamento);
        municipio = findViewById(R.id.edtMunicipio);
        latitud = findViewById(R.id.edtLatitud);
        longitud = findViewById(R.id.edtLongitud);
        guardar = findViewById(R.id.btnAgregar);
        ver = findViewById(R.id.btnMapa);
        listar = findViewById(R.id.btnLista);

        gasolineraController = new GasolineraController(this);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!camposVacios()){
                    g = new Gasolinera();
                    g.setNombre(gasolinera.getText().toString());
                    g.setDepartamento(departamento.getText().toString());
                    g.setEmpresa(empresa.getText().toString());
                    g.setMunicipio(municipio.getText().toString());
                    g.setLatitud(Double.parseDouble(latitud.getText().toString()));
                    g.setLongitud(Double.parseDouble(longitud.getText().toString()));
                    setBlank();
                    if(gasolineraController.buscarGasolinera(g.getId())){
                        Toast.makeText(MainActivity.this, "Estación de gasolina ya registrada.", Toast.LENGTH_SHORT).show();
                    }else{
                        gasolineraController.agregarGasolinera(g);
                    }
                }else{
                    Toast.makeText(MainActivity.this, "Por favor rellenar los campos.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Mapa.class);
                startActivity(intent);
            }
        });

        listar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ListadoGasolineras.class);
                startActivity(intent);
            }
        });
    }

    private void setBlank() {
        gasolinera.setText("");
        departamento.setText("");
        municipio.setText("");
        empresa.setText("");
        longitud.setText("");
        latitud.setText("");
    }

    private boolean camposVacios() {
        if (gasolinera.getText().toString().isEmpty() || departamento.getText().toString().isEmpty() || municipio.getText().toString().isEmpty()
        || longitud.getText().toString().isEmpty() || latitud.getText().toString().isEmpty()){
            return true;
        }
        return false;
    }
}