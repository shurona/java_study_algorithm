package sort;

import javax.sound.midi.Soundbank;
import java.util.Arrays;
import java.util.Comparator;

public class MaxNumber {
    public void solution() {
        String answer = "";
//        int[] first = {1000, 100, 10};
//        int[] first =  {6, 10, 2};
//        int[] first = { 3, 30, 34 ,5, 9 };
//        int[] first = { 565, 56 };
//        int[] first = { 818, 81 };
//        int[] first = { 56, 5};
        int[] first = {0, 0};

        boolean check = Arrays.stream(first).allMatch(n -> n == 0);
//        if(check) return "0";

        Integer[] numbers = Arrays.stream(first).boxed().toArray(Integer[]::new);


        for (Integer number : numbers) {
            System.out.println("number: " + number + " mod : " + (int)(Math.log10(number) + 1));
        }

        Arrays.sort(numbers, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                int o1digit = (int) Math.log10(o1);
                int o2digit = (int) Math.log10(o2);

                int firstModOne = (int) (o1 / Math.pow(10, o1digit));
                int firstModTwo = (int) (o2 / Math.pow(10, o2digit));

                // 첫 값이 같았을 때
                if (firstModOne == firstModTwo) {
                    // 만약 자릿수가 같으면
                    if (o2digit == o1digit) {
                        return o2 - o1;
                    } else {
                        // padding
                        int maxDigit = Math.max(o2digit, o1digit) + 1;

                        StringBuilder sb1 = new StringBuilder(String.valueOf(o1));
                        while (sb1.length() < maxDigit) {
                            sb1.append(firstModOne);
                        }

                        StringBuilder sb2 = new StringBuilder(String.valueOf(o2));
                        while (sb2.length() < maxDigit) {
                            sb2.append(firstModTwo);
                        }

//                        System.out.println(sb1 + "  " + sb2);
//                        System.out.println(o1 + "  " + o2);

                        if (Integer.parseInt(sb2.toString()) == Integer.parseInt(sb1.toString())) {
                            // 두번 째 자리를 확인
                            if (maxDigit == 3) {
                                int o1TenDigits = (Integer.parseInt(sb1.toString()) / 10) % 10;
                                int o1HundredDigits = (Integer.parseInt(sb1.toString()) / 10) / 10;

                                if (o1HundredDigits < o1TenDigits) {
                                    return o1 - o2;
                                } else {
                                    return o2 - o1;
                                }
                            }
                        } else {
                          return Integer.parseInt(sb2.toString()) - Integer.parseInt(sb1.toString());
                        }

                    }
                }
                return firstModTwo - firstModOne;
            }
        });

        System.out.println(Arrays.stream(numbers).map(String::valueOf).reduce("", String::concat));
    }

    public void anotherSolutoin() {
//        int[] first = { 565, 56 };
        int[] first = { 818, 81 };
        String[] nums = new String[first.length];

        for (int i = 0; i < nums.length; i++) {
            nums[i] = first[i] + "";
        }

        System.out.println(Integer.compare(2,1));

        Arrays.sort(nums, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o2 + o1).compareTo(o1 + o2);
            }
        });

        System.out.println(Arrays.toString(nums));
    }
}
