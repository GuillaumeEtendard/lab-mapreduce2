package com.opstty.reducer;

import com.opstty.writable.DistrictTreesNumberWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class DistrictMostTreesReducer2 extends Reducer<IntWritable, DistrictTreesNumberWritable, IntWritable, NullWritable> {
    private DistrictTreesNumberWritable result = new DistrictTreesNumberWritable();

    public void reduce(IntWritable key, Iterable<DistrictTreesNumberWritable> values, Context context)
            throws IOException, InterruptedException {
        for (DistrictTreesNumberWritable val : values) {
            if (val.getTreesNumber().get() > result.getTreesNumber().get()) {
                result.set(new IntWritable(val.getDistrict().get()), new IntWritable(val.getTreesNumber().get()));
            }
        }
        context.write(result.getDistrict(), NullWritable.get());
    }
}
