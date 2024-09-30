import java.util.*;

class Task {
    String name;
    int start; // 시작하는 시간(분)
    int timeRemaining;
    
    public Task(String nameInput, int startInput, int time){
        name = nameInput;
        start = startInput;
        timeRemaining = time;
    }
    
    public void proceed(){
        timeRemaining--;
    }
}

class Solution {
    public String[] solution(String[][] plans) {
        String[] answer = new String[plans.length];
        ArrayList<String> completed = new ArrayList<>();
        Task[] tasks = new Task[plans.length];
        for (int i = 0; i < plans.length; i++){
            String[] plan = plans[i];
            Task t = new Task(plan[0], getMinute(plan[1]), Integer.parseInt(plan[2]));
            tasks[i] = t;
        }
        
        // tasks를 시작 시간이 빠른 순으로 정렬한다
        Arrays.sort(tasks, (a, b) -> {
            return a.start - b.start;
        });
        
        // 진행 시작
        Stack<Task> paused = new Stack<>(); // 중단된 작업 모음(최근에 중단된거 먼저 꺼내기 위해 stack 사용)
        int next = 1; // tasks에서 다음 과제를 가리키는 포인터
        Task current = tasks[0];
        for (int time = tasks[0].start; completed.size() < tasks.length; time++){
            // 현재 진행중인 것을 완료한 경우
            if (current.timeRemaining <= 0){
                completed.add(current.name); // 완료 저장
                
                // 1. 다음 과제를 바로 시작할 수 있는 경우
                if (next < tasks.length && tasks[next].start <= time){
                    current = tasks[next++];
                }
                // 2. 다음 과제를 바로 시작할 수 없는 경우
                else {
                    // 2-1. stack에 중단한 과제가 없는 경우
                    if (paused.isEmpty()){
                        current = (next < tasks.length)? tasks[next++] : null;
                    }
                    // 2-2. stack에 중단한 과제가 남은 경우
                    else {
                        current = paused.pop();
                    }
                }
            }
            // 아직 남은 상태에서 새로 시작해야되는 과제가 나오는 경우
            else if (next < tasks.length && tasks[next].start <= time){
                // 현재 하던 작업은 stack에 저장
                paused.push(current);
                // current를 새 작업으로 교체
                current = tasks[next++];
            }
            
            // 현재 일 진행
            if (current != null && current.start <= time) current.proceed();
        }
        
        // 완료된 이름을 그대로 answer에 담아 보낸다
        for (int i = 0; i < answer.length; i++){
            answer[i] = completed.get(i);
        }
        
        return answer;
    }
    
    static int getMinute(String time){
        String[] tokens = time.split(":");
        int hour = Integer.parseInt(tokens[0]), minute = Integer.parseInt(tokens[1]);
        
        return hour * 60 + minute;
    }
    
    static void printTasks(Task[] tasks){
        for (int i = 0; i < tasks.length; i++){
            System.out.println(tasks[i].name + "-" + tasks[i].start + "시작 - " + tasks[i].timeRemaining + "분 남음");
        }
    }
}