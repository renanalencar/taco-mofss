package upe.poli.tcc.problem.cec2013;

import upe.poli.tcc.problem.Problem;


/**
 * F5: Six-Hump Camel Back
 * Variable ranges: x in [−1.9, 1.9]; y in [−1.1, 1.1]
 * No. of global peaks: 2
 * No. of local peaks:  2.
 */

public class SixHumpCamelBack extends Problem{

	public SixHumpCamelBack(int dimensions) {
		super(dimensions);
	}

	@Override
	public void init() {
		
	}

	@Override
	public double getLowerBound(int dimension) {
		
		if(dimension == 0){
			return -1.9;
		}else if(dimension == 1){
			return -1.1;
		}else{
			throw new IllegalArgumentException("This function should have exactly 2 dimensions");
		}
	}

	@Override
	public double getUpperBound(int dimension) {
		if(dimension == 0){
			return 1.9;
		}else if(dimension == 1){
			return 1.1;
		}else{
			throw new IllegalArgumentException("This function should have exactly 2 dimensions");
		}
	}

	@Override
	public double evaluateSolution(double[] solution) {
		return -( (4.0 - 2.1*solution[0]*solution[0] + Math.pow(solution[0],4.0)/3.0)*solution[0]*solution[0] +
		        solution[0]*solution[1] + (4.0*solution[1]*solution[1] -4.0)*solution[1]*solution[1] );
	}

	@Override
	public boolean isFitnessBetterThan(double currentSolutionFitness,
			double bestSolutionFitness) {
		return currentSolutionFitness > bestSolutionFitness;
	}

	@Override
	public String getName() {
		return "Six Hump Camel Back";
	}

}
