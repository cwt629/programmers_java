import java.util.*;

class Solution {
    public String solution(String s, String skip, int index) {
        String answer = "";
        LinkedList<Character> alphabets = new LinkedList<>();
        
        for (char alphabet = 'a'; alphabet <= 'z'; alphabet++){
            // skip에 포함되어 있는지 확인한다(contains는 인자에 String을 받음을 조심하자.)
            if (!skip.contains(String.valueOf(alphabet))){
                alphabets.add(alphabet);
            }
        }
        
        final int ALPHABET_SIZE = alphabets.size();
        
        for (int i = 0; i < s.length(); i++){
            char current = s.charAt(i);
            int prevIndex = alphabets.indexOf(current);
            
            // 변경된 알파벳 적용
            answer += alphabets.get((prevIndex + index) % ALPHABET_SIZE);
        }
        
        return answer;
    }
}