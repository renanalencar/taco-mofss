package upe.poli.ecomp.algorithm;

import upe.poli.ecomp.problem.Problem;

import java.util.Random;

public class MyFSS_Padrao {

	private MyFish[] school;
	private Random rand;
	private double[] bestPosition;
	private double[] bestFitnessVector;
	private double[] schoolWeight;
	private double stepIndPercentage;
	private double stepVolPercentage;
	private int iterationsNumber;
	private int iterationCounter;
	private boolean isMinimumOptimization;

	private double signal;
	
	private Problem problem = null;

	
	public void init(Problem problem) {

	    	this.problem = problem;
		rand = new Random();
		//first and only stop condition must be maximum iterations reached
		iterationsNumber = Parameters.ITERATION_NUMBER;
		initializeSchool();
		schoolWeight = new double[iterationsNumber];
		iterationCounter = 0;
		initializeBestPosition();
		stepIndPercentage = Parameters.STEP_IND_INIT;
		stepVolPercentage = 2*stepIndPercentage;
		isMinimumOptimization = getProblem().isFitnessBetterThan(0, 1);

		
	}

	public void iterate() {

		localSearch();
		feed();
		instinctiveMovement();
		volitiveMovement();
		//the following feeding is a variation from the original version
		//setSchoolNewWeight();
		updateBestPosition();
		updateStepIndPercentage();
		updateStepVolPercentage();
		calculateSchoolWeight();
		iterationCounter++;

	}

	public void initializeSchool(){

		school = new MyFish[Parameters.SCHOOL_SIZE];
		double position[];
		for (int i = 0; i < school.length; i++) {
			position = createRandomPosition(Parameters.DIMENSION);
			school[i] = new MyFish(1,position);
		}
	}

	public double[] createRandomPosition(int dimensions){
		double position[] = new double[dimensions];
		for (int i = 0; i < position.length; i++) {
//			position[i] = getProblem().getUpperBound(i)/2 + (getProblem().getUpperBound(i) - 
//					getProblem().getUpperBound(i)/2)*rand.nextDouble();
			position[i] = getProblem().getLowerBound(i) + (getProblem().getUpperBound(i) - 
					getProblem().getLowerBound(i))*rand.nextDouble();
		}
		return position;
	}

	//individual greedy search
	public void localSearch(){

		double[][] movDeltaSchool = new double[school.length][Parameters.DIMENSION];

		double[] neighboorPosition;
		double success = 0;

		double neighboorFitness = -1;
		double currentFitness = -1;
		
		for (int i = 0; i < school.length; i++) {
			neighboorPosition = validatePosition(createNeighboorPosition(school[i].
					getMyCurrentPosition()));

			neighboorFitness = getProblem().evaluateSolution(neighboorPosition);
			currentFitness = getProblem().evaluateSolution(school[i].getMyCurrentPosition());

			if(getProblem().isFitnessBetterThan(neighboorFitness, currentFitness)){
				success++;
				school[i].setImprovedFitness(true);
				school[i].setPreviousPosition(school[i].getMyCurrentPosition());
				school[i].setMyCurrentPosition(neighboorPosition);
				school[i].setIndividualDisplacement(calculateIndividualDisplacement(
						school[i].getPreviousPosition(), school[i].getMyCurrentPosition()));
				school[i].setCurrentFitness(neighboorFitness);
				school[i].setPreviousFitness(currentFitness);
			}else{
				//fish that has NOT improved the fitness in current iteration
				school[i].setWeightVariation(0);
				school[i].setImprovedFitness(false);
			}
		}
	}

	private int calcImprovedFish(){

		int count = 0;

		for (int i = 0; i < school.length; i++) {
			if(school[i].isImprovedFitness()){
				count++;
			}
		}
		return count;
	}

	private double[] validatePosition(double position[]){

		for (int i = 0; i < position.length; i++) {
			if(position[i] > getProblem().getUpperBound(i)){
				position[i] = getProblem().getUpperBound(i);
			}else if(position[i] < getProblem().getLowerBound(i)){
				position[i] = getProblem().getLowerBound(i);
			}
		}
		return position;
	}

	public double[] createNeighboorPosition(double position[]){

		double neighboorPosition[] = new double[position.length];

		for (int i = 0; i < position.length; i++) {
			neighboorPosition[i] = position[i] + stepIndPercentage*(getProblem().getUpperBound(i) - 
					getProblem().getLowerBound(i))*createRandomNumberInRange(-1.0, 1.0);
		}

		return neighboorPosition;
	}

	public double[] calculateIndividualDisplacement(double previousPosition[],
			double currentPosition[]){

		double displacement[] = new double[Parameters.DIMENSION];

		for (int j = 0; j < Parameters.DIMENSION; j++) {
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
	public void feed(){

		//		if(iterationCounter == 6500){
		//			System.out.println();
		//		}
		double newWeight;
		double fitnessGain;
		double greaterFitnessGain = calculateLocalSearchGreaterFitnessGain();
		double putOnWeight = 0;

		if(greaterFitnessGain != 0){

			for (int i = 0; i < school.length; i++) {
				if(school[i].isImprovedFitness()){
					fitnessGain = calculateFitnessGain(school[i]);

					putOnWeight = fitnessGain/greaterFitnessGain;

					newWeight = validateWeight(school[i].getWeight() + 
							putOnWeight);
					school[i].setWeightVariation(newWeight - school[i].getWeight());
					school[i].setWeight(newWeight);
				}else{
					school[i].setWeightVariation(0);
				}
			}
		}
	}
	
	public void setSchoolNewWeight(){

		double newWeight;
		double fitnessGain;
		double greaterFitnessGain = calculateGreaterFitnessGain();
		double fitnessFactor = 1;

		if(Math.abs(greaterFitnessGain) < 1){
			greaterFitnessGain = 1;
		}

		for (int i = 0; i < school.length; i++) {
			fitnessGain = calculateFitnessGain(school[i]);
			newWeight = validateWeight(school[i].getWeight() + 
					(fitnessGain/greaterFitnessGain)*fitnessFactor);
			//			if(Math.abs(newWeight - school[i].getWeight()) > 1){
			//				System.out.println("ganhou ou perdeu mais que um");
			//			}
			school[i].setWeightVariation(newWeight - school[i].getWeight());
			school[i].setWeight(newWeight);
		}
	}

	//calculate greater fitness gain just among fishes that
	//have been successful in local search
	public double calculateLocalSearchGreaterFitnessGain(){

		double greaterFitnessGain = 0;
		double fitnessGain;

		for (int i = 0; i < school.length; i++) {
			if(school[i].isImprovedFitness()){
				fitnessGain = calculateFitnessGain(school[i]);
				if(Math.abs(fitnessGain) > greaterFitnessGain){
					greaterFitnessGain = Math.abs(fitnessGain);
				}
			}
		}
		return greaterFitnessGain;
	}

	public double calculateGreaterFitnessGain(){

		double greaterFitnessGain = 0;
		double fitnessGain;

		for (int i = 0; i < school.length; i++) {
			fitnessGain = calculateFitnessGain(school[i]);
			if(Math.abs(fitnessGain) > greaterFitnessGain){
				greaterFitnessGain = Math.abs(fitnessGain);
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


	public double calculateFitnessGain(MyFish fish){

		double fitnessGain = fish.getCurrentFitness() - fish.getPreviousFitness();
		if(isMinimumOptimization){
			//fitness gain should be positive when the current
			//solution is better then the previous and negative
			//otherwise
			fitnessGain *= -1;
		}
		
		return fitnessGain;

	}

	public void instinctiveMovement(){

		double position[] = new double[Parameters.DIMENSION];
		double instinctiveMovementSum[] = calculateInstinctiveMovementSum();

		for (int i = 0; i < school.length; i++) {
			school[i].setPreviousPosition(school[i].getMyCurrentPosition());
			position = new double[Parameters.DIMENSION];
			for (int j = 0; j < Parameters.DIMENSION; j++) {
				position[j] = school[i].getMyCurrentPosition()[j] + instinctiveMovementSum[j];
			}
			school[i].setMyCurrentPosition(validatePosition(position));
		}
	}

	public double[] calculateInstinctiveMovementSum(){

		double instinctiveMovement[] = new double[Parameters.DIMENSION];
		double schoolFitnessGain[] = schoolFitnessGain();
		double fitnessGainSum = calculateFitnessGainSum();
		double fitnessTimesIndividualDisplacement = 0;


		for (int i = 0; i < Parameters.DIMENSION; i++) {
			fitnessTimesIndividualDisplacement = 0;
			for (int j = 0; j < school.length; j++) {
				if(school[j].isImprovedFitness()){
					fitnessTimesIndividualDisplacement += schoolFitnessGain[j]*
							school[j].getIndividualDisplacement()[i];
				}
			}
			if(fitnessGainSum != 0){
				instinctiveMovement[i] = 1*(fitnessTimesIndividualDisplacement/fitnessGainSum);

			}else{
				instinctiveMovement[i] = 0;
			}
			if(fitnessGainSum == 0 && Parameters.DEBUG){
				System.out.println("fitnessGainSum = "+fitnessGainSum);
			}
		}
		return instinctiveMovement;
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
	private double[] schoolFitnessGain(){

		double schoolFitnessGain[] = new double[school.length];

		for (int i = 0; i < school.length; i++) {
			if(school[i].isImprovedFitness()){
				schoolFitnessGain[i] = calculateFitnessGain(school[i]);
			}
		}
		return schoolFitnessGain;
	}

	public void volitiveMovement(){

		double position[] = new double[Parameters.DIMENSION];
		double barycenter[] = calculateBarycenter();
		double initialBarycenter[] = calculateInitialBarycenter();
		double signal = calculateSignalFromSchoolWeightVariation();
		this.signal = signal;
		
		double distanceToBarycenter;
		double volitive;
		double[][] volitiveTemp = new double[school.length][Parameters.DIMENSION];

		for (int i = 0; i < school.length; i++) {
			position = new double[Parameters.DIMENSION];
			distanceToBarycenter = euclidianDistance(school[i].getMyCurrentPosition(), 
					barycenter);

			for (int j = 0; j < Parameters.DIMENSION; j++) {

				volitive = signal*stepVolPercentage*rand.nextDouble()*(getProblem().getUpperBound(j) - 
						getProblem().getLowerBound(j))*((school[i].getMyCurrentPosition()[j] - 
								barycenter[j])/distanceToBarycenter);

				position[j] = school[i].getMyCurrentPosition()[j] + volitive;
			}
			school[i].setPositionBeforeVolitive(school[i].getMyCurrentPosition().clone());
			school[i].setMyCurrentPosition(validatePosition(position));
			
		}

				
	}

	public double calculateSignalFromSchoolWeightVariation(){

		double weightVariation = 0;

		for (int i = 0; i < school.length; i++) {
			weightVariation += school[i].getWeightVariation();
		}
		if(weightVariation > 0){
			return -1;
		}else{
			//System.out.println("foi outwards");
			return 1;
		}
	}

	private static double euclidianDistance(double[] a, double[] b){
		double sum=0;
		for(int i=0;i<a.length;i++){
			sum+=Math.pow(a[i]-b[i],2.0);
		}
		return Math.sqrt(sum);
	}

	public double[] calculateBarycenter(){

		double barycenter[] = new double[Parameters.DIMENSION];
		double weightSum = calculateWeightSum();
		double weightSumTimesPosition = 0;

		for (int i = 0; i < Parameters.DIMENSION; i++) {
			weightSumTimesPosition = 0;
			for (int j = 0; j < school.length; j++) {
				weightSumTimesPosition += school[j].getWeight()*
						school[j].getMyCurrentPosition()[i];
			}
			barycenter[i] = weightSumTimesPosition/weightSum;
		}
		return barycenter;
	}

	public double[] calculateInitialBarycenter(){

		double barycenter[] = new double[Parameters.DIMENSION];
		double weightSum = ((Parameters.MIN_WEIGHT + Parameters.MAX_WEIGHT)/2.0)*school.length;
		double weightSumTimesPosition = 0;

		for (int i = 0; i < Parameters.DIMENSION; i++) {
			weightSumTimesPosition = 0;
			for (int j = 0; j < school.length; j++) {
				weightSumTimesPosition += ((Parameters.MIN_WEIGHT + Parameters.MAX_WEIGHT)/2.0)*
						school[j].getMyCurrentPosition()[i];
			}
			barycenter[i] = weightSumTimesPosition/weightSum;
		}
		return barycenter;
	}

	public double calculateWeightSum(){

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

		this.bestPosition = school[0].getMyCurrentPosition().clone();

	}

	public void updateBestPosition(){

		double bestPosition[] = school[0].getMyCurrentPosition();

		for (int i = 1; i < school.length; i++) {
			if(getProblem().isFitnessBetterThan(getProblem().evaluateSolution(school[i].getMyCurrentPosition()),
				getProblem().evaluateSolution(bestPosition)
					)){
				bestPosition = school[i].getMyCurrentPosition();
			}
		}

		if(getProblem().isFitnessBetterThan(getProblem().evaluateSolution(bestPosition),
			getProblem().evaluateSolution(this.bestPosition)
				)){
			this.bestPosition = bestPosition.clone();
		}
	}

	private void updateStepIndPercentage(){
		
	    	stepIndPercentage -= (double)(Parameters.STEP_IND_INIT - Parameters.STEP_IND_FINAL)/
	    				(double)iterationsNumber;
			

	}

	private void updateStepVolPercentage(){
		
			stepVolPercentage = 2*stepIndPercentage;

		
	}

	public MyFish[] getSchool(){
		return school;
	}
	//for testing
	public void setSchool(MyFish school[]){
		this.school = school;
	}

	public double getBestFitness() {
		return getProblem().evaluateSolution(bestPosition);
	}

	public double[] getBestFitnessVector() {
		return bestFitnessVector;
	}

	private void calculateSchoolWeight(){

		for (int i = 0; i < school.length; i++) {
			schoolWeight[iterationCounter] += school[i].getWeight();
		}
		//		if(iterationCounter > 0){
		//			if(schoolWeight[iterationCounter] < schoolWeight[iterationCounter-1]){
		//				System.out.println("cardume perdeu peso");
		//			}
		//		}
	}

	public Problem getProblem() {
	    return problem;
	}

	public void setProblem(Problem problem) {
	    this.problem = problem;
	}	

}
