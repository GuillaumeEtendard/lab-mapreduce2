package com.opstty.mapper;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class MaxHeightPerSpecieMapper extends Mapper<Object, Text, Text, DoubleWritable> {

    public void map(Object key, Text value, Context context)
            throws IOException, InterruptedException {
        if(!value.toString().contains("GEOPOINT")){
            try {
                Text specie = new Text(value.toString().split(";")[3]);
                DoubleWritable height = new DoubleWritable(Double.parseDouble(value.toString().split(";")[6]));
                context.write(specie, height);
            } catch (Exception ignored) {
            }
        }
    }
}
