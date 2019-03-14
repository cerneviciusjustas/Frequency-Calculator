/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home_work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

/**
 * frequency calculator of given numbers
 *
 * @author Justas
 */
public class Freq_calc {

    public static void main(String[] args) {
        
        List<Integer> list = input();              // reading from input stream

        // finding frequency of each number
        // complexity O(n)
        Map<Integer, Integer> hashMap = frequencyByHashMap(list);
        System.out.println("\nFrequency by HashMap");
        // printing results
        print(hashMap);
        printBonus(hashMap);

        // finding frequency of each number
        // complexity O(n^2)
        ValueHolder<Integer> holder = frequency(list);

        // printing results
        System.out.println("\nFrequency by Collections");
        print(holder.getValue1(), holder.getValue2());
        printBonus(holder.getValue1(), holder.getValue2());
        
        //**********************************************
        //uncommeent to test performance of both methods.
        //performanceTest(64000, 1000);
    }

    /**
     * Prints numbers and their frequency in a graph.
     *
     * @param frequency frequency of numbers.
     * @param number numbers.
     */
    private static void printBonus(List<Integer> frequency, List<Integer> number) {
        int maxFreq = Collections.max(frequency);
        for (int i = maxFreq; i >= 1; i--) {
            for (int value : frequency) {
                if (value >= i) {
                    System.out.print(String.format("%-3s", "*"));
                } else {
                    System.out.print(String.format("%-3s", " "));
                }
            }
            System.out.println();
        }
        for (int value : number) {
            System.out.print(String.format("%-3d", value));
        }
        System.out.println();
    }

    /**
     * Prints numbers and their frequency.
     *
     * @param frequency frequency of numbers.
     * @param number numbers.
     */
    private static void print(List<Integer> frequency, List<Integer> number) {
        System.out.print("\nfrequency:");
        for (int value : frequency) {
            System.out.print(String.format("%-3d", value));
        }
        System.out.print("\nnumber:   ");
        for (int value : number) {
            System.out.print(String.format("%-3d", value));
        }
        System.out.println();
    }

    /**
     * Reads input of the user.
     *
     * @return entered integers in ArrayList by user.
     */
    private static List<Integer> input() {
        List<Integer> list = new ArrayList<>();
        System.out.println("Enter numbers separated by commas and then hit <Enter>:");
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String in = "";
            // handles empty string if enter has been pressed
            while (in.equals("")) {
                in = reader.readLine();
            }
            String[] parts = in.split(",");
            for (String part : parts) {
                part = part.trim();
                list.add(Integer.parseInt(part));
            }
        } catch (IOException ex) {
        }
        return list;
    }

    /**
     * Finds frequency of list members by using HashMap Complexity O(n)
     *
     * @param list
     * @return HashMap
     */
    private static Map<Integer, Integer> frequencyByHashMap(List<Integer> list) {
        int max = Collections.max(list);           // bigest number in the list
        int min = Collections.min(list);           // smallest number in the list
        Map<Integer, Integer> hashMap = new HashMap<>();
        for (Integer number : list) {
            Integer count = hashMap.get(number);
            hashMap.put(number, count != null ? count + 1 : 1);
        }
        // for putting numbers which have 0 frequency
        for (int i = min; i <= max; i++) {
            Integer count = hashMap.get(i);
            if (count == null) {
                hashMap.put(i, 0);
            }
        }
        return hashMap;
    }

    /**
     * Prints numbers and their frequency.
     *
     * @param hashMap key - number, value - frequency
     */
    private static void print(Map<Integer, Integer> hashMap) {
        System.out.print("\nfrequency:");
        for (Entry<Integer, Integer> el : hashMap.entrySet()) {
            System.out.print(String.format("%-3d", el.getValue()));
        }
        System.out.print("\nnumber:   ");
        for (Entry<Integer, Integer> el : hashMap.entrySet()) {
            System.out.print(String.format("%-3d", el.getKey()));
        }
        System.out.println();
    }

    /**
     * Prints numbers and their frequency in a graph.
     *
     * @param hashMap key - number, value - frequency
     */
    private static void printBonus(Map<Integer, Integer> hashMap) {
        int maxFreq = Collections.max(hashMap.values());
        for (int i = maxFreq; i >= 1; i--) {
            for (int value : hashMap.values()) {
                if (value >= i) {
                    System.out.print(String.format("%-3s", "*"));
                } else {
                    System.out.print(String.format("%-3s", " "));
                }
            }
            System.out.println();
        }
        for (int value : hashMap.keySet()) {
            System.out.print(String.format("%-3d", value));
        }
        System.out.println();
    }

    /**
     * Test of both frequency methods
     * @param inputSize size of input
     * @param bounds  random bounds
     */
    private static void performanceTest(int inputSize, int bounds) {
        System.out.println("--------------------------performance test--------------------------");
        List<Integer> list = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < inputSize; i++) {
            list.add(random.nextInt(bounds));
        }
        
        System.out.println("Performance of Collections.frequency().");
        long t0 = System.currentTimeMillis();
        frequency(list);
        System.out.println("Used time: " + (System.currentTimeMillis() - t0) + "ms");
        
        System.out.println("Performance of HashMap.");
        t0 = System.currentTimeMillis();
        frequencyByHashMap(list);
        System.out.println("Used time: " + (System.currentTimeMillis() - t0) + "ms");

    }

    /**
     * Finds frequency of list members by using HashMap Complexity O(n^2)
     * @param list 
     * @return ValueHolder
     */
    private static ValueHolder<Integer> frequency(List<Integer> list) {

        int max = Collections.max(list);           // bigest number in the list
        int min = Collections.min(list);           // smallest number in the list
        List<Integer> freq = new ArrayList<>();
        List<Integer> numb = new ArrayList<>();
        for (int i = min; i <= max; i++) {
            freq.add(Collections.frequency(list, i));
            numb.add(i);
        }
        return new ValueHolder<>(freq, numb);
    }
}
