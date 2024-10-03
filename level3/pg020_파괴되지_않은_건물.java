class Solution {
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        int[][] changes = new int[board.length + 1][]; // 누적합을 위한 추가 배열
        for (int i = 0; i < changes.length; i++){
            changes[i] = new int[board[0].length + 1];
        }
        
        // 누적합을 적용하기 위한 데이터 적용
        for (int[] data: skill){
            int type = data[0], r1 = data[1], c1 = data[2], r2 = data[3],
            c2 = data[4], degree = data[5];
            int mult = (type == 1)? -1 : 1;
            // 누적합 이차원 배열 적용
            changes[r1][c1] += degree * mult;
            changes[r1][c2 + 1] -= degree * mult;
            changes[r2 + 1][c1] -= degree * mult;
            changes[r2 + 1][c2 + 1] += degree * mult;
        }
        
        // 누적합을 통해, 각 지점의 변화량 구하기
        // 1. 좌 -> 우
        for (int r = 0; r < changes.length - 1; r++){
            for (int c = 1; c < changes[r].length - 1; c++){
                changes[r][c] += changes[r][c - 1];
            }
        }
        // 2. 위 -> 아래
        for (int c = 0; c < changes[0].length - 1; c++){
            for (int r = 1; r < changes.length - 1; r++){
                changes[r][c] += changes[r - 1][c];
            }
        }
        
        // 최종 내구도가 0보다 큰 건물의 개수를 구한다
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[i].length; j++){
                if (board[i][j] + changes[i][j] > 0) answer++;
            }
        }
        
        return answer;
    }
}