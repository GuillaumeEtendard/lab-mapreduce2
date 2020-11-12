package com.opstty.mapper;

import com.opstty.writable.AgeDistrictWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.Calendar;

public class OldestTreeMapper extends Mapper<Object, Text, IntWritable, AgeDistrictWritable> {
    private final static IntWritable one = new IntWritable(1);

    public void map(Object key, Text value, Context context)
            throws IOException, InterruptedException {
        if (!value.toString().contains("GEOPOINT")) {
            try {
                AgeDistrictWritable ageDistrict = new AgeDistrictWritable(
                        new IntWritable(Integer.parseInt(value.toString().split(";")[1])),
                        new IntWritable(Calendar.getInstance().get(Calendar.YEAR) - Integer.parseInt(value.toString().split(";")[5]))
                );
                context.write(one, ageDistrict);
            } catch (Exception ignored) {
            }
        }
    }
}
