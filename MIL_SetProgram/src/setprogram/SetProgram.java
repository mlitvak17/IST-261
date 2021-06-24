package setprogram;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SetProgram {

    public static void main(String[] args) {

        Map<String, Integer> stringCountMap = new HashMap<>();
        String fileName = "GettysburgAddress.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                // split on one or more groups of punctuation or whitespace
                // see Java API for Pattern
                String[] words = line.split("[\\p{Punct}\\s]+");
                int count = 0;
                for (String word : words) {
                    word = word.toLowerCase();
                    stringCountMap.put(word, ++count);
                }
            }
        } catch (IOException ex) {
            System.err.println("Problem reading file: " + ex.getMessage());
        }
        System.out.println(stringCountMap);
    }

}