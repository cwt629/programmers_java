class Solution {
    public long solution(int k, int d) {
        long answer = 0;
        for (int x = 0; x <= d; x += k){
            answer += getDotsOnX(k, d, x);
        }
        return answer;
    }
    
    // 반지름이 d인 사분원에서 특정 x값에 대해 찍히는 점의 개수를 반환하는 함수
    static long getDotsOnX(int k, int d, int x){
        if (x > d) return -1;
        return (long)(Math.sqrt(Math.pow(d, 2) - Math.pow(x, 2)) / k + 1);
    }
}