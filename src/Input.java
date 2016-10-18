import java.util.Scanner;

public class Input {
	int task;
	int player;
	int cutOffDepth;
	String boardPlayer1;
	String boardPlayer2;
	int numberOfStonesInMancala1;
	int numberOfStonesInMancala2;

	public Input(Scanner sc) {
		if (sc == null)
			return;
		task = Integer.parseInt(sc.nextLine());
		player = Integer.parseInt(sc.nextLine());
		cutOffDepth = Integer.parseInt(sc.nextLine());
		boardPlayer2 = sc.nextLine();
		boardPlayer1 = sc.nextLine();
		numberOfStonesInMancala2 = Integer.parseInt(sc.nextLine());
		numberOfStonesInMancala1 = Integer.parseInt(sc.nextLine());
	}

	public int getNumberOfStonesInMancala1() {
		return numberOfStonesInMancala1;
	}

	public void setNumberOfStonesInMancala1(int numberOfStonesInMancala1) {
		this.numberOfStonesInMancala1 = numberOfStonesInMancala1;
	}

	public int getNumberOfStonesInMancala2() {
		return numberOfStonesInMancala2;
	}

	public void setNumberOfStonesInMancala2(int numberOfStonesInMancala2) {
		this.numberOfStonesInMancala2 = numberOfStonesInMancala2;
	}

	public int getTask() {
		return task;
	}

	public void setTask(int task) {
		this.task = task;
	}

	public int getPlayer() {
		return player;
	}

	public void setPlayer(int player) {
		this.player = player;
	}

	public int getCutOffDepth() {
		return cutOffDepth;
	}

	public void setCutOffDepth(int cutOffDepth) {
		this.cutOffDepth = cutOffDepth;
	}

	public String getBoardPlayer1() {
		return boardPlayer1;
	}

	public void setBoardPlayer1(String boardPlayer1) {
	}

	public String getBoardPlayer2() {
		return boardPlayer2;
	}

	public void setBoardPlayer2(String boardPlayer2) {
		this.boardPlayer2 = boardPlayer2;
	}

	public MancalaState getMancalaState() {
		MancalaState state = new MancalaState();

		state.setPlayer(player);
		state.setNumberOfStonesInMancala1(numberOfStonesInMancala1);
		state.setNumberOfStonesInMancala2(numberOfStonesInMancala2);

		String temp[] = boardPlayer1.split(" ");
		int[] board = new int[temp.length];
		for (int i = 0; i < temp.length; i++) {
			board[i] = Integer.parseInt(temp[i]);
		}
		state.setBoardPlayer1(board);

		temp = boardPlayer2.split(" ");
		board = new int[temp.length];
		for (int i = 0; i < temp.length; i++) {
			board[i] = Integer.parseInt(temp[i]);
		}
		state.setBoardPlayer2(board);
		state.setParent(null);
		return state;
	}

}
