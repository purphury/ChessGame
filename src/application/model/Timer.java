package application.model;

import application.controller.BoardController;
import application.model.Board.Type;

public class Timer implements Runnable {
	private long playerOne; //Type.WHITE//
	private long playerTwo; //Type.BLACK//
	
	public Timer() {
		//60000 milliseconds in 5 minutes//
		playerOne = 60000;
		playerTwo = 60000;
	}
	
	@Override
	public void run() {
		long startTime = System.currentTimeMillis();
		long now;
		Type turn = BoardController.boardModel.getTurn();
		while(playerOne > 0 || playerTwo > 0) {
			now = System.currentTimeMillis();
			//Check if it's the other player's turn//
			if(BoardController.boardModel.getTurn() != turn) {
				turn = BoardController.boardModel.getTurn();
				//Restart the timer//
				startTime = System.currentTimeMillis();
				continue;
			}
			//Check if currentTime has changed and subtract 1 from current player//
			if(now > startTime) {
				if(turn == Type.WHITE)
					playerOne -= now - startTime;
				else
					playerTwo -= now - startTime;
				BoardController.setTime(playerOne, playerTwo);
				startTime = System.currentTimeMillis();
			}
		}
	}
	
	public long getCurrentPlayerTimeInSeconds() {
		return BoardController.boardModel.getTurn() == Type.WHITE 
				? playerOne : playerTwo;
	}
	

}
