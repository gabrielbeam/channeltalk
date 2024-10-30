import 'package:pigeon/pigeon.dart';

class BootConfig {
  String? pluginKey;
  String? memberId;
}

class BootResult {
  String? status;
}

@HostApi()
abstract class ChannelIOApi {
  BootResult boot(BootConfig config);
  void sleep();
  void shutdown();
  void showChannelButton();
  void showMessenger();
  void track(String eventName);
}

@FlutterApi()
abstract class ChannelIOBadgeApi {
  void onBadgeCountChanged(int badgeCount);
}