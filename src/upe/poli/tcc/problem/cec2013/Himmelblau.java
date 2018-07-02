package upe.poli.tcc.problem.cec2013;

import upe.poli.tcc.problem.Problem;


public class Himmelblau extends Problem{

	public Himmelblau(int dimensions) {
		super(dimensions);
	}

	@Override
	public void init() {
		
	}

	@Override
	public double getLowerBound(int dimension) {
		return -6;
	}

	@Override
	public double getUpperBound(int dimension) {
		return 6;
	}

	@Override
	public double evaluateSolution(double[] solution) {
		 return 200.0 - (solution[0]*solution[0] + solution[1] - 11.0)*
				 (solution[0]*solution[0] + solution[1] - 11.0) -
			        (solution[0] + solution[1]*solution[1] - 7.0)*
			        (solution[0] + solution[1]*solution[1] - 7.0);
	}

	@Override
	public boolean isFitnessBetterThan(double currentSolutionFitness,
			double bestSolutionFitness) {
		return currentSolutionFitness > bestSolutionFitness;
	}

	@Override
	public String getName() {
		return "Himmelblau";
	}

	
}
