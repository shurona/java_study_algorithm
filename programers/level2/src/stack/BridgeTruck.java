package stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BridgeTruck {
    public void solution() {

        int bridge_length = 2;
        int weight = 10;
        int[] truck_weights = { 7, 4, 5, 6};

        int currentWeight = 0;
        int currentLocation = 0;

        int[] truckTimer = new int[truck_weights.length];
        ArrayList<Integer> complete = new ArrayList<>();


        int second = 0;
        while (complete.size() < truck_weights.length) {
            second+=1;

            // 현재 위치의 truck 정보를 확인한다
            if (currentLocation < truck_weights.length) {
                int newTruckWeight = truck_weights[currentLocation];

                // 중량을 확인해서 이하면 넣어준다
                if (weight >= newTruckWeight + currentWeight) {
                    //
                    truckTimer[currentLocation] = bridge_length;

                    //
                    currentLocation+=1;
                    currentWeight+=newTruckWeight;
                }
            }
            System.out.println(Arrays.toString(truckTimer));

            for (int i = complete.size(); i < currentLocation; i++) {
                truckTimer[i] -= 1;
                if (truckTimer[i] == 0) {
                    complete.add(truck_weights[i]);
                    currentWeight -= truck_weights[i];
                }
            }
        }

        System.out.println("시간은 : " + second);
    }
}
