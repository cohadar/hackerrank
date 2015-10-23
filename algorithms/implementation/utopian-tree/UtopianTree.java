import java.util.*;
import java.io.*;

public class UtopianTree {

	void solve(int n) {
		int height = 1;
		int i = 0;
		while (true) {
			if (i >= n) break;
			height *= 2;
			i++;
			if (i >= n) break;
			height += 1;
			i++;
		}
		System.out.println(height);
	}

	static void load(Scanner scanner) {
		int t = scanner.nextInt();
		for (int i = 0; i < t; i++) {
			int n = scanner.nextInt();
			new UtopianTree().solve(n);
		}
	}

	public static void main(String[] args) throws Exception {
		Scanner scanner;
		if (args.length == 1 && "COHADAR".equals(args[0])) {
			scanner = new Scanner(new File("utopian-tree.in"));
		} else {
			scanner = new Scanner(System.in);
		}
		load(scanner);
	}
}
