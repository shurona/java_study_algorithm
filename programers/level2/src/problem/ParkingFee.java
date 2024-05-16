package problem;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

public class ParkingFee {
    static class CarWithNumber {
        public Integer carNumber;
        public Integer cost;
    }
    public int[] solution(int[] fees, String[] records) {
        HashMap<String, Integer> carIn = new HashMap<>();
        HashMap<String, Integer> carCost = new HashMap<>();

        // 분 단위
        // 180분 5000원 => 10분 600원

        for (String record : records) {
            String[] sp = record.split(" ");

            String carNumber = sp[1];

            String[] time = sp[0].split(":");
            Integer min = Integer.parseInt(time[1]);
            Integer hour = Integer.parseInt(time[0]);
            // 처리
            if (sp[2].equals("IN")) {
                carIn.put(carNumber, (hour * 60 + min));
                if (carCost.getOrDefault(carNumber, null) == null) {
                    carCost.put(carNumber, 0);
                }

            } else {
                carCost.put(carNumber, carCost.get(carNumber) + ((hour * 60 + min) - carIn.get(carNumber)));
                carIn.remove(carNumber);
            }
        }

        for (String car : carIn.keySet()) {
            carCost.put(car, carCost.get(car) + ((23 * 60 + 59) - carIn.get(car)));
        }


        CarWithNumber[] tp = new CarWithNumber[carCost.keySet().size()];
        int index = -1;
        for (String carNum : carCost.keySet()) {
            index +=1;
            CarWithNumber cn = new CarWithNumber();
            tp[index] = cn;
            Integer cost = carCost.get(carNum);
            int remainTime = cost - fees[0];

            cn.carNumber = Integer.parseInt(carNum);
            cn.cost = 0;
            cn.cost += fees[1];

            if (remainTime <= 0) {
                continue;
            }

            if (remainTime % fees[2] > 0) {
                cn.cost += fees[3];
            }

            cn.cost += (int)(remainTime / fees[2]) * fees[3];
        }

        Arrays.sort(tp, new Comparator<CarWithNumber>() {
            @Override
            public int compare(CarWithNumber o1, CarWithNumber o2) {
                return o1.carNumber - o2.carNumber;
            }
        });

        int[] answer = new int[tp.length];

        for (int i = 0; i < answer.length; i++) {
            answer[i] = tp[i].cost;
        }

        return answer;
    }

    public void init() {
//        int[] fees = {180, 5000, 10, 600};
//        String[] records = {"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"};

        int[] fees = {1, 461, 1, 10};
        String[] records = {"00:00 1234 IN"};

        int[] solution = solution(fees, records);

        System.out.println("solution = " + Arrays.toString(solution));
    }
}
