import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:pigeon_sample2/permissonHandler.dart';

class ImageSelectionDialog extends StatefulWidget {
  Function(String camerapath) oncameraImagePath;
  Function(String gallerypath) onGalleryPath;
  ImageSelectionDialog(
      {super.key,
      required this.oncameraImagePath,
      required this.onGalleryPath});

  @override
  _ImageSelectionDialogState createState() => _ImageSelectionDialogState();
}

class _ImageSelectionDialogState extends State<ImageSelectionDialog> {
  static const EventChannel eventChannel =
      EventChannel('com.example.pigeon_sample2/camera');

  static const EventChannel galleryEventChannel =
      EventChannel('com.example.pigeon_sample2/gallery_method');
  String? _selectedOption;

  @override
  Widget build(BuildContext context) {
    return AlertDialog(
      title: const Text("Select an Image"),
      content: Column(
        mainAxisSize: MainAxisSize.min,
        crossAxisAlignment: CrossAxisAlignment.stretch,
        children: [
          ElevatedButton(
            onPressed: () {
              // Set the selected option to "Camera"
              setState(() {
                _selectedOption = "Camera";
              });
              Navigator.pop(context);
              _openCamera();
            },
            child: const Text("Camera"),
          ),
          ElevatedButton(
            onPressed: () {
              // Set the selected option to "Gallery"
              setState(() {
                _selectedOption = "Gallery";
              });
              Navigator.pop(context);
              _openGallery();
            },
            child: const Text("Gallery"),
          ),
        ],
      ),
    );
  }

  void _openCamera() {
    // Add your camera logic here
    debugPrint("Opening Camera...");
    _startListeningForCameraImage();
  }

  void _openGallery() async {
    // Add your gallery logic here
    debugPrint("Opening Gallery...");
    bool isPermission = await PermissionHandler.requestStoragePermission();
    if (isPermission) {
      _startListeningForGallery();
    } else {
      debugPrint('dont have permission');
    }
  }

  void _startListeningForCameraImage() {
    eventChannel.receiveBroadcastStream().listen((event) {
      debugPrint("listning path: $event");

      widget.oncameraImagePath(event);
    }, onError: (error) {
      debugPrint("Error receiving camera image: $error");
    });
  }

  void _startListeningForGallery() {
    galleryEventChannel.receiveBroadcastStream().listen((event) {
      String data = event as String;
      widget.onGalleryPath(data);
    }, onError: (error) {
      debugPrint("Error receiving gallery image: $error");
    });
  }
}
