package hidra.experiments.settings;

import hidra.experiments.GlobalSettings;
import hidra.experiments.util.HIDRASettings;
import hidra.many.metaheuristics.MDFA.MDFA;
import hidra.qualityIndicator.QualityIndicator;
import jmetal.core.Algorithm;
import jmetal.operators.crossover.Crossover;
import jmetal.operators.crossover.CrossoverFactory;
import jmetal.operators.mutation.Mutation;
import jmetal.operators.mutation.MutationFactory;
import jmetal.operators.selection.Selection;
import jmetal.problems.ProblemFactory;
import jmetal.util.JMException;

import java.util.HashMap;

public class HIDRA_MDFA_Settings extends HIDRASettings {

	
	
	  public int populationSize_                 ; 
	  public int maxIterations_                 ;
	  public double mutationProbability_         ;
	  public double crossoverProbability_        ;
	  public double mutationDistributionIndex_   ;
	  public double crossoverDistributionIndex_  ;
	  
	  /**
	   * Constructor
	   * @throws JMException
	   */
	  public HIDRA_MDFA_Settings(String problem) throws JMException {
	    super(problem) ;
	    
	    Object [] problemParams = {"Real"};
	    try {
		    problem_ = (new ProblemFactory()).getProblem(problemName_, problemParams);
	    } catch (JMException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
	    }  
	    // Default settings
	    populationSize_              = GlobalSettings.populationSizeWithoutArchive ; 
	    maxIterations_               = (GlobalSettings.maxEvaluations / GlobalSettings.populationSize) - 1;
	    mutationProbability_         = 1.0/problem_.getNumberOfVariables() ;
	    
	    crossoverProbability_        = GlobalSettings.crossoverProbability_  ;
	    mutationDistributionIndex_   = GlobalSettings.mutationDistributionIndex_ ;
	    crossoverDistributionIndex_  = GlobalSettings.crossoverDistributionIndex_ ;
	  } // PSO_Settings

	  
	  /**
	   * Configure NSGAII with user-defined parameter settings
	   * @return A NSGAII algorithm object
	   * @throws jmetal.util.JMException
	   */
	  public Algorithm configure() throws JMException {
	    Algorithm algorithm ;
	    Selection  selection ;
	    Crossover  crossover ;
	    Mutation   mutation  ;

	    HashMap  parameters ; // Operator parameters

	    QualityIndicator indicators ;
	    
	    // Creating the algorithm. There are two choices: NSGAII and its steady-
	    // state variant ssNSGAII
	    algorithm = new MDFA(problem_) ;
	    //algorithm = new ssNSGAII(problem_) ;
	    
	    // Algorithm parameters
	    algorithm.setInputParameter("populationSize",populationSize_);
	    algorithm.setInputParameter("maxIterations",maxIterations_);

	    // Mutation and Crossover for Real codification
	    parameters = new HashMap() ;
	    parameters.put("probability", crossoverProbability_) ;
	    parameters.put("distributionIndex", crossoverDistributionIndex_) ;
	    crossover = CrossoverFactory.getCrossoverOperator("SBXCrossover", parameters);

	    parameters = new HashMap() ;
	    parameters.put("probability", mutationProbability_) ;
	    parameters.put("distributionIndex", mutationDistributionIndex_) ;
	    mutation = MutationFactory.getMutationOperator("PolynomialMutation", parameters);

	   
	    // Add the operators to the algorithm
	    algorithm.addOperator("crossover",crossover);
	    algorithm.addOperator("mutation",mutation);
	   
	    
	   // Creating the indicator object
	   if ((paretoFrontFile_!=null) && (!paretoFrontFile_.equals(""))) {
		   //System.out.println(paretoFrontFile_);
	      indicators = new QualityIndicator(problem_, paretoFrontFile_);      
	      algorithm.setInputParameter("indicators", indicators) ;  
	   } // if
	   
	    return algorithm ;
	  } // configure
	
	
	
	
	
	
	
	
}
