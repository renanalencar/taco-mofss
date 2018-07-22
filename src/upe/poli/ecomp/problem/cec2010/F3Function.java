package upe.poli.ecomp.problem.cec2010;

import upe.poli.ecomp.problem.Problem;
import upe.poli.ecomp.problem.cec2010.core.Defaults;
import upe.poli.ecomp.problem.cec2010.core.F3;

public class F3Function extends Problem{

	private F3 f3;
	
	public F3Function(int dimensions) {
		super(dimensions);
		Defaults.DEFAULT_DIM = dimensions;
		f3 = new F3();
		
	}
	
	@Override
	public void init() {

	}

	@Override
	public double getLowerBound(int dimension) {
		
		return f3.getMin();
	}

	@Override
	public double getUpperBound(int dimension) {
		
		return f3.getMax();
	}

	@Override
	public boolean isFitnessBetterThan(double currentSolutionFitness,
			double bestSolutionFitness) {

		return currentSolutionFitness < bestSolutionFitness;
	}

	@Override
	public String getName() {

		return "F3Function";
	}

	@Override
	public double evaluateSolution(double[] solution) {
	    return f3.compute(solution);
	}
}
