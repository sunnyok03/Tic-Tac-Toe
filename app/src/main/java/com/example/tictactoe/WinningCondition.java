package com.example.tictactoe;

public class WinningCondition {
    static int[][] win = {{1, 2, 3},
            {4, 5, 6},
            {7, 8, 9},
            {1, 4, 7},
            {2, 5, 8},
            {3, 6, 9},
            {1, 5, 9},
            {3, 5, 7}};

    public static boolean check(int[] currStatus) {
        boolean status = false;
        for (int i = 0; i < 8; i++) {
            if (currStatus[win[i][0]] != 0 && currStatus[win[i][0]] == currStatus[win[i][1]]
                    && currStatus[win[i][1]] == currStatus[win[i][2]]) {
                status = true;
                break;
            }
        }
        return status;
    }

}
