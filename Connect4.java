import javax.swing.*;
import java.util.*;

/**
 * Created by Jesse on 8/26/2016.
 */
public class Connect4 {
    private String[][] board = {    {"  ","  ","  ","  ","  ","  ","  "},
                            {"  ","  ","  ","  ","  ","  ","  "},
                            {"  ","  ","  ","  ","  ","  ","  "},
                            {"  ","  ","  ","  ","  ","  ","  "},
                            {"  ","  ","  ","  ","  ","  ","  "},
                            {"  ","  ","  ","  ","  ","  ","  "}};

    private int rowCheck(int column) {
        for(int i = board.length-1; i >= 0; i--) {
            if(board[i][column].equals("  ")){
                return i;
            }
        }
        return 666;
    }

    private int moveSelect() {
        String[] columnOptions = {"1","2","3","4","5","6","7"};

        return JOptionPane.showOptionDialog(null, Arrays.toString(board[0]) + "\n" + Arrays.toString(board[1]) + "\n" +
                        Arrays.toString(board[2]) + "\n" + Arrays.toString(board[3]) + "\n" + Arrays.toString(board[4]) + "\n" +
                Arrays.toString(board[5]), "Move", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                        null, columnOptions, 0);

    }

    private boolean checkHoriz(int row, int column) {
        int count = 1;
        int left = 1, right = 1;
        boolean checkHoriz =true;
        while(checkHoriz) {
            if(column != 0) {
                while (board[row][column] == board[row][column - left]) {
                    count++;
                    left++;
                    if(count == 4) {
                        return true;
                    } else if(column-left < 0 ) {
                        break;
                    }
                }
            }
            if(column != 6) {
                while (board[row][column].equals(board[row][column + right])) {
                    count++;
                    right++;
                    if(count == 4) {
                        return true;
                    } else if(column + right > 6) {
                        break;
                    }
                }
            }
            checkHoriz = false;
        }
        return false;
    }

    public boolean checkVert(int row, int column) {
        int count = 1;
        int down = 1;
        boolean checkVert = true;
        while(checkVert){
            if(row != 5) {
                while(board[row + down][column] == board[row][column]){
                    count++;
                    down++;
                    if(count ==4){
                        return true;
                    } else if(row + down > 5){
                        break;
                    }
                }
            }
            checkVert = false;
        }
        return false;
    }

    private boolean checkDiag(int row, int column) {
        int count = 1;
        int up = 1, down = 1, left = 1, right = 1;
        boolean checkDiag = true;
        while(checkDiag) {
            if (row != 0 && column != 0) {
                while (board[row][column] == board[row - up][column - left]) {
                    count++;
                    left++;
                    up++;
                    System.out.println("1");
                    if (count == 4) {
                        return true;
                    } else if (row - up < 0 || column - left < 0) {
                        break;
                    }
                }
            }
            up = 1;
            down = 1;
            left = 1;
            right = 1;
            if(row != 5 && column != 6) {
                while(board[row][column] == board[row + down][column + right]) {
                    count++;
                    right++;
                    down++;
                    System.out.println("2");
                    if(count == 4) {
                        return true;
                    } else if(row + down > 5 || column + right > 6) {
                        count = 1;
                        break;
                    }
                }
            }
            up = 1;
            down = 1;
            left = 1;
            right = 1;
            if (row != 5 && column != 0) {
                while (board[row][column] == board[row + down][column - left]) {
                    count++;
                    left++;
                    down++;
                    System.out.println("3");
                    if (count == 4) {
                        return true;
                    } else if (row + down > 5 || column - left < 0) {
                        break;
                    }
                }
            }
            up = 1;
            down = 1;
            left = 1;
            right = 1;
            if(row != 0 && column != 6) {
                while(board[row][column] == board[row - up][column + right]) {
                    count++;
                    right++;
                    up++;
                    System.out.println("4");
                    if(count == 4) {
                        return true;
                    } else if(row + down < 0 || column + right > 6) {
                        count = 1;
                        break;
                    }
                }
            }
            checkDiag = false;
        }
        return false;
    }

    private boolean gameWon(int row, int column) {
        if(checkHoriz(row, column)) {
            return true;
        } else if(checkVert(row, column)) {
            return true;
        } else if(checkDiag(row, column)) {
            return true;
        }

        return false;
    }

    public void run(){
        Connect4 test = new Connect4();
        String user1 = "o", user2 = "x";
        String user = user1;
        boolean running = true, booltest;
        int column, row;
        firstloop:
        while(running) {
            do {
                column = test.moveSelect();
                System.out.println(column);
                if (column == -1) {
                    break firstloop;
                }
                row = test.rowCheck(column);
                System.out.println(row);
                if (row < 7) {
                    test.board[row][column] = user;
                    booltest = false;
                } else {
                    JOptionPane.showMessageDialog(null, "Column is full.");
                    booltest = true;
                }
            }while(booltest);
            if(test.gameWon(row, column)){
                JOptionPane.showMessageDialog(null, user.toUpperCase() + " has won!");
                running = false;
            }
            if (user.equals(user1)) {
                user = user2;
            } else {
                user = user1;
            }
        }
    }

    public static void main(String[] args) {
        Connect4 test = new Connect4();
        String user1 = "o", user2 = "x";
        String user = user1;
        boolean running = true, booltest;
        int column, row;
        firstloop:
        while(running) {
            do {
                column = test.moveSelect();
                System.out.println(column);
                if (column == -1) {
                    break firstloop;
                }
                row = test.rowCheck(column);
                System.out.println(row);
                if (row < 7) {
                    test.board[row][column] = user;
                    booltest = false;
                } else {
                    JOptionPane.showMessageDialog(null, "Column is full.");
                    booltest = true;
                }
            }while(booltest);
            if(test.gameWon(row, column)){
                JOptionPane.showMessageDialog(null, user.toUpperCase() + " has won!");
                running = false;
            }
            if (user.equals(user1)) {
                user = user2;
            } else {
                user = user1;
            }
        }
    }
}
