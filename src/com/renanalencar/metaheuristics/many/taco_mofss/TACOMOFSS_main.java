package com.renanalencar.metaheuristics.many.taco_mofss;

import hidra.gui.MOPSOCDRSJFrame;
import hidra.many.metaheuristics.mofssv1.MOFSSv9s1s2;
import hidra.qualityIndicator.QualityIndicator;
import jmetal.core.Algorithm;
import jmetal.core.Operator;
import jmetal.core.Problem;
import jmetal.core.SolutionSet;
import jmetal.operators.crossover.CrossoverFactory;
import jmetal.operators.mutation.MutationFactory;
import jmetal.operators.selection.SelectionFactory;
import jmetal.problems.DTLZ.DTLZ1;
import jmetal.problems.ProblemFactory;
import jmetal.util.Configuration;
import jmetal.util.JMException;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

/**
 * @author: renanalencar
 * @version: 1.0
 * @date: 2018-03-15
 */
public class TACOMOFSS_main implements ControlExperiment, ControlMOFSS {
    public static Logger logger_ ;      // Logger object
    public static FileHandler fileHandler_ ; // FileHandler object

    /**
     * @param args Command line arguments.
     * @throws JMException
     * @throws IOException
     * @throws SecurityException
     * Usage: three options
     *      - jmetal.metaheuristics.nsgaII.NSGAII_main
     *      - jmetal.metaheuristics.nsgaII.NSGAII_main problemName
     *      - jmetal.metaheuristics.nsgaII.NSGAII_main problemName paretoFrontFile
     */
    public static void main(String [] args) throws
            JMException,
            SecurityException,
            IOException,
            ClassNotFoundException {
        Problem problem   ; // The problem to solve
        Algorithm algorithm ; // The algorithm to use
        Operator crossover ; // Crossover operator
        Operator mutation  ; // Mutation operator
        Operator selection ; // Selection operator

        HashMap parameters ; // Operator parameters

        QualityIndicator indicators ; // Object to get quality indicators

        // Logger object and file to store log messages
        logger_      = Configuration.logger_ ;
        fileHandler_ = new FileHandler("log/TACOMOFSS_main.log");
        logger_.addHandler(fileHandler_) ;

        indicators = null ;
        if (args.length == 1) {
            Object [] params = {"Real"};
            problem = (new ProblemFactory()).getProblem(args[0],params);
        } // if
        else if (args.length == 2) {
            Object [] params = {"Real"};
            problem = (new ProblemFactory()).getProblem(args[0],params);
            indicators = new QualityIndicator(problem, args[1]) ;
        } // if
        else { // Default problem
            //problem = new Kursawe("Real", 3);
            //problem = new Water("Real");
            //problem = new ZDT3("Real", 2);
            //problem = new ZDT4("BinaryReal");
            //problem = new WFG1("Real");
            //problem = new DTLZ1("Real", 2);
            //problem = new OKA2("Real") ;
            //problem = new ConstrEx("Real");
            //problem = new Fonseca("Real");
            //problem = new Schaffer("Real");
            problem = new MtspProblem("Real", N_OBJECTIVES);
        } // else

        /*
         *
         *  JMetal Pareto Front Problems: http://jmetal.sourceforge.net/problems.html
         *
         *
         */

        algorithm = new MOFSSv9s1s2(problem);
//        String paretoFrontFile = new String("paretofront/MTSP.2D.pf");

//        indicators = new QualityIndicator(problem, paretoFrontFile);

        // Algorithm parameters
        algorithm.setInputParameter("swarmSize",SWARM_SIZE);
        algorithm.setInputParameter("archiveSize",ARCHIVE_SIZE);
        algorithm.setInputParameter("maxIterations",MAX_INTERATIONS);

        // Mutation and Crossover for Real codification
        parameters = new HashMap() ;
        parameters.put("probability", 0.9) ;
        parameters.put("distributionIndex", 20.0) ;
        crossover = CrossoverFactory.getCrossoverOperator(CROSSOVER_OPERATOR, parameters);

        parameters = new HashMap() ;
        parameters.put("probability", 1.0/problem.getNumberOfVariables()) ;
        parameters.put("distributionIndex", 20.0) ;
        mutation = MutationFactory.getMutationOperator(MUTATION_OPERATOR, parameters);

        // Selection Operator
        parameters = null ;
        selection = SelectionFactory.getSelectionOperator(SELECTION_OPERATOR, parameters) ;

        // Add the operators to the algorithm
        algorithm.addOperator("crossover",crossover);
        algorithm.addOperator("mutation",mutation);
        algorithm.addOperator("selection",selection);

        // Add the indicator object to the algorithm
        algorithm.setInputParameter("indicators", indicators) ;

        //graphics
        MOPSOCDRSJFrame frameMopso_CDR = new MOPSOCDRSJFrame( (MOFSSv9s1s2) algorithm) ;
        frameMopso_CDR.setTitle("MOFSS (" + problem.getName() + ")");
        frameMopso_CDR.setVisible(true);

        // Execute the Algorithm
        long initTime = System.currentTimeMillis();
        SolutionSet population = algorithm.execute();
        long estimatedTime = System.currentTimeMillis() - initTime;

        // Result messages
        logger_.info("Total execution time: "+estimatedTime + "ms");
        logger_.info("Variables values have been written to file VAR"/*+"-MOFSS-"+problem.getName()*/);
        population.printVariablesToFile("results/VAR"+"-TACOMOFSS-"+problem.getName()/*+"-"+
    								algorithm.getInputParameter("swarmSize").toString()+"|"+
    								algorithm.getInputParameter("maxIterations").toString()*/);
        logger_.info("Objectives values have been written to file FUN"/*+"-MOFSS-"+problem.getName()*/);
        population.printObjectivesToFile("results/FUN"+"-TACOMOFSS-"+problem.getName()/*+"-"+
									algorithm.getInputParameter("swarmSize").toString()+"|"+
									algorithm.getInputParameter("maxIterations").toString()*/);

        String[] name = new String[5];
        double[] value = new double[5];
        name[0] = "Hypervolume";
        value[0] = indicators.getHypervolume(population);
        name[1] = "Spread";
        value[1] = indicators.getSpread(population);
        name[2] = "Spacing";
        value[2] = indicators.getSpacing(population);
        name[3] = "Convergence";
        value[3] = indicators.getConvergenceMeasure(population, true);
        name[4] = "Hypervolume PF";
        value[4] = indicators.getTrueParetoFrontHypervolume();
        logger_.info("Quality indicators values have been writen to file IND");
        population.printQualityIndicatorToFile("results/IND"+"-MOFSS-"+problem.getName()+"-"+
                algorithm.getInputParameter("swarmSize").toString()+"|"+
                algorithm.getInputParameter("maxIterations").toString(), name, value);

/*        if (indicators != null) {
            logger_.info("Quality indicators") ;
            logger_.info("Hypervolume    : " + indicators.getHypervolume(population)) ;
            logger_.info("HypervolumePF  : " + indicators.getTrueParetoFrontHypervolume());
            logger_.info("GD             : " + indicators.getGD(population)) ;
            logger_.info("IGD            : " + indicators.getIGD(population)) ;
            logger_.info("Spread         : " + indicators.getSpread(population)) ;
            logger_.info("Epsilon        : " + indicators.getEpsilon(population)) ;
            logger_.info("Spacing        : " + indicators.getSpacing(population));

        }*/ // if

    } //main

} // TACOMOFSS
