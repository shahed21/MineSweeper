package com.shahedrahim.minesweeper;

//import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

/**
 * This is the board for the mine sweeper game
 * @author shahe
 *
 */
class Board {
//    private static final String TAG = "Board";

    // Number of numRows
    public int numRows;
    // Number of numCols
    public int numCols;
    // Board Size
    public int boardSize;

    // Status of cells on the board
    private static final int BOMB = -2;
    private static final int UNDISTURBED = -1;
    private static final int NO_BOMB = 0;

    // Array of booleans showing where the bombs are placed
    private Boolean[][] bombs;

    private ArrayList<Integer> bombPositions;
    // Array of integers showing the status of the board
    private Integer[][] board;

    /**
     * Constructor: accepts the number of bombs to be introduced in the game
     * @param numBombs
     */
    public Board(int numBombs, int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;
        this.boardSize  = numRows * numCols;
        //Initialize the board
        bombs = new Boolean[numRows][numCols];
        board = new Integer[numRows][numCols];
        bombPositions = new ArrayList<>();

        for (int i = 0; i< numRows; i++) {
            for (int j = 0; j< numCols; j++) {
                bombs[i][j] = false;
                board[i][j] = UNDISTURBED;
            }
        }

        //Insert bombs
        Random r = new Random();
        for (int i=0; i<numBombs; i++) {
            int bombPos;
            // Repeat until empty slot found
            do {
                bombPos = (int) r.nextInt(boardSize);
            } while (bombs[rpos(bombPos)][cpos(bombPos)]);
            bombs[rpos(bombPos)][cpos(bombPos)] = true;
            bombPositions.add(bombPos);
        }
    }

    private int rpos(int pos) {
        return pos/ numCols;
    }

    private int cpos(int pos) {
        return pos% numCols;
    }

    /**
     * Simulates a click on the board
     * @param pos
     * @return boolean if the click was on a bomb
     */
    /**
     * Simulates a click on the board
     * @param rpos row position
     * @param cpos column position
     * @return boolean if the click was on a bomb
     */
    public boolean click(int rpos, int cpos) {
        if ((rpos< numRows && rpos>=0)&&(cpos< numCols && cpos>=0)) {
            if (bombs[rpos][cpos]) {
                board[rpos][cpos] = BOMB;
                return true; //Found the bomb
            } else {
                exposeBoard(rpos, cpos);
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * this method counts the number of bombs in the immediate vicinity of the position
     * @param rpos
     * @param cpos
     * @return number of bombs
     */
    private int countNeighborBombs(int rpos, int cpos) {
        int count = 0;
        if (rpos>0) {
            if (bombs[rpos-1][cpos]) {
                count++;
            }
        }
        if (rpos<numRows-1) {
            if (bombs[rpos+1][cpos]) {
                count++;
            }
        }

        if (cpos >0) {
            if (bombs[rpos][cpos-1]) {
                count++;
            }
        }

        if (cpos < numCols-1) {
            if (bombs[rpos][cpos+1]) {
                count++;
            }
        }

        if (rpos>0 && cpos >0) {
            if (bombs[rpos-1][cpos-1]) {
                count++;
            }
        }
        if (rpos<numRows-1 && cpos >0) {
            if (bombs[rpos+1][cpos-1]) {
                count++;
            }
        }

        if (rpos>0 && cpos < numCols-1) {
            if (bombs[rpos-1][cpos+1]) {
                count++;
            }
        }

        if (rpos<numRows-1 && cpos < numCols-1) {
            if (bombs[rpos+1][cpos+1]) {
                count++;
            }
        }
        return count;
    }

    /**
     * recursive function that finds and exposes all empty cells
     * @param rpos
     * @param cpos
     */
    private void exposeBoard(int rpos, int cpos) {
        if (board[rpos][cpos] == UNDISTURBED) {
            int countBombsAround = countNeighborBombs(rpos, cpos);
            if (countBombsAround==NO_BOMB) {
                board[rpos][cpos] = NO_BOMB;
                if (rpos>0) {
                    exposeBoard(rpos-1, cpos);
                }

                if (rpos< numRows -1) {
                    exposeBoard(rpos+1, cpos);
                }

                if (cpos > 0) {
                    exposeBoard(rpos, cpos-1);
                }

                if (cpos < numCols -1) {
                    exposeBoard(rpos, cpos+1);
                }

                if (rpos>0 && cpos >0) {
                    exposeBoard(rpos-1, cpos-1);
                }
                if (rpos<numRows-1 && cpos >0) {
                    exposeBoard(rpos+1, cpos-1);
                }

                if (rpos>0 && cpos < numCols-1) {
                    exposeBoard(rpos-1, cpos+1);
                }

                if (rpos<numRows-1 && cpos < numCols-1) {
                    exposeBoard(rpos+1, cpos+1);
                }
            } else {
                board[rpos][cpos] = countBombsAround;
            }
        }
    }


    public String toString() {
        String val = "";
        for (int i = 0; i< numRows; i++ ) {
            for (int j = 0; j< numCols; j++) {
                if (board[i][j]==UNDISTURBED) {
                    val+="U";
                } else if (board[i][j]==NO_BOMB) {
                    val+=" ";
                } else if (board[i][j]==BOMB) {
                    val+="X";
                } else {
                    val+= ""+board[i][j];
                }
            }
            val+="\n";
        }
        return val;
    }

    /**
     * Prints the board onto the console
     */
    void printBoardConsole() {
        System.out.println(toString());
    }

//    /**
//     * Prints the board onto the console
//     */
//    void printBoard() {
//        Log.i(TAG, "printBoard: \n"+toString());
//    }
}
