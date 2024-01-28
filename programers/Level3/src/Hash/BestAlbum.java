package Hash;

import java.util.*;
import java.util.stream.Collectors;

class PlayWithIndex {
    private int index;
    private int count;
    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
}

public class BestAlbum {
    public void make() {

        String[] genres = {"classic", "pop", "classic", "classic", "pop"};
        int[] plays = {500, 600, 150, 800, 2500};

//        String[] genres = {"classic", "pop", "classic", "classic"};
//        int[] plays = {500, 600, 150, 800};

        Map<String, List<PlayWithIndex>> output = new HashMap<>();
        Map<String, Integer> generesWithPlays = new HashMap<>();

        for(int i = 0; i < genres.length; i++) {
            if(!output.containsKey(genres[i])) {
                output.put(genres[i], new ArrayList<>());
            }

            PlayWithIndex tp = new PlayWithIndex();
            tp.setCount(plays[i]);
            tp.setIndex(i);
            output.get(genres[i]).add(tp);

            int genresCount = generesWithPlays.getOrDefault(genres[i], 0);
            generesWithPlays.put(genres[i], genresCount + plays[i]);

        }

        List<Map.Entry<String, Integer>> sortedGenres = generesWithPlays.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(Collectors.toList());

        output.forEach((genre, playList) -> playList.sort(Comparator.comparing(PlayWithIndex::getCount).reversed().thenComparing(PlayWithIndex::getIndex)));

        List<Integer> ttpp = new ArrayList<>();
        for(int i = 0 ; i < sortedGenres.size(); i++) {
            String gen = sortedGenres.get(i).getKey();

            for(int j = 0; j < output.get(gen).size(); j++) {
                if(j == 2) break;
                ttpp.add(output.get(gen).get(j).getIndex());
            }
        }


        int[] answer = ttpp.stream().mapToInt(value -> value).toArray();

    }

    // 다른 해결책
    public Boolean anotherSolution() {

        String[] genres = {"classic", "pop", "classic", "classic", "pop"};
        int[] plays = {500, 600, 150, 800, 2500};

        HashMap<String, Object> genresMap = new HashMap<String, Object>();      //<장르, 곡 정보>
        HashMap<String, Integer> playMap = new HashMap<String, Integer>(); //<장르, 총 장르 재생수>
        ArrayList<Integer> resultAL = new ArrayList<Integer>();

        for(int i = 0; i < genres.length; i++){
            String key = genres[i];
            HashMap<Integer, Integer> infoMap;       // 곡 정보 : <곡 고유번호, 재생횟수>

            if(genresMap.containsKey(key)){
                infoMap = (HashMap<Integer, Integer>)genresMap.get(key);
            }
            else {
                infoMap = new HashMap<Integer, Integer>();
            }

            infoMap.put(i, plays[i]);
            genresMap.put(key, infoMap);

            //재생수
            if(playMap.containsKey(key)){
                playMap.put(key, playMap.get(key) + plays[i]);
            }
            else {
                playMap.put(key, plays[i]);
            }
        }

        System.out.println(genresMap);
        System.out.println(playMap);

        int mCnt = 0;
        Iterator it = sortByValue(playMap).iterator();

        System.out.println(it.next());

        while(it.hasNext()){
            String key = (String)it.next();
            Iterator indexIt = sortByValue((HashMap<Integer, Integer>)genresMap.get(key)).iterator();
            int playsCnt = 0;

            while(indexIt.hasNext()){
                resultAL.add((int)indexIt.next());
                mCnt++;
                playsCnt++;
                if(playsCnt > 1) break;
            }
        }

        int[] answer = new int[resultAL.size()];

        for(int i = 0; i < resultAL.size(); i++){
            answer[i] = resultAL.get(i).intValue();
        }

        return true;
    }

    private ArrayList sortByValue(final Map map){
        ArrayList<Object> keyList = new ArrayList();
        keyList.addAll(map.keySet());

        Collections.sort(keyList, new Comparator(){
            public int compare(Object o1, Object o2){
                Object v1 = map.get(o1);
                Object v2 = map.get(o2);

                return ((Comparable) v2).compareTo(v1);
            }
        });

        return keyList;
    }
}
