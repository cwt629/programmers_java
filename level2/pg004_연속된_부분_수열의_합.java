import java.util.*;

class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = {};
        // 조건을 만족하는 시작&끝 인덱스를 보관하는 이차원 배열
        ArrayList<int[]> indices = new ArrayList<>();
        
        // 슬라이딩 윈도우 방식으로 인덱스 구하기
        int start = 0, end = 1, sum = sequence[0]; // end 직전까지를 본다
        while (start <= end){
            // 현재 합에 대해 확인
            if (sum == k){
                indices.add(new int[]{start, end - 1}); // k와 일치 시 시작, 끝 인덱스 저장
            }
            // 모든 요소가 양수이므로, 합이 k 이상인 경우 start를 늘린다.
            if (sum >= k){
                sum -= sequence[start++];
            }
            // 합이 k 미만인 경우 end를 늘린다.
            else {
                if (end == sequence.length) start++; // end를 늘릴 수 없다면, start를 늘린다
                else sum += sequence[end++]; // end 직전까지를 더해왔으므로, 현 end 위치를 더해주고 1 증가시킨다
            }
        }
        
        return getAnswer(indices);
    }
    
    static int[] getAnswer(ArrayList<int[]> indices){
        int[] result = indices.get(0);
        
        for (int i = 1; i < indices.size(); i++){
            int[] current = indices.get(i);
            // 길이가 더 짧다면 갱신한다
            if (result[1] - result[0] > current[1] - current[0])
                result = current;
            // 길이가 같고 시작 인덱스가 작다면 갱신한다
            else if (result[1] - result[0] == current[1] - current[0] 
                     && current[0] < result[0])
                result = current;
        }
        
        return result;
    }
}