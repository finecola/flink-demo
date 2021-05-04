package com.choco.manager;

import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;

/**
 * @author: choco
 * @date: 2021/5/4 14:02
 */
public class WordCount {
    public static void main(String[] args) {
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        String inputPath="/hello.txt";

        DataSet<String> inputDataSet = env.readTextFile(inputPath);
    }
}
