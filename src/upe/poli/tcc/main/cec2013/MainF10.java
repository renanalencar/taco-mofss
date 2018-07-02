package upe.poli.tcc.main.cec2013;

import upe.poli.tcc.algorithm.FishSchoolSearch;
import upe.poli.tcc.problem.cec2013.compositefunction.CF2;
import upe.poli.tcc.util.MyFileReaderWriter;
import upe.poli.tcc.util.Statistics;

import java.util.ArrayList;
import java.util.List;

public class MainF10 {

	public static void main(String[] args) {
		
		//F10 = CF2
		//dimensions = 2D
		//peak height = 0.0
		
		CF2 problem = new CF2(2);
		FishSchoolSearch fss = new FishSchoolSearch(problem);
		double bestFitnessAverage = Double.NEGATIVE_INFINITY;
		
		System.out.println("FSS - Simulation  F10 has started");
		System.out.println();
		bestFitnessAverage = fss.searchAndReturn(10000, 5);
		List<String> list = new ArrayList<String>();
		list.add("Problema = "+fss.getProblem().getName());
		list.add("Número de dimensões = "+fss.getProblem().getDimensions());
		list.add("Melhor solução possível = 0");
		list.add("Fitness médio = "+bestFitnessAverage);
		list.add("Desvio padrão = "+Statistics.getStandardDeviation(fss.getBestFitnessVector()));
		list.add("");
		MyFileReaderWriter.createFileFromList(list, "Simulacoes_CEC2013_FSS.txt");		
	}
}
