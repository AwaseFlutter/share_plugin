import 'dart:async';

import 'package:flutter/services.dart';

class Share {
  Share._();

  static const MethodChannel _channel = const MethodChannel('plugins/share');

  static Future<void> share(String text) {
    assert(text != null || text.isNotEmpty);
    final Map<String, String> params = <String, String>{"text": text};
    return _channel.invokeMethod('share', params);
  }
}
