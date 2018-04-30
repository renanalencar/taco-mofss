package com.renanalencar.metaheuristics.many.taco_mofss;

import java.io.IOException;

/**
 * @author: renanalencar
 * @version: 1.0
 * @date: 2018-04-15
 */
public class IOSource {

    static IOSource ioSource;

    static double [] objectives_;
    static double [] variables_;

    private IOSource() throws IOException {

    }

    public static synchronized IOSource getInstance() throws IOException {
        if (ioSource == null)
            ioSource = new IOSource();

        return ioSource;
    }

}
