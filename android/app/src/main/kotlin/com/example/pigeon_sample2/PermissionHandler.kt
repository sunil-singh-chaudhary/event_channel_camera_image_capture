package com.example.pigeon_sample2

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class PermissionHandler {


     companion object{
         public const val PERMISSION_REQUEST_CODE = 1001

         fun arePermissionsGranted(context : Context): Boolean {
             val cameraPermission = ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
             val writeStoragePermission = ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
             val readStoragePermission = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE)

             return cameraPermission == PackageManager.PERMISSION_GRANTED &&
                     writeStoragePermission == PackageManager.PERMISSION_GRANTED &&
                     readStoragePermission == PackageManager.PERMISSION_GRANTED
         }

         public fun requestPermissions(context: Context) {
             ActivityCompat.requestPermissions(
                 context as Activity,
                 arrayOf(
                     Manifest.permission.CAMERA,
                     Manifest.permission.WRITE_EXTERNAL_STORAGE,
                     Manifest.permission.READ_EXTERNAL_STORAGE
                 ),
                 PermissionHandler.PERMISSION_REQUEST_CODE
             )
         }



     }

}