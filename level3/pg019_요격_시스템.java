import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        int answer = 0;
        
        // targets를 끝점에 대해 오름차순으로 정렬
        // 단, 끝점이 같다면 시작점에 대해 오름차순 정렬
        Arrays.sort(targets, (a, b) -> {
            if (a[1] == b[1]) return a[0] - b[0];
            return a[1] - b[1];
        });
            
        // 앞에서부터 미사일 발사(끝점 직전)
        int i = 0;
        while (i < targets.length){
            answer++;
            int end = targets[i++][1];
            // 시작점이 현재 발사한 미사일 이상인(현위치 끝점 이상) 부분까지 이동
            while (i < targets.length && targets[i][0] < end) i++;
        }
        
        return answer;
    }
}