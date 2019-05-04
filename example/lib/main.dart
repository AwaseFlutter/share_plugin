import 'package:flutter/material.dart';
import 'dart:async';
import 'package:share/share.dart';

import 'package:flutter/services.dart';
import 'package:share/share.dart';

void main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
          appBar: AppBar(
            title: const Text('Plugin example app'),
          ),
          body: FloatingActionButton(onPressed: () {
            Share.share("https://twitter.com/");
          })),
    );
  }
}
