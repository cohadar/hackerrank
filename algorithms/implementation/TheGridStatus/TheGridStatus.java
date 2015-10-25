import java.util.*;
import java.io.*;

public class TheGridStatus {

	static boolean match(String[] G, String[] P, int gy, int gx) {
		for (int py = 0; py < P.length; py++) {
			for (int px = 0; px < P[0].length(); px++) {
				if (P[py].charAt(px) != G[gy + py].charAt(gx + px)) {
					return false;
				}
			}
		}
		debug(String.format("\nMATCH(%d, %d)\n", gy, gx));
		return true;
	}

	static boolean contains(String[] G, String[] P) {
		int gy = G.length;
		int gx = G[0].length();
		int py = P.length;
		int px = P[0].length();
		for (int y = 0; y <= gy - py; y++) {
			for (int x = 0; x <= gx - px; x++) {
				if (match(G, P, y, x)) {
					return true;
				}
			}
		}
		return false;
	}

	static String[] loadMatrix(Scanner scanner) {
		String[] _rc = scanner.nextLine().split(" ");
		int r = Integer.valueOf(_rc[0]);
		int c = Integer.valueOf(_rc[1]);
		debug(r, c);
		String[] M = new String[r];
		for (int j = 0; j < r; j++) {
			M[j] = scanner.nextLine();
			assert M[j].length() == c;
			debug(M[j]);
		}
		return M;
	}

	static void load(Scanner scanner) {
		int t = Integer.valueOf(scanner.nextLine());
		debug(t);
		for (int i = 0; i < t; i++) {
			String[] G = loadMatrix(scanner);
			String[] P = loadMatrix(scanner);
			System.out.println((contains(G, P)) ? "YES" : "NO");
		}
	}

	public static void main(String[] args) throws Exception {
		Scanner scanner;
		if (args.length == 1 && "COHADAR".equals(args[0])) {
			scanner = new Scanner(new File("TheGridStatus.in"));
		} else {
			scanner = new Scanner(System.in);
		}
		load(scanner);
	}

	static void debug(Object...os) {
		// System.err.println(Arrays.deepToString(os));
	}

}