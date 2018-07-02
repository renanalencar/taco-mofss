package upe.poli.tcc.problem.cec2013;

import upe.poli.tcc.problem.Problem;


/**
 *  F1: Five-Uneven-Peak Trap
 *  Variable ranges: x in [0, 30]
 *  No. of global peaks: 2
 *  No. of local peaks:  3.
 */
public class FiveUnevenPeakTrap extends Problem{

	public FiveUnevenPeakTrap(int dimensions) {
		super(dimensions);
	}

	@Override
	public void init() {
		//Do nothing
		
	}

	@Override
	public double getLowerBound(int dimension) {
		return 0;
	}

	@Override
	public double getUpperBound(int dimension) {
		return 30;
	}

	@Override
	public double evaluateSolution(double[] solution) {
		double result=-1.0;
	    if (solution[0]>=0 && solution[0]< 2.5) {
	        result = 80*(2.5-solution[0]);
	    } else if (solution[0] >= 2.5 && solution[0] < 5.0) {
	        result = 64*(solution[0]-2.5);
	    } else if (solution[0] >= 5.0 && solution[0] < 7.5) {
	        result = 64*(7.5-solution[0]);
	    } else if (solution[0] >= 7.5 && solution[0] < 12.5) {
	        result = 28*(solution[0]-7.5);
	    } else if (solution[0] >= 12.5 && solution[0] < 17.5) {
	        result = 28*(17.5-solution[0]);
	    } else if (solution[0] >= 17.5 && solution[0] < 22.5) {
	        result = 32*(solution[0]-17.5);
	    } else if (solution[0] >= 22.5 && solution[0] < 27.5) {
	        result = 32*(27.5-solution[0]);
	    } else if (solution[0] >= 27.5 && solution[0] <= 30) {
	        result = 80*(solution[0]-27.5);
	    }

	    return result;	
	}

	@Override
	public boolean isFitnessBetterThan(double currentSolutionFitness,
			double bestSolutionFitness) {
		return currentSolutionFitness > bestSolutionFitness;
	}

	@Override
	public String getName() {
		return "Five Uneven Peak";
	}

	
}
