package upe.poli.ecomp.main;

import upe.poli.ecomp.algorithm.MyFSS_Padrao;
import upe.poli.ecomp.algorithm.Parameters;
import upe.poli.ecomp.problem.Problem;
import upe.poli.ecomp.problem.Rastrigin;

public class Main {
    
    public static void main(String[] args) {
	Parameters.SCHOOL_SIZE = 30;
	Parameters.SIMULATION_NUMBER = 30;
	Parameters.ITERATION_NUMBER = 5000;
	Parameters.DIMENSION = 30; 
	Parameters.STEP_IND_INIT = 0.1;
	Parameters.STEP_IND_FINAL = 0.00001;
	Parameters.DEBUG = false;
	
	Problem p = new Rastrigin(Parameters.DIMENSION);
	
	double bestFitness = 0;
	
	for (int i = 0; i < Parameters.SIMULATION_NUMBER; i++) {
	    MyFSS_Padrao fss = new MyFSS_Padrao();
	    fss.init(p);
	    for (int j = 0; j < Parameters.ITERATION_NUMBER; j++) {
		fss.iterate();
	    }
	    bestFitness += fss.getBestFitness();
	    System.out.println("simulacao "+i+" bestFitness = "+fss.getBestFitness());
	}
	System.out.println("soma bestFitness = "+bestFitness);
	bestFitness = bestFitness/(double)Parameters.SIMULATION_NUMBER;
	System.out.println("Media do melhor fitness das "+Parameters.SIMULATION_NUMBER+
		"simulacoes = "+bestFitness);
    }
}
