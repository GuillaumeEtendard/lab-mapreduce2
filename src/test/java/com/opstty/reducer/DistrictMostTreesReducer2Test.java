package com.opstty.reducer;

import com.opstty.writable.DistrictTreesNumberWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.Arrays;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class DistrictMostTreesReducer2Test {
    @Mock
    private Reducer.Context context;
    private DistrictMostTreesReducer2 districtMostTreesReducer2;

    @Before
    public void setup() {
        this.districtMostTreesReducer2 = new DistrictMostTreesReducer2();
    }

    @Test
    public void testReduce() throws IOException, InterruptedException {
        Iterable<DistrictTreesNumberWritable> values = Arrays.asList(
                new DistrictTreesNumberWritable(new IntWritable(10), new IntWritable(15)),
                new DistrictTreesNumberWritable(new IntWritable(20), new IntWritable(10))
        );
        this.districtMostTreesReducer2.reduce(new IntWritable(1), values, this.context);
        verify(this.context).write(new IntWritable(10), NullWritable.get());
    }
}
