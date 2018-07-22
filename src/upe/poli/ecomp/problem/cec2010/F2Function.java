package upe.poli.ecomp.problem.cec2010;

import upe.poli.ecomp.problem.Problem;
import upe.poli.ecomp.problem.cec2010.core.Defaults;
import upe.poli.ecomp.problem.cec2010.core.F2;

public class F2Function extends Problem{

	private F2 f2;
	
	public F2Function(int dimensions) {
		super(dimensions);
		Defaults.DEFAULT_DIM = dimensions;
		f2 = new F2();
		
	}
	
	@Override
	public void init() {

	}

	@Override
	public double getLowerBound(int dimension) {
		
		return f2.getMin();
	}

	@Override
	public double getUpperBound(int dimension) {
		
		return f2.getMax();
	}

	@Override
	public boolean isFitnessBetterThan(double currentSolutionFitness,
			double bestSolutionFitness) {

		return currentSolutionFitness < bestSolutionFitness;
	}

	@Override
	public String getName() {

		return "F2Function";
	}

	@Override
	public double evaluateSolution(double[] solution) {
	    return f2.compute(solution);
	}
}
