package upe.poli.ecomp.algorithm;

public class MyFish {

	private double weight;
	private double weightVariation;
	private double currentPosition[];
	private double previousPosition[];
	private double positionBeforeVolitive[];
	private double individualDisplacement[];
	private double fitnessVariation;
	private double previousFitness;
	private double currentFitness;
	private boolean improvedFitness;
	
	public MyFish(double weight, double position[]){
		this.setWeight(weight);
		this.setMyCurrentPosition(position);
	}
	
	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double[] getMyCurrentPosition() {
		return currentPosition;
	}

	public void setMyCurrentPosition(double position[]) {
		this.currentPosition = position;
	}

	public double[] getPreviousPosition() {
		return previousPosition;
	}

	public void setPreviousPosition(double previousPosition[]) {
		this.previousPosition = previousPosition;
	}

	public double[] getIndividualDisplacement() {
		return individualDisplacement;
	}

	public void setIndividualDisplacement(double individualDisplacement[]) {
		this.individualDisplacement = individualDisplacement;
	}

	public double getWeightVariation() {
		return weightVariation;
	}

	public void setWeightVariation(double weightVariation) {
		this.weightVariation = weightVariation;
	}

	public boolean isImprovedFitness() {
		return improvedFitness;
	}

	public void setImprovedFitness(boolean improvedFitness) {
		this.improvedFitness = improvedFitness;
	}

	public double getFitnessVariation() {
		return fitnessVariation;
	}

	public void setFitnessVariation(double fitnessVariation) {
		this.fitnessVariation = fitnessVariation;
	}

	public double[] getPositionBeforeVolitive() {
		return positionBeforeVolitive;
	}

	public void setPositionBeforeVolitive(double[] positionBeforeVolitive) {
		this.positionBeforeVolitive = positionBeforeVolitive;
	}

	public double getPreviousFitness() {
		return previousFitness;
	}

	public void setPreviousFitness(double previousFitness) {
		this.previousFitness = previousFitness;
	}

	public double getCurrentFitness() {
	    return currentFitness;
	}

	public void setCurrentFitness(double currentFitness) {
	    this.currentFitness = currentFitness;
	}
	
	

}
