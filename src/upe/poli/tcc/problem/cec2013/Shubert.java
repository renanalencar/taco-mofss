package upe.poli.tcc.problem.cec2013;

import upe.poli.tcc.problem.Problem;


/**
 * F6: Shubert
 * Variable ranges: x_i in  [−10, 10]^n, i=1,2,...,n
 * No. of global peaks: n*3^n
 * No. of local peaks: many
 * Global optima in 2D = 186.73
 * Global optima in 3D = 2079.0935
 */
public class Shubert extends Problem{

	public Shubert(int dimensions) {
		super(dimensions);
	}

	@Override
	public void init() {
		//Do nothing
	}

	@Override
	public double getLowerBound(int dimension) {
		return -10;
	}

	@Override
	public double getUpperBound(int dimension) {
		return 10;
	}

	@Override
	public double evaluateSolution(double[] solution) {
		double result = 1, sum = 0;
	    for (int i=0; i<this.getDimensions(); i++) {
	        sum=0;
	        for (int j=1; j<6; j++) {
	            sum = sum + j * Math.cos((j+1) * solution[i] + j);
	        }
	        result = result * sum;
	    }
	    return -result;
	}

	@Override
	public boolean isFitnessBetterThan(double currentSolutionFitness,
			double bestSolutionFitness) {
		return currentSolutionFitness > bestSolutionFitness;
	}

	@Override
	public String getName() {
		return "Shubert";
	}

}
