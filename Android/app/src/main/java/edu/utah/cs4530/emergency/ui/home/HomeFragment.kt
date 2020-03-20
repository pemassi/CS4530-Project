package edu.utah.cs4530.emergency.ui.home

import android.Manifest
import android.os.Bundle
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import edu.utah.cs4530.emergency.R
import edu.utah.cs4530.emergency.abstract.LiveModelFragment
import edu.utah.cs4530.emergency.extension.getLogger
import kotlinx.android.synthetic.main.fragment_home.view.*


class HomeFragment : LiveModelFragment<HomeViewModel>(HomeViewModel::class, R.layout.fragment_home), OnMapReadyCallback, PermissionListener {

    private val logger by getLogger()

    private var mMap: GoogleMap? = null
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private val locationRequest = LocationRequest()
        .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
        .setInterval(1000)
        .setFastestInterval(500)

    override fun onCreateViewModel(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?)
    {
        root.mapView.apply {
            onCreate(savedInstanceState)
            onResume()
            MapsInitializer.initialize(activity!!.applicationContext)
            getMapAsync(this@HomeFragment)
        }

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context!!)
    }

    override fun onResume() {
        super.onResume()

        startLocationUpdates()
    }

    override fun onPause() {
        super.onPause()

        stopLocationUpdates()
    }

    override fun onMapReady(map: GoogleMap) {
        map.apply {
            uiSettings.apply {
                isMyLocationButtonEnabled = false
                isScrollGesturesEnabled = false
                isZoomGesturesEnabled = false
                isCompassEnabled = false
            }
        }
        mMap = map

        TedPermission.with(applicationContext)
            .setPermissionListener(this)
            .setDeniedMessage("If you reject permission, we cannot track your current location.")
            .setPermissions(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
            .check()
    }

    private fun startLocationUpdates() {
       mMap?.let {
           if (TedPermission.isGranted(applicationContext, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION))
           {
               fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper())
               it.isMyLocationEnabled = true
           }
           else
           {
               val cameraUpdate = CameraUpdateFactory.newLatLngZoom(LatLng(37.56, 126.97), 15F)
               it.moveCamera(cameraUpdate)
               it.isMyLocationEnabled = false
           }
       }
    }

    private fun stopLocationUpdates() {
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    override fun onPermissionGranted() {
        logger.debug("Permission Granted")
        startLocationUpdates()
    }

    override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
        logger.debug("Permission Denied")
    }

    private val locationCallback: LocationCallback = object: LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult?) {
            locationResult ?: return

            val currentLatLng = LatLng(locationResult.locations.last().latitude, locationResult.locations.last().longitude)
            val cameraUpdate = CameraUpdateFactory.newLatLngZoom(currentLatLng, 15F)
            mMap?.moveCamera(cameraUpdate)
        }
    }

}