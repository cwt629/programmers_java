import java.util.*;

class Solution {
    public int solution(String[][] book_time) {
        int answer = 0;
        final int MINUTE_PER_DAY = 1440;
        int[] changes = new int[MINUTE_PER_DAY]; // 방의 변화를 표현하기 위한 배열(구간합)
        int[] occupied = new int[MINUTE_PER_DAY];
        
        // 예약시간에 따라, 구간합 구현을 위해 준비
        for (String[] book: book_time){
            int start = getMinute(book[0]), end = getMinute(book[1]) + 10; // 청소시간까지 고려
            changes[start]++;
            if (end < changes.length) changes[end]--;
        }
        
        // 분별 사용중인 객실 수 구하기
        occupied[0] = changes[0];
        for (int i = 1; i < changes.length; i++){
            occupied[i] = occupied[i - 1] + changes[i];
        }
        
        // 가장 많이 이용한 객실 수 반환
        return getMax(occupied);
    }
    
    // String 형태의 시간을 분 형태로 변환하는 함수
    static int getMinute(String time){
        String[] tokens = time.split(":");
        int hour = Integer.parseInt(tokens[0]), minute = Integer.parseInt(tokens[1]);
        
        return hour * 60 + minute;
    }
    
    // int 배열에서 최대값 구하는 함수
    static int getMax(int[] array){
        int max = array[0];
        for (int i = 1; i < array.length; i++){
            if (max < array[i]) max = array[i];
        }
        
        return max;
    }
}