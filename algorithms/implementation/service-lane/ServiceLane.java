import java.util.*;
import java.io.*;

public class ServiceLane {

	void solve(int[] W, int a, int b) {
		int m = 3;
		for (int i = a; i <= b; i++) {
			if (W[i] < m) {
				m = W[i];
			}
		}
		System.out.println(m);
	}

	static void load(Scanner scanner) {
		int n = scanner.nextInt();
		int t = scanner.nextInt();
		int[] W = new int[n];
		for (int i = 0; i < n; i++) {
			W[i] = scanner.nextInt();
		}
		for (int i = 0; i < t; i++) {
			int a = scanner.nextInt();
			int b = scanner.nextInt();
			new ServiceLane().solve(W, a, b);
		}
	}

	public static void main(String[] args) throws Exception {
		Scanner scanner;
		if (args.length == 1 && "COHADAR".equals(args[0])) {
			scanner = new Scanner(new File("service-lane.in"));
		} else {
			scanner = new Scanner(System.in);
		}
		load(scanner);
	}
}
