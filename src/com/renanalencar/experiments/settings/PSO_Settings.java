package com.renanalencar.experiments.settings;

/**
 * @author renanalencar
 * @version 1.0
 * @since 2018-07-30
 *
 */
public interface PSO_Settings {


    // Default problem
    int DEFAULT_PROBLEM         = 1;
    int N_SIMULATIONS           = 30;

    // Algorithm parameters
    int SWARM_SIZE              = 30;      // Tamanho do cardume
    int MAX_INTERATIONS         = 1000;    // Quantidade de iterações. Default: 2500



    // Mutation and Crossover for Real codification
    String CROSSOVER_OPERATOR   = "SBXCrossover";       // ("SBXCrossover", "SinglePointCrossover", "PMXCrossover", "TwoPointsCrossover", "HUXCrossover", "DifferentialEvolutionCrossover")
    String MUTATION_OPERATOR    = "PolynomialMutation"; // ("PolynomialMutation", "BitFlipMutation", "SwapMutation")
    String SELECTION_OPERATOR   = "BinaryTournament2";  // Seleção de líderes do arquivo externo (AE) ("BinaryTournament", "BinaryTournament2", "PESA2Selection", "RandomSelection", "RankingAndCrowdingSelection", "DifferentialEvolutionSelection")

}
