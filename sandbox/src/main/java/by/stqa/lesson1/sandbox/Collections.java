package by.stqa.lesson1.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collections {
    public static void main(String[] args) {
        String[] langs = {"Java","PHP","Python"};
        Integer[] years = {1942,2000,1987};

        List<String> langs2 = Arrays.asList("Java","PHP","Python","C#");
        List langs3 = Arrays.asList("Java",1963,"Python",2.0);

        for (String el : langs2){
            System.out.println("Языки программирования: " + el);
        }
        for (Object el2 : langs3){
            System.out.println("Языки программирования: " + el2);
        }
    }
}

