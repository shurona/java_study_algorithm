package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import utils.BaekAlgoStudy;

/*
    문제 링크
    https://www.acmicpc.net/problem/11501
 */
public class StockAiCalculate implements BaekAlgoStudy {

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // 뒤집은 다음에 앞에를 확인해서 현재가 더 높은 가격이였으면 판다.

        int maxCt = Integer.parseInt(reader.readLine());

        StringBuilder sb = new StringBuilder();
        for (int ih = 0; ih < maxCt; ih++) {
            int maxDate = Integer.parseInt(reader.readLine());

            int[] stockList = new int[maxDate];

            String[] input = reader.readLine().split(" ");

            for (int i = 0; i < input.length; i++) {
                stockList[maxDate - i - 1] = Integer.parseInt(input[i]);
            }

//            System.out.println(Arrays.toString(stockList));

            int loc = 0;
            long sum = 0;
            while (loc < stockList.length - 1) {
                List<Integer> tp = new ArrayList<>();
                int now = stockList[loc];
                // 앞에를 확인해보자
                for (int check = loc + 1; check < stockList.length; check++) {
                    // 작거나 같으면 전진
//                    System.out.println(now + " : " + stockList[check]);
                    if (now >= stockList[check]) {
                        tp.add(stockList[check]);
//                        now = stockList[check];
                        loc += 1;
                    } else {
                        // 아니면 스탑하고 이전 거를 안사고 이전 가격으로 판매
                        for (Integer data : tp) {
                            sum += (now - data);
                        }

                        // 배열 초기화
                        tp.clear();

                        // 위치를 현재시점으로 조정
                        loc = check;
                        break;
                    }
                }

                for (Integer data : tp) {
                    sum += (now - data);
                }

//                System.out.println(sum);

            }
            sb.append(sum).append("\n");

        }
        System.out.println(sb.toString());
        reader.close();

    }

    @Override
    public void init() throws IOException {
        solution();
    }
}
