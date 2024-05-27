package search;

import utils.AlgoStudy;

public class NextBigNumber implements AlgoStudy {

    public int makeBinary(int n) {
        StringBuilder sb = new StringBuilder();
        int oneCount = 1;
        while (n > 1) {
            int mod = n % 2;
            if(mod == 1) oneCount +=1;
            sb.append(mod);
            n = n / 2;
        }
        sb.append(1);
        return oneCount;
    }

    public int solution(int n) {
        // 2진 수로 변환

        int nowOne = makeBinary(n);

        boolean button = true;


        int answer = 0;
        while (button) {
            n +=1;
            if (nowOne == makeBinary(n)) {
                answer = n;
                button = false;
            }
        }

        return answer;
    }

    @Override
    public void init() {
        System.out.println(solution(78));
    }
}
