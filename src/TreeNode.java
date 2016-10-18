import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TreeNode {
	int score;
	MancalaState state;
	List<TreeNode> children;
	TreeNode parent;
	int depth = -1;
	String name = "";
	boolean isIntermediate;

	public TreeNode(MancalaState state) {
		this.state = state;
		this.depth = 1;
	}

	public TreeNode(MancalaState state, int depth) {
		this.state = state;
		this.depth = depth;
	}

	public MancalaState getState() {
		return state;
	}

	public void setState(MancalaState state) {
		this.state = state;
	}

	public List<TreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}

	public TreeNode getParent() {
		return parent;
	}

	public void setParent(TreeNode parent) {
		this.parent = parent;
	}

	@Override
	public String toString() {
		return this.state.toString();
	}

	public void generateChildren(int player) {
		generateTree(player, 1);

	}

	public int minimax(int player, int depth, int maxDepth, int max, int alpha, int beta) {
		if (depth == maxDepth || state.gameOver) {
			return this.getState().getEvaluation();
		}
		int score;
		generateChildren(player);
		if (player == max) {
			for (int i = 0; i < this.getChildren().size(); i++) {
				score = Integer.MAX_VALUE;
				if ((depth == maxDepth - 1 && !children.get(i).isIntermediate) || children.get(i).state.gameOver)
					score = children.get(i).state.getEvaluation();
				else if (children.get(i).isIntermediate)
					score = Integer.MIN_VALUE;
				TreeNode child = children.get(i);
				Logger.traverseLog(child.name, (child.depth - 1), score, alpha, beta);
				child.score = score;
				if (child.isIntermediate) {
					score = child.minimax(player, depth, maxDepth, max, alpha, beta);
				} else {
					score = child.minimax(player == 1 ? 2 : 1, depth + 1, maxDepth, max, alpha, beta);
				}
				child.score = score;
				int temp = alpha;
				if (score > alpha) {
					alpha = score;
				}
				if (alpha >= beta) {
					Logger.traverseLog(this.name, (this.depth - 1), alpha, temp, beta);
					break;
				}
				Logger.traverseLog(this.name, (this.depth - 1), alpha, alpha, beta);

			}
			return alpha;
		} else {
			for (int i = 0; i < this.getChildren().size(); i++) {
				score = Integer.MIN_VALUE;
				if ((depth == maxDepth - 1 && !children.get(i).isIntermediate) || children.get(i).state.gameOver)
					score = children.get(i).state.getEvaluation();
				if (children.get(i).isIntermediate)
					score = Integer.MAX_VALUE;
				TreeNode child = children.get(i);
				Logger.traverseLog(child.name, (child.depth - 1), score, alpha, beta);
				child.score = score;
				if (child.isIntermediate) {
					score = child.minimax(player, depth, maxDepth, max, alpha, beta);
				} else {
					score = child.minimax(player == 1 ? 2 : 1, depth + 1, maxDepth, max, alpha, beta);
				}
				child.score = score;
				int temp = beta;
				if (score < beta) {
					beta = score;
					child.score = score;
				}
				if (alpha >= beta) {
					Logger.traverseLog(this.name, (this.depth - 1), beta, alpha, temp);
					break;
				}
				Logger.traverseLog(this.name, (this.depth - 1), beta, alpha, beta);

			}
			return beta;

		}
	}

	public int minimax(int player, int depth, int maxDepth, int max) {
		if (depth == maxDepth || state.gameOver) {
			return this.getState().getEvaluation();
		}
		int bestScore;
		int score;
		generateChildren(player);
		if (player == max) {
			bestScore = Integer.MIN_VALUE;
			for (int i = 0; i < this.getChildren().size(); i++) {
				score = Integer.MAX_VALUE;
				if ((depth == maxDepth - 1 && !children.get(i).isIntermediate) || children.get(i).state.gameOver)
					score = children.get(i).state.getEvaluation();
				else if (children.get(i).isIntermediate)
					score = Integer.MIN_VALUE;
				TreeNode child = children.get(i);
				Logger.traverseLog(child.name, (child.depth - 1), score);
				child.score = score;
				if (child.isIntermediate) {
					score = child.minimax(player, depth, maxDepth, max);
				} else {
					score = child.minimax(player == 1 ? 2 : 1, depth + 1, maxDepth, max);
				}
				child.score = score;
				if (score > bestScore) {
					bestScore = score;
					child.score = bestScore;
				}
				Logger.traverseLog(this.name, (this.depth - 1), bestScore);
			}
		} else {
			bestScore = Integer.MAX_VALUE;
			for (int i = 0; i < this.getChildren().size(); i++) {
				score = Integer.MIN_VALUE;
				if ((depth == maxDepth - 1 && !children.get(i).isIntermediate) || children.get(i).state.gameOver)
					score = children.get(i).state.getEvaluation();
				if (children.get(i).isIntermediate)
					score = Integer.MAX_VALUE;
				TreeNode child = children.get(i);
				Logger.traverseLog(child.name, (child.depth - 1), score);
				child.score = score;
				if (child.isIntermediate) {
					score = child.minimax(player, depth, maxDepth, max);
				} else {
					score = child.minimax(player == 1 ? 2 : 1, depth + 1, maxDepth, max);
				}
				child.score = score;
				if (score < bestScore) {
					bestScore = score;
					child.score = bestScore;
				}
				Logger.traverseLog(this.name, (this.depth - 1), bestScore);

			}
		}
		return bestScore;
	}

	public void generateTree(int player, int maxDepth) {
		LinkedList<TreeNode> toBeExpanded = new LinkedList<TreeNode>();
		toBeExpanded.addLast(this);
		while (!toBeExpanded.isEmpty()) {
			TreeNode node = toBeExpanded.removeFirst();
			node.children = new ArrayList<>();
			int length = node.state.getBoardPlayer1().length;
			for (int i = 1; i <= length; i++) {
				boolean gameOver = false;
				MancalaState childState = node.state.clone();
				if (player == 1) {
					if (childState.getBoardPlayer1()[i - 1] == 0) {
						continue;
					}
				} else {
					if (childState.getBoardPlayer2()[i - 1] == 0) {
						continue;
					}
				}
				TreeNode childNode;
				if (childState.play(player, i) == false) {
					childNode = new TreeNode(childState.clone(), node.depth + 1);
					if (childNode.state.postPlayCheck(player == 1 ? 2 : 1) || childNode.state.postPlayCheck(player)) {
						childNode.state.gameOver = true;
						gameOver = true;
					}

					childNode.isIntermediate = false;
					childNode.name = (player == 1 ? "B" : "A") + (i + 1);
				} else {
					childNode = new TreeNode(childState.clone(), node.depth + 1);
					if (childNode.state.postPlayCheck(player) || childNode.state.postPlayCheck(player == 1 ? 2 : 1)) {
						childNode.state.gameOver = true;
						gameOver = true;
					} else {
						childNode.isIntermediate = true;
					}
					childNode.name = (player == 1 ? "B" : "A") + (i + 1);
				}
				if (node.isIntermediate) {
					childNode.depth = node.depth;
				}
				childNode.parent = node;
				node.children.add(childNode);
				if (childNode.depth <= maxDepth && !gameOver) {
					toBeExpanded.addLast(childNode);
				}
			}
		}

	}

	public static void main(String args[]) {
		MancalaState state = new MancalaState();
		int[] array1 = { 2, 1, 3 };
		int[] array2 = { 0, 0, 1 };
		state.setBoardPlayer1(array2);
		state.setBoardPlayer2(array1);
		state.setNumberOfStonesInMancala1(0);
		state.setNumberOfStonesInMancala2(0);
		TreeNode node = new TreeNode(state);
		node.generateChildren(1);
		for (int i = 0; i < node.children.size(); i++) {
			System.out.println(node.getChildren().get(i) + "" + node.getChildren().get(i).name
					+ node.children.get(i).depth + "\n");
		}
	}

}
