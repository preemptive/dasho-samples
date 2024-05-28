
package com.DashO.app.JEP_440;

public class JEP_440_Record_Patterns {
    public record TotalMarks(double physicsMarks, double mathsMarks, double chemistryMarks) {}
    public record Details(String name, int idNumber, TotalMarks marks) {}
    public sealed interface Position permits Position2D, Position3D {}

    public record Position2D(int xPoint, int yPoint) implements Position {}
    public record PositionFirst(int xPoint,int yPoint){}
    public record PathForNestedRecord(PositionFirst from, PositionFirst to) {}
    public record Position3D(int xPointFor3D, int yPointFor3D, int zPointFor3D) implements Position {}

    public record Path<ParameterPath extends Position>(ParameterPath from, ParameterPath to) {}

    //    Record patterns and exhaustive switch
    public static void JEP440_Exhaustive(Object object) {
        switch (object) {
            case Path(Position2D from, Position2D to) ->
                    System.out.printf("object is a 2D path: %d/%d -> %d/%d%n",
                            from.xPoint(), from.yPoint(), to.xPoint(), to.yPoint());
            case Path(Position3D from, Position3D to) ->
                    System.out.printf("object path from : xPoint-> %d  yPoint-> %d  zPoint-> %d to xPoint-> %d  yPoint-> %d zPoint-> %d %n",
                            from.xPointFor3D(), from.yPointFor3D(), from.zPointFor3D(), to.xPointFor3D(), to.yPointFor3D(), to.zPointFor3D());
            default -> System.out.println("object is something else: " + object);
        }
    }
    //    Record patterns and exhaustive switch
    public static void JEP440_Exhaustive_Switch(Object object) {
        switch (object) {
            case PathForNestedRecord(PositionFirst(int xStartPoint, int yStartPoint), PositionFirst(int xEndPoint, int yEndPoint)) ->
                    System.out.printf("object path from : xPoint-> %d  yPoint-> %d to xPoint-> %d  yPoint-> %d%n", xStartPoint, yStartPoint, xEndPoint, yEndPoint);
            default-> System.out.println("object is something else that was not present in the cases");
        }
    }

    public static void JEP440_Nested(Object object) {
        // record nested pattern in
        if (object instanceof Details(String name2, int id_Number, TotalMarks(double subject1, double subject2, double subject3))) {
            System.out.println("Details of students ");
            System.out.println("Student ID_number : " + id_Number);
            System.out.println("Student Name : " + name2);
            System.out.println("Subject total marks obtain by student Marks : " + (subject1+subject2+subject3));
        }
    }

    public static void JEP440_Method_Call(){
        JEP440_Nested(new Details("Jack", 1,
                new TotalMarks(45, 89, 65)));
        Position2D p2d = new Position2D(1, 2);
        Position3D p3d = new Position3D(1, 2, 3);
        Path<Position2D> path2d = new Path<>(p2d,
                new Position2D(3, 4));
        Path<Position3D> path3d = new Path<>(p3d,
                new Position3D(4, 5, 6));
        PathForNestedRecord pathForNestedRecord = new PathForNestedRecord(
                new PositionFirst(3, 5), new PositionFirst(3, 5));
        JEP440_Exhaustive(p2d);
        JEP440_Exhaustive(path3d);
        JEP440_Exhaustive_Switch(pathForNestedRecord);
    }

}
