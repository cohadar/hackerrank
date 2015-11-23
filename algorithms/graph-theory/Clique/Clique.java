import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class Clique {

	// maximum number of m in a graph with no (r + 1) cliques
	// if m is bigger it has at least an (r + 1) clique
	static int turan(int n, int r) {
		long res = n * n;
		res *= (r - 1);
		res /= (2 * r);
		return (int)res;
	}

	static int solve(int n, int m) {
		int low = 1;
		int high = n;
		int ret = 1;
		while (low <= high) {
			if (high - low <= 16) {
				for (int r = low; r <= high; r++) {
					int t = turan(n, r);
					if (t < m) {
						ret = r + 1;
					} else {
						break;
					}
				}
				return ret;
			}
			int mid = (low + high) >>> 1;
			int t = turan(n, mid);
			if (t < m) {
				ret = Math.max(ret, mid + 1);
				low = mid;
			} else {
				high = mid;
			}
		}
		assert false : "grrrrr";
		return 1;
	}

	static int solve2(int n, int m) {
		int r = 1;
		while (turan(n, r) < m) {
			r++;
		}
		return r;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int t = scanner.nextInt();
		assert 1 <= t && t <= 1e5 : "out of range, t: " + t;
		int max_n = (int)1e4;
		while (t-->0) {
			int n = scanner.nextInt();
			int m = scanner.nextInt();
			assert 2 <= n && n <= 1e4 : "out of range, n: " + n;
			assert 1 <= m && m <= n * (n - 1) / 2 : "out of range, m: " + m;
			System.out.println(solve2(n, m));
		}
	}

}

