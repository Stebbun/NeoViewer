/**Homework 7.2 - ReferenceIDComparator Class
 * Comparator that sorts NearEarthObjects according to reference ID.
 * 
 * @author Steven Li
 * E-mail: steven.li@stonybrook.edu
 * SBU id: 110296296
 * course: CSE 214
 * recitation: 05
 */
public class ReferenceIDComparator implements java.util.Comparator<NearEarthObject>{
	
	public ReferenceIDComparator(){
		
	}
	
	@Override
	public int compare(NearEarthObject o1, NearEarthObject o2) {
		if(o1.getReferenceID() == o2.getReferenceID())
			return 0;
		else if(o1.getReferenceID() > o2.getReferenceID())
			return 1;
		else
			return -1;
	}

}
