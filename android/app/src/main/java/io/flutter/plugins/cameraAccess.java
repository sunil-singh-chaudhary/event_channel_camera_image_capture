// Autogenerated from Pigeon (v10.1.4), do not edit directly.
// See also: https://pub.dev/packages/pigeon

package io.flutter.plugins;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import io.flutter.plugin.common.BasicMessageChannel;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MessageCodec;
import io.flutter.plugin.common.StandardMessageCodec;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** Generated class from Pigeon. */
@SuppressWarnings({"unused", "unchecked", "CodeBlock2Expr", "RedundantSuppression", "serial"})
public class cameraAccess {

  /** Error class for passing custom error details to Flutter via a thrown PlatformException. */
  public static class FlutterError extends RuntimeException {

    /** The error code. */
    public final String code;

    /** The error details. Must be a datatype supported by the api codec. */
    public final Object details;

    public FlutterError(@NonNull String code, @Nullable String message, @Nullable Object details) 
    {
      super(message);
      this.code = code;
      this.details = details;
    }
  }

  @NonNull
  protected static ArrayList<Object> wrapError(@NonNull Throwable exception) {
    ArrayList<Object> errorList = new ArrayList<Object>(3);
    if (exception instanceof FlutterError) {
      FlutterError error = (FlutterError) exception;
      errorList.add(error.code);
      errorList.add(error.getMessage());
      errorList.add(error.details);
    } else {
      errorList.add(exception.toString());
      errorList.add(exception.getClass().getSimpleName());
      errorList.add(
        "Cause: " + exception.getCause() + ", Stacktrace: " + Log.getStackTraceString(exception));
    }
    return errorList;
  }

  /** Generated class from Pigeon that represents data sent in messages. */
  public static final class CameraPath {
    private @Nullable String imagePath;

    public @Nullable String getImagePath() {
      return imagePath;
    }

    public void setImagePath(@Nullable String setterArg) {
      this.imagePath = setterArg;
    }

    public static final class Builder {

      private @Nullable String imagePath;

      public @NonNull Builder setImagePath(@Nullable String setterArg) {
        this.imagePath = setterArg;
        return this;
      }

      public @NonNull CameraPath build() {
        CameraPath pigeonReturn = new CameraPath();
        pigeonReturn.setImagePath(imagePath);
        return pigeonReturn;
      }
    }

    @NonNull
    ArrayList<Object> toList() {
      ArrayList<Object> toListResult = new ArrayList<Object>(1);
      toListResult.add(imagePath);
      return toListResult;
    }

    static @NonNull CameraPath fromList(@NonNull ArrayList<Object> list) {
      CameraPath pigeonResult = new CameraPath();
      Object imagePath = list.get(0);
      pigeonResult.setImagePath((String) imagePath);
      return pigeonResult;
    }
  }

  private static class CameraPathApiCodec extends StandardMessageCodec {
    public static final CameraPathApiCodec INSTANCE = new CameraPathApiCodec();

    private CameraPathApiCodec() {}

    @Override
    protected Object readValueOfType(byte type, @NonNull ByteBuffer buffer) {
      switch (type) {
        case (byte) 128:
          return CameraPath.fromList((ArrayList<Object>) readValue(buffer));
        default:
          return super.readValueOfType(type, buffer);
      }
    }

    @Override
    protected void writeValue(@NonNull ByteArrayOutputStream stream, Object value) {
      if (value instanceof CameraPath) {
        stream.write(128);
        writeValue(stream, ((CameraPath) value).toList());
      } else {
        super.writeValue(stream, value);
      }
    }
  }

  /** Generated interface from Pigeon that represents a handler of messages from Flutter. */
  public interface CameraPathApi {

    @NonNull 
    CameraPath takeCameraImage();

    /** The codec used by CameraPathApi. */
    static @NonNull MessageCodec<Object> getCodec() {
      return CameraPathApiCodec.INSTANCE;
    }
    /**Sets up an instance of `CameraPathApi` to handle messages through the `binaryMessenger`. */
    static void setup(@NonNull BinaryMessenger binaryMessenger, @Nullable CameraPathApi api) {
      {
        BasicMessageChannel<Object> channel =
            new BasicMessageChannel<>(
                binaryMessenger, "dev.flutter.pigeon.pigeon_sample2.CameraPathApi.takeCameraImage", getCodec());
        if (api != null) {
          channel.setMessageHandler(
              (message, reply) -> {
                ArrayList<Object> wrapped = new ArrayList<Object>();
                try {
                  CameraPath output = api.takeCameraImage();
                  wrapped.add(0, output);
                }
 catch (Throwable exception) {
                  ArrayList<Object> wrappedError = wrapError(exception);
                  wrapped = wrappedError;
                }
                reply.reply(wrapped);
              });
        } else {
          channel.setMessageHandler(null);
        }
      }
    }
  }
  /** Generated class from Pigeon that represents Flutter messages that can be called from Java. */
  public static class ImagePathCallback {
    private final @NonNull BinaryMessenger binaryMessenger;

    public ImagePathCallback(@NonNull BinaryMessenger argBinaryMessenger) {
      this.binaryMessenger = argBinaryMessenger;
    }

    /** Public interface for sending reply. */ 
    @SuppressWarnings("UnknownNullness")
    public interface Reply<T> {
      void reply(T reply);
    }
    /** The codec used by ImagePathCallback. */
    static @NonNull MessageCodec<Object> getCodec() {
      return new StandardMessageCodec();
    }
    public void onImagePathUpdated(@Nullable String imagePathArg, @NonNull Reply<Void> callback) {
      BasicMessageChannel<Object> channel =
          new BasicMessageChannel<>(
              binaryMessenger, "dev.flutter.pigeon.pigeon_sample2.ImagePathCallback.onImagePathUpdated", getCodec());
      channel.send(
          new ArrayList<Object>(Collections.singletonList(imagePathArg)),
          channelReply -> callback.reply(null));
    }
  }
}
