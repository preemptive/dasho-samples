Java.perform(function() {
    var mainActivity = Java.use("com.dasho.android.hook.MainActivity");

        mainActivity.getValue.implementation = function() {

            var result = this.getValue();

            console.log( "getValue returned " + result);

            return result;
        }
});
