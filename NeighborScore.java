package com.sequential; /**
 * Created by futailin on 9/25/17.
 */
import java.io.*;
import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;


/**
 * Created by futailin on 9/25/17.
 */

public class NeighborScore {

    private String pathName;
    private ArrayList<String> wordsArray = new ArrayList<String>();

    // use construct method to get words array of the corpus
    public NeighborScore(String pathName) {
        this.pathName = pathName;
        File f = new File(pathName);
        Scanner in;
        for (File file: f.listFiles()) {
            try {
                in = new Scanner(file);
                in.useDelimiter("[^A-Za-z]+");

                while (in.hasNext()) {
                    String word = in.next().toLowerCase();
                    wordsArray.add(word);
                }
            } catch (FileNotFoundException e) {
                System.out.println(file + " was not found.");
            }

        }
    }



    // get WordScore using the rule according the question
    public HashMap<String, Double> WordScore() {

        HashMap<String, Double> map = new HashMap<String, Double>();
        HashMap<String, Double> wordMap = new HashMap<String, Double>();


        double count = 0;

        for (String w : wordsArray) {
            if (!wordMap.containsKey(w)) {
                wordMap.put(w, 0.0);
            }
            char[] c = w.toCharArray();
            for (char i : c) {
                String letter = Character.toString(i);
                if (map.containsKey(letter)) {
                    map.put(letter, map.get(letter) + 1.0);
                } else {
                    map.put(letter, 1.0);
                }
                count++;
            }
        }

        for (String letter : map.keySet()) {
            map.put(letter, map.get(letter) / count * 100);

            if (map.get(letter) > 10) {
                map.put(letter, 0.0);
            } else if (map.get(letter) < 10 & map.get(letter) >= 8) {
                map.put(letter, 1.0);
            } else if (map.get(letter) < 8 & map.get(letter) >= 6) {
                map.put(letter, 2.0);
            } else if (map.get(letter) < 6 & map.get(letter) >= 4) {
                map.put(letter, 4.0);
            } else if (map.get(letter) < 4 & map.get(letter) >= 2) {
                map.put(letter, 8.0);
            } else if (map.get(letter) < 2 & map.get(letter) >= 1) {
                map.put(letter, 16.0);
            } else {
                map.put(letter, 32.0);
            }

        }


        for (String letter : map.keySet()) {
            System.out.println(letter + " " + map.get(letter));

        }

        for (String x : wordMap.keySet()) {
            char[] c = x.toCharArray();
            for (char i : c) {
                String j = Character.toString(i);
                wordMap.put(x, wordMap.get(x) + map.get(j));
            }
        }


        System.out.println("total letter count is: " + count);

        return wordMap;
    }

    // calculate k-neighbor score of words
    public HashMap<String, Double> KNeighborScore(int k) {
        HashMap<String, Double> kNeighborScoreMap = new HashMap<String, Double>();
        HashMap<String, Double> kNeighborNumber = new HashMap<String, Double>();
        HashMap<String, Double> wordScoreMap = WordScore();

        for (int i = 0; i < wordsArray.size(); i++) {
            String word = wordsArray.get(i);
            if (!kNeighborScoreMap.containsKey(word)) {
                kNeighborScoreMap.put(word, 0.0);
                kNeighborNumber.put(word, 0.0);
            }


            for (int j = 1; j <= k; j++) {
                if (i - j < 0) {
                    break;
                } else {
                    String neighborWord = wordsArray.get(i-j);
                    kNeighborScoreMap.put(word, kNeighborScoreMap.get(word) + wordScoreMap.get(neighborWord));
                }
                kNeighborNumber.put(word, kNeighborNumber.get(word) + 1.0);
            }
            for (int j = 1; j <= k; j++) {
                if (i + j > wordsArray.size() - 1) {
                    break;
                } else {
                    String neighborWord = wordsArray.get(i+j);
                    kNeighborScoreMap.put(word, kNeighborScoreMap.get(word) + wordScoreMap.get(neighborWord));
                }
                kNeighborNumber.put(word, kNeighborNumber.get(word) + 1.0);
            }

        }
        for (String w : kNeighborScoreMap.keySet()) {
            double countNumber = kNeighborNumber.get(w);
            kNeighborScoreMap.put(w, kNeighborScoreMap.get(w) / countNumber);
        }
        return kNeighborScoreMap;
    }

    // write the map result to csv file.
    public void WriteToCsv(HashMap<String, Double> kNeighborScore) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(new File("result.csv"));
        StringBuilder sb = new StringBuilder();
        sb.append("Word");
        sb.append(',');
        sb.append("2-com.sequential.NeighborScore");
        sb.append('\n');
        for (String word: kNeighborScore.keySet()) {
            Double score = kNeighborScore.get(word);
            sb.append(word);
            sb.append(",");
            sb.append(score);
            sb.append("\n");
        }

        pw.write(sb.toString());
        pw.close();
    }

}
