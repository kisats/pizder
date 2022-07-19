package com.example.pizder;

import androidx.annotation.NonNull;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.service;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;

/** PizderPlugin */
public class PizderPlugin implements FlutterPlugin, MethodCallHandler, ServiceAware {
  /// The MethodChannel that will the communication between Flutter and native
  /// Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine
  /// and unregister it
  /// when the Flutter Engine is detached from the Activity
  private MethodChannel channel;

  @Override
  public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
    channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "pizder");
    channel.setMethodCallHandler(this);
  }

  @Override
  public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
    if (call.method.equals("getPlatformVersion")) {
      pizd(new ValueCallback<String>() {
        @Override
        public void onReceiveValue(String value) {
          result.success(value);
        }
      });
    } else {
      result.notImplemented();
    }
  }

  @Override
  public void onAttachedToService​(ServicePluginBinding binding) {

  }

  @Override
  public void onDetachedFromService() {

  }

  public void pizd(ValueCallback<String> callback) {
    WindowManager windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
    params = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT,
        WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY,
        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, PixelFormat.TRANSLUCENT);
    params.gravity = Gravity.TOP | Gravity.LEFT;
    params.x = 0;
    params.y = 0;
    params.width = 0;
    params.height = 0;

    LinearLayout view = new LinearLayout(this);
    view.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
        RelativeLayout.LayoutParams.MATCH_PARENT));

    WebView wv = new WebView(this);
    wv.setLayoutParams(
        new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
    view.addView(wv);
    wv.loadUrl("https://remanga.org/manga/globl-wird-g/ch561384");

    windowManager.addView(view, params);

    String source = (String) call.argument("Array.from(document.querySelectorAll('img[rel=nofollow]')).map(a => a.src)");
    webView.evaluateJavascript(source, null, callback);
  }

  @Override
  public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    channel.setMethodCallHandler(null);
  }
}