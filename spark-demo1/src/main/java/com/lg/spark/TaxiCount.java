package com.lg.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.PairFlatMapFunction;
import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;

import java.util.Iterator;

/**
 * <p>
 * description:纽约出租车计算
 * </p>
 * Created on 2017/7/27 19:36
 *
 * @author leiguang
 */
public class TaxiCount {

    static String PATH = "";

    public static void main(String[] args){


        SparkConf conf = new SparkConf().setAppName("taxi count").setMaster("local");
        JavaSparkContext context = new JavaSparkContext(conf);
        JavaRDD<String> lines = context.textFile(PATH);

        JavaPairRDD<String, Integer> result = lines.mapToPair(new PairFunction<String, String, Integer>() {
            public Tuple2<String, Integer> call(String s) throws Exception {
                return null;
            }
        });
        result = lines.flatMapToPair(new PairFlatMapFunction<String, String, Integer>() {
            public Iterator<Tuple2<String, Integer>> call(String s) throws Exception {
                return null;
            }
        });
    }

}
