//Hook check script
Java.perform(function () {
    var MainActivity = Java.use('com.dasho.android.hook.MainActivity'); // Reference the MainActivity class
    var SystemClass = Java.use('java.lang.System'); // Reference the System class for exit

      // Hook isInitialized() to always return true to satify the condition to trigger hook response
    MainActivity.isInitialized.implementation = function () {
        console.log('MainActivity.isInitialized() hooked to return true');
        return true; // Force isInitialized to return true
    };

    // Hook the toastAndLaunch method
    MainActivity.toastAndLaunch.overload('java.lang.Class').implementation = function (clazz) {
        console.log('Exiting application as per hookCheck action.');
        this.toastAndLaunch(clazz)
    };
});