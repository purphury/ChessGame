package application.model;

import application.controller.BoardController;
import application.model.Board.Type;

public class Timer implements Runnable {
	private long playerOne; //Type.WHITE//
	private long playerTwo; //Type.BLACK//
	private int count;
	public Timer() {
		//300000 milliseconds in 5 minutes//
		playerOne = 300000;
		playerTwo = 300000;
		count = 0;
	}
	
	@Override
	public void run() {
		long startTime = System.currentTimeMillis();
		long now;
		Type turn = BoardController.boardModel.getTurn();
		while(playerOne > 0 && playerTwo > 0) {
			now = System.currentTimeMillis();
			//Check if it's the other player's turn//		
			if(BoardController.boardModel.getTurn() != turn) {
				turn = BoardController.boardModel.getTurn();
				//Restart the timer//
				startTime = System.currentTimeMillis();
				continue;
			}
			//Check if currentTime has changed and subtract milliseconds elapsed from current player//
			if(now > startTime) {
				synchronized(this.getClass()) {
					if(turn == Type.WHITE) {
						playerOne -= (now - startTime);
					}
					else if(turn == Type.BLACK){
						playerTwo -= (now - startTime);
					}
					count += now;
					startTime = System.currentTimeMillis();
				}
			}
		}
	}
	
	public synchronized long getCurrentPlayerTimeInMilliseconds() {
		return BoardController.boardModel.getTurn() == Type.WHITE 
				? playerOne : playerTwo;
	}

	public long getPlayerOne() {
		return playerOne;
	}
	public void setPlayerOne(long x) {
		playerOne = x;
	}
	public long getPlayerTwo() {
		return playerTwo;
	}
	public void setPlayerTwo(long x) {
		playerTwo = x;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int x) {
		count = x;
	}
}
