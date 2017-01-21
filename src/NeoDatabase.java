/**Homework 7.3 - NeoDatabase Class
 * The NeoDatabase contains and manages records of NearEarthObject instances.
 * These records are retrieved from NASA's public API.
 * 
 * @author Steven Li
 * E-mail: steven.li@stonybrook.edu
 * SBU id: 110296296
 * course: CSE 214
 * recitation: 05
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import big.data.DataSource;

public class NeoDatabase extends ArrayList<NearEarthObject>{
	public static final String API_KEY = "hlIB16lNyVtVf6QNFVYuBkHwwxKLWHU1efsMJMs6";
	public static final String API_ROOT = "https://api.nasa.gov/neo/rest/v1/neo/browse?";
	public static final int MAX_PAGE_NUM = 715;
	
	/**Default constructor. Constructs an empty NeoDatabase instance.
	 * 
	 */
	public NeoDatabase(){
		
	}
	
	/**Builds a query URL given a page number.
	 * 
	 * @param pageNumber
	 * 	Integer ranging from 0 to 715 indicating the page the user wishes to load.
	 * @return
	 * 	Query URL as a String.
	 * @throws IllegalArgumentException
	 * 	If pageNumber is not in the valid range.
	 * <dt>Preconditions:
	 *	0 ≤ pageNumber ≤ 715.
	 */
	public String buildQueryURL(int pageNumber) throws IllegalArgumentException{
		if(pageNumber < 0 || pageNumber > MAX_PAGE_NUM)
			throw new IllegalArgumentException("Not a valid page number.");
		return (API_ROOT + "page=" + pageNumber + "&api_key=" + API_KEY);
	}
	
	/**Opens a connection to the data source indicated by queryURL and adds all NearEarthObjects found in the dataset.
	 * 
	 * @param queryURL
	 * 	String containing the URL requesting a data set from the NASA NeoW service.
	 * @throws IllegalArgumentException
	 * 	If queryURL is null or could not be resolved by the server.
	 * <dt>Preconditions:
	 * 	queryURL is a non-null string representing a valid API request to the NASA NeoW service.
	 * <dt>Postconditions:
	 * 	All NearEarthObject records returned have been added to the database, or else a IllegalArgumentException has been thrown.
	 */
	public void addAll(String queryURL) throws IllegalArgumentException{
		if(queryURL == null)
			throw new IllegalArgumentException("Query not found.");
		
		DataSource source = DataSource.connectJSON(queryURL);
		source.load();
		
		NearEarthObject[] ufoArr = source.fetchArray(
				"NearEarthObject",
				"near_earth_objects/neo_reference_id",
				"near_earth_objects/name",
				"near_earth_objects/absolute_magnitude_h",
				"near_earth_objects/estimated_diameter/kilometers/estimated_diameter_min",
				"near_earth_objects/estimated_diameter/kilometers/estimated_diameter_max",
				"near_earth_objects/is_potentially_hazardous_asteroid",
				"near_earth_objects/close_approach_data/epoch_date_close_approach",
				"near_earth_objects/close_approach_data/miss_distance/kilometers",
				"near_earth_objects/close_approach_data/orbiting_body");
		
		Collections.addAll(this, ufoArr);
	}
	
	/**Sorts the database using the specified Comparator of NearEarthObjects.
	 * 
	 * @param comp
	 * 	Comparator of NearEarthObjects which will be used to sort the database.
	 * @throws IllegalArgumentException
	 * 	If comp is null.
	 * <dt>Preconditions:
	 * 	comp is not null.
	 * <dt>Postconditions:
	 * 	The database has been sorted based on the order specified by the inidcated Comparator of NearEarthObjects.
	 */
	public void sortData(Comparator<NearEarthObject> comp) throws IllegalArgumentException{
		if(comp == null)
			throw new IllegalArgumentException();
		Collections.sort(this, comp);
	}
	
	/**Displays the database in a neat, tabular form, listing all member variables for each NearEarthObject.
	 * 
	 * <dt>Preconditions:
	 * 	This NeoDatabase is initialized and not null.
	 * <dt>Postconditions:
	 * 	The table has been printed to the console but remains unchanged.
	 */
	public void printTable(){
		String header = String.format("%-12s%-17s%-8s%-12s%-10s%-12s%-12s%-10s",
										"\t| ID","| Name","| Mag.","| Diameter","| Danger",
										"| Close Date","| Miss Dist.", "| Orbits |");
		String border = "\t";
		for(int i = 0; i < 90; i++)
			border += "=";
		
		System.out.println(header);
		System.out.println(border);
		for(NearEarthObject e : this){
			System.out.println(e.toString());
		}
	}
}
