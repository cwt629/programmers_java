import java.util.*;

class MoveData {
    int row;
    int col;
    int moves;
    
    public MoveData(int r, int c, int m){
        row = r;
        col = c;
        moves = m;
    }
}

class Solution {
    public int solution(String[] maps) {
        int answer = 0;
        // 시작점, 레버, 출구의 좌표
        int[] start = {-1, -1}, lever = {-1, -1}, exit = {-1, -1};
        for (int i = 0; i < maps.length; i++){
            for (int j = 0; j < maps[i].length(); j++){
                switch(maps[i].charAt(j)){
                    case 'S':
                        start = new int[]{i, j};
                        break;
                        
                    case 'L':
                        lever = new int[]{i, j};
                        break;
                        
                    case 'E':
                        exit = new int[]{i, j};
                        break;
                }
            }
        }
        
        // 방문 여부
        boolean[][] visited = new boolean[maps.length][maps[0].length()];
        initVisited(visited);
        
        // BFS를 위한 queue
        Queue<MoveData> bfsQueue = new LinkedList<>();
        
        // 1. 시작 -> 레버
        visited[start[0]][start[1]] = true;
        MoveData initData = new MoveData(start[0], start[1], 0);
        bfsQueue.offer(initData);
        
        int timeToLever = -1;
        while (!bfsQueue.isEmpty()){
            MoveData current = bfsQueue.poll();
            int row = current.row, col = current.col;
            int nextMoves = current.moves + 1;
            
            // 오른쪽
            if (col < maps[row].length() - 1 && !visited[row][col + 1]
               && maps[row].charAt(col + 1) != 'X'){
                visited[row][col + 1] = true;
                // 도착 시
                if (maps[row].charAt(col + 1) == 'L'){
                    timeToLever = nextMoves;
                    break;
                }
                MoveData newData = new MoveData(row, col + 1, nextMoves);
                bfsQueue.offer(newData);
            }
            
            // 아래쪽
            if (row < maps.length - 1 && !visited[row + 1][col]
               && maps[row + 1].charAt(col) != 'X'){
                visited[row + 1][col] = true;
                // 도착 시
                if (maps[row + 1].charAt(col) == 'L'){
                    timeToLever = nextMoves;
                    break;
                }
                MoveData newData = new MoveData(row + 1, col, nextMoves);
                bfsQueue.offer(newData);
            }
            
            // 왼쪽
            if (col > 0 && !visited[row][col - 1]
               && maps[row].charAt(col - 1) != 'X'){
                visited[row][col - 1] = true;
                // 도착 시
                if (maps[row].charAt(col - 1) == 'L'){
                    timeToLever = nextMoves;
                    break;
                }
                MoveData newData = new MoveData(row, col - 1, nextMoves);
                bfsQueue.offer(newData);
            }
            
            // 위쪽
            if (row > 0 && !visited[row - 1][col]
               && maps[row - 1].charAt(col) != 'X'){
                visited[row - 1][col] = true;
                // 도착 시
                if (maps[row - 1].charAt(col) == 'L'){
                    timeToLever = nextMoves;
                    break;
                }
                MoveData newData = new MoveData(row - 1, col, nextMoves);
                bfsQueue.offer(newData);
            }
        }
        
        // 레버 실패 시 -1, 그렇지 않으면 answer에 갱신
        if (timeToLever < 0) return -1;
        answer += timeToLever;
        
        // 앞서 초기화
        initVisited(visited);
        bfsQueue.clear();
        
        // 2. 레버 -> 도착
        visited[lever[0]][lever[1]] = true;
        MoveData secondInitData = new MoveData(lever[0], lever[1], 0);
        bfsQueue.offer(secondInitData);
        
        int timeToGoal = -1;
        while (!bfsQueue.isEmpty()){
            MoveData current = bfsQueue.poll();
            int row = current.row, col = current.col;
            int nextMoves = current.moves + 1;
            
            // 오른쪽
            if (col < maps[row].length() - 1 && !visited[row][col + 1]
               && maps[row].charAt(col + 1) != 'X'){
                visited[row][col + 1] = true;
                // 도착 시
                if (maps[row].charAt(col + 1) == 'E'){
                    timeToGoal = nextMoves;
                    break;
                }
                MoveData newData = new MoveData(row, col + 1, nextMoves);
                bfsQueue.offer(newData);
            }
            
            // 아래쪽
            if (row < maps.length - 1 && !visited[row + 1][col]
               && maps[row + 1].charAt(col) != 'X'){
                visited[row + 1][col] = true;
                // 도착 시
                if (maps[row + 1].charAt(col) == 'E'){
                    timeToGoal = nextMoves;
                    break;
                }
                MoveData newData = new MoveData(row + 1, col, nextMoves);
                bfsQueue.offer(newData);
            }
            
            // 왼쪽
            if (col > 0 && !visited[row][col - 1]
               && maps[row].charAt(col - 1) != 'X'){
                visited[row][col - 1] = true;
                // 도착 시
                if (maps[row].charAt(col - 1) == 'E'){
                    timeToGoal = nextMoves;
                    break;
                }
                MoveData newData = new MoveData(row, col - 1, nextMoves);
                bfsQueue.offer(newData);
            }
            
            // 위쪽
            if (row > 0 && !visited[row - 1][col]
               && maps[row - 1].charAt(col) != 'X'){
                visited[row - 1][col] = true;
                // 도착 시
                if (maps[row - 1].charAt(col) == 'E'){
                    timeToGoal = nextMoves;
                    break;
                }
                MoveData newData = new MoveData(row - 1, col, nextMoves);
                bfsQueue.offer(newData);
            }
        }
        
        if (timeToGoal < 0) return -1;
        answer += timeToGoal;
        
        return answer;
    }
    
    // visited 배열 초기화 함수
    static void initVisited(boolean[][] visited){
        for (int i = 0; i < visited.length; i++){
            for (int j = 0; j < visited[i].length; j++){
                visited[i][j] = false;
            }
        }
    }
    
    // 임시: visited 배열 출력 함수
    static void printVisited(boolean[][] visited){
        for (int i = 0; i < visited.length; i++){
            for (int j = 0; j < visited[i].length; j++){
                System.out.print(visited[i][j]);
            }
            System.out.println();
        }
    }
}