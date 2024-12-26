package problem;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import utils.BaekAlgoStudy;

public class DarkTunnelBridge implements BaekAlgoStudy {

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int length = Integer.parseInt(reader.readLine());
        int count = Integer.parseInt(reader.readLine());

        List<Integer> loc = Arrays.stream(reader.readLine().split(" "))
            .map(i -> Integer.parseInt(i))
            .collect(Collectors.toList());

        int maxDiff = Integer.MIN_VALUE;
        int before = 0;

        for (Integer diff : loc.subList(0, loc.size())) {
            int cal = (int) Math.ceil((double) (diff - before) / 2);
            maxDiff = Math.max(cal, maxDiff);
            before = diff;
        }

        maxDiff = Math.max(maxDiff, loc.get(0));
        maxDiff = Math.max(maxDiff, length - loc.get(loc.size() - 1));

        System.out.println(maxDiff);

        reader.close();

    }

    @Override
    public void init() throws IOException {
        solution();
    }
}
