package com.renanalencar.metaheuristics.many.taco_mofss;

public interface ControlMOFSS {

    // Default problem
    int DEFAULT_PROBLEM         = 1;

    // Algorithm parameters

    double MUTATION_RATE        = 0.3;      // Percentual de Mutação (M_RATE)
    double INITIAL_FITNESS      = 100.0;    // Peso inicial do peixe (Wi)
    double MIN_FITNESS          = 1.0;      // Mínimo para o peso do peixe (Wi_min)
    double MAX_FITNESS          = 1000.0;   // Máximo para o peso do peixe (Wi_max)
    double D_A                  = 1.0;      // Alimentação do peixe (dominância, a)
    double IND_B                = 0.1;      // Alimentação do peixe (indiferença, b)
    double ALPHA                = 0.001;    // Adaptação do passo volitivo (alpha)
    double MIN_STEP_IND         = 0.25;     // Passo Individual Inicial (Si)
    double MAX_STEP_IND         = 0.005;    // Passo Individual Final (Sf)
    int SWARM_SIZE              = 100;      // Tamanho do cardume
    int ARCHIVE_SIZE            = 100;      // Tamanho do arquivo externo (AE)
    int MAX_INTERATIONS         = 1000;       // Quantidade de iterações. Default: 2500



    // Mutation and Crossover for Real codification
    String CROSSOVER_OPERATOR   = "SBXCrossover";       // ("SBXCrossover", "SinglePointCrossover", "PMXCrossover", "TwoPointsCrossover", "HUXCrossover", "DifferentialEvolutionCrossover")
    String MUTATION_OPERATOR    = "PolynomialMutation"; // ("PolynomialMutation", "BitFlipMutation", "SwapMutation")
    String SELECTION_OPERATOR   = "BinaryTournament2";  // Seleção de líderes do arquivo externo (AE) ("BinaryTournament", "BinaryTournament2", "PESA2Selection", "RandomSelection", "RankingAndCrowdingSelection", "DifferentialEvolutionSelection")

}
