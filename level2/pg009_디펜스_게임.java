import java.util.*;

/* 2nd solution: Heap 구성에 PriorityQueue 클래스 활용 */
class MaxHeap {
    PriorityQueue<Integer> elements = new PriorityQueue<>((a, b) -> (b - a));
    int total = 0;
    
    public void add(int num){
        elements.offer(num);
        total += num;
    }
    
    public int pop(){
        int element = elements.poll();
        total -= element;
        
        return element;
    }
}

/* 1st solution: Heap 직접 구성 */
// class MaxHeap {
//     ArrayList<Integer> elements;
//     int size = 0;
//     int total = 0;
    
//     public MaxHeap(){
//         elements = new ArrayList<>();
//         elements.add(-1); // Heap 구현을 위해, 0번째 인덱스는 임의로 채워넣는다
//     }
    
//     public void add(int num){
//         if (size < elements.size() - 1){
//             // 이전에 남아있는 값이 있는 경우
//             elements.set(++size, num);
//         }
//         else {
//             elements.add(num);
//             size++;
//         }
//         total += num;
        
//         int lastIndex = size;
//         while (lastIndex > 1 && elements.get(lastIndex) > elements.get(lastIndex / 2)){
//             swap(lastIndex, lastIndex / 2);
//             lastIndex /= 2;
//         }
//     }
    
//     public int pop(){
//         int element = elements.get(1);
//         elements.set(1, elements.get(size--)); // 마지막에 있는 요소로 수정하고 size 줄이기
//         total -= element;
        
//         // Heapify
//         int parent = 1, child = 2;
//         while (child <= size){
//             if (child < size && elements.get(child) < elements.get(child + 1)) child++;
//             if (elements.get(parent) < elements.get(child)){
//                 swap(parent, child);
//             }
//             parent = child;
//             child *= 2;
//         }
        
//         return element;
//     }
    
//     public void swap(int index1, int index2){
//         int temp = elements.get(index1);
//         elements.set(index1, elements.get(index2));
//         elements.set(index2, temp);
//     }
    
//     public void print(){
//         for (int i = 1; i <= size; i++){
//             System.out.print(elements.get(i) + " ");
//         }
//         System.out.println();
//     }
// }

class Solution {
    public int solution(int n, int k, int[] enemy) {
        int answer = 0;
        MaxHeap heap = new MaxHeap();
        int shields = k;
        
        for (answer = 0; answer < enemy.length; answer++){
            heap.add(enemy[answer]);
            // 버티지 못하는 경우, 지나온 가장 높은 스테이지에 대해 실드 사용
            if (heap.total > n){
                if (shields == 0) break;
                shields--;
                heap.pop();
            }
        }
        
        return answer;
    }
}