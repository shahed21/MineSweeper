package com.shahedrahim.minesweeper;

import java.util.Random;

class Board {
    private static final String TAG = "Board";

    public static final int ROWS = 10;
    public static final int COLS = 10;
    public static final int BOARDSIZE = ROWS * COLS;

    private static final int BOMB = -2;
    private static final int UNDISTURBED = -1;
    private static final int NO_BOMB = 0;

    private Boolean[] bombs;
    private Integer[] board;

    public Board(int numBombs) {
        bombs = new Boolean[BOARDSIZE];
        board = new Integer[BOARDSIZE];

        for (int i= 0;  i<BOARDSIZE; i++) {
            bombs[i] = false;
            board[i] = UNDISTURBED;
        }

        Random r = new Random();
        for (int i=0; i<numBombs; i++) {
            int bombPos = (int) r.nextInt(BOARDSIZE);
            //TODO check if there is a bomb there already, to get exact number of bombs
            bombs[bombPos] = true;
        }
    }

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
