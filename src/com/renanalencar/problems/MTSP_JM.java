package com.renanalencar.problems;

/**
 * @author: renanalencar
 * @version: 1.0
 * @date: 2018-03-15
 */

import com.renanalencar.metaheuristics.taco_modified.*;
import jmetal.core.Problem;
import jmetal.core.Solution;
import jmetal.core.Variable;
import jmetal.encodings.solutionType.ArrayRealSolutionType;
import jmetal.encodings.solutionType.BinaryRealSolutionType;
import jmetal.encodings.solutionType.RealSolutionType;
import jmetal.util.JMException;

import java.io.IOException;

/**
 * Class representing problem MTSP
 */
public class MTSP_JM extends Problem implements ControlExperiment {
//    private static final int K = 3;

    private IOSource iosource_;

    /**
     * Constructor.
     * Creates a default instance of the MTSP problem.
     * @param solutionType The solution type must "Real", "BinaryReal, and "ArrayReal".
     */
//    public MTSP_JM(String solutionType) throws ClassNotFoundException {
//        this(solutionType, 2);
//    } // MTSP_JM

    /**
     *
     * @param solutionType
     * @param numberOfObjectives
     * @throws ClassNotFoundException
     */
//    public MTSP_JM(String solutionType, Integer numberOfObjectives) throws ClassNotFoundException {
//        this(solutionType, numberOfObjectives+K-1, numberOfObjectives);
//    } // MTSP_JM

    /**
     * Constructor.
     * Creates a new instance of the MTSP problem.
     * @param solutionType The solution type must "Real", "BinaryReal, and "ArrayReal".
     * @param numberOfVariables Number of variables
     * @throws ClassNotFoundException
     */
    public MTSP_JM(String solutionType, Integer numberOfVariables, Integer numberOfObjectives) throws ClassNotFoundException {
        numberOfVariables_   = numberOfVariables.intValue() ;
        numberOfObjectives_  = numberOfObjectives.intValue();
        numberOfConstraints_ = 0                            ;
        problemName_         = "MTSP"                       ;

        upperLimit_ = new double[numberOfVariables_] ;
        lowerLimit_ = new double[numberOfVariables_] ;

        for (int i = 0; i < numberOfVariables_; i++) {
            lowerLimit_[i] = 0.0 ;
            upperLimit_[i] = 2.0  ;
        } // for

        if (solutionType.compareTo("BinaryReal") == 0)
            solutionType_ = new BinaryRealSolutionType(this) ;
        else if (solutionType.compareTo("Real") == 0)
            solutionType_ = new RealSolutionType(this) ;
        else if (solutionType.compareTo("ArrayReal") == 0)
            solutionType_ = new ArrayRealSolutionType(this) ;
        else {
            System.out.println("Error: solution type " + solutionType + " invalid") ;
            System.exit(-1) ;
        }

        try {
            this.iosource_ = IOSource.getInstance();
        } catch (IOException e) {
            e.printStackTrace();
        }

    } // MTSP_JM

    /**
     * Evaluates a solution
     * @param solution The solution to evaluate
     * @throws JMException
     */
    @Override
    public void evaluate(Solution solution) throws JMException {

        Variable[] gen  = solution.getDecisionVariables();

        double [] x = new double[numberOfVariables_];
        double [] f = new double[numberOfObjectives_];

        for (int i = 0; i < numberOfVariables_; i++) {
            x[i] = gen[i].getValue();
        }

        // salva as variáveis geradas
        this.iosource_.variables_ = new double[numberOfVariables_];
        this.iosource_.variables_ = x.clone();

        // salva os objetivos gerado
        this.iosource_.objectives_ = new double[N_OBJECTIVES];

        // salva os valores dos resultados de cada simulação por dia
        this.iosource_.total_cost_r = new double[N_SIMULATIONS_BY_DAY];
        this.iosource_.max_cost_r   = new double[N_SIMULATIONS_BY_DAY];

        this.iosource_.total_cost_w = new double[N_SIMULATIONS_BY_DAY];
        this.iosource_.max_cost_w   = new double[N_SIMULATIONS_BY_DAY];


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

//            try {
//                log.f_resume_optimizer.write(iosource_.objectives_[0] + "," + iosource_.objectives_[1] + "\n");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }

            try {
                log.closeFilesRealExperiment();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // retorna o(s) objetivo(s) minimizado(s) escolhido(s) nas configurações ControlExperiment.java
        if (OBJECTIVE_MODE == 1) { // minimização multi-objetivo
            for (int i = 0; i < numberOfObjectives_; i++)
                solution.setObjective(i,iosource_.objectives_[i]);
        } else { // minimização mono-objetivo
            if (MINIMIZATION_TYPE == 0) {
                solution.setObjective(0, iosource_.objectives_[0]); // minimizar o custo total
            } else {
                solution.setObjective(0, iosource_.objectives_[1]); // minimizar o maximo custo
            }
        }

    } // evaluate

    public String toString() {
        return "MTSP";
    }

} // MTSP_JM
