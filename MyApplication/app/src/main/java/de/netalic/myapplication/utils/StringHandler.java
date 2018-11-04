package de.netalic.myapplication.utils;

public class StringHandler {

    public static String replacer(String string){
        String replacedString;
        if (string.contains("a")){
            replacedString = string.replace('a','b');
        }else{
            replacedString = string;
        }
        return replacedString;
    }

    public static char[] splitter(String string){
        char[] sortedString;
        sortedString = string.toCharArray();
        return sortedString;
    }

    public static int converter(String string){

        int integer = -1;

        if (string.matches("[0-9]+")){
            integer = Integer.valueOf(string);
        }

        return integer;
    }

    public static boolean checkOnlyContainChar(String string){
        if (string.matches("[a-zA-Z]+")){
            return true;
        }
        else {
            return false;
        }

    }
}
