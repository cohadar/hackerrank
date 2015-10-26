import java.util.*;
import java.io.*;

public class ACMICPCTeam {

	static int together(String a, String b, int nx) {
		int together = 0;
		for (int i = 0; i < nx; i++) {
			if (a.charAt(i) == '1' || b.charAt(i) == '1') {
				together++;
			}
		}
		return together;
	}

	static void solve(String[] P, int ny, int nx) {
		debug(P, nx, ny);
		// index is max topics together, value is number of teams
		int[] teams = new int[nx + 1];  // <----<< +1
		int imax = 0;
		for (int a = 0; a < ny - 1; a++) {
			for (int b = a + 1; b < ny; b++) {
				int t = together(P[a], P[b], nx);
				teams[t]++;
				if (imax < t) {
					imax = t;
				}
			}
		}
		System.out.println(imax);
		System.out.println(teams[imax]);

	}

	static void load(Scanner scanner) {
		String[] _nm = scanner.nextLine().split(" ");
		int n = Integer.valueOf(_nm[0]);
		int m = Integer.valueOf(_nm[1]);
		String[] P = new String[n];
		for (int i = 0; i < n; i++) {
			P[i] = scanner.nextLine();
			assert P[i].length() == m;
		}
		solve(P, n, m);
	}

	public static void main(String[] args) throws Exception {
		Scanner scanner;
		if (args.length == 1 && "COHADAR".equals(args[0])) {
			scanner = new Scanner(new File("ACMICPCTeam.in"));
		} else {
			scanner = new Scanner(System.in);
		}
		load(scanner);
	}

	static void debug(Object...os) {
		System.err.println(Arrays.deepToString(os));
	}

}

