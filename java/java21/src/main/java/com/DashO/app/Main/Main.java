package com.DashO.app.Main;

import com.DashO.app.JEP_430.JEP_430_String_Templates;
import com.DashO.app.JEP_431.JEP_431_Sequenced_Collections;
import com.DashO.app.JEP_440.JEP_440_Record_Patterns;
import com.DashO.app.JEP_441.JEP_441_Pattern_Matching;
import com.DashO.app.JEP_442.JEP_442_Foreign_Function_Memory_API;
import com.DashO.app.JEP_443.JEP_443_Unnamed_Patterns_And_Variables;
import com.DashO.app.JEP_444.JEP_444_Virtual_Threads;
import com.DashO.app.JEP_446.JEP_446_Scoped_Value;
import com.DashO.app.JEP_448.JEP_448_Vector_API;
import com.DashO.app.JEP_452.JEP_452_Key_Encapsulation_Mechanism_API;
import com.DashO.app.JEP_453.JEP_453_Structured_Concurrency;

public class Main {
    public static void main(String args[]) throws Throwable {
        if (args.length < 1) {
            //JEP_430
            System.out.println("\nJEP430: String_Templates : \n");
            JEP_430_String_Templates processor = new JEP_430_String_Templates();
            System.out.println("\n################################################################\n");
            ////////////////////////////////////////////////////////////////

            //JEP_431
            System.out.println("\nJEP431: Sequenced Collections : \n");
            JEP_431_Sequenced_Collections.JEP_431_Method_Call();
            System.out.println("\n################################################################\n");
            ////////////////////////////////////////////////////////////////

            //JEP_439
            System.out.println("\nJEP439: Generational ZGC :\n");
            JEP_439.JEP_439_Generational_ZGC.Generational_ZGC();
            System.out.println("\n################################################################\n");
            ////////////////////////////////////////////////////////////////

            //JEP_440
            System.out.println("\nJEP440: Record_Patterns :\n");
            JEP_440_Record_Patterns.JEP440_Method_Call();
            System.out.println("\n################################################################\n");
            ////////////////////////////////////////////////////////////////

            //JEP_441
            System.out.println("\nJEP441: Switch Pattern Matching :\n");
            JEP_441_Pattern_Matching.JEP_441_Method_Call();
            System.out.println("\n################################################################\n");
            ////////////////////////////////////////////////////////////////

            //JEP_442
            System.out.println("\nJEP442: Foreign Function & Memory API : \n");
            System.out.println(JEP_442_Foreign_Function_Memory_API.JEP442());
            System.out.println("\n################################################################\n");
            ////////////////////////////////////////////////////////////////

            //JEP_443
            System.out.println("\nJEP_443_Unnamed_Patterns_Variables : \n");
            JEP_443_Unnamed_Patterns_And_Variables.JEP_443_Unnamed_Patterns_And_Variable();
            System.out.println("\n################################################################\n");
            ////////////////////////////////////////////////////////////////

            //JEP_444
            System.out.println("\nJEP444: Virtual Threads : \n");
            JEP_444_Virtual_Threads.JEP_444_Method_Call();
            System.out.println("\n################################################################\n");
            ////////////////////////////////////////////////////////////////

            //JEP446
            System.out.println("\nJEP_446_Scoped_Values : \n");
            JEP_446_Scoped_Value.JEP_446_Method_Call();
            System.out.println("\n################################################################\n");
            ////////////////////////////////////////////////////////////////

            //JEP_448
            System.out.println("\nJEP448: Vector API : \n");
            JEP_448_Vector_API.JEP_448_Method_Call();
            System.out.println("\n################################################################\n");
            ////////////////////////////////////////////////////////////////

            //JEP_451
            System.out.println("\nJEP-451: Dynamic Loading : \n");
            JEP_451.JEP_451_Dynamic_loading.agentLoader();
            System.out.println("\n################################################################\n");
            ////////////////////////////////////////////////////////////////

            //JEP452
            System.out.println("\nJEP-452: Key Encapsulation Mechanism API : \n");
            JEP_452_Key_Encapsulation_Mechanism_API.jep_452();
            System.out.println("\n################################################################\n");
            ////////////////////////////////////////////////////////////////

            //JEP453
            System.out.println("\nJEP453: Structured_Concurrency : \n");
            JEP_453_Structured_Concurrency obj = new JEP_453_Structured_Concurrency();
            System.out.println(obj.handleStructure());
            System.out.println("\n################################################################\n");
            System.exit(1);
        }

        String choiceString = args[0];

        switch (choiceString) {
            case "JEP430":
                //JEP_430
                System.out.println("\nJEP430: String_Templates : \n");
                JEP_430_String_Templates processor = new JEP_430_String_Templates();
                System.out.println("\n################################################################\n");
                break;
            ////////////////////////////////////////////////////////////////

            case "JEP431":
                //JEP_431
                System.out.println("\nJEP431: Sequenced Collections : \n");
                JEP_431_Sequenced_Collections.JEP_431_Method_Call();
                System.out.println("\n################################################################\n");
                break;
            ////////////////////////////////////////////////////////////////

            case "JEP439":
                //JEP_439
                System.out.println("\nJEP439: Generational ZGC :\n");
                JEP_439.JEP_439_Generational_ZGC.Generational_ZGC();
                System.out.println("\n################################################################\n");
                break;
            ////////////////////////////////////////////////////////////////

            case "JEP440":
                //JEP_440
                System.out.println("\nJEP440: Record_Patterns :\n");
                JEP_440_Record_Patterns.JEP440_Method_Call();
                System.out.println("\n################################################################\n");
                break;
            ////////////////////////////////////////////////////////////////

            case "JEP441":
                //JEP_441
                System.out.println("\nJEP441: Switch Pattern Matching :\n");
                JEP_441_Pattern_Matching.JEP_441_Method_Call();
                System.out.println("\n################################################################\n");
                break;
            ////////////////////////////////////////////////////////////////


            case "JEP442":
                //JEP_442
                System.out.println("\nJEP442: Foreign Function & Memory API : \n");
                System.out.println(JEP_442_Foreign_Function_Memory_API.JEP442());
                System.out.println("\n################################################################\n");
                break;
            ////////////////////////////////////////////////////////////////

            case "JEP443":
                //JEP_443
                System.out.println("\nJEP_443_Unnamed_Patterns_Variables : \n");
                JEP_443_Unnamed_Patterns_And_Variables.JEP_443_Unnamed_Patterns_And_Variable();
                System.out.println("\n################################################################\n");
                break;
            ////////////////////////////////////////////////////////////////

            case "JEP444":
                //JEP_444
                System.out.println("\nJEP444: Virtual Threads : \n");
                JEP_444_Virtual_Threads.JEP_444_Method_Call();
                System.out.println("\n################################################################\n");
                break;
            ////////////////////////////////////////////////////////////////

            case "JEP446":
                //JEP446
                System.out.println("\nJEP_446_Scoped_Values : \n");
                JEP_446_Scoped_Value.JEP_446_Method_Call();
                System.out.println("\n################################################################\n");
                break;
            ////////////////////////////////////////////////////////////////

            case "JEP448":
                //JEP_448
                System.out.println("\nJEP448: Vector API : \n");
                JEP_448_Vector_API.JEP_448_Method_Call();
                System.out.println("\n################################################################\n");
                break;
            ////////////////////////////////////////////////////////////////

            case "JEP451":
                //JEP_451
                System.out.println("\nJEP-451: Dynamic Loading : \n");
                JEP_451.JEP_451_Dynamic_loading.agentLoader();
                System.out.println("\n################################################################\n");
                break;
            ////////////////////////////////////////////////////////////////

            case "JEP452":
                //JEP452
                System.out.println("\nJEP-452: Key Encapsulation Mechanism API : \n");
                JEP_452_Key_Encapsulation_Mechanism_API.jep_452();
                System.out.println("\n################################################################\n");
                break;
            ////////////////////////////////////////////////////////////////

            case "JEP453":
                //JEP453
                System.out.println("\nJEP453: Structured_Concurrency : \n");
                JEP_453_Structured_Concurrency obj = new JEP_453_Structured_Concurrency();
                System.out.println(obj.handleStructure());
                System.out.println("\n################################################################\n");
                break;
            ////////////////////////////////////////////////////////////////

            case "EXIT":
                System.out.println("\n Exiting...");
                break;
            ////////////////////////////////////////////////////////////////

            default:
                System.out.println("\nInvalid choice. Please try again.");
                break;
            ////////////////////////////////////////////////////////////////
        }
    }
}