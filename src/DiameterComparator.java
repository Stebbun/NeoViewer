/**Homework 7.2 - DiameterComparator Class
 * Comparator that sorts NearEarthObjects according to their average diameter.
 * 
 * @author Steven Li
 * E-mail: steven.li@stonybrook.edu
 * SBU id: 110296296
 * course: CSE 214
 * recitation: 05
 */
public class DiameterComparator implements java.util.Comparator<NearEarthObject>{

	@Override
	public int compare(NearEarthObject o1, NearEarthObject o2) {
		if(o1.getAverageDiameter() == o2.getAverageDiameter())
			return 0;
		else if(o1.getAverageDiameter() > o2.getAverageDiameter())
			return 1;
		else
			return -1;
	}

}
