/**Homework 7.2 - ApproachDateComparator Class
 * Comparator that sorts NearEarthObjects according to their closest approach date.
 * 
 * @author Steven Li
 * E-mail: steven.li@stonybrook.edu
 * SBU id: 110296296
 * course: CSE 214
 * recitation: 05
 */
public class ApproachDateComparator implements java.util.Comparator<NearEarthObject>{

	@Override
	public int compare(NearEarthObject o1, NearEarthObject o2) {
		return o1.getClosestApproachDate().compareTo(o2.getClosestApproachDate());
	}

}
