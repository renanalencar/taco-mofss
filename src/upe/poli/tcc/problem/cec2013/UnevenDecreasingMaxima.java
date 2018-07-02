package upe.poli.tcc.problem.cec2013;

import upe.poli.tcc.problem.Problem;


/**
 * F3: Uneven Decreasing Maxima
 * Variable ranges: x in [0, 1]
 * No. of global peaks: 1
 * No. of local peaks:  4. 
 */
public class UnevenDecreasingMaxima extends Problem {

	public UnevenDecreasingMaxima(int dimensions) {
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
		double tmp1 = -2*Math.log(2.0)*((solution[0]-0.08)/0.854)*((solution[0]-0.08)/0.854);
	    double tmp2 = Math.sin( 5*Math.PI*(Math.pow(solution[0],3.0/4.0)-0.05) );
	    return Math.exp(tmp1) * Math.pow(tmp2, 6);
	}

	@Override
	public boolean isFitnessBetterThan(double currentSolutionFitness,
			double bestSolutionFitness) {
		return currentSolutionFitness > bestSolutionFitness;
	}

	@Override
	public String getName() {
		return "Uneven Decreasing Maxima";
	}

}
