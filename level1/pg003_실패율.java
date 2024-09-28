import java.util.*;

class Solution {
    static class Stage {
        int number;
        int stopped = 0;
        float failureRate = 0.0f;
        
        public Stage(int num){
            number = num;
        }
        
        public void incrementStop(){
            stopped++;
        }
        
        public void setFailureRate(float rate){
            failureRate = rate;
        }
    }
    
    public int[] solution(int N, int[] stages) {
        Stage[] stageData = new Stage[N + 1];
        for (int i = 0; i < N + 1; i++){
            stageData[i] = new Stage(i + 1);
        }
        
        // stageData에 stopped 저장
        for (int stageNum: stages){
            stageData[stageNum - 1].incrementStop();
        }
        
        // 실패율을 뒤에서부터 계산한다
        int sum = stageData[N].stopped;
        for (int i = N - 1; i >= 0; i--){
            float stops = (float)stageData[i].stopped, reachs = (float)(stageData[i].stopped + sum);
            float rate = (reachs > 0)? stops / reachs : 0;
            stageData[i].setFailureRate(rate);
            sum += stageData[i].stopped;
        }
        
        // stageData 정렬
        Arrays.sort(stageData, (a, b) -> {
            // 서로 실패율이 같다면, 번호에 대해 오름차순
            if (a.failureRate == b.failureRate) return a.number - b.number;
            // 실패율이 다르다면, 실패율에 대해 내림차순
            return (a.failureRate < b.failureRate)? 1 : -1;
        });
        
        // 번호만 추려내기
        int[] answer = new int[N];
        for (int i = 0; i < N; i++){
            answer[i] = stageData[i].number;
        }
        
        return answer;
    }
}