package upe.poli.tcc.main;

import upe.poli.tcc.algorithm.FishSchoolSearch;
import upe.poli.tcc.algorithm.Parameters;
import upe.poli.tcc.problem.Rosenbrock;

public class Main {
	
	public static void main(String[] args) {
		
		Rosenbrock problem = new Rosenbrock(Parameters.DIMENSION);
//		Sphere problem = new Sphere(Parameters.DIMENSION);
//		Schwefel12 problem = new Schwefel12(Parameters.DIMENSION);
//		Rastrigin problem = new Rastrigin(Parameters.DIMENSION);
//		Griewank problem = new Griewank(Parameters.DIMENSION);
//		Ackley problem = new Ackley(30);
//		PID problem = new PID();
//		Parameters.IS_MATLAB_FUNCTION = true;
		
		//Parameters have to be changed depending on the function selected
			FishSchoolSearch fss = new FishSchoolSearch(problem);
			System.out.println("media dos melhores fitness = "
			+fss.searchAndReturn(10000,5));
	}
}
