package org.uma.jmetal.util.fileoutput;

import org.uma.jmetal.qualityindicator.impl.*;
import org.uma.jmetal.solution.DoubleSolution;
import org.uma.jmetal.solution.Solution;
import org.uma.jmetal.util.JMetalException;
import org.uma.jmetal.util.fileoutput.impl.DefaultFileOutputContext;
import org.uma.jmetal.util.front.Front;
import org.uma.jmetal.util.front.imp.ArrayFront;
import org.uma.jmetal.util.front.util.FrontNormalizer;
import org.uma.jmetal.util.front.util.FrontUtils;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * Created by Lenovo on 2015/10/5.
 */
public class QualityIndicatorSetOutput {

    public static void printQualityIndicatorsToFile(FileOutputContext context,
                                                    List<? extends Solution<?>> solutionSet,
                                                    List<? extends Solution<?>> normalizedPopulation,
                                                    Front referenceFront,
                                                    Front normalizedReferenceFront) {
        try {
            BufferedWriter bufferedWriter = context.getFileWriter();

            bufferedWriter.write(
                    new Hypervolume<List<? extends Solution<?>>>(normalizedReferenceFront).evaluate(normalizedPopulation)
                            + context.getSeparator() +
                            new Hypervolume<List<? extends Solution<?>>>(referenceFront).evaluate(solutionSet)
                            + context.getSeparator() +
                            new Epsilon<List<? extends Solution<?>>>(normalizedReferenceFront).evaluate(normalizedPopulation)
                            + context.getSeparator() +
                            new Epsilon<List<? extends Solution<?>>>(referenceFront).evaluate(solutionSet)
                            + context.getSeparator() +
                            new GenerationalDistance<List<? extends Solution<?>>>(normalizedReferenceFront).evaluate(normalizedPopulation)
                            + context.getSeparator() +
                            new GenerationalDistance<List<? extends Solution<?>>>(referenceFront).evaluate(solutionSet)
                            + context.getSeparator() +
                            new InvertedGenerationalDistance<List<? extends Solution<?>>>(normalizedReferenceFront).evaluate(normalizedPopulation)
                            + context.getSeparator() +
                            new InvertedGenerationalDistance<List<? extends Solution<?>>>(referenceFront).evaluate(solutionSet)
                            + context.getSeparator() +
                            new InvertedGenerationalDistancePlus<List<? extends Solution<?>>>(normalizedReferenceFront).evaluate(normalizedPopulation)
                            + context.getSeparator() +
                            new InvertedGenerationalDistancePlus<List<? extends Solution<?>>>(referenceFront).evaluate(solutionSet)
                            + context.getSeparator() +
                            new Spread<List<? extends Solution<?>>>(normalizedReferenceFront).evaluate(normalizedPopulation)
                            + context.getSeparator() +
                            new Spread<List<? extends Solution<?>>>(referenceFront).evaluate(solutionSet)
                            + context.getSeparator() +
                            /*new R2<List<? extends Solution<?>>>(normalizedReferenceFront).evaluate(normalizedPopulation)
                            + context.getSeparator() +
                            new R2<List<? extends Solution<?>>>(referenceFront).evaluate(solutionSet)
                            + context.getSeparator() +*/
                            new ErrorRatio<List<? extends Solution<?>>>(referenceFront).evaluate(solutionSet)
                            + System.getProperty("line.separator"));

            bufferedWriter.close();
        } catch (IOException e) {
            throw new JMetalException("Error in printQualityIndicators", e);
        }

    }

    /*
     * Wrappers for printing with default configuration
     */
    public static void printQualityIndicatorsToFile(List<? extends Solution<?>> solutionSet,
                                                    List<? extends Solution<?>> normalizedPopulation,
                                                    Front referenceFront,
                                                    Front normalizedReferenceFront,
                                                    String fileName)
            throws IOException {
        printQualityIndicatorsToFile(new DefaultFileOutputContext(fileName),
                solutionSet,
                normalizedPopulation,
                referenceFront,
                normalizedReferenceFront);
    }

    public static class Printer {
        private FileOutputContext QualityIndicatorsFileContext;
        private String QualityIndicatorsFileName = "Indicators";
        private String separator = "\t";
        private List<? extends Solution<?>> solutionSet;
        private Front referenceFront;
        private FrontNormalizer frontNormalizer;
        private Front normalizedReferenceFront;
        private Front normalizedFront;
        private List<DoubleSolution> normalizedPopulation;
        private boolean selectFeasibleSolutions;

        public Printer(List<? extends Solution<?>> solutionSet, String paretoFrontFile) {
            try {
                referenceFront = new ArrayFront(paretoFrontFile);
            } catch (FileNotFoundException e) {
                throw new JMetalException("Error in paretoFrontFile to ArrayFront.Class", e);
            }
            frontNormalizer = new FrontNormalizer(referenceFront);
            normalizedReferenceFront = frontNormalizer.normalize(referenceFront);
            normalizedFront = frontNormalizer.normalize(new ArrayFront(solutionSet));
            normalizedPopulation = FrontUtils.convertFrontToSolutionList(normalizedFront);

            QualityIndicatorsFileContext = new DefaultFileOutputContext(QualityIndicatorsFileName);
            QualityIndicatorsFileContext.setSeparator(separator);
            this.solutionSet = solutionSet;
            selectFeasibleSolutions = false;
        }

        public Printer selectFeasibleSolutions() {
            return this;
        }

        public FileOutputContext getQualityIndicatorsFileContext() {
            return QualityIndicatorsFileContext;
        }

        public Printer setQualityIndicatorsFileContext(FileOutputContext qualityIndicatorsFileContext) {
            QualityIndicatorsFileContext = qualityIndicatorsFileContext;
            return this;
        }

        public String getQualityIndicatorsFileName() {
            return QualityIndicatorsFileName;
        }

        public void setQualityIndicatorsFileName(String qualityIndicatorsFileName) {
            QualityIndicatorsFileName = qualityIndicatorsFileName;
        }

        public String getSeparator() {
            return separator;
        }

        public Printer setSeparator(String separator) {
            this.separator = separator;
            QualityIndicatorsFileContext.setSeparator(this.separator);
            return this;
        }

        public Front getReferenceFront() {
            return referenceFront;
        }

        public void setReferenceFront(Front referenceFront) {
            this.referenceFront = referenceFront;
        }

        public List<? extends Solution<?>> getSolutionSet() {
            return solutionSet;
        }

        public void setSolutionSet(List<? extends Solution<?>> solutionSet) {
            this.solutionSet = solutionSet;
        }

        public FrontNormalizer getFrontNormalizer() {
            return frontNormalizer;
        }

        public void setFrontNormalizer(FrontNormalizer frontNormalizer) {
            this.frontNormalizer = frontNormalizer;
        }

        public Front getNormalizedReferenceFront() {
            return normalizedReferenceFront;
        }

        public void setNormalizedReferenceFront(Front normalizedReferenceFront) {
            this.normalizedReferenceFront = normalizedReferenceFront;
        }

        public Front getNormalizedFront() {
            return normalizedFront;
        }

        public void setNormalizedFront(Front normalizedFront) {
            this.normalizedFront = normalizedFront;
        }

        public List<DoubleSolution> getNormalizedPopulation() {
            return normalizedPopulation;
        }

        public void setNormalizedPopulation(List<DoubleSolution> normalizedPopulation) {
            this.normalizedPopulation = normalizedPopulation;
        }

        public boolean isSelectFeasibleSolutions() {
            return selectFeasibleSolutions;
        }

        public void setSelectFeasibleSolutions(boolean selectFeasibleSolutions) {
            this.selectFeasibleSolutions = selectFeasibleSolutions;
        }

        public void print() {
            printQualityIndicatorsToFile(QualityIndicatorsFileContext,
                    solutionSet,
                    normalizedPopulation,
                    referenceFront,
                    normalizedReferenceFront);
        }
    }
}
