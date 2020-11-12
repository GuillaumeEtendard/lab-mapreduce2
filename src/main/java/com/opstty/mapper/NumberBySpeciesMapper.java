package com.opstty.mapper;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class NumberBySpeciesMapper extends Mapper<Object, Text, Text, IntWritable> {
    private final static IntWritable one = new IntWritable(1);
    public void map(Object key, Text value, Context context)
            throws IOException, InterruptedException {
        if(!value.toString().contains("GEOPOINT")){
            Text specie = new Text(value.toString().split(";")[3]);
            context.write(specie, one);
        }
    }
}
