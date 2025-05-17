package com.ayoub;

import java.util.Scanner;

public class TicTacToeGame {

    private static final Scanner sc = new Scanner(System.in);

    private static char[][] initializeBoard() {
        char[][] board = new char[3][3];
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = '-';
            }
        }
        return board;
    }

    private static void printBoard(char[][] board) {
        for (int row = 0; row < board.length; row++) {
            System.out.print("|");
            for (int col = 0; col < board[row].length; col++) {
                System.out.print(" " + board[row][col] + " |");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    private static void getPlayerMove(char[][] board, char currentPlayer) {
        int row, col;
        while (true) {
            System.out.println("Player " + currentPlayer + ", enter your move (row and column 0–2):");

            row = sc.nextInt();
            col = sc.nextInt();
            sc.nextLine();  // consume the leftover newline

            if (row < 0 || row > 2 || col < 0 || col > 2) {
                System.out.println("Out of range. Rows and columns must be between 0 and 2.");
                continue;
            }

            if (board[row][col] != '-') {
                System.out.println("cell is already taken .");
                continue;
            }

            // Place the move and exit loop
            board[row][col] = currentPlayer;
            break;
        }
    }

    private static boolean checkWin(char[][] board, char currentPlayer){
        // check rows
        for (int row = 0; row < board.length; row++){
            if (board[row][0] == currentPlayer && board[row][1] == currentPlayer && board[row][2] == currentPlayer){
                return true;
            }
        }
        // check columns
        for (int col = 0; col < board[0].length; col++){
            if (board[0][col] == currentPlayer && board[1][col] == currentPlayer && board[2][col] == currentPlayer){
                return true;
            }
        }
        // check diagonal
        if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer){
            return true;
        }
        if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer){
            return true;
        }
        return false;
    }

    private static boolean checkDraw(char[][] board) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] == '-') {
                    return false;
                }
            }
        }
        // No empty cells left → it’s a draw
        return true;
    }

    private static char switchPlayer(char currentPlayer){
        return (currentPlayer == 'X') ? 'O' : 'X';
    }

    private static boolean playAgain() {
        while (true) {
            System.out.print("Play again? (Y/N): ");
            String answer = sc.nextLine().trim().toUpperCase();

            if (answer.equals("Y")) {
                return true;
            }
            if (answer.equals("N")) {
                return false;
            }
        }
    }

    public static void main(String[] args) {
        do {
            char[][] board = initializeBoard();
            char currentPlayer = 'X';

            while (true) {
                printBoard(board);
                getPlayerMove(board, currentPlayer);

                if (checkWin(board, currentPlayer)) {
                    System.out.println("Player " + currentPlayer + " wins!");
                    printBoard(board);
                    break;
                }

                if (checkDraw(board)) {
                    System.out.println("It's a draw!");
                    printBoard(board);
                    break;
                }

                currentPlayer = switchPlayer(currentPlayer);
            }

        } while (playAgain());
        sc.close();
    }

}