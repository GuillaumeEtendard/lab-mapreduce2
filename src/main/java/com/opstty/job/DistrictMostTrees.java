package com.opstty.job;

import com.opstty.mapper.DistrictMostTreesMapper;
import com.opstty.mapper.DistrictMostTreesMapper2;
import com.opstty.reducer.DistrictMostTreesReducer;
import com.opstty.reducer.DistrictMostTreesReducer2;
import com.opstty.writable.DistrictTreesNumberWritable;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

public class DistrictMostTrees {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
        if (otherArgs.length < 2) {
            System.err.println("Usage: mosttrees <in> [<in>...] <out>");
            System.exit(2);
        }
        Job job = Job.getInstance(conf, "mosttrees");
        job.setJarByClass(DistrictMostTrees.class);
        job.setMapperClass(DistrictMostTreesMapper.class);
        job.setReducerClass(DistrictMostTreesReducer.class);
        job.setCombinerClass(DistrictMostTreesReducer.class);

        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(IntWritable.class);

        for (int i = 0; i < otherArgs.length - 1; ++i) {
            FileInputFormat.addInputPath(job, new Path(otherArgs[i]));
        }
        Path tempOutputPath = new Path("districtmosttrees_temp");
        FileOutputFormat.setOutputPath(job,
                tempOutputPath);

        job.waitForCompletion(true);


        Configuration conf2 = new Configuration();
        Job job2 = Job.getInstance(conf2, "mosttrees2");
        job2.setJarByClass(DistrictMostTrees.class);
        job2.setMapperClass(DistrictMostTreesMapper2.class);
        job2.setReducerClass(DistrictMostTreesReducer2.class);

        job2.setMapOutputKeyClass(IntWritable.class);
        job2.setMapOutputValueClass(DistrictTreesNumberWritable.class);

        job2.setOutputKeyClass(IntWritable.class);
        job2.setOutputValueClass(NullWritable.class);

        FileInputFormat.addInputPath(job2, tempOutputPath);
        FileOutputFormat.setOutputPath(job2,
                new Path(otherArgs[otherArgs.length - 1]));

        tempOutputPath.getFileSystem(conf).deleteOnExit(tempOutputPath);
        System.exit(job2.waitForCompletion(true) ? 0 : 1);
    }
}
