package upe.poli.tcc.main;

import upe.poli.tcc.algorithm.FishSchoolSearch;
import upe.poli.tcc.algorithm.Parameters;
import upe.poli.tcc.problem.cec2013.FiveUnevenPeakTrap;

public class MainCEC2013 {

public static void main(String[] args) {
		
//		F1:	
		FiveUnevenPeakTrap problem = new FiveUnevenPeakTrap(Parameters.DIMENSION);
//		F2:
//		EqualMaxima problem = new EqualMaxima(Parameters.DIMENSION);
//		F3:
//		UnevenDecreasingMaxima problem = new UnevenDecreasingMaxima(Parameters.DIMENSION);
//		F4:
//		Himmelblau problem = new Himmelblau(Parameters.DIMENSION);
//		F5:
//		SixHumpCamelBack problem = new SixHumpCamelBack(Parameters.DIMENSION);
//		F6:
//		Shubert prolem = new Shubert(Parameters.DIMENSION);
//		F7:
//		Vincent problem = new Vincent(Parameters.DIMENSION);
//		F8:
//		ModifiedRastriginAll problem = new ModifiedRastriginAll(Parameters.DIMENSION);
//		F9:		
//		CF1 problem = new CF1(Parameters.DIMENSION);
//		F10:		
//		CF2 problem = new CF2(Parameters.DIMENSION);
//		F11:
//		CF3 problem = new CF3(Parameters.DIMENSION);
//		F12:
//		CF4 problem = new CF4(Parameters.DIMENSION);
		
		//Parameters have to be changed depending on the function selected
		FishSchoolSearch fss = new FishSchoolSearch(problem);
		fss.search(10000,1);
	}
}
