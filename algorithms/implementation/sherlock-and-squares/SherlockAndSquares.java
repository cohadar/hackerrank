import java.util.*;
import java.io.*;

public class SherlockAndSquares {

	void solve(int a, int b) {
		// lets avoid special cases with a special algo
		if (b - a < 100) {
			int count = 0;
			for (int i = a; i <= b; i++) {
				int sqrt_i = (int)Math.floor(Math.sqrt(i));
				if (sqrt_i * sqrt_i == i) {
					count++;
				}
			}
			System.out.println(count);
			return;
		}
		// now for big lengths
		int sqrt_a = (int)Math.floor(Math.sqrt(a));
		int sqrt_b = (int)Math.floor(Math.sqrt(b));
		int low = -1;
		for (int i = sqrt_a; i < sqrt_b; i++) {
			if (a <= i * i) {
				low = i;
				break;
			}
		}
		assert low > 0;
		int high = -1;
		for (int i = sqrt_b; i >= sqrt_a; i--) {
			if (i * i <= b) {
				high = i;
				break;
			}
		}
		assert high > 0;
		System.out.println(high - low + 1);
	}

	static void load(Scanner scanner) {
		int t = scanner.nextInt();
		for (int i = 0; i < t; i++) {
			int a = scanner.nextInt();
			int b = scanner.nextInt();
			new SherlockAndSquares().solve(a, b);
		}
	}

	public static void main(String[] args) throws Exception {
		Scanner scanner;
		if (args.length == 1 && "COHADAR".equals(args[0])) {
			scanner = new Scanner(new File("sherlock-and-squares.in"));
		} else {
			scanner = new Scanner(System.in);
		}
		load(scanner);
	}
}
