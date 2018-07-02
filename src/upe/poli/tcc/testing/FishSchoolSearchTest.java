package upe.poli.tcc.testing;

import junit.framework.TestCase;
import upe.poli.tcc.algorithm.FishSchoolSearch;
import upe.poli.tcc.algorithm.Parameters;
import upe.poli.tcc.problem.Sphere;

public class FishSchoolSearchTest extends TestCase{

	public void testLocalSearch(){

		Sphere s = new Sphere(30);
		FishSchoolSearch fss = new FishSchoolSearch(s);
		fss.initializeSchool();
		double position1 = fss.getSchool()[0].getCurrentPosition()[0];
		fss.localSearch();
		double position2 = fss.getSchool()[0].getCurrentPosition()[0];
		//System.out.println(position1);
		//System.out.println(position2);
		assertTrue(Math.abs(position2 - position1) <= Parameters.STEP_IND_INIT*(s.getUpperBound(0) - 
				s.getLowerBound(0)));
		//assertTrue(Math.abs(position2 - position1) != 0);
	}
	
	public void testLocalSearchNewWeight(){

		Sphere s = new Sphere(30);
		FishSchoolSearch fss = new FishSchoolSearch(s);
		fss.initializeSchool();
		fss.localSearch();
		fss.setSchoolLocalSearchNewWeight();
		
		for (int i = 0; i < fss.getSchool().length; i++) {
			if(fss.getSchool()[i].isImprovedFitness()){
				assertTrue(fss.getSchool()[i].getWeight() > 
						((Parameters.MAX_WEIGHT + Parameters.MIN_WEIGHT)/2));
			}else{
				assertTrue((double)fss.getSchool()[i].getWeight() == 
						((double)(Parameters.MAX_WEIGHT + Parameters.MIN_WEIGHT)/2));
			}
		}
	}
	
	
}
