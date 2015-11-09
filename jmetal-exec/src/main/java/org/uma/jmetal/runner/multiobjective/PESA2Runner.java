//  NSGAIIRunner.java
//
//  Author:
//       Antonio J. Nebro <antonio@lcc.uma.es>
//
//  Copyright (c) 2014 Antonio J. Nebro
//
//  This program is free software: you can redistribute it and/or modify
//  it under the terms of the GNU Lesser General Public License as published by
//  the Free Software Foundation, either version 3 of the License, or
//  (at your option) any later version.
//
//  This program is distributed in the hope that it will be useful,
//  but WITHOUT ANY WARRANTY; without even the implied warranty of
//  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//  GNU Lesser General Public License for more details.
// 
//  You should have received a copy of the GNU Lesser General Public License
//  along with this program.  If not, see <http://www.gnu.org/licenses/>.

package org.uma.jmetal.runner.multiobjective;

import org.apache.commons.collections.map.ListOrderedMap;
import org.uma.jmetal.algorithm.Algorithm;
import org.uma.jmetal.algorithm.multiobjective.pesa2.PESA2Builder;
import org.uma.jmetal.operator.CrossoverOperator;
import org.uma.jmetal.operator.MutationOperator;
import org.uma.jmetal.operator.impl.crossover.SBXCrossover;
import org.uma.jmetal.operator.impl.mutation.PolynomialMutation;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.runner.AbstractAlgorithmRunner;
import org.uma.jmetal.solution.DoubleSolution;
import org.uma.jmetal.util.AlgorithmRunner;
import org.uma.jmetal.util.JMetalLogger;
import org.uma.jmetal.util.ProblemListUtils;
import org.uma.jmetal.util.ProblemUtils;
import org.uma.jmetal.util.fileoutput.FileRename;

import java.util.List;

/**
 * Class for configuring and running the PESA2 algorithm
 *
 * @author Antonio J. Nebro <antonio@lcc.uma.es>
 */

public class PESA2Runner extends AbstractAlgorithmRunner {
    /**
     * @param args Command line arguments.
     * @throws SecurityException Invoking command:
     *                           java PESA2Runner problemName [referenceFront]
     */
    public static void experiment() {
        ListOrderedMap problemMap = ProblemListUtils.getProblemsMap();

        for (int i = 0; i < problemMap.size(); i++) {
            for (int j = 0; j < 30; j++) {
                singleRun((String) problemMap.get(i), (String) problemMap.getValue(i));
            }
        }

        FileRename.renameFile("G:\\学习资料\\百度云\\ItelliWorkSpace\\J4MOP",
                "indicators.tsv",
                "PESA2_metrics.tsv");
        FileRename.renameFile("G:\\学习资料\\百度云\\ItelliWorkSpace\\J4MOP",
                "FUN.tsv",
                "PESA2_FUN.tsv");
        FileRename.renameFile("G:\\学习资料\\百度云\\ItelliWorkSpace\\J4MOP",
                "VAR.tsv",
                "PESA2_VAR.tsv");
    }

    public static void singleRun(String pro, String referencePareto) {
        Problem<DoubleSolution> problem;
        Algorithm<List<DoubleSolution>> algorithm;
        CrossoverOperator<DoubleSolution> crossover;
        MutationOperator<DoubleSolution> mutation;

        String problemName;
        String referenceParetoFront;
        if (!pro.isEmpty() && !referencePareto.isEmpty() && pro.trim() != null && referencePareto.trim() != null) {
            problemName = pro;
            referenceParetoFront = referencePareto;
        } else {
            problemName = "problem.multiobjective.zdt.ZDT1";
            referenceParetoFront = "problem/src/test/resources/pareto_fronts/ZDT1.pf";
        }

        problem = ProblemUtils.<DoubleSolution>loadProblem(problemName);

        int maxEvaluations;
        if (problem.getNumberOfObjectives() == 2) {
            maxEvaluations = 30000;
        } else {
            maxEvaluations = 50000;
        }

        double crossoverProbability = 0.9;
        double crossoverDistributionIndex = 20.0;
        crossover = new SBXCrossover(crossoverProbability, crossoverDistributionIndex);

        double mutationProbability = 1.0 / problem.getNumberOfVariables();
        double mutationDistributionIndex = 20.0;
        mutation = new PolynomialMutation(mutationProbability, mutationDistributionIndex);

        algorithm = new PESA2Builder<DoubleSolution>(problem, crossover, mutation)
                .setMaxEvaluations(maxEvaluations)
                .setPopulationSize(20)
                .setArchiveSize(100)
                .setBisections(5)
                .build();

        AlgorithmRunner algorithmRunner = new AlgorithmRunner.Executor(algorithm)
                .execute();

        List<DoubleSolution> population = algorithm.getResult();

        long computingTime = algorithmRunner.getComputingTime();

        JMetalLogger.logger.info("Total execution time: " + computingTime + "ms");

        printFinalSolutionSet(population);
        if (!referenceParetoFront.equals("")) {
            printQualityIndicators(population, referenceParetoFront);
        }
    }
}
