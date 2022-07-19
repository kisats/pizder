import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'pizder_platform_interface.dart';

/// An implementation of [PizderPlatform] that uses method channels.
class MethodChannelPizder extends PizderPlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('pizder');

  @override
  Future<String?> pizd() async {
    final version = await methodChannel.invokeMethod<String>('pizd');
    return version;
  }
}
