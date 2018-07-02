package upe.poli.tcc.problem.cec2013;

import upe.poli.tcc.problem.Problem;

public class EF8F2 extends Problem{

	public EF8F2(int dimensions) {
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
		double result=0.0;
	    double x=0, y=0, f=0, f2=0;

	    for (int i=0; i<this.getDimensions()-1; ++i) {
	        x = solution[i]   +1;
	        y = solution[i+1] +1;

	        f2 = 100.0*(x*x - y)*(x*x - y) + (1.0 - x)*(1.0 - x);
	        f  = 1.0 + f2*f2/4000.0 - Math.cos(f2);

	        result += f;
	    }
	    /* do not forget the (dim-1,0) case! */
	    x = solution[this.getDimensions()-1] +1;
	    y = solution[0]     +1;

	    f2 = 100.0*(x*x - y)*(x*x - y) + (1.0 - x)*(1.0 - x);
	    f  = 1.0 + f2*f2/4000.0 - Math.cos(f2);

	    result += f;

	    return result;
	}

	@Override
	public boolean isFitnessBetterThan(double currentSolutionFitness,
			double bestSolutionFitness) {
		return currentSolutionFitness < bestSolutionFitness;
	}

	@Override
	public String getName() {
		return "EF8F2";
	}

	
}
