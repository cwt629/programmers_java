class Solution {
    public long solution(int r1, int r2) {
        long answer = 0;
        int x;
        // 1. 외부 원의 왼쪽끝 ~ 내부 원의 왼쪽 끝
        for (x = -r2; x <= -r1; x++){
            int maxY = (int)Math.sqrt(Math.pow(r2, 2) - Math.pow(x, 2));
            answer += maxY * 2 + 1; // x축 대칭임을 이용
        }
        
        // 2. 내부 원의 왼쪽 끝 직후 ~ y축 닿기 직전
        for (x = -r1 + 1; x < 0; x++){
            int outerTop = (int)Math.sqrt(Math.pow(r2, 2) - Math.pow(x, 2));
            int innerTop = (int)Math.ceil(Math.sqrt(Math.pow(r1, 2) - Math.pow(x, 2))); // 올림
            answer += (outerTop - innerTop + 1) * 2;
        }
        
        // 3. 현재까지 구한 점에 대해 y축대칭
        answer *= 2;
        
        // 4. y축상
        answer += 2 * (r2 - r1 + 1);
        
        return answer;
    }
}