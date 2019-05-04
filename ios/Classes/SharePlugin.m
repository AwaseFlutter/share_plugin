#import "SharePlugin.h"
#import <share/share-Swift.h>

@implementation SharePlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftSharePlugin registerWithRegistrar:registrar];
}
@end
