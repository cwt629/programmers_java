class Solution {
    public int solution(int[] topping) {
        int answer = 0;
        final int TOTAL_TOPPING_TYPES = getMax(topping);
        int[] myToppings = new int[TOTAL_TOPPING_TYPES],
        broToppings = new int[TOTAL_TOPPING_TYPES];
        
        // 오른쪽 토핑이 동생 꺼라고 가정하고, 동생의 토핑 데이터 저장
        for (int t: topping){
            broToppings[t - 1]++;
        }
        
        // 철수와 동생의 토핑 가짓수
        int myTypes = 0, broTypes = 0;
        for (int t: broToppings){
            if (t > 0) broTypes++;
        }
        
        // 특정 인덱스의 부분까지 철수가 가져간다고 생각하고 썰어본다
        // sliding window 방식으로 매번 나누는 경우에 대해 탐색한다
        for (int cut = 0; cut < topping.length; cut++){
            // 해당 부분 토핑 철수로 가져옴
            int currentType = topping[cut];
            if (myToppings[currentType - 1] == 0) myTypes++;
            myToppings[currentType - 1]++;
            broToppings[currentType - 1]--;
            if (broToppings[currentType - 1] == 0) broTypes--;
            
            // 토핑 가짓수가 같은 경우
            if (myTypes == broTypes) answer++;
        }
        
        return answer;
    }
    
    // int array의 최대값을 반환하는 함수
    static int getMax(int[] array){
        int max = array[0];
        
        for (int i = 1; i < array.length; i++){
            if (max < array[i]) max = array[i];
        }
        
        return max;
    }
}