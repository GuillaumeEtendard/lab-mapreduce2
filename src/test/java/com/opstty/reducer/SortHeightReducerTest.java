package com.opstty.reducer;

import org.apache.hadoop.io.DoubleWritable;
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
public class SortHeightReducerTest {
    @Mock
    private Reducer.Context context;
    private SortHeightReducer sortHeightReducer;

    @Before
    public void setup() {
        this.sortHeightReducer = new SortHeightReducer();
    }

    @Test
    public void testReduce() throws IOException, InterruptedException {
        Text geoPoint = new Text("(48.857140829, 2.29533455314)");
        DoubleWritable height = new DoubleWritable(20.0);
        Iterable<Text> values = Arrays.asList(new Text(geoPoint));
        this.sortHeightReducer.reduce(height, values, this.context);
        verify(this.context).write(new Text(geoPoint), height);
    }
}
