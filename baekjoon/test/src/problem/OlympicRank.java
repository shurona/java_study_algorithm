package problem;

import utils.BaekAlgoStudy;

import java.util.*;
import java.io.*;

public class OlympicRank implements BaekAlgoStudy {
    public void solution() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String firstLine = bufferedReader.readLine();

        String[] ff = firstLine.split(" ");
        int ct = Integer.parseInt(ff[0]);
        int foundCountry = Integer.parseInt(ff[1]);

        String[] inputs = new String[ct];
        for (int i = 0; i < ct; i++) {
            inputs[i] = bufferedReader.readLine();
        }

        Arrays.sort(inputs, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {

                int oneGold = Integer.parseInt(o1.split(" ")[1]);
                int oneSilver = Integer.parseInt(o1.split(" ")[2]);
                int oneBronze = Integer.parseInt(o1.split(" ")[3]);

                int twoGold = Integer.parseInt(o2.split(" ")[1]);
                int twoSilver = Integer.parseInt(o2.split(" ")[2]);
                int twoBronze = Integer.parseInt(o2.split(" ")[3]);
                if (oneGold == twoGold) {
                    if (oneSilver == twoSilver) {
                        if (oneBronze > twoBronze) {
                            return -1;
                        } else {
                            return 1;
                        }
                    } else {
                        if (oneSilver > twoSilver) {
                            return -1;
                        } else {
                            return 1;
                        }
                    }

                } else {
                    if (oneGold > twoGold) {
                        return -1;
                    } else {
                        return 1;
                    }
                }
            }
        });

        System.out.println(Arrays.toString(inputs));

        int rank = 1;
        String before = inputs[0];
        int step = 0;
        for (int index = 0; index < inputs.length; index++) {
            String current = inputs[index];

            String[] sp = current.split(" ");
            int curCountry = Integer.parseInt(sp[0]);
            int curGold = Integer.parseInt(sp[1]);
            int curSilver = Integer.parseInt(sp[2]);
            int curBronze = Integer.parseInt(sp[3]);

            String[] nowSP = before.split(" ");
            int gold = Integer.parseInt(nowSP[1]);
            int silver = Integer.parseInt(nowSP[2]);
            int bronze = Integer.parseInt(nowSP[3]);


            if (index == 0) {
                if (curCountry == foundCountry) {
                    break;
                }
                continue;
            }

            if (curGold == gold && curSilver == silver && curBronze == bronze) {
                step +=1;
            } else {
                rank +=1;
                rank += step;
                step = 0;
            }

            if (curCountry == foundCountry) {
                break;
            }
            before = current;
        }

        System.out.println(rank);
        bufferedReader.close();
    }

    @Override
    public void init() throws IOException {
        solution();
    }
}
