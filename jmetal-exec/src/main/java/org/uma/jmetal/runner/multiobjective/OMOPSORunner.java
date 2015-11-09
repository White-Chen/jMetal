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
import org.uma.jmetal.algorithm.multiobjective.omopso.OMOPSOBuilder;
import org.uma.jmetal.operator.MutationOperator;
import org.uma.jmetal.operator.impl.mutation.NonUniformMutation;
import org.uma.jmetal.operator.impl.mutation.UniformMutation;
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

import java.util.List;

/**
 * Class for configuring and running the OMOPSO algorithm
 *
 * @author Antonio J. Nebro <antonio@lcc.uma.es>
 */

public class OMOPSORunner extends AbstractAlgorithmRunner {
    /**
     * @throws util.JMetalException
     * @throws java.io.IOException
     * @throws SecurityException    Invoking command:
     *                              java OMOPSORunner problemName [referenceFront]
     */
    public static void main(String[] agrs) {
        singleRun("", "");
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
                "OMOPSO_metrics.tsv");
        FileRename.renameFile("G:\\学习资料\\百度云\\ItelliWorkSpace\\J4MOP",
                "FUN.tsv",
                "OMOPSO_FUN.tsv");
        FileRename.renameFile("G:\\学习资料\\百度云\\ItelliWorkSpace\\J4MOP",
                "VAR.tsv",
                "OMOPSO_VAR.tsv");
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
            problemName = "problem.multiobjective.cec2009Competition.UF6";
            referenceParetoFront = "problem/src/test/resources/pareto_fronts/UF6.pf";
        }

        problem = (DoubleProblem) ProblemUtils.<DoubleSolution>loadProblem(problemName);

        int iterations;
        if (problem.getNumberOfObjectives() == 2) {
            iterations = 300;
        } else {
            iterations = 500;
        }

        Archive<DoubleSolution> archive = new CrowdingDistanceArchive<DoubleSolution>(100);

        double mutationProbability = 1.0 / problem.getNumberOfVariables();

        algorithm = new OMOPSOBuilder(problem, new SequentialSolutionListEvaluator<DoubleSolution>())
                .setMaxIterations(iterations)
                .setSwarmSize(100)
                .setUniformMutation(new UniformMutation(mutationProbability, 0.5))
                .setNonUniformMutation(new NonUniformMutation(mutationProbability, 0.5, iterations))
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
