package upe.poli.tcc.algorithm;

public class Fish {

	private double weight;
	private double weightVariation;
	private double currentPosition[];
	private double previousPosition[];
	private double individualDisplacement[];
	private double fitnessVariation;
	private boolean improvedFitness;
	
	public Fish(double weight, double position[]){
		
		this.setWeight(weight);
		this.setCurrentPosition(position);
	}
	
	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double[] getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(double position[]) {
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
}
