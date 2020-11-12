package com.opstty.mapper;

import com.opstty.writable.DistrictTreesNumberWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class DistrictMostTreesMapper2Test {
    @Mock
    private Mapper.Context context;
    private DistrictMostTreesMapper2 districtMostTreesMapper2;

    @Before
    public void setup() {
        this.districtMostTreesMapper2 = new DistrictMostTreesMapper2();
    }

    @Test
    public void testMap() throws IOException, InterruptedException {
        String value = "7\t1";
        IntWritable district = new IntWritable(7);
        IntWritable number = new IntWritable(1);
        DistrictTreesNumberWritable districtTreesNumberWritable = new DistrictTreesNumberWritable(district, number);

        this.districtMostTreesMapper2.map(null, new Text(value), this.context);
        verify(this.context, times(1))
                .write(new IntWritable(1), districtTreesNumberWritable);
    }
}