package upe.poli.tcc.problem.cec2013.compositefunction;

import upe.poli.tcc.problem.Griewank;
import upe.poli.tcc.problem.Problem;
import upe.poli.tcc.problem.Rastrigin;
import upe.poli.tcc.problem.Sphere;
import upe.poli.tcc.problem.cec2013.Weierstrass;

import java.util.ArrayList;

public class CF2 extends CFunc{

		public CF2(int dimensions) {
			super(dimensions, 8);
		    for (int i=0; i<nofunc_; ++i) {
		        sigma_[i] = 1.0;
		        bias_[i]  = 0.0;
		        weight_[i]= 0.0;
		    }
		    lambda_[0] = 1.0;
		    lambda_[1] = 1.0;
		    lambda_[2] = 10.0;
		    lambda_[3] = 10.0;
		    lambda_[4] = 1.0/10.0;
		    lambda_[5] = 1.0/10.0;
		    lambda_[6] = 1.0/7.0;
		    lambda_[7] = 1.0/7.0;
		    /* load optima */
		    if (this.getDimensions() == 2 || this.getDimensions() == 3 || this.getDimensions() == 5
		            || this.getDimensions() == 10 || this.getDimensions() == 20 ) {
		        String fname = "data/CF2_M_D" + this.getDimensions() + "_opt.dat";
		        loadOptima(fname);
		    } else {
		        initOptimaRandomly();
		    }
		    
		    /* M_ Identity matrices */
		    initRotmatIdentity();
		    
		    /* Initialise functions of the composition */
		    funcs_ = new ArrayList< Problem >();

		    funcs_.add(new Rastrigin(dimensions) );
		    funcs_.add(new Rastrigin(dimensions) );
		    funcs_.add(new Weierstrass(dimensions) );
		    funcs_.add(new Weierstrass(dimensions) );
		    funcs_.add(new Griewank(dimensions) );
		    funcs_.add(new Griewank(dimensions) );
		    funcs_.add(new Sphere(dimensions) );
		    funcs_.add(new Sphere(dimensions) );

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
		return "CF2";
	}

}
