package com.opstty.reducer;

import com.opstty.writable.AgeDistrictWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class OldestTreeReducer extends Reducer<IntWritable, AgeDistrictWritable, IntWritable, NullWritable> {
    private AgeDistrictWritable result = new AgeDistrictWritable();

    public void reduce(IntWritable key, Iterable<AgeDistrictWritable> values, Context context)
            throws IOException, InterruptedException {
        for (AgeDistrictWritable val : values) {
            if (val.getAge().get() > result.getAge().get()) {
                result.set(new IntWritable(val.getDistrict().get()), new IntWritable(val.getAge().get()));
            }
        }
        context.write(result.getDistrict(), NullWritable.get());
    }
}
