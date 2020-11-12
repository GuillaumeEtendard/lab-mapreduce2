package com.opstty.mapper;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class DistrictMostTreesMapper extends Mapper<Object, Text, IntWritable, IntWritable> {
    private final static IntWritable one = new IntWritable(1);

    public void map(Object key, Text value, Context context)
            throws IOException, InterruptedException {
        if (!value.toString().contains("GEOPOINT")) {
            try {
                context.write(new IntWritable(Integer.parseInt(value.toString().split(";")[1])), one);
            } catch (Exception ignored) {
            }
        }
    }
}
