import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class mancala {
	public static void main(String args[]) {
		Scanner sc = null;
		try {
			if (args[0].equals("-i"))
				sc = new Scanner(new File(args[1]));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Input input = new Input(sc);
		MancalaState initialState = input.getMancalaState();
		TreeNode root = new TreeNode(initialState);
		int player = input.getPlayer();

		switch (input.getTask()) {
		case 1: {
			root.name = "root";
			Logger.traverseLog(root.name, (root.depth - 1), Integer.MIN_VALUE);
			root.minimax(player, 1, 2, player);
			TreeNode node = root;
			while (true) {
				TreeNode max = node.children.get(0);
				for (TreeNode child : node.children) {
					if (child.score > max.score) {
						max = child;
					}
				}
				if (max.isIntermediate) {
					node = max;
				} else {
					Logger.writeState(max.toString());
					break;
				}
			}
			Logger.stateWriter.close();
			Logger.stateWriter = null;
			break;
		}
		case 2: {
			root.name = "root";
			Logger.traverseLog(root.name, (root.depth - 1), Integer.MIN_VALUE);
			root.minimax(player, 1, input.getCutOffDepth() + 1, player);
			TreeNode node = root;
			while (true) {
				TreeNode max = node.children.get(0);
				for (TreeNode child : node.children) {
					if (child.score > max.score) {
						max = child;
					}
				}
				if (max.isIntermediate) {
					node = max;
				} else {
					Logger.writeState(max.toString());
					break;
				}
			}
			Logger.traverseLogWriter.close();
			Logger.stateWriter.close();
			Logger.stateWriter = null;
			break;
		}
		case 3: {
			root.name = "root";
			Logger.traverseLog(root.name, (root.depth - 1), Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
			root.minimax(player, 1, input.getCutOffDepth() + 1, player, Integer.MIN_VALUE, Integer.MAX_VALUE);
			TreeNode node = root;
			while (true) {
				TreeNode max = node.children.get(0);
				for (TreeNode child : node.children) {
					if (child.score > max.score) {
						max = child;
					}
				}
				if (max.isIntermediate) {
					node = max;
				} else {
					Logger.writeState(max.toString());
					break;
				}
			}
		}
			Logger.traverseLogWriter.close();
			Logger.stateWriter.close();
			Logger.stateWriter = null;
			break;
		}

	}

	private static TreeNode findMaxEval(TreeNode node, int player) {
		System.out.println(node);
		while (node.isIntermediate) {
			node.generateChildren(player);
			if (node.children == null) {
				return node;
			}
			TreeNode maxNode = node;
			for (int j = 0; j < node.children.size(); j++) {
				System.out.println(node.getChildren().get(j));
				if (node.getChildren().get(j).getState().getEvaluation() > maxNode.getState().getEvaluation()) {
					maxNode = node.children.get(j);
				}
			}
			node = maxNode;
		}
		return node;
	}
}
