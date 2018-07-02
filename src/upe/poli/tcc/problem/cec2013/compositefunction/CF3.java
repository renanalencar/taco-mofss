package upe.poli.tcc.problem.cec2013.compositefunction;

import upe.poli.tcc.problem.Griewank;
import upe.poli.tcc.problem.Problem;
import upe.poli.tcc.problem.cec2013.EF8F2;
import upe.poli.tcc.problem.cec2013.Weierstrass;

import java.util.ArrayList;

public class CF3 extends CFunc{

	public CF3(int dimensions) {
		super(dimensions, 6);

		for (int i=0; i<nofunc_; ++i) {
	        bias_[i]  = 0.0;
	        weight_[i]= 0.0;
	    }
	    sigma_[0] = 1.0;
	    sigma_[1] = 1.0;
	    sigma_[2] = 2.0;
	    sigma_[3] = 2.0;
	    sigma_[4] = 2.0;
	    sigma_[5] = 2.0;
	    lambda_[0] = 1.0/4.0;
	    lambda_[1] = 1.0/10.0;
	    lambda_[2] = 2.0;
	    lambda_[3] = 1.0;
	    lambda_[4] = 2.0;
	    lambda_[5] = 5.0;
	    /* load optima */
	    if (this.getDimensions() == 2 || this.getDimensions() == 3 || this.getDimensions() == 5
	            || this.getDimensions() == 10 || this.getDimensions() == 20 ) {
	        String fname;
	        fname = "data/CF3_M_D" + this.getDimensions() + "_opt.dat";
	        loadOptima(fname);
	        fname = "data/CF3_M_D" + this.getDimensions() + ".dat";
	        loadRotationMatrix(fname);
	    } else {
	        initOptimaRandomly();
	        /* M_ Identity matrices */
	        initRotmatIdentity();
	    }
	    /* Initialise functions of the composition */
	    funcs_ = new ArrayList< Problem >();

	    funcs_.add(new EF8F2(dimensions) );
	    funcs_.add(new EF8F2(dimensions) );
	    funcs_.add(new Weierstrass(dimensions) );
	    funcs_.add(new Weierstrass(dimensions) );
	    funcs_.add(new Griewank(dimensions) );
	    funcs_.add(new Griewank(dimensions) );

	    CalculateFMaxi();
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
		return this.evaluateInner_(solution);
	}

	@Override
	public boolean isFitnessBetterThan(double currentSolutionFitness,
			double bestSolutionFitness) {
		return currentSolutionFitness > bestSolutionFitness;
	}

	@Override
	public double doEvaluate(double[] x) {
		return this.evaluateInner_(x);
	}

	@Override
	public String getName() {
		return "CF3";
	}

	
}
