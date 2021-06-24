package hashmapkeysandvalues;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Iterator;
import java.util.stream.IntStream;

/**
 * Code based on
 * https://github.com/PacktPublishing/Java-Data-Analysis/blob/master/Chapter02/HashMapExamples/HashMapExample1/HashMapExample.java
 *
 * Java Data Analysis John R. Hubbard Sep 13, 2017
 *
 * License at end of file
 * 
 * Also used https://www.geeksforgeeks.org/iterate-map-java/ and 
 * https://stackoverflow.com/questions/46898/how-do-i-efficiently-iterate-over-each-entry-in-a-java-map
 */
public class HashMapKeysAndValues {

    public static void main(String[] args) {
        // read data values into countryPopulationMap
        File dataFile = new File("Countries.dat");
        Map<String, Integer> countryPopulationMap = new HashMap<>();
        try (Scanner input = new Scanner(dataFile)) {
            while (input.hasNext()) {
                String country = input.next();
                int population = input.nextInt();
                countryPopulationMap.put(country, population);
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Problem reading file: " + ex.getMessage());
        }

        System.out.println("\nThe keys are:");
        printKeys(countryPopulationMap);

        System.out.println("\nThe values are:");
        printValues(countryPopulationMap);

        // print key-value pairs in 4 different ways
        printKeyValuePairs(countryPopulationMap);

    }

    private static void printKeys(Map<String, Integer> myMap) {
        for (String country : myMap.keySet()) {
            System.out.println(country);
        }
    }

    private static void printValues(Map<String, Integer> myMap) {
        for (Integer area : myMap.values()) {
            System.out.println(area);
        }
    }

    
    
    /**
     * prints key-value pairs four ways
     * 
     *  1. using for loop and Map.Entry
     *  2. using lambda expression and foreach
     *  3. using Iterator of <Map.Entry<String,Integer>> and while loop
     *  4. using Java 8 streams
     * 
     * @param map the Map of String-Integer pairs
     * 
     */
    private static void printKeyValuePairs(Map<String, Integer> map) {
        // See https://www.geeksforgeeks.org/iterate-map-java/ 

        // using for loop and Map.Entry
        System.out.println("\nkey-value pairs using Map.Entry");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println("Key = " + entry.getKey() + 
                    ", Value = " + entry.getValue());
        }

        
        // using lambda expression and foreach
        System.out.println("\nkey-value pairs using lambda expression and foreach");
        map.forEach((String k,Integer v) -> {
            System.out.println("Key = " + k + ", Value = " + v);
        });

        // using Iterator of <Map.Entry<String,Integer>> and while loop
        System.out.println("\nkey-value pairs using Iterator on <Map.Entry<String,Integer>> and while loop");
        Iterator<Map.Entry<String, Integer>> itr = map.entrySet().iterator(); 
          
        while(itr.hasNext()) 
        { 
             Map.Entry<String, Integer> entry = itr.next(); 
             System.out.println("Key = " + entry.getKey() +  
                                 ", Value = " + entry.getValue()); 
        } 

        // using streams
        // https://stackoverflow.com/questions/46898/how-do-i-efficiently-iterate-over-each-entry-in-a-java-map
        // See summary post, item #7
        System.out.println("\nkey-value pairs using streams");
        map.entrySet().stream().forEach((Map.Entry<String, Integer> entry) ->
            {
                System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
            });
    }
    
}

/* https://github.com/PacktPublishing/Java-Data-Analysis/blob/master/LICENSE
MIT License

Copyright (c) 2017 Packt

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
