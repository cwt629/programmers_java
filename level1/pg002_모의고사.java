import java.util.*;

class Solution {
    public int[] solution(int[] answers) {
        int[] corrects = {0, 0, 0}; // 1, 2, 3번 수포자가 정답을 맞힌 개수
        
        for (int i = 0; i < answers.length; i++){
            int currentAnswer = answers[i];
            
            if (currentAnswer == getFirstAnswer(i)) corrects[0]++;
            if (currentAnswer == getSecondAnswer(i)) corrects[1]++;
            if (currentAnswer == getThirdAnswer(i)) corrects[2]++;
        }
        
        return getTopCorrect(corrects);
    }
    
    // 1번 수포자의 특정 문제 정답을 반환하는 함수(problem: 인덱스)
    static int getFirstAnswer(int problem){
        return (problem % 5) + 1;
    }
    
    // 2번 수포자의 특정 문제 정답을 반환하는 함수
    static int getSecondAnswer(int problem){
        // 짝수 인덱스 - 2
        if (problem % 2 == 0) return 2;
        
        // 홀수 - 1, 3, 4, 5 반복
        return (problem % 8 == 1)? 1 :
        (problem % 8 == 3)? 3 :
        (problem % 8 == 5)? 4 : 5;
    }
    
    // 3번 수포자의 특정 문제 정답을 반환하는 함수
    static int getThirdAnswer(int problem){
        int dualGroup = (int)(problem / 2); // 둘씩 묶었을 때 몇번째 그룹 인덱스인지
        switch(dualGroup % 5){
            case 0:
                return 3;
                
            case 1:
                return 1;
                
            case 2:
                return 2;
                
            case 3:
                return 4;
                
            case 4:
                return 5;
                
            default:
                return -1; // 임의 지정
        }
    }
    
    // 가장 많이 맞힌 그룹을 추려내는 함수
    static int[] getTopCorrect(int[] corrects){
        ArrayList<Integer> result = new ArrayList<>();
        result.add(1);
        
        // 2번 수포자와 비교
        if (corrects[1] > corrects[0]){
            result.clear();
            result.add(2);
        }
        else if (corrects[1] == corrects[0]) result.add(2);
        
        // 3번 수포자와 비교
        if (corrects[2] > corrects[result.get(0) - 1]){
            result.clear();
            result.add(3);
        }
        else if (corrects[2] == corrects[result.get(0) - 1]) result.add(3);
        
        // ArrayList를 int[]로 변환
        int[] resultArray = new int[result.size()];
        for (int i = 0; i < result.size(); i++){
            resultArray[i] = result.get(i);
        }
        
        return resultArray;
    }
}