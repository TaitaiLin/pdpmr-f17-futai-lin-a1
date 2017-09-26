package com.sequential;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by futailin on 9/25/17.
 */
public class NeighborScoreSequential {

    public static void main(String[] args) throws FileNotFoundException {
        long d1 = System.nanoTime();

        NeighborScore obj1 = new NeighborScore("./books/");

        //HashMap<String, Double> map= obj1.LetterScore();
        //System.out.println(map.get("color"));
        //System.out.println(map.size());
        HashMap<String, Double> kNeighborScore = obj1.KNeighborScore(2);
        System.out.println(kNeighborScore.get("apple"));
        obj1.WriteToCsv(kNeighborScore);
        long d2 = System.nanoTime();
        System.out.println((d2 - d1) / 1000000000.0);
    }
}
