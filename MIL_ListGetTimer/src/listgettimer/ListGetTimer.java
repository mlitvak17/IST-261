package listgettimer;

import com.opencsv.CSVReaderHeaderAware;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * class to show the differences in the average time to get an item from an ArrayList versus a LinkedList
 * 
 * CSV data slightly modified from the main csv file at
 * http://api.worldbank.org/v2/en/indicator/AG.LND.TOTL.K2?downloadformat=csv
 * 
 * opencsv information from http://opencsv.sourceforge.net/
 * 
 */
public class ListGetTimer {

    private final List<GeographicRegion> theArrayList;
    private final List<GeographicRegion> theLinkedList;
    
    // number to compute average time of get operation
    private static final long NUMBER_OF_GETS = 1000000;
    
    public ListGetTimer() {   
        theArrayList = new ArrayList<>();
        theLinkedList = new LinkedList<>();
        addDataToLists(); 
    }

    public static void main(String[] args) {

        ListGetTimer timer = new ListGetTimer();

        long avgTimeToGetItemFromArrayList = timer.calcAvgTimeOfListGet(timer.theArrayList);
        long avgTimeToGetItemFromLinkedList = timer.calcAvgTimeOfListGet(timer.theLinkedList);

        System.out.println("The average time to get an item from the Array List is "
                + avgTimeToGetItemFromArrayList + " nanoseconds.");
        System.out.println("The average time to get an item from the Linked List is "
                + avgTimeToGetItemFromLinkedList + " nanoseconds.");
    }

    private void addDataToLists() {
        String fileName = "regionsAndAreas.csv";
        Map<String, String> map;
        try (FileReader fileReader = new FileReader(fileName)) {
            CSVReaderHeaderAware csvReader = new CSVReaderHeaderAware(fileReader);
            while ((map = csvReader.readMap()) != null) {
                if (!map.get("2017").isEmpty()) {
                    this.theArrayList.add(new GeographicRegion(map.get("Country Name"), Double.parseDouble(map.get("2017"))));
                    this.theLinkedList.add(new GeographicRegion(map.get("Country Name"), Double.parseDouble(map.get("2017"))));
                }
            }
        } catch (IOException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    private long calcAvgTimeOfListGet(List<GeographicRegion> list) {
        return 0;
    }
}
