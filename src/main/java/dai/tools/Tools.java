package main.java.dai.tools;

import java.util.ArrayList;
import java.util.Scanner;

public class Tools {
    public static String getScanner(){
        return   new Scanner(System.in).nextLine();
    }
    public static ArrayList<String> getInfo(String s) {
        String[] strings = s.split("，");
        ArrayList<String> info = new ArrayList<>();
        for (String string : strings) {
            info.add(string.split("：")[1]);
        }
        return info;
    }
}
