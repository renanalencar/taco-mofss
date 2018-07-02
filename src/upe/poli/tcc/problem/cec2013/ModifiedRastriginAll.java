package upe.poli.tcc.problem.cec2013;

import upe.poli.tcc.problem.Problem;


public class ModifiedRastriginAll extends Problem{

	static final double [] MPPF92 = {3, 4};
	static final double [] MPPF98 = {1, 2, 1, 2, 1, 3, 1, 4};
	static final double [] MPPF916 = {1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 3, 1, 1, 1, 4};
	
	public ModifiedRastriginAll(int dimensions) {
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
		double result = 0;
	    for (int i=0; i<this.getDimensions(); i++){
	        if (this.getDimensions() == 2)  { 
	        	result = result + 10+ 9*Math.cos(2*Math.PI*MPPF92[i]*solution[i]); 
	        }
	        if (this.getDimensions() == 8)  { 
	        	result = result + 10+ 9*Math.cos(2*Math.PI*MPPF98[i]*solution[i]); 
	        }
	        if (this.getDimensions() == 16) { 
	        	result = result + 10+ 9*Math.cos(2*Math.PI*MPPF916[i]*solution[i]); 
	        }
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
		return "Modified Rastrigin";
	}

}
