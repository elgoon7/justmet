package io.justmet.justmet.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import io.justmet.justmet.R;


public class MapFragment extends Fragment {

    private MapView mapView;
    private GoogleMap gMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Initialize a view to inflate
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        // Get the MapView from the XML layout and create it
        mapView = (MapView) view.findViewById(R.id.map_frag);
        mapView.onCreate(savedInstanceState);

        // Get to GoogleMap from the MapView and add marker at Empire State Bld
        gMap = mapView.getMap();
        gMap.addMarker(new MarkerOptions().position(new LatLng(40.7484,73.5897)));

        // Call MapsInitializer before calling CameraUpdateFactory
        try {
            MapsInitializer.initialize(this.getActivity());
        } catch (Exception e){
            e.printStackTrace();
        }

        // Update the location and zoom of the MapView
        CameraUpdate cam = CameraUpdateFactory.newLatLngZoom(new LatLng(41,74),10);
        gMap.animateCamera(cam);

        return view;
    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}
