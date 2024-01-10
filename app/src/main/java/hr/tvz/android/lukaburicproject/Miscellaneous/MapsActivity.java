package hr.tvz.android.lukaburicproject.Miscellaneous;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import hr.tvz.android.lukaburicproject.Main.Model.Hotel;
import hr.tvz.android.lukaburicproject.R;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Hotel hot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        hot = getIntent().getExtras().getParcelable("tag");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        float var1=Float.parseFloat(hot.getX());
        float var2=Float.parseFloat(hot.getY());
        // Add a marker in Sydney and move the camera
        LatLng mark = new LatLng(var1, var2);
        mMap.addMarker(new MarkerOptions().position(mark).title("Location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mark,15));
    }
}