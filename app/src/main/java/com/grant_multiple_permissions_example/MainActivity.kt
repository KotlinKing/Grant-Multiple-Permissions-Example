package com.grant_multiple_permissions_example

import android.Manifest
import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import java.io.IOException
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {

    companion object {
        private const val PERMISSIONS_REQUEST = 10
    }

    //add your permissions here
    //don't forget to add the same permissions in Manifest file
    private var permissions = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.CAMERA
    )

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //checking permissions
        checkLocationPermission()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun checkLocationPermission() {
        //checking for multiple permissions array
        if (checkPermission(this, permissions)) {
        //  When all permissions are granted
            navigateToActivity()
        } else {
            requestPermissions(permissions, PERMISSIONS_REQUEST)
        }
    }

    fun checkPermission(context: Context, permissionArray: Array<String>): Boolean {
        var allPermissions = true
        for (i in permissionArray.indices) {
            if (checkCallingOrSelfPermission(permissionArray[i]) == PackageManager.PERMISSION_DENIED) {
                allPermissions = false
            }
        }
        return allPermissions
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSIONS_REQUEST) {
            var allPermissions = true
            for (i in permissions.indices) {
                if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                    allPermissions = false
                    val requestAgain = shouldShowRequestPermissionRationale(permissions[i])
                    if (requestAgain) {
                        Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Go to settings and enable the permission", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            if (allPermissions) {
 //               Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
                //  When all permissions are granted
                navigateToActivity()
            }
        }
    }

    private fun navigateToActivity() {
        Intent(this, PermissionsActivity::class.java).apply {
            startActivity(this)
        }
    }
}