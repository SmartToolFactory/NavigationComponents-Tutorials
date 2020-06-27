package com.smarttoolfactory.tutorial5_2navigationui_bottomnavigation_advanced.blankfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.smarttoolfactory.tutorial5_2navigationui_bottomnavigation_advanced.R


class MapFragment : Fragment(), OnMapReadyCallback {

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        initMap(googleMap)
    }

    private lateinit var map: GoogleMap

    /**
     * Marker to display touched position
     */
    private lateinit var marker: Marker


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        Toast.makeText(activity, "MapFragment onCreateView() this: $this", Toast.LENGTH_SHORT).show()


        // Each fragment can have it's seprate toolbar menu
        setHasOptionsMenu(true)

        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Toast.makeText(activity, "MapFragment onViewCreated() this: $this", Toast.LENGTH_SHORT).show()


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        // Set up Map
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?

        mapFragment?.getMapAsync(this)
    }

    private fun initMap(map: GoogleMap) {

        // Set camera position
        val istanbul = LatLng(41.01384, 28.94966)
        map.moveCamera(CameraUpdateFactory.newLatLng(istanbul))
        val zoom = CameraUpdateFactory.zoomTo(10f)
        map.animateCamera(zoom)

        // Set map rotation button
        map.uiSettings.isRotateGesturesEnabled = false
        // Set my location button
        map.uiSettings.isMyLocationButtonEnabled = true
        // Set navigation menu
        map.uiSettings.isZoomControlsEnabled = true
        map.uiSettings.isMapToolbarEnabled = true

        // TODO Not for production
//        addMarker(map, istanbul)


        // Add marker whenever user
        map.setOnMapClickListener { latLng ->
            addMarker(map, latLng)
        }
    }

    private fun addMarker(map: GoogleMap, latLng: LatLng) {

        map.clear()

        val options = MarkerOptions().title("Selected").position(latLng)
            .draggable(false)

        marker = map.addMarker(options)
    }

}