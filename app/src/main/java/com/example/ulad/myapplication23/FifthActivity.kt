package com.example.ulad.myapplication23

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.location.LocationManager
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.widget.Toast
import android.os.Build
import android.support.v4.content.ContextCompat
import android.location.LocationListener
import com.example.ulad.myapplication23.model.Location
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.FileInputStream
import java.io.IOException
import java.io.InputStreamReader
import java.util.*


class FifthActivity : AppCompatActivity(), DetailFragment.DataPass {
    override fun onDataPass() {

    }

    private val MY_LOCATION_REQUEST_CODE = 0

    private var locationManager: LocationManager? = null

    private var locate: List<Location>? = null

    private var mapView: MapView? = null

    private var myLocation: android.location.Location? = null

    private val listener = object : LocationListener {
        override fun onLocationChanged(p0: android.location.Location?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {

        }

        override fun onProviderEnabled(provider: String) {

        }

        override fun onProviderDisabled(provider: String) {

        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_fifth)

        locate = ArrayList()

        val itemsListType = object : TypeToken<List<Location>>() {

        }.type

        var streamReader: InputStreamReader? = null

        var fileInputStream: FileInputStream? = null

        try {

            fileInputStream = openFileInput("shops.json")

            streamReader = InputStreamReader(fileInputStream)

            val gson = Gson()

            locate = gson.fromJson(streamReader, itemsListType)

        } catch (ex: IOException) {

            ex.printStackTrace()

        } finally {

            if (streamReader != null) {

                try {

                    streamReader!!.close()

                } catch (e: IOException) {

                    e.printStackTrace()

                }

            }

            if (fileInputStream != null) {

                try {

                    fileInputStream!!.close()

                } catch (e: IOException) {

                    e.printStackTrace()

                }

            }

        }

        mapView = findViewById<View>(R.id.mapView) as MapView

        mapView!!.onCreate(savedInstanceState)

        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

    }

    override fun onResume() {

        super.onResume()

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {

                    Toast.makeText(this, "Application need access to the location", Toast.LENGTH_LONG).show()

                }

                requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), MY_LOCATION_REQUEST_CODE)

            }

        } else {

            locationManager!!.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 10f, listener)

            resume()

        }

    }
    @SuppressLint("MissingPermission")

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {

        if (requestCode == MY_LOCATION_REQUEST_CODE) {

            if (requestCode == MY_LOCATION_REQUEST_CODE) {

                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                        return

                    }

                    locationManager!!.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 10f, listener)

                    resume()

                } else {

                    Toast.makeText(application, "Error", Toast.LENGTH_LONG).show()

                }

            }

        }

    }

    private fun resume() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return

        }

        myLocation = locationManager!!.getLastKnownLocation(LocationManager.GPS_PROVIDER)

        val distance = ArrayList<Float>()

        for (i in locate!!.indices) {

            val location = android.location.Location("endLocation")

            location.latitude = (locate!![i].latitude)

            location.latitude = locate!![i].latitude

            distance.add(myLocation!!.distanceTo(location) / 1000)

        }

        val numMinDistance = distance.indexOf(Collections.min(distance))

        mapView!!.getMapAsync { googleMap ->
            googleMap.clear()

            googleMap.addMarker(MarkerOptions().position(LatLng(myLocation!!.latitude,

                    myLocation!!.getLongitude())).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))

                    .title("My Position"))

            googleMap.addMarker(MarkerOptions().position(LatLng(locate!![numMinDistance].longitude,

                    locate!![numMinDistance].longitude)).title(locate!![numMinDistance].name))

            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(locate!![numMinDistance].longitude,

                    locate!![numMinDistance].longitude), 15F))

            mapView!!.onResume()
        }

    }
}