import java.util.*;

class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        int answer = 0;
        
        // 2. col번째(인덱스 col-1) 값 기준 오름차순 정렬(동일하면 0번 컬럼 내림차순 정렬)
        Arrays.sort(data, (a, b) -> {
            if (a[col - 1] == b[col - 1]) return b[0] - a[0];
            return a[col - 1] - b[col - 1];
        });
        
        // 3. S_i = 각 컬럼값을 i로 나눈 나머지의 합 구하기(row_begin ~ row_end)
        int[] S = new int[row_end - row_begin + 1];
        for (int i = row_begin; i <= row_end; i++){
            S[i - row_begin] = 0;
            for (int c = 0; c < data[i - 1].length; c++){
                S[i - row_begin] += data[i - 1][c] % i;
            }
        }
        
        // 4. 모두 XOR
        answer = S[0];
        for (int i = 1; i < S.length; i++){
            answer = answer ^ S[i];
        }
        
        return answer;
    }
}