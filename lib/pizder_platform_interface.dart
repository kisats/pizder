import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'pizder_method_channel.dart';

abstract class PizderPlatform extends PlatformInterface {
  /// Constructs a PizderPlatform.
  PizderPlatform() : super(token: _token);

  static final Object _token = Object();

  static PizderPlatform _instance = MethodChannelPizder();

  /// The default instance of [PizderPlatform] to use.
  ///
  /// Defaults to [MethodChannelPizder].
  static PizderPlatform get instance => _instance;
  
  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [PizderPlatform] when
  /// they register themselves.
  static set instance(PizderPlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Future<String?> pizd() {
    throw UnimplementedError('platformVersion() has not been implemented.');
  }
}
