package com.opstty.mapper;

import com.opstty.writable.DistrictTreesNumberWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class DistrictMostTreesMapper2 extends Mapper<Object, Text, IntWritable, DistrictTreesNumberWritable> {
    private final static IntWritable one = new IntWritable(1);

    public void map(Object key, Text value, Context context)
            throws IOException, InterruptedException {
        try {
            if (!value.toString().isEmpty()) {
                IntWritable district = new IntWritable(Integer.parseInt(value.toString().split("\t")[0]));
                IntWritable number = new IntWritable(Integer.parseInt(value.toString().split("\t")[1]));
                DistrictTreesNumberWritable districtTreesNumberWritable = new DistrictTreesNumberWritable(district, number);
                context.write(one, districtTreesNumberWritable);
            }
        } catch (Exception ignored) {
        }
    }
}
