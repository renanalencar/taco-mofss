package com.renanalencar.metaheuristics.many.taco_mofss;

import java.io.IOException;

/**
 * @author renanalencar
 * @version 1.0
 * @since 2017-11-01
 *
 */
public class TACO_main implements ControlExperiment, ControlSTACS {

    public static void main(String[] args) throws IOException {
    	
    	LogExperiment log = LogExperiment.getInstance();

    	IOSource iosource_ = IOSource.getInstance();

        iosource_.variables_    = new double[N_VARIABLES];
    	iosource_.variables_[0] = ALFA;
        iosource_.variables_[1] = BETA;
        iosource_.variables_[2] = KSI;
        iosource_.variables_[3] = RO;

        iosource_.objectives_   = new double[N_OBJECTIVES];

        iosource_.total_cost_r = new double[N_SIMULATIONS_BY_DAY];
        iosource_.max_cost_r = new double[N_SIMULATIONS_BY_DAY];

        iosource_.total_cost_w = new double[N_SIMULATIONS_BY_DAY];
        iosource_.max_cost_w = new double[N_SIMULATIONS_BY_DAY];


        if (MODE_EXECUTION == 1) { // experimento padrão
        	log.loadBuffersStandardExperiment();
            StandardExperiment se = new StandardExperiment();
            se.run_standard_experiment();  // sem uma instância como parâmetro é carregada a instância modelo defida em control.cpp
            log.closeFilesStandardExperiment();

        } else { // experimento com dados reais
        	log.loaBuffersdRealExperiment();
            RealExperiment re = new RealExperiment();
            re.run_real_experiment();
            
            log.closeFilesRealExperiment();
        }

    }



} // TACO_main
