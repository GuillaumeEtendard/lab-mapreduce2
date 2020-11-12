package com.opstty.reducer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
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
public class DistrictMostTreesReducerTest {
    @Mock
    private Reducer.Context context;
    private DistrictMostTreesReducer districtMostTreesReducer;

    @Before
    public void setup() {
        this.districtMostTreesReducer = new DistrictMostTreesReducer();
    }

    @Test
    public void testReduce() throws IOException, InterruptedException {
        Iterable<IntWritable> values = Arrays.asList(new IntWritable(2), new IntWritable(4), new IntWritable(1));
        this.districtMostTreesReducer.reduce(new IntWritable(4), values, this.context);
        verify(this.context).write(new IntWritable(4), new IntWritable(7));
    }
}
