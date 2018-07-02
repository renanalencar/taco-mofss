package upe.poli.tcc.problem.cec2013.compositefunction;

import upe.poli.tcc.problem.Griewank;
import upe.poli.tcc.problem.Problem;
import upe.poli.tcc.problem.Sphere;
import upe.poli.tcc.problem.cec2013.Weierstrass;

import java.util.ArrayList;

public class CF1 extends CFunc{

	public CF1(int dimensions) {
		super(dimensions, 6);
		
	    for (int i=0; i<nofunc_; ++i) {
	        sigma_[i] = 1;
	        bias_[i]  = 0;
	        weight_[i]= 0;
	    }
	    lambda_[0] = 1.0;
	    lambda_[1] = 1.0;
	    lambda_[2] = 8.0;
	    lambda_[3] = 8.0;
	    lambda_[4] = 1.0/5.0;
	    lambda_[5] = 1.0/5.0;
	    /* load optima */
	    if (this.getDimensions() == 2 || this.getDimensions() == 3 || this.getDimensions() == 5
	            || this.getDimensions() == 10 || this.getDimensions() == 20 ) {
	        String fname = "data/CF1_M_D" + this.getDimensions() + "_opt.dat";
	        //String root = System.getProperty("user.dir");
//	        System.out.println(root+ " : " +fname);
	        loadOptima(fname);
	    } else {
	    	System.err.println("NOT SUPPOSED TO BE HERE");
	        initOptimaRandomly();
	    }
	    /* M_ Identity matrices */
	    initRotmatIdentity();
	    /* Initialise functions of the composition */
	    funcs_ = new ArrayList< Problem >();

	    funcs_.add(new Griewank(dimensions) );
	    funcs_.add(new Griewank(dimensions) );
	    funcs_.add(new Weierstrass(dimensions) );
	    funcs_.add(new Weierstrass(dimensions) );
	    funcs_.add(new Sphere(dimensions) );
	    funcs_.add(new Sphere(dimensions) );

	    //TODO: calculate this correctly in the initialisation phase
	    CalculateFMaxi();
	}

	@Override
	public double doEvaluate(double[] x) {
		return this.evaluateInner_(x);
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
	public String getName() {
		return "CF1";
	}
}
