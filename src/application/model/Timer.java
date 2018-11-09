package application.model;

import application.controller.BoardController;
import application.model.Board.Type;

public class Timer implements Runnable {
	private long playerOne;
	private long playerTwo;
	
	public Timer() {
		//300 seconds in 5 minutes//
		playerOne = 300;
		playerTwo = 300;
	}
	
	@Override
	public void run() {
		while(playerOne > 0 || playerTwo > 0) {
			
		}
	}
	
	public long getCurrentPlayerTimeInSeconds() {
		return BoardController.boardModel.getTurn() == Type.WHITE 
				? playerOne : playerTwo;
	}

}
