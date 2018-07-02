package upe.poli.tcc.problem.cec2013;

import upe.poli.tcc.problem.Problem;


/**
 * F2: Equal Maxima
 * Variable ranges: x in [0, 1]
 * No. of global peaks: 5
 * No. of local peaks:  0. 
 */
public class EqualMaxima extends Problem{

	public EqualMaxima(int dimensions) {
		super(dimensions);
		
	}

	@Override
	public void init() {
		
	}

	@Override
	public double getLowerBound(int dimension) {
		return 0;
	}

	@Override
	public double getUpperBound(int dimension) {
		return 1;
	}

	@Override
	public double evaluateSolution(double[] solution) {
		double s = Math.sin(5.0 * Math.PI * solution[0]);
	    return Math.pow(s, 6);	}

	@Override
	public boolean isFitnessBetterThan(double currentSolutionFitness,
			double bestSolutionFitness) {
		return currentSolutionFitness > bestSolutionFitness;
	}

	@Override
	public String getName() {
		return "Equal Maxima";
	}

}
