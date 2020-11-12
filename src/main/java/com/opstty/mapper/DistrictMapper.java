package com.opstty.mapper;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class DistrictMapper extends Mapper<Object, Text, Text, NullWritable> {

    public void map(Object key, Text value, Context context)
            throws IOException, InterruptedException {
        if(!value.toString().contains("GEOPOINT")){
            Text district = new Text(value.toString().split(";")[1]);
            context.write(district, NullWritable.get());
        }
    }
}
