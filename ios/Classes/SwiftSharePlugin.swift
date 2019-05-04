import Flutter
import UIKit
import share

public class SwiftSharePlugin: NSObject, FlutterPlugin {
  public static func register(with registrar: FlutterPluginRegistrar) {
    let channel = FlutterMethodChannel(name: "plugins/share", binaryMessenger: registrar.messenger())
    let instance = SwiftSharePlugin()
    registrar.addMethodCallDelegate(instance, channel: channel)
  }

  public func handle(_ call: FlutterMethodCall, result: @escaping FlutterResult) {
      switch call.method {
          case "share":
              guard
                  let args = call.arguments as? [String: Any],
                  let text = args["text"] as? String
              else {
                  result(FlutterError(code: "Error", message: "TextEmptyError", details: "Text is Null or Empty"))
                  return
              }
              if(!text.isEmpty){
                  share(sharedItems: [text])
                  result(true)
              } else {
                  result(FlutterError(code: "Error", message: "TextEmptyError", details: "Text is Null or Empty"))
              }
          default:
              result(FlutterError(code: "Error", message: "No corresponding method error", details: "\(call.method) is not allowed type here."))
       }
  }

  private func share(sharedItems: [String]) {
      let controller = UIActivityViewController(activityItems: sharedItems, applicationActivities: nil)
      let rootViewController = UIApplication.shared.keyWindow?.rootViewController
      rootViewController?.present(controller, animated: true, completion: nil)
  }
}
