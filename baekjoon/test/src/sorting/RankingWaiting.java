package sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import utils.BaekAlgoStudy;

/*
    문제 링크
    https://www.acmicpc.net/problem/20006
 */
public class RankingWaiting implements BaekAlgoStudy {

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<Room> waiting = new ArrayList<>();

        String[] input = reader.readLine().split(" ");

        int peopleCt = Integer.parseInt(input[0]);
        int roomSize = Integer.parseInt(input[1]);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < peopleCt; i++) {
            String[] line = reader.readLine().split(" ");
            int level = Integer.parseInt(line[0]);
            String nickname = line[1];

            boolean isFind = false;
            // 먼저 대기열에서 방을 찾는다
            for (int loc = 0; loc < waiting.size(); loc++) {
                // 범위 확인
                Room room = waiting.get(loc);
                if (room.initLevel <= level + 10 && room.initLevel >= level - 10) {
                    // 이미 찼으면 패스
                    if (room.members.size() == roomSize) {
                        continue;
                    }
                    isFind = true;
                    room.members.add(new Member(level, nickname));
                    break;
                }
            }

            if (isFind) {
                continue;
            }

            // 없으면 방을 만들어 넣어준다.
            waiting.add(new Room(level, nickname));
        }

        for (Room room : waiting) {
            int loop = room.members.size();
            if (room.members.size() == roomSize) {
                sb.append("Started!").append("\n");
                for (int print = 0; print < loop; print++) {
                    Member poll = room.members.poll();
                    sb.append(poll.level + " " + poll.nickname).append("\n");
                }
            } else {
                sb.append("Waiting!").append("\n");
                for (int i = 0; i < loop; i++) {
                    Member poll = room.members.poll();
                    sb.append(poll.level + " " + poll.nickname).append("\n");

                }
            }
        }

        System.out.println(sb.toString());

        reader.close();

    }

    @Override
    public void init() throws IOException {
        solution();
    }

    static class Room {

        Queue<Member> members;
        int initLevel;

        public Room(int initLevel, String nickname) {
            this.initLevel = initLevel;
            members = new PriorityQueue<>(new Comparator<Member>() {
                @Override
                public int compare(Member o1, Member o2) {
                    return o1.nickname.compareTo(o2.nickname);
                }
            });
            members.add(new Member(initLevel, nickname));
        }

        @Override
        public String toString() {
            return "Room{" +
                "members=" + members +
                ", initLevel=" + initLevel +
                '}';
        }
    }

    static class Member {

        int level;
        String nickname;

        public Member(int level, String nickname) {
            this.level = level;
            this.nickname = nickname;
        }

        @Override
        public String toString() {
            return "Member{" +
                "level=" + level +
                ", nickname='" + nickname + '\'' +
                '}';
        }
    }
}
