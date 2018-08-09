//  PSO_main.java
//
//  Author:
//       Antonio J. Nebro <antonio@lcc.uma.es>
//       Juan J. Durillo <durillo@lcc.uma.es>
//
//  Copyright (c) 2011 Antonio J. Nebro, Juan J. Durillo
//
//  This program is free software: you can redistribute it and/or modify
//  it under the terms of the GNU Lesser General Public License as published by
//  the Free Software Foundation, either version 3 of the License, or
//  (at your option) any later version.
//
//  This program is distributed in the hope that it will be useful,
//  but WITHOUT ANY WARRANTY; without even the implied warranty of
//  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//  GNU Lesser General Public License for more details.
// 
//  You should have received a copy of the GNU Lesser General Public License
//  along with this program.  If not, see <http://www.gnu.org/licenses/>.

package com.renanalencar.experiments;

import com.renanalencar.experiments.settings.PSO_Settings;
import com.renanalencar.metaheuristics.taco_modified.ControlExperiment;
import com.renanalencar.metaheuristics.taco_modified.IOSource;
import com.renanalencar.problems.MTSP_JM;
import hidra.qualityIndicator.QualityIndicator;
import jmetal.core.Algorithm;
import jmetal.core.Problem;
import jmetal.core.SolutionSet;
import jmetal.metaheuristics.singleObjective.particleSwarmOptimization.PSO;
import jmetal.operators.mutation.Mutation;
import jmetal.operators.mutation.MutationFactory;
import jmetal.util.Configuration;
import jmetal.util.JMException;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

/**
 * Class for configuring and running a single-objective PSO algorithm
 */
public class PSO_main implements ControlExperiment, PSO_Settings {
    public static Logger logger_;      // Logger object
    public static FileHandler fileHandler_; // FileHandler object

    /**
     * @param args Command line arguments. The first (optional) argument specifies
     *             the problem to solve.
     * @throws JMException
     * @throws IOException
     * @throws SecurityException Usage: three options
     *                           - jmetal.metaheuristics.mocell.MOCell_main
     *                           - jmetal.metaheuristics.mocell.MOCell_main problemName
     *                           - jmetal.metaheuristics.mocell.MOCell_main problemName ParetoFrontFile
     */
    public static void main(String[] args) throws JMException, IOException, ClassNotFoundException {
        IOSource iosource = IOSource.getInstance();

        iosource.independent_run = 0;

        Problem problem;  // The problem to solve
        Algorithm algorithm;  // The algorithm to use
        Mutation mutation;  // "Turbulence" operator

        QualityIndicator indicators; // Object to get quality indicators

        HashMap parameters; // Operator parameters

        while (iosource.independent_run < N_SIMULATIONS) {
            // Logger object and file to store log messages
            logger_ = Configuration.logger_;
//        fileHandler_ = new FileHandler("log/PSO_main.log");
            fileHandler_ = new FileHandler("log/PSO_main-" + (iosource.independent_run + 1) + ".log");
            logger_.addHandler(fileHandler_);

            problem = new MTSP_JM("Real", N_VARIABLES, N_OBJECTIVES);

            algorithm = new PSO(problem);

            // Algorithm parameters
            algorithm.setInputParameter("swarmSize", SWARM_SIZE);
            algorithm.setInputParameter("maxIterations", MAX_INTERATIONS);

            parameters = new HashMap();
            parameters.put("probability", 1.0 / problem.getNumberOfVariables());
            parameters.put("distributionIndes", 20.0);
            mutation = MutationFactory.getMutationOperator(MUTATION_OPERATOR, parameters);

            algorithm.addOperator("mutation", mutation);

            // Execute the Algorithm
            long initTime = System.currentTimeMillis();
            SolutionSet population = algorithm.execute();
            long estimatedTime = System.currentTimeMillis() - initTime;

            // Result messages
            logger_.info("Total execution time: " + estimatedTime + "ms");
            logger_.info("Objectives values have been writen to file FUN");
//    population.printObjectivesToFile("FUN");
//            population.printObjectivesToFile("results/FUN" + "-PSO-" + problem.getName());
            population.printObjectivesToFile("results/FUN" + "-PSO-" + problem.getName() + "-" + (iosource.independent_run+1));
            logger_.info("Variables values have been writen to file VAR");
//    population.printVariablesToFile("VAR");
//            population.printVariablesToFile("results/VAR" + "-PSO-" + problem.getName());
            population.printVariablesToFile("results/VAR" + "-PSO-" + problem.getName() + "-" + (iosource.independent_run+1));

            iosource.independent_run++;
        }
    } //main
} // PSO_main
