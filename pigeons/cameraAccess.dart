import 'package:pigeon/pigeon.dart';

class CameraPath {
  String? imagePath;
}

@HostApi()
abstract class CameraPathApi {
  // Method to initiate the camera image capture
  CameraPath takeCameraImage();
}

// Define the callback
@FlutterApi()
abstract class ImagePathCallback {
  void onImagePathUpdated(String? imagePath);
}
