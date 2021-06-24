package listgettimer;

/**
 * data from http://api.worldbank.org/v2/en/indicator/AG.LND.TOTL.K2?downloadformat=csv
 * from the main csv file there 
 */
public class GeographicRegion {
    
    private final String name;
    private final double area; // in sq. km. -- 

    public GeographicRegion(String name, double area) {
        this.name = name;
        this.area = area;
    }
    
}
