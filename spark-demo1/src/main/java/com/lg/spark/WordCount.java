package com.lg.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * <p>
 * description:
 * </p>
 * Created on 2017/7/24 18:18
 *
 * @author leiguang
 */
public class WordCount {

    public static void main(String[] args) throws  Exception{
        SparkConf sparkConf = new SparkConf().setAppName("wordCount").setMaster("local");
        JavaSparkContext ctx = new JavaSparkContext(sparkConf);
        JavaRDD<String> lines = ctx.textFile("C:\\Users\\richstone\\Desktop\\hdfs-site.xml", 1);

        System.out.println("-------------------------------------------------------------------");
        JavaRDD<String> words = lines.flatMap(new FlatMapFunction<String, String>() {
            public Iterator<String> call(String s) throws Exception {
                return Arrays.asList(s.split("\\s+")).iterator();
            }
        });

        System.out.println(words.name());
        System.out.println("...............................................................");
        JavaPairRDD<String, Integer> paris = words.mapToPair(new PairFunction<String, String, Integer>() {
            public Tuple2<String, Integer> call(String word) throws Exception {
                return new Tuple2<String, Integer>(word, 1);
            }
        });

        JavaPairRDD<String, Integer>  wordcount = paris.reduceByKey(new Function2<Integer, Integer, Integer>() {
            public Integer call(Integer integer, Integer integer2) throws Exception {
                return integer + integer2;
            }
        });

        for (Tuple2<String, Integer> pair : wordcount.collect()) {
            System.out.println(pair._1+":"+pair._2);
        }

        ctx.close();

    }

}
