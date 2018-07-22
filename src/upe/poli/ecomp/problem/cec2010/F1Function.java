package upe.poli.ecomp.problem.cec2010;

import upe.poli.ecomp.problem.Problem;
import upe.poli.ecomp.problem.cec2010.core.Defaults;
import upe.poli.ecomp.problem.cec2010.core.F1;

public class F1Function extends Problem {

	private F1 f1;
	
	public F1Function(int dimensions) {
		
	    	super(dimensions);
		Defaults.DEFAULT_DIM = dimensions;
		f1 = new F1();
		
	}
	
	@Override
	public void init() {

	}

	@Override
	public double getLowerBound(int dimension) {
		
		return f1.getMin();
	}

	@Override
	public double getUpperBound(int dimension) {
		
		return f1.getMax();
	}

	@Override
	public boolean isFitnessBetterThan(double currentSolutionFitness,
			double bestSolutionFitness) {

		return currentSolutionFitness < bestSolutionFitness;
	}

	@Override
	public String getName() {

		return "F1Function";
	}

	@Override
	public double evaluateSolution(double[] solution) {
	    return f1.compute(solution);
	}
}
