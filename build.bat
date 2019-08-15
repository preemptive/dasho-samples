call ant -f java\simpleapp\build.xml
call ant -f java\simpleappdir\build.xml
call ant -f shelflife\basic\build.xml
call android\TamperOnAndroid\gradlew -b android\TamperOnAndroid\build.gradle clean assembleRelease
