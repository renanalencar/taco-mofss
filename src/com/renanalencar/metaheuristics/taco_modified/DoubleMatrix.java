package com.renanalencar.metaheuristics.taco_modified;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static com.renanalencar.metaheuristics.taco_modified.ControlExperiment.FLOAT_PRECISION;


/**
 * @author renanalencar
 * @version 1.0
 * @since 2017-11-01
 *
 */
public class DoubleMatrix {
    private int dimension; // numero de valores da matriz = dimension x dimension
    double matrix[][]; // matriz

    /**
     *
     * @param dimension
     */
    public DoubleMatrix(int dimension) {
        this.dimension  = dimension;
        this.matrix     = new double[dimension][dimension];
    }

    /**
     *
     * @param i
     * @param j
     * @param value
     */
    public void set_value(int i, int j, double value) {
        this.matrix[i][j] = value;
    }

    /**
     *
     * @param i
     * @param j
     * @return
     */
    public double get_value(int i, int j) {
        return this.matrix[i][j];
    }

    /**
     *
     * @param col
     * @return
     */
    public double[] get_col_matrix(int col) {
        return this.matrix[col];
    }

    /**
     *
     */
    public void print_matrix() {
        System.out.print("matrix:\r\n");

        for (int i = 0; i < dimension; i++) {
            System.out.print("\t" + i);
        }

        for (int i = 0; i < dimension; i++) {
            System.out.print("\r\n" + i);
            for (int j = 0; j < dimension; j++) {
                if (i == j) {
                    System.out.print("\t" + 0.00);
                } else {
                    System.out.print("\t" + String.format("%."+FLOAT_PRECISION+"f", matrix[i][j]));
                }
            }
        }
        System.out.print("\r\n");
    }

    /**
     *
     * @throws IOException
     */
    public void save_matrix() throws IOException {
        BufferedWriter file_mat = null;
        file_mat = new BufferedWriter(new FileWriter("outs/matrix.txt"));

        file_mat.write("Matrix:\r\n");
        for (int i = 0; i < dimension; i++) {
            file_mat.write("\t" + i);
        }
        for (int i = 0; i < dimension; i++) {
            file_mat.write("\r\n" + i);
            for (int j = 0; j < dimension; j++) {
                file_mat.write("\t" + matrix[i][j]);
            }
        }
        file_mat.write("\r\n\r\n");

    }

} // DoubleMatrix
