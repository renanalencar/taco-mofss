package com.renanalencar.metaheuristics.many.taco_mofss;

/**
 * @author renanalencar
 * @version 1.0
 * @since 2017-11-01
 *
 */
public interface ControlFSS {

    // Parametros para configuração do TACO + FSS
    int MIN_VAR = 1; // 0: minimizar o desvio padrão; 1: minimizar o custo máximo

    int N_ITERACTIONS = 1000;
    int N_SIMULATIONS = 30;

    double TOLERANCE = 0.00001;
} // ControlFSS
