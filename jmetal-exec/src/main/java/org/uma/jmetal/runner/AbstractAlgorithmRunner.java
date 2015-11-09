package org.uma.jmetal.runner;

import org.uma.jmetal.solution.Solution;
import org.uma.jmetal.util.JMetalLogger;
import org.uma.jmetal.util.fileoutput.QualityIndicatorSetOutput;
import org.uma.jmetal.util.fileoutput.SolutionSetOutput;
import org.uma.jmetal.util.fileoutput.impl.DefaultFileOutputContext;
import org.uma.jmetal.util.pseudorandom.JMetalRandom;

import java.util.List;

/**
 * Abstract class for Runner classes
 *
 * @author Antonio J. Nebro <antonio@lcc.uma.es>
 */
public abstract class AbstractAlgorithmRunner {
    /**
     * Write the population into two files and prints some data on screen
     *
     * @param population
     */
    public static void printFinalSolutionSet(List<? extends Solution<?>> population) {

        new SolutionSetOutput.Printer(population)
                .setSeparator("\t")
                .setVarFileOutputContext(new DefaultFileOutputContext("VAR.tsv", true))
                .setFunFileOutputContext(new DefaultFileOutputContext("FUN.tsv", true))
                .print();

        JMetalLogger.logger.info("Random seed: " + JMetalRandom.getInstance().getSeed());
        JMetalLogger.logger.info("Objectives values have been written to file FUN.tsv");
        JMetalLogger.logger.info("Variables values have been written to file VAR.tsv");
    }

    /**
     * Print all the available quality indicators
     *
     * @param population
     * @param paretoFrontFile
     */
    public static void printQualityIndicators(List<? extends Solution<?>> population, String paretoFrontFile) {
        new QualityIndicatorSetOutput.Printer(population, paretoFrontFile)
                .setSeparator("\t")
                .setQualityIndicatorsFileContext(new DefaultFileOutputContext("Indicators.tsv", true))
                .print();

        JMetalLogger.logger.info("QualityIndicators values have been written to file Indicators.tsv");
    }
}
