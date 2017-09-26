package com.multithread;

import com.sequential.NeighborScore;

import java.io.FileNotFoundException;
import java.util.HashMap;

/**
 * Created by futailin on 9/25/17.
 */
/*
public class TransformationThread implements Runnable {

    private String filename;

    public TransformationThread(String filename) {
        this.filename = filename;
    }


    public void run()  {
        NeighborScore obj2 = new NeighborScore(filename);

        HashMap<String, Double> kNeighborScore = obj2.KNeighborScore(2);

        try{
            obj2.WriteToCsv(kNeighborScore);
        } catch (FileNotFoundException e) {

        }

    }


}
*/