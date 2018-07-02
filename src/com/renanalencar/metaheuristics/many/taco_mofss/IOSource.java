package com.renanalencar.metaheuristics.many.taco_mofss;

import java.io.IOException;

/**
 * @author: renanalencar
 * @version: 1.0
 * @date: 2018-04-15
 */
public class IOSource {

    public static IOSource ioSource;

    public static double [] objectives_;
    public static double [] variables_;

    public static double [] total_cost_r;
    public static double [] max_cost_r;

    public static double [] max_cost_w;
    public static int sim_counter;

    private IOSource() throws IOException {

    }

    public static synchronized IOSource getInstance() throws IOException {
        if (ioSource == null)
            ioSource = new IOSource();

        return ioSource;
    }

}
