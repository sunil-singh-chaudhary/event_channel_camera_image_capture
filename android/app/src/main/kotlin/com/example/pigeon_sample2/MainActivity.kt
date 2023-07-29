package com.example.pigeon_sample2

import android.content.Intent
import android.graphics.Bitmap
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.annotation.NonNull
import androidx.lifecycle.MutableLiveData
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.EventChannel

class MainActivity : FlutterActivity() {
    private val imagePathLiveData = MutableLiveData<String?>()

    companion object {
        private const val CAMERA_PERMISSION_REQUEST_CODE = 101
        private const val EVENT_CHANNEL = "com.example.pigeon_sample2/camera"
    }

    private var imagePath: String = "nopath"

    override fun configureFlutterEngine(@NonNull flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        EventChannel(flutterEngine.dartExecutor.binaryMessenger, EVENT_CHANNEL).setStreamHandler(
            object : EventChannel.StreamHandler {
                override fun onListen(arguments: Any?, events: EventChannel.EventSink?) {
                    // When Flutter starts listening, you can trigger the camera capture here.
                    takeImageUsingCamera()
                    // Pass the EventSink to the takeImageUsingCamera method,
                    // so that it can send the image path to Flutter when the image is captured.
                    imagePathLiveData.observe(this@MainActivity) { imagePath ->
                        events?.success(imagePath)
                    }
                }

                override fun onCancel(arguments: Any?) {
                    // If needed, you can handle cleanup when Flutter cancels the event channel.
                    imagePathLiveData.removeObservers(this@MainActivity)
                }
            }
        )
    }

    private fun takeImageUsingCamera() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(packageManager) != null) {
            startActivityForResult(takePictureIntent, CAMERA_PERMISSION_REQUEST_CODE)
        } else {
            // Handle the case when the camera app is not available
            Toast.makeText(this@MainActivity, "Camera app not found", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE && resultCode == ComponentActivity.RESULT_OK) {
            val imageBitmap: Bitmap? = data?.extras?.get("data") as? Bitmap
            if (imageBitmap != null) {

                val savedImagePath = StorageUtils.saveToInternalStorage(imageBitmap,MainActivity@this)
                Log.e("PATH IS set viemodel : ", savedImagePath.toString())
                imagePathLiveData.value = savedImagePath
            }
        }
    }

}
