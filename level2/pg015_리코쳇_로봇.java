import java.util.*;

enum Direction {
    RIGHT,
    DOWN,
    LEFT,
    UP
};

class Position {
    int row;
    int col;
    int moves;
    
    public Position(int r, int c, int m){
        row = r;
        col = c;
        moves = m;
    }
}

class Solution {
    public int solution(String[] board) {
        int answer = -1;
        boolean[][] visited = new boolean[board.length][];
        for (int i = 0; i < board.length; i++){
            visited[i] = new boolean[board[i].length()];
        }
        
        int[] start = {-1, -1};
        GetStart:
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[i].length(); j++){
                if (board[i].charAt(j) == 'R'){
                    start[0] = i;
                    start[1] = j;
                    break GetStart;
                }
            }
        }
        
        // BFS 방식으로 진행
        Queue<Position> bfsQueue = new LinkedList<>();
        visited[start[0]][start[1]] = true;
        bfsQueue.offer(new Position(start[0], start[1], 0));
        
        while (!bfsQueue.isEmpty()){
            Position current = bfsQueue.poll();
            int row = current.row, col = current.col, nextMoves = current.moves + 1;
            // 오른쪽
            int[] rightTarget = getTargetPosition(row, col, Direction.RIGHT, board);
            if (!visited[rightTarget[0]][rightTarget[1]]){
                visited[rightTarget[0]][rightTarget[1]] = true;
                // 목표 달성
                if (board[rightTarget[0]].charAt(rightTarget[1]) == 'G'){
                    answer = nextMoves;
                    break;
                }
                bfsQueue.offer(new Position(rightTarget[0], rightTarget[1], nextMoves));
            }
            
            // 아래쪽
            int[] downTarget = getTargetPosition(row, col, Direction.DOWN, board);
            if (!visited[downTarget[0]][downTarget[1]]){
                visited[downTarget[0]][downTarget[1]] = true;
                // 목표 달성
                if (board[downTarget[0]].charAt(downTarget[1]) == 'G'){
                    answer = nextMoves;
                    break;
                }
                bfsQueue.offer(new Position(downTarget[0], downTarget[1], nextMoves));
            }
            
            // 왼쪽
            int[] leftTarget = getTargetPosition(row, col, Direction.LEFT, board);
            if (!visited[leftTarget[0]][leftTarget[1]]){
                visited[leftTarget[0]][leftTarget[1]] = true;
                // 목표 달성
                if (board[leftTarget[0]].charAt(leftTarget[1]) == 'G'){
                    answer = nextMoves;
                    break;
                }
                bfsQueue.offer(new Position(leftTarget[0], leftTarget[1], nextMoves));
            }
            
            // 위쪽
            int[] upTarget = getTargetPosition(row, col, Direction.UP, board);
            if (!visited[upTarget[0]][upTarget[1]]){
                visited[upTarget[0]][upTarget[1]] = true;
                // 목표 달성
                if (board[upTarget[0]].charAt(upTarget[1]) == 'G'){
                    answer = nextMoves;
                    break;
                }
                bfsQueue.offer(new Position(upTarget[0], upTarget[1], nextMoves));
            }
            
        }
        
        return answer;
    }
    
    // board에서 특정 방향으로 쭉 이동했을 때 나오는 row, col값을 반환하는 함수
    static int[] getTargetPosition(int startRow, int startCol, Direction dir, String[] board){
        int[] position = {startRow, startCol};
        
        switch(dir){
            case RIGHT:
                while (position[1] + 1 < board[position[0]].length() && board[position[0]].charAt(position[1] + 1) != 'D') position[1]++;
                break;
                
            case DOWN:
                while (position[0] + 1 < board.length && board[position[0] + 1].charAt(position[1]) != 'D') position[0]++;
                break;
                
            case LEFT:
                while (position[1] - 1 >= 0 && board[position[0]].charAt(position[1] - 1) != 'D') position[1]--;
                break;
                
            case UP:
                while (position[0] - 1 >= 0 && board[position[0] - 1].charAt(position[1]) != 'D') position[0]--;
                break;
        }
        
        return position;
    }
}