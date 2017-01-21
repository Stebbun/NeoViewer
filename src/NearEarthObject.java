/**Homework 7.1 - NearEarthObject Class
 * A NearEarthObject instance represents a record in the database of asteroids currently tracked by NASA. 
 * An instance will always be constructed by the BigData library.
 * 
 * 
 * @author Steven Li
 * E-mail: steven.li@stonybrook.edu
 * SBU id: 110296296
 * course: CSE 214
 * recitation: 05
 */
import java.text.SimpleDateFormat;
import java.util.Date;

public class NearEarthObject {
	private int referenceID;
	private String name;
	private double absoluteMagnitude;
	private double averageDiameter;
	private boolean isDangerous;
	private Date closestApproachDate;
	private double missDistance;
	private String orbitingBody;
	private String isDangerousStr;
	private String dateStr;
	
	/**Default Constructor. Creates an instance of the NearEarthObject class.
	 * 
	 * @param referenceID
	 * 	Unique the ID of the NEO.
	 * @param name
	 * 	Unique name of the asteroid or orbital body.
	 * @param absoluteMagnitude
	 * 	Absolute brightness of the asteroid or orbital body in the sky.
	 * @param minDiameter
	 * 	Estimated minimum diameter of the asteroid or orbital body in kilometers.
	 * @param maxDiameter
	 * 	Estimated maximum diameter of the asteroid or orbital body in kilometers.
	 * @param isDangerous
	 * 	Boolean value indicating whether the asteroid or orbital body is a potential impact threat.
	 * @param closestDateTimestamp
	 * 	Unix timestamp representing the date of closest approach.
	 * @param missDistance
	 * 	Distance in kilometers at which the asteroid or orbital body will pass by the Earth on the date of it's closest approach.
	 * @param orbitingBody
	 * 	Planet or other orbital body which this NEO orbits.
	 */
	public NearEarthObject(int referenceID, String name, double absoluteMagnitude, double minDiameter,
							double maxDiameter, boolean isDangerous, long closestDateTimestamp,
							double missDistance, String orbitingBody)
	{
		this.referenceID = referenceID;
		this.name = name;
		this.absoluteMagnitude = absoluteMagnitude;
		this.averageDiameter = (minDiameter + maxDiameter) / 2;
		this.isDangerous = isDangerous;
		this.closestApproachDate = new Date(closestDateTimestamp);
		this.missDistance = missDistance;
		this.orbitingBody = orbitingBody;
		
		this.setIsDangerousStr(Boolean.toString(isDangerous));
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		this.setDateStr(format.format(this.closestApproachDate));
	}
	
	public String toString(){
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		String dateStr = format.format(this.closestApproachDate);
		
		return String.format("%3s%-12d%-18s%-8s%-12s%-10s%-12s%-12s%-10s", "\t", this.referenceID, this.name, Double.toString(this.absoluteMagnitude),
								String.format("%1.3f", this.averageDiameter), this.isDangerous, dateStr,
								String.format("%.0f", this.missDistance), this.orbitingBody);
	}

	/**Receive the reference ID of the NEO.
	 * 
	 * @return
	 * 	Unique ID of the NEO.
	 */
	public int getReferenceID() {
		return referenceID;
	}

	
	/**Set the reference ID of the NEO.
	 * 
	 * @param referenceID
	 * 	
	 */
	public void setReferenceID(int referenceID) {
		this.referenceID = referenceID;
	}

	/**Receive the name of the NEO.
	 * 
	 * @return
	 * 	Unique name of the NEO.
	 */
	public String getName() {
		return name;
	}

	/**Set the name of the NEO.
	 * 
	 * @param name
	 * 	Unique name of the NEO.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**Receive the absolute brightness of the NEO.
	 * 
	 * @return
	 * 	Absolute brightness of the NEO.
	 */
	public double getAbsoluteMagnitude() {
		return absoluteMagnitude;
	}

	/**Set the absolute brightness of the NEO.
	 * 
	 * @param absoluteMagnitude
	 * 	Absolute brightness of the NEO.
	 */
	public void setAbsoluteMagnitude(double absoluteMagnitude) {
		this.absoluteMagnitude = absoluteMagnitude;
	}

	/**Receive the average diameter of the NEO.
	 * 
	 * @return
	 * 	Average diameter of the NEO in kilometers.
	 */
	public double getAverageDiameter() {
		return averageDiameter;
	}

	/**Set the average diameter of the NEO.
	 * 
	 * @param averageDiameter
	 * 	Average diameter of the NEO in kilometers.
	 */
	public void setAverageDiameter(double averageDiameter) {
		this.averageDiameter = averageDiameter;
	}

	/**Receive the boolean value indicating whether the NEO is a potential impact threat.
	 * 
	 * @return
	 * 	Boolean value indicating whether the asteroid or orbital body is a potential impact threat.
	 */
	public boolean isDangerous() {
		return isDangerous;
	}

	/**Set the boolean value indicating whether the NEO is a potential impact threat.
	 * 
	 * @param isDangerous
	 * 	Boolean value indicating whether the asteroid or orbital body is a potential impact threat.
	 */
	public void setDangerous(boolean isDangerous) {
		this.isDangerous = isDangerous;
	}

	/**Receive the closest approach date of the NEO.
	 * 
	 * @return
	 * 	The closest approach date of the NEO as a Date.
	 */
	public Date getClosestApproachDate() {
		return closestApproachDate;
	}

	/**Set the closest approach date of the NEO.
	 * 
	 * @param closestApproachDate
	 * 	The closest approach date of the NEO as a Date.
	 */
	public void setClosestApproachDate(Date closestApproachDate) {
		this.closestApproachDate = closestApproachDate;
	}

	/**Receive the miss distance of the NEO on its closest approach date.
	 * 
	 * @return
	 * 	Distance in kilometers at which the asteroid or orbital body will pass by the Earth on the date of it's closest approach.
	 */
	public double getMissDistance() {
		return missDistance;
	}

	/**Set the miss distance of the NEO on its closest approach date.
	 * 
	 * @param missDistance
	 * 	Distance in kilometers at which the asteroid or orbital body will pass by the Earth on the date of it's closest approach.
	 */
	public void setMissDistance(double missDistance) {
		this.missDistance = missDistance;
	}

	/**Receive planet or other orbital body which this NEO orbits.
	 * 
	 * @return
	 * The planet or other orbital body which this NEO orbits.
	 */
	public String getOrbitingBody() {
		return orbitingBody;
	}

	/**Set the planet or other orbital body which this NEO orbits.
	 * 
	 * @param orbitingBody
	 * 	The planet or other orbital body which this NEO orbits.
	 */
	public void setOrbitingBody(String orbitingBody) {
		this.orbitingBody = orbitingBody;
	}

	/**Receive the closest approach date of the NEO as a string.
	 * 
	 * @return
	 * 	Closest approach date of the NEO as a string.
	 */
	public String getDateStr() {
		return dateStr;
	}

	/**Set the closest approach date of the NEO as a string.
	 * 
	 * @param dateStr
	 * 	Closest approach date of the NEO as a string.
	 */
	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}

	/**Receive the boolean indicating if potential threat as a string.
	 * 
	 * @return
	 * 	Boolean indicating if potential threat as a string.
	 */
	public String getIsDangerousStr() {
		return isDangerousStr;
	}

	/**Set the boolean indicating if potential threat as a string.
	 * 
	 * @param isDangerousStr
	 *	Boolean indicating if potential threat as a string. 	
	 */
	public void setIsDangerousStr(String isDangerousStr) {
		this.isDangerousStr = isDangerousStr;
	}
}
