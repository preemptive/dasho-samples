package com.DashO.app.JEP_453;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.StructuredTaskScope;
import java.util.function.Supplier;


public class JEP_453_Structured_Concurrency {
    public final static ScopedValue<Integer> scopevalue = ScopedValue.newInstance();
    public static void test(){
        JEP_453_Structured_Concurrency scopevalued = new JEP_453_Structured_Concurrency();
        ScopedValue.where(scopevalue, 20).run(() -> {
            try {
                System.out.println(scopevalued.handleStructure());
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public handle handleStructure() throws ExecutionException, InterruptedException
    {
        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            Supplier<Integer> user  = scope.fork(() -> findAccount());
            Supplier<Integer> order = scope.fork(() -> fetchOrder());

            scope.join()            // Join both subtasks
                    .throwIfFailed();  // ... and propagate errors

            // Here, both subtasks have succeeded, so compose their results
            return new handle(user.get(), order.get());
        }
    }
    private int findAccount(){
        return 30;
    }
    private int fetchOrder(){
        return 40;
    }
    record handle(int user, int order){
    }
}