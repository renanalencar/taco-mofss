package upe.poli.ecomp.problem.cec2010;

import upe.poli.ecomp.problem.Problem;
import upe.poli.ecomp.problem.cec2010.core.Defaults;
import upe.poli.ecomp.problem.cec2010.core.F4;

public class F4Function extends Problem {

	private F4 f4;
	
	public F4Function(int dimensions) {
		super(dimensions);
		Defaults.DEFAULT_DIM = dimensions;
		Defaults.DEFAULT_M = Defaults.DEFAULT_DIM/2;
		f4 = new F4();
		
	}
	
	@Override
	public void init() {

	}

	@Override
	public double getLowerBound(int dimension) {
		
		return f4.getMin();
	}

	@Override
	public double getUpperBound(int dimension) {
		
		return f4.getMax();
	}

	@Override
	public boolean isFitnessBetterThan(double currentSolutionFitness,
			double bestSolutionFitness) {

		return currentSolutionFitness < bestSolutionFitness;
	}

	@Override
	public String getName() {

		return "F4Function";
	}

	@Override
	public double evaluateSolution(double[] solution) {
	    return f4.compute(solution);
	}
}
