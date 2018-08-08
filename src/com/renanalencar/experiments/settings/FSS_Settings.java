package com.renanalencar.experiments.settings;

/**
 * @author renanalencar
 * @version 1.0
 * @since 2017-11-01
 *
 */
public interface FSS_Settings {

    // Parametros para configuração do TACO + FSS
    int MIN_VAR             = 0; // 0: minimizar o custo total; 1: minimizar o custo máximo

    // Experiment parameters
    int SIMULATION_NUMBER   = 30;
    int ITERATION_NUMBER    = 1000;

    // Algorithm parameters
    int SCHOOL_SIZE         = 30;
    int MIN_WEIGHT          = 1;
    int MAX_WEIGHT          = 5000;
    int DIMENSION           = 4;
    double STEP_IND_INIT    = 0.1;
    double STEP_IND_FINAL   = 0.00001;
    boolean DEBUG           = false;

} // FSS_Settings
