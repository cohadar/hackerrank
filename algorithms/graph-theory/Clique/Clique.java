import java.util.*;
import java.io.*;

/**
  * @author Mighty Cohadar 
  * @link 
  */
public class Clique {

	static long turan(int n, int r) {
		long n2 = (long)n * (long)n;
		long nmr = n % r;
		long c = (long)Math.ceil((double)n / (double)(r));
		long f = (long)Math.floor((double)n / (double)(r));
		return (n2 - nmr * c * c - (r - nmr) * f * f) / 2;
	}

	static int lowerBound(int left, int right, int value) {
		int n = right;
		right--;
		while (left <= right) {
			int mid = (left + right) >>> 1;
			int midValue = (int)turan(n, mid);
			if (midValue >= value) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return left;
	}	

	static int solve(final int n, int m) {
		return lowerBound(1, n, m);
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int t = scanner.nextInt();
		assert 1 <= t && t <= 1e5 : "out of range, t: " + t;
		while (t-->0) {
			int n = scanner.nextInt();
			int m = scanner.nextInt();
			assert 2 <= n && n <= 1e4 : "out of range, n: " + n;
			assert 1 <= m && m <= n * (n - 1) / 2 : "out of range, m: " + m;
			sb.append(solve(n, m));
			sb.append('\n');
		}
		System.out.println(sb);
	}

}
