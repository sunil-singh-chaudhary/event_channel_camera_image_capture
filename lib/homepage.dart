import 'dart:async'; // Import dart:async for the StreamSubscription
import 'dart:io';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

import 'ImageSelectionDialog.dart';

class HomePage extends StatefulWidget {
  HomePage({super.key});

  @override
  State<HomePage> createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  final ValueNotifier<String> _imagepathNotifier =
      ValueNotifier<String>("nopath");

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(),
      body: Center(
        child: Container(
          alignment: Alignment.center,
          child: Column(
            children: [
              Text(_imagepathNotifier.value),
              ValueListenableBuilder<String?>(
                  valueListenable: _imagepathNotifier,
                  builder: (context, imagePath, child) {
                    // Check if the imagePath is not null, and only display the image if it has a valid path.
                    if (imagePath != null) {
                      return Image.file(
                        File(imagePath),
                        width: 200,
                        height: 200,
                        fit: BoxFit.cover,
                      );
                    } else {
                      // Placeholder or alternative content when imagePath is null (e.g., no image selected).
                      return const Placeholder(); // You can replace this with any widget you like.
                    }
                  }),
              ElevatedButton(
                onPressed: () async {
                  showDialog(
                    context: context,
                    builder: (BuildContext context) {
                      return ImageSelectionDialog(
                        oncameraImagePath: (camerapath) {
                          _imagepathNotifier.value = camerapath;
                        },
                        onGalleryPath: (gallerypath) {
                          _imagepathNotifier.value = gallerypath;
                        },
                      );
                    },
                  );
                },
                child: const Text('Get Image'),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
