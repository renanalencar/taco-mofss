package upe.poli.tcc.main;

import upe.poli.tcc.algorithm.FishSchoolSearch;
import upe.poli.tcc.algorithm.Parameters;
import upe.poli.tcc.problem.*;
import upe.poli.tcc.util.MyFileReaderWriter;
import upe.poli.tcc.util.Statistics;

import java.util.ArrayList;
import java.util.List;

public class MainAllFunctions {
	
	//args[0] = function name
	//args[1] = dimensions number
	//args[2] = number of particles
	//args[3] = filename
	
	//args[4] = step_ind_init
	//args[5] = step_ind_final
	//args[6] = step_vol_init
	//args[7] = step_vol_final
	
	public static void main(String[] args) {
		
		String functionName = args[0];
		functionName = functionName.toLowerCase();
		Parameters.DIMENSION = Integer.parseInt(args[1]);
		Parameters.POPULATION_SIZE = Integer.parseInt(args[2]);
		String fileName = args[3];
		
		//step parameters
		Parameters.STEP_IND_INIT = Double.parseDouble(args[4]);
		Parameters.STEP_IND_FINAL = Double.parseDouble(args[5]);
		Parameters.STEP_VOL_INIT = Double.parseDouble(args[6]);
		Parameters.STEP_VOL_FINAL = Double.parseDouble(args[7]);
		
		
		Problem problem = null;
		
		switch(functionName){
		
			case "sphere":{
				problem = new Sphere(Parameters.DIMENSION);
				break;
			}
			case "rastrigin":{
				problem = new Rastrigin(Parameters.DIMENSION);
				break;
			}
			case "rosenbrock":{
				problem = new Rosenbrock(Parameters.DIMENSION);
				break;
			}
			case "griewank":{
				problem = new Griewank(Parameters.DIMENSION);
				break;
			}
			case "ackley":{
				problem = new Ackley(Parameters.DIMENSION);
				break;
			}
			case "schwefel":{
				problem = new Schwefel12(Parameters.DIMENSION);
				break;
			}
		}
		FishSchoolSearch fss = new FishSchoolSearch(problem);
		List<String> list = new ArrayList<String>();
		//run
		double bestFitnessAverage = fss.searchAndReturn(10000, 30);
		
		list.add("Problema = "+fss.getProblem().getName());
		list.add("Média dos melhores fitness = "+bestFitnessAverage);
		list.add("Desvio padrão = "+Statistics.getStandardDeviation(fss.getBestFitnessVector()));
		list.add("");
		
		MyFileReaderWriter.createFileFromList(list, fileName);	
	}
}
