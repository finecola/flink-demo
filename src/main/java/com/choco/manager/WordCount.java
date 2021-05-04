package com.choco.manager;

import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

/**
 * @author: choco
 * @date: 2021/5/4 14:02
 */
@Slf4j
public class WordCount {
    public static void main(String[] args) throws Exception {
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        String inputPath = "/hello.txt";

        DataSet<String> inputDataSet = env.readTextFile(inputPath);

        // 0,1都表示位置,二元组的第0个
        DataSet<Tuple2<String, Integer>> resultSet = inputDataSet.flatMap(new MyFlatMap())
                .groupBy(0)
                .sum(1);

        resultSet.print();
    }

    /**
     * 输入string,输出tuple2的集合
     */
    public static class MyFlatMap implements FlatMapFunction<String, Tuple2<String, Integer>> {

        @Override
        public void flatMap(String s, Collector<Tuple2<String, Integer>> collector) throws Exception {
            String[] words = s.split(" ");
            for (String word:words){
                collector.collect(new Tuple2<>(word, 1));
            }
        }
    }
}
