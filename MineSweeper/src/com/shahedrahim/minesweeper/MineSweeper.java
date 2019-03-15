/**
 * 
 */
package com.shahedrahim.minesweeper;

import java.util.Scanner;

/**
 * @author shahe
 *
 */
public class MineSweeper {
	
	Board board;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Scanner scan = new Scanner(System.in);
        System.out.print("Please enter the number of bombs you want >");
        int numBombs = scan.nextInt();

        System.out.print("Please enter the number of rows you want in the board >");
        int numRows = scan.nextInt();

        System.out.print("Please enter the number of columns you want in the board >");
        int numCols = scan.nextInt();

        Board board = new Board(numBombs, numRows, numCols);
        boolean bombExploded = false;
        
        while(!bombExploded) {
            System.out.println("Please enter the position number you want to click.");
            System.out.print("Row >");
        	int rpos = scan.nextInt();
            System.out.print("Col >");
        	int cpos = scan.nextInt();
        	
        	bombExploded = board.click(rpos, cpos);
        	board.printBoardConsole();
        	if (bombExploded)
        		System.out.println("Bomb Exploded");
        }
        scan.close();
	}
}
