public class ParkWalk {
    boolean checkOverSize(int rowLen, int colLen, int currentRow, int currentCol) {
        if(currentRow < 0 || currentCol < 0) {
            return false;
        }
        if(currentRow >= rowLen || currentCol >= colLen) {
            return false;
        }
        return true;
    }

    public int[] solution(String[] park, String[] routes) {
        int[] currentLocation = new int[2];

        // 시작지점의 위치를 찾는다.
        for(int row = 0; row < park.length; row++) {
            int col = park[row].indexOf('S');
            if(col >= 0) {
                currentLocation[0] = row;
                currentLocation[1] = col;
            }
        }


        // routes 따라서 이동한다.
        for(String route: routes) {
            int currentRow = currentLocation[0];
            int currentCol = currentLocation[1];
            boolean switchLocation = true;
            String[] splitedRoute = route.split(" ");

            switch(splitedRoute[0]) {
                // row 변수 변경
                case "N":
                    for(int step = 0; step < Integer.parseInt(splitedRoute[1]); step++) {
                        currentRow -= 1;
                        if(!checkOverSize(park.length, park[0].length(), currentRow, currentCol)) {
                            switchLocation = false;
                            break;
                        }

                        if(park[currentRow].charAt(currentCol) == 'X') {
                            switchLocation = false;
                            break;
                        }
                    }
                    break;
                case "S":
                    for(int step = 0; step < Integer.parseInt(splitedRoute[1]); step++) {
                        currentRow += 1;
                        if(!checkOverSize(park.length, park[0].length(), currentRow, currentCol)) {
                            switchLocation = false;
                            break;
                        }

                        if(park[currentRow].charAt(currentCol) == 'X') {
                            switchLocation = false;
                            break;
                        }
                    }
                    break;
                // col 변수 변경
                case "E":
                    for(int step = 0; step < Integer.parseInt(splitedRoute[1]); step++) {
                        currentCol += 1;
                        if(!checkOverSize(park.length, park[0].length(), currentRow, currentCol)) {
                            switchLocation = false;
                            break;
                        }

                        if(park[currentRow].charAt(currentCol) == 'X') {
                            switchLocation = false;
                            break;
                        }
                    }
                    break;
                case "W":
                    for(int step = 0; step < Integer.parseInt(splitedRoute[1]); step++) {
                        currentCol -= 1;
                        if(!checkOverSize(park.length, park[0].length(), currentRow, currentCol)) {
                            switchLocation = false;
                            break;
                        }

                        if(park[currentRow].charAt(currentCol) == 'X') {
                            switchLocation = false;
                            break;
                        }
                    }
                    break;
                default:
                    throw new Error("yahoo");
            }

            if(switchLocation) {
                currentLocation[0] = currentRow;
                currentLocation[1] = currentCol;
            }

        }

        return currentLocation;
    }
}
