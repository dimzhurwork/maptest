package org.dimzhur.maptest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import java.util.Random;

public class MainActivity extends Activity {

    private GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        try {
            initilizeMap();

        } catch (Exception e) {
        }

    }

    private void initilizeMap() {
        if (googleMap == null) {
            googleMap = ((MapFragment) getFragmentManager().findFragmentById(
                    R.id.map)).getMap();
            for (int i = 0; i < 10; i++) {
                double latitude = 60 + new Random().nextDouble()*2-0.5;
                double longitude = 30 + new Random().nextDouble()-0.5;
                MarkerOptions marker = new MarkerOptions().position(new LatLng(latitude, longitude)).title("Position " + i);

                googleMap.addMarker(marker);
            }
            CameraPosition cameraPosition = new CameraPosition.Builder().target(
				new LatLng(60, 30)).zoom(5).build();

googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));



            if (googleMap == null) {
                Toast.makeText(getApplicationContext(),
                        "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        initilizeMap();
    }

}
