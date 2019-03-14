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
        System.out.print("Please enter the number of bombs you want >");
        Scanner scan = new Scanner(System.in);
        int numBombs = scan.nextInt();

        Board board = new Board(numBombs);
        boolean bombExploded = false;
        
        while(!bombExploded) {
            System.out.print("Please enter the position number you want to click >");
        	int pos = scan.nextInt();
        	
        	bombExploded = board.click(pos);
        	board.printBoard();
        	if (bombExploded)
        		System.out.println("Bomb Exploded");
        }
	}

}
