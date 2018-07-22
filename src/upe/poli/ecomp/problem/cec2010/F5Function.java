	package upe.poli.ecomp.problem.cec2010;

import upe.poli.ecomp.problem.Problem;
import upe.poli.ecomp.problem.cec2010.core.Defaults;
import upe.poli.ecomp.problem.cec2010.core.F5;

    public class F5Function extends Problem {

        private F5 f5;

        public F5Function(int dimensions) {
            super(dimensions);
            Defaults.DEFAULT_DIM = dimensions;
            Defaults.DEFAULT_M = Defaults.DEFAULT_DIM/2;

            f5 = new F5();

        }

        @Override
        public void init() {

        }

        @Override
        public double getLowerBound(int dimension) {

            return f5.getMin();
        }

        @Override
        public double getUpperBound(int dimension) {

            return f5.getMax();
        }

        @Override
        public boolean isFitnessBetterThan(double currentSolutionFitness,
                double bestSolutionFitness) {

            return currentSolutionFitness < bestSolutionFitness;
        }

        @Override
        public String getName() {

            return "F5Function";
        }

        @Override
        public double evaluateSolution(double[] solution) {
            return f5.compute(solution);
        }
    }
