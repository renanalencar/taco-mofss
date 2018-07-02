package upe.poli.tcc.algorithm;

import upe.poli.tcc.problem.PID;
import upe.poli.tcc.problem.Problem;

import java.util.Random;

/**
 * 
 * @author Luiz Vercosa
 *
 */
public class FishSchoolSearch {

	private Fish[] school;
	private Random rand;
	private Problem problem;
	private double[] bestPosition;
	private double[] bestFitnessVector;
	private double[] individualFitness;
	
	private double stepIndPercentage;
	private double stepVolPercentage;
	private int iterationsNumber;
	private boolean isMinimumOptimization;

	public FishSchoolSearch(Problem problem){
		rand = new Random();
		this.problem = problem;
		
		//check if the problem is optimized by a minimum (or maximum) solution
		//the first parameter is any smaller number then the second
		isMinimumOptimization = problem.isFitnessBetterThan(0, 1);
	}

	public void search(int iterationsNumber, int simulations){
		this.iterationsNumber = iterationsNumber;
		
		for (int i = 0; i < simulations; i++) {
			System.out.println("Searching...");
			initializeSchool();
			initializeBestPosition();
			stepIndPercentage = Parameters.STEP_IND_INIT;
			stepVolPercentage = Parameters.STEP_VOL_INIT;
			for (int j = 0; j < iterationsNumber; j++) {
				localSearch();
				setSchoolLocalSearchNewWeight();
				collectiveMovement();
				volitiveMovement();
				updateBestPosition();
				updateStepIndPercentage();
				updateStepVolPercentage();
			}
			System.out.println("best fitness = "+getBestFitness());
			
		}
	}

	public double searchAndReturn(int iterationsNumber, int simulationNumber){
		
		this.iterationsNumber = iterationsNumber;
		double bestFitnessAverage = 0;
		this.bestFitnessVector = new double[simulationNumber];
		
		System.out.println("Simulations have started");
		for (int s = 0; s < simulationNumber; s++) {
			System.out.println("Simulation # " + (s+1));
			initializeSchool();
			initializeBestPosition();
			stepIndPercentage = Parameters.STEP_IND_INIT;
			stepVolPercentage = Parameters.STEP_VOL_INIT;
			for (int i = 0; i < iterationsNumber; i++) {
				System.out.println("Iteration # " + (i+1));
				localSearch();
				setSchoolLocalSearchNewWeight();
				collectiveMovement();
				volitiveMovement();
				updateBestPosition();
				updateStepIndPercentage();
				updateStepVolPercentage();
				if(i == (iterationsNumber - 1) && Parameters.IS_MATLAB_FUNCTION){
					if(problem instanceof PID){
						individualFitness = ((PID)problem).returnIndividualFitness(bestPosition);						
					}
				}
			}	
			System.out.println("FSS - simulation "+(s+1)+" best fitness = "+getBestFitness());
			bestFitnessAverage += getBestFitness();
			bestFitnessVector[s] = getBestFitness();
			
		}
		bestFitnessAverage /= simulationNumber;
		return bestFitnessAverage;
	}
	
	public void initializeSchool(){

		school = new Fish[Parameters.POPULATION_SIZE];
		double position[];
		for (int i = 0; i < school.length; i++) {
			position = createRandomPosition(problem.getDimensions());
			school[i] = new Fish((double)(Parameters.MIN_WEIGHT + Parameters.MAX_WEIGHT)/2.0, 
					position);
		}
	}

	public double[] createRandomPosition(int dimensions){
		double position[] = new double[dimensions];
		for (int i = 0; i < position.length; i++) {
//			position[i] = problem.getUpperBound(i)/2 + (problem.getUpperBound(i) - 
//					problem.getUpperBound(i)/2)*rand.nextDouble();
			position[i] = problem.getLowerBound(i) + (problem.getUpperBound(i) - 
					problem.getLowerBound(i))*rand.nextDouble();
		}
		return position;
	}

	//individual greedy search
	public void localSearch(){

		double[] neighboorPosition;

		for (int i = 0; i < school.length; i++) {
			neighboorPosition = validatePosition(createNeighboorPosition(school[i].
					getCurrentPosition()));
			//fish that has improved the fitness in current iteration
			if(problem.isFitnessBetterThan(problem.evaluateSolution(neighboorPosition), 
					problem.evaluateSolution(school[i].getCurrentPosition()))){
				school[i].setImprovedFitness(true);
				school[i].setPreviousPosition(school[i].getCurrentPosition());
				school[i].setCurrentPosition(neighboorPosition);
				school[i].setIndividualDisplacement(calculateIndividualDisplacement(
						school[i].getPreviousPosition(), school[i].getCurrentPosition()));
			}else{
				//fish that has NOT improved the fitness in current iteration
				school[i].setImprovedFitness(false);
			}
		}
	}


	private double[] validatePosition(double position[]){

		for (int i = 0; i < position.length; i++) {
			if(position[i] > problem.getUpperBound(i)){
				position[i] = problem.getUpperBound(i);
			}else if(position[i] < problem.getLowerBound(i)){
				position[i] = problem.getLowerBound(i);
			}
		}
		return position;
	}

	public double[] createNeighboorPosition(double position[]){

		double neighboorPosition[] = new double[position.length];

		for (int i = 0; i < position.length; i++) {
			neighboorPosition[i] = position[i] + stepIndPercentage*(problem.getUpperBound(i) - 
					problem.getLowerBound(i))*createRandomNumberInRange(-1.0, 1.0);
		}
		return neighboorPosition;
	}

	public double[] calculateIndividualDisplacement(double previousPosition[],
			double currentPosition[]){

		double displacement[] = new double[problem.getDimensions()];

		for (int j = 0; j < problem.getDimensions(); j++) {
			displacement[j] = currentPosition[j] - 
					previousPosition[j];
		}
		return displacement;
	}

	public double createRandomNumberInRange(double min, double max){
		return min + ( max - min) * rand.nextDouble();
	}

	//local search requires a particular method, since just part of the school
	//is fed
	public void setSchoolLocalSearchNewWeight(){

		double greaterFitnessGain = calculateLocalSearchGreaterFitnessGain();
		double newWeight;
		double fitnessGain;

		for (int i = 0; i < school.length; i++) {
			if(school[i].isImprovedFitness()){
				fitnessGain = calculateFitnessGain(school[i]);
				newWeight = validateWeight(school[i].getWeight() + 
						fitnessGain/greaterFitnessGain);
				
				if(greaterFitnessGain == 0 && Parameters.DEBUG){
					System.out.println("greaterFitnessGain = "+greaterFitnessGain);
					
				}
				school[i].setWeightVariation(newWeight - school[i].getWeight());
				school[i].setWeight(newWeight);
			}
		}
	}

	//calculate greater fitness gain just among fishes that
	//have been successful in local search
	public double calculateLocalSearchGreaterFitnessGain(){

		double greaterFitnessGain = Double.MIN_VALUE;
		double fitnessGain;

		for (int i = 1; i < school.length; i++) {
			if(school[i].isImprovedFitness()){
				fitnessGain = calculateFitnessGain(school[i]);
				if(fitnessGain > greaterFitnessGain){
					greaterFitnessGain = fitnessGain;
				}
			}
		}
		return greaterFitnessGain;
	}

	public double calculateGreaterFitnessGain(){

		double greaterFitnessGain = Double.MIN_VALUE;
		double fitnessGain;

		for (int i = 1; i < school.length; i++) {
			fitnessGain = calculateFitnessGain(school[i]);
			if(fitnessGain > greaterFitnessGain){
				greaterFitnessGain = fitnessGain;
			}
		}
		if(greaterFitnessGain == 0 && Parameters.DEBUG){
			System.out.println("greaterFitnessGain = "+greaterFitnessGain);
		}
		return greaterFitnessGain;
	}

	private double validateWeight(double weight){
		if(weight < Parameters.MIN_WEIGHT){
			weight = Parameters.MIN_WEIGHT;
		}else if(weight > Parameters.MAX_WEIGHT){
			weight = Parameters.MAX_WEIGHT;
		}
		return weight;
	}


	public double calculateFitnessGain(Fish fish){

		double fitnessGain = problem.evaluateSolution(fish.getCurrentPosition()) -
				problem.evaluateSolution(fish.getPreviousPosition());
		if(isMinimumOptimization){
			//fitness gain should be positive when the current
			//solution is better then the previous and negative
			//otherwise
			fitnessGain *= -1;
		}
		return fitnessGain;

	}

	public void collectiveMovement(){

		double position[] = new double[problem.getDimensions()];
		double collectiveMovementSum[] = calculateCollectiveMovementSum();

		for (int i = 0; i < school.length; i++) {
			school[i].setPreviousPosition(school[i].getCurrentPosition());
			position = new double[problem.getDimensions()];
			for (int j = 0; j < problem.getDimensions(); j++) {
				position[j] = school[i].getCurrentPosition()[j] + collectiveMovementSum[j];
			}
			school[i].setCurrentPosition(validatePosition(position));
		}
	}

	public double[] calculateCollectiveMovementSum(){

		double collectiveMovement[] = new double[problem.getDimensions()];
		double schoolFitnessGain[] = schoolFitnessGain();
		double fitnessGainSum = calculateFitnessGainSum();
		double fitnessTimesIndividualDisplacement = 0;

		for (int i = 0; i < problem.getDimensions(); i++) {
			fitnessTimesIndividualDisplacement = 0;
			for (int j = 0; j < school.length; j++) {
				if(school[j].isImprovedFitness()){
					fitnessTimesIndividualDisplacement += schoolFitnessGain[j]*
							school[j].getIndividualDisplacement()[i];
				}
			}
			if(fitnessGainSum != 0){
				collectiveMovement[i] = fitnessTimesIndividualDisplacement/fitnessGainSum;
				
			}else{
				collectiveMovement[i] = 0;
			}
			if(fitnessGainSum == 0 && Parameters.DEBUG){
				System.out.println("fitnessGainSum = "+fitnessGainSum);
			}
		}
		return collectiveMovement;
	}

	private double[] schoolFitnessGain(){

		double schoolFitnessGain[] = new double[school.length];

		for (int i = 0; i < school.length; i++) {
			if(school[i].isImprovedFitness()){
				schoolFitnessGain[i] = calculateFitnessGain(school[i]);
			}
		}
		return schoolFitnessGain;
	}
	private double calculateFitnessGainSum(){

		double fitnessGainSum = 0;
		double schoolFitnessGain[] = schoolFitnessGain();

		for (int j = 0; j < schoolFitnessGain.length; j++) {
			if(school[j].isImprovedFitness()){
				fitnessGainSum += schoolFitnessGain[j];
			}
		}
		return fitnessGainSum;
	}

	public void volitiveMovement(){

		double position[] = new double[problem.getDimensions()];
		double barycenter[] = calculateBarycenter();
		double signal = calculateSignalFromSchoolWeightVariation();
		double distanceToBarycenter;

		for (int i = 0; i < school.length; i++) {
			position = new double[problem.getDimensions()];
			distanceToBarycenter = euclidianDistance(school[i].getCurrentPosition(), 
					barycenter);
			for (int j = 0; j < problem.getDimensions(); j++) {
				position[j] = school[i].getCurrentPosition()[j] + 
						signal*stepVolPercentage*(problem.getUpperBound(j) - 
								problem.getLowerBound(j))*((school[i].getCurrentPosition()[j] - 
										barycenter[j])/distanceToBarycenter) ;
			}
			school[i].setCurrentPosition(validatePosition(position));
		}
	}

	public double calculateSignalFromSchoolWeightVariation(){

		double weightVariation = 0;

		for (int i = 0; i < school.length; i++) {
			weightVariation += school[i].getWeightVariation();
		}
		return (weightVariation >= 0 ? -1.0 : 1.0);
	}

	private static double euclidianDistance(double[] a,double[] b){
		double sum=0;
		for(int i=0;i<a.length;i++){
			sum+=Math.pow(a[i]-b[i],2.0);
		}
		return Math.sqrt(sum);
	}

	public double[] calculateBarycenter(){

		double barycenter[] = new double[problem.getDimensions()];
		double weightSum = calculateWeightSum();
		double weightSumTimesPosition = 0;

		for (int i = 0; i < problem.getDimensions(); i++) {
			weightSumTimesPosition = 0;
			for (int j = 0; j < school.length; j++) {
				weightSumTimesPosition += school[j].getWeight()*
						school[j].getCurrentPosition()[i];
			}
			barycenter[i] = weightSumTimesPosition/weightSum;
		}
		return barycenter;
	}

	private double calculateWeightSum(){

		double weightSum = 0;

		for (int j = 0; j < school.length; j++) {
			weightSum += school[j].getWeight();
		}
		if(weightSum == 0 && Parameters.DEBUG){
			System.out.println("weightSum = "+weightSum);
		}
		return weightSum;
	}

	//it is possible to initialize fssII best position with any school position
	public void initializeBestPosition(){
        
        this.bestPosition = school[0].getCurrentPosition().clone();
    }
	
	public void updateBestPosition(){
		
		double bestPosition[] = school[0].getCurrentPosition();
		
		for (int i = 1; i < school.length; i++) {
			if(problem.isFitnessBetterThan(problem.evaluateSolution(school[i].
					getCurrentPosition()), problem.evaluateSolution(bestPosition))){
				bestPosition = school[i].getCurrentPosition();
			}
		}
		if(problem.isFitnessBetterThan(problem.evaluateSolution(bestPosition),
				problem.evaluateSolution(this.bestPosition))){
			this.bestPosition = bestPosition.clone();
		}
	}
	
	private void updateStepIndPercentage(){
		stepIndPercentage -= (double)(Parameters.STEP_IND_INIT - Parameters.STEP_IND_FINAL)/
				(double)iterationsNumber;
	}
	private void updateStepVolPercentage(){
		stepVolPercentage -= (double)(Parameters.STEP_VOL_INIT - Parameters.STEP_VOL_FINAL)/
				(double)iterationsNumber;
	}
	public Fish[] getSchool(){
		return school;
	}
	//for testing
	public void setSchool(Fish school[]){
		this.school = school;
	}

	public double getBestFitness() {
		return problem.evaluateSolution(bestPosition);
	}
	
	public Problem getProblem() {
		return problem;
	}
	
	public double[] getBestFitnessVector() {
		return bestFitnessVector;
	}
	public double[] getBestPosition() {
		return bestPosition;
	}
	public double[] getIndividualFitness(){
		return individualFitness;
	}
}
