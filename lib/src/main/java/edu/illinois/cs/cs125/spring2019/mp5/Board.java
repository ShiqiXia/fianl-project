package edu.illinois.cs.cs125.spring2019.mp5;



public class Board {
    /** the length of the board's side.*/
    private int length = 3;
    /** the pieces on the board.*/
    private Player[][] board;
    /** if the game began.*/
    private boolean start = false;
    /** if the game is ended.*/
    private boolean end = false;
    /** the number in row needed to win*/
    private int N = 3;
    public Board(int setLength) {
        length = setLength;
        N = length;
        board = new Player [length][length];
        start = true;
    }
    public Board(Player[][] setBoard) {
        board = setBoard;
        length = setBoard.length;
        N = length;
        start = true;
    }
    public Board setLength(int setLength) {
        if (setLength > 3 && setLength < 10) {
            return new Board(setLength);
        }
        return null;
    }
    public int getLength(){
        return length;
    }
    public Player[][] getBoard(){
        return board;
    }
    public Player[][] setAt(Player player, int x, int y){
        if (start && x >= 0 && x < length && y >= 0 && y < length && board[x][y] == null) {
            board[x][y] = player;
        }
        return board;
    }
    public Player getWinner() {
        boolean flag = true;
        if (start) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {
                    if (board[i][j] == null) {
                        flag = false;
                    }
                }
            }
        }
        if (flag) {
            end = true;
        }
        if (checkWinDiagnals()!= null && end) {
            return checkWinDiagnals();
        }
        if (checkWinRows() != null && end) {
            return checkWinRows();
        }
        if (checkWinColumns() != null && end) {
            return checkWinColumns();
        }
        return null;
    }
    public Player checkWinDiagnals() {
        if (start) {
            boolean firstFlag = true;
            boolean secondFlag = true;
            for (int i = 0; i < board.length - 1; i++) {
                if (!board[i][i].equals(board[i + 1][i + 1])) {
                    firstFlag = false;
                }
            }
            for (int i = 0; i < board.length - 1; i++) {
                if (!board[i][board.length - i - 1].equals(board[i + 1][board.length - i - 2])) {
                    secondFlag = false;
                }
            }
            if (firstFlag) {
                end = true;
                return board[0][0];
            }
            if (secondFlag) {
                end = true;
                return board[0][board.length - 1];
            }
        }
        return null;
    }
    public Player checkWinRows() {
        if (start) {
            for (int i = 0; i < board.length; i++) {
                boolean flag = true;
                for (int j = 0; j < board.length - 1; j++) {
                    if (!board[j][i].equals(board[j + 1][i])) {
                        flag = false;
                    }
                }
                if (flag) {
                    end = true;
                    return board[0][i];
                }
            }
        }
        return null;
    }
    public Player checkWinColumns() {
        if (start) {
            for (int i = 0; i < board.length; i++) {
                boolean flag =true;
                for (int j = 0; j < board.length - 1; j++) {
                    if (!board[i][j].equals(board[i][j + 1])) {
                        flag = false;
                    }
                }
                if (flag) {
                    end = true;
                    return board[i][0];
                }
            }
        }
        return null;
    }
}
