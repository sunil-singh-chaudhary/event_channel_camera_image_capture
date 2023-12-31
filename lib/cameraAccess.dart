// Autogenerated from Pigeon (v10.1.4), do not edit directly.
// See also: https://pub.dev/packages/pigeon
// ignore_for_file: public_member_api_docs, non_constant_identifier_names, avoid_as, unused_import, unnecessary_parenthesis, prefer_null_aware_operators, omit_local_variable_types, unused_shown_name, unnecessary_import

import 'dart:async';
import 'dart:typed_data' show Float64List, Int32List, Int64List, Uint8List;

import 'package:flutter/foundation.dart' show ReadBuffer, WriteBuffer;
import 'package:flutter/services.dart';

class CameraPath {
  CameraPath({
    this.imagePath,
  });

  String? imagePath;

  Object encode() {
    return <Object?>[
      imagePath,
    ];
  }

  static CameraPath decode(Object result) {
    result as List<Object?>;
    return CameraPath(
      imagePath: result[0] as String?,
    );
  }
}

class _CameraPathApiCodec extends StandardMessageCodec {
  const _CameraPathApiCodec();
  @override
  void writeValue(WriteBuffer buffer, Object? value) {
    if (value is CameraPath) {
      buffer.putUint8(128);
      writeValue(buffer, value.encode());
    } else {
      super.writeValue(buffer, value);
    }
  }

  @override
  Object? readValueOfType(int type, ReadBuffer buffer) {
    switch (type) {
      case 128: 
        return CameraPath.decode(readValue(buffer)!);
      default:
        return super.readValueOfType(type, buffer);
    }
  }
}

class CameraPathApi {
  /// Constructor for [CameraPathApi].  The [binaryMessenger] named argument is
  /// available for dependency injection.  If it is left null, the default
  /// BinaryMessenger will be used which routes to the host platform.
  CameraPathApi({BinaryMessenger? binaryMessenger})
      : _binaryMessenger = binaryMessenger;
  final BinaryMessenger? _binaryMessenger;

  static const MessageCodec<Object?> codec = _CameraPathApiCodec();

  Future<CameraPath> takeCameraImage() async {
    final BasicMessageChannel<Object?> channel = BasicMessageChannel<Object?>(
        'dev.flutter.pigeon.pigeon_sample2.CameraPathApi.takeCameraImage', codec,
        binaryMessenger: _binaryMessenger);
    final List<Object?>? replyList =
        await channel.send(null) as List<Object?>?;
    if (replyList == null) {
      throw PlatformException(
        code: 'channel-error',
        message: 'Unable to establish connection on channel.',
      );
    } else if (replyList.length > 1) {
      throw PlatformException(
        code: replyList[0]! as String,
        message: replyList[1] as String?,
        details: replyList[2],
      );
    } else if (replyList[0] == null) {
      throw PlatformException(
        code: 'null-error',
        message: 'Host platform returned null value for non-null return value.',
      );
    } else {
      return (replyList[0] as CameraPath?)!;
    }
  }
}

abstract class ImagePathCallback {
  static const MessageCodec<Object?> codec = StandardMessageCodec();

  void onImagePathUpdated(String? imagePath);

  static void setup(ImagePathCallback? api, {BinaryMessenger? binaryMessenger}) {
    {
      final BasicMessageChannel<Object?> channel = BasicMessageChannel<Object?>(
          'dev.flutter.pigeon.pigeon_sample2.ImagePathCallback.onImagePathUpdated', codec,
          binaryMessenger: binaryMessenger);
      if (api == null) {
        channel.setMessageHandler(null);
      } else {
        channel.setMessageHandler((Object? message) async {
          assert(message != null,
          'Argument for dev.flutter.pigeon.pigeon_sample2.ImagePathCallback.onImagePathUpdated was null.');
          final List<Object?> args = (message as List<Object?>?)!;
          final String? arg_imagePath = (args[0] as String?);
          api.onImagePathUpdated(arg_imagePath);
          return;
        });
      }
    }
  }
}
