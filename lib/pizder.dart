
import 'pizder_platform_interface.dart';

class Pizder {
  Future<String?> pizd() {
    return PizderPlatform.instance.pizd();
  }
}
