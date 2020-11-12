package com.opstty.writable;


import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Objects;

public class AgeDistrictWritable implements WritableComparable<AgeDistrictWritable> {
    private IntWritable district, age;

    public AgeDistrictWritable() {
        this.district = new IntWritable();
        this.age = new IntWritable();
    }

    public AgeDistrictWritable(IntWritable district, IntWritable age) {
        this.district = district;
        this.age = age;
    }

    public void set(IntWritable district, IntWritable age) {
        this.district = district;
        this.age = age;
    }

    public IntWritable getDistrict() {
        return district;
    }

    public IntWritable getAge() {
        return age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AgeDistrictWritable that = (AgeDistrictWritable) o;
        return Objects.equals(district, that.district) &&
                Objects.equals(age, that.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(district, age);
    }

    @Override
    public String toString() {
        return "AgeDistrictWritable{" +
                "district=" + district +
                ", age=" + age +
                '}';
    }

    @Override
    public int compareTo(AgeDistrictWritable o) {
        return age.compareTo(o.getAge());
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        district.write(dataOutput);
        age.write(dataOutput);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        district.readFields(dataInput);
        age.readFields(dataInput);
    }
}
