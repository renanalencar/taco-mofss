package upe.poli.tcc.problem.cec2013;

import upe.poli.tcc.problem.Problem;


public class Vincent extends Problem{

	public Vincent(int dimensions) {
		super(dimensions);
	}

	@Override
	public void init() {
		
	}

	@Override
	public double getLowerBound(int dimension) {
		return 0.25;
	}

	@Override
	public double getUpperBound(int dimension) {
		return 10;
	}

	@Override
	public double evaluateSolution(double[] solution) {
		double result = 0;
	    for (int i=0; i<this.getDimensions(); i++){
	        if (solution[i]<=0){
	        	throw new IllegalArgumentException();
	        }
	        result = result + Math.sin(10 * Math.log(solution[i]) );
	    }
	    return result/(1.0*this.getDimensions());
	}

	@Override
	public boolean isFitnessBetterThan(double currentSolutionFitness,
			double bestSolutionFitness) {
		return currentSolutionFitness > bestSolutionFitness;
	}

	@Override
	public String getName() {
		return "Vincent";
	}

	
}
