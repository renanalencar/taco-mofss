package com.renanalencar.problems;

import com.renanalencar.metaheuristics.taco_modified.*;
import upe.poli.ecomp.problem.Problem;

import java.io.IOException;

public class MTSP_FSS extends Problem implements ControlExperiment, ControlFSS {

    private IOSource iosource_;

	public MTSP_FSS(int dimensions) {
		super(dimensions);
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
//		double sumSquare = 0.0;
//		for (int i = 0; i < solution.length; i++) {
//			sumSquare += (Math.pow((solution[i] + 30), 2.0));
//		}
//		return sumSquare;

        int numberOfVariables_ = 4;
        int numberOfObjectives_ = 2;

        try {
            iosource_ = IOSource.getInstance();
        } catch (IOException e) {
            e.printStackTrace();
        }

        iosource_.variables_ = new double[numberOfVariables_];
        iosource_.variables_ = solution.clone();

        //TODO Corrigir o problema NaN gerado pelo FSS
//        for (int i = 0; i < iosource_.variables_.length; i++) {
//            if (Math.abs(iosource_.variables_[i]) < TOLERANCE || Math.abs(iosource_.variables_[i]) == Double.POSITIVE_INFINITY)  // TOLERANCE = 0.00001
//                iosource_.variables_[i] = TOLERANCE;
//        }

        System.out.print("alfa: " + iosource_.variables_[0]);
        System.out.print("\tbeta: " + iosource_.variables_[1]);
        System.out.print("\tksi: " + iosource_.variables_[2]);
        System.out.println("\tro: " + iosource_.variables_[3]);

        iosource_.objectives_ = new double[numberOfObjectives_];

        iosource_.total_cost_r = new double[N_SIMULATIONS_BY_DAY];
        iosource_.max_cost_r = new double[N_SIMULATIONS_BY_DAY];

        iosource_.total_cost_w = new double[N_SIMULATIONS_BY_DAY];
        iosource_.max_cost_w = new double[N_SIMULATIONS_BY_DAY];

		double standard_deviation = 0.0;
		double max_cost = 0.0;
		double r = 0.0;

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
                se.run_standard_experiment();  // sem uma instância como parâmetro é carregada a instância modelo defida em control.cpp
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

        standard_deviation = iosource_.objectives_[0];
        max_cost = iosource_.objectives_[1];

        if (MIN_VAR == 0)
            r = standard_deviation;
        else
            r = max_cost;

        return r;

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
