//Hook response script
Java.perform(function () {
    var FindFibTask = Java.use('com.dasho.android.hook.FindFibTask');
    var MainActivity = Java.use('com.dasho.android.hook.MainActivity');
        var SystemClass = Java.use('java.lang.System');

        // Hook isInitialized() to always return true to satify the condition to trigger hook response
        MainActivity.isInitialized.implementation = function () {
            console.log('MainActivity.isInitialized() hooked to return true');
            return true; // Force isInitialized to return true
        };

        // Hook System.currentTimeMillis() to return an even number to satify the condition to trigger hook response
        SystemClass.currentTimeMillis.implementation = function () {
            var fakeTime = Math.floor(Date.now() / 1000) * 1000; // Current time rounded to seconds
            if ((fakeTime & 1) !== 0) {
                fakeTime += 1; // Ensure it is even
            }
            console.log('System.currentTimeMillis() hooked to return: ' + fakeTime);
            return fakeTime;
        };
    // Hooking the find method
    FindFibTask.find.overload('int').implementation = function (n) {
        console.log('find called with argument: ' + n);
        
        // Throwing an exception with a probability of 0.25
        var random = Math.random();
        if (random < 0.25) {
            console.log('Throwing exception from find()');
        }

        // Call the original find method
        return this.find(n);
    };
});