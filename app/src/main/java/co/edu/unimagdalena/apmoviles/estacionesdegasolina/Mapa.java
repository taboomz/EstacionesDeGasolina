package co.edu.unimagdalena.apmoviles.estacionesdegasolina;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class Mapa extends FragmentActivity implements OnMapReadyCallback {

    double latitudDispositivo, longitudDispositivo;
    GasolineraController gasolineraController;
    ArrayList<Double> longitudes = new ArrayList<>();
    ArrayList<Double> latitudes = new ArrayList<>();
    ArrayList<String> nombres = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
        gasolineraController = new GasolineraController(this);
        localizacion();

        Cursor c = gasolineraController.allGasolineras();
        obtenerCordenadasGasolineras(c);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapa);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(final GoogleMap googleMap) {

                LatLng ubicacionDispositivo = new LatLng(latitudDispositivo, longitudDispositivo);
                googleMap.addMarker(new MarkerOptions().position(ubicacionDispositivo).title("Estás aquí"));
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ubicacionDispositivo, 14));
                googleMap.getUiSettings().setMyLocationButtonEnabled(true);
                agregarMarcadores(googleMap);
            }
        });

    }

    private void localizacion(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1000);
        }

        LocationManager ubicacion = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location loc = ubicacion.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        if(ubicacion != null) {
            this.latitudDispositivo = loc.getLatitude();
            this.longitudDispositivo = loc.getLongitude();
            Log.d("Latitud: ", String.valueOf(latitudDispositivo));
            Log.d("Longitud: ", String.valueOf(longitudDispositivo));
        }
    }



    private void agregarMarcadores(GoogleMap mMap) {
        Toast.makeText(this, "Agregando Marcadores",Toast.LENGTH_LONG).show();
        for (int i = 0; i < nombres.size(); i++){
            LatLng latLng = new LatLng(latitudes.get(i),longitudes.get(i));
            mMap.addMarker(new MarkerOptions().position(latLng).title(nombres.get(i)));
            //mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        }
    }

    private void obtenerCordenadasGasolineras(Cursor c) {
        Toast.makeText(this, "Obteniendo informacion de las gasolineras",Toast.LENGTH_LONG).show();
        c.moveToFirst();
        while (!c.isAfterLast()){
            longitudes.add(Double.parseDouble(c.getString(5)));
            latitudes.add(Double.parseDouble(c.getString(6)));
            nombres.add(c.getString(1));
            c.moveToNext();
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }


    /*private void locationStart() {
        LocationManager mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Localizacion Local = new Localizacion();

        miLongitud = Local.getLongitud();
        miLatitud = Local.getLatitud();
        final boolean gpsEnabled = mlocManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!gpsEnabled) {
            Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(settingsIntent);
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
            return;
        }
        mlocManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, (android.location.LocationListener) Local);
        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, (android.location.LocationListener) Local);

    }
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1000) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                locationStart();
                return;
            }
        }
    }*/


}