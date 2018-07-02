package upe.poli.tcc.problem.cec2013;

import upe.poli.tcc.problem.Problem;


public class Weierstrass extends Problem{

	public Weierstrass(int dimensions) {
		super(dimensions);
	}

	@Override
	public void init() {
		
	}

	@Override
	public double getLowerBound(int dimension) {
		return -5;
	}

	@Override
	public double getUpperBound(int dimension) {
		return 5;
	}

	@Override
	public double evaluateSolution(double[] solution) {
		
		double result=0.0, sum=0.0, sum2=0.0, a=0.5, b=3.0;
	    int k_max=20;

	    for (int j=0; j<=k_max; ++j) {
	        sum2 += Math.pow(a,j)*Math.cos(2.0*Math.PI*Math.pow(b,j)*(0.5));
	    }
	    for (int i=0; i<this.getDimensions(); ++i) {
	        sum = 0.0;
	        for (int j=0; j<=k_max; ++j) {
	            sum += Math.pow(a,j)*Math.cos(2.0*Math.PI*Math.pow(b,j)*(solution[i]+0.5));
	        }
	        result += sum;
	    }
	    return result - sum2*this.getDimensions(); 
	}

	@Override
	public boolean isFitnessBetterThan(double currentSolutionFitness,
			double bestSolutionFitness) {
		return currentSolutionFitness < bestSolutionFitness;
	}

	@Override
	public String getName() {
		return "Weierstrass";
	}

	
}
