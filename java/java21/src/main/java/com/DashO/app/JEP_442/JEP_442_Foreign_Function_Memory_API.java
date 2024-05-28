package com.DashO.app.JEP_442;

import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;

import static java.lang.foreign.ValueLayout.ADDRESS;
import static java.lang.foreign.ValueLayout.JAVA_LONG;

public class JEP_442_Foreign_Function_Memory_API {

    public static long JEP442() throws Throwable{
        //Get a lookup object for commonly used libraries
        SymbolLookup standardLibrary = Linker.nativeLinker().defaultLookup();

        //Get a handle to the "strlen" function in the C standard library
        MethodHandle strlen = Linker.nativeLinker().downcallHandle(
                standardLibrary.find("strlen").orElseThrow(),
                FunctionDescriptor.of(JAVA_LONG, ADDRESS));

        //Convert Java String to C string and store it in off-heap memory
        try(Arena offHeap = Arena.ofConfined()) {
            MemorySegment string = offHeap.allocateUtf8String("Codicological");

            //Invoke the foreign function
            long length = (long) strlen.invoke(string);
            return length;
        }
        // 5. Off-heap memory is deallocated at end of try-with-resources
    }
}
