package com.DashO.app.JEP_446;

public class JEP_446_Scoped_Value {
    //Define scope value
    private final static  ScopedValue<String> KEY = ScopedValue.newInstance();
    private final ScopedValue<String> USER = ScopedValue.newInstance();

    //Define the Runnable
    Runnable task = () -> System.out.println("KEY -> " + (KEY.isBound() ? KEY.get() : "Unbound"));
    Runnable runnable = () -> System.out.println("USER -> " + (USER.isBound() ? USER.get() : "Hello"));

    public void printResult() {
        var result =JEP_446_Scoped_Value.scopeValue.get();
        System.out.println("Value of ScopedValue Variable is = " + (result));
    }
    public final static ScopedValue<Integer> scopeValue = ScopedValue.newInstance();
    public void PrintTheResult(){
        //Bind the scope value value "100"
        ScopedValue.where(scopeValue, 100)
                .run(() -> {
                    printResult();
                });
    }
    public void Framework() {}
    public void runAsUser(String user, Runnable op) {
        ScopedValue.where(USER, user).run(op);
    }
    public void doOperation() {
        try{
            String user = USER.orElseThrow(() -> new IllegalStateException("User not set"));}
        catch(Exception e){
            System.out.println("User is not set");
        }
    }

    public void JEP_446() {

        runAsUser("John", runnable);
        doOperation();
        // USE THE SAME RUNNABLE FOR EACH CARRIER
        task.run(); // -> Unbound
        ScopedValue.where(KEY, "KEY A").run(task); // KEY A
        ScopedValue.where(KEY, "KEY B").run(task);  //KEY B
        task.run(); // -> Unbound
    }

    public static void JEP_446_Method_Call(){
        JEP_446_Scoped_Value scopedValue = new JEP_446_Scoped_Value();
        scopedValue.Framework();
        scopedValue.JEP_446();
        //Call a class that contain scope value
        scopedValue.PrintTheResult();
    }

}
