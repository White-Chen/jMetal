package org.uma.jmetal.util;

import org.apache.commons.collections.map.ListOrderedMap;

/**
 * Created by Lenovo on 2015/10/28.
 */
public class ProblemListUtils {

    public static ListOrderedMap getProblemsMap() {
        ListOrderedMap problemMap = new ListOrderedMap();

        /*ZDT*/
        problemMap.put(0
                , "org.uma.jmetal.problem.multiobjective.zdt.ZDT1"
                , "jmetal-problem/src/test/resources/pareto_fronts/ZDT1.pf");
        problemMap.put(1
                , "org.uma.jmetal.problem.multiobjective.zdt.ZDT2"
                , "jmetal-problem/src/test/resources/pareto_fronts/ZDT2.pf");
        problemMap.put(2
                , "org.uma.jmetal.problem.multiobjective.zdt.ZDT3"
                , "jmetal-problem/src/test/resources/pareto_fronts/ZDT3.pf");
        problemMap.put(3
                , "org.uma.jmetal.problem.multiobjective.zdt.ZDT4"
                , "jmetal-problem/src/test/resources/pareto_fronts/ZDT4.pf");
        problemMap.put(4
                , "org.uma.jmetal.problem.multiobjective.zdt.ZDT6"
                , "jmetal-problem/src/test/resources/pareto_fronts/ZDT6.pf");

        /*DTLZ*/
        problemMap.put(5
                , "org.uma.jmetal.problem.multiobjective.dtlz.DTLZ1"
                , "jmetal-problem/src/test/resources/pareto_fronts/DTLZ1.3D.pf");
        problemMap.put(6
                , "org.uma.jmetal.problem.multiobjective.dtlz.DTLZ2"
                , "jmetal-problem/src/test/resources/pareto_fronts/DTLZ2.3D.pf");
        problemMap.put(7
                , "org.uma.jmetal.problem.multiobjective.dtlz.DTLZ3"
                , "jmetal-problem/src/test/resources/pareto_fronts/DTLZ3.3D.pf");
        problemMap.put(8
                , "org.uma.jmetal.problem.multiobjective.dtlz.DTLZ4"
                , "jmetal-problem/src/test/resources/pareto_fronts/DTLZ4.3D.pf");
        problemMap.put(9
                , "org.uma.jmetal.problem.multiobjective.dtlz.DTLZ5"
                , "jmetal-problem/src/test/resources/pareto_fronts/DTLZ5.3D.pf");
        problemMap.put(10
                , "org.uma.jmetal.problem.multiobjective.dtlz.DTLZ6"
                , "jmetal-problem/src/test/resources/pareto_fronts/DTLZ6.3D.pf");
        problemMap.put(11
                , "org.uma.jmetal.problem.multiobjective.dtlz.DTLZ7"
                , "jmetal-problem/src/test/resources/pareto_fronts/DTLZ7.3D.pf");

        /*WFG*/
        problemMap.put(12
                , "org.uma.jmetal.problem.multiobjective.wfg.WFG1"
                , "jmetal-problem/src/test/resources/pareto_fronts/WFG1.2D.pf");
        problemMap.put(13
                , "org.uma.jmetal.problem.multiobjective.wfg.WFG2"
                , "jmetal-problem/src/test/resources/pareto_fronts/WFG2.2D.pf");
        problemMap.put(14
                , "org.uma.jmetal.problem.multiobjective.wfg.WFG3"
                , "jmetal-problem/src/test/resources/pareto_fronts/WFG3.2D.pf");
        problemMap.put(15
                , "org.uma.jmetal.problem.multiobjective.wfg.WFG4"
                , "jmetal-problem/src/test/resources/pareto_fronts/WFG4.2D.pf");
        problemMap.put(16
                , "org.uma.jmetal.problem.multiobjective.wfg.WFG5"
                , "jmetal-problem/src/test/resources/pareto_fronts/WFG5.2D.pf");
        problemMap.put(17
                , "org.uma.jmetal.problem.multiobjective.wfg.WFG6"
                , "jmetal-problem/src/test/resources/pareto_fronts/WFG6.2D.pf");
        problemMap.put(18
                , "org.uma.jmetal.problem.multiobjective.wfg.WFG7"
                , "jmetal-problem/src/test/resources/pareto_fronts/WFG7.2D.pf");
        problemMap.put(19
                , "org.uma.jmetal.problem.multiobjective.wfg.WFG8"
                , "jmetal-problem/src/test/resources/pareto_fronts/WFG8.2D.pf");
        problemMap.put(20
                , "org.uma.jmetal.problem.multiobjective.wfg.WFG9"
                , "jmetal-problem/src/test/resources/pareto_fronts/WFG9.2D.pf");

        /*LZ09*/
        problemMap.put(21
                , "org.uma.jmetal.problem.multiobjective.lz09.LZ09F1"
                , "jmetal-problem/src/test/resources/pareto_fronts/LZ09_F1.pf");
        problemMap.put(22
                , "org.uma.jmetal.problem.multiobjective.lz09.LZ09F2"
                , "jmetal-problem/src/test/resources/pareto_fronts/LZ09_F2.pf");
        problemMap.put(23
                , "org.uma.jmetal.problem.multiobjective.lz09.LZ09F3"
                , "jmetal-problem/src/test/resources/pareto_fronts/LZ09_F3.pf");
        problemMap.put(24
                , "org.uma.jmetal.problem.multiobjective.lz09.LZ09F4"
                , "jmetal-problem/src/test/resources/pareto_fronts/LZ09_F4.pf");
        problemMap.put(25
                , "org.uma.jmetal.problem.multiobjective.lz09.LZ09F5"
                , "jmetal-problem/src/test/resources/pareto_fronts/LZ09_F5.pf");
        problemMap.put(26
                , "org.uma.jmetal.problem.multiobjective.lz09.LZ09F6"
                , "jmetal-problem/src/test/resources/pareto_fronts/LZ09_F6.pf");
        problemMap.put(27
                , "org.uma.jmetal.problem.multiobjective.lz09.LZ09F7"
                , "jmetal-problem/src/test/resources/pareto_fronts/LZ09_F7.pf");
        problemMap.put(28
                , "org.uma.jmetal.problem.multiobjective.lz09.LZ09F8"
                , "jmetal-problem/src/test/resources/pareto_fronts/LZ09_F8.pf");
        problemMap.put(29
                , "org.uma.jmetal.problem.multiobjective.lz09.LZ09F9"
                , "jmetal-problem/src/test/resources/pareto_fronts/LZ09_F9.pf");

        /*CEC09*/
        problemMap.put(30
                , "org.uma.jmetal.problem.multiobjective.cec2009Competition.UF1"
                , "jmetal-problem/src/test/resources/pareto_fronts/UF1.pf");
        problemMap.put(31
                , "org.uma.jmetal.problem.multiobjective.cec2009Competition.UF2"
                , "jmetal-problem/src/test/resources/pareto_fronts/UF2.pf");
        problemMap.put(32
                , "org.uma.jmetal.problem.multiobjective.cec2009Competition.UF3"
                , "jmetal-problem/src/test/resources/pareto_fronts/UF3.pf");
/*        problemMap.put( 33
                ,"problem.multiobjective.cec2009Competition.UF4"
                ,"jmetal-problem/src/test/resources/pareto_fronts/UF4.pf");*/
        problemMap.put(33
                , "org.uma.jmetal.problem.multiobjective.cec2009Competition.UF5"
                , "jmetal-problem/src/test/resources/pareto_fronts/UF5.pf");
        problemMap.put(34
                , "org.uma.jmetal.problem.multiobjective.cec2009Competition.UF6"
                , "jmetal-problem/src/test/resources/pareto_fronts/UF6.pf");
        problemMap.put(35
                , "org.uma.jmetal.problem.multiobjective.cec2009Competition.UF7"
                , "jmetal-problem/src/test/resources/pareto_fronts/UF7.pf");
        problemMap.put(36
                , "org.uma.jmetal.problem.multiobjective.cec2009Competition.UF8"
                , "jmetal-problem/src/test/resources/pareto_fronts/UF8.pf");
        problemMap.put(37
                , "org.uma.jmetal.problem.multiobjective.cec2009Competition.UF9"
                , "jmetal-problem/src/test/resources/pareto_fronts/UF9.pf");
        problemMap.put(38
                , "org.uma.jmetal.problem.multiobjective.cec2009Competition.UF10"
                , "jmetal-problem/src/test/resources/pareto_fronts/UF10.pf");


        return problemMap;
    }
}
