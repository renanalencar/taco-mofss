package com.renanalencar.metaheuristics.scenarios;

import com.renanalencar.metaheuristics.many.taco_mofss.ControlExperiment;
import com.renanalencar.metaheuristics.many.taco_mofss.ControlFSS;
import com.renanalencar.metaheuristics.many.taco_mofss.TACOFSS_Problem;
import upe.poli.tcc.algorithm.FishSchoolSearch;

/**
 * Proposta 1 - Cenário 2 - MinFSS(∆Ei) + TACO para obter:
 * Desvio Padrão da Entrega (∆Ei) e Custo Máximo Max(Ci) , i para qualquer n,
 * com MIN_VAR = 0 (ver ControlFSS.java)
 */

/**
 * Proposta 1 - Cenário 3 - MinFSS(Max(Ci)) + TACO para obter:
 * Desvio Padrão da Entrega (∆Ei) e Custo Máximo Max(Ci) , i para qualquer n,
 * com MIN_VAR = 1 (ver ControlFSS.java)
 */

public class TACOFSS_Main implements ControlExperiment, ControlFSS {
	
	public static void main(String[] args) {
		
		TACOFSS_Problem problem = new TACOFSS_Problem(N_VARIABLES);
//		PID problem = new PID();
//		Parameters.IS_MATLAB_FUNCTION = true;
		
		//Parameters have to be changed depending on the function selected
			FishSchoolSearch fss = new FishSchoolSearch(problem);
			System.out.println("média dos melhores fitness = "
			+fss.searchAndReturn(N_ITERACTIONS,N_SIMULATIONS));
	}
}
