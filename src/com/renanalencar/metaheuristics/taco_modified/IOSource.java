package com.renanalencar.metaheuristics.taco_modified;

import java.io.IOException;

/**
 * @author: renanalencar
 * @version: 1.0
 * @date: 2018-04-15
 */
public class IOSource {

    public static IOSource ioSource;    // Classe em singleton para salvar dados de entrada e saída

    public static double [] objectives_; // valores dos objetivos
    public static double [] variables_;  // valores das variáveis

    public static double [] total_cost_r; // valores do percurso total de cada simulação
    public static double [] max_cost_r;   // valores do maior custo de rota de cada simulação

    public static double [] total_cost_w; // valores do peso total de cada simulação
    public static double [] max_cost_w;   // valores do maior custo de peso de cada simulação
    public static int sim_counter;        // contador do número de simulações para o TACO
    public static int independent_run;    // contador do número de simulações para o JMETAL

    public static DoubleMatrix real_weight_matrix;

    private IOSource() throws IOException {

    }

    /**
     *
     * @return
     * @throws IOException
     */
    public static synchronized IOSource getInstance() throws IOException {
        if (ioSource == null)
            ioSource = new IOSource();

        return ioSource;
    }

} // IOSource
