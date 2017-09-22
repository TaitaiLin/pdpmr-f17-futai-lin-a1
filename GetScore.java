/**
 * Created by futailin on 9/22/17.
 */
/**
 * Created by futailin on 9/22/17.
 */
/**
 * Created by futailin on 9/21/17.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.ArrayList;
import java.io.PrintWriter;



public class GetScore {
    // tokenize text file
    public static ArrayList<String> FileWordSplit(String filename) throws FileNotFoundException {
        File f = new File(filename);
        Scanner scan = new Scanner(f);
        scan.useDelimiter("[^A-Za-z]+");
        ArrayList<String> corpus = new ArrayList<String>();
        while(scan.hasNext()) {
            String out = scan.next().toLowerCase();
            corpus.add(out);
        }
        return corpus;
    }

    // calculate the percentage of letter in the documents
    public static double LetterPercent(Character letter, ArrayList<String> wordsList) {
        String str = String.join("", wordsList);
        char[] c = str.toCharArray();
        double counter = 0;

        for (int i = 0; i < c.length; i++) {
            if (letter == c[i]) {
                counter ++;
            }
        }
        double letterPercent = counter / c.length * 100;

        return letterPercent;
    }


    // calculate WordScore

    public static int WordScore(String word, ArrayList<String> wordsList)  {
        char[] w = word.toCharArray();
        int totalScore = 0;
        for (int i = 0; i < w.length; i++) {

            double letterPercent = LetterPercent(w[i], wordsList);

            if (letterPercent > 10) {
                totalScore += 0;
            } else if (letterPercent < 10 & letterPercent >= 8) {
                totalScore += 1;
            } else if (letterPercent < 8 & letterPercent >= 6) {
                totalScore += 2;
            } else if (letterPercent < 6 & letterPercent >= 4) {
                totalScore += 4;
            } else if (letterPercent < 4 & letterPercent >= 2) {
                totalScore += 8;
            } else if (letterPercent < 2 & letterPercent >= 1) {
                totalScore += 16;
            } else {
                totalScore += 32;
            }

        }

        return totalScore;
    }

    // get the index of word that appear in the documents

    public static ArrayList<Integer> GetWordPosition(String word, ArrayList<String> wordList) {
        ArrayList<Integer> pos = new ArrayList<Integer>();
        for (int i = 0; i < wordList.size(); i++) {
            if (wordList.get(i).equals(word)) {
                pos.add(i);
            }
        }
        return pos;
    }

    // Get the KNeighborScore method

    public static double KNeighborScore(String word, int k, ArrayList<String> wordList) {
        ArrayList<Integer> pos = GetWordPosition(word, wordList);
        double totalScores = 0;
        for (int i: pos) {
            int score = 0;
            for (int j = 1; j <= k; j++) {
                if (i - j < 0) {
                    break;
                } else {
                    String left = wordList.get(i - j);
                    int lScore = WordScore(left, wordList);
                    score += lScore;
                }

            }

            for (int j = 1; j <= k; j++) {
                if (i + j > pos.size() - 1) {
                    break;
                } else {
                    String right = wordList.get(i + j);
                    Integer rScore = WordScore(right, wordList);
                    score += rScore;
                }

            }
            totalScores += score;
        }

        return totalScores / pos.size();

    }



    public static void main(String[] args) throws FileNotFoundException {
        GetScore x = new GetScore();
        ArrayList<String> wordList = x.FileWordSplit("Pride_and_Prejudice.txt");
        System.out.println(x.GetWordPosition("good", wordList));
        System.out.println(x.WordScore("object", wordList));
        System.out.println(x.KNeighborScore("object", 2,  wordList));




        // Generate the csv file

        HashSet<String> set = new HashSet<String>(wordList);
        ArrayList<String> wordSet = new ArrayList<String>(set);
        System.out.println(wordSet.get(0));


        PrintWriter pw = new PrintWriter(new File("result.csv"));
        StringBuilder sb = new StringBuilder();
        sb.append("Word");
        sb.append(',');
        sb.append("2-NeighborScore");
        sb.append('\n');
        for (int i = 0; i < 10; i++) {
            String word = wordSet.get(i);
            sb.append(word);
            sb.append(',');
            sb.append(x.KNeighborScore(word, 2, wordList));
            sb.append('\n');
        }
        pw.write(sb.toString());
        pw.close();

    }


}









