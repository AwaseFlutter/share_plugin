import 'dart:async';

import 'package:flutter/services.dart';

class Share {
  static const MethodChannel _channel = const MethodChannel('plugins/share');

  static Future<void> share(String text) {
    assert(text != null || text.isNotEmpty);
    return _channel.invokeMethod('share', text);
  }
}
