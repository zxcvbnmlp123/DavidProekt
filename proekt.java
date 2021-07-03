package com.company;

import java.util.Set;
import java.util.HashSet;
import java.lang.Math;
import java.util.Scanner;
import java.util.Arrays;
import java.util.List;
public class proekt {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Input first sentence");
        String textOne = input.nextLine();
        System.out.println("Input second sentence");
        String textTwo = input.nextLine();
        System.out.println("Similarities between the two texts: " + res(textOne, textTwo));
    }
    public static String[] cleanString(String text) {
        String[] words = text.replace("\n", " ").replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\s+");
        return words;
    }
    public static double wordLength(String[] text) {
        double allLength = 0;
        for (int x = 0; x < text.length; x++) {
            allLength = allLength + text[x].length();
        }
        double result = allLength / text.length;
        double finalResult = Math.round(result * 100.0) / 100.0;
        return finalResult;
    }
    public static double typeTokenRatio(String[] text) {
        Set<String> targetSet = new HashSet<String>(Arrays.asList(text));
        double setLength = targetSet.size();
        double textLength = text.length;
        double result = Math.round(setLength / textLength * 100.0) / 100.0;
        return result;
    }
    public static double hapaxRatio(String[] text) {
        Set<String> targetSet = new HashSet<String>(Arrays.asList(text));
        double unique = 0;
        for (String word : targetSet) {
            int count = 0;
            for (int x = 0; x < text.length; x++) {
                if (text[x].equals(word)) {
                    count++;
                }
            }
            if (count < 2) {
                unique++;
            }
        }
        double result = Math.round(unique / text.length * 100.0) / 100.0;
        return result;
    }
    public static double words(String text) {
        String[] average = text.replace("\n", " ").replace("'", "").toLowerCase().split("\s+");
        double count = 1;
        for (int x = 1; x < average.length; x++) {
            if ((average[x].endsWith(".") || average[x].endsWith("!") || average[x].endsWith("?")) && average[x - 1].matches("^[a-zA-Z0-9]*$")) {
                count++;
            }
        }
        double res = cleanString(text).length;
        double result = res / count;
        return result;
    }
    public static double res(String t1, String t2) {
        String[] cleanTextOne = cleanString(t1);
        String[] cleanTextTwo = cleanString(t2);
        System.out.println("Avr. word length for the first text: "+wordLength(cleanTextOne));
        System.out.println("Type-Toker Ratio for first text: " + typeTokenRatio(cleanTextOne));
        System.out.println("Hapax Logomena Ratio for first text: "+ hapaxRatio(cleanTextOne));
        System.out.println("Avg. sentence length for first text: "+ words(t1));
        System.out.println("-".repeat(20));
        System.out.println("Avr. word length for the second text: "+wordLength(cleanTextTwo));
        System.out.println("Type-Toker Ratio for second text: " + typeTokenRatio(cleanTextTwo));
        System.out.println("Hapax Logomena Ratio for second text: "+ hapaxRatio(cleanTextTwo));
        System.out.println("Avg. sentence length for second text: "+ words(t2));
        System.out.println("-".repeat(20));
        double f1 = wordLength(cleanTextOne) - wordLength(cleanTextTwo);
        double f2 = typeTokenRatio(cleanTextOne) - typeTokenRatio(cleanTextTwo);
        double f3 = hapaxRatio(cleanTextOne) - hapaxRatio(cleanTextTwo);
        double f4 = words(t1) - words(t2);
        double result = Math.abs(f1) * 11 + Math.abs(f2) * 33 + Math.abs(f3) * 50 + Math.abs(f4) * 0.4;
        result = Math.round(result* 100.0) / 100.0;
        return result;
    }
}









