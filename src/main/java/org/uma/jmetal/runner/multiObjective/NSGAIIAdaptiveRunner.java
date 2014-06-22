//  NSGAIIAdaptive_main.java
//
//  Author:
//       Antonio J. Nebro <antonio@lcc.uma.es>
//       Juan J. Durillo <durillo@lcc.uma.es>
//
//  Copyright (c) 2011 Antonio J. Nebro, Juan J. Durillo
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

package org.uma.jmetal.runner.multiObjective;

import org.uma.jmetal.core.Algorithm;
import org.uma.jmetal.core.Operator;
import org.uma.jmetal.core.Problem;
import org.uma.jmetal.core.SolutionSet;
import org.uma.jmetal.metaheuristic.nsgaII.NSGAIIAdaptive;
import org.uma.jmetal.operator.selection.SelectionFactory;
import org.uma.jmetal.problem.Kursawe;
import org.uma.jmetal.problem.ProblemFactory;
import org.uma.jmetal.qualityIndicator.QualityIndicator;
import org.uma.jmetal.util.Configuration;
import org.uma.jmetal.util.JMetalException;
import org.uma.jmetal.util.evaluator.SequentialSolutionSetEvaluator;
import org.uma.jmetal.util.evaluator.SolutionSetEvaluator;
import org.uma.jmetal.util.offspring.DifferentialEvolutionOffspring;
import org.uma.jmetal.util.offspring.Offspring;
import org.uma.jmetal.util.offspring.PolynomialMutationOffspring;
import org.uma.jmetal.util.offspring.SBXCrossoverOffspring;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

/**
 * Class implementing the NSGA-II algorithm.
 * This implementation of NSGA-II makes use of a QualityIndicator object
 * to obtained the convergence speed of the algorithm. This version is used
 * in the paper:
 * A.J. Nebro, J.J. Durillo, C.A. Coello Coello, F. Luna, E. Alba
 * "A Study of Convergence Speed in Multi-Objective Metaheuristics."
 * To be presented in: PPSN'08. Dortmund. September 2008.
 * <p/>
 * Besides the classic NSGA-II, a steady-state version (ssNSGAII) is also
 * included (See: J.J. Durillo, A.J. Nebro, F. Luna and E. Alba
 * "On the Effect of the Steady-State Selection Scheme in
 * Multi-Objective Genetic Algorithms"
 * 5th International Conference, EMO 2009, pp: 183-197.
 * April 2009)
 */

public class NSGAIIAdaptiveRunner {
  public static Logger logger_;      
  public static FileHandler fileHandler_; 

  /**
   * @param args Command line arguments.
   * @throws org.uma.jmetal.util.JMetalException
   * @throws java.io.IOException
   * @throws SecurityException Usage: three options
   *                           - org.uma.jmetal.metaheuristic.nsgaII.NSGAII_main
   *                           - org.uma.jmetal.metaheuristic.nsgaII.NSGAII_main problemName
   *                           - org.uma.jmetal.metaheuristic.nsgaII.NSGAII_main problemName paretoFrontFile
   */
  public static void main(String[] args) throws
    JMetalException,
    SecurityException,
    IOException,
    ClassNotFoundException {
    Problem problem;
    Algorithm algorithm;
    Operator crossover;
    Operator mutation;
    Operator selection;

    HashMap parameters;

    QualityIndicator indicators;

    // Logger object and file to store log messages
    logger_ = Configuration.logger_;
    fileHandler_ = new FileHandler("NSGAII_main.log");
    logger_.addHandler(fileHandler_);

    indicators = null;
    if (args.length == 1) {
      Object[] params = {"Real"};
      problem = (new ProblemFactory()).getProblem(args[0], params);
    }
    else if (args.length == 2) {
      Object[] params = {"Real"};
      problem = (new ProblemFactory()).getProblem(args[0], params);
      indicators = new QualityIndicator(problem, args[1]);
    }
    else {
      problem = new Kursawe("Real", 3);
      /*
        Examples:
        problem = new Kursawe("BinaryReal", 3);
        problem = new Water("Real");
        problem = new ZDT3("ArrayReal", 30);
        problem = new ConstrEx("Real");
        problem = new DTLZ1("Real");
        problem = new OKA2("Real")
      */
    }

    SolutionSetEvaluator evaluator = new SequentialSolutionSetEvaluator() ;
    algorithm = new NSGAIIAdaptive(evaluator);
    algorithm.setProblem(problem);

    // Algorithm parameters
    algorithm.setInputParameter("populationSize", 100);
    algorithm.setInputParameter("maxEvaluations", 150000);

    // Selection Operator 
    HashMap<String, Object> selectionParameters = null;
    selection = SelectionFactory.getSelectionOperator("BinaryTournament2", selectionParameters);

    // Add the operator to the algorithm
    algorithm.addOperator("selection", selection);

    // Add the indicator object to the algorithm
    algorithm.setInputParameter("indicators", indicators);

    Offspring[] getOffspring = new Offspring[3];

    double CR, F;
    getOffspring[0] = new DifferentialEvolutionOffspring(CR = 1.0, F = 0.5);
    getOffspring[1] = new SBXCrossoverOffspring(1.0, 20);
    getOffspring[2] = new PolynomialMutationOffspring(1.0 / problem.getNumberOfVariables(), 20);
    
    algorithm.setInputParameter("offspringsCreators", getOffspring);

    // Execute the Algorithm
    long initTime = System.currentTimeMillis();
    SolutionSet population = algorithm.execute();
    long estimatedTime = System.currentTimeMillis() - initTime;

    // Result messages 
    logger_.info("Total execution time: " + estimatedTime + "ms");
    logger_.info("Variables values have been writen to file VAR");
    population.printVariablesToFile("VAR");
    logger_.info("Objectives values have been writen to file FUN");
    population.printObjectivesToFile("FUN");

    if (indicators != null) {
      logger_.info("Quality indicators");
      logger_.info("Hypervolume: " + indicators.getHypervolume(population));
      logger_.info("GD         : " + indicators.getGD(population));
      logger_.info("IGD        : " + indicators.getIGD(population));
      logger_.info("Spread     : " + indicators.getSpread(population));
      logger_.info("Epsilon    : " + indicators.getEpsilon(population));
    } 
  } 
} 