dart run pigeon \
  --input lib/pigeon/leanplum_interface.dart \
  --dart_out lib/leanplum.dart \
  --objc_header_out ios/Runner/LeanplumMessage.h \
  --objc_source_out ios/Runner/LeanplumMessage.m \
  --java_out android/app/src/main/kotlin/com/ridebeam/channeltalk/LeanplumMessage.java \
  --java_package "com.ridebeam.channeltalk"


dart run pigeon \
--input lib/pigeon/channel_talk_interface.dart \
--dart_out lib/channel_talk.dart \
--kotlin_out android/app/src/main/kotlin/com/ridebeam/channeltalk/ChanneTalk.kt \
--java_package "com.ridebeam.channeltalk"