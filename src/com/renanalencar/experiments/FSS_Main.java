package com.renanalencar.experiments;

import com.renanalencar.experiments.settings.FSS_Settings;
import com.renanalencar.problems.MTSP_FSS;
import upe.poli.ecomp.algorithm.LogFSS;
import upe.poli.ecomp.algorithm.MyFSS_Padrao;
import upe.poli.ecomp.problem.Problem;

import java.io.IOException;

import static com.renanalencar.metaheuristics.taco_modified.ControlExperiment.MINIMIZATION_TYPE;

public class FSS_Main implements FSS_Settings {

    public static void main(String[] args) throws IOException {

        // Execute the Algorithm
        long initTime = System.currentTimeMillis();

        Problem p = new MTSP_FSS(DIMENSION);

        double bestFitness = 0;

        LogFSS log = LogFSS.getInstance();
        log.loadBuffersdRealExperiment();

        log.fss_simul_res.write("FSS Simulation Resume:\n");
        log.fss_simul_res.write("School Size: " + SCHOOL_SIZE + "\n");
        log.fss_simul_res.write("Simulation: " + SIMULATION_NUMBER + "\n");
        log.fss_simul_res.write("Iteration: " + ITERATION_NUMBER + "\n");
        log.fss_simul_res.write("Number of variables: " + DIMENSION + "\n");
        log.fss_simul_res.write("Step Individual - Initial: " + STEP_IND_INIT + "\n");
        log.fss_simul_res.write("Step Individual - Final: " + STEP_IND_FINAL + "\n");

        if (MINIMIZATION_TYPE == 0) {
            log.fss_simul_res.write("Minimization Type: Minimize the total cost\n");
        } else {
            log.fss_simul_res.write("FSS Simulation Resume: Minimize the longest route\n");
        }

        for (int i = 0; i < SIMULATION_NUMBER; i++) {
            MyFSS_Padrao fss = new MyFSS_Padrao();
            fss.init(p);

            for (int j = 0; j < ITERATION_NUMBER; j++) {
                System.out.println("Simulation #" + (i + 1)
                        + "\tIteration #" + (j + 1) + "\n");
                fss.iterate();
            }

            bestFitness += fss.getBestFitness();
            System.out.println("Simulação " + i + " bestFitness = " + fss.getBestFitness());
            log.fss_simul_res.write("Simulação " + (i + 1) + "\tMelhor fitness: " + fss.getBestFitness() + "\n");

        }

        System.out.println("Soma do bestFitness = " + bestFitness);
        bestFitness = bestFitness / (double) SIMULATION_NUMBER;
        System.out.println("Média do melhor fitness das " + SIMULATION_NUMBER +
                "simulações = " + bestFitness);

        // Result messages
        long estimatedTime = System.currentTimeMillis() - initTime;
        System.out.println("\nTempo total do experimento: " + estimatedTime);
        log.fss_simul_res.write("\nTempo total do experimento: " + estimatedTime);

        log.closeFilesRealExperiment();
    }
}
