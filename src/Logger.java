import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class Logger {

	static PrintWriter traverseLogWriter;
	static PrintWriter stateWriter;

	public static void traverseLog(String name, int depth, int value) {
		if (traverseLogWriter == null) {
			try {
				traverseLogWriter = new PrintWriter("traverse_log.txt");
			} catch (FileNotFoundException e) {
				File f = new File("traverse_log.txt");
				try {
					f.createNewFile();
					traverseLogWriter = new PrintWriter("traverse_log.txt");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
			traverseLogWriter.println("Node,Depth,Value");
		}
		if (value == Integer.MAX_VALUE)
			traverseLogWriter.println(name + "," + depth + ",Infinity");
		else if (value == Integer.MIN_VALUE)
			traverseLogWriter.println(name + "," + depth + ",-Infinity");
		else
			traverseLogWriter.println(name + "," + depth + "," + value);
	}

	public static void traverseLog(String name, int depth, int score, int alpha, int beta) {
		if (traverseLogWriter == null) {
			try {
				traverseLogWriter = new PrintWriter("traverse_log.txt");
			} catch (FileNotFoundException e) {
				File f = new File("traverse_log.txt");
				try {
					f.createNewFile();
					traverseLogWriter = new PrintWriter("traverse_log.txt");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
			System.out.println("Node,Depth,Value,Alpha,Beta");
		}
		String a;
		String b;
		String v;
		if (alpha == Integer.MIN_VALUE)
			a = "-Infinity";
		else if (alpha == Integer.MAX_VALUE) {
			a = "Infinity";
		} else {
			a = alpha + "";
		}
		if (beta == Integer.MIN_VALUE)
			b = "-Infinity";
		else if (beta == Integer.MAX_VALUE) {
			b = "Infinity";
		} else {
			b = beta + "";
		}
		if (score == Integer.MIN_VALUE)
			v = "-Infinity";
		else if (score == Integer.MAX_VALUE) {
			v = "Infinity";
		} else {
			v = score + "";
		}
		traverseLogWriter.println(name + "," + depth + "," + v + "," + a + "," + b);
	}

	public static void writeState(String state) {
		if (stateWriter == null) {
			try {
				stateWriter = new PrintWriter("next_state.txt");
			} catch (FileNotFoundException e) {
				File f = new File("next_state.txt");
				try {
					f.createNewFile();
					stateWriter = new PrintWriter("next_state.txt");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
			stateWriter.print(state);
		}
	}
}
