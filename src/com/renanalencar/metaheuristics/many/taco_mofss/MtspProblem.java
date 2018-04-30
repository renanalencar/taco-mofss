package com.renanalencar.metaheuristics.many.taco_mofss;

/**
 * @author: renanalencar
 * @version: 1.0
 * @date: 2018-03-15
 */

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
public class MtspProblem extends Problem implements ControlExperiment {
    private static final int K = 3;

    private IOSource iosource_;

    /**
     * Constructor.
     * Creates a default instance of the MTSP problem.
     * @param solutionType The solution type must "Real", "BinaryReal, and "ArrayReal".
     */
    public MtspProblem(String solutionType) throws ClassNotFoundException {
        this(solutionType, 2);
    } // MtspProblem

    public MtspProblem(String solutionType, Integer numberOfObjectives) throws ClassNotFoundException {
        this(solutionType, numberOfObjectives+K-1, numberOfObjectives);
    } // MtspProblem

    /**
     * Constructor.
     * Creates a new instance of the MTSP problem.
     * @param solutionType The solution type must "Real", "BinaryReal, and "ArrayReal".
     * @param numberOfVariables Number of variables
     * @throws ClassNotFoundException
     */
    public MtspProblem(String solutionType, Integer numberOfVariables, Integer numberOfObjectives) throws ClassNotFoundException {
        numberOfVariables_   = numberOfVariables.intValue() ;
        numberOfObjectives_  = numberOfObjectives.intValue();
        numberOfConstraints_ = 0                            ;
        problemName_         = "MtspProblem"                ;

        upperLimit_ = new double[numberOfVariables_] ;
        lowerLimit_ = new double[numberOfVariables_] ;

        for (int i = 0; i < numberOfVariables_; i++) {
            lowerLimit_[i] = 0.0 ;
            upperLimit_[i] = 1.0  ;
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
    } // MtspProblem

    /**
     * Evaluates a solution
     * @param solution The solution to evaluate
     * @throws JMException
     */
    @Override
    public void evaluate(Solution solution) throws JMException {

        Variable[] gen  = solution.getDecisionVariables();

        double [] x = new double[numberOfVariables_]; // numberOfVariables_ = 4 (ALFA, BETA, KSI, RO)
        double [] f = new double[numberOfObjectives_];

        for (int i = 0; i < numberOfVariables_; i++) {
            x[i] = gen[i].getValue();
        }

        iosource_.variables_ = new double[numberOfVariables_];
        iosource_.variables_ = x.clone();

        iosource_.objectives_ = new double[numberOfObjectives_];


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
                log.flushFilesStandardExperiment();
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
                log.flushFilesRealExperiment();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < numberOfObjectives_; i++)
            solution.setObjective(i,iosource_.objectives_[i]);

    } // evaluate

    public String toString() {
        return "MtspProblem";
    }

} // MtspProblem
