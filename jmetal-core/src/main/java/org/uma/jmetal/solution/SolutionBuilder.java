package org.uma.jmetal.solution;

import org.uma.jmetal.algorithm.Algorithm;
import org.uma.jmetal.util.naming.DescribedEntity;

import java.util.Collection;

/**
 * A {@link SolutionBuilder} allows to generate a {@link Solution} by setting
 * its fundamental information, in other words by providing the values of its
 * {@link Variable}s.
 *
 * @param <Solution>
 * @author Matthieu Vergne <matthieu.vergne@gmail.com>
 */
public interface SolutionBuilder<Solution> {
    /**
     * @return the list of {@link Variable}s managed by this
     * {@link SolutionBuilder}
     */
    public Collection<Variable<Solution, ?>> getVariables();

    /**
     * This method tells which {@link Value} to assign to the next
     * {@link Solution}, generated by {@link #build()}, for a given
     * {@link Variable}. Once all the required {@link Variable}s are prepared,
     * {@link #build()} can be called to generate the {@link Solution}. If this
     * method is called several time on the same {@link Variable} before to call
     * {@link #build()}, the last prepared {@link Value} should be considered.
     *
     * @param variable the {@link Variable} to consider
     * @param value    the {@link Value} to prepare for this {@link Variable}
     */
    public <Value> void prepare(Variable<Solution, Value> variable, Value value);

    /**
     * This method generates a valid {@link Solution} based on all the
     * {@link Value}s prepared by calling {@link #prepare(Variable, Object)}.
     * Usually, all the {@link Variable}s should have been prepared before to be
     * able to build a valid {@link Solution}, but it depends on the definition
     * of the {@link Solution} (e.g. there could have {@link Variable}s
     * depending on each other, such that preparing one is equivalent to prepare
     * others). Specific implementation could provide a method to know whether
     * or not {@link #build()} can be called, or other facilities to ensure that
     * a {@link Solution} is properly prepared when {@link #build()} is called.
     *
     * @return a new {@link Solution} instance
     */
    public Solution build();

    /**
     * A {@link Variable} represents the fundamental information of a set of
     * homogeneous {@link Solution}s (e.g. a population of solutions returned by
     * an {@link Algorithm}). For instance, an {@link Algorithm} used to solve a
     * TSP problem would manage a whole population of {@link Solution}s, each
     * representing a different path, and a {@link Variable} would represent a
     * type of information which defines these {@link Solution}s, like the path
     * they represent or something more fine grained like the i<sup>th</sup>
     * city.
     *
     * @param <Solution>
     * @param <Value>
     * @author Matthieu Vergne <matthieu.vergne@gmail.com>
     */
    public static interface Variable<Solution, Value> extends DescribedEntity {
        /**
         * @param solution the {@link Solution} to read
         * @return the {@link Value} of the {@link Variable} for this
         * {@link Solution}
         */
        public Value get(Solution solution);
    }
}
