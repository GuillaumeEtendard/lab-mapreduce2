package com.opstty;

import com.opstty.job.*;
import org.apache.hadoop.util.ProgramDriver;

public class AppDriver {
    public static void main(String argv[]) {
        int exitCode = -1;
        ProgramDriver programDriver = new ProgramDriver();

        try {
            programDriver.addClass("wordcount", WordCount.class,
                    "A map/reduce program that counts the words in the input files.");
            programDriver.addClass("districts_with_trees", District.class,
                    "A map/reduce program that displays the list of distinct containing trees.");
            programDriver.addClass("existing_species", Species.class,
                    "A map/reduce program that displays the list of different species trees.");
            programDriver.addClass("number_by_species", NumberBySpecies.class,
                    "A map/reduce program that calculates the number of trees of each species.");
            programDriver.addClass("max_height_per_specie", MaxHeightPerSpecie.class,
                    "A map/reduce program that calculates the height of the tallest tree of each kind.");
            programDriver.addClass("height_sorted", SortHeight.class,
                    "A map/reduce program that sort the trees height from smallest to largest.");
            programDriver.addClass("district_oldest_tree", OldestTree.class,
                    "A map/reduce program that displays the district where the oldest tree is.");
            programDriver.addClass("district_most_trees", DistrictMostTrees.class,
                    "A map/reduce program that displays the district that contains the most trees.");

            exitCode = programDriver.run(argv);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        System.exit(exitCode);
    }
}
