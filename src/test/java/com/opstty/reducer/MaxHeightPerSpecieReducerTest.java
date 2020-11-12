package com.opstty.reducer;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.FloatWritable;
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
public class MaxHeightPerSpecieReducerTest {
    @Mock
    private Reducer.Context context;
    private MaxHeightPerSpecieReducer maxHeightPerSpecieReducer;

    @Before
    public void setup() {
        this.maxHeightPerSpecieReducer = new MaxHeightPerSpecieReducer();
    }

    @Test
    public void testReduce() throws IOException, InterruptedException {
        String key = "pomifera";
        Iterable<DoubleWritable> values = Arrays.asList(new DoubleWritable(26.0), new DoubleWritable(13.0));
        this.maxHeightPerSpecieReducer.reduce(new Text(key), values, this.context);
        verify(this.context).write(new Text(key), new DoubleWritable(26.0));
    }
}
