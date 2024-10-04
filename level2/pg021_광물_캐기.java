import java.util.*;

class MineData {
    int fatigue;
    int nextIndex;
    int[] currentPicks;
    
    public MineData(int f, int i, int[] p){
        fatigue = f;
        nextIndex = i;
        currentPicks = p;
    }
}

class Solution {
    public int solution(int[] picks, String[] minerals) {
        final int[][] FATIGUE_TABLE = {{1, 1, 1}, {5, 1, 1}, {25, 5, 1}};
        int answer = -1;
        
        // DFS 방식으로 모두 탐색한다
        Stack<MineData> dfsStack = new Stack<>();
        dfsStack.push(new MineData(0, 0, picks.clone()));
        
        while (!dfsStack.isEmpty()){
            MineData current = dfsStack.pop();
            int[] currentPicks = current.currentPicks;
            
            // 각 타입의 곡괭이 사용
            for (int i = 0; i < 3; i++){
                int fatigue = current.fatigue;
                int next = current.nextIndex;
                if (currentPicks[i] > 0){
                    int start = next;
                    // 최대 5개까지 캔다
                    while (next < minerals.length && next < start + 5){
                        int mIndex = (minerals[next].equals("diamond"))? 0 :
                        (minerals[next].equals("iron"))? 1 : 2;
                        fatigue += FATIGUE_TABLE[i][mIndex];
                        next++;
                    }
                    
                    // 모든 광물을 캔 경우
                    if (next >= minerals.length){
                        // 갱신
                        if (answer < 0 || answer > fatigue) answer = fatigue;
                        continue;
                    }
                    
                    int[] nextPicks = currentPicks.clone();
                    nextPicks[i]--; // 현재 사용한 곡괭이
                    
                    // 모든 곡괭이를 사용한 경우
                    if (nextPicks[0] + nextPicks[1] + nextPicks[2] == 0){
                        // 갱신
                        if (answer < 0 || answer > fatigue) answer = fatigue;
                        continue;
                    }
                    
                    // 더 진행
                    dfsStack.push(new MineData(fatigue, next, nextPicks));
                }
            }
        }
        
        return answer;
    }
}