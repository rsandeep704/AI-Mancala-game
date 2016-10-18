import java.util.List;

public class MancalaState {
	private int player;
	private int[] boardPlayer1;
	private int[] boardPlayer2;
	private int numberOfStonesInMancala1;
	private int numberOfStonesInMancala2;
	private boolean isIntermediateState;
	MancalaState parent;
	List<MancalaState> children;
	boolean gameOver;

	public boolean isIntermediateState() {
		return isIntermediateState;
	}

	public void setIntermediateState(boolean isIntermediateState) {
		this.isIntermediateState = isIntermediateState;
	}

	public MancalaState getParent() {
		return parent;
	}

	public void setParent(MancalaState parent) {
		this.parent = parent;
	}

	public List<MancalaState> getChildren() {
		return children;
	}

	public void setChildren(List<MancalaState> children) {
		this.children = children;
	}

	public int getPlayer() {
		return player;
	}

	public void setPlayer(int player) {
		this.player = player;
	}

	public int[] getBoardPlayer1() {
		return boardPlayer1;
	}

	public void setBoardPlayer1(int[] boardPlayer1) {
		this.boardPlayer1 = boardPlayer1;
	}

	public int[] getBoardPlayer2() {
		return boardPlayer2;
	}

	public void setBoardPlayer2(int[] boardPlayer2) {
		this.boardPlayer2 = boardPlayer2;
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

	public int getEvaluation() {
		if (player == 1) {
			return numberOfStonesInMancala1 - numberOfStonesInMancala2;
		} else {
			return numberOfStonesInMancala2 - numberOfStonesInMancala1;
		}
	}

	@Override
	public String toString() {
		String builder = "";
		for (int x : boardPlayer2)
			builder += (x + " ");
		builder = builder.trim();

		builder += "\n";
		for (int x : boardPlayer1)
			builder += (x + " ");
		builder = builder.trim();

		return builder + "\n" + numberOfStonesInMancala2 + "\n" + numberOfStonesInMancala1;
	}

	public boolean play(int player, int startLocation) {
		startLocation--;
		if (startLocation > boardPlayer1.length - 1) {
			return false;
		}
		int nextLocation;
		if (player == 1) {
			if (boardPlayer1[startLocation] == 0) {
				return false;
			}
			int playerToPutTo = player;
			nextLocation = startLocation + 1;
			int temp = boardPlayer1[startLocation];
			boardPlayer1[startLocation] = 0;
			while (temp > 0) {
				if (nextLocation == boardPlayer1.length) {
					playerToPutTo = 2;
					temp--;
					numberOfStonesInMancala1++;
					if (temp == 0) {
						return true;
					}
					nextLocation--;
					continue;
				} else if (nextLocation == -1) {
					playerToPutTo = 1;
					nextLocation++;
				}
				if (playerToPutTo == 1) {
					temp--;
					boardPlayer1[nextLocation]++;
					if (temp == 0 && boardPlayer1[nextLocation] == 1) {
						numberOfStonesInMancala1 += boardPlayer2[nextLocation];
						numberOfStonesInMancala1 += boardPlayer1[nextLocation];
						boardPlayer2[nextLocation] = 0;
						boardPlayer1[nextLocation] = 0;
					}
					nextLocation++;
				} else {
					temp--;
					boardPlayer2[nextLocation]++;
					nextLocation--;
				}
			}

		} else { // player 2
			if (boardPlayer2[startLocation] == 0) {
				return false;
			}
			int playerToPutTo = player;
			nextLocation = startLocation - 1;
			int temp = boardPlayer2[startLocation];
			boardPlayer2[startLocation] = 0;
			while (temp > 0) {
				if (nextLocation == -1) {
					playerToPutTo = 1;
					temp--;
					numberOfStonesInMancala2++;
					if (temp == 0) {
						return true;
					}
					nextLocation++;
					continue;
				} else if (nextLocation == boardPlayer2.length) {
					playerToPutTo = 2;
					nextLocation--;
				}
				if (playerToPutTo == 1) {
					temp--;
					boardPlayer1[nextLocation]++;
					nextLocation++;
				} else {
					temp--;
					boardPlayer2[nextLocation]++;
					if (temp == 0 && boardPlayer2[nextLocation] == 1) {
						numberOfStonesInMancala2 += boardPlayer2[nextLocation];
						numberOfStonesInMancala2 += boardPlayer1[nextLocation];
						boardPlayer2[nextLocation] = 0;
						boardPlayer1[nextLocation] = 0;
					}
					nextLocation--;
				}
			}
		}
		return false;
	}

	public boolean postPlayCheck(int player) {
		// check if player has a valid move
		if (player == 1) {
			for (int x : boardPlayer1) {
				if (x != 0) {
					return false; // There is a valid move
				}
			}
			// Game over
			for (int x = 0; x < boardPlayer2.length; x++) {
				numberOfStonesInMancala2 += boardPlayer2[x];
				boardPlayer2[x] = 0;
			}
		} else if (player == 2) {
			for (int x : boardPlayer2) {
				if (x != 0) {
					return false; // There is a valid move
				}
			}
			// Game over
			for (int x = 0; x < boardPlayer1.length; x++) {
				numberOfStonesInMancala1 += boardPlayer1[x];
				boardPlayer1[x] = 0;
			}
		}
		return true;
	}

	public MancalaState clone() {
		MancalaState newState = new MancalaState();
		newState.player = player;
		newState.numberOfStonesInMancala1 = numberOfStonesInMancala1;
		newState.numberOfStonesInMancala2 = numberOfStonesInMancala2;
		newState.boardPlayer1 = boardPlayer1.clone();
		newState.boardPlayer2 = boardPlayer2.clone();
		return newState;
	}

	public static void main(String args[]) {
		MancalaState state = new MancalaState();
		int[] array1 = { 5, 4, 1, 3, 2 };
		int[] array2 = { 11, 6, 4, 5, 1 };
		state.setBoardPlayer1(array2);
		state.setBoardPlayer2(array1);
		state.setNumberOfStonesInMancala1(0);
		state.setNumberOfStonesInMancala2(0);
		System.out.println(state);
		state.play(1, 1);
		System.out.println(state);
	}
}
