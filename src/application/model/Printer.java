package application.model;

public class Printer {
	
	public static void boardCheck(Board previousBoard, Board current) {
		System.out.println("--------------------------------------------");
		System.out.println();
		System.out.println("previous.whiteName = "+previousBoard.getWhiteName());
		System.out.println("previous.blackName = "+previousBoard.getBlackName());
		System.out.println("previous.rook1 = "+previousBoard.getRook1());
		System.out.println("previous.rook2 = "+previousBoard.getRook2());
		System.out.println("previous.blackEverChecked = "+previousBoard.isBlackEverChecked());
		System.out.println("previous.whiteEverChecked = "+previousBoard.isWhiteEverChecked());		
		System.out.println("previous.isCurrentlyCheck = "+previousBoard.isCurrentlyCheck());
		System.out.println("previous.turn = "+previousBoard.getTurn());
		System.out.println("previous.board = "+previousBoard.getBoard());
		previousBoard.display();
		
		System.out.println();
		System.out.println();
		
		System.out.println("current.whiteName = "+current.getWhiteName());
		System.out.println("current.blackName = "+current.getBlackName());
		System.out.println("current.rook1 = "+previousBoard.getRook1());
		System.out.println("current.rook2 = "+previousBoard.getRook2());
		System.out.println("current.blackEverChecked = "+previousBoard.isBlackEverChecked());
		System.out.println("current.whiteEverChecked = "+previousBoard.isWhiteEverChecked());		
		System.out.println("current.isCurrentlyCheck = "+previousBoard.isCurrentlyCheck());
		System.out.println("current.turn = "+previousBoard.getTurn());
		System.out.println("current.board = "+previousBoard.getBoard());
		System.out.println("current.previousBoard = "+current.getPreviousBoard());
		current.display();
		System.out.println();
		System.out.println("--------------------------------------------");

	}
	
	public static void printNodeLabel(Board board, Coordinate c) {
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		System.out.print(c);
		if(board.hasPiece(c)) {
			System.out.println("  "+board.getPiece(c));
		}
		System.out.println("----------------------------------");
			
	}

	public static void print(Board board, int depth, int totalDepth) {
		for(int i = 0; i < 8 ; i++) {
			System.out.print(tabs((totalDepth - depth)));

			for(int j = 0; j < 8 ; j++) {
				System.out.print("| ");
				if(board.hasPiece(i,j)) {
					System.out.print(board.getPiece(i,j).toString()+" ");
				}
				else
					System.out.print("  ");
			}
			System.out.println("|");
		}
		System.out.println();
		
	}
	
	private static String tabs(int newDepth) {
		String ret = "";
		for(int i=0; i < newDepth; i++) {
			ret+="\t";
		}
		return ret;
	}
}
