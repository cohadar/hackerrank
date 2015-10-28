import java.util.*;
import java.io.*;

public class SherlockAndArray {

	static boolean solve(int[] a) {
		int[] lsum = new int[a.length];
		lsum[0] = a[0];
		for (int i = 1; i < a.length; i++) {
			lsum[i] = lsum[i - 1] + a[i];
		}
		int rsum = 0;
		for (int i = a.length - 1; i >= 0; i--) {
			rsum += a[i];
			if (lsum[i] == rsum) {
				return true;
			}
		}
		return false;
	}

	static void scan(Scanner scanner) {
		int t = Integer.valueOf(scanner.nextLine());
		for (int i = 0; i < t; i++) {
			int n = Integer.valueOf(scanner.nextLine());
			int[] a = scanArray(scanner, n);
			System.out.println((solve(a)) ? "YES" : "NO");
		}
	}

	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		if (args.length == 1 && "COHADAR".equals(args[0])) {
			scanner = new Scanner(new File("SherlockAndArray.in"));
		}
		scan(scanner);
	}

	static int[] scanArray(Scanner scanner, int n) {
		String line = scanner.nextLine();
		Scanner arrayScanner = new Scanner(line);
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = arrayScanner.nextInt();
		}
		return a;
	}

	static void debug(Object...os) {
		System.err.println(Arrays.deepToString(os));
	}

}

