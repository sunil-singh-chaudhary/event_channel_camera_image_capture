package com.example.pigeon_sample2

import android.content.Context
import android.content.ContextWrapper
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class StorageUtils {
    companion object  {
         fun saveToInternalStorage(bitmapImage: Bitmap, context: Context): String? {
            val cw = ContextWrapper(context)
            val directory = cw.getDir("imageDir", Context.MODE_PRIVATE)
            val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
            val fileName = "cameraImage_$timestamp.jpg"

            // Create imageDir
            val mypath = File(directory, fileName)
            var fos: FileOutputStream? = null
            

            try {
                fos = FileOutputStream(mypath)
                // Use the compress method on the BitMap object to write image to the OutputStream
                bitmapImage.compress(Bitmap.CompressFormat.WEBP, 100, fos)
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                try {
                    fos?.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
                return mypath.absolutePath
        }

         fun getRealPathFromURI(uri: Uri, context: Context): String {
            val projection = arrayOf(MediaStore.Images.Media.DATA)
            val cursor = context.contentResolver.query(uri, projection, null, null, null)
            val columnIndex = cursor?.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            cursor?.moveToFirst()
            val path = cursor?.getString(columnIndex ?: 0)
            cursor?.close()
            return path ?: ""
        }

    }
//    fun showImage(imagePath: String, onImageLoaded: (ImageBitmap) -> Unit) {
//        // Decode the image file and create an ImageBitmap object
//        val bitmap: Bitmap? = BitmapFactory.decodeFile(imagePath)
//        val imageBitmap: ImageBitmap? = bitmap?.asImageBitmap()
//
//        // Notify the caller about the loaded image
//        imageBitmap?.let { onImageLoaded(it) }
//    }
}