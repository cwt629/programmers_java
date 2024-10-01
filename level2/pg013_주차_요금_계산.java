import java.util.*;

class Car {
    String number;
    int entered = -1;
    int fee = 0;
    int parked = 0;
    
    public Car(String n, int enteredMinute){
        number = n;
        entered = enteredMinute;
    }
    
    public void setEntered(int time){
        entered = time;
    }
    
    public void parkOut(int time){
        parked += time - entered;
        entered = -1;
    }
    
    // 비용 정산
    public void addFee(int pay){
        fee += pay;
        entered = -1; // 정산이 완료되면 엔터시간 -1로 세팅
    }
}

class Solution {
    public int[] solution(int[] fees, String[] records) {
        final int LAST_MINUTE = getMinute("23:59");
        Map<String, Car> cars = new HashMap<>();
        
        for (String record: records){
            String[] recTokens = record.split(" ");
            int time = getMinute(recTokens[0]);
            String number = recTokens[1];
            
            switch(recTokens[2]){
                case "IN":
                    if (cars.containsKey(number)){
                        cars.get(number).setEntered(time);
                    }
                    else {
                        cars.put(number, new Car(number, time));
                    }
                    break;
                    
                case "OUT":
                    cars.get(number).parkOut(time);
                    break;
            }
        }
        
        // 각 Car 객체를 가져온다
        List<Car> carCollections = new ArrayList<>(cars.values());
        Collections.sort(carCollections, (a, b) -> {
            return a.number.compareTo(b.number);
        });
        
        // 비용을 정산한다
        for (Car c: carCollections){
            if (c.entered >= 0){
                c.parkOut(LAST_MINUTE);
            }
            int currentFee = getFee(c.parked, fees);
            c.addFee(currentFee);
        }
        
        // 각 차의 비용 저장
        int[] answer = new int[carCollections.size()];
        int i = 0;
        for (Car c: carCollections){
            answer[i++] = c.fee;
        }
        
        return answer;
    }
    
    // "hh:mm" 문자열을 분 형태로 변환하는 함수
    static int getMinute(String time){
        String[] tokens = time.split(":");
        int hour = Integer.parseInt(tokens[0]), minute = Integer.parseInt(tokens[1]);
        
        return hour * 60 + minute;
    }
    
    // 특정 시간(분)에 대한 요금을 계산하여 반환하는 함수
    static int getFee(int minute, int[] fees){
        int fee = fees[1]; // 기본 요금으로 시작
        // 기본 시간을 초과한 시간
        int additionalTime = minute - fees[0];
        if (additionalTime > 0){
            int mult = (int)Math.ceil((float)additionalTime / (float)fees[2]);
            fee += fees[3] * mult;
        }
        
        return fee;
    }
}