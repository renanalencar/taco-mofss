package com.renanalencar.experiments;

import com.renanalencar.experiments.settings.FSS_Settings;
import com.renanalencar.problems.MTSP_FSS;
import upe.poli.ecomp.algorithm.LogFSS;
import upe.poli.ecomp.algorithm.MyFSS_Padrao;
import upe.poli.ecomp.algorithm.Parameters;
import upe.poli.ecomp.problem.Problem;

import java.io.IOException;

public class FSS_Main implements FSS_Settings {
    
    public static void main(String[] args) throws IOException {
	Parameters.SCHOOL_SIZE = 30;
	Parameters.SIMULATION_NUMBER = 30;
	Parameters.ITERATION_NUMBER = 1000;
	Parameters.DIMENSION = 4;
	Parameters.STEP_IND_INIT = 0.1;
	Parameters.STEP_IND_FINAL = 0.00001;
	Parameters.DEBUG = false;
	
	Problem p = new MTSP_FSS(Parameters.DIMENSION);
	
	double bestFitness = 0;

	LogFSS log = LogFSS.getInstance();
	log.loadBuffersdRealExperiment();

	log.fss_simul_res.write("FSS Simulation Resume:\n");
	log.fss_simul_res.write("School Size: " + Parameters.SCHOOL_SIZE + "\n");
	log.fss_simul_res.write("Simulation: " + Parameters.SIMULATION_NUMBER + "\n");
	log.fss_simul_res.write("Iteration: " + Parameters.ITERATION_NUMBER + "\n");
	log.fss_simul_res.write("Dimension: " + Parameters.DIMENSION + "\n");
	log.fss_simul_res.write("Step Individual - Initial: " + Parameters.STEP_IND_INIT + "\n");
	log.fss_simul_res.write("Step Individual - Final: " + Parameters.STEP_IND_FINAL + "\n");

	for (int i = 0; i < Parameters.SIMULATION_NUMBER; i++) {
		System.out.println("Simulation #" + (i+1) + "\n");
		MyFSS_Padrao fss = new MyFSS_Padrao();
		fss.init(p);

		for (int j = 0; j < Parameters.ITERATION_NUMBER; j++) {
			System.out.println("Iteration #" + (j+1) + "\n");
			fss.iterate();
		}

		bestFitness += fss.getBestFitness();
		System.out.println("simulacao "+i+" bestFitness = "+fss.getBestFitness());
		log.fss_simul_res.write("Simulação "+ (i+1) + "\tMelhor fitness: " + fss.getBestFitness() + "\n");

	}

	System.out.println("soma bestFitness = "+bestFitness);
	bestFitness = bestFitness/(double)Parameters.SIMULATION_NUMBER;
	System.out.println("Media do melhor fitness das "+Parameters.SIMULATION_NUMBER+
		"simulacoes = "+bestFitness);

	log.closeFilesRealExperiment();
    }
}
