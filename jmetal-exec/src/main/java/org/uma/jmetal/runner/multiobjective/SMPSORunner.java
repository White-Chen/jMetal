//  SMPSORunner.java
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
import org.uma.jmetal.algorithm.multiobjective.smpso.SMPSO;
import org.uma.jmetal.algorithm.multiobjective.smpso.SMPSOBuilder;
import org.uma.jmetal.operator.MutationOperator;
import org.uma.jmetal.operator.impl.mutation.PolynomialMutation;
import org.uma.jmetal.problem.DoubleProblem;
import org.uma.jmetal.runner.AbstractAlgorithmRunner;
import org.uma.jmetal.solution.DoubleSolution;
import org.uma.jmetal.util.AlgorithmRunner;
import org.uma.jmetal.util.JMetalLogger;
import org.uma.jmetal.util.ProblemListUtils;
import org.uma.jmetal.util.ProblemUtils;
import org.uma.jmetal.util.archive.Archive;
import org.uma.jmetal.util.archive.impl.CrowdingDistanceArchive;
import org.uma.jmetal.util.evaluator.impl.SequentialSolutionListEvaluator;
import org.uma.jmetal.util.fileoutput.FileRename;
import org.uma.jmetal.util.pseudorandom.impl.MersenneTwisterGenerator;

import java.util.List;

/**
 * Class for configuring and running the SMPSO algorithm
 *
 * @author Antonio J. Nebro <antonio@lcc.uma.es>
 */
public class SMPSORunner extends AbstractAlgorithmRunner {
    /**
     * @param args Command line arguments. The first (optional) argument specifies
     *             the problem to solve.
     * @throws util.JMetalException
     * @throws java.io.IOException
     * @throws SecurityException    Invoking command:
     *                              java SMPSORunner problemName [referenceFront]
     */
    public static void main(String[] args) {
        experiment();
    }

    public static void experiment() {
        ListOrderedMap problemMap = ProblemListUtils.getProblemsMap();

        for (int i = 0; i < problemMap.size(); i++) {
            for (int j = 0; j < 1; j++) {
                singleRun((String) problemMap.get(i), (String) problemMap.getValue(i));
            }
        }

        FileRename.renameFile("G:\\学习资料\\百度云\\ItelliWorkSpace\\J4MOP",
                "indicators.tsv",
                "SMPSO_metrics.tsv");
        FileRename.renameFile("G:\\学习资料\\百度云\\ItelliWorkSpace\\J4MOP",
                "FUN.tsv",
                "SMPSO_FUN.tsv");
        FileRename.renameFile("G:\\学习资料\\百度云\\ItelliWorkSpace\\J4MOP",
                "VAR.tsv",
                "SMPSO_VAR.tsv");
    }

    public static void singleRun(String pro, String referencePareto) {
        DoubleProblem problem;
        Algorithm<List<DoubleSolution>> algorithm;
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

        problem = (DoubleProblem) ProblemUtils.<DoubleSolution>loadProblem(problemName);

        int iterations, maxEvaluations, populationSize, archiveSize;
        if (problem.getNumberOfObjectives() == 2) {
            iterations = 300;
            maxEvaluations = 30000;
            populationSize = 100;
            archiveSize = 100;
        } else {
            iterations = 500;
            maxEvaluations = 75000;
            populationSize = 150;
            archiveSize = 150;
        }

        Archive<DoubleSolution> archive = new CrowdingDistanceArchive<DoubleSolution>(archiveSize);

        double mutationProbability = 1.0 / problem.getNumberOfVariables();
        double mutationDistributionIndex = 20.0;
        mutation = new PolynomialMutation(mutationProbability, mutationDistributionIndex);

        algorithm = new SMPSOBuilder(problem, archive)
                .setMutation(mutation)
                .setMaxIterations(iterations)
                .setMaxEvaluations(maxEvaluations)
                .setSwarmSize(populationSize)
                .setWeightMax(0.9)
                .setWeightMin(0.4)
                .setRandomGenerator(new MersenneTwisterGenerator())
                .setSolutionListEvaluator(new SequentialSolutionListEvaluator<DoubleSolution>())
                .build();

        AlgorithmRunner algorithmRunner = new AlgorithmRunner.Executor(algorithm)
                .execute();

        List<DoubleSolution> population = ((SMPSO) algorithm).getResult();
        long computingTime = algorithmRunner.getComputingTime();

        JMetalLogger.logger.info("Total execution time: " + computingTime + "ms");

        printFinalSolutionSet(population);
        if (!referenceParetoFront.equals("")) {
            printQualityIndicators(population, referenceParetoFront);
        }
    }
}
