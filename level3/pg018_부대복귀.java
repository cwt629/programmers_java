import java.util.*;

class MoveData {
    int index;
    int moves;
    
    public MoveData(int n, int m){
        index = n;
        moves = m;
    }
}

class Solution {
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = new int[sources.length];
        // roads 정보를 저장할 배열(배열의 각 원소는 링크드 리스트를 가진다)
        LinkedList<Integer>[] roadData = (LinkedList<Integer>[])new LinkedList[n];
        for (int i = 0; i < n; i++){
            roadData[i] = new LinkedList<Integer>();
        }
        
        // destination에서 각 위치로의 최단경로 길이
        int[] routes = new int[n];
        for (int i = 0; i < n; i++){
            routes[i] = -1;
        }
        
        // roads 정보를 링크드리스트 형태로 저장한다
        for (int[] road : roads){
            roadData[road[0] - 1].add(road[1] - 1);
            roadData[road[1] - 1].add(road[0] - 1);
        }
        
        // 길을 destination에서부터 BFS 탐색하여 최단경로값 저장
        Queue<MoveData> bfsQueue = new LinkedList<>();
        bfsQueue.offer(new MoveData(destination - 1, 0));
        routes[destination - 1] = 0; // 시작점
        
        while (!bfsQueue.isEmpty()){
            MoveData current = bfsQueue.poll();
            int nextMoves = current.moves + 1;
            
            for (Integer nextIndex: roadData[current.index]){
                if (routes[nextIndex] < 0){
                    routes[nextIndex] = nextMoves;
                    bfsQueue.offer(new MoveData(nextIndex, nextMoves));
                }
            }
        }
        
        // 정답 구하기
        for (int i = 0; i < answer.length; i++){
            answer[i] = routes[sources[i] - 1];
        }
        
        return answer;
    }
}