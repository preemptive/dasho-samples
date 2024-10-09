package org.example;

public class Main {
    public static void main(String[] args) {
        OnlyStrings onlyStrings = new OnlyStrings();
        StringIssue stringIssue = new StringIssue();
        MultipleVars multipleVars = new MultipleVars();

        String value1 = onlyStrings.writeSting();
        String value2 = stringIssue.writeStingAndVariable();

        System.out.println(value1);
        System.out.println(value2);
        System.out.println(multipleVars.writeStingAndVariable());
    }
}