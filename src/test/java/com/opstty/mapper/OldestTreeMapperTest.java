package com.opstty.mapper;

import com.opstty.writable.AgeDistrictWritable;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.Calendar;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class OldestTreeMapperTest {
    @Mock
    private Mapper.Context context;
    private OldestTreeMapper oldestTreeMapper;

    @Before
    public void setup() {
        this.oldestTreeMapper = new OldestTreeMapper();
    }

    @Test
    public void testMap() throws IOException, InterruptedException {
        String value = "(48.857140829, 2.29533455314);7;Maclura;pomifera;Moraceae;1935;15.0;;Quai Branly, avenue de La Motte-Piquet, avenue de la Bourdonnais, avenue de Suffren;Oranger des Osages;;6;Parc du Champs de Mars";
        value += "\n(48.857140829, 2.29533455314);20;Maclura;pomifera;Moraceae;1900;20.0;;Quai Branly, avenue de La Motte-Piquet, avenue de la Bourdonnais, avenue de Suffren;Oranger des Osages;;6;Parc du Champs de Mars";
        value += "\n(48.857140829, 2.29533455314);15;Maclura;pomifera;Moraceae;2000;17.0;;Quai Branly, avenue de La Motte-Piquet, avenue de la Bourdonnais, avenue de Suffren;Oranger des Osages;;6;Parc du Champs de Mars";

        String[] lines = value.split("\n");

        for (String line : lines) {
            this.oldestTreeMapper.map(null, new Text(line), this.context);
        }

        AgeDistrictWritable ageDistrict = new AgeDistrictWritable(
                new IntWritable(20),
                new IntWritable(Calendar.getInstance().get(Calendar.YEAR) - 1900)
        );

        verify(this.context, times(1))
                .write(new IntWritable(1), ageDistrict);
    }
}