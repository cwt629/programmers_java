enum Direction {
    LEFT,
    RIGHT,
    UP,
    DOWN
};

class Solution {
    public long solution(int n, int m, int x, int y, int[][] queries) {
        long answer = 0;
        int startRow = x, endRow = x, startCol = y, endCol = y;
        
        // 역순으로 진행한다
        for (int i = queries.length - 1; i >= 0; i--){
            int[] query = queries[i];
            Direction dir = query[0] == 0? Direction.LEFT : query[0] == 1? Direction.RIGHT :
            query[0] == 2? Direction.UP : Direction.DOWN;
            
            int moves = query[1];
            
            switch(dir){
                case LEFT:
                    // 오른쪽으로 이동한다. 단, 현 위치가 왼쪽 끝이면 end만 늘린다
                    endCol = (endCol + moves >= m)? m - 1 : endCol + moves;
                    if (startCol > 0) startCol += moves;
                    break;
                    
                case RIGHT:
                    // 왼쪽으로 이동한다. 단, 현 위치가 오른쪽 끝이면 start만 줄인다
                    startCol = (startCol - moves < 0)? 0 : startCol - moves;
                    if (endCol < m - 1) endCol -= moves;
                    break;
                    
                case UP:
                    // 아래쪽으로 이동한다. 단, 현 위치가 위쪽 끝이면 end만 늘린다
                    endRow = (endRow + moves >= n)? n - 1 : endRow + moves;
                    if (startRow > 0) startRow += moves;
                    break;
                    
                case DOWN:
                    // 위쪽으로 이동한다. 단, 현 위치가 아래쪽 끝이면 start만 줄인다
                    startRow = (startRow - moves < 0)? 0 : startRow - moves;
                    if (endRow < n - 1) endRow -= moves;
                    break;
            }
            
            // 어느쪽이라도 start>end가 되는 부분이 생기면, 불가능한 것
            if (startRow > endRow || startCol > endCol) return 0;
        }
        
        answer = (long)(endRow - startRow + 1) * (long)(endCol - startCol + 1);
        return answer;
    }
}