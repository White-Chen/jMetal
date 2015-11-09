package org.uma.jmetal.algorithm.impl;

import org.uma.jmetal.algorithm.Algorithm;
import org.uma.jmetal.solution.Solution;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Antonio J. Nebro on 11/06/2015.
 *
 * @param <S> Solution
 * @param <R> Result
 */
public abstract class AbstractScatterSearch<S extends Solution<?>, R> implements Algorithm<R> {
    private List<S> population;
    private int populationSize;

    public List<S> getPopulation() {
        return population;
    }

    public void setPopulation(List<S> population) {
        this.population = population;
    }

    public int getPopulationSize() {
        return populationSize;
    }

    public void setPopulationSize(int populationSize) {
        this.populationSize = populationSize;
    }

    public abstract boolean isStoppingConditionReached();

    public abstract boolean restartConditionIsFulfilled(List<S> solutionList);

    public abstract void restart();

    public abstract S diversificationGeneration();

    public abstract S improvement(S solution);

    public abstract void referenceSetUpdate();

    public abstract void referenceSetUpdate(S solution);

    public abstract List<List<S>> subsetGeneration();

    public abstract List<S> solutionCombination(List<List<S>> population);

    @Override
    public abstract R getResult();

    @Override
    public void run() {
        initializationPhase();
        referenceSetUpdate();
        while (!isStoppingConditionReached()) {
            List<List<S>> subset = subsetGeneration();
            List<S> combinedSolutions = solutionCombination(subset);
            if (restartConditionIsFulfilled(combinedSolutions)) {
                restart();
                referenceSetUpdate();
            } else {
                for (S solution : combinedSolutions) {
                    S improvedSolution = improvement(solution);
                    referenceSetUpdate(improvedSolution);
                }
            }
        }
    }

    /**
     * Initialization phase of the scatter search: the population is filled with diverse solutions that
     * have been improved.
     *
     * @return The population
     */
    public void initializationPhase() {
        population = new ArrayList<>(populationSize);
        while (population.size() < populationSize) {
            S newSolution = diversificationGeneration();
            S improvedSolution = improvement(newSolution);
            population.add(improvedSolution);
        }
    }
}
