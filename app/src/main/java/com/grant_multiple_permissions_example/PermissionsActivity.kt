package com.grant_multiple_permissions_example

import android.Manifest
import android.content.pm.PackageManager.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat.*

class PermissionsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permissions)
    }

    override fun onResume() {
        super.onResume()
        checkPermissions()
    }

    private fun checkPermissions() {
        //camera permission
        if (checkSelfPermission(this, Manifest.permission.CAMERA).equals(PERMISSION_GRANTED)) {
            Toast.makeText(this, "Camera Permission Granted!", Toast.LENGTH_SHORT).show()
        }

        //location permission
        if (checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION).equals(PERMISSION_GRANTED)) {
            Toast.makeText(this, "Location Permission Granted!", Toast.LENGTH_SHORT).show()
        }

        //storage permission
        //checking only write because read and write permissions belongs to the same group
        if (checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE).equals(PERMISSION_GRANTED)) {
            Toast.makeText(this, "Storage Permission Granted!", Toast.LENGTH_SHORT).show()
        }
    }
}