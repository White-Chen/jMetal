package org.uma.jmetal.runner.multiobjective;

import org.apache.commons.collections.map.ListOrderedMap;
import org.uma.jmetal.algorithm.Algorithm;
import org.uma.jmetal.algorithm.multiobjective.admopso.AdMOPSOBuilder;
import org.uma.jmetal.operator.impl.mutation.RandomizeMutation;
import org.uma.jmetal.problem.DoubleProblem;
import org.uma.jmetal.runner.AbstractAlgorithmRunner;
import org.uma.jmetal.solution.DoubleSolution;
import org.uma.jmetal.util.AlgorithmRunner;
import org.uma.jmetal.util.JMetalLogger;
import org.uma.jmetal.util.ProblemListUtils;
import org.uma.jmetal.util.ProblemUtils;
import org.uma.jmetal.util.archive.impl.AdaptiveGridArchiveII;
import org.uma.jmetal.util.fileoutput.FileRename;

import java.util.List;

/**
 * Created by Lenovo on 2015/11/5.
 *
 * @author chenzhe
 */
public class AdMOPSORunner extends AbstractAlgorithmRunner {

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
                "AdMOPSO_metrics.tsv");
        FileRename.renameFile("G:\\学习资料\\百度云\\ItelliWorkSpace\\J4MOP",
                "FUN.tsv",
                "AdMOPSO_FUN.tsv");
        FileRename.renameFile("G:\\学习资料\\百度云\\ItelliWorkSpace\\J4MOP",
                "VAR.tsv",
                "AdMOPSO_VAR.tsv");
    }

    public static void singleRun(String pro, String referencePareto) {
        DoubleProblem problem;
        Algorithm<List<DoubleSolution>> algorithm;

        String problemName;
        String referenceParetoFront;
        if (!pro.isEmpty() && !referencePareto.isEmpty() && pro.trim() != null && referencePareto.trim() != null) {
            problemName = pro;
            referenceParetoFront = referencePareto;
        } else {
            problemName = "org.uma.jmetal.problem.multiobjective.zdt.ZDT1";
            referenceParetoFront = "jmetal-problem/src/test/resources/pareto_fronts/ZDT1.pf";
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

        AdaptiveGridArchiveII<DoubleSolution> archive = new AdaptiveGridArchiveII<>(archiveSize, 30, problem.getNumberOfObjectives());

        double mutationProbability = 0.5;

        algorithm = new AdMOPSOBuilder(problem, archive)
                .setDeltaDivision(2)
                .setSelectionPressure(4)
                .setEliminatePressure(2)
                .setWeightMax(0.9)
                .setWeightMin(0.4)
                .setSwarmSize(populationSize)
                .setMaxIterations(iterations)
                .setMaxEvaluations(maxEvaluations)
                .setMutation(new RandomizeMutation(mutationProbability))
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
