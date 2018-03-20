package com.renanalencar.metaheuristics.many.taco_mofss;

import com.renanalencar.metaheuristics.mono.taco.ControlExperiment;
import com.renanalencar.metaheuristics.mono.taco.LogExperiment;
import com.renanalencar.metaheuristics.mono.taco.RealExperiment;
import com.renanalencar.metaheuristics.mono.taco.StandardExperiment;

import java.io.IOException;

/**
 * @author renanalencar
 * @version 1.0
 * @since 2017-11-01
 *
 */
public class TACO_main implements ControlExperiment {

    public static void main(String[] args) throws IOException {
    	
    	LogExperiment log = LogExperiment.getInstance();

        if (MODE_EXECUTION == 1) { // experimento padrão
        	log.loadBuffersStandardExperiment();
            StandardExperiment se = new StandardExperiment();
            se.run_standard_experiment();  // sem uma instância como parâmetro é carregada a instância modelo defida em control.cpp
            log.flushFilesStandardExperiment();

        } else { // experimento com dados reais
        	log.loaBuffersdRealExperiment();
            RealExperiment re = new RealExperiment();
            re.run_real_experiment();
            
            log.flushFilesRealExperiment();
        }

    }
}