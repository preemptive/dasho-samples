package com.DashO.app.JEP_443;
public class JEP_443_Unnamed_Patterns_And_Variables {
    record GeoLocation(double latitude,
                       double longitude,
                       double altitude) {}
    enum BuildingType { APARTMENT, OFFICE,
        SCHOOL, FACTORY, SHOP }
    record Building(String name,
                    GeoLocation location,
                    BuildingType type) {}

    record AppPayment<T extends Payment>(T payment) { }

    public static void JEP_443_Unnamed_Patterns_And_Variable() {
        Building destination = new Building("Diamond Heights",
                new GeoLocation(17.43543, 78.47551, 5),
                BuildingType.APARTMENT);
        // Code does not use BuildingType type
        if (destination instanceof Building(String buildingName,
                                            GeoLocation mainLoaction,
                                            BuildingType typeOfBuilding) ){
            System.out.println(String
                    .format("Your destination, %s, is at Location (%f, %f)",
                            buildingName , mainLoaction.latitude, mainLoaction.longitude));
        }

        // New Feature code can use BuildingType _ and
        // GeoLocation altitude
        /* The unnamed pattern is denoted by an underscore character _ (U+005F)
           It allows the type and name of a record component to
           be elided in pattern matching
        */
        if (destination instanceof Building(String buildingName,
                                            GeoLocation mainLoaction,
                                            _)) {
            System.out.println(String
                    .format("Your destination, %s, is at Location (%f, %f)",
                            buildingName, mainLoaction.latitude, mainLoaction.longitude));
        }


        if (destination instanceof
                Building(String name,
                         GeoLocation(double latitude,
                                     double longitude,
                                     _) ,
                         BuildingType _)) {
            System.out.println(String
                    .format("Your destination, %s, is at Location (%f, %f)",
                            name, latitude, longitude));
        }

        // Catch block where the Exception variable is
        // not intended to be used
        // Unnamed variables
        String string = "java 22";

        try {
            int integer = Integer.parseInt(string);
            System.out.println("My number is: " + integer);
        } catch (Exception _) {
        }

        // Unnamed pattern variables

        AppPayment<? extends Payment> payment =
                new AppPayment<>(new PayPalPayment());

        // Unnamed variables
        switch (payment) {
            case AppPayment(PayPalPayment _)   ->
                    System.out.println("Deducted from PayPal.");
            case AppPayment(WalletPayment _)  ->
                    System.out.println("Deducted from Wallet.");
            case AppPayment(CardPayment _) ->
                    System.out.println("Deducted from VISA.");
        }
    }
}

sealed abstract class Payment permits
        PayPalPayment,
        WalletPayment,
        CardPayment { }
final class PayPalPayment extends Payment { }
final class WalletPayment extends Payment { }
final class CardPayment extends Payment { }