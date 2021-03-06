package com.renanalencar.problems;

import com.renanalencar.experiments.settings.FSS_Settings;
import com.renanalencar.metaheuristics.taco_modified.*;
import upe.poli.ecomp.problem.Problem;

import java.io.IOException;

public class MTSP_FSS extends Problem implements ControlExperiment, FSS_Settings {

    private IOSource iosource_;

	public MTSP_FSS(int dimensions) {
		super(dimensions);

        try {
            this.iosource_ = IOSource.getInstance();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

	@Override
	public void init() {
		// Do nothing
	}

	@Override
	public double getLowerBound(int dimension) {
		return 0.0;
	}

	@Override
	public double getUpperBound(int dimension) {
		return 2.0;
	}

	@Override
	public double evaluateSolution(double[] solution) {

        int numberOfVariables_ = 4;
        int numberOfObjectives_ = 1;

        iosource_.variables_ = new double[numberOfVariables_];
        iosource_.variables_ = solution.clone();


//        System.out.print("alfa: " + iosource_.variables_[0]);
//        System.out.print("\tbeta: " + iosource_.variables_[1]);
//        System.out.print("\tksi: " + iosource_.variables_[2]);
//        System.out.println("\tro: " + iosource_.variables_[3]);

        this.iosource_.objectives_  = new double[2];

        this.iosource_.total_cost_r = new double[N_SIMULATIONS_BY_DAY];
        this.iosource_.max_cost_r   = new double[N_SIMULATIONS_BY_DAY];

        this.iosource_.total_cost_w = new double[N_SIMULATIONS_BY_DAY];
        this.iosource_.max_cost_w   = new double[N_SIMULATIONS_BY_DAY];

		double result = 0.0;

		LogExperiment log = null;

        try {
            log = LogExperiment.getInstance();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (MODE_EXECUTION == 1) { // experimento padrão
            try {
                log.loadBuffersStandardExperiment();
            } catch (IOException e) {
                e.printStackTrace();
            }
            StandardExperiment se = null;
            try {
                se = new StandardExperiment();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                se.run_standard_experiment();  // sem uma instância como parâmetro é carregada a instância modelo definida em control.cpp
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                log.closeFilesStandardExperiment();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else { // experimento com dados reais
            try {
                log.loaBuffersdRealExperiment();
            } catch (IOException e) {
                e.printStackTrace();
            }
            RealExperiment re = null;
            try {
                re = new RealExperiment();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                re.run_real_experiment();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                log.closeFilesRealExperiment();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // retorna o objetivo minimizado escolhido nas configurações ControlExperiment.java
        if (MINIMIZATION_TYPE == 2)
            result = iosource_.objectives_[0]; // minizar o custo total
        else
            result = iosource_.objectives_[1]; // minimizar o custo máximo

        return result;

	}

	@Override
	public boolean isFitnessBetterThan(double currentSolutionFitness,double bestSolutionFitness) {

		return currentSolutionFitness < bestSolutionFitness;
	}

	@Override
	public String getName() {
		return "MTSP";
	}

}
