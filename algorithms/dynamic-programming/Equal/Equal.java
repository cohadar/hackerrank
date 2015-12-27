import java.util.*;
import java.io.*;

/**
  * @author Mighty Cohadar 
  */
public class Equal {

	final int[] A;

	public Equal(int[] A) {
		this.A = A;
		Arrays.sort(this.A);
	}

	public int solve(int a, int target) {
		int d = a - target;
		int sum = d / 5;
		d %= 5;
		sum += d / 2;
		d %= 2;
		sum += d;
		return sum;
	}

	public int solve(int target) {
		int sum = 0;
		for (int i = 0; i < A.length; i++) {
			sum += solve(A[i], target);
		}
		return sum;
	}

	public int solve() {
		int min = Integer.MAX_VALUE;
		int target = A[0];
		for (int i = 0; i < 5; i++) {
			int v = solve(target--);
			min = Math.min(min, v);
			debug(v);
		}
		return min;
	}

	static Equal load(Scanner scanner) {
		int n = scanner.nextInt();
		assert 1 <= n && n <= 1e4 : "out of range, n: " + n;
		int[] A = scanArray(scanner, n);
		return new Equal(A);
	}	

	public static void main(String[] args) throws Exception {
		// generate(new PrintWriter(new FileWriter("Equal.large.in")));
		Scanner scanner = new Scanner(System.in);
		int t = scanner.nextInt();		
		assert 1 <= t && t <= 100 : "out of range, t: " + t;
		while (t-->0) {
			Equal o = Equal.load(scanner);
			System.out.println(o.solve());
		}
	}

	static boolean DEBUG = false;
	
	static void debug(Object...os) {
		if (!DEBUG) { return; }
		System.err.printf("%.65536s\n", Arrays.deepToString(os));
	}	

	static int[] scanArray(Scanner scanner, int n) {
		int[] A = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = scanner.nextInt();
		}
		return A;
	}

	static Random random = new Random();

	// [low, high]
	static int nextInt(int low, int high) {
		return low + random.nextInt(high - low + 1);
	}

	public static void generate(PrintWriter out) {
		StringBuilder sb = new StringBuilder();
		int t = 100;
		sb.append(t);
		sb.append('\n');
		while (t-->0) {
			int n = nextInt(1, (int)1e4);
			sb.append(n);
			sb.append('\n');
			for (int i = 0; i < n; i++) {
				sb.append(nextInt(0, 999));
				sb.append(' ');
			}
			sb.append('\n');
		}
		out.print(sb);
		out.close();
	}

}
