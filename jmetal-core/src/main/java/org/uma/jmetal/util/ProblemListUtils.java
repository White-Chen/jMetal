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
                , "problem.multiobjective.zdt.ZDT1"
                , "problem/src/test/resources/pareto_fronts/ZDT1.pf");
        problemMap.put(1
                , "problem.multiobjective.zdt.ZDT2"
                , "problem/src/test/resources/pareto_fronts/ZDT2.pf");
        problemMap.put(2
                , "problem.multiobjective.zdt.ZDT3"
                , "problem/src/test/resources/pareto_fronts/ZDT3.pf");
        problemMap.put(3
                , "problem.multiobjective.zdt.ZDT4"
                , "problem/src/test/resources/pareto_fronts/ZDT4.pf");
        problemMap.put(4
                , "problem.multiobjective.zdt.ZDT6"
                , "problem/src/test/resources/pareto_fronts/ZDT6.pf");

        /*DTLZ*/
        problemMap.put(5
                , "problem.multiobjective.dtlz.DTLZ1"
                , "problem/src/test/resources/pareto_fronts/DTLZ1.3D.pf");
        problemMap.put(6
                , "problem.multiobjective.dtlz.DTLZ2"
                , "problem/src/test/resources/pareto_fronts/DTLZ2.3D.pf");
        problemMap.put(7
                , "problem.multiobjective.dtlz.DTLZ3"
                , "problem/src/test/resources/pareto_fronts/DTLZ3.3D.pf");
        problemMap.put(8
                , "problem.multiobjective.dtlz.DTLZ4"
                , "problem/src/test/resources/pareto_fronts/DTLZ4.3D.pf");
        problemMap.put(9
                , "problem.multiobjective.dtlz.DTLZ5"
                , "problem/src/test/resources/pareto_fronts/DTLZ5.3D.pf");
        problemMap.put(10
                , "problem.multiobjective.dtlz.DTLZ6"
                , "problem/src/test/resources/pareto_fronts/DTLZ6.3D.pf");
        problemMap.put(11
                , "problem.multiobjective.dtlz.DTLZ7"
                , "problem/src/test/resources/pareto_fronts/DTLZ7.3D.pf");

        /*WFG*/
        problemMap.put(12
                , "problem.multiobjective.wfg.WFG1"
                , "problem/src/test/resources/pareto_fronts/WFG1.2D.pf");
        problemMap.put(13
                , "problem.multiobjective.wfg.WFG2"
                , "problem/src/test/resources/pareto_fronts/WFG2.2D.pf");
        problemMap.put(14
                , "problem.multiobjective.wfg.WFG3"
                , "problem/src/test/resources/pareto_fronts/WFG3.2D.pf");
        problemMap.put(15
                , "problem.multiobjective.wfg.WFG4"
                , "problem/src/test/resources/pareto_fronts/WFG4.2D.pf");
        problemMap.put(16
                , "problem.multiobjective.wfg.WFG5"
                , "problem/src/test/resources/pareto_fronts/WFG5.2D.pf");
        problemMap.put(17
                , "problem.multiobjective.wfg.WFG6"
                , "problem/src/test/resources/pareto_fronts/WFG6.2D.pf");
        problemMap.put(18
                , "problem.multiobjective.wfg.WFG7"
                , "problem/src/test/resources/pareto_fronts/WFG7.2D.pf");
        problemMap.put(19
                , "problem.multiobjective.wfg.WFG8"
                , "problem/src/test/resources/pareto_fronts/WFG8.2D.pf");
        problemMap.put(20
                , "problem.multiobjective.wfg.WFG9"
                , "problem/src/test/resources/pareto_fronts/WFG9.2D.pf");

        /*LZ09*/
        problemMap.put(21
                , "problem.multiobjective.lz09.LZ09F1"
                , "problem/src/test/resources/pareto_fronts/LZ09_F1.pf");
        problemMap.put(22
                , "problem.multiobjective.lz09.LZ09F2"
                , "problem/src/test/resources/pareto_fronts/LZ09_F2.pf");
        problemMap.put(23
                , "problem.multiobjective.lz09.LZ09F3"
                , "problem/src/test/resources/pareto_fronts/LZ09_F3.pf");
        problemMap.put(24
                , "problem.multiobjective.lz09.LZ09F4"
                , "problem/src/test/resources/pareto_fronts/LZ09_F4.pf");
        problemMap.put(25
                , "problem.multiobjective.lz09.LZ09F5"
                , "problem/src/test/resources/pareto_fronts/LZ09_F5.pf");
        problemMap.put(26
                , "problem.multiobjective.lz09.LZ09F6"
                , "problem/src/test/resources/pareto_fronts/LZ09_F6.pf");
        problemMap.put(27
                , "problem.multiobjective.lz09.LZ09F7"
                , "problem/src/test/resources/pareto_fronts/LZ09_F7.pf");
        problemMap.put(28
                , "problem.multiobjective.lz09.LZ09F8"
                , "problem/src/test/resources/pareto_fronts/LZ09_F8.pf");
        problemMap.put(29
                , "problem.multiobjective.lz09.LZ09F9"
                , "problem/src/test/resources/pareto_fronts/LZ09_F9.pf");

        /*CEC09*/
        problemMap.put(30
                , "problem.multiobjective.cec2009Competition.UF1"
                , "problem/src/test/resources/pareto_fronts/UF1.pf");
        problemMap.put(31
                , "problem.multiobjective.cec2009Competition.UF2"
                , "problem/src/test/resources/pareto_fronts/UF2.pf");
        problemMap.put(32
                , "problem.multiobjective.cec2009Competition.UF3"
                , "problem/src/test/resources/pareto_fronts/UF3.pf");
/*        problemMap.put( 33
                ,"problem.multiobjective.cec2009Competition.UF4"
                ,"problem/src/test/resources/pareto_fronts/UF4.pf");*/
        problemMap.put(33
                , "problem.multiobjective.cec2009Competition.UF5"
                , "problem/src/test/resources/pareto_fronts/UF5.pf");
        problemMap.put(34
                , "problem.multiobjective.cec2009Competition.UF6"
                , "problem/src/test/resources/pareto_fronts/UF6.pf");
        problemMap.put(35
                , "problem.multiobjective.cec2009Competition.UF7"
                , "problem/src/test/resources/pareto_fronts/UF7.pf");
        problemMap.put(36
                , "problem.multiobjective.cec2009Competition.UF8"
                , "problem/src/test/resources/pareto_fronts/UF8.pf");
        problemMap.put(37
                , "problem.multiobjective.cec2009Competition.UF9"
                , "problem/src/test/resources/pareto_fronts/UF9.pf");
        problemMap.put(38
                , "problem.multiobjective.cec2009Competition.UF10"
                , "problem/src/test/resources/pareto_fronts/UF10.pf");


        return problemMap;
    }
}
