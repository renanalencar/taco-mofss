package com.renanalencar.metaheuristics.many.taco_mofss;

import VANILLA_FSS_rev20110129.cec2010suite.Function;

public class TACOVANILLA_Function extends Function {

    /** the serial version id */
    private static final long serialVersionUID = 1;

    /** the maximum value */
    public static final double MAX = 100d;

    /** the minimum value */
    public static final double MIN = (-MAX);

    /**
     * Create the benchmark function
     *
     * @param dimension the dimension of the function
     * @param min       the minimum value which the decision variables can take on
     * @param max
     */
    protected TACOVANILLA_Function(int dimension, double min, double max) {
        super(dimension, MIN, MAX);
    }

    @Override
    public double compute(double[] x) {
        return 0;
    }

    @Override
    public String getFullName() {
        return "MTSP Problem";
    }

    @Override
    public double[] getOptimum() {
        return new double[0];
    }
}
