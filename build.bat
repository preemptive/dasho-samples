call ant -f java\simpleapp\build.xml
call ant -f java\simpleappdir\build.xml
call ant -f shelflife\basic\build.xml
call android\TamperOnAndroid\gradlew -b android\TamperOnAndroid\build.gradle clean assembleRelease
call android\DebuggingOnAndroid\gradlew -b android\DebuggingOnAndroid\build.gradle clean assembleDebug
copy android\DebuggingOnAndroid\build\outputs\apk\debug\DebuggingOnAndroid-debug.apk android\
@echo
@echo Review instruction at http://wiki.preemptive.internal/Dev/DashO/Documentation/
