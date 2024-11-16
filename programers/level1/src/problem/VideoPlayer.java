package problem;

public class VideoPlayer {

    public int checkTime(int now, int opStart, int opEnd) {
        if (now >= opStart && now <= opEnd) {
            return opEnd;
        }
        return now;
    }

    public int convertStringToInt(String input) {

        String[] arr = input.split(":");

        return Integer.parseInt(arr[0]) * 60 + Integer.parseInt(arr[1]);
    }

    public String solution(String video_len, String pos, String op_start, String op_end,
        String[] commands) {

        int videoLen = convertStringToInt(video_len);
        int now = convertStringToInt(pos);
        int opStart = convertStringToInt(op_start);
        int opEnd = convertStringToInt(op_end);
        now = checkTime(now, opStart, opEnd);
        for (String command : commands) {
            switch (command) {
                case "next":
                    now = now + 10;
                    if (now >= videoLen) {
                        now = videoLen;
                    }
                    break;
                default:
                    now = now - 10;
                    if (now < 0) {
                        now = 0;
                    }
            }

            now = checkTime(now, opStart, opEnd);
        }

        int min = now / 60;
        int sec = now % 60;

        String minAnswer = "";
        if (min < 10) {
            minAnswer = "0" + min;
        } else {
            minAnswer = "" + min;
        }

        String secAnswer = "";
        if (sec < 10) {
            secAnswer = "0" + sec;
        } else {
            secAnswer = "" + sec;
        }

        return minAnswer + ":" + secAnswer;
    }

}
