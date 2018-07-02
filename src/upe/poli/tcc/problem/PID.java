package upe.poli.tcc.problem;

import matlabcontrol.MatlabConnectionException;
import matlabcontrol.MatlabInvocationException;
import matlabcontrol.MatlabProxy;
import matlabcontrol.MatlabProxyFactory;

public class PID extends Problem {


	private MatlabProxyFactory factory;
	private MatlabProxy proxy;
	//pesos
	private double w1, w2, w3;
	
	public PID() {
		super(3);
		this.factory = new MatlabProxyFactory();
		try {
			this.proxy = factory.getProxy();
		} catch (MatlabConnectionException e) {
			e.printStackTrace();
		}
		w1 = 1;
		w2 = 1;
		w3 = 1;
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
		return 20;
	}

	@Override
	public double evaluateSolution(double[] solution) {
		
		double ISE = Double.NEGATIVE_INFINITY;
		double Mp = Double.NEGATIVE_INFINITY;
		double Ts = Double.NEGATIVE_INFINITY;
		
		Object[] returnParams = null;
		try {
			returnParams = proxy.returningFeval("Test", 3, solution);
		} catch (MatlabInvocationException e) {
			e.printStackTrace();
		}	
		
		ISE = ((double[]) returnParams[0])[0];
		Mp = ((double[]) returnParams[1])[0];
		Ts = ((double[]) returnParams[2])[0];
		
		return ISE*w1 + Mp*w2 + Ts*w3;

	}
	
	public double[] returnIndividualFitness(double[] solution){
		
		double ISE = Double.NEGATIVE_INFINITY;
		double Mp = Double.NEGATIVE_INFINITY;
		double Ts = Double.NEGATIVE_INFINITY;
		double[] individualFitness = new double[3];
		
		Object[] returnParams = null;
		try {
			returnParams = proxy.returningFeval("Test", 3, solution);
		} catch (MatlabInvocationException e) {
			e.printStackTrace();
		}	
		
		ISE = ((double[]) returnParams[0])[0];
		Mp = ((double[]) returnParams[1])[0];
		Ts = ((double[]) returnParams[2])[0];
		
		individualFitness[0] = ISE;
		individualFitness[1] = Mp;
		individualFitness[2] = Ts;
		
		return individualFitness;
	}

	@Override
	public boolean isFitnessBetterThan(double currentSolutionFitness,
			double bestSolutionFitness) {
		return currentSolutionFitness < bestSolutionFitness;
	}

	
	public String getName() {
		return "PID";
	}

}
