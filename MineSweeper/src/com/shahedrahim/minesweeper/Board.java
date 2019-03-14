package com.shahedrahim.minesweeper;

import java.util.Random;

/**
 * This is the board for the mine sweeper game
 * @author shahe
 *
 */
class Board {
    private static final String TAG = "Board";

    // Number of ROWS
    public static final int ROWS = 10;
    // Number of COLS
    public static final int COLS = 10;
    // Board Size
    public static final int BOARDSIZE = ROWS * COLS;

    // Status of cells on the board
    private static final int BOMB = -2;
    private static final int UNDISTURBED = -1;
    private static final int NO_BOMB = 0;

    // Array of booleans showing where the bombs are placed
    private Boolean[] bombs;
    // Array of integers showing the status of the board
    private Integer[] board;

    /**
     * Constructor: accepts the number of bombs to be introduced in the game
     * @param numBombs
     */
    public Board(int numBombs) {
    	//Initialize the board
        bombs = new Boolean[BOARDSIZE];
        board = new Integer[BOARDSIZE];

        for (int i= 0;  i<BOARDSIZE; i++) {
            bombs[i] = false;
            board[i] = UNDISTURBED;
        }

        //Insert bombs
        Random r = new Random();
        for (int i=0; i<numBombs; i++) {
            int bombPos = (int) r.nextInt(BOARDSIZE);
            //TODO check if there is a bomb there already, to get exact number of bombs
            bombs[bombPos] = true;
        }
    }

    /**
     * Simulates a click on the board
     * @param pos
     * @return boolean if the click was on a bomb
     */
    public boolean click(int pos) {
        if (pos<BOARDSIZE && pos>=0) {
            if (bombs[pos]) {
            	board[pos] = BOMB;
                return true; //Found the bomb
            } else {
                exposeBoard(pos);
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * count the number of bombs around the cell at position pos
     * @param pos
     * @return number of bombs in neighboring cells
     */
    private int countNeighborBombs(int pos) {
        int count = 0;
        if (pos>9) {
            if (bombs[pos-10]) {
                count++;
            }
        }
        if (pos<90) {
            if (bombs[pos+10]) {
                count++;
            }
        }

        if (pos % COLS>0) {
            if (bombs[pos-1]) {
                count++;
            }
        }

        if (pos % COLS<9) {
            if (bombs[pos+1]) {
                count++;
            }
        }

        if (pos>9 && pos % COLS>0) {
            if (bombs[pos-11]) {
                count++;
            }
        }

        if (pos>9 && pos % COLS<9) {
            if (bombs[pos-9]) {
                count++;
            }
        }

        if (pos<90 && pos % COLS>0) {
            if (bombs[pos+9]) {
                count++;
            }
        }

        if (pos<90 && pos % COLS<9) {
            if (bombs[pos+11]) {
                count++;
            }
        }
        return count;
    }

    /**
     * recursive function that finds and exposes all empty cells
     * @param pos
     */
    private void exposeBoard(int pos) {
    	if (board[pos] == UNDISTURBED) {
            int countBombsAround = countNeighborBombs(pos);
            if (countBombsAround==NO_BOMB) {
            	board[pos] = NO_BOMB;
                if (pos>9) {
                    exposeBoard(pos-10);
                }
                if (pos<90) {
                    exposeBoard(pos+10);
                }

                if (pos % COLS>0) {
                    exposeBoard(pos-1);
                }

                if (pos % COLS<9) {
                    exposeBoard(pos+1);
                }

                if (pos>9 && pos % COLS>0) {
                    exposeBoard(pos-11);
                }

                if (pos>9 && pos % COLS<9) {
                    exposeBoard(pos-9);
                }

                if (pos<90 && pos % COLS>0) {
                    exposeBoard(pos+9);
                }

                if (pos<90 && pos % COLS<9) {
                    exposeBoard(pos+11);
                }
            } else {
                board[pos] = countBombsAround;
            }
    	}
    }
    
    /**
     * Prints the board onto the console
     */
    void printBoard() {
    	for (int i = 0; i<ROWS; i++ ) {
    		for (int j=0; j<COLS; j++) {
    			if (board[10*i+j]==UNDISTURBED) {
    				System.out.print("U");
    			} else if (board[10*i+j]==NO_BOMB) {
    				System.out.print(" ");
    			} else if (board[10*i+j]==BOMB) {
    				System.out.print("X");
    			} else {
    				System.out.print(board[10*i+j]);
    			}
    		}
    		System.out.println("");
    	}
    }
}
