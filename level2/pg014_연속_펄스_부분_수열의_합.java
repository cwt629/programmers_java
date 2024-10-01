class Solution {
    public long solution(int[] sequence) {
        long answer = 0;
        // 1, -1, 1, -1 ...를 곱한 수열을 구한다
        int[] pulsedSequence = new int[sequence.length];
        for (int i = 0; i < sequence.length; i++){
            pulsedSequence[i] = sequence[i] * ((i % 2 == 0)? 1 : (-1));
        }
        
        // 앞에서부터 i - 1번째 인덱스까지의 합을 배열로 표현한다
        long[] sum = new long[sequence.length + 1];
        sum[0] = 0;
        for (int i = 1; i < sum.length; i++){
            sum[i] = sum[i - 1] + pulsedSequence[i - 1];
        }
        
        // 구해진 S에서 최대값 - 최소값을 구하면, 그것이 가장 큰 연속 펄스 부분 수열의 합!
        long min = sum[0], max = sum[0];
        for (int i = 1; i < sum.length; i++){
            if (max < sum[i]) max = sum[i];
            if (min > sum[i]) min = sum[i];
        }
        
        return (max - min);
    }
}