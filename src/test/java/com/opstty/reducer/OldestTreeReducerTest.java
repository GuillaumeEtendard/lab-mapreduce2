package com.opstty.reducer;

import com.opstty.writable.AgeDistrictWritable;
import org.apache.hadoop.io.DoubleWritable;
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
public class OldestTreeReducerTest {
    @Mock
    private Reducer.Context context;
    private OldestTreeReducer oldestTreeReducer;

    @Before
    public void setup() {
        this.oldestTreeReducer = new OldestTreeReducer();
    }

    @Test
    public void testReduce() throws IOException, InterruptedException {
        Iterable<AgeDistrictWritable> values = Arrays.asList(
                new AgeDistrictWritable(new IntWritable(15), new IntWritable(60)),
                new AgeDistrictWritable(new IntWritable(20), new IntWritable(70))
        );
        this.oldestTreeReducer.reduce(new IntWritable(1), values, this.context);
        verify(this.context).write(new IntWritable(20), NullWritable.get());
    }
}
