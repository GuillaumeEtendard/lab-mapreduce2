package com.opstty.writable;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Objects;

public class DistrictTreesNumberWritable implements WritableComparable<DistrictTreesNumberWritable> {
    private IntWritable district, treesNumber;

    public DistrictTreesNumberWritable() {
        this.district = new IntWritable();
        this.treesNumber = new IntWritable();
    }

    public DistrictTreesNumberWritable(IntWritable district, IntWritable treesNumber) {
        this.district = district;
        this.treesNumber = treesNumber;
    }

    public void set(IntWritable district, IntWritable treesNumber) {
        this.district = district;
        this.treesNumber = treesNumber;
    }

    public IntWritable getDistrict() {
        return district;
    }

    public IntWritable getTreesNumber() {
        return treesNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DistrictTreesNumberWritable that = (DistrictTreesNumberWritable) o;
        return Objects.equals(district, that.district) &&
                Objects.equals(treesNumber, that.treesNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(district, treesNumber);
    }

    @Override
    public String toString() {
        return "TreesNumberDistrictWritable{" +
                "district=" + district +
                ", treesNumber=" + treesNumber +
                '}';
    }

    @Override
    public int compareTo(DistrictTreesNumberWritable o) {
        return treesNumber.compareTo(o.getTreesNumber());
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        district.write(dataOutput);
        treesNumber.write(dataOutput);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        district.readFields(dataInput);
        treesNumber.readFields(dataInput);
    }
}
