package com.DashO.app.JEP_431;

import java.util.*;

public class JEP_431_Sequenced_Collections {

    // #1.Using SequencedCollection methods on an ArrayList:
    public static void SequencedCollection() {
        ArrayList<Integer> arrayList = new ArrayList<>();

        // Add elements to the front and back of the list
        arrayList.addFirst(1); // [1]
        arrayList.addFirst(0); // [0, 1]
        arrayList.addLast(2); // [0, 1, 2]

        // Get the first and last elements of the list
        arrayList.getFirst(); // 0
        arrayList.getLast(); // 2

        // Get a reversed view of the list
        arrayList.reversed(); // [2, 1, 0]

        // Print the ArrayList and the results
        System.out.println("ArrayList: " + arrayList);
        System.out.println("ArrayList First: " + arrayList.getFirst());
        System.out.println("ArrayList Last: " + arrayList.getLast());
        System.out.println("ArrayList Reversed: " + arrayList.reversed());


    }

    public static void SequencedSet() {

        // Create a LinkedHashSet of strings
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();

        // Add elements to the set
        linkedHashSet.add("apple"); // ["apple"]
        linkedHashSet.add("banana"); // ["apple", "banana"]
        linkedHashSet.add("cherry"); // ["apple", "banana", "cherry"]

        // Get the first and last elements of the set
        linkedHashSet.getFirst(); // "apple"
        linkedHashSet.getLast(); // "cherry"

        // Get a reversed view of the set
        linkedHashSet.reversed(); // ["cherry", "banana", "apple"]

        // Print the ArrayList and the results
        System.out.println("LinkedHashSet : " + linkedHashSet);
        System.out.println("LinkedHashSet First: " + linkedHashSet.getFirst());
        System.out.println("LinkedHashSet Last: " + linkedHashSet.getLast());
        System.out.println("LinkedHashSet Reversed: " + linkedHashSet.reversed());
    }

    public static void SequencedMap() {
        LinkedHashMap<Integer, String> map = new LinkedHashMap<>();
        map.put(1, "One");
        map.put(2, "Two");
        map.put(3, "Three");
        map.firstEntry();   //1=One
        map.lastEntry();    //3=Three
        System.out.println(map);  //{1=One, 2=Two, 3=Three}
        Map.Entry<Integer, String> first = map.pollFirstEntry();   //1=One
        Map.Entry<Integer, String> last = map.pollLastEntry();    //3=Three
        System.out.println(map);  //{2=Two}
        map.putFirst(1, "One");     //{1=One, 2=Two}
        map.putLast(3, "Three");    //{1=One, 2=Two, 3=Three}
        System.out.println(map);  //{1=One, 2=Two, 3=Three}
        System.out.println(map.reversed());   //{3=Three, 2=Two, 1=One}
    }

    public static void UnsupportedOperationException() {
        List<Integer> list = List.of(1, 2, 3);
        try {
            list.addLast(4);
        }
        catch (UnsupportedOperationException e) {
            System.out.println("Caught Exception: Because there is no element present to return from the method. ");
        }

    }

    public static void NoSuchElementException() {

        List<Integer> list = List.of();

        try {
            list.getFirst();  //Exception in thread "main" java.lang.NoSuchElementException
        }
        catch (NoSuchElementException e) {
            System.out.println("Caught Exception: Because list is an unmodifiable type so we cannot use the methods such as addFirst(), addLast() ");
        }
    }

    public static void JEP_431_Method_Call(){
        System.out.println("1. ArrayList Operations(SequencedCollection):");
        SequencedCollection();

        System.out.println("\n2. LinkedHashSet Operations(SequencedSet):");
        SequencedSet();

        System.out.println("\n3. LinkedHashMap Operations(SequencedMap):");
        SequencedMap();

        System.out.println("\n4. List Operations(UnsupportedOperationException):");
        UnsupportedOperationException();

        System.out.println("\n5. List Operations(NoSuchElementException):");
        NoSuchElementException();
    }
}



