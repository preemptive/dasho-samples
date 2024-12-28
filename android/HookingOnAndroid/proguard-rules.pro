# Keep ApplicationLogic class and its method
-keep class com.dasho.android.hook.other.ApplicationLogic {
    void someApplicationLogic();
}

-keep class com.dasho.android.hook.RandomGen.** {
    void findRnd(...);
}

-keep class com.dasho.android.hook.FindFibTask {
    int find(int);
}

-keepclassmembers class com.dasho.android.hook.MainActivity {
    boolean isInitialized();
}

