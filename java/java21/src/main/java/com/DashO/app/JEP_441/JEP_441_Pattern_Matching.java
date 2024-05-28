package com.DashO.app.JEP_441;

public class JEP_441_Pattern_Matching {

    String formatAndDescribeValue(Object obj) {
        return switch (obj) {
            case Integer i -> String.format("Integer %d", i);
            case Long l -> String.format("Long %d", l);
            case Double d -> String.format("Double %f", d);
            case String s -> String.format("String %s", s);
            default -> obj.toString();
        };
    }

    void generateResponseBasedOnString(String s) {
        switch (s) {
            case null -> System.out.println("No event received.");
            case "Hello", "Bye" -> System.out.println(("Greet."));
            default -> System.out.println(("Same to you!"));
        }
    }

    String validateInputAndRespond(String response) {
        return switch (response) {
            case null -> "No event received.";
            case "y", "Y" -> "Yes.";
            case "n", "N" -> "No.";
            case String s when s.equalsIgnoreCase("YES") -> "Yes.";
            case String s when s.equalsIgnoreCase("NO") -> "No.";
            default -> "Retry!";
        };
    }

    sealed interface GameClassification permits IndoorGame, OutdoorGame {
    }

    public enum IndoorGame implements GameClassification {BOARD, BALL, VIDEO, PUZZLE}

    final class OutdoorGame implements GameClassification {
    }

    void switchClassicEnums(IndoorGame c) {
        switch (c) {
            case BOARD -> System.out.println("It's BOARD");
            case BALL -> System.out.println("It's BALL");
            case VIDEO -> System.out.println("It's VIDEO");
            default -> System.out.println("It's PUZZLE");
        }
    }

    void exhaustiveSwitchWithoutEnumSupport(GameClassification c) {
        switch (c) {
            case IndoorGame s when s == IndoorGame.BOARD -> System.out.println("It's BOARD");
            case IndoorGame s when s == IndoorGame.BALL -> System.out.println("It's BALL");
            case IndoorGame s when s == IndoorGame.VIDEO -> System.out.println("It's VIDEO");
            case IndoorGame s -> System.out.println("It's PUZZLE");
            case OutdoorGame t -> System.out.println("It's a OutdoorGame");
        }
    }

    void exhaustiveSwitchWithBetterEnumSupport(GameClassification c) {
        switch (c) {
            case IndoorGame.BOARD -> System.out.println("It's BOARD");
            case IndoorGame.BALL -> System.out.println("It's BALL");
            case IndoorGame.VIDEO -> System.out.println("It's VIDEO");
            case IndoorGame.PUZZLE -> System.out.println("It's PUZZLE");
            case OutdoorGame t -> System.out.println("It's a OutdoorGame");
        }
    }
    public static void JEP_441_Method_Call(){
        JEP_441_Pattern_Matching JEP441 = new JEP_441_Pattern_Matching();

        System.out.println(JEP441.formatAndDescribeValue(Integer.MAX_VALUE));
        System.out.println(JEP441.formatAndDescribeValue(Integer.MIN_VALUE));
        System.out.println(JEP441.formatAndDescribeValue("John"));

        JEP441.generateResponseBasedOnString(null);
        JEP441.generateResponseBasedOnString("Hello");

        System.out.println(JEP441.validateInputAndRespond(null));
        System.out.println(JEP441.validateInputAndRespond("y"));
        System.out.println(JEP441.validateInputAndRespond("Yes"));

        JEP441.switchClassicEnums(JEP_441_Pattern_Matching.IndoorGame.BOARD);

        JEP441.exhaustiveSwitchWithoutEnumSupport(JEP_441_Pattern_Matching.IndoorGame.BALL);

        JEP441.exhaustiveSwitchWithBetterEnumSupport(JEP_441_Pattern_Matching.IndoorGame.PUZZLE);
    }
}
