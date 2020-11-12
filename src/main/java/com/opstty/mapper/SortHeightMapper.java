package com.opstty.mapper;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class SortHeightMapper extends Mapper<Object, Text, DoubleWritable, Text> {

    public void map(Object key, Text value, Context context)
            throws IOException, InterruptedException {
        if (!value.toString().contains("GEOPOINT")) {
            try {
                Text geoPoint = new Text(value.toString().split(";")[0]);
                DoubleWritable height = new DoubleWritable(Double.parseDouble(value.toString().split(";")[6]));
                context.write(height, geoPoint);
            } catch (Exception ignored) {
            }
        }
    }
}
